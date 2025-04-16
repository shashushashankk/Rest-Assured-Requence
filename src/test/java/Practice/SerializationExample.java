package Practice;

import org.json.JSONObject;

import com.google.gson.Gson;

class User {
    private String name;
    private int age;

    // Constructor
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public User() {

    }

    // Getters and Setters (Optional for JSON parsing)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class SerializationExample {
    public static void main(String[] args) {
        // Create a User object
//        User user = new User("Alice", 25);
        User user = new User();

        // Create a Gson instance
        Gson gson = new Gson();

        // Serialize: Convert User object to JSON
        String json = gson.toJson(user);
        JSONObject jsonObject = new JSONObject(json);
        jsonObject.put("name", "Shashank");
        json=jsonObject.toString();
        System.out.println("Serialized JSON: " + json);

        // Deserialize: Convert JSON back to User object
        User deserializedUser = gson.fromJson(json, User.class);
        System.out.println("Deserialized User: Name = " + deserializedUser.getName() + ", Age = " + deserializedUser.getAge());
    }
}