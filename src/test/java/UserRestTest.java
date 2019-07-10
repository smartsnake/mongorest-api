import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.david.mongorest.models.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserRestTest {
    static User user;
    static String tempID;
    static JSONObject jsonUser;

    //before each test create a user and record its id.  serves as a create test
    @Before
    public void createNewUserWithValidJSON() throws JSONException{
        UserRestTest.user = new User();
        UserRestTest.tempID = UserRestTest.user.getid();
        UserRestTest.jsonUser = UserRestTest.user.createJSONObject();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.body(jsonUser.toString());
        request.post("user/");
    }

    //afterwards delete the entry to not alter the database serves as a delete test
    @After
    public void deleteUserWithValidID() {
        RequestSpecification request = RestAssured.given();
        request.delete("/user/" + UserRestTest.tempID);
    }

    @Test
    public void requestUserWithValidID() {
        get("/user/" + UserRestTest.tempID)
          .then().statusCode(200).assertThat()
          .body("id", equalTo(UserRestTest.tempID));
    }
    @Test
    public void requestUserWithBadID() {
        get("/user/thisWillNeverBeAValidID")
          .then().statusCode(404);
    }
}
