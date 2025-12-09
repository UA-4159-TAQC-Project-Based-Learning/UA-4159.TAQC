Feature: Title Field Validation on Create News Page
  As a logged-in user
  I want the Title field to be validated properly
  So that I cannot publish news with an invalid or incomplete title

  Background:
    Given the user opens the "Create News" page
    And the page is fully loaded

  Scenario: Title input invalid when empty and Publish disabled
    When the user clicks on the Title field
    And the user enters an empty title
    Then the Title field should be marked as invalid
    And the Publish button should be disabled
    And the Title counter should display "0/170"

  Scenario: Title input should not exceed 170 characters
    When the user enters a title longer than 170 characters
    Then the Title field value should not exceed 170 characters
    And the Title field should display a warning message

  Scenario: Valid title should have correct counter value
    When the user enters a valid title "Test News"
    Then the Title counter should display "9/170"
    And the Title field should not display a warning message
    And the Main text field should remain empty
    And the Publish button should remain disabled

  Scenario: Publish button enabled with valid title, tag and main text
    Given the user enters a valid title "Test News"
    And the user enters valid main text of at least 20 characters
    And the user selects the "News" tag
    Then the Publish button should be enabled
