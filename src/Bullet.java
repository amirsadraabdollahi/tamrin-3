
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Bullet {

    private static final String BULLET_IMAGE = "http://icons.iconarchive.com/icons/hopstarter/soft-scraps/32/Button-Blank-Red-icon.png";

    private static Bullet ourInstance = new Bullet();

    public static Bullet getInstance() {
        return ourInstance;
    }

    private Bullet() {
    }

    public ImageView createImage() {
        Image ballImage = new Image(BULLET_IMAGE);
        ImageView imageView = new ImageView(ballImage);
        imageView.setFitHeight(10);
        imageView.setFitWidth(10);
        return imageView;
    }

}
