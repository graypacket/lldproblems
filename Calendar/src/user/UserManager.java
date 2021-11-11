package user;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserManager {
    private Map<String, User> users = new HashMap<>();

    public User getUser(String username) {
        return users.get(username);
    }

    public User createUser(String firstName, String lastName, String username) {
        if(users.containsKey(username)) return null;
        User user = new User(firstName, lastName, username);
        users.put(username, user);
        return user;
    }

    public List<User> getUsers() {
        return new ArrayList<User>(users.values());
    }
}
