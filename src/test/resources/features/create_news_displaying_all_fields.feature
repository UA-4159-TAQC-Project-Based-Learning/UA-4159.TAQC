Feature: Displaying All Fields on Create News Page
  As a logged-in user
  I want to see all required fields and UI components on the Create News page
  So that I can understand what information is needed to publish news

  Background:
    Given the user opens the "Create News" page
    And the page is fully loaded

  Scenario: Title block should be displayed
    Then the Title block should be visible
    And the Title label should be visible
    And the Title field info should be visible
    And the Title input field should be visible

  Scenario: Tags block should be displayed and tags can be selected
    Then the Tags block should be visible
    When the user selects the "News" tag
    Then the "News" tag should be selected
    When the user unselects the "News" tag
    Then the "News" tag should not be selected

  Scenario: Image Add block should be displayed
    Then the Add Image block should be visible
    And the Image dropzone should be visible

  Scenario: Content Editor should be displayed
    Then the Content Editor should be visible
    And the Content Editor field info should contain "63 206"

  Scenario: Author of news should be filled
    Then the Author field should not be empty
    And the Author field should match the logged-in username
    And the Author field should not be editable

  Scenario: Date of news should be filled
    Then the Date field should be visible
    And the Date field should not be empty
    And the Date field should not be editable
    And the Date field should match the format "MMMM d, yyyy"

  Scenario: Source block should be displayed
    Then the Source block should be visible
    And the Source label should be visible
    And the Source field info should be visible
    And the Source input field should be visible
    And the Source field info should contain "http(s)://"

  Scenario: Cancel, Preview and Publish buttons should be displayed
    Then the buttons block should be visible
    And the Cancel button should be visible
    And the Preview button should be visible
    And the Publish button should be visible
