package com.company;

import javax.tools.Tool;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String pageDosyaYolu = "C:/Users/Kaan BOLDAN/Desktop/UserProfil/src/test/java/com/Page/UserProfilPage.java";
        String sqlDosyaYolu = "C:/Users/Kaan BOLDAN/Desktop/UserProfil/src/test/java/com/DBLayer/UserProfilDBLayer.java";
        String objeRepoDosyaYolu = "C:/Users/Kaan BOLDAN/Desktop/UserProfil/src/test/java/com/ObjeRepo/UserProfilPageObjeRepo.java";
        String fonksiyonlarYolu = "C:/Users/Kaan BOLDAN/IdeaProjects/Tüm işlemler/src/com/company/deneme.txt";
        String cacheYolu="C:/Users/Kaan BOLDAN/IdeaProjects/Tüm işlemler/src/com/company/cacheDizin.java";



        Tools.JavaKodunuOku(pageDosyaYolu);
        Tools.JavaKodunuOku(sqlDosyaYolu);
        Tools.JavaKodunuOku(objeRepoDosyaYolu);

        InsertingFile.Selection(Tools.dosyaOku(fonksiyonlarYolu), "UserProfil",pageDosyaYolu,sqlDosyaYolu,objeRepoDosyaYolu);

        Tools.DosyayıSil(fonksiyonlarYolu);
        Tools.DosyayıSil(cacheYolu);
        Tools.dosyaYarat(fonksiyonlarYolu);
        Tools.dosyaYarat(cacheYolu);

    }
}
