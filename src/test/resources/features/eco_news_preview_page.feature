Feature: Preview News Before Publishing
  As a User
  I want to be able to preview a piece of news before posting it
  So that I can see how it will look in the Single View

  Background:
    Given the user is logged in the system
    And the user clicks on 'Eco News' in the header
    Then the Eco News page is loaded

  Scenario: User previews news content
    When the user navigates to Eco News and clicks 'Create news'
    And the user enters some text into 'Title' field
    And the user clicks "News" tag
    And the user enters text into 'Content' field
    And the user clicks the "Preview" button
    Then the preview mode should open
    And the preview should display the entered title
    And the preview should display the tag "News"
    And the preview should display the entered content text
    And the preview should display the current date
    And the preview should display the author's name
    And a 'Back to editing' button should be available

  Scenario: User publishes eco news successfully
    Given the user has entered valid news title and content
    When the user clicks the "Publish" button
    Then the eco news list should be successfully opened
    And the published eco news should appear in Single View
    And the displayed content should match the previously entered data
