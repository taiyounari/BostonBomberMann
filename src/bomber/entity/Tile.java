package bomber.entity;

public class Tile extends Entity {
    private Tile floorTile;

    public Tile() {


    }

    public Tile(String path) {
        super(path);
    }

    public Tile(Tile other, boolean active) {
        this.x = other.x;
        this.y = other.y;
        this.canBePassed = other.canBePassed;
        this.destructible = other.destructible;
        this.health = other.health;
        this.northTexture = other.northTexture;
        this.floorTile = other.floorTile;


        this.active = active;
    }

    public Tile(Tile other) {
        this.x = other.x;
        this.y = other.y;
        this.canBePassed = other.canBePassed;
        this.destructible = other.destructible;
        this.health = other.health;
        this.northTexture = other.northTexture;
        this.floorTile = other.floorTile;
    }

    public Tile getFloorTile() {
        return floorTile;
    }

    public void setFloorTile(Tile floorTile) {
        this.floorTile = floorTile;
    }

    @Override
    public void update() {

    }
}