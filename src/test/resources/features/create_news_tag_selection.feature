Feature: Create News - Tag Selection
  As a logged-in user
  I want to select tags when creating news
  So that the system allows choosing between 1 and 3 tags

  Background:
    Given the user is logged in the system
    And the user opens the 'Create News' page

  Scenario Outline: Publish news with a valid number of selected tags
    When the user selects the tags: "<selected_tags>"
    And the user enters the title "<title>"
    And the user enters the main text "<text>"
    And the user clicks the Publish button
    Then the news should be published successfully
    And the published news should contain the tags: "<expected_present>"
    And the published news should not contain tags: "<expected_absent>"

    Examples:
      | selected_tags         | title        | text                       | expected_present      | expected_absent                  |
      | News                  | Test One Tag | Test content with 20 chars | News                  | Events,Education,Initiatives,Ads |
      | News,Events,Education | Test Three   | Test content with 20 chars | News,Events,Education | Initiatives,Ads                  |

  Scenario Outline: Selecting more than 3 tags is not allowed
    When the user selects the tags: "<selected_tags>"
    Then only 3 tags should remain selected: "<expected_selected>"
    And the selected tags should not include: "<expected_unselected>"

    Examples:
      | selected_tags                        | expected_selected          | expected_unselected |
      | News,Events,Education,Initiatives,Ads | News,Events,Education      | Initiatives,Ads     |

  Scenario Outline: Publishing news without selecting any tags is not allowed
    When the user enters the title "<title>"
    And the user enters the main text "<text>"
    Then the Publish button should be disabled

    Examples:
      | title | text                       |
      | Test  | Test content with 20 chars |


