import java.util.ArrayList;

/**
 * Created by Pias Tanmoy on 4/22/2019.
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<String > t = new ArrayList<>();
        t.add("0");
        t.add("1");
        t.add("2");
        t.add("3");

        t.remove(0);
        t.add(0, "100");
        System.out.println(t);

        String s = "15%";
        s = s.substring(0, s.length()-1);
        System.out.println(s);
    }
}
