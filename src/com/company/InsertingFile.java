package com.company;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.regex.Pattern;

public class InsertingFile {
    static void AppendLine(String dosyaYolu, String kelime) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter(dosyaYolu, true));
        output.append(kelime + System.lineSeparator());
        output.close();
    }

    static void Selection(String[] Page, String Görev, String pageDosyaYolu, String sqlDosyaYolu, String objeRepoDosyaYolu) throws IOException {
        String pageKelime = "";
        String sqlKelime = "";
        String objeRepoKelime = "";

        //Page
        System.out.println("Page Dosyaları Hazırlanıyor...");

        for (String Pages : Page) {
            if (Pages == null) {
                continue;
            } else if (Pattern.matches("click.+", Pages)) {
                pageKelime = pageKelime + "public " + Görev + "Page " + Pages + "() throws InterruptedException {\n" +
                        "        WebElement enable = findElement(" + Görev + "PageObjeRepo." + Pages + ");\n" +
                        "        if (enable.isEnabled()) {\n" +
                        "            click(" + Görev + "PageObjeRepo." + Pages + ");\n" +
                        "            System.out.println(testcase + \": aaa tıklandı.\");\n" +
                        "            childTest.log(Status.PASS, \" aaa tıklandı.\");\n" +
                        "            " + Görev + "DBLayer.DB_" + Pages + "PassInsert();\n" +
                        "        } else {\n" +
                        "            childTest.log(Status.FAIL, \" aaa tıklanmadı.\");\n" +
                        "            " + Görev + "DBLayer.DB_" + Pages + "FailInsert();\n" +
                        "            Assert.assertTrue(false);\n" +
                        "        }\n" +
                        "        return this;\n" +
                        "    }\n";
            } else if (Pattern.matches("control.+", Pages)) {

                pageKelime = pageKelime + "public " + Görev + "Page " + Pages + "() {\n" +
                        "        if (isElementExist(" + Görev + "PageObjeRepo." + Pages + ")) {\n" +
                        "            control(isElementExist(" + Görev + "PageObjeRepo." + Pages + "), \" aaa.\", \" aaa değil.\");\n" +
                        "            childTest.log(Status.PASS, \"aaa .\");\n" +
                        "            " + Görev + "DBLayer.DB_" + Pages + "PassInsert();\n" +
                        "        } else {\n" +
                        "            childTest.log(Status.FAIL, \"aaa değil.\");\n" +
                        "            " + Görev + "DBLayer.DB_" + Pages + "FailInsert();\n" +
                        "            Assert.assertTrue(false);\n" +
                        "        }\n" +
                        "        return this;\n" +
                        "    }\n";

            } else if (Pattern.matches("enter.+", Pages)) {
                pageKelime = pageKelime + "public AccountDashboardPage " + Pages + "(String Input) throws InterruptedException {\n" +
                        "        WebElement enable = findElement(AccountDashboardObjeRepo." + Pages + ");\n" +
                        "        if (enable.isEnabled()) {\n" +
                        "            sendKeys(AccountDashboardObjeRepo." + Pages + ", Input);\n" +
                        "            System.out.println(testcase + \": \" + Input + \" yazıldı.\");\n" +
                        "            childTest.log(Status.PASS, \"Arama butonuna hesap yazıldı.\" + Input);\n" +
                        "            AccountDashboardDBLayer.DB_" + Pages + "PassInsertScenario4();\n" +
                        "        }\n" +
                        "        return this;\n" +
                        "    }\n";

            } else {
                pageKelime = pageKelime + "//TODO:" + Pages + "\n";
            }


        }
        pageKelime = pageKelime + "}//end";
        InsertingFile.AppendLine(pageDosyaYolu, pageKelime);
        System.out.println("Page Dosyaları Hazırlandı...");


        //Sql
        System.out.println("SQL Dosyaları Hazırlanıyor...");
        for (String Pages : Page) {
            if (Pages == null) {
                continue;
            } else if (Pattern.matches("enter.+", Pages)) {
                sqlKelime = sqlKelime + " public static void DB_" + Pages + "PassInsertScenario() {\n" +
                        "        try (Connection connection = DriverManager.getConnection(connectionUrl);) {\n" +
                        "            Statement statement = connection.createStatement();\n" +
                        "            String queryPass = \"insert into dbo." + Görev + "TestLog (Status,Timestamp,Details,Scenario) values ('PASS','\" + timestamp + \"','Arama butonuna hesap yazıldı.','Hesap Detay ekranında \\\"...\\\" tıklanıldığında kontrollerinin yapılması.')\";\n" +
                        "            statement.executeUpdate(queryPass);\n" +
                        "        } catch (SQLException e) {\n" +
                        "            e.printStackTrace();\n" +
                        "        }\n" +
                        "    }\n";
                continue;

            }

            sqlKelime = sqlKelime + "public static void DB_" + Pages + "PassInsert() {\n" +
                    "        try (Connection connection = DriverManager.getConnection(connectionUrl);) {\n" +
                    "            Statement statement = connection.createStatement();\n" +
                    "            String queryPass = \"insert into dbo." + Görev + "TestLog (Status,Timestamp,Details,Scenario) values ('PASS','\" + timestamp + \"','aaa','bbb')\";\n" +
                    "            statement.executeUpdate(queryPass);\n" +
                    "        } catch (SQLException e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    public static void DB_" + Pages + "FailInsert() {\n" +
                    "        try (Connection connection = DriverManager.getConnection(connectionUrl);\n" +
                    "             Statement statement = connection.createStatement();) {\n" +
                    "            String queryFail = \"insert into dbo." + Görev + "TestLog (Status,Timestamp,Details,Scenario) values ('FAIL','\" + timestamp + \"','ccc','ddd')\";\n" +
                    "            statement.executeUpdate(queryFail);\n" +
                    "        } catch (SQLException e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }\n" +
                    "    }\n";

        }

        sqlKelime = sqlKelime + "}//end";
        InsertingFile.AppendLine(sqlDosyaYolu, sqlKelime);
        System.out.println("Sql Dosyaları Hazırlandı...");

        //ObjeRepo
        System.out.println("ObjeRepo Dosyaları Hazırlanıyor...");
        for (String Pages : Page) {
            if (Pages == null) {
                continue;
            } else {
                objeRepoKelime = objeRepoKelime + "public static final By " + Pages + "=null;\n";
            }
        }
        objeRepoKelime = objeRepoKelime + "}//end";
        InsertingFile.AppendLine(objeRepoDosyaYolu, objeRepoKelime);
        System.out.println("ObjeRepo Dosyaları Hazırlandı...");

    }
}


