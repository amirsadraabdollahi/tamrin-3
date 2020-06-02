import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public void changePassword(String password) {
        this.password = password;
    }

    public boolean correctPassword(String password){
        if(this.getPassword().equals(password)){
            return true;
        } else{
            return false;
        }
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
    public double getAveragePoint(){
        int gamePlayed = points.size();
        return ((double)getMaxPoint())/gamePlayed;
    }

    public static User getLastUser() {
        return users.get(users.size() - 1);
    }

    public static String newUserPass(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return "invalid userName";
            }
        }
        return "correct";
    }

    public static String correctUserPass(String userName, String password) {
        for (User user : users) {
            System.out.println(user.getUserName());
            if (user.getUserName().equals(userName)) {
                if (user.getPassword().equals(password)) {
                    return "correct";
                } else {
                    return "invalid password";
                }
            }
        }
        return "invalid userName";
    }

    public static void putOnlineUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                onlineUser = user;
                return;
            }
        }
    }

    public static ArrayList<User> sortedUsers(){
        ArrayList<User> sortedUsers = new ArrayList<>();
        for (User user : users) {
            if(user.points.size()!=0){
                sortedUsers.add(user);
            }
        }
        Collections.sort(sortedUsers, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if(o1.getMaxPoint() > o2.getMaxPoint()){
                    return -1;
                } else if(o1.getAveragePoint() < o2.getAveragePoint()){
                    return 1;
                } else{
                    return 0;
                }
            }
        });
        return sortedUsers;
    }
}
