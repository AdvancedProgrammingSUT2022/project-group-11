package Model;

public class River {
    private Tile first;
    private Tile second;
    private Color color;

    public River(Tile first, Tile second) {

        this.first = first;
        this.second = second;
        color = Color.BLUE_BACKGROUND;
    }

    public Tile getFirstTile() {
        return this.first;
    }

    public Tile getSecondTile() {
        return this.second;
    }

    public Color getColor() {
        return this.color;
    }
}