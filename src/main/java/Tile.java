

public class Tile{

    private int x;
    private int y;
    private String Type;
    private String color;

    Tile(int x,int y,String Type){

        this.x = x;
        this.y = y;
        this.Type = Type;
    }

    public String getType(){
        return this.Type;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

}