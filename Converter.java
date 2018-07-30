import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author merth
 */

public class Converter {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String splitter = ",";
        String fileLoc = "CSV_FILE_LOCATION";
		//At first Arff creating as Txt
        File file = new File("FILE_LOCATION/x.txt");
		//At the end of the code, Txt file is renaming as Arff.
        File file2 = new File("FILE_LOCATION/converted.arff"); // Converted file location

        BufferedReader br = null;
        BufferedReader br2 = null;
        BufferedReader last = null;

        String line = "";
        int length = 0;
        int c = 0;
        int choose[] = new int[100];
        String nominal_data = "";
        String data = "";
        String labels="";
        int nominal_indexes[] = new int[100];
        int k = 0;
        int count=0;
        int sort[]=new int[100];
        try {
            br2 = new BufferedReader(new FileReader(fileLoc));
            labels += br2.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] nom = labels.split(splitter);
        for (int i = 0; i < 100; i++) {
            choose[i] = 999;

        }
		
        String opts[] = labels.split(splitter);
        System.out.println("Labels are:\n" + labels);
        System.out.println("All Nominal : 1 | All Numeric : 2 | Personal Choice : 3");
        int opt = sc.nextInt();

        if (opt == 1) {
            for (int i = 0; i < opts.length; i++) {
                choose[i] = i;
            }
        }
        if (opt == 2);

        if (opt == 3) {
            System.out.println("Enter the number of nominal value");
            count = sc.nextInt();    
            System.out.println("Enter the index/es of nominal value/s (First label index is 0):");

            for (int i = 0; i < count; i++) {
                sort[i] = sc.nextInt();
            }
        }
        
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count-i-1; j++) {
                if(sort[j]>sort[j+1]){
                    int tmp=sort[j];
                    sort[j]=sort[j+1];
                    sort[j+1]=tmp;
                }
            }
            
        }
        if(opt==3){
        for (int i = 0; i < count; i++) {
            choose[i]=sort[i];
        }
        }
        List all_data = new ArrayList();
        List nominals = new ArrayList();

        for (int i = 0; i < nom.length; i++) {
            try {
                br = new BufferedReader(new FileReader(fileLoc));
                br.readLine();
                while ((line = br.readLine()) != null) {

                    String[] str = line.split(splitter);
                    length = str.length;

                    if (choose[i] != 999) {
                        nominal_data += str[choose[i]] + ",";
                    }

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            all_data.add(i, nominal_data);
            nominal_data = "";
        }
        for (int i = 0; i < 100; i++) {
            if (choose[i] != 999) {
                nominal_indexes[choose[i]] = 1;
            }
        }

        String defining = "@RELATION " + fileLoc.substring(fileLoc.lastIndexOf("/") + 1, fileLoc.lastIndexOf(".")) + "\n\n";

        for (int x = 0; x < all_data.size(); x++) {
            if (all_data.get(x) == "") {
                continue;
            }
            nominal_data = "" + all_data.get(x);

            String[] x1 = nominal_data.split(splitter);
            nominal_data = "";

            for (int i = 0; i < x1.length; i++) {
                if (i == 0) {
                    nominal_data += x1[i] + ",";
                }
                if (i > 0) {
                    c = 0;
                    for (int j = i; j >= 0; j--) {
                        if (x1[i].equals(x1[j]) && i != j) {
                            c++;
                            break;
                        }
                    }
                    if (c == 0) {
                        nominal_data += x1[i] + ",";
                    }
                }

            }

            data = "{";
            data += nominal_data.substring(0, nominal_data.length() - 1);
            data += "}";
            nominals.add(data);

            System.out.println(data + "\n");

        }

        for (int i = 0; i < length; i++) {
            defining += "@ATTRIBUTE " + opts[i];
            if (nominal_indexes[i] != 1) {
                defining += " REAL\n";
            } else {
                defining += " " + nominals.get(k) + "\n";
                k++;
            }
        }

        defining += "\n@DATA\n";

        try {

            br = new BufferedReader(new FileReader(fileLoc));
            line = br.readLine();
            while ((line = br.readLine()) != null) {

                String laststr = line;
                defining += laststr + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        System.out.println(defining);
		
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(defining);
        bWriter.close();
		
        file.renameTo(file2);
    }
}
