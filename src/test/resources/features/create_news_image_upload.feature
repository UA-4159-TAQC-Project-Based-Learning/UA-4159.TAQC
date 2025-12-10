Feature: Create News - Image Upload Validation
  As a logged-in user
  I want to upload an image when creating news
  So that only valid PNG/JPG files up to 10MB are accepted

  Background:
    Given the user is logged in the system
    And the user opens the 'Create News' page

  Scenario Outline: Successful upload of valid image
    When the user uploads the image file "<file_name>"
    Then the image should be uploaded successfully
    And no validation errors should be displayed
    And the image preview should be visible

    Examples:
      | file_name       |
      | imageJPG5MB.jpg |
      | imagePNG5MB.png |


  Scenario Outline: Warning when uploading image of invalid format or size
    When the user uploads the image file "<file_name>"
    Then a validation error should be displayed with the message "Upload only PNG or JPEG. File size must be less than 10MB"
    And the upload image field should be highlighted in red

    Examples:
      | file_name        |
      | imageGIF1MB.gif  |
      | imageJPG15MB.jpg |
