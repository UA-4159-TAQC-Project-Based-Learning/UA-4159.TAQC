Feature: GreenCity Eco News DetailsPage Verification for logged in user

  Background:
    Given User is logged in into the system
    And the user clicks on 'Eco News' in the header
    Then the Eco News page is loaded

  Scenario: existing details page is loaded
    When user click on Eco News with title containing 'test title' in table
    Then Eco News details page is loaded
    And Eco News details title is populated
    And Eco News details author is populated
    And Eco News details date is populated
    And Eco News details text is populated
    And Eco News details tags is populated
    And Other interesting news are populated


  Scenario: Eco News details add comment
    When user click on Eco News with title containing 'test title' in table
    Then Eco News details page is loaded
    When user scrols to comments on Eco News page
    And user click on comment field
    And user entered text 'test'
    And user click button Comment
    Then comment with text 'test' appear
    And comment's user name is populated
    And comment's date is populated
    When user clicks delete comment with text 'test'
    Then popup appears
    When user click button to delete comment
    Then popup dissappears
    And comment 'test' is not displayed


