Feature: Content Field Validation on Create News Page
  As a logged-in user
  I want the Content field to be validated properly
  So that I cannot publish news with invalid or incomplete content

  Background:
    Given the user is logged in the system
    And the user opens the 'Create News' page
    And the page is fully loaded

  Scenario: Content input invalid when less than 20 characters
    When the user enters 'Short text' in the Content field
    And the user enters the title 'Test'
    Then an error message should appear in red with text 'Must be minimum 20 and maximum 63 206 symbols'
    And the Publish button should remain disabled

#  Scenario: Content input truncated when exceeding 63,206 characters
#    When the user enters '63210' characters in the Content field
#    Then the Content field value should be truncated to '63206' characters
#    And no error message should be displayed

  Scenario: Content input valid when between 20 and 63,206 characters
    When the user enters 'This is a valid test content' in the Content field
    And the user enters the title 'Test'
    Then no error message should be displayed
    When the user selects the News tag
    And the Publish button should be enabled
    When the user clicks the Publish button
    Then the news should be published successfully
