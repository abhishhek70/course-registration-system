import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CourseRegistrationSystem {

    Scanner scanner = new Scanner(System.in);
    private List<Course> availableCourses;
    private List<Student> students;
    private List<Professor> professors;
    private List<Complaint> pendingComplaints;
    private List<Complaint> resolvedComplaints;
    private List<TeachingAssistant> taList;
    private String adminPassword = "pass1234";
    private int numComplaints = 0;
    private boolean courseDropDeadlinePassed = false;

    private Administrator admin;

    public CourseRegistrationSystem() {
        admin = new Administrator(adminPassword, this);
        this.students = new ArrayList<>();
        this.availableCourses = new ArrayList<>();
        this.professors = new ArrayList<>();
        this.pendingComplaints = new ArrayList<>();
        this.resolvedComplaints = new ArrayList<>();
        this.taList = new ArrayList<>();
        // HardCode Professors
        Professor p1 = new Professor("Ankit Jain", "ankit@uni.ac.in", "pass", this, admin);
        Professor p2 = new Professor("Suresh Verma", "suresh@uni.ac.in", "pass", this, admin);
        Professor p3 = new Professor("Vinod Sharma", "vinod@uni.ac.in", "pass", this, admin);
        Professor p4 = new Professor("Pooja Kaushik", "pooja@uni.ac.in", "pass", this, admin);
        Professor p5 = new Professor("Monika Aggarwal", "monika@uni.ac.in", "pass", this, admin);
        Professor p6 = new Professor("Mayank Yadav", "mayank@uni.ac.in", "pass", this, admin);
        Professor p7 = new Professor("Rohit", "rohit@uni.ac.in", "pass", this, admin);
        Professor p8 = new Professor("Mohit", "mohit@uni.ac.in", "pass", this, admin);
        Professor p9 = new Professor("Ramesh", "ramesh@uni.ac.in", "pass", this, admin);
        Professor p10 = new Professor("Sunita Malik", "mohit@uni.ac.in", "pass", this, admin);
        Professor p11 = new Professor("Yogesh Kumar", "yogesh@uni.ac.in", "pass", this, admin);

        // HardCode Courses
        Course c1 = new Course("CSE101", "Intro to Programming", 4, new ArrayList<>(), p1, "Monday and Wednesday, 9:00AM-10:30AM", "LHC C101", "Python Programming Language and basics of OOPs", -1, "Monday 1:00PM-1:30PM");
        Course c2 = new Course("CSE102", "Data Structures and Algorithms", 4, new ArrayList<>(), p2, "Monday and Wednesday, 3:00PM-4:30PM", "LHC C101", "Sorting Algo, Binary Search, Asymptotic Analysis, Stack, Queue, Heap, Hashing, Graph", -1, "Tuesday 1:00PM-1:30PM");
        Course c3 = new Course("MTH101", "Linear Algebra", 4, new ArrayList<>(), p3, "Tuesday and Thursday, 9:00AM-10:30AM", "LHC C201", null, -1, "Monday 2:00PM-2:30PM");
        Course c4 = new Course("MTH201", "Calculus", 4, new ArrayList<>(), p4, "Monday and Friday, 11:30AM-12:30PM", "LHC C102", null, -1, null);
        Course c5 = new Course("ECE101", "Digital Circuits", 4, new ArrayList<>(), p5, "Wednesday and Friday, 4:30PM-6:00PM", "LHC C201", "Binary number system, Logic gates, Adder, Multiplexer", -1, "Thursday 1:00PM-1:30PM");
        Course c6 = new Course("CSE205", "Machine Learning", 4, new ArrayList<>(), null, "Tuesday and Thursday, 3:00PM-4:30PM", "LHC C102", null, -1, "Friday 2:00PM-2:30PM");
        Course c7 = new Course("CSE206", "Artificial Intelligence", 4, new ArrayList<>(), p7, "Tuesday and Thursday, 11:00AM-12:30PM", "LHC C210", null, -1, null);
        Course c8 = new Course("MTH201", "Probability", 4, new ArrayList<>(), p8, "Monday and Friday, 11:00AM-12:30PM", "LHC C209", null, -1, "Friday 1:30PM-2:00PM");
        Course c9 = new Course("MTH205", "Real Analysis", 4, new ArrayList<>(), p9, "Wednesday and Thursday, 11:00AM-12:30PM", "LHC C204", null, -1, "Thursday 2:00PM-2:30PM");
        Course c10 = new Course("SSH111", "Critical Thinking", 4, new ArrayList<>(), p10, "Tuesday and Thursday, 3:00PM-4:30PM", "LHC C208", null, -1, null);
        availableCourses.add(c1);
        availableCourses.add(c2);
        availableCourses.add(c3);
        availableCourses.add(c4);
        availableCourses.add(c5);
        availableCourses.add(c6);
        availableCourses.add(c7);
        availableCourses.add(c8);
        availableCourses.add(c9);
        availableCourses.add(c10);
        p1.setCourse(c1);
        p2.setCourse(c2);
        p3.setCourse(c3);
        p4.setCourse(c4);
        p5.setCourse(c5);
        p7.setCourse(c7);
        p8.setCourse(c8);
        p9.setCourse(c9);
        p10.setCourse(c10);
        professors.add(p1);
        professors.add(p2);
        professors.add(p3);
        professors.add(p4);
        professors.add(p5);
        professors.add(p6);
        professors.add(p7);
        professors.add(p8);
        professors.add(p9);
        professors.add(p10);
        professors.add(p11);
        // HardCode students
        Student s1 = new Student("Nishant", "nishant100@uni.ac.in", "pass", this);
        Student s2 = new Student("Manan", "manan101@uni.ac.in", "pass", this);
        Student s3 = new Student("Riya", "riya102@uni.ac.in", "pass", this);
        Student s4 = new Student("Sneha", "sneha103@uni.ac.in", "pass", this);
        // student s1
        List<Course> regCourses1 = s1.getRegisteredCourses();
        regCourses1.add(c1);
        regCourses1.add(c2);
        regCourses1.add(c3);
        regCourses1.add(c4);
        regCourses1.add(c5);
        s1.setCredits(20);
        // student s2
        List<Course> regCourses2 = s2.getRegisteredCourses();
        regCourses2.add(c1);
        regCourses2.add(c2);
        regCourses2.add(c3);
        regCourses2.add(c4);
        regCourses2.add(c5);
        s2.setCredits(20);
        // student s3
        List<Course> regCourses3 = s3.getRegisteredCourses();
        regCourses3.add(c1);
        regCourses3.add(c2);
        regCourses3.add(c3);
        regCourses3.add(c4);
        regCourses3.add(c5);
        s3.setCredits(20);
        // student s4
        List<Course> regCourses4 = s4.getRegisteredCourses();
        List<Course> completedCourses4 = s4.getCompletedCourses();
        List<Integer> grades4 = s4.getGradePoints();
        regCourses4.add(c7);
        regCourses4.add(c8);
        regCourses4.add(c9);
        regCourses4.add(c10);
        s4.setCredits(16);
        completedCourses4.add(c1);
        completedCourses4.add(c2);
        completedCourses4.add(c3);
        completedCourses4.add(c4);
        completedCourses4.add(c5);
        grades4.add(10);
        grades4.add(10);
        grades4.add(8);
        grades4.add(8);
        grades4.add(6);
        s4.setSemester(2);
        float cg = s4.getCGPA();
        s4.setSgpa(cg);
        // adding students to students list
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        // making s4 TA of course CSE101
        TeachingAssistant ta = new TeachingAssistant(s4, this, c1, admin);
        c1.addTA(ta);
        taList.add(ta);
    }

    public boolean getCourseDropDeadlinePassed() {
        return courseDropDeadlinePassed;
    }

    public void setCourseDropDeadlinePassed(boolean val) {
        courseDropDeadlinePassed = val;
    }

    public List<Course> getAllCourses() {
        return availableCourses;
    }

    public Course getCourse(String code) {
        for (Course course : availableCourses) {
            if (course.getCourseCode().equals(code)) {
                return course;
            }
        }
        return null;
    }

    public int generateComplaintId() {
        numComplaints++;
        return numComplaints;
    }

    public void addNewComplaint(Complaint complaint) {
        pendingComplaints.add(complaint);
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Complaint> getPendingComplaints() {
        return this.pendingComplaints;
    }

    public List<Complaint> getResolvedComplaints() {
        return this.resolvedComplaints;
    }

    public void addTA(TeachingAssistant ta) {
        taList.add(ta);
    }

    private void showMainMenu() {
        System.out.println("***** Welcome to the Course Registration System *****");
        System.out.println("-> Select your Role");
        System.out.println("1. Student");
        System.out.println("2. Professor");
        System.out.println("3. Administrator");
        System.out.println("4. Teaching Assistant");
        System.out.println("5. Exit");
        System.out.print("Enter option number: ");
    }

    private void exit() {
        System.out.println("***** Thank you for using the Course Registration System *****");
        System.exit(0);
    }

    private void enterStudent() {
        while (true) {
            showStudentMenu();
            int choice = scanner.nextInt();
            System.out.println(" ");
            if (choice == 1) {
                logInStudent();
            } else if (choice == 2) {
                signUpStudent();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Enter a valid option.");
            }
        }
    }

    private void showStudentMenu() {
        System.out.println("-> Choose one of the following commands [Student]");
        System.out.println("1. Log In");
        System.out.println("2. Sign Up");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter command number: ");
    }

    private void logInStudent() {
        try {
            System.out.print("+ Enter Email: ");
            String email = scanner.next();
            System.out.print("+ Enter Password: ");
            String password = scanner.next();
            boolean validUser = false;
            for (Student st : students) {
                if (st.getEmail().equals(email) && st.getPassword().equals(password)) {
                    System.out.println("You are now logged in as a student.\n");
                    st.runUser();
                    validUser = true;
                }
            }
            if (!validUser) {
                throw new InvalidLoginException("Incorrect Details.");
            }
        }
        catch (InvalidLoginException ile) {
            System.out.println(ile.getMessage());
            System.out.print("\n");
        }
    }

    private void signUpStudent() {
        System.out.print("+ Enter Email: ");
        String email = scanner.next();
        System.out.print("+ Enter Password: ");
        String password = scanner.next();
        System.out.print("+ Enter Your Name: ");
        String name = scanner.next();
        Student st = new Student(name, email, password, this);
        students.add(st);
        System.out.println("-> You are now logged in as a student.\n");
        st.runUser();
    }

    private void enterProfessor() {
        while (true) {
            showProfessorMenu();
            int choice = scanner.nextInt();
            System.out.println(" ");
            if (choice == 1) {
                logInProfessor();
            } else if (choice == 2) {
                signUpProfessor();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Enter a valid option.");
            }
        }
    }

    private void showProfessorMenu() {
        System.out.println("-> Choose one of the following commands [Professor]");
        System.out.println("1. Log In");
        System.out.println("2. Sign Up");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter command number: ");
    }

    private void logInProfessor() {
        try {
            System.out.print("+ Enter Email: ");
            String email = scanner.next();
            System.out.print("+ Enter Password: ");
            String password = scanner.next();
            boolean validUser = false;
            for (Professor prof : professors) {
                if (prof.getEmail().equals(email) && prof.getPassword().equals(password)) {
                    System.out.println("You are now logged in as a professor.\n");
                    prof.runUser();
                    validUser = true;
                }
            }
            if (!validUser) {
                throw new InvalidLoginException("Incorrect Details.");
            }
        }
        catch (InvalidLoginException ile) {
            System.out.println(ile.getMessage());
            System.out.print("\n");
        }
    }

    private void signUpProfessor() {
        System.out.print("+ Enter Email: ");
        String email = scanner.next();
        System.out.print("+ Enter Password: ");
        String password = scanner.next();
        System.out.print("+ Enter Your Name: ");
        String name = scanner.next();
        Professor prof = new Professor(name, email, password, this, admin);
        professors.add(prof);
        System.out.println("-> You are now logged in as a Professor.\n");
        prof.runUser();
    }

    private void enterAdministrator() {
        while (true) {
            showAdministratorMenu();
            int choice = scanner.nextInt();
            System.out.println(" ");
            if (choice == 1) {
                logInAdministrator();
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Enter a valid option.");
            }
        }
    }

    private void showAdministratorMenu() {
        System.out.println("-> Choose one of the following commands [Administrator]");
        System.out.println("1. Log In");
        System.out.println("2. Back to Main Menu");
        System.out.print("Enter command number: ");
    }

    private void logInAdministrator() {
        try {
            System.out.print("+ Enter Administrator Password: ");
            String password = scanner.next();
            boolean validUser = adminPassword.equals(password);
            if (!validUser) {
                throw new InvalidLoginException("Incorrect Password.");
            } else {
                System.out.println("You are now logged in as an Administrator.\n");
                admin.runUser();
            }
        }
        catch (InvalidLoginException ile) {
            System.out.println(ile.getMessage());
            System.out.print("\n");
        }
    }

    private void enterTA() {
        while (true) {
            showTAMenu();
            int choice = scanner.nextInt();
            System.out.println(" ");
            if (choice == 1) {
                logInTA();
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Enter a valid option.");
            }
        }
    }

    private void showTAMenu() {
        System.out.println("-> Choose one of the following commands [Teaching Assistant]");
        System.out.println("1. Log In");
        System.out.println("2. Back to Main Menu");
        System.out.print("Enter command number: ");
    }

    private void logInTA() {
        System.out.print("+ Enter Email: ");
        String email = scanner.next();
        System.out.print("+ Enter Password: ");
        String password = scanner.next();
        boolean validTA = false;
        boolean validStudent = false;
        for (Student student : students) {
            if(student.getEmail().equals(email) && student.getPassword().equals(password)) {
                validStudent = true;
                break;
            }
        }
        if (!validStudent) {
            System.out.println("Incorrect details.\n");
            return;
        }
        for (TeachingAssistant ta : taList) {
            if(ta.getEmail().equals(email) && ta.getPassword().equals(password)) {
                System.out.println("You are now logged in as a TA.\n");
                ta.runUser();
                validTA = true;
                break;
            }
        }
        if (!validTA) {
            System.out.println("You are not a TA of any course.\n");
        }
    }

    public void run() {
        while (true) {
            showMainMenu();
            int choice = scanner.nextInt();
            System.out.println(" ");
            if (choice == 1) {
                enterStudent();
            } else if (choice == 2) {
                enterProfessor();
            }else if (choice == 3) {
                enterAdministrator();
            } else if (choice == 4) {
                enterTA();
            } else if (choice == 5) {
                exit();
            } else {
                System.out.println("Enter a valid option.\n");
            }
        }
    }
}