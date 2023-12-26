package ICar;

import java.time.LocalDateTime;
import java.util.ArrayList;



public class InstallationManager {

    private ArrayList<InstallationRequest> installationRequests;
    private ArrayList<Installer> installers;

    private NotificationService notificationService;
    public InstallationManager(NotificationService notificationService) {
        this.installationRequests = new ArrayList<InstallationRequest>();
        this.installers = new ArrayList<Installer>();
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public InstallationRequest makeInstallationRequest(Order order, String notes) {
        InstallationRequest installationRequest = new InstallationRequest(RandomIDGenerator.generateUniqueId(),order,order.getCustomer());
        installationRequest.setNotes(notes);
        installationRequests.add(installationRequest);

        return installationRequest;
    }


    public void assignInstallerToRequest(InstallationRequest installationRequest, Installer installer, LocalDateTime scheduledDateTime) {
        installationRequest.setAssignedInstaller(installer);
        installationRequest.setStatus(InstallationRequest.Status.SCHEDULED);
        installer.getAssignedRequests().add(installationRequest);
        installationRequest.setScheduledDateTime(scheduledDateTime);

        notificationService.sendInstallationRequestNotification(installer, installationRequest);

    }

    public void completeInstallationRequest(InstallationRequest request) {
            request.completeRequest();
            System.out.println("Installation request completed successfully.");
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

    public Boolean removeInstaller (User user) {
        Installer installerToRemove = getInstallerByName(user.getName());
        return installers.remove(installerToRemove);
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
