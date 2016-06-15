Feature: TC-114

  TC-114
  Verify Order number is generated in booking confirmation page
  1. Launch hotel reservation application using URL as in test data.
  2. Login to the application using username and password as in test data.
  3. Select location as in test data.
  4. Select hotel as in test data.
  5. Select room type as in test data.
  6. Select no-of-rooms as in test data.
  7. Enter check-out-date as in test data.
  8. Select No-of-adults as in test data.
  9. Select No-of-children as in test data.
  10. Click on Search button.
  11. Select the hotel and click on continue button
  12. Verify Order number is generated
  URL: http://adactin.com/HotelApp/index.php
  User:{test username}
  Password:{test password}
  Location: Sydney
  Hotel: hotel Creek
  Room type: standard
  No-of-rooms:2
  Check-in-date: today’s date
  Check-out-date:today+1 date
  No-of-adults:1
  No-of-children: 0

  Scenario: verify Order number is generated
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
    Then The Order number is generated
