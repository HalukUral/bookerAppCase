package com.bookerApp.Tests;

import com.bookerApp.Base.BaseTest;
import com.bookerApp.Pojos.CreateBookingPojo;
import com.bookerApp.Utilities.JsonUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingCreateTest extends BaseTest {
    @Test(priority = 1, description = "Scnerio 1: User should able to create a new booking with valid information successfully")
    public void createBooking(){
        CreateBookingPojo requestBody= JsonUtil.fromResource("testdata.json", CreateBookingPojo.class);
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(JsonUtil.toJson(requestBody))
                .when()
                .post("/booking");
        response.then().statusCode(200);
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"),requestBody.getFirstname());
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"), requestBody.getLastname());
    }
}
