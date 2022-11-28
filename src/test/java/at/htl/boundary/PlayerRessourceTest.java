package at.htl.boundary;

import at.htl.control.PlayerRepository;
import at.htl.entity.Player;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.Table;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PlayerRessourceTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api";
    }

    @Inject
    Logger logger;

    @Inject
    PlayerRepository playerRepository;

    @Test
    public void get_returns_200_status_code(){
        Response response = RestAssured.get("http://localhost:8080/api/player/1");

        assertEquals(response.statusCode(), 200);
        //assertEquals(response.body(), );
    }

    @Test
    public void test_that_post_works(){
        Player player = new Player("test", "user", "utc raag");

        Response response = given()
                .header("Content-type", "object")
                .and()
                .body(player)
                .when()
                .post("/player")
                .then()
                .extract()
                .response();

        assertEquals(response.statusCode(), 200);
    }
}