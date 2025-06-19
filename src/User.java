abstract class User {

    protected String email;
    protected String password;
    protected CourseRegistrationSystem erp;

    public User(String email, String password, CourseRegistrationSystem erp) {
        this.email = email;
        this.password = password;
        this.erp = erp;
    }

    abstract void showMenu();
    abstract void runUser();
}