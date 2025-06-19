import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

class Student extends User {

    Scanner scanner = new Scanner(System.in);

    private String name;
    private List<Course> availableCourses;
    private List<Course> registeredCourses;
    private List<Course> completedCourses;
    private List<Integer> gradePoints;
    private List<Complaint> complaints;
    private HashSet<String> feedbackSubmitted;
    private int credits;
    private int semester;
    private float sgpa;

    public Student(String name, String email, String password, CourseRegistrationSystem erp) {
        super(email, password, erp);
        this.name = name;
        this.registeredCourses = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
        this.gradePoints = new ArrayList<>();
        this.complaints = new ArrayList<>();
        this.feedbackSubmitted = new HashSet<>();
        this.credits = 0;
        this.semester = 1;
        this.sgpa = -1;
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

    public int getSemester() {
        return this.semester;
    }

    public float getCGPA() {
        return calcCGPA();
    }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int updatedCredits) {
        this.credits = updatedCredits;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public List<Course> getCompletedCourses() {
        return completedCourses;
    }

    public List<Integer> getGradePoints() {
        return gradePoints;
    }

    public void setSemester(int semNew) {
        this.semester = semNew;
    }

    public void setSgpa(float sgpa) {
        this.sgpa = sgpa;
    }

    public void viewDetailsAdmin() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Semester: " + semester);
        float cgpa = calcCGPA();
        System.out.println("CGPA: " + cgpa);
    }

    public int viewGrade(String code) {
        int i = 0;
        for (Course course : completedCourses) {
            if (course.getCourseCode().equals(code)) {
                return gradePoints.get(i);
            }
            i++;
        }
        return -1;
    }

    @Override
    void showMenu() {
        System.out.println("-> Choose one of the following commands [Student]");
        System.out.println("1. View Available Courses");
        System.out.println("2. Register for Courses");
        System.out.println("3. View Schedule");
        System.out.println("4. Track Academic Progress");
        System.out.println("5. Drop Courses");
        System.out.println("6. Submit Complaints");
        System.out.println("7. View Complaints");
        System.out.println("8. Give Feedback");
        System.out.println("9. Logout");
        System.out.print("Enter command number: ");
    }

    void viewAvailableCourses() {
        availableCourses = erp.getAllCourses();
        for (Course course: availableCourses) {
            course.showDetailsStudent();
        }
    }

    void registerCourse() {
        System.out.print("Enter course code: ");
        String code = scanner.next();
        availableCourses = erp.getAllCourses();
        boolean success = false;
        for (Course course: availableCourses) {
            if (course.getCourseCode().trim().equals(code.trim())) {
                if (canRegister(course)) {
                    registeredCourses.add(course);
                    credits += course.getCredits();
                    success = true;
                    course.incCount();
                }
            }
        }
        if (success) {
            System.out.println("Course Registration Successful.");

        } else {
            System.out.println("Course Registration Failed.");
        }
    }

    boolean canRegister (Course course) {
        try {
            if (course.isFull()) {
                throw new CourseFullException("Cannot Register. Course is already full.");
            }
            if (credits + course.getCredits() > 20) {
                System.out.println("Cannot Register. Credits are getting over 20.");
                return false;
            }
            List<Course> prerequisite = course.getPrerequisite();
            int done = 0;
            int total = prerequisite.size();
            for (Course preq : prerequisite) {
                String code = preq.getCourseCode();
                for (Course cmpCourse : completedCourses) {
                    if (cmpCourse.getCourseCode().equals(code)) {
                        done += 1;
                    }
                }
            }
            if (done != total) {
                System.out.println("Cannot Register. Prerequisite courses not completed.");
                return false;
            }
            return true;
        }
        catch (CourseFullException cfe) {
            System.out.println(cfe.getMessage());
            return false;
        }
    }

    private void viewSchedule() {
        for (Course regCourse : registeredCourses) {
            regCourse.showSchedule();
        }
    }

