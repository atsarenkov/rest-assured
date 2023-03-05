import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static builder.SpecBuilder.*;
import io.restassured.response.*;
import org.testng.annotations.*;

public class TrelloBoardsTest {
    Response response;
    private String boardId;

    @Test(description = "Create A Board", priority = 1)
    public void createBoard() {
        Board board = Board.builder().
                permissionLevel("public").
                background("green").
                build();
        response = given().
                spec(requestSpec()).
                queryParam("name", "New Board").
                body(board).
        when().
                post("/boards").
        then().
                spec(responseSpec()).
                assertThat().
                body("prefs.permissionLevel", equalTo(board.getPermissionLevel()),
                     "prefs.background", equalTo(board.getBackground())).
                extract().response();
        boardId = response.path("id");
    }

    @Test(description = "Get A Board", priority = 2, dependsOnMethods = { "createBoard" })
    public void getBoard() {
        given().
                spec(requestSpec()).
        when().
                get("/boards/" + boardId).
        then().
                assertThat().
                spec(responseSpec()).
                body("name", equalTo("New Board"));
    }

    @Test(description = "Update A Board", priority = 3, dependsOnMethods = { "createBoard" })
    public void updateBoard() {
        given().
                spec(requestSpec()).
                queryParam("desc", "Agile Board").
        when().
                put("/boards/" + boardId).
        then().
                assertThat().
                spec(responseSpec()).
                body("desc", equalTo("Agile Board"));
    }

    @Test(description = "Delete A Board", priority = 4, dependsOnMethods = { "createBoard" })
    public void deleteBoard() {
        given().
                spec(requestSpec()).
        when().
                delete("/boards/" + boardId).
        then().
                assertThat().
                spec(responseSpec());
    }
}
