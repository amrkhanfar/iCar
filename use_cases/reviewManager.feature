Feature: Managing Reviews

  Background:
    Given all the mangers for reviewManagerSteps are initialized
    And a category called general exists

  Scenario: Adding a Product Review
    Given a product "Car Radio" with ID 1
    And a user "Fawwaz Qanazea" with email "fozymozy@gmail.com"
    When Fawwaz Qanazea adds a review with rating 4 and content "Great product!"
    Then the product "Car Radio" should have an average rating of "4.0"
    And the review should be displayed in the product reviews


  Scenario: Adding an Installation Request Review
    Given a user "Installer Sam" with email "Sam@gmail.com"
    And a customer "Farah" with email "Farah@gmail.com.com"
    And a product "Car Radio" with ID 223 that Farah ordered
    And an installation request for Farah's order
    And it was scheduled by an admin
    When Installer Sam adds a review with rating 4 and content "Smooth installation process"
    Then the installation request should have a review
    And the review should be displayed in the installation request reviews
