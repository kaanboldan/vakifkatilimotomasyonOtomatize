package com.company;

import java.io.*;
import java.util.ArrayList;

public class Tools {

    public static void JavaKodunuOku(String DosyaYolu) throws IOException {
        String cacheDizi = "C:/Users/Kaan BOLDAN/IdeaProjects/Tüm işlemler/src/com/company/cacheDizin.java";


        BufferedReader reader = new BufferedReader(new FileReader(DosyaYolu));
        BufferedWriter writer = new BufferedWriter(new FileWriter(cacheDizi));

        String lineToRemove = "}//end";
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }

        writer.close();
        reader.close();
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(DosyaYolu));
        BufferedReader reader1 = new BufferedReader(new FileReader(cacheDizi));
        String currentLine1 = null;
        String lineToRemove1 = "gdfkjgkrewlkjrwelrwekljrwelkrjweklrjfdlsg";

        while ((currentLine = reader1.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine1 = currentLine.trim();
            if (trimmedLine1.equals(lineToRemove1)) continue;
            writer1.write(currentLine + System.getProperty("line.separator"));
        }
        //boolean successful = tempFile.renameTo(DosyaYolu);
        //System.out.println(successful);
        writer1.close();
        reader1.close();

    }

    public static String[] dosyaOku(String DosyaYolu) throws IOException {
        ArrayList<String> dosya = new ArrayList<String>();
        FileInputStream fstream = new FileInputStream(DosyaYolu);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;

        while ((strLine = br.readLine()) != null) {
            dosya.add(stringDuzelt(strLine));
        }
        String[] sonuc = dosya.toArray(new String[0]);
        fstream.close();

        return sonuc;
    }

    public static String stringDuzelt(String in) {
        String out;
        int uzunluk;
        in = in.trim();
        uzunluk = in.length();
        if (in.contains(";")) {
            out = in.substring(1, uzunluk - 3);

        } else {
            out = in.substring(1, uzunluk - 2);
        }

        return out;
    }

    public static void DosyayıSil(String DosyaYolu) {
        File myObj = new File(DosyaYolu);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
    public static void dosyaYarat(String DosyaYolu){
        try {
            File myObj = new File(DosyaYolu);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
