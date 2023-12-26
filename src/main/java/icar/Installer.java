package icar;

import java.util.ArrayList;

public class Installer {


    private String name;
    private String email;
    private User userClassProfile;
    private ArrayList<InstallationRequest> assignedRequests; // Track assigned requests using ArrayList

    public Installer( String name, String email, User userClassProfile) {
        this.name = name;
        this.email = email;
        this.userClassProfile = userClassProfile;
        this.assignedRequests = new ArrayList<>(); // Initialize as ArrayList
    }

    public User getUserClassProfile() {
        return userClassProfile;
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
