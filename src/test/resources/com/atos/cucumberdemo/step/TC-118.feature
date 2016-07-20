Feature: TC-118

  TC-118
  Verify that all the details of newly generated order number in booked itinerary page are correct and match with data during booking.
  1. Launch hotel reservation application using URL as in test data.
  2. Login to the application using username and password as in test data.
  3. Book an order as in previous test cases
  4. Click on My itinerary button
  5. Search for Order number
  6. Verify all the details of order number are correct as entered during saving order
  http://adactin.com/HotelApp/index.php
  User:{test username}
  Password:{test password}
  Location: Sydney
  Hotel: Hotel Creek
  Room type: standard
  No-of-rooms:2
  Check-in-date: today’s date
  Check-out-date:today+1 date
  No-of-adults:1
  No-of-children: 0
  All the details in booked itinerary page should be same as those entered during booking

  Background: Order is booked
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

  Scenario: Verify all details in booked itinerary page are the same as those entered during booking
    When I click on the My itinerary button
    Then The itinerary details are correct