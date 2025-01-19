Feature: Buy products
    As a customer
    I want to buy products

Background:
    Given the store is ready to service customers
    And a product "Bread" with price 20.50 and stock of 5 exists
    And a product "Jam" with price 80.00 and stock of 10 exists
    And a product "Butter" with price 89.00 and stock of 8 exists

Scenario: Buy one product
    When I buy "Bread" with quantity 1
    Then total should be 20.50

Scenario: Buy multiple products
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 1
    And I buy "Butter" with quantity 1
    Then total should be 210.00

Scenario Outline: Buy one product
   When I buy <product> with quantity <quantity>
   Then total should be <total>
   Examples:
       | product  | quantity |  total  |
       | "Bread"  |     1    |   20.50 |
       | "Jam"    |     2    |  160.00 |
       | "Butter" |     1    |  89.00  |

Scenario: Attempt to buy more than available stock
    When I buy "Jam" with quantity 12
    And I buy "Bread" with quantity 6
    Then the purchase should fail

Scenario Outline: Buy one product
   When I buy <product> with quantity <quantity>
   Then the purchase should fail
   And the stock of <product> should remain <remain>
   Examples:
       | product  | quantity |  remain  |
       |  "Jam"   |    12    |      10  |
       | "Bread"  |     6    |       5  |