public class Complaint {

    private int id;
    private String description;
    private int status; // 0 : Pending, 1 : Resolved

    public Complaint(int id, String description) {
        this.id = id;
        this.description = description;
        status = 0;
    }

    public int getId() {
        return this.id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void showComplaint() {
        System.out.println("Id: " + id);
        System.out.println("Description: " + description);
        System.out.print("Status: ");
        if (status == 0) {
            System.out.println("Pending");
        } else {
            System.out.println("Resolved");
        }
        System.out.println(" ");
    }

}
