Feature: checkout

  Scenario: checkout one apple
    Given I should have 2 NIS
    When I choose one apple
    Then I should have zero NIS