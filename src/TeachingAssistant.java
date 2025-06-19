import java.util.ArrayList;
import java.util.List;

public class TeachingAssistant extends Student{

    private Course taCourse;
    private Administrator admin;

    public TeachingAssistant(Student student, CourseRegistrationSystem erp, Course course, Administrator admin) {
        super(student.getName(), student.getEmail(), student.getPassword(), erp);
        this.taCourse = course;
        this.admin = admin;
    }

    public void setTACourse(Course course) {
        this.taCourse = course;
    }

    @Override
    void showMenu() {
        System.out.println("-> Choose one of the following commands [Teaching Assistant]");
        System.out.println("1. View Course Details");
        System.out.println("2. View Enrolled Students");
        System.out.println("3. View Student Grades");
        System.out.println("4. Manage Student Grades");
        System.out.println("5. Logout");
        System.out.print("Enter command number: ");
    }

    public void viewCourseDetails() {
        taCourse.showDetailsProfessor();
    }

    public void viewEnrolledStudents() {
        List<Student> enrolledStudents = new ArrayList<>();
        List<Student> students = erp.getStudents();
        for (Student student : students) {
            List<Course> reg = student.getRegisteredCourses();
            for (Course regCourse : reg) {
                if (regCourse.getCourseCode().equals(taCourse.getCourseCode())) {
                    enrolledStudents.add(student);
                }
            }
        }
        for (Student enrollStudent : enrolledStudents) {
            System.out.println("Name: " + enrollStudent.getName() + ", Email: " + enrollStudent.getEmail() + ", Semester: " + enrollStudent.getSemester());
        }
    }

    public void viewStudentGrades() {
        System.out.print("Enter email of student: ");
        String email = scanner.next();
        Student student = null;
        List<Student> allStudents = erp.getStudents();
        for (Student s : allStudents) {
            if (s.getEmail().equals(email)) {
                student = s;
            }
        }
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            int grade = student.viewGrade(taCourse.getCourseCode());
            if (grade != -1) {
                System.out.println("Grade Point: " + grade);
            } else {
                System.out.println("Course not completed.");
            }
        }
    }

    public void manageStudentGrades() {
        System.out.print("Enter email of student: ");
        String email = scanner.next();
        System.out.print("Enter updated grade point (between 1-10): ");
        int newGrade = scanner.nextInt();
        Student student = null;
        List<Student> allStudents = erp.getStudents();
        for (Student s : allStudents) {
            if (s.getEmail().equals(email)) {
                student = s;
            }
        }
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            admin.changeGrade(student, taCourse, newGrade);
        }
    }

    @Override
    public void runUser() {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            System.out.println(" ");
            if (choice == 1) {
                viewCourseDetails();
            } else if (choice == 2) {
                viewEnrolledStudents();
            } else if (choice == 3) {
                viewStudentGrades();
            } else if (choice == 4) {
                manageStudentGrades();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Enter a valid command.");
            }
            System.out.println(" ");
        }
    }
}
