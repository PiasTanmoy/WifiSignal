/**
 * Created by Pias Tanmoy on 4/9/2019.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ExtractData {




    public static void main(String[] args) throws IOException {

        Set<String> macSet = new HashSet<>();

        try {
            List<String> allLines = Files.readAllLines(Paths.get("F:\\CSE\\Java save files\\WifiSignals\\src\\output.txt"));
            for (String line : allLines) {
                if(line.contains("BSSID")) {
                    String[] words = line.split(" ");
                    System.out.println(words[words.length-1]);

                    macSet.add(words[words.length-1]);


                }
                //System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("F:\\CSE\\Java save files\\WifiSignals\\src\\macAddress.txt");
        FileWriter fr = new FileWriter(file, true);
        for(String mac:macSet){
            fr.write(mac+"\n");
        }
        fr.close();
    }
}
