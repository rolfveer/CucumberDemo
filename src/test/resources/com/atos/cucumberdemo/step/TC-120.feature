Feature: TC-120

  TC-120:
  To Verify Title of every Page reflects what the page objective is.
  For example Title of Search Hotel page should have “Search Hotel”
  1. Launch hotel reservation application using URL as in test data.
  2. Login to the application using username and password as in test data.
  3. Verify that title of each page is the same as the page objective
  4. Click on Search hotel link and verify whether application directs to search hotel form
  5. Click on booked itinerary link and verify that application directs to booked itinerary form
  http://adactin.com/HotelApp/index.php
  User:{test username}
  Password:{test password}
  Title of each page should reflect its objective and the buttons should redirect as specified, to the relevant page


  Scenario: verify each page reflects its objective_1
    Given I am on the adactin site
    And I log in with my credentials
    And I am logged in
    Then the title of the page is "Search Hotel"

  Scenario: verify each page reflects its objective_2
    Given I am on the adactin site
    And I log in with my credentials
    And I am logged in
    When I set the location to "Sydney"
    And I select Hotel "Hotel Creek"
    And I select Room type "Standard"
    And I select the number of rooms "2"
    And I select the amount of adults "1"
    And I select the amount of children "0"
    And the day that I check in is "0" days from now
    And the day that I check out is "1" days from now
    And I click on the search button
    Then the title of the page is "Select Hotel"

  Scenario: verify each page reflects its objective_3
    Given I am on the adactin site
    And I log in with my credentials
    And I am logged in
    When I set the location to "Sydney"
    And I select Hotel "Hotel Creek"
    And I select Room type "Standard"
    And I select the number of rooms "2"
    And I select the amount of adults "1"
    And I select the amount of children "0"
    And the day that I check in is "0" days from now
    And the day that I check out is "1" days from now
    And I click on the search button
    And I select the hotel and click on the continue button
    Then the title of the page is "Book A Hotel"

  Scenario: verify each page reflects its objective_4
    Given I am on the adactin site
    And I log in with my credentials
    And I am logged in
    When I set the location to "Sydney"
    And I select Hotel "Hotel Creek"
    And I select Room type "Standard"
    And I select the number of rooms "2"
    And I select the amount of adults "1"
    And I select the amount of children "0"
    And the day that I check in is "0" days from now
    And the day that I check out is "1" days from now
    And I click on the search button
    And I select the hotel and click on the continue button
    And the customerdata is entered
    And I click on the Book now button
    And The Order number is generated
    Then the title of the page is "Booking Confirmation"
    And I click on the My itinerary button
    And I check the checkbox of the order inserted
    And I click on the Cancel Selected button


  Scenario: verify each page reflects its objective_5
    Given I am on the adactin site
    And I log in with my credentials
    And I am logged in
    When I click on the booked itinerary link
    Then the title of the page is "Booked Itinerary"



