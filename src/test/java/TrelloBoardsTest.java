import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static builder.SpecBuilder.*;
import io.restassured.response.*;
import org.testng.annotations.*;

public class TrelloBoardsTest {
    Response response;
    Board board = new Board();
    private String boardId;

    @Test(description = "Create A Board", groups = { "functional" }, priority = 1)
    public void createBoard() {
        board.setPermissionLevel("public");
        board.setBackground("green");
        response = given().
                spec(requestSpec()).
                queryParam("name", "New Board").
                body(board).
        when().
                post("/boards").
        then().
                spec(responseSpec()).
                assertThat().
                body("prefs.permissionLevel", equalTo(board.getPermissionLevel())).
                body("prefs.background", equalTo(board.getBackground())).
                extract().response();
        boardId = response.path("id");
    }

    @Test(description = "Get A Board", groups = { "functional" }, priority = 2, dependsOnMethods = { "createBoard" })
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

    @Test(description = "Update A Board", groups = { "functional" }, priority = 3, dependsOnMethods = { "createBoard" })
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

    @Test(description = "Delete A Board", groups = { "functional" }, priority = 4, dependsOnMethods = { "createBoard" })
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
