Feature: Order Management

  Background:
    Given all the mangers are initialized
    And a general category exists


  Scenario: Customer places an order
    Given the customer "Sam Hamadneh" with email "amrkhanfarbis@gmail.com" is registered
    And the following products are available:
      | Product Name | Price |
      | ProductA      | 50.0  |
      | ProductB      | 30.0  |
    When Sam Hamadneh adds the following products to the cart:
      | Product Name | Quantity |
      | ProductA      | 2        |
      | ProductB      | 1        |
    And Sam Hamadneh places the order
    Then an order confirmation email is sent to Sam Hamadneh

