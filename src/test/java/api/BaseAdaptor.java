package api;
import com.google.gson.Gson;
import utils.PropertyReader;
import static io.restassured.RestAssured.given;

public class BaseAdaptor {
        protected final static String BASE_URL = System.getenv().getOrDefault(
            "BASE_URL", PropertyReader.getProperty("test_rail.api.all.base_url"));
        private final static String ACCESS_TOKEN = System.getenv().getOrDefault("ACCESS_TOKEN",
            PropertyReader.getProperty("test_rail.api.all.access_token"));
        private final static String EMAIL = PropertyReader.getProperty("test_rail.all.email");

        protected final static Gson gson = new Gson();

        public String get(String endpoint, int statusCode) {
            return given()
                .header("Content-Type", "application/json")
                .log().all()
                .auth()
                .preemptive()
                .basic(EMAIL, ACCESS_TOKEN)
                .when()
                .get(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().body().asString();
        }

        public String post(String endpoint, int statusCode, String requestBody) {
            return given()
                .header("Content-Type", "application/json")
                .log().all()
                .auth()
                .preemptive()
                .basic(EMAIL, ACCESS_TOKEN)
                .body(requestBody)
                .when()
                .post(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().body().asString();
        }
}
