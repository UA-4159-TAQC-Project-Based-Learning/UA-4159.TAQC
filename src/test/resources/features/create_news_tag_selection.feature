Feature: Create News - Tag Selection
  As a logged-in user
  I want to select tags when creating news
  So that the system allows choosing between 1 and 3 tags

  Background:
    Given the user is logged in the system
    And the user opens the 'Create News' page

  Scenario: Publish news with one selected tag
    When the user selects the tag "News"
    And the user enters the title "Test"
    And the user enters the main text "Test content with 20 chars"
    And the user clicks the Publish button
    Then the news should be published successfully
    And the published news should contain the tag 'News'
    And the published news should not contain tags: 'Events', 'Education', 'Initiatives', 'Ads'

  Scenario: Publish news with three selected tags
    When the user selects the tags: 'News', 'Events', 'Education'
    And the user enters the title "Test"
    And the user enters the main text "Test content with 20 chars"
    And the user clicks the Publish button
    Then the news should be published successfully
    And the published news should contain the tags: 'News', 'Events', 'Education'
    And the published news should not contain tags: 'Initiatives', 'Ads'

  Scenario: Selecting more than three tags is not allowed
    When the user selects the tags: 'News','Events', 'Education', 'Initiatives', 'Ads'
    Then only 3 tags should remain selected: 'News', 'Events', 'Education'
    And the selected tags should not include: 'Initiatives', 'Ads'

  Scenario: Publishing news without selecting any tags is not allowed
    When the user enters the title "Test"
    And the user enters the main text "Test content with 20 chars"
    Then the Publish button should be disabled

