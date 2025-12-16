Feature: Create News - Cancel, Preview and Publish buttons
  As a user creating a news/eco-news item
  I want to be able to cancel creating a news, preview it, or publish it
  So that I can confirm destructive actions, review my content, and submit it for publication

  Background:
    Given the user is logged in the system
    And the user opens the 'Create News' page
    And all required fields for the news draft are filled with valid data

  @create-news @buttons @cancel
  Scenario: Clicking the Cancel button on the Create News page opens a confirmation dialog modal
    When click the Cancel button
    Then a confirmation dialog should be visible
    And the dialog title should indicate a warning about cancelling changes

  @create-news @buttons @cancel
  Scenario: A confirmation dialog modal contains all buttons
    When click the Cancel button
    #Then a confirmation dialog should be visible
    Then the cancel news dialog should contain all buttons


  @create-news @buttons @cancel
  Scenario: Choosing "Continue editing" closes the dialog and returns me to the Create News page
    Given a confirmation dialog should be visible
    When click the Continue editing button
    Then the confirmation dialog should be closed
    #And the "Create News" page is opened with my draft preserved

  @create-news @buttons @cancel
  Scenario: Choosing "Yes, cancel" confirms cancellation and navigates away
    Given the cancel confirmation dialog is open
    When I click the "Yes, cancel" button
    Then the news creation should be cancelled
    And I should be navigated away from the Create News page to the Eco News page
    # Note: behavior follows the application flow implemented in CancelNewsModal.clickYesCancelButton()

  @create-news @buttons @cancel
  Scenario: Clicking the close icon closes the confirmation dialog without cancelling
    Given the cancel confirmation dialog is open
    When I click the dialog close icon
    Then the confirmation dialog should be closed
    And I should remain on the "Create News" page with my draft preserved

  @create-news @buttons @cancel
  Scenario: Clicking outside the pop-up closes the confirmation dialog (backdrop)
    Given the cancel confirmation dialog is open
    When I click outside the pop-up on the backdrop
    Then the confirmation dialog should be closed
    And I should remain on the "Create News" page with my draft preserved

  @create-news @buttons @preview
  Scenario: Preview button displays a preview of the news entry
    When I click the "Preview" button
    Then a preview of the news entry should be displayed to the user

  @create-news @buttons @publish
  Scenario: Publish button submits the news for publication
    When I click the "Publish" button
    Then the news should be submitted for publication
    And the system should show confirmation or navigate according to the post-publish flow
