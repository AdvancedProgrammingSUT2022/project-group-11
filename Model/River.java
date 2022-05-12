package Model;

public class River {

    private final Terrain first;
    private final Terrain second;
    private final Color color;

    public River(Terrain first, Terrain second) {

        this.first = first;
        this.second = second;
        color = Color.BLUE_BACKGROUND;
    }

    public Terrain getFirstTerrain() {
        return this.first;
    }

    public Terrain getSecondTerrain() {
        return this.second;
    }

    public Color getColor() {
        return this.color;
    }
}