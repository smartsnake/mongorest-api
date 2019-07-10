import com.david.mongorest.models.User;
  import org.json.JSONException;
  import org.json.JSONObject;
  import org.junit.Before;
  import org.junit.Test;

  import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user;
    String tempID;
    JSONObject jsonUser;


    @Before
    public void createNewUserWithValidJSON() throws JSONException {
        this.user = new User();
        this.tempID = this.user.getid();
        this.jsonUser = this.user.createJSONObject();
    }

    @Test
    public void createJSONObject() {
        JSONObject jsonTest = this.user.createJSONObject();
        assertEquals(jsonUser.toString(), jsonTest.toString());
    }

    @Test
    public void loadFromJSON() {
        User testUser = user.loadFromJSON(jsonUser);
        assertEquals(user, testUser);
    }

    @Test
    public void isEqual() {
        User b = new User(this.user);

        boolean isSame = this.user.equals(b);
        assert(isSame);

    }

    @Test
    public void isEqualFalse(){
        User b = new User();

        boolean isSame = this.user.equals(b);
        assert(!isSame);
    }
}
