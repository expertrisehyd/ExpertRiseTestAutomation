package com.expertrise.automation.apiTraining;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.junit.Test;

public class ResponseSpecificationExample {

    @Test
    public void getRequestDetails(){


  /*      HTTP/1.1 200 OK

        Content-Type: application/json; charset=utf-8
        Transfer-Encoding: chunked
        Connection: keep-alive
        x-content-type-options: nosniff
        x-powered-by: Express
        x-ratelimit-limit: 1000
        x-ratelimit-remaining: 999
        x-ratelimit-reset: 1778755441
        cf-cache-status: REVALIDATED
        Content-Encoding: gzip
        CF-RAY: 9fbc0daa5fe12b45-HYD
        alt-svc: h3=":443"; ma=86400

        {
            "id": 1,
                "name": "Leanne Graham",
                "username": "Bret",
                "email": "Sincere@april.biz",
                "address": {
            "street": "Kulas Light",
                    "suite": "Apt. 556",
                    "city": "Gwenborough",
                    "zipcode": "92998-3874",
                    "geo": {
                "lat": "-37.3159",
                        "lng": "81.1496"
            }
        },
            "phone": "1-770-736-8031 x56442",
                "website": "hildegard.org",
                "company": {
            "name": "Romaguera-Crona",
                    "catchPhrase": "Multi-layered client-server neural-net",
                    "bs": "harness real-time e-markets"
        }
        }*/

        ResponseSpecification responsespec =new ResponseSpecBuilder()
                .expectHeader("Content-Type", "application/json; charset=utf-8")
                .expectHeader("Server", "cloudflare")
                .expectStatusCode(200)
                .build();

                RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/users/1")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .log().all()
                .when()
                .get()
                .then()
                   .spec(responsespec).log().all()
                        .extract().response();


    }
}
