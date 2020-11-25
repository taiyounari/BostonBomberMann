package bomber.entity;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class Player extends Pawn {

    List<Enemy> enemyList;
    Scene scene;
    ArrayList<String> input = new ArrayList<String>();

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void checkCollision() {
        for (Enemy enemy :
                enemyList) {
            if (isCollidedWith(enemy)) {

            }
        }
    }

    public Player() {
        super(System.getProperty("user.dir") + "\\src\\texture\\" + "player" + "North.png",
                System.getProperty("user.dir") + "\\src\\texture\\" + "player" + "East.png",
                System.getProperty("user.dir") + "\\src\\texture\\" + "player" + "South.png");
        this.label = "player";
    }

    public void getInput() {
        scene.setOnKeyPressed(keyEvent -> {
            String code = keyEvent.getCode().toString();
            System.out.println("Input detected: " + code);

            if (!input.contains(code))
                input.add(code);
        });
        scene.setOnKeyReleased(keyEvent -> {
            String code = keyEvent.getCode().toString();
            System.out.println("Input released: " + code);
            input.remove(code);
        });
    }

    public void handleInput() {
        if (!isMoving()) {
            if (input.contains("LEFT")||input.contains("A")) {
                toX--;
                //input.remove("LEFT");
            } else


            if (input.contains("RIGHT")||input.contains("D")) {
                toX++;
                //input.remove("RIGHT");

            } else


            if (input.contains("UP")||input.contains("W")) {
                toY--;
                //input.remove("UP");

            } else


            if (input.contains("DOWN")||input.contains("S")) {
                toY++;
                //input.remove("DOWN");

            } else {

            }
        }

    }

    @Override
    public void update() {
        getInput();
        handleInput();
        move();
    }
}