package com.bookerApp.Utilities;

import com.bookerApp.Pojos.BookingDatesPojo;
import com.bookerApp.Pojos.CreateBookingPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.InputStream;
import java.util.Properties;

public class ApiTools {
    public static Properties loadProperties(String fileName){
        try (InputStream inputStream = ApiTools.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RuntimeException("Properties file not found: " + fileName);
            }
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties file: " + fileName, e);
        }
    }
    public static int createBooking() {
        CreateBookingPojo requestBody = new CreateBookingPojo(
                "TEST",
                "TESTTEST",
                123,
                true,
                new BookingDatesPojo("2026-04-16", "2026-04-20"),
                "Yemek.."
        );

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(JsonUtil.toJson(requestBody))
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.jsonPath().getInt("bookingid");
    }
}
