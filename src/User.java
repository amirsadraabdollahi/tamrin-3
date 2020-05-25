import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

    public static ArrayList<User> users = new ArrayList<>();
    private String userName;

    public User(String userName) {
        this.userName = userName;
        users.add(this);
    }
}
