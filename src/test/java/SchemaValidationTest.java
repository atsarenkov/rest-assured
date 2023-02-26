import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static builder.SpecBuilder.*;
import org.testng.annotations.*;

public class SchemaValidationTest {

    @Test(description = "Validate JSON Schema", groups = { "contract" })
    public void getWorkspace() {
        given().
                spec(requestSpec()).
        when().
                get("/organizations/" + System.getProperty("ORGANIZATION_ID")).
        then().
                spec(responseSpec()).
                assertThat().
                body(matchesJsonSchemaInClasspath("organization.json"));
    }
}