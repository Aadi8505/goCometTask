Feature: CaseKaro Shopping Flow Automation

  Scenario: Add three material variants of a phone cover to cart and validate
    Given I navigate to the CaseKaro website "https://casekaro.com/"
    When I click on "Mobile Covers" from the top navigation menu
    And I scroll down to the "Phone cases by model" search box and search for "Apple"
    Then I verify that non-Apple brands are not visible in the search results
    When I search for "iPhone 16 Pro" in the phone model search box and wait for autocomplete suggestions
    And I select specifically "iPhone 16 Pro" from the autocomplete dropdown
    And I click "Choose Options" on the first product card
    And I add all three material variants "Hard", "Soft", and "Glass" of this case to the cart
    And I open the cart
    Then I validate that all three items are added in the cart
    And I print the price of all items with details including Material, Price, and Link in the console
