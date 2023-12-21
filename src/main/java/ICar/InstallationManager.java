package ICar;

import java.util.ArrayList;

public class InstallationManager {

    private ArrayList<InstallationRequest> installationRequests;

    public InstallationManager() {
        this.installationRequests = new ArrayList<>();
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

    public void assignInstallerToRequest(int requestId, Installer installer) {
        InstallationRequest request = findInstallationRequestById(requestId);
        if (request != null && request.getStatus() == InstallationRequest.Status.PENDING) {
            request.assignInstaller(installer);
            System.out.println("Installer assigned successfully.");
        } else {
            System.out.println("Invalid request ID or the request is not pending.");
        }
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

    private InstallationRequest findInstallationRequestById(int requestId) {
        for (InstallationRequest request : installationRequests) {
            if (request.getId() == requestId) {
                return request;
            }
        }
        return null; // Request not found.
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
}
