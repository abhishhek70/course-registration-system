import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Administrator extends User {

    Scanner scanner = new Scanner(System.in);

    public Administrator(String password, CourseRegistrationSystem erp) {
        super(null, password, erp);
    }

    public void changeGrade(Student student, Course course, int newGrade) {
        List<Course> completedCourses = student.getCompletedCourses();
        List<Integer> grades = student.getGradePoints();
        boolean completed = false;
        int pos = 0;
        for (Course c : completedCourses) {
            if (c.getCourseCode().equals(course.getCourseCode())) {
                completed = true;
                break;
            }
            pos++;
        }
        if (!completed) {
            System.out.println("Course not completed.");
        } else {
            grades.set(pos, newGrade);
            System.out.println("Grade Point updated successfully.");
        }
    }

    @Override
    void showMenu() {
        System.out.println("-> Choose one of the following commands [Administrator]");
        System.out.println("1. Manage Course Catalog");
        System.out.println("2. Manage Student Records");
        System.out.println("3. Assign Professors to Courses");
        System.out.println("4. Handle Complaints");
        System.out.println("5. Logout");
    }

    private void manageCourseCatalog() {
        while (true) {
            showOptionsManageCourses();
            System.out.print("Enter command: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                viewCourse();
            } else if (choice == 2) {
                addCourse();
            } else if (choice == 3) {
                deleteCourse();
            } else if (choice == 4) {
                changeCourseDropDeadline();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Enter a valid Command number.");
            }
            System.out.println(" ");
        }
    }

    private void showOptionsManageCourses() {
        System.out.println("-> Choose one of the following commands [Manage Course Catalog]");
        System.out.println("1. View course.");
        System.out.println("2. Add course.");
        System.out.println("3. Delete course.");
        System.out.println("4. Change Course Drop Deadline.");
        System.out.println("5. Back to Administrator Menu.");
    }

    private void viewCourse() {
        List<Course> allCourses = erp.getAllCourses();
        System.out.print("Enter course code: ");
        String code = scanner.next();
        int pos = searchCourse(allCourses, code);
        if (pos == -1) {
            System.out.println("Invalid course code.");
        } else {
            Course course = allCourses.get(pos);
            course.showDetailsAdmin();
        }
    }

    private void addCourse() {
        List<Professor> allProfessor = erp.getProfessors();
        List<Course> allCourses = erp.getAllCourses();
        System.out.println("Enter following course details to add a new course.");
        System.out.print("Enter course code: ");
        String code = scanner.next();
        scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter credits: ");
        int credits = scanner.nextInt();
        System.out.print("Enter number of prerequisite courses: ");
        int numPrereq = scanner.nextInt();
        List<Course> preReq = new ArrayList<>();
        for (int i=0; i<numPrereq; i++) {
            System.out.print("Enter Course Code of Prerequisite course " + i + ": ");
            String code1 = scanner.next();
            int pos = searchCourse(allCourses, code1);
            if (pos == -1) {
                System.out.println("Invalid Course Code.");
                i--;
            } else {
                Course course = allCourses.get(i);
                preReq.add(course);
            }
        }
        scanner.nextLine();
        System.out.print("Enter timings (TBA if not decided): ");
        String timings = scanner.nextLine();
        if (timings.equals("TBA")) {
            timings = null;
        }
        System.out.print("Enter location (TBA if not decided): ");
        String location = scanner.nextLine();
        if (location.equals("TBA")) {
            location = null;
        }
        System.out.print("Enter Enrollment Limit (-1 if no limit): ");
        int limit = scanner.nextInt();
        Course newCourse = new Course(code, title, credits, preReq, null, timings, location, null, limit, null);
        for (Professor prof : allProfessor) {
            Course profCourse = prof.getCourse();
            if (profCourse == null) {
                prof.setCourse(newCourse);
                newCourse.setProfessor(prof);
                break;
            }
        }
        allCourses.add(newCourse);
        System.out.println("Course added successfully");
    }

    private void deleteCourse() {
        List<Course> allCourses = erp.getAllCourses();
        List<Student> allStudents = erp.getStudents();
        System.out.print("Enter course code: ");
        String code = scanner.next();
        int pos = searchCourse(allCourses, code);
        if (pos != -1) {
            allCourses.remove(pos);
            for (Student student : allStudents) {
                List<Course> regCourses = student.getRegisteredCourses();
                int pos1 = searchCourse(regCourses, code);
                if (pos1 != -1) {
                    int currCredits = student.getCredits();
                    int courseCredits = regCourses.get(pos1).getCredits();
                    int updatedCredits = currCredits - courseCredits;
                    student.setCredits(updatedCredits);
                    regCourses.remove(pos1);
                }
            }
            System.out.println("Course successfully deleted.");
        } else {
            System.out.println("Invalid course code.");
        }
    }

    private void changeCourseDropDeadline() {
        if (erp.getCourseDropDeadlinePassed()) {
            erp.setCourseDropDeadlinePassed(false);
        } else {
            erp.setCourseDropDeadlinePassed(true);
        }
        System.out.println("Course Deadline successfully dropped.");
    }

    private void manageStudentRecords() {
        while (true) {
            showOptionsManageStudents();
            System.out.print("Enter command: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                viewStudent();
            } else if (choice == 2) {
                viewStudentGrade();
            } else if (choice == 3) {
                assignStudentGrade();
            } else if (choice == 4) {
                updateStudentGrade();
            }
            else if (choice == 5) {
                break;
            } else {
                System.out.println("Enter a valid Command number.");
            }
            System.out.println(" ");
        }
    }

    private void showOptionsManageStudents() {
        System.out.println("-> Choose one of the following commands [Manage Student Records]");
        System.out.println("1. View student record and personal information.");
        System.out.println("2. View grades of a student.");
        System.out.println("3. Assign grade after declaration of result.");
        System.out.println("4. Update student's grade of an completed course.");
        System.out.println("5. Back to Administrator Menu.");
    }

    private void viewStudent() {
        List<Student> allStudents = erp.getStudents();
        System.out.print("Enter Student's Email: ");
        String email = scanner.next();
        int pos = searchStudent(allStudents, email);
        if (pos == -1) {
            System.out.println("Student Not Found.");
        } else {
            Student stud = allStudents.get(pos);
            stud.viewDetailsAdmin();
        }
    }

    private void viewStudentGrade() {
        List<Student> allStudents = erp.getStudents();
        System.out.print("Enter Student's Email: ");
        String email = scanner.next();
        int pos = searchStudent(allStudents, email);
        if (pos == -1) {
            System.out.println("Student Not Found.");
        } else {
            Student stud = allStudents.get(pos);
            System.out.print("Enter Course Code: ");
            String code = scanner.next();
            int grade = stud.viewGrade(code);
            if (grade == -1) {
                System.out.println("Student has not completed this course.");
            } else {
                System.out.println("Grade Point obtained: " + grade);
            }
        }
    }

    private void assignStudentGrade() {
        List<Student> allStudents = erp.getStudents();
        System.out.print("Enter Student's Email: ");
        String email = scanner.next();
        int pos = searchStudent(allStudents, email);
        if (pos == -1) {
            System.out.println("Student Not Found.");
        } else {
            Student stud = allStudents.get(pos);
            List<Course> regCourses = stud.getRegisteredCourses();
            List<Course> completedCourses = stud.getCompletedCourses();
            List<Integer> grades = stud.getGradePoints();
            System.out.print("Enter Course Code: ");
            String code = scanner.next();
            int pos1 = searchCourse(regCourses, code);
            if (pos1 == -1) {
                System.out.println("This course was not registered by the student.");
            } else {
                Course completed = regCourses.remove(pos1);
                completedCourses.add(completed);
                System.out.println("Enter the grade point to be assigned: ");
                int points = scanner.nextInt();
                grades.add(points);
                int sz = regCourses.size();
                if (sz == 0) {
                    int sem = stud.getSemester();
                    stud.setSemester(sem+1);
                }
                System.out.println("Grade point assigned successfully.");
            }
        }
    }

    private void updateStudentGrade() {
        List<Student> allStudents = erp.getStudents();
        System.out.print("Enter Student's Email: ");
        String email = scanner.next();
        int pos = searchStudent(allStudents, email);
        if (pos == -1) {
            System.out.println("Student Not Found.");
        } else {
            Student stud = allStudents.get(pos);
            List<Course> completedCourses = stud.getCompletedCourses();
            List<Integer> grades = stud.getGradePoints();
            System.out.print("Enter Course Code: ");
            String code = scanner.next();
            int pos1 = searchCourse(completedCourses, code);
            if (pos1 == -1) {
                System.out.println("This course has not been completed by the student.");
            } else {
                System.out.println("Enter the new grade point to be assigned: ");
                int points = scanner.nextInt();
                grades.set(pos1, points);
                System.out.println("Grade point updated successfully.");
            }
        }
    }

    private void assignProfessorsToCourses() {
        List<Professor> allProfessor = erp.getProfessors();
        List<Course> allCourses = erp.getAllCourses();
        System.out.print("Enter course code: ");
        String code = scanner.next();
        int pos = searchCourse(allCourses, code);
        if (pos != -1) {
            for (Professor prof : allProfessor) {
                Course profCourse = prof.getCourse();
                if (profCourse == null) {
                    Course course = allCourses.get(pos);
                    prof.setCourse(course);
                    course.setProfessor(prof);
                    break;
                }
            }
            System.out.println("Professor assigned successfully.");
        } else {
            System.out.println("Invalid Course Code.");
        }
    }

    private void handleComplaints() {
        while (true) {
            showOptionsComplaints();
            System.out.print("Enter command: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                viewAllComplaints();
            } else if (choice == 2) {
                changeComplaintStatus();
            } else if (choice == 3) {
                viewPendingComplaints();
            } else if (choice == 4) {
                viewResolvedComplaints();
            }
            else if (choice == 5) {
                break;
            } else {
                System.out.println("Enter a valid Command number.");
            }
            System.out.println(" ");
        }
    }

    private void showOptionsComplaints() {
        System.out.println("-> Choose one of the following commands [Handle Complaints]");
        System.out.println("1. View all submitted complaints.");
        System.out.println("2. Update status of a complaint.");
        System.out.println("3. View only pending complaints");
        System.out.println("4. View only resolved complaints");
        System.out.println("5. Back to Administrator Menu.");
    }

    private void viewAllComplaints() {
        List<Complaint> pendingComp = erp.getPendingComplaints();
        List<Complaint> resolvedComp = erp.getResolvedComplaints();
        for (Complaint comp : pendingComp) {
            comp.showComplaint();
        }
        for (Complaint comp : resolvedComp) {
            comp.showComplaint();
        }
    }

    private void changeComplaintStatus() {
        List<Complaint> pendingComp = erp.getPendingComplaints();
        List<Complaint> resolvedComp = erp.getResolvedComplaints();
        System.out.println("Enter complaint id: ");
        int id = scanner.nextInt();
        boolean found = false;
        int pos = 0;
        for (Complaint comp : pendingComp) {
            if (comp.getId() == id) {
                found = true;
                break;
            }
            pos++;
        }
        if (!found) {
            System.out.println("Invalid id.");
            return;
        }
        Complaint comp = pendingComp.remove(pos);
        comp.setStatus(1);
        resolvedComp.add(comp);
        System.out.println("Complaint resolved successfully.");
    }

    private void viewPendingComplaints() {
        List<Complaint> pendingComp = erp.getPendingComplaints();
        for (Complaint comp : pendingComp) {
            comp.showComplaint();
        }
    }

    private void viewResolvedComplaints() {
        List<Complaint> resolvedComp = erp.getResolvedComplaints();
        for (Complaint comp : resolvedComp) {
            comp.showComplaint();
        }
    }

    private int searchCourse(List<Course> ls, String code) {
        int sz = ls.size();
        int pos = -1;
        for (int i=0; i<sz; i++) {
            if (ls.get(i).getCourseCode().equals(code)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    private int searchStudent(List<Student> ls, String email) {
        int sz = ls.size();
        int pos = -1;
        for (int i=0; i<sz; i++) {
            if (ls.get(i).getEmail().equals(email)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    @Override
    void runUser() {
        while (true) {
            showMenu();
            System.out.print("Enter command number: ");
            int choice = scanner.nextInt();
            System.out.println(" ");
            if (choice == 1) {
                manageCourseCatalog();
            } else if (choice == 2) {
                manageStudentRecords();
            } else if (choice == 3) {
                assignProfessorsToCourses();
            } else if (choice == 4) {
                handleComplaints();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Enter a valid command.");
            }
            System.out.println(" ");
        }
    }
}
