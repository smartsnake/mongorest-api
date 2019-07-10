package com.david.mongorest.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "Login")
public class User {

    private Set<String> roles;

    @Id
    private String id;
    public User setid(String id) {
        this.id = id;
        return this;
    }
    public String getid() {
        return this.id;
    }

    private String username;
    public User setUsername(String username) {
        this.username = username;
        return this;
    }
    public String getUsername() {
        return this.username;
    }

    private String password;
    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    public String getPassword() {
        return this.password;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getRoles(){
        return this.roles;
    }

    public User(String id, String username, String password, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User() {
        this.id = new ObjectId().toString();
        this.username = "default";
        this.password = "{noop}password";
        this.roles = new HashSet<>();
        this.roles.add("USER");

    }

    public User(User o) {
        this.id = o.getid();
        this.username = o.getUsername();
        this.password = o.getPassword();
        this.roles = o.getRoles();
    }

    public User loadFromJSON(JSONObject json) throws JSONException {
        this.id = json.optString("id");
        this.username = json.optString("username");
        this.password = json.optString("password");
        this.roles = new HashSet<>();
        this.roles.add(json.optString("roles"));
        return this;
    }

    public JSONObject createJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", this.id);
            json.put("username", this.username);
            json.put("password", this.password);
            json.put("roles", this.roles);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User u = (User) o;
        if (this.id.equals(u.getid())) {
            if (this.username.equals(u.getUsername())) {
                if (this.password.equals(u.getPassword())) {
                    if(this.roles.equals(u.roles)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
