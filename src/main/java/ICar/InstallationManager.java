package ICar;

import java.util.ArrayList;

public class InstallationManager {

    private ArrayList<InstallationRequest> installationRequests;
    private ArrayList<Installer> installers;

    public InstallationManager() {
        this.installationRequests = new ArrayList<InstallationRequest>();
        this.installers = new ArrayList<Installer>();
    }

    public void addInstallationRequest(InstallationRequest request) {
        installationRequests.add(request);
        System.out.println("Installation request added successfully.");
    }

    public void viewInstallationRequests() {
        if (installationRequests.isEmpty()) {
            System.out.println("No installation requests available.");
        } else {
            System.out.println("List of Installation Requests:");
            for (InstallationRequest request : installationRequests) {
                System.out.println(request.getRequestDetails());
                System.out.println("----------------------");
            }
        }
    }

    public void assignInstallerToRequest(InstallationRequest installationRequest, Installer installer) {
        installationRequest.assignInstaller(installer);
        installer.getAssignedRequests().add(installationRequest);
    }

    public void completeInstallationRequest(int requestId) {
        InstallationRequest request = findInstallationRequestById(requestId);
        if (request != null && request.getStatus() == InstallationRequest.Status.SCHEDULED) {
            request.completeRequest();
            System.out.println("Installation request completed successfully.");
        } else {
            System.out.println("Invalid request ID or the request is not scheduled.");
        }
    }

    public InstallationRequest findInstallationRequestById(int requestId) {
        for (InstallationRequest request : installationRequests) {
            if (request.getId() == requestId) {
                return request;
            }
        }
        return null; // Request not found.
    }

    public ArrayList<InstallationRequest> getPendingInstallationRequest() {
        ArrayList<InstallationRequest> pendingRequests = new ArrayList<InstallationRequest>();

        for (InstallationRequest installationRequest : getInstallationRequests()) {
            if(installationRequest.getStatus() == InstallationRequest.Status.PENDING) {
                pendingRequests.add(installationRequest);
            }
        }
        return pendingRequests;
    }

    public ArrayList<InstallationRequest> getInstallationRequests() {
        return installationRequests;
    }

    public InstallationRequest checkIfOrderHasInstallationRequest(Order order) {
        for (InstallationRequest installationRequest : installationRequests) {
            if (installationRequest.getOrder().equals(order)) {
                return installationRequest;
            }
        }
        return null;
    }

    public Installer registerInstaller (User user) {
        Installer installerToRegister = new Installer(user.getName(), user.getEmail(), user);

        installers.add(installerToRegister);
        return installerToRegister;
    }

    public ArrayList<Installer> getInstallers() {
        return installers;
    }

    public Installer getInstallerByName(String name){
        for (Installer installer : installers){
            if (installer.getName().trim().toLowerCase().equals(name.trim().toLowerCase())) {
                return installer;  //Email exists in the database.
            }
        }
        return null;
    }
}
