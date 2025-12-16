Feature: Create News - Cancel flow
  As a logged-in user
  I want to cancel Create News safely
  So that my data is either preserved or discarded depending on my choice

  Background:
    Given the user is logged in the system
    And the user opens Eco News via header
    And the user opens the Create News page
    And the Create News page is fully loaded

  Scenario: Cancel button is visible and enabled on Create News page
    Then the Cancel button should be visible in newsPage
    And the Cancel button should be enabled

  Scenario Outline: Cancel with filled form shows confirmation popup
    When the user enters title "<title>" and content "<content>"
    And the user clicks Cancel on Create News
    Then the cancel confirmation popup should be displayed
    And the popup should contain Continue editing button
    And the popup should contain Yes cancel button

    Examples:
      | title           | content                |
      | Title example   | Content Example        |
      | Valid title     | valid content example  |

  Scenario Outline: Confirm cancel discards data, redirects to Eco News, and clears form when reopened
    When the user enters title "<title>" and content "<content>"
    And the user clicks Cancel on Create News
    And the user confirms cancel in the popup
    Then the user should be redirected to Eco News page
    And the current URL should contain Eco News path
    And the news card with title "<title>" should not exist in Eco News list
    When the user opens Create News page from Eco News
    Then the Create News title field should be empty
    And the Create News content editor should be empty

    Examples:
      | title             | content              |
      | Unique title {ts} | Unique content {ts} |

  Scenario Outline: Continue editing keeps user on Create News and preserves entered data
    When the user enters title "<title>" and content "<content>"
    And the user clicks Cancel on Create News
    And the user clicks Continue editing in the popup
    Then the user should stay on Create News page
    And the Create News title should equal "<title>"
    And the Create News content should equal "<content>"

    Examples:
      | title            | content            |
      | Title entered    | Content entered    |

  Scenario Outline: Clicking outside popup closes it and preserves entered data
    When the user enters title "<title>" and content "<content>"
    And the user clicks Cancel on Create News
    And the user clicks outside cancel popup
    Then the user should stay on Create News page
    And the Create News title should equal "<title>"
    And the Create News content should equal "<content>"

    Examples:
      | title            | content            |
      | Title entered    | Content entered    |

  Scenario Outline: Closing popup via cross keeps user on Create News and preserves entered data
    When the user enters title "<title>" and content "<content>"
    And the user clicks Cancel on Create News
    And the user closes cancel popup via cross icon
    Then the user should stay on Create News page
    And the Create News title should equal "<title>"
    And the Create News content should equal "<content>"

    Examples:
      | title            | content            |
      | Title entered    | Content entered    |

  Scenario Outline: Header navigation with entered data shows cancel confirmation popup
    When the user enters title "Title entered" and content "Content entered"
    And the user clicks header nav item "<nav_item>" without navigation
    Then the cancel confirmation popup should be displayed
    And the popup should contain Continue editing button
    And the popup should contain Yes cancel button

    Examples:
      | nav_item  |
      | ECO_NEWS  |

  Scenario Outline: Header navigation with empty form redirects without popup
    Given the Create News URL should contain "/create-news"
    When the user clicks header nav item "<nav_item>"
    Then the current URL should not contain "/create-news"
    And the current URL should contain "<expected_path>"

    Examples:
      | nav_item  | expected_path |
      | ECO_NEWS  | /news         |

  Scenario: Cancel popup should appear within one second
    When the user enters title "Title entered" and content "Content entered"
    And the user clicks Cancel and measures popup appearance time
    Then the cancel popup should be visible
    And the popup should appear within 1000 ms
