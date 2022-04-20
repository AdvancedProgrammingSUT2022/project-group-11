import Model.Color;

public class Main{
    public static void main(String[] args) {
        String print = "";
        print += "/";
        print+= Color.GREEN_BACKGROUND;
        for(int i = 0; i < 5;i++){
          print+=" ";
        }
        print+= Color.RESET;
        print+= "\\";
        System.out.println(print);
       /* System.out.print(Color.RED);
        System.out.print(Color.YELLOW_BACKGROUND);
      
        System.out.print("/ hello");
        System.out.print(Color.RESET);
*/
       
        
    }
}