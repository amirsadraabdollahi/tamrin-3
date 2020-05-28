
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Bullet {

    private static final String SPACE_SHIP_BULLET_IMAGE = "http://icons.iconarchive.com/icons/hopstarter/soft-scraps/32/Button-Blank-Red-icon.png";
    private static final String ENEMIES_BULLET_IMAGE = "http://icons.iconarchive.com/icons/dapino/magic-people/48/Magic-ball-icon.png";
    private static Bullet ourInstance = new Bullet();

    public static Bullet getInstance() {
        return ourInstance;
    }

    private Bullet() {
    }
    public ImageView createImage(int i){
        if(i == 1){
            return createSpaceShipBulletImage();
        }else if(i == 2){
            return createEnemiesBulletImage();
        }
        return null;
    }

    public ImageView createSpaceShipBulletImage() {
        Image ballImage = new Image(SPACE_SHIP_BULLET_IMAGE);
        ImageView imageView = new ImageView(ballImage);
        imageView.setFitHeight(10);
        imageView.setFitWidth(10);
        return imageView;
    }

    public ImageView createEnemiesBulletImage(){
        Image ballImage = new Image(ENEMIES_BULLET_IMAGE);
        ImageView imageView = new ImageView(ballImage);
        imageView.setFitHeight(13);
        imageView.setFitWidth(13);
        return imageView;
    }

}
