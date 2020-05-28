import javafx.scene.media.MediaPlayer;

public class MusicThread extends Thread {
    public MusicThread() {
    }

    @Override
    public void run() {
        MediaPlayer mediaPlayer = Main.music();
        while (true) {
            mediaPlayer.play();
        }
    }
}
