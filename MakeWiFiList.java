import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pias Tanmoy on 4/21/2019.
 */
public class MakeWiFiList {


    public static void main(String[] args) throws IOException {
        List<String> allLines;
        List<String> y = new ArrayList<>();
        ArrayList<ArrayList<String>> X = new ArrayList<ArrayList<String>>();
        BufferedReader reader;



        /**
         * Make a list of all unique mac addresses and unique places/room numbers
         */
        List<String> allPlaces;
        List<String> allMacs;
        allMacs = Files.readAllLines(Paths.get("F:\\CSE\\Java save files\\WifiSignals\\src\\macAddress.txt"));
        allPlaces = Files.readAllLines(Paths.get("F:\\CSE\\Java save files\\WifiSignals\\src\\places.txt"));

        System.out.println(allMacs.size() + " " + allPlaces.size());

        /**
         * Count the number of samples
         */
        int nSample = 0;
        allLines = Files.readAllLines(Paths.get("F:\\CSE\\Java save files\\WifiSignals\\src\\UAP_data.txt"));
        for (String line : allLines) {
            if(line.contains("*****************************************")) {
                nSample++;
            }
        }

        System.out.println(nSample);


        /**
         * Initialize all the the X (2D array) with 0
         */
        for(int place=0; place<nSample; place++) {
            ArrayList<String> wifiSignals = new ArrayList<>();
            for(int wifi=0; wifi<allMacs.size(); wifi++) {
                wifiSignals.add("0");
            }
            X.add(wifiSignals);
        }

        System.out.println(X.size());




        /**
         * Generate X with wifi signal strength
         */

        reader = new BufferedReader(new FileReader("F:\\CSE\\Java save files\\WifiSignals\\src\\UAP_data.txt"));
        String line = reader.readLine();
        int n = 0;
        int wifinum;
        String signal;
        ArrayList<String > row = new ArrayList<>();
        String currentPlace = "";

        while (line != null) {

            if(line.contains("*****************************************")) {

                if(n!=0) {
                    X.remove(n-1);
                    X.add(n-1, row);
                }

                line = reader.readLine(); //room number
                currentPlace = line;
                y.add(n, currentPlace);
                row = X.get(n);
                n++;
            }

            //int wifiNo = allMacs.indexOf(currentPlace);


            if(line.contains("BSSID")) {
                String[] words = line.split(" ");
                String mac = words[words.length-1];
                wifinum = allMacs.indexOf(mac);

                line = reader.readLine(); //next line
                words = line.split(" ");
                signal = words[words.length-1];
                signal = signal.substring(0, signal.length()-1);

                row.remove(wifinum);
                row.add(wifinum, signal);
            }

            line = reader.readLine();
        }

        /**
         * Write the data set
         */
        File file = new File("F:\\CSE\\Java save files\\WifiSignals\\src\\UAP_dataset.txt");
        FileWriter fr = new FileWriter(file, true);

        for(int i=0; i<allMacs.size(); i++) {
            fr.write(allMacs.get(i));
            fr.write(",");
        }

        fr.write("\n");

        for(int i=0; i<X.size(); i++) {
            for (int j=0; j<allMacs.size(); j++) {
                fr.write(X.get(i).get(j) + ",");
            }
            fr.write("\n");
        }
        fr.close();

        file = new File("F:\\CSE\\Java save files\\WifiSignals\\src\\UAP_dataset_y.txt");
        fr = new FileWriter(file, true);

        for(int i=0; i<y.size(); i++) {
            fr.write(y.get(i) + "\n");
        }
        fr.close();
    }
}
