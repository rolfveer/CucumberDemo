package com.atos.cucumberdemo.step;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import jdk.nashorn.internal.AssertsEnabled;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Vincent Free on 27-3-2015.
 */


public class adactinSteps {
    private final WebDriver webDriver;
    private List<String> results = new ArrayList<String>();
    private int adults;
    private int no_rooms;
    private String orderid;


    public adactinSteps(SharedDriver webDriver) {
        this.webDriver = webDriver; 
        //this.driver = new driver;
    }

    @Given("^I am on the adactin site$")
    public void I_am_on_the_adactin_site() throws Throwable {
        webDriver.manage().window().maximize();
        webDriver.get("http://adactin.com/HotelAppBuild2/");
    }

    @When("^I log in with my credentials$")
    public void I_log_in_with_my_credentials() throws Throwable {
        webDriver.findElement(By.id("username")).sendKeys("TestlabCucumberDemo");
        webDriver.findElement(By.id("password")).sendKeys("testlabcucumberdemo");
        webDriver.findElement(By.id("login")).click();
    }

    @Then("^I am logged in$")
    public void I_am_logged_in() throws Throwable {
        WebElement element = webDriver.findElement(By.id("username_show"));
        String WelcomeText = "Hello TestlabCucumberDemo!";
        WelcomeText.equals(element.getText());
    }

    @When("^I log in with the wrong credentials$")
    public void I_log_in_with_the_wrong_credentials() throws Throwable {
        webDriver.findElement(By.id("username")).sendKeys("TestlabFout");
        webDriver.findElement(By.id("password")).sendKeys("Foutje");
        webDriver.findElement(By.id("login")).click();
    }

    @Then("^I should get a message$")
    public void I_should_get_a_message() {
        WebElement element = webDriver.findElement(By.className("auth_error"));
        String InvalidLogin = "Invalid1 Login Details";
        InvalidLogin.equals(element.getText());
    }

   /* @When("^I fill out \"([^\"]*)\" with \"([^\"]*)\"$")
    public void I_fill_out_with(String dorpdown, String choice) {
        WebElement element = webDriver.findElement(By.id(dorpdown.toLowerCase()));
        element.click();
        element.findElement(By.xpath(choice)).click();
        element.findElement(By.id("Submit")).click();
    }*/

    @And("^the day that I check in is \"([^\"]*)\" days from now$")
    public void the_day_that_I_check_in_is_days_from_now(int days) throws Throwable {
        WebElement element = webDriver.findElement(By.xpath("//input[@id='datepick_in']"));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        java.util.Date mydate = format.parse(today);
        mydate = DateUtils.addDays(mydate, days);
        String inputdate = new SimpleDateFormat("dd/MM/yyyy").format(mydate);
        element.clear();
        element.sendKeys(inputdate);
        this.results.add(inputdate);
    }

    @And("^the day that I check out is \"([^\"]*)\" days from now$")
    public void the_day_that_I_check_out_is_days_from_now(int days) throws Throwable {
        WebElement element = webDriver.findElement(By.xpath("//input[@id='datepick_out']"));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        java.util.Date mydate = format.parse(today);
        mydate = DateUtils.addDays(mydate, days);
        String inputdate = new SimpleDateFormat("dd/MM/yyyy").format(mydate);
        element.clear();
        element.sendKeys(inputdate);
        this.results.add(inputdate);
    }

