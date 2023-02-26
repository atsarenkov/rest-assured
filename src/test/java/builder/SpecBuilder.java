package builder;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.*;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.*;

public class SpecBuilder {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder().
                setBaseUri("https://api.trello.com").
                setBasePath("/1").
                addQueryParam("key", System.getProperty("KEY")).
                addQueryParam("token", System.getProperty("TOKEN")).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }
}
