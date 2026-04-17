package com.bookerApp.Tests;

import com.bookerApp.Base.BaseTest;
import com.bookerApp.Utilities.ApiTools;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingDeleteTest extends BaseTest {

    @Test(priority = 1, description = "Scnerio 2: User should be able to delete booking with valid booking id number")
    public void deleteBooking() {
        int bookingId = ApiTools.createBooking();
        System.out.println(bookingId);

        RestAssured
                .given()
                .cookie("token", token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201);

        Response getResponse = RestAssured
                .given()
                .when()
                .get("/booking/" + bookingId);

        Assert.assertEquals(getResponse.getStatusCode(), 404);

    }


}
