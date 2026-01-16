Feature: GreenCity Eco News DetailsPage Verification for logged in user

  Background:
    Given the user is logged in the system
    Given the user clicks on 'Eco News' in the header menu
    Then the Eco News page is loaded successfully

  Scenario: Existing Eco News details page is loaded
    When the user clicks on Eco News with title containing 'test' in the table
    Then the Eco News details page is loaded
    And the Eco News details title is populated
    And the Eco News details author is populated
    And the Eco News details date is populated
    And the Eco News details text is populated
    And the Eco News details tags are populated
    And the Other interesting news section is populated


  Scenario Outline: Eco News details — add and delete a comment
    When the user clicks on Eco News with title containing 'test' in the table
    Then the Eco News details page is loaded

    When the user scrolls to the comments section
    And the user clicks on the comment input field
    And the user enters text '<textComment>'
    And the user clicks the add Comment button

    Then a comment with text '<textComment>' appears
    And user name is populated for comment '<textComment>'

    When the user clicks delete on comment with text '<textComment>'

    When the user confirms deletion in the popup
    Then the popup disappears
    And the comment with text '<textComment>' is not displayed

    Examples: textComment
      | textComment  |
      | someTestComment |