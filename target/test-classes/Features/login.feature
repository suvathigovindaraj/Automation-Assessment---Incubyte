Feature: Magento Account Registration and Login

  Scenario: Create a new account and log in
    Given I open the Magento website
    When I register a new user
    Then I logout
    And I login with the same credentials
    Then I should see the account dashboard