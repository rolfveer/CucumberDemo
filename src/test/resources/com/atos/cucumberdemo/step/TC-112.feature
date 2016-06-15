Feature: TC-112
To check correct Final billed price is Total Price + 10% Total price in Book a Hotel page.
1. Launch hotel reservation application using URL as in test data.
2. Login to the application using username and password as in test data.
3. Select location as in test data. Select Hotel as in test data.
4. Select room type as in test data.
5. Select no-of-rooms as in test data.
6. Enter check-out-date as in test data.
7. Select No-of-adults as in test data.
8. Select No-of-children as in test data.
9. Click on Search button.
10. Select the hotel and click on continue button
11. Verify that Final Billed Price is being calculated as (price-per-night*no-of-rooms*no-of-days.
URL: http://adactin.com/HotelApp/index.php
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
Final billed Price=125+12.5=137.5 in Book a Hotel page

Scenario: verify final billed price
Given I am on the adactin site
And I log in with my credentials
And I am logged in
When I set the location to "Sydney"
And I select Hotel "Hotel Creek"
And I select Room type "Standard"
And I select the number of rooms "1"
And I select the amount of adults "1"
And I select the amount of children "0"
And the day that I check in is "0" days from now
And the day that I check out is "1" days from now
And I click on the search button
And I select the hotel and click on the continue button
Then The billed price should be correct