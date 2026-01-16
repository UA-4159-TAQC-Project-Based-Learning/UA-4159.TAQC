Feature: GreenCity Eco News DetailsPage Verification for logged in user

  Background:
    Given the user is logged in the system
    Given the user clicks on 'Eco News' in the header menu
    Then the Eco News page is loaded successfully

  Scenario: Eco News table card is populated on the Create News Page
    When user clicks on News tag on the Create News Page
    Then Eco News table card is populated on the Create News Page
    And Eco News table card title is populated for all items
    And Eco News table card author is populated for all items
    And Eco News table card date is populated for all items
    And Eco News table card text is populated for all items