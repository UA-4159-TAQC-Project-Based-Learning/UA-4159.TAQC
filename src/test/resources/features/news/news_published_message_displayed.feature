Feature: News publication with confirmation message

  As a registered user
  I want to publish eco news
  So that it appears on the website

  Background:
    Given the user is logged in the system
    And the user opens the 'Create News' page
    And the page is fully loaded

  Scenario: Successful news publication
    Given the user enters a valid title 'Eco news title'
    And the user enters valid main text of at least 20 characters
    And the user selects three news tags
    When the user clicks the Publish button
    And the user should see the news successfully published notification
