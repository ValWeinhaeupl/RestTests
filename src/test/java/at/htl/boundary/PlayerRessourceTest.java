package at.htl.boundary;

import at.htl.control.PlayerRepository;
import at.htl.entity.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.Table;
import java.util.List;

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
    public void test_that_first_entry_is_valentin(){
        Response response = RestAssured.get("http://localhost:8080/api/player/1");

        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        assertEquals(jsonPath.getString("playerId"), "1");
        assertEquals(jsonPath.getString("vname"), "valentin");
        assertEquals(jsonPath.getString("nname"), "weinh");
        assertEquals(jsonPath.getString("verein"), "utc raab");
    }

    @Test
    public void test_that_(){
        List<Player> response = given().when().get("http://localhost:8080/api/player")
                .then().statusCode(200).extract().jsonPath().getList(".",Player.class);

            assertEquals(response.get(0).getPlayerId(), 1);
            assertEquals(response.get(0).getVname(), "valentin");
            assertEquals(response.get(0).getNname(), "weinh");
            assertEquals(response.get(0).getVerein(), "utc raab");

    }

    //write a test that checks if you can post to the player resource
    @Test
    public void test_that_player_upload_works(){
        Player player = new Player("test", "test", "test");
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(player);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Response response = given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("http://localhost:8080/api/player")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        Player player1 = new JsonPath(response.getBody().asString()).getObject("$", Player.class);

        assertEquals(player1.getNname(), jsonPath.getString("nname"));
        assertEquals(player1.getVname(), jsonPath.getString("vname"));
        assertEquals(player1.getVerein(), jsonPath.getString("verein"));
    }
}