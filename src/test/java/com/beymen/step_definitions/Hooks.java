package com.beymen.step_definitions;
import static io.restassured.RestAssured.baseURI;
import com.beymen.utilities.ConfigurationReader;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@Wip")
    public void before() {
        baseURI= ConfigurationReader.get("apiUrl");

    }

}
