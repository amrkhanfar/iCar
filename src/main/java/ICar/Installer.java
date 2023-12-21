package ICar;

import java.util.ArrayList;

public class Installer {

    private int id;
    private String name;
    private String email;
    private String phone;
    private ArrayList<InstallationRequest> assignedRequests; // Track assigned requests using ArrayList

    public Installer(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.assignedRequests = new ArrayList<>(); // Initialize as ArrayList
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAssignedRequests(ArrayList<InstallationRequest> assignedRequests) {
        this.assignedRequests = assignedRequests;
    }


    public void addAssignedRequest(InstallationRequest request) {
        assignedRequests.add(request);
    }

    // Remove an assigned request:
    public void removeAssignedRequest(InstallationRequest request) {
        assignedRequests.remove(request);
    }

    // Get a list of assigned requests (no need to create a new ArrayList):
    public ArrayList<InstallationRequest> getAssignedRequests() {
        return assignedRequests;
    }

}
