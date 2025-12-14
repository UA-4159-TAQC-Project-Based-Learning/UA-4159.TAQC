Feature: Create News - Crop image
  As a logged-in user
  I want to crop an uploaded image
  So that I can submit the selected area or cancel and return to dropzone

  Background:
    Given the user is logged in the system
    And the user opens Eco News via header
    And the user opens the Create News page
    And the Create News page is fully loaded

  Scenario: Upload image, move crop frame, submit should apply selection
    When the user uploads image for cropping "crop-image-test-01.jpg"
    Then crop action buttons should be visible
    And crop overlay and frame should be visible
    And crop frame should have non-zero size
    When the user drags crop frame by 50 and 40
    Then crop frame position should change
    When the user submits cropped image
    Then image preview should be displayed

  Scenario: Upload image, cancel should return to dropzone state
    When the user uploads image for cropping "crop-image-test-01.jpg"
    Then cropper should be active
    When the user cancels image cropping
    Then dropzone should be displayed
    And cropper should not be active
    And image preview should not be displayed