    @Then("^the search results in an error message$")
    public void the_search_results_in_an_error_message() throws Throwable {
        webDriver.findElement(By.id("Submit")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkin_span")));
        WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_span")));
        assertEquals("Check-In Date shall be before than Check-Out Date", element.getText());
        assertEquals("Check-Out Date shall be after than Check-In Date", element1.getText());
    }

    @When("^I set the location to \"([^\"]*)\"$")
    public void I_set_the_location_to(String location) throws Throwable {
        this.results.add(location);
        WebElement element = webDriver.findElement(By.id("location"));
        element.click();
        //element.findElement(By.xpath("//select[@id='location']/option[text()='"+location+"']")).click();
        element.findElement(By.id("location")).click();
        element.sendKeys(location);
    }

    @And("^I select Hotel \"([^\"]*)\"$")
    public void I_select_Hotel(String hotels) throws Throwable {
        this.results.add(hotels);
        WebElement element = webDriver.findElement(By.id("hotels"));
        element.click();
        element.sendKeys(hotels);
    }

    @And("^I select Room type \"([^\"]*)\"$")
    public void I_select_Room_type(String room) throws Throwable {
        this.results.add(room);
        WebElement element = webDriver.findElement(By.id("room_type"));
        element.click();
        element.sendKeys(room);
    }

    @And("^I select the number of rooms \"([^\"]*)\"$")
    public void I_select_the_number_of_rooms(String room) throws Throwable {
        this.results.add(room + " Rooms");
        this.no_rooms = Integer.parseInt(room);
        WebElement element = webDriver.findElement(By.id("room_nos"));
        if (room.equals("1") && (element.getAttribute("value").contains("One") || element.getAttribute("value").contains("1"))) {

            //System.out.println("number of rooms would be: "+room);
        } else {
            element.click();
            element.sendKeys(room);
        }
    }

    @And("^I select the amount of adults \"([^\"]*)\"$")
    public void I_select_the_amount_of_adults(String adult) throws Throwable {
        //this.results.add(adult);
        this.adults = Integer.parseInt(adult);
        WebElement element = webDriver.findElement(By.id("adult_room"));
        if (adult.equals("1") && (element.getAttribute("value").contains("One") || element.getAttribute("value").contains("1"))) {

            //System.out.println("number of adults: "+adult);
        } else {
            element.click();
            element.sendKeys(adult);
        }
    }

    @And("^I select the amount of children \"([^\"]*)\"$")
    public void I_select_the_amount_of_children(String children) throws Throwable {
        //this.results.add(children);
        WebElement element = webDriver.findElement(By.id("adult_room"));
        if (children == "0") {

        } else {
            element.click();
            element.sendKeys(children);
        }
    }

    @Then("^The system should report an error message 'Enter Valid dates'$")
    public void The_system_should_report_an_error_message_Enter_Valid_dates() throws Throwable {
        webDriver.findElement(By.id("Submit")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkin_span")));
        assertEquals("Check-In Date should be either Today or Later Date", element.getText());
    }


    @Then("^The right hotel should be shown$")
    public void The_right_hotel_should_be_shown() throws Throwable {
        webDriver.findElement(By.id("Submit")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element;
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("select_form")));
        for (Object object : results) {
            element = webDriver.findElement(By.xpath("//input[@value='" + object.toString() + "']"));
            //System.out.println(element.getAttribute("value"));
            assertEquals(element.getAttribute("value").toLowerCase(), object.toString().toLowerCase());

        }
    }

    @And("^The price should be correct$")
    public void The_price_should_be_correct() throws Throwable {
        int price = 125 * adults * no_rooms;
        WebElement element = webDriver.findElement(By.id("total_price_0"));
        assertEquals("AUD $ " + price + "", element.getAttribute("value"));
        //System.out.println(element.getAttribute("value"));
    }

    @And("^I want to logout and verify that I am logged out$")
    public void I_want_to_logout_and_verify_that_I_am_logged_out() throws Throwable {
        WebElement element = webDriver.findElement(By.xpath("//a[@href='Logout.php']"));
        element.click();
        element = webDriver.findElement(By.className("reg_success"));
        assertEquals("You have successfully logged out. Click here to login again", element.getText());
    }

    @Given("^I am on the Select Hotel page$")
    public void I_am_on_the_Select_Hotel_page() throws Throwable {
        webDriver.getCurrentUrl().equals("http://adactin.com/HotelAppBuild2/SelectHotel.php");
        WebElement element = webDriver.findElement(By.className("login_title"));
        assertEquals("Select Hotel", element.getText());
    }

    @When("^I select the first hotel$")
    public void I_select_the_first_hotel() throws Throwable {
        webDriver.findElement(By.id("radiobutton_0")).click();
        webDriver.findElement(By.id("continue")).click();
    }

    @Then("^The \"([^\"]*)\" page should be shown$")
    public void The_page_should_be_shown(String page) throws Throwable {
        webDriver.getCurrentUrl().equals("http://adactin.com/HotelAppBuild2/BookHotel.php");
        WebElement element = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td[text()]"));
        assertEquals(page, element.getText());
    }

    @And("^The price should be the same as the previous screen$")
    public void The_price_should_be_the_same_as_the_previous_screen() throws Throwable {
        //TODO check hotel info and price
        //WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element;
        //element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Book A Hotel")));
        for (Object object : results) {
            if (object.toString().contains("Rooms")) {
                String newResult;
                newResult = object.toString();
                newResult.replace("Rooms", "Room(s)");
                //System.out.println(newResult);
            } else {
                element = webDriver.findElement(By.xpath("//input[@value='" + object.toString() + "']"));
                //System.out.println(element.getAttribute("value"));
                assertEquals(element.getAttribute("value").toLowerCase(), object.toString().toLowerCase());
            }
        }
    }

    @Then("^The billed price should be correct$")
    public void theBilledPriceShouldBeCorrect() throws Throwable {
        double price = 125 ;
        double total = price * adults * no_rooms * 110/100;
        WebElement element = webDriver.findElement(By.id("final_price_dis"));
        assertEquals("AUD $ " + total + "", element.getAttribute("value"));
    }

    @And("^I click on the search button$")
    public void iClickOnTheSearchButton() throws Throwable {
        webDriver.findElement(By.id("Submit")).click();
    }

    @And("^I select the hotel and click on the continue button$")
    public void iSelectTheHotelAndClickOnTheContinueButton() throws Throwable {
        webDriver.findElement(By.cssSelector("input[id='radiobutton_0']")).click();
        webDriver.findElement(By.id("continue")).click();
    }

    @Then("^The displayed data should be correct$")
    public void theDisplayedDataShouldBeCorrect() throws Throwable {
        WebElement hotelname = webDriver.findElement(By.id("hotel_name_dis"));
        assertEquals(hotelname.getAttribute("value"), "Hotel Creek");
        WebElement location = webDriver.findElement(By.id("location_dis"));
        assertEquals(location.getAttribute("value"), "Sydney");
        WebElement roomtype = webDriver.findElement(By.id("room_type_dis"));
        assertEquals(roomtype.getAttribute("value"), "Standard");
        WebElement numberofrooms = webDriver.findElement(By.id("room_num_dis"));
        assertEquals(numberofrooms.getAttribute("value"), "2 Room(s)");
        WebElement totaldays = webDriver.findElement(By.id("total_days_dis"));
        assertEquals(totaldays.getAttribute("value"), "1 Day(s)");
        WebElement pricepernight = webDriver.findElement(By.id("price_night_dis"));
        assertEquals(pricepernight.getAttribute("value"), "AUD $ 125");
    }

    @Then("^The Order number is generated$")
    public void theOrderNumberIsGenerated() throws Throwable {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("order_no")));
        WebElement element1 = webDriver.findElement(By.id("order_no"));
        assertEquals(10, element1.getAttribute("value").length());
        orderid = element1.getAttribute("value");
    }

    @And("^the customerdata is entered$")
    public void theCustomerdataIsEntered() throws Throwable {
        WebElement firstname = webDriver.findElement(By.id("first_name"));
        firstname.sendKeys("Rolf");
        WebElement lastname = webDriver.findElement(By.id("last_name"));
        lastname.sendKeys("van der Veer");
        WebElement address = webDriver.findElement(By.id("address"));
        address.sendKeys("Aweg 50 Groningen");
        WebElement creditcardnum = webDriver.findElement(By.id("cc_num"));
        creditcardnum.sendKeys("1234567890987654");
        WebElement creditcardtype = webDriver.findElement(By.id("cc_type"));
        creditcardtype.sendKeys("AMEX");
        WebElement expmonth = webDriver.findElement(By.id("cc_exp_month"));
        expmonth.sendKeys("February");
        WebElement expyear = webDriver.findElement(By.id("cc_exp_year"));
        expyear.sendKeys("2018");
        WebElement cvvnumber = webDriver.findElement(By.id("cc_cvv"));
        cvvnumber.sendKeys("1234");
    }

    @And("^I click on the Book now button$")
    public void iClickOnTheBookNowButton() throws Throwable {
        webDriver.findElement(By.id("book_now")).click();
    }

    @And("^I click on the My itinerary button$")
    public void iClickOnTheMyItineraryButton() throws Throwable {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("order_no")));
        webDriver.findElement(By.id("my_itinerary")).click();
    }

    @Then("^The itinerary details are not editable$")
    public void theItineraryDetailsAreNotEditable() throws Throwable {
        String digits;
        WebElement element1 = webDriver.findElement(By.xpath("//input[@value='"+orderid+"']"));
        digits = element1.getAttribute("id").substring(9);
        String hotelid = "hotel_name_" + digits;
        String locationid = "location_" + digits;
        String roomsid = "rooms_" + digits;
        String totalpriceid = "total_price_" + digits;
        WebElement hotelname = webDriver.findElement(By.id(hotelid));
        assertEquals (hotelname.getAttribute("onfocus"), "disable_ctrlV()");
        WebElement loc = webDriver.findElement(By.id(locationid));
        assertEquals (loc.getAttribute("onfocus"), "disable_ctrlV()");
        WebElement rms = webDriver.findElement(By.id(roomsid));
        assertEquals (rms.getAttribute("onfocus"), "disable_ctrlV()");
        WebElement totpr = webDriver.findElement(By.id(totalpriceid));
        assertEquals (totpr.getAttribute("onfocus"), "disable_ctrlV()");
    }

    @Then("^The itinerary details are correct$")
    public void theItineraryDetailsAreCorrect() throws Throwable {
        String digits;
        WebElement element1 = webDriver.findElement(By.xpath("//input[@value='"+orderid+"']"));
        digits = element1.getAttribute("id").substring(9);
        String hotelid = "hotel_name_" + digits;
        String locationid = "location_" + digits;
        String roomsid = "rooms_" + digits;
        String totalpriceid = "total_price_" + digits;
        String lastnameid = "last_name_" + digits;
        WebElement hotelname = webDriver.findElement(By.id(hotelid));
        assertEquals (hotelname.getAttribute("value"), "Hotel Creek");
        WebElement loc = webDriver.findElement(By.id(locationid));
        assertEquals (loc.getAttribute("value"), "Sydney");
        WebElement rms = webDriver.findElement(By.id(roomsid));
        assertEquals (rms.getAttribute("value"), "2 Room(s)");
        WebElement totpr = webDriver.findElement(By.id(totalpriceid));
        assertEquals (totpr.getAttribute("value"), "AUD $ 275");
        WebElement lastnm = webDriver.findElement(By.id(lastnameid));
        assertEquals (lastnm.getAttribute("value"), "van der Veer");

    }

    @Then("^the orderdetails of the selected order are shown correctly$")
    public void theOrderdetailsOfTheSelectedOrderAreShownCorrectly() throws Throwable {
        WebElement ord = webDriver.findElement(By.id("order_id_139730"));
        assertEquals (ord.getAttribute("value"), "8HZ7OI7314");
        WebElement hotelname = webDriver.findElement(By.id("hotel_name_139730"));
        assertEquals (hotelname.getAttribute("value"), "Hotel Creek");
        WebElement loc = webDriver.findElement(By.id("location_139730"));
        assertEquals (loc.getAttribute("value"), "Sydney");
        WebElement rms = webDriver.findElement(By.id("rooms_139730"));
        assertEquals (rms.getAttribute("value"), "2 Room(s)");
        WebElement totpr = webDriver.findElement(By.id("total_price_139730"));
        assertEquals (totpr.getAttribute("value"), "AUD $ 149");
        WebElement lastnm = webDriver.findElement(By.id("last_name_139730"));
        assertEquals (lastnm.getAttribute("value"), "van der Veer");
    }

    @When("^I click on the booked itinerary link$")
    public void iClickOnTheBookItineraryLink() throws Throwable {
        webDriver.findElement(By.xpath("//a[@href='BookedItinerary.php']")).click();
    }

    @And("^provide the order id$")
    public void provideTheOrderId() throws Throwable {
        WebElement ord = webDriver.findElement(By.id("order_id_text"));
        ord.sendKeys("8HZ7OI7314");
    }

    @And("^click on the Go button$")
    public void clickOnTheGoButton() throws Throwable {
        webDriver.findElement(By.id("search_hotel_id")).click();
    }

    @And("^I check the checkbox of the order inserted$")
    public void iCheckTheCheckboxOfTheOrderInserted() throws Throwable {
        String digits;
        WebElement element1 = webDriver.findElement(By.xpath("//input[@value='"+orderid+"']"));
        digits = element1.getAttribute("id").substring(9);
        WebElement element2 = webDriver.findElement(By.xpath("//input[@value='"+digits+"']"));
        element2.click();
    }

    @And("^I click on the Cancel Selected button$")
    public void iClickOnTheCancelSelectedButton() throws Throwable {
        WebElement element = webDriver.findElement(By.name("cancelall"));
        element.click();
        webDriver.switchTo().alert().accept();
    }

    @Then("^The orderdata has been removed from the booked itinerary page$")
    public void theOrderdataHasBeenRemovedFromTheBookedItineraryPage() throws Throwable {

                //WebElement element = webDriver.findElement(By.xpath("//input[@value='600J05K95G']"));
        try {
            WebElement element = webDriver.findElement(By.xpath("//input[@value='" + orderid + "']"));
            assertEquals(element,null);
        }
        catch (NoSuchElementException e)
        {

        }

    }

    @Then("^the title of the page is \"([^\"]*)\"$")
    public void theTitleOfThePageIs(String title) throws Throwable {
        this.results.add(title);
        WebElement element = webDriver.findElement(By.xpath("//td[@class = 'login_title']"));
        String retrievedtitle = element.getText();

        if (retrievedtitle.length() >25) {
            assertEquals(retrievedtitle.substring(0, 12), title);
        }
        else {
            if (retrievedtitle.equals("Back")) {
                WebElement element2 = webDriver.findElement(By.xpath("//tbody/tr[2]/td[@class = 'login_title']"));
                retrievedtitle = element2.getText();
                assertEquals(retrievedtitle, title);
            }
            else {
                assertEquals(retrievedtitle, title);
            }
        }

    }
}