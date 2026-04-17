package com.bookerApp.Base;

import com.bookerApp.Pojos.AuthPojo;
import com.bookerApp.Utilities.ApiTools;
import com.bookerApp.Utilities.JsonUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.util.Properties;

public class BaseTest {
    Properties properties;
    public String token;
    @BeforeClass
    public void setup(){
        properties= ApiTools.loadProperties("config.properties");
        RestAssured.baseURI=properties.getProperty("base.url");
        token=createToken();
    }
    public String createToken(){
        AuthPojo authPojo = new AuthPojo(
                properties.getProperty("username"),
                properties.getProperty("password")
        );
        Response response= RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(JsonUtil.toJson(authPojo))
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().response();
        return response.jsonPath().getString("token");


    }
}
