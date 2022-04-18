package Model;
import java.util.Random;

public class Map {
    private int Iteration = 8;
    private int size = 5;

    Tile[][] Tiles = new Tile[10][8];

    public void AssignTileWithRandom() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                Random rand = new Random();
                int Condition = rand.nextInt(9);
                if ((i < 3 || i > 8) && (j < 2 || j > 6)) {

                    if(Condition < 5){
                        
                        Tile tile = new Tile(i, j, "fog of war", Color.RESET);
                        Tiles[i][j] = tile;
                    }

                }else{
                    
                }

            }
        }
    }

    public void space(int size) {
        int counter = 0;
        for (counter = 0; counter < size; counter++) {
            // Add space
            System.out.print(" ");
        }
    }

    public void print_symbol(int size) {
        int counter = 0;
        for (counter = 0; counter < size; counter++) {
            System.out.print("_");
        }
    }

    public void PrintFirstRows() {
        space(size);
        for (int j = 0; j < Iteration; j++) {
            print_symbol(size);
            space(size + 6);
        }

        System.out.print("\n");
    }

    public void PrintMapNextRows() {
        // Print top layers
        int mid = size + 3;
        int CountSpaceFinal = size + 2;
        int CountSpaceFirst = size;
        for (int i = 1; i < mid / 2; i++) {
            space(size - i);

            if (i == mid / 2 - 1) {
                for (int j = 0; j < Iteration; j++) {
                    System.out.print("/");
                    space(CountSpaceFirst);
                    System.out.print("\\");
                    print_symbol(size);
                }
                System.out.print("\n");
                CountSpaceFirst += 2;
            } else {
                for (int j = 0; j < Iteration; j++) {
                    System.out.print("/");
                    space(CountSpaceFirst);
                    System.out.print("\\");
                    space(CountSpaceFinal + 2);
                }

                CountSpaceFinal -= 2;
                CountSpaceFirst += 2;
                System.out.print("\n");
            }

        }
        CountSpaceFinal = size;
        CountSpaceFirst = size + 4;
        // Print bottom layers

        for (int i = mid / 2 - 1; i >= 1; i--) {
            space(size - i);
            // last layer
            if (i == 1) {
                for (int j = 0; j < Iteration; j++) {
                    System.out.print("\\");
                    print_symbol(size);
                    System.out.print("/");
                    space(CountSpaceFinal);
                }

                CountSpaceFinal += 2;
                CountSpaceFirst -= 2;
                System.out.print("\n");

            } else {

                for (int j = 0; j < Iteration; j++) {
                    System.out.print("\\");
                    space(CountSpaceFirst);
                    System.out.print("/");
                    space(CountSpaceFinal);
                }

                CountSpaceFinal += 2;
                CountSpaceFirst -= 2;
                System.out.print("\n");

            }

        }

    }

}
