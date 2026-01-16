Feature: Source field validation on Create News page
  As a logged-in user
  I want the Source field to validate URL input correctly
  So that invalid URLs show validation and publishing is blocked, while valid or empty sources allow publishing

  Background:
    Given the user is logged in the system
    And the user opens the 'Create News' page

  @news @source_validation @ui
  Scenario Outline: Invalid Source shows validation warning and red border
    When enter "<invalidUrl>" into the Source field
    Then the Source field shows a validation warning
    And the Source field border color is red

    Examples: invalidUrls
      | invalidUrl          |
      | invalid-url         |
      | ht!tp:/bad          |
      | 12345               |
      | http:/missing-slash |

  @news @source_validation @ui
  Scenario Outline: Publish button is disabled for invalid Source URLs
    When enter "<invalidUrl>" into the Source field
    Then the Publish button is disabled

    Examples: invalidUrlsForSubmit
      | invalidUrl                  |
      | invalid-url                 |
      | ht!tp:/bad                  |
      | 12345                       |
      | http:/missing-slash         |
      | http://                     |
      | https://                    |
      | http:/m.                    |
      | https://.com                |
      | https://te st.com           |
      | https://test.com            |
      | https://testnodot           |
      | https://-test.com           |
      | https://test-.com           |
      | https://test..com           |
      | https:///test.com           |
      | https://tes$t.com           |
      | https://test.com/path space |

  @news @source_validation @ui
  Scenario Outline: News is published when Source is a valid URL
    Given all required fields for the news draft are filled with valid data
    When enter "<validUrl>" into the Source field
    And click the Publish button
    Then success message is displayed containing text 'Your news has been successfully published'
    And the user is navigated away from the Create News page to the Eco News page
    And published news with title is in the news list
    And published news contains entered source url "<validUrl>"

    Examples: validSources
      | validUrl          |
      | http://test1.com  |
      | https://test2.com |

  @news @source_validation @ui
  Scenario: News is published when Source field is empty
    Given all required fields for the news draft are filled with valid data
    When clear the Source field
    And click the Publish button
    Then success message is displayed containing text 'Your news has been successfully published'
    And the user is navigated away from the Create News page to the Eco News page
    And published news with title is in the news list