    private void trackAcademicProgress() {
        float cgpa = calcCGPA();
        float sgpa = calcSGPA();
        int numCmp = completedCourses.size();
        System.out.println("Number of Completed Course: " + numCmp);
        for (int i=0; i<numCmp; i++) {
            String code = completedCourses.get(i).getCourseCode();
            String title = completedCourses.get(i).getTitle();
            int score = gradePoints.get(i);
            System.out.println(code + " " + title + " : " + score);
        }
        System.out.println(" ");
        if (semester == 1) {
            System.out.println("First Semester not completed.");
        } else {
            System.out.println("CGPA: " + cgpa);
            System.out.println("SGPA: " + sgpa);
        }
    }

    private float calcCGPA() {
        float sum1 = 0;
        float sum2 = 0;
        int sz = gradePoints.size();
        if (sz == 0) {
            return -1;
        }
        for (int i=0; i<sz; i++) {
            float currCredits = (completedCourses.get(i)).getCredits();
            float points = gradePoints.get(i);
            float product = currCredits*points;
            sum1 += product;
            sum2 += currCredits;
        }
        float cgpa = sum1/sum2;
        return cgpa;
    }

    private float calcSGPA(){
        return this.sgpa;
    }

    private void dropCourse() {
        try {
            if (erp.getCourseDropDeadlinePassed() == true) {
                throw new DropDeadLinePassedException("Cannot drop course, course drop deadline has passed.");
            }
            System.out.print("Enter the course code for the course to be dropped: ");
            String code = scanner.next();
            boolean found = false;
            int pos = -1;
            int sz = registeredCourses.size();
            for (int i = 0; i < sz; i++) {
                if (registeredCourses.get(i).getCourseCode().equals(code)) {
                    pos = i;
                    found = true;
                    Course course = registeredCourses.get(i);
                    course.decCount();
                    break;
                }
            }
            if (found) {
                registeredCourses.remove(pos);
                System.out.println("Course Successfully dropped.");
            } else {
                System.out.println("You hava not registered for this course.");
            }
        }
        catch (DropDeadLinePassedException ddpe) {
            System.out.println(ddpe.getMessage());
        }
    }

    private void submitComplaint() {
        int id = erp.generateComplaintId();
        scanner.nextLine();
        System.out.print("Enter Description of the Complaint: ");
        String description = scanner.nextLine();
        Complaint complaint = new Complaint(id, description);
        complaints.add(complaint);
        erp.addNewComplaint(complaint);
        System.out.println("Complaint successfully submitted.");
    }

    private void viewComplaints() {
        for (Complaint complaint : complaints) {
            complaint.showComplaint();
        }
    }

    private void giveFeedback() {
        System.out.print("Enter the course code for which you want to give feedback: ");
        String code = scanner.next();
        Course course = null;
        availableCourses = erp.getAllCourses();
        for (Course c : availableCourses) {
            if (c.getCourseCode().equals(code)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("Course not found.");
        } else {
            Boolean submitted = feedbackSubmitted.contains(code);
            Boolean registered = false;
            for (Course regCourse : registeredCourses) {
                if(regCourse.getCourseCode().equals(code)) {
                    registered = true;
                }
            }
            if (!registered) {
                System.out.println("You have not registered for this course.");
                return;
            }
            if (submitted) {
                System.out.println("Course Feedback already submitted.");
            } else {
                System.out.print("Enter course rating (out of 5): ");
                int rating = scanner.nextInt();
                System.out.print("Enter textual feedback: ");
                scanner.nextLine();
                String text = scanner.nextLine();
                Feedback<Integer> f1 = new Feedback<>(rating);
                Feedback<String> f2 = new Feedback<>(text);
                course.addIntFeedback(f1);
                course.addStrFeedback(f2);
                feedbackSubmitted.add(code);
                System.out.println("Feedback submitted successfully.");
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
                viewAvailableCourses();
            } else if (choice == 2) {
                registerCourse();
            } else if (choice == 3) {
                viewSchedule();
            } else if (choice == 4) {
                trackAcademicProgress();
            } else if (choice == 5) {
                dropCourse();
            } else if (choice == 6) {
                submitComplaint();
            } else if (choice == 7) {
                viewComplaints();
            } else if (choice == 8) {
                giveFeedback();
            } else if (choice == 9) {
                break;
            } else {
                System.out.println("Enter a valid command.");
            }
            System.out.println(" ");
        }
    }

}
