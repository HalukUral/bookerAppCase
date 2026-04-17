package com.bookerApp.Tests;

import com.bookerApp.Base.BaseTest;
import com.bookerApp.Pojos.BookingDatesPojo;
import com.bookerApp.Pojos.CreateBookingPojo;
import com.bookerApp.Utilities.ApiTools;
import com.bookerApp.Utilities.JsonUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingUpdateTest extends BaseTest {
    @Test(description = "User should be able to update booking information")
    public void bookingUpdate(){
        int bookingId = ApiTools.createBooking();

        CreateBookingPojo requestBody = new CreateBookingPojo(
                "TestUpdated",
                "UpdatedTest",
                750,
                false,
                new BookingDatesPojo("2026-05-10", "2026-05-15"),
                "Dinner"
        );

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(JsonUtil.toJson(requestBody))
                .when()
                .put("/booking/" + bookingId);

        System.out.println(response.jsonPath().getString("totalprice"));

        response.then().statusCode(200);
        Assert.assertEquals(response.jsonPath().getString("firstname"), "TestUpdated");
        Assert.assertEquals(response.jsonPath().getString("lastname"), "UpdatedTest");
    }

}
