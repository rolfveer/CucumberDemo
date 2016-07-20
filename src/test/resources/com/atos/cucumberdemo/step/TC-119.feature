Feature: TC-119

  TC-119
  To verify that the order gets cancelled after click on Cancel order number link
  1. Launch hotel reservation application using URL as in test data.
  2. Login to the application using username and password as in test data.
  3. Book the Hotel as in previous test cases. Keep a note of order number generated
  4. Click on Booked Itinerary link
  5. Search for order number booked
  6. Click on Cancel <Order Number>
  7. Click Yes on pop-up which asks to cancel order or not
  8. Verify that order number is cancelled and no longer exists in Booked Itinerary page
  http://adactin.com/HotelApp/index.php
  User:{test username}
  Password:{test password}
  Order number should not longer be present in booked itinerary page after cancellation

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


  Scenario: Verify order gets cancelled after click on Cancel order number link
    When I click on the My itinerary button
    And I check the checkbox of the order inserted
    And I click on the Cancel Selected button
    Then The orderdata has been removed from the booked itinerary page