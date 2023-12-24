Feature: Installation Request Management

  Background:
    Given all the required managers are set up
    And there are installers registered in the system
    And a customer placed an order with installation request

  Scenario: View Pending Installation Requests
    Given there are pending installation requests
    When the admin views the list of pending installation requests
    Then the admin should see the details of each pending installation request

  Scenario: Assign Installer to Installation Request
    Given there is a pending installation request
    When an installer is assigned to the installation request
    Then the installation request should be in the scheduled status

  Scenario: Complete Installation Request
    Given there is a scheduled installation request
    When the installer completes the installation request
    Then the installation request should be in the completed status

  Scenario: Create and Complete Installation Request
    Given a user places an order for installation
    When an installation request is created
    Then the installation request should be in the pending status

    When an installer is assigned to the installation request
    Then the installation request should be in the scheduled status

    When the installer completes the installation request
    Then the installation request should be in the completed status
