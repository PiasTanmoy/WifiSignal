/**
 * Created by Pias Tanmoy on 4/9/2019.
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ExtractData {




    public static void main(String[] args) throws IOException {

        Set<String> macSet = new HashSet<>();
        List<String> allLines = Files.readAllLines(Paths.get("F:\\CSE\\Java save files\\WifiSignals\\src\\UAP.txt"));

        /**
         * This is to extract the mac addresses from the raw data set
         */
        for (String line : allLines) {
            if(line.contains("BSSID")) {
                String[] words = line.split(" ");
                //System.out.println(words[words.length-1]);
                macSet.add(words[words.length-1]);
            }
            //System.out.println(line);
        }

        /**
         * Write the wifi mac addresses to the file macAddress.txt
         */
        File file = new File("F:\\CSE\\Java save files\\WifiSignals\\src\\macAddress.txt");
        FileWriter fr = new FileWriter(file, true);
        for(String mac:macSet){
            fr.write(mac+"\n");
        }
        fr.close();

        /**
         * Extract the room numbers of places ids
         * and write them in to a file called places.txt
         */
        file = new File("F:\\CSE\\Java save files\\WifiSignals\\src\\places.txt");
        fr = new FileWriter(file, true);

        BufferedReader reader;
        reader = new BufferedReader(new FileReader("F:\\CSE\\Java save files\\WifiSignals\\src\\UAP.txt"));
        String line = reader.readLine();
        while (line != null) {

            if(line.contains("##############################################################")) {
                line = reader.readLine();
                if(!line.contains("*****************************************")) {
                    //System.out.println(line);
                    fr.write(line + "\n");
                }
            }
            // read next line
            line = reader.readLine();
        }
        reader.close();
        fr.close();

        /**
         * Eliminate the unnecessary lines from the raw data
         */

        file = new File("F:\\CSE\\Java save files\\WifiSignals\\src\\UAP_data.txt");
        fr = new FileWriter(file, true);

        reader = new BufferedReader(new FileReader("F:\\CSE\\Java save files\\WifiSignals\\src\\UAP.txt"));
        line = reader.readLine();
        String currentPlace = "";
        while (line != null) {

            if(line.contains("##############################################################")) {
                line = reader.readLine();
                if(!line.contains("*****************************************")) {
                    currentPlace = line;
                }
            }

            if(line.contains("##############################################################")) {
                fr.write(line + "\n");
            }
            else if(line.contains("*****************************************")) {
                fr.write(line + "\n");
                fr.write(currentPlace + "\n");
            }
            else if(line.contains("BSSID")) {
                fr.write(line + "\n");
            }
            else if(line.contains("Signal")) {
                fr.write(line + "\n");
            }
            // read next line
            line = reader.readLine();
        }
        reader.close();
        fr.close();

    }
}
