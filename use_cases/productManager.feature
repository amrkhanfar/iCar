Feature: Product Management

  Background:
    Given Product manager is initialized
    And The product manager has categories:
  | CategoryName | CategoryDescription                   |
  | Interior     | Accessories for the car's interior.   |
  | Exterior     | Accessories for the car's exterior.   |

  Scenario: Adding a product to a category
    Given a product with ID 1 and name "Car Cover"
    When the product manager adds the previous product to the category "Exterior"
    Then the product should be added to the "Exterior" category

  Scenario: Removing a product from a category
    Given the product manager has a category "Electronics"
    And a product with ID 2 and name "GPS Tracker"
    And the product manager adds the product to the "Electronics" category
    When the product manager removes the product from the "Electronics" category
    Then the product should be removed from the "Electronics" category

  Scenario: Finding a category by product
    Given the product manager has a category "Wheels"
    And a product with ID 3 and name "Alloy Wheels"
    And the product manager adds the product to the "Wheels" category
    When the product manager finds the category for the product with ID 3
    Then the category should be "Wheels"

  Scenario: Finding a product by ID
    Given the product manager has a category "Lighting"
    And a product with ID 4 and name "LED Headlights"
    And the product manager adds the product to the "Lighting" category
    When the product manager finds the product with ID 4
    Then the product name should be "LED Headlights"

  Scenario: Displaying categories
    When the product manager displays the categories
    Then the product manager should see the following categories:
      | Interior |
      | Exterior |

  Scenario: Adding a new category
    Given the product manager adds a new category "Wheels" with description "Accessories for the car's wheels"
    When the product manager displays the categories
    Then the product manager should see the following categories:
      | Interior |
      | Exterior |
      | Wheels   |

  Scenario: Removing an existing category
    Given the product manager removes the category "Interior"
    When the product manager displays the categories
    Then the product manager should see the following categories:
      | Exterior |


  Scenario: Getting all categories
    When the product manager gets all categories
    Then the product manager should have the following categories:
      | Interior |
      | Exterior   |

  Scenario: Removing a product by ID
    Given the product manager has a category "Electronics"
    And a product with ID 5 and name "Dash Cam"
    And the product manager adds the product to the "Electronics" category
    When the product manager removes the product with ID 5
    Then the product should be removed from the "Electronics" category
    And the product manager should not find the product with ID 5
