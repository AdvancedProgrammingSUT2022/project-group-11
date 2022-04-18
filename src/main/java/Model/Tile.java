package Model;
public class Tile {

    private int x;
    private int y;
    private String Type;
   
    private Color color;

    Tile(int x, int y, String Type, Color color) {
        this.x = x;
        this.y = y;
        this.Type = Type;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public String getType() {
        return this.Type;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}