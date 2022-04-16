public class Map {
    public  int Iteration = 8;
    public  int size = 5;

    public  void space(int size) {
        int counter = 0;
        for (counter = 0; counter < size; counter++) {
            // Add space
            System.out.print(" ");
        }
    }

    public static void print_symbol(int size) {
        int counter = 0;
        for (counter = 0; counter < size; counter++) {
            System.out.print("_");
        }
    }

    // Print hexagon of given side
    public void PrintMap() {
        // Print top layers
        int mid = size + 3;
        int CountSpaceFinal = size + 4;
        int CountSpaceFirst = size;
        for (int i = 0; i < mid / 2; i++) {
            space(size - i);
            if (i == 0) {

                for (int j = 0; j < Iteration; j++) {
                    print_symbol(size);
                    space(CountSpaceFinal + 2);
                }
                CountSpaceFinal -= 2;
                System.out.print("\n");
            } else {

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
