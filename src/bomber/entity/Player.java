package bomber.entity;

import bomber.Game;
import bomber.entity.Enemy.Enemy;
import bomber.gameFunction.Sound;
import bomber.gameFunction.Texture;
import bomber.gameFunction.TimeCounter;

import java.util.ArrayList;
import java.util.List;

public class Player extends Pawn {
    List<Bomb> bombs;
    List<Enemy> enemyList = new ArrayList<>();
    ArrayList<String> input;
    int MaxLevel = 3;
    public void setInput(ArrayList<String> input) {
        this.input = input;
    }
    public  void healthUp() {
        health ++;
    }
    static final int numberOfBombType = 4;
    public int bombRangeBonus = 4;
    private int selectedBomb = 0;

    public int getSelectedBomb() {
        return selectedBomb;
    }

    public TimeCounter[] bombCountdownCounter;
    public double[] bombCoolDown;

    TimeCounter tc = new TimeCounter();

    public void checkCollision() {
        if (!enemyList.isEmpty()) {
            for (Enemy enemy : enemyList) {
                if (isCollidedWith(enemy)) {
                    Sound.playerDead();
                }
            }
        }
    }

    @Override
    public void setXY(double x, double y) {
        super.setXY(x, y);
        toY = y;
        toX = x;
    }

    public void setBombRangeBonus(int bombRangeBonus) {
        this.bombRangeBonus = bombRangeBonus;
    }

    public void setBombCoolDown(double bombCoolDown1) {
        for (int i = 0; i < bombCoolDown.length; i++) {
            if (bombCoolDown[i] - bombCoolDown1 >= 0)
                bombCoolDown[i] -= bombCoolDown1;
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void deactivate() {
        super.deactivate();
    }

    @Override
    public void start() {

    }

    public Player() {
        super(Game.textureFolderPath + "player" + "North.png",
                Game.textureFolderPath + "player" + "East.png",
                Game.textureFolderPath + "player" + "South.png");
        this.label = "player";
        canBePassed = true;
        this.setSpeed(2);
        //this.setHealth(3);
        bombCountdownCounter = new TimeCounter[numberOfBombType];
        for (int i = 0; i < numberOfBombType; i++) {
            bombCountdownCounter[i] = new TimeCounter();
        }
        double[] b = {1, 4, 10, 15};
        bombCoolDown = b;
    }

    public void createNormalBomb() {

    }
    public void isLive(){
        if(this.health <= 0) active = false;
    }
    boolean moved = true;

    public void handleInput() {
        if (!isMoving()) {
            if (input.contains("LEFT") || input.contains("A")) {
                toX--;

            } else if (input.contains("RIGHT") || input.contains("D")) {
                toX++;


            } else if (input.contains("UP") || input.contains("W")) {
                toY--;


            } else if (input.contains("DOWN") || input.contains("S")) {
                toY++;


            } else if (input.contains("V")) {
                System.out.println("HP : " + health + " ,BombRadius : " + bombRangeBonus + " ,speed : " + speed * (double) Texture.IMAGE_SIZE
                        + " ,BombCoolDown : " + bombCoolDown[0] + ", Des : " + (destructible ? "true" : "false"));
            } else {

            }
            if (input.contains("SPACE")) {
                //System.out.println("Speed : " + this.speed*(double) Texture.IMAGE_SIZE);
                if (checkIfTileEmpty(x, y))
                    switch (selectedBomb) {
                        case 0 -> placeBomb();
                        case 1 -> placeDynamite();
                        case 2 -> placeGasolineBarrel();
                        case 3 -> placeMine();
                        case 4 -> placeGasCanister();
                    }

            }
        } else moved = true;

        for (int i = 0; i < numberOfBombType; i++) {
            if (input.contains("DIGIT" + (i + 1))) {
                selectedBomb = i;
            }
        }

    }



    TimeCounter invulCounter = new TimeCounter();
    public double invulDuration = 1;

    @Override
    public void onTakeDamage() {
        invulCounter.resetCounter();
    }

    public boolean isThisBombReady(int index) {
        boolean b = bombCountdownCounter[index].getTime() > bombCoolDown[index];
        if (b) {
            bombCountdownCounter[index].resetCounter();
        }
        return b;
    }

    public void placeBomb() {
        if (isThisBombReady(0)) {
            mapRef.getGame().addEntity(new Bomb(
                    x, y, 1 + bombRangeBonus,
                    1, mapRef, 1, 1,
                    "explosionBomb", "placeGentle"));
            moved = false;

        }

    }

    public void placeDynamite() {
        if (isThisBombReady(1)) {
            mapRef.getGame().addEntity(new Bomb(
                    x, y, 2 + 2 * bombRangeBonus,
                    4, mapRef, 2, 2 + bombRangeBonus,
                    "explosionBig", "placeGentle"));
            moved = false;

        }
    }

    public void placeGasolineBarrel() {
        if (isThisBombReady(2)) {
            mapRef.getGame().addEntity(new GasolineBarrel(x, y, 5 + bombRangeBonus,
                    mapRef, "explosionFlame", "placeGentle"));
            moved = false;

        }
    }

    public void placeMine() {
        if (isThisBombReady(3)) {
            mapRef.getGame().addEntity(new ProximityMine(x, y, 1, mapRef,
                    "explosionBomb", "beepSmall"));
            moved = false;

        }
    }

    public void placeGasCanister() {

    }


    @Override
    public void update() {


        isLive();

        if (invulCounter.getTime() < invulDuration) {
            tempSpeedBoost = 1;
            destructible = false;
        }
        else {
            tempSpeedBoost = 0;
            destructible = true;
        }
        updateMapInfo();
        handleInput();
        move();
    }
}
