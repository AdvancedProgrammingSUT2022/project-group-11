package Model;

public class River {
    private Terrain first;
    private Terrain second;
    private Color color;

    public River(Terrain first, Terrain second) {

        this.first = first;
        this.second = second;
        color = Color.BLUE_BACKGROUND;
    }

    public Terrain getFirstTile() {
        return this.first;
    }

    public Terrain getSecondTile() {
        return this.second;
    }

    public Color getColor() {
        return this.color;
    }
}