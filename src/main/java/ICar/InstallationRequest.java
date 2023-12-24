package ICar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InstallationRequest {

    private int id;
    private Order order;
    private User customer;
    private LocalDateTime scheduledDateTime;
    private Installer assignedInstaller;
    private Status status;
    private String notes;

    public enum Status {

        PENDING,
        SCHEDULED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }



    public InstallationRequest(int ID, Order order, User customer) {
        this.id = ID;
        this.order = order;
        this.customer = customer;
        this.status = Status.PENDING;
        this.notes = "";
    }



    public void completeRequest() {
        this.status = Status.COMPLETED;
    }

    public void cancelRequest() {
        this.status = Status.CANCELLED;
    }

    public void addNotes(String notes) {
        this.notes += notes + "\n"; // Append notes with a newline
    }

    public String getRequestDetails() {

        String allDetails = "Installation Request ID: " + id + "\n" +
                "Order ID: " + order.getOrderID() + "\n" +
                "Customer Name: " + customer.getName() + "\n" +
                "Status: " + status.toString() + "\n" +
                "Notes: " + notes;

        if (!status.equals(Status.PENDING)) {
            String detailsForScheduledOnly = "\nScheduled Date/Time: " + scheduledDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n" +
                    "Assigned Installer: " + (assignedInstaller != null ? assignedInstaller.getName() : "Not Assigned") + "\n";
            allDetails = allDetails + detailsForScheduledOnly;
        }
        return allDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }

    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }

    public Installer getAssignedInstaller() {
        return assignedInstaller;
    }

    public void setAssignedInstaller(Installer assignedInstaller) {
        this.assignedInstaller = assignedInstaller;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
