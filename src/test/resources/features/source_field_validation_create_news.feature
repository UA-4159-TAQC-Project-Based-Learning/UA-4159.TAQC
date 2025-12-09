Feature: Source field validation on Create News page
  As a logged-in user
  I want the Source field to validate URL input correctly
  So that invalid URLs show validation and publishing is blocked, while valid or empty sources allow publishing

  Background:
    Given I am logged in as a valid user
    And I am on the Create News page

  @news @source_validation @ui
  Scenario Outline: Invalid Source shows validation warning and red border
    When I enter "<invalidUrl>" into the Source field
    Then the Source field shows a validation warning
    And the Source field border color is "rgb(255, 0, 0)"

    Examples: invalidUrls
      | invalidUrl          |
      | invalid-url         |
      | ht!tp:/bad          |
      | 12345               |
      | http:/missing-slash |

  @news @source_validation @ui
  Scenario Outline: Publish button is disabled for invalid Source URLs
    When I enter "<invalidUrl>" into the Source field
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
    Given I have filled mandatory news fields with a unique title and valid content
    When I enter "<validUrl>" into the Source field
    And I click Publish
    Then the news should be published and visible in the news list with the title I entered

    Examples: validSources
      | validUrl          |
      | http://test1.com  |
      | https://test2.com |

  @news @source_validation @ui
  Scenario: News is published when Source field is empty
    Given I have filled mandatory news fields with a unique title and valid content
    And I clear the Source field
    When I click Publish
    Then the news should be published and visible in the news list with the title I entered