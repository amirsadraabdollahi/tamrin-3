import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

    public static ArrayList<User> users = new ArrayList<>();
    private String userName;
    private ArrayList<Integer> points;

    public User(String userName) {
        points = new ArrayList<>();
        this.userName = userName;
        users.add(this);
    }


    public String getUserName() {
        return userName;
    }

    public void addPoint(int point){
        this.points.add(point);
    }

    public int getMaxPoint(){
        int maxPoint = 0;
        for (Integer point : points) {
            if(point>maxPoint){
                maxPoint = point;
            }
        }
        return maxPoint;
    }

    public static boolean hasUserWithThisUserName(String userName){
        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }
}
