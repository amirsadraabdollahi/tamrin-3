import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

    public static ArrayList<User> users = new ArrayList<>();
    public static User onlineUser;
    private String userName;
    private String password;
    private ArrayList<Integer> points;

    public User(String userName, String password) {
        points = new ArrayList<>();
        this.userName = userName;
        this.password = password;
        users.add(this);
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void addPoint(int point) {
        this.points.add(point);
    }

    public int getMaxPoint() {
        int maxPoint = 0;
        for (Integer point : points) {
            if (point > maxPoint) {
                maxPoint = point;
            }
        }
        return maxPoint;
    }

    public static boolean hasUserWithThisUserName(String userName) {
        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

    public static User getLastUser() {
        return users.get(users.size() - 1);
    }

    public static String correctUserPass(String userName, String password){
        for (User user : users) {
            if(user.getUserName().equals(userName)){
                if(user.getPassword().equals(password)){
                    return "correct";
                } else{
                    return "invalid password";
                }
            }
        }
        return "invalid userName";
    }

    public static void putOnlineUser(String userName){
        for (User user : users) {
            if(user.getUserName().equalsIgnoreCase(userName)){
                onlineUser = user;
                return;
            }
        }
    }
}
