
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {
    private static final String ENEMY_IMAGE1 = "img/enemy1.png";
    private static final String ENEMY_IMAGE2 = "img/enemy2.png";
    private static final String ENEMY_IMAGE3 = "img/enemy3.png";
    private static final String ENEMY_IMAGE4 = "img/enemy4.png";

    private static Enemy ourInstance = new Enemy();

    public static Enemy getInstance() {
        return ourInstance;
    }

    private Enemy() {
    }

    public ImageView createImage(int number) {
        Image enemyImage;
        if (number == 1) {
            enemyImage = new Image(ENEMY_IMAGE1);
        } else if (number == 2) {
            enemyImage = new Image(ENEMY_IMAGE2);
        } else if (number == 3) {
            enemyImage = new Image(ENEMY_IMAGE3);
        } else {
            enemyImage = new Image(ENEMY_IMAGE4);
        }
        ImageView imageView = new ImageView(enemyImage);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        return imageView;
    }

}
