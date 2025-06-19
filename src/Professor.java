import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Professor extends User {

    Scanner scanner = new Scanner(System.in);

    private String name;
    private Course course;
    private Administrator admin;

    List<Course> allCourses;
    List<Student> allStudents;
    private List<Feedback<Integer>> intFeedback;
    private List<Feedback<String>> strFeedback;

    public Professor(String name, String email, String password, CourseRegistrationSystem erp, Administrator admin) {
        super(email, password, erp);
        this.name = name;
        this.admin = admin;
        course = null;
        allCourses = erp.getAllCourses();
        allStudents = erp.getStudents();
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    @Override
    void showMenu() {
        System.out.println("-> Choose one of the following commands [Professor]");
        System.out.println("1. Manage Courses");
        System.out.println("2. View Enrolled Students");
        System.out.println("3. Logout");
        System.out.print("Enter command number: ");
    }

    private void manageCourse() {
        if (course == null) {
            System.out.println("You are not teaching any course this semester.");
            return;
        }
        while (true) {
            showOptionsManageCourse();
            System.out.print("Enter command number: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                course.showDetailsProfessor();
            } else if (choice == 2) {
                changeCredits();
            } else if (choice == 3) {
                changePrerequisite();
            } else if (choice == 4) {
                changeEnrollementLimits();
            } else if (choice == 5) {
                changeClassTimings();
            } else if (choice == 6) {
                changeLocation();
            } else if (choice == 7) {
                changeSyllabus();
            } else if (choice == 8) {
                changeOfficeHours();
            } else if (choice == 9) {
                viewFeedback();
            } else if (choice == 10) {
                addTAs();
            } else if (choice == 11) {
                viewTA();
            } else if (choice == 12) {
                break;
            } else {
                System.out.println("Enter a valid Command number.");
            }
            System.out.println(" ");
        }
    }

    private void changeCredits() {
        System.out.print("Enter updated credits: ");
        int updatedCredits = scanner.nextInt();
        course.setCredits(updatedCredits);
        System.out.println("Course credits successfully updated.");
    }

    private void changePrerequisite() {
        System.out.println("1. Add prerequisite courses");
        System.out.println("2. Remove prerequisite courses");
        System.out.println("3. Back");
        int choice = scanner.nextInt();
        List<Course> preReq = course.getPrerequisite();
        if (choice == 1) {
            System.out.print("Enter course code of the course that need to be added as a prerequisite: ");
            String code = scanner.next();
            int pos = searchCourse(allCourses, code);
            if (pos == -1) {
                System.out.println("Invalid Course Code.");
            } else {
                int pos1 = searchCourse(preReq, code);
                if (pos1 != -1) {
                    System.out.println("Given course is already a prerequisite.");
                } else {
                    Course newPreReq = allCourses.get(pos);
                    preReq.add(newPreReq);
                    System.out.println("Given course is now a prerequisite.");
                    System.out.println("Prerequisites updated successfully.");
                }
            }
        } else if (choice == 2) {
            System.out.print("Enter course code of the course that need to be removed as a prerequisite: ");
            String code = scanner.next();
            int pos = searchCourse(allCourses, code);
            if (pos == -1) {
                System.out.println("Invalid Course Code.");
            } else {
                int pos1 = searchCourse(preReq, code);
                if (pos1 == -1) {
                    System.out.println("Given course is not an existing prerequisite.");
                } else {
                    preReq.remove(pos1);
                    System.out.println("Given course is removed as a prerequisite.");
                    System.out.println("Prerequisites updated successfully.");
                }
            }
        } else if (choice == 3) {
            return;
        } else {
            System.out.println("Invalid command.");
        }
    }

    private void changeEnrollementLimits() {
        System.out.print("Enter updated limit: ");
        int limit = scanner.nextInt();
        course.setEnrollmentLimit(limit);
        System.out.println("Enrollment Limits successfully changed.");
    }

    private void changeClassTimings() {
        scanner.nextLine();
        System.out.println("Enter updated timings: ");
        String timing = scanner.nextLine();
        course.setTimings(timing);
        System.out.println("Class timings successfully changed.");
    }

    private void changeLocation() {
        scanner.nextLine();
        System.out.println("Enter updated location: ");
        String location = scanner.nextLine();
        course.setLocation(location);
        System.out.println("Course Location successfully changed.");
    }

    private void changeSyllabus() {
        scanner.nextLine();
        System.out.println("Enter updated syllabus: ");
        String syllabus = scanner.nextLine();
        course.setSyllabus(syllabus);
        System.out.println("Course syllabus successfully changed.");
    }

    private void changeOfficeHours() {
        scanner.nextLine();
        System.out.println("Enter updated Office Hours: ");
        String officeHours = scanner.nextLine();
        course.setOfficeHours(officeHours);
        System.out.println("Course office hours successfully changed.");
    }

    private void viewFeedback() {
        intFeedback = course.getIntFeedback();
        strFeedback = course.getStrFeedback();
        System.out.println("Course Rating (out of 5) as submitted by students:");
        int count = 0;
        float sm = 0;
        for (Feedback<Integer> feedback : intFeedback) {
            Integer rating = feedback.getFeedback();
            sm += rating;
            System.out.print(rating);
            if (count != intFeedback.size()-1) {
                System.out.print(", ");
            }
            count++;
        }
        System.out.print("\n");
        float averageRating = sm/count;
        System.out.printf("Average Rating: %.2f", averageRating);
        System.out.print("\n");
        System.out.println("Textual Feedback as submitted bu students:");
        count = 1;
        for (Feedback<String> feedback : strFeedback) {
            String text = feedback.getFeedback();
            System.out.println(count + ". " + text);
            count++;
        }
    }

    private void addTAs() {
        allStudents = erp.getStudents();
        System.out.print("Enter Email of student that need to be made a TA: ");
        String email = scanner.next();
        Student student = null;
        for (Student s : allStudents) {
            if (s.getEmail().equals(email)) {
                student = s;
            }
        }
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            TeachingAssistant ta = new TeachingAssistant(student, erp, course, admin);
            course.addTA(ta);
            erp.addTA(ta);
            System.out.println("TA successfully added.");
        }
    }

    private void viewTA() {
        course.viewTAs();
    }

    private void showOptionsManageCourse() {
        System.out.println("-> Choose one of the following commands [Manage Course]");
        System.out.println("1. View course details.");
        System.out.println("2. Change course credits");
        System.out.println("3. Change course prerequisite");
        System.out.println("4. Change course enrollment limits");
        System.out.println("5. Change class timings");
        System.out.println("6. Change course location");
        System.out.println("7. Change course syllabus");
        System.out.println("8. Change course office hours");
        System.out.println("9. View Feedback");
        System.out.println("10. Add a New Teaching Assistant");
        System.out.println("11. View all TAs");
        System.out.println("12. Back to Professor Menu.");
    }

    private void viewEnrolledStudents() {
        if (course == null) {
            System.out.println("You are not teaching any course this semester.");
            return;
        }
        List<Student> enrolledStudents = new ArrayList<>();
        List<Student> students = erp.getStudents();
        for (Student student : students) {
            List<Course> reg = student.getRegisteredCourses();
            for (Course regCourse : reg) {
                if (regCourse.getCourseCode().equals(course.getCourseCode())) {
                    enrolledStudents.add(student);
                }
            }
        }
        for (Student enrollStudent : enrolledStudents) {
            System.out.print("Name: " + enrollStudent.getName() + ", Email: " + enrollStudent.getEmail() + ", Semester: " + enrollStudent.getSemester());
            float cgpa = enrollStudent.getCGPA();
            if (cgpa == -1) {
                System.out.println(", CGPA: Currently in 1st semester");
            } else {
                System.out.println(", CGPA: " + cgpa);
            }
        }
    }

    @Override
    public void runUser() {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            System.out.println(" ");
            if (choice == 1) {
                manageCourse();
            } else if (choice == 2) {
                viewEnrolledStudents();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Enter a valid command.");
            }
            System.out.println(" ");
        }
    }
}
