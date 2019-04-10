import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Pias Tanmoy on 4/9/2019.
 */
public class GetWifiSignals {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        int roomNo;

        File dir = new File("F:\\CSE\\Java save files\\WifiSignals\\src");
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start","cmdwifi.bat");
        pb.directory(dir);

        roomNo = scanner.nextInt();
        File file = new File("F:\\CSE\\Java save files\\WifiSignals\\src\\output.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write("##############################################################\n");
        fr.write(roomNo+"\n");
        fr.write("##############################################################\n");
        fr.close();


        for (int i = 0; i < 5; i++) {
            System.out.println("i = " + i);
            fr = new FileWriter(file, true);
            fr.write("*****************************************\n");
            fr.close();
            pb.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
