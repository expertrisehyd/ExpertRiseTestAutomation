package com.expertrise.automation.apiTraining;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;


public class RequestSpecificationExample {
    /*RequestSpecification requestSpecification = io.restassured.RestAssured.given()
            .baseUri()
            .basePath("/users")
            .header("Content-Type", "application/json")
            .header("Accept", "application/json");
        */
/*    RequestParameter examples:
    Request URI:	https://jsonplaceholder.typicode.com/users/1
    Proxy:			<none>
    Request params:	<none>
    Query params:	<none>
    Form params:	<none>
    Path params:	<none>
    Headers:		Accept=application/json
    Content-Type=application/json
    Cookies:		<none>
    Multiparts:		<none>
    Body:			<none>*/
    @Test
    public void getRequestDetails() {

        RequestSpecification reqspec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setBasePath("/users/1")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        RestAssured.given()
                .spec(reqspec)
                .log().all()
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200);

    }

}
