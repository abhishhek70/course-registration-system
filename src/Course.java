import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

public class Course {

    private String courseCode;
    private String title;
    private int credits;
    private List<Course> prerequisite;
    private Professor professor;
    private String timings;
    private String location;
    private String syllabus;
    private int enrollmentLimit;
    private int enrolledCount;
    private String officeHours;
    private List<Feedback<Integer>> intFeedback;
    private List<Feedback<String>> strFeedback;
    private List<TeachingAssistant> taList;

    public Course (String courseCode, String title, int credits, List<Course> prerequisite, Professor professor, String timings, String location, String syllabus, int enrollmentLimit, String officeHours) {
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
        this.prerequisite = prerequisite;
        this.professor = professor;
        this.timings = timings;
        this.location = location;
        this.syllabus = syllabus;
        this.enrollmentLimit = enrollmentLimit;
        this.officeHours = officeHours;
        this.intFeedback = new ArrayList<>();
        this.strFeedback = new ArrayList<>();
        this.taList = new ArrayList<>();
        this.enrolledCount = 0;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public String getTitle() {
        return this.title;
    }

    public List<Course> getPrerequisite() {
        return this.prerequisite;
    }

    public int getCredits() {
        return this.credits;
    }

    public List<Feedback<Integer>> getIntFeedback() {
        return this.intFeedback;
    }

    public void addIntFeedback(Feedback<Integer> feedback) {
        intFeedback.add(feedback);
    }

    public void addStrFeedback(Feedback<String> feedback) {
        strFeedback.add(feedback);
    }

    public List<Feedback<String>> getStrFeedback() {
        return this.strFeedback;
    }

    public void addTA(TeachingAssistant ta) {
        taList.add(ta);
    }

    public void viewTAs() {
        for (TeachingAssistant ta : taList) {
            System.out.println("> " + ta.getEmail() + " " + ta.getName());
        }
    }

    public void setCredits(int updatedCredits) {
        this.credits = updatedCredits;
    }

    public void setEnrollmentLimit(int limit) {
        this.enrollmentLimit = limit;
    }

    public void setTimings(String timing) {
        this.timings = timing;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public void setOfficeHours(String officeHours) {
        this.syllabus = officeHours;
    }

    public void setProfessor(Professor prof) {
        this.professor = prof;
    }

    public boolean isFull() {
        return enrolledCount == enrollmentLimit;
    }

    public void incCount() {
        enrolledCount++;
    }

    public void decCount() {
        enrolledCount--;
    }

    public void showDetailsStudent() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Title: " + title);
        System.out.println("Credits: " + credits);
        System.out.print("Prerequisite: ");
        int sz = prerequisite.size();
        if (sz == 0) {
            System.out.println("No prerequisites");
        } else {
            for (int i=0; i<sz; i++) {
                System.out.print(prerequisite.get(i).getCourseCode());
                if (i != (sz-1)) {
                    System.out.print(", ");
                } else {
                    System.out.println(" ");
                }
            }
        }
        System.out.print("Professor: ");
        if (professor == null) {
            System.out.println("TBA");
        } else {
            System.out.println(professor.getName());
        }
        System.out.print("Timings: ");
        if (timings == null) {
            System.out.println("TBA");
        } else {
            System.out.println(timings);
        }
        System.out.print("Location: ");
        if (location == null) {
            System.out.println("TBA");
        } else {
            System.out.println(location);
        }
        System.out.print("Enrollment Limit: ");
        if (enrollmentLimit > 0) {
            System.out.println("Yes, " + enrollmentLimit);
        } else {
            System.out.println("No");
        }
        System.out.println(" ");
    }

    public void showSchedule() {
        System.out.print("Course: " + courseCode + " " + title);
        System.out.print(", Professor: ");
        if (professor == null) {
            System.out.print("TBA");
        } else {
            System.out.print(professor.getName());
        }
        System.out.print(", Timings: ");
        if (timings == null) {
            System.out.print("TBA");
        } else {
            System.out.print(timings);
        }
        System.out.print(", Location: ");
        if (location == null) {
            System.out.print("TBA");
        } else {
            System.out.print(location);
        }
        System.out.print("\n");
    }

    public void showDetailsProfessor() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Title: " + title);
        System.out.println("Credits: " + credits);
        System.out.print("Prerequisite: ");
        int sz = prerequisite.size();
        if (sz == 0) {
            System.out.println("No prerequisites");
        } else {
            for (int i=0; i<sz; i++) {
                System.out.print(prerequisite.get(i).getCourseCode());
                if (i != (sz-1)) {
                    System.out.print(", ");
                } else {
                    System.out.println(" ");
                }
            }
        }
        System.out.print("Professor: ");
        if (professor == null) {
            System.out.println("TBA");
        } else {
            System.out.println(professor.getName());
        }
        System.out.print("Timings: ");
        if (timings == null) {
            System.out.println("TBA");
        } else {
            System.out.println(timings);
        }
        System.out.print("Location: ");
        if (location == null) {
            System.out.println("TBA");
        } else {
            System.out.println(location);
        }
        System.out.print("Enrollment Limit: ");
        if (enrollmentLimit > 0) {
            System.out.println("Yes, " + enrollmentLimit);
        } else {
            System.out.println("No");
        }
        System.out.println(" ");
    }

    public void showDetailsAdmin() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Title: " + title);
        System.out.println("Credits: " + credits);
        System.out.print("Prerequisite: ");
        int sz = prerequisite.size();
        if (sz == 0) {
            System.out.println("No prerequisites");
        } else {
            for (int i=0; i<sz; i++) {
                System.out.print(prerequisite.get(i).getCourseCode());
                if (i != (sz-1)) {
                    System.out.print(", ");
                } else {
                    System.out.println(" ");
                }
            }
        }
        System.out.print("Professor: ");
        if (professor == null) {
            System.out.println("TBA");
        } else {
            System.out.println(professor.getName());
        }
        System.out.print("Timings: ");
        if (timings == null) {
            System.out.println("TBA");
        } else {
            System.out.println(timings);
        }
        System.out.print("Location: ");
        if (location == null) {
            System.out.println("TBA");
        } else {
            System.out.println(location);
        }
        System.out.print("Enrollment Limit: ");
        if (enrollmentLimit > 0) {
            System.out.println("Yes, " + enrollmentLimit);
        } else {
            System.out.println("No");
        }
        System.out.println(" ");
    }

}
