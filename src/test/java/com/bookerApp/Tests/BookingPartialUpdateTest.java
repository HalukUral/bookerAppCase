package com.bookerApp.Tests;

import com.bookerApp.Base.BaseTest;
import com.bookerApp.Utilities.ApiTools;
import com.bookerApp.Utilities.JsonUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class BookingPartialUpdateTest extends BaseTest {
    @Test(description = "User should able to partial update on booking information")
    public void patchBooking() {
        int bookingId = ApiTools.createBooking();

        Map<String, Object> patchBody = new HashMap<>();
        patchBody.put("firstname", "TestPatched");
        patchBody.put("additionalneeds", "Late Checkout");

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(JsonUtil.toJson(patchBody))
                .when()
                .patch("/booking/" + bookingId);

        response.then().statusCode(200);
        Assert.assertEquals(response.jsonPath().getString("firstname"), "TestPatched");
        Assert.assertEquals(response.jsonPath().getString("additionalneeds"), "Late Checkout");
    }
}
