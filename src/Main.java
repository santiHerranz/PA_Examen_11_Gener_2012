import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int qQuadres= 4; //Keyboard.readInt();
        float paret = 100; //Keyboard.readInt();
        Decoració s = new Decoració(qQuadres, paret);
        System.out.println(Arrays.toString(s.getQuadres()));
        s.backtracking(0);
        System.out.println(s.toString());
    }
}


