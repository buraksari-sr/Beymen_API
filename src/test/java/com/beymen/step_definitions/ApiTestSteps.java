package com.beymen.step_definitions;
import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;



import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.baseURI;
public class ApiTestSteps {

    String [] cardId = new String[2];
    String boardId = null;

    @Given("create a board")
    public void create_a_board() {
        Response response = given()
                .queryParam("name","API/board1")
                .queryParam("key", "bfc58d5cfdd4c366bbd75a0861e4dcb2")
                .queryParam("token", "f73d1e2816720190ed3c83dfa5f3957f0a920ae629412eb167b068bfa33189cc")
                .when()
                .log().all()
                .contentType("application/json; charset=utf-8")
                .post(baseURI+"/boards/");
        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(200,response.statusCode());
        boardId=response.jsonPath().get("id");
        System.out.println("boardId = " + boardId);

    }

    @When("add two card into the board")
    public void add_two_card_into_the_board() {

        for (int i = 0; i < 1; i++) {
            Response response = given()
                    .accept("application/json")
                    .queryParam("idlist","630ec2f39328c800aed17864")
                    .queryParam("key", "bfc58d5cfdd4c366bbd75a0861e4dcb2")
                    .queryParam("token", "f73d1e2816720190ed3c83dfa5f3957f0a920ae629412eb167b068bfa33189cc")
                    .when()
                    .log().all()
                    .contentType("application/json; charset=utf-8")
                    .post(baseURI+"/cards");
            response.prettyPrint();
            System.out.println("response.statusCode() = " + response.statusCode());
            assertEquals(200,response.statusCode());
            cardId[i]=response.jsonPath().get("id");
        }
      }
    @When("update one card randomly")
    public void update_one_card_randomly() {
        int min = 0;
        int max = 1;
        int a = (int) (Math.random()*(max-min+1)+min);

        Response response = given()
                .pathParam("id",cardId[a])
                .queryParam("key", "bfc58d5cfdd4c366bbd75a0861e4dcb2")
                .queryParam("token", "f73d1e2816720190ed3c83dfa5f3957f0a920ae629412eb167b068bfa33189cc")
                .when()
                .log().all()
                .contentType("application/json; charset=utf-8")
                .put(baseURI+"/cards/{id}");
        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(200,response.statusCode());
    }
    @When("delete all cards")
    public void delete_all_cards() {
        for (int i = 0; i < 1; i++) {
             Response response = given()
                    .pathParam("id", cardId[i])
                     .queryParam("key", "bfc58d5cfdd4c366bbd75a0861e4dcb2")
                     .queryParam("token", "f73d1e2816720190ed3c83dfa5f3957f0a920ae629412eb167b068bfa33189cc")
                    .when()
                    .log().all()
                    .contentType("application/json; charset=utf-8")
                    .delete(baseURI + "/cards/{id}");
            response.prettyPrint();
            System.out.println("response.statusCode() = " + response.statusCode());
            assertEquals(200,response.statusCode());
        }
    }
    @Then("delete the boards")
    public void delete_the_boards() {
        Response response = given()
                .pathParam("id",boardId)
                .queryParam("key", "bfc58d5cfdd4c366bbd75a0861e4dcb2")
                .queryParam("token", "f73d1e2816720190ed3c83dfa5f3957f0a920ae629412eb167b068bfa33189cc")
                .when()
                .log().all()
                .contentType("application/json; charset=utf-8")
                .delete(baseURI+"/boards/{id}");
        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(200,response.statusCode());

    }


}
