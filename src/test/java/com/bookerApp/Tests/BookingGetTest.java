package com.bookerApp.Tests;


import com.bookerApp.Base.BaseTest;
import com.bookerApp.Utilities.ApiTools;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingGetTest extends BaseTest {
    @Test(description = "Scenario 1 : User should be able to Get a booking data by ID successfully")
    public void getBookingById() {
        int bookingId = ApiTools.createBooking();
        System.out.println(bookingId);
        Response response = RestAssured
                .given()
                .when()
                .get("/booking/" + bookingId);

        response.then().statusCode(200);
        Assert.assertEquals(response.jsonPath().getString("firstname"), "TEST");
        Assert.assertEquals(response.jsonPath().getString("lastname"), "TESTTEST");
    }
}
