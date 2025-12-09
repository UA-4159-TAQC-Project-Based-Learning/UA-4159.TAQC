Feature: GreenCity Eco News DetailsPage Verification for logged in user

  Background:
    Given User is logged in into the system
    And the user clicks on 'Eco News' in the header
    Then the Eco News page is loaded

  Scenario: Eco News table card is populated
    When user clicks on News tag
    Then Eco News table card is populated
    And Eco News table card title is populated
    And Eco News table card author is populated
    And Eco News table card date is populated
    And Eco News table card text is populated
    And comment counter is populated
    And likes counter is populated

    Scenario: Table Eco News card add to favorite
      When user clicks on favorite button
      Then favorite button is changed flag to active
      When user clicks on favorite button
      Then favorite button is changed flag to not active

