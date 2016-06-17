Feature: TC-117

  TC-117
  To check whether “search order id” query is working and displaying the relevant details.
  1. Launch hotel reservation application using URL as in test data.
  2. Login to the application using username and password as in test data.
  3. Click on booked itinerary link.
  4. Enter the order id.
  5. Verify that the relevant details are displayed
  http://adactin.com/HotelApp/index.php
  User:{test username}
  Password:{test password}
  Order id :pick existing order id
  Search Order ID query should display the relevant details for Order ID

  Scenario: verify search order id query shows relevant details correctly
    Given I am on the adactin site
    And I log in with my credentials
    And I am logged in
    When I click on the booked itinerary link
    And provide the order id
    And click on the Go button
    Then the orderdetails of the selected order are shown correctly