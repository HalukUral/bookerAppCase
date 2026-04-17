package com.bookerApp.Tests;

import com.bookerApp.Base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PingTest extends BaseTest {
    @Test(description = "User should be able to perform a health check")
    public void HealthCheckEndpoint(){
        Response response= RestAssured
                .given()
                .when()
                .get("/ping")
                .then()
                .extract()
                .response();
        Assert.assertEquals(response.getStatusCode(),201);
        System.out.println("Ping Response: "+ response.getBody().asString() );
    }
}
