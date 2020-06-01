
import java.lang.Math;

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Random;

public class Game {


    private static final double W = 600, H = 600;

//    private static final String SPACESHIP_IMAGE =
//            "http://icons.iconarchive.com/icons/everaldo/crystal-clear/128/App-launch-spaceship-icon.png";

    private static final String SPACESHIP_IMAGE = "img/spaceShipImage.png";
    User user;
    private Random random;
    private Image spaceShipImage;
    private Node spaceShip;
    private ArrayList<Node> bullets;
    private ArrayList<Node> enemyBullets;
    private ArrayList<Node> enemies;
    private ArrayList<Node> enemies1;
    private ArrayList<Node> enemies2;
    private ArrayList<Node> enemies3;
    private ArrayList<Node> enemies4;
    private Group group;
    private Bullet bullet;
    private Enemy enemy;
    private boolean finish = false, running, goRight, goLeft, shooting;
    private boolean keepShooting = true, isRight = true;
    private int countDown = 0;
    private int points = 0;
    private Label pointsLabel;
    private MyThread myThread;
    private GameOver gameOver;

    public Game(Group group, User user) {
        this.group = group;
        this.user = user;
    }

    public void createGame() {

        gameOver = new GameOver();
        random = new Random();
        spaceShipImage = new Image(SPACESHIP_IMAGE, true);
        myThread = new MyThread();
        ImageView imageView = new ImageView(spaceShipImage);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        spaceShip = imageView;
        StackPane.setAlignment(spaceShip, Pos.CENTER);
        bullet = Bullet.getInstance();
        enemy = Enemy.getInstance();
        bullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemies1 = new ArrayList<>();
        enemies2 = new ArrayList<>();
        enemies3 = new ArrayList<>();
        enemies4 = new ArrayList<>();
        spaceShip.relocate(W / 2, 500);
        group.getChildren().add(spaceShip);
        group.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:
                        goLeft = true;
                        break;
                    case RIGHT:
                        goRight = true;
                        break;
                    case SHIFT:
                        running = true;
                        break;
                    case SPACE:
                        if (!keepShooting) {
                            shooting = true;
                        }
                        break;
                }
            }
        });

        group.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:
                        goLeft = false;
                        break;
                    case RIGHT:
                        goRight = false;
                        break;
                    case SHIFT:
                        running = false;
                        break;
                    case SPACE:
                        keepShooting = false;
                        shooting = false;
                        break;
                }
            }
        });
        writePoint();
        addEnemyToScene();
        myThread.start();
        timer.start();
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {

            int dx = 0;

            if (finish) {
                timer.stop();
            }

            if (goRight) {
                dx += 1;
            }
            if (goLeft) {
                dx -= 1;
            }
            if (running) {
                dx *= 3;
            }
            if (shooting) {
                keepShooting = true;
                shootByThread();
                shooting = false;
            }
//                moveEnemiesByThread();
            moveEnemies();
            moveSpaceShipBy(dx);
            enemyShootByThread();
            moveEnemyBallByThread();
            moveBallByThread();

        }
    };

    private void writePoint() {
        pointsLabel = new Label("points : " + points);
        pointsLabel.getStyleClass().add("outline");
        pointsLabel.setFont(new Font("Arial", 20));
        pointsLabel.setTextFill(Color.GREEN);
        pointsLabel.setVisible(true);
        group.getChildren().add(pointsLabel);
    }

    private void addEnemyToScene() {
        for (int j = 1; j <= 4; j++) {
            for (int i = 0; i < 10; i++) {
                Node enemyNode;
                enemyNode = enemy.createImage(j);
                if (j == 1) {
                    enemies1.add(enemyNode);
                } else if (j == 2) {
                    enemies2.add(enemyNode);
                } else if (j == 3) {
                    enemies3.add(enemyNode);
                } else if (j == 4) {
                    enemies4.add(enemyNode);
                }
                enemies.add(enemyNode);
                enemyNode.relocate((double) (100 + i * 40), (double) (60 + (j - 1) * 50));
                StackPane.setAlignment(enemyNode, Pos.CENTER_RIGHT);
                group.getChildren().add(enemyNode);
            }
        }
    }

    public void enemyShootByThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                enemiesShoot();
            }
        };
        Thread thread7 = new Thread(runnable);
        thread7.start();
    }

    private void enemiesShoot() {
        if (myThread.getCountSecond() % 250 < 5) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Node enemyBall;
                    Node enemyNode;
                    enemyNode = enemies.get(random.nextInt(enemies.size() - 1));
                    enemyBall = bullet.createImage(2);
                    enemyBullets.add(enemyBall);
                    enemyBall.relocate(enemyNode.getBoundsInLocal().getWidth() / 2 - 6 + enemyNode.getLayoutX(), enemyNode.getBoundsInLocal().getHeight() / 2 - 6 + enemyNode.getLayoutY());
                    group.getChildren().add(enemyBall);
                }
            });

            myThread.increaseCountSecond(5);
        }
    }

    public void moveEnemyBallByThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                moveEnemyBalls();
            }
        };
        Thread thread8 = new Thread(runnable);
        thread8.start();
    }

    public void moveEnemyBalls() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < enemyBullets.size(); i++) {
                    Node enemyBall = enemyBullets.get(i);
                    enemyBall.relocate(enemyBall.getLayoutX(), enemyBall.getLayoutY() + 5);
                    if (enemyBall.getLayoutY() > 600 || checkEnemyShoot(enemyBall)) {
                        enemyBullets.remove(enemyBall);
                        enemyBall.setVisible(false);
                        i--;
                    }
                }
            }
        });
    }

    public boolean checkEnemyShoot(Node enemyBall) {
        if ((Math.pow(spaceShip.getLayoutX() - enemyBall.getLayoutX(), 2) + Math.pow(spaceShip.getLayoutY() - enemyBall.getLayoutY(), 2)) <= 1200) {
            finish = true;
            removeNode(spaceShip);
            gameOver.gameIsOVer("GAME OVER", String.valueOf(points));
            return true;
        }
        return false;
    }

    public void moveEnemiesByThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                moveEnemies();
            }
        };
        Thread thread5 = new Thread(runnable);
        thread5.start();
    }

    public void checkWin() {
        if (enemies.isEmpty()) {
            finish = true;
            gameOver.gameIsOVer("!! CONGRATULATION !!", "40");
        }
    }

    public void moveEnemies() {

        if (myThread.getCountSecond() % 400 < 5) {
            countDown += 1;
            moveRowsDown(enemies4, enemies4.size());
            moveRowsDown(enemies3, enemies3.size());
            moveRowsDown(enemies2, enemies2.size());
            moveRowsDown(enemies1, enemies1.size());
            myThread.increaseCountSecond(5);
            if (countDown >= 6) checkDie();
            return;
        }
        if (isRight) {
            moveRowsRight(enemies4, enemies4.size());
            moveRowsRight(enemies3, enemies3.size());
            moveRowsRight(enemies2, enemies2.size());
            moveRowsRight(enemies1, enemies1.size());
            if (countDown >= 6) checkDie();
        } else {
            moveRowsLeft(enemies4, enemies4.size());
            moveRowsLeft(enemies3, enemies3.size());
            moveRowsLeft(enemies2, enemies2.size());
            moveRowsLeft(enemies1, enemies1.size());
            if (countDown >= 6) checkDie();
        }
    }

    public void moveRowsDown(ArrayList<Node> enemies, int size) {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            Node enemy = enemies.get(i);
            enemy.relocate(enemy.getLayoutX(), enemy.getLayoutY() + 40);
            if (enemy.getLayoutY() > 600) {
                finish = true;
                gameOver.gameIsOVer("GAME OVER", String.valueOf(points));
            }

        }
    }

    private void removeNode(Node enemy) {
        enemies.remove(enemy);
        if (enemies4.contains(enemy)) {
            enemies4.remove(enemy);
        } else if (enemies3.contains(enemy)) {
            enemies3.remove(enemy);
        } else if (enemies2.contains(enemy)) {
            enemies2.remove(enemy);
        } else if (enemies1.contains(enemy)) {
            enemies1.remove(enemy);
        }
    }

    public void checkDie() {
        checkRowsForDie(enemies4, enemies4.size());
        checkRowsForDie(enemies3, enemies3.size());
        checkRowsForDie(enemies2, enemies2.size());
        checkRowsForDie(enemies1, enemies1.size());
    }

    public void checkRowsForDie(ArrayList<Node> enemies, int size) {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            Node enemy = enemies.get(i);
            if ((Math.pow(enemy.getLayoutX() - spaceShip.getLayoutX(), 2) + Math.pow(enemy.getLayoutY() - spaceShip.getLayoutY(), 2)) < 1000) {
                group.setVisible(false);
                finish = true;
                gameOver.gameIsOVer("GAME OVER", String.valueOf(points));
            }
        }
    }

    public void moveRowsRight(ArrayList<Node> enemies, int size) {
        if (size == 0) {
            return;
        }
        Node enemyNodeCheck = enemies.get(size - 1);
        if (600 - enemyNodeCheck.getLayoutX() < 20) {
            isRight = false;
            return;
        }
        for (int j = size - 1; j >= 0; j--) {
            Node enemyNode = enemies.get(j);
            enemyNode.relocate((double) (enemyNode.getLayoutX() + 2), enemyNode.getLayoutY());
        }
    }

    public void moveRowsLeft(ArrayList<Node> enemies, int size) {
        if (size == 0) {
            return;
        }
        Node enemyNodeCheck = enemies.get(0);
        if (enemyNodeCheck.getLayoutX() < 20) {
            isRight = true;
            return;
        }
        for (int j = 0; j < size; j++) {
            Node enemyNode = enemies.get(j);
            enemyNode.relocate((double) (enemyNode.getLayoutX() - 2), enemyNode.getLayoutY());
        }
    }

    public void shootByThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                shoot();
            }
        };
        Thread thread4 = new Thread(runnable);
        thread4.start();
    }

    public void shoot() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Node ball;
                ball = bullet.createImage(1);
                bullets.add(ball);
                ball.relocate(spaceShip.getBoundsInLocal().getWidth() / 2 - 6 + spaceShip.getLayoutX(), 495);
                group.getChildren().add(ball);
            }
        });
    }

    public void moveBallByThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                moveBalls();
            }
        };
        Thread thread3 = new Thread(runnable);
        thread3.start();

    }

    public void moveBalls() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < bullets.size(); i++) {
                    Node ball = bullets.get(i);
                    ball.relocate(ball.getLayoutX(), ball.getLayoutY() - 5);
                    if (ball.getLayoutY() < 0 || checkShoot(ball)) {
                        bullets.remove(ball);
                        ball.setVisible(false);
                        i--;
                    }
                }
            }
        });

    }

    public boolean checkShoot(Node ball) {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Node enemy = enemies.get(i);
            if ((Math.pow(enemy.getLayoutX() - ball.getLayoutX(), 2) + Math.pow(enemy.getLayoutY() - ball.getLayoutY(), 2)) <= 1000) {
                removeNode(enemy);
                enemy.setVisible(false);
                points += 1;
                updatePoints();
                checkWin();
                return true;
            }
        }

        return false;
    }

    private void updatePoints() {
        pointsLabel.setText("points : " + points);
    }

    public void moveSpaceShipBy(int dx) {
        if (dx == 0) return;

        final double cx = spaceShip.getBoundsInLocal().getWidth() / 2;

        double x = cx + spaceShip.getLayoutX() + dx;
        moveSpaceShipTo(x);
    }

    public void moveSpaceShipTo(double x) {
        final double cx = spaceShip.getBoundsInLocal().getWidth() / 2;

        if (x - cx >= 0 && x + cx <= W
        ) {
            spaceShip.relocate(x - cx, 500);
        }
    }

}