package com.company;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.regex.Pattern;

public class InsertingCP {
    static void Selection(int secim,String[] Page) throws IOException {
        String kelime = "";
        if (secim == 1) {
            for (String Pages : Page) {
                if (Pages == null) {
                    continue;
                } else if (Pattern.matches("click.+", Pages)) {
                    kelime = kelime + "public UserProfilPage " + Pages + "() throws InterruptedException {\n" +
                            "        WebElement enable = findElement(UserProfilPageObjeRepo." + Pages + ");\n" +
                            "        if (enable.isEnabled()) {\n" +
                            "            click(UserProfilPageObjeRepo." + Pages + ");\n" +
                            "            System.out.println(testcase + \": aaa tıklandı.\");\n" +
                            "            childTest.log(Status.PASS, \" aaa tıklandı.\");\n" +
                            "            UserProfilDBLayer.DB_" + Pages + "PassInsert();\n" +
                            "        } else {\n" +
                            "            childTest.log(Status.FAIL, \" aaa tıklanmadı.\");\n" +
                            "            UserProfilDBLayer.DB_" + Pages + "FailInsert();\n" +
                            "            Assert.assertTrue(false);\n" +
                            "        }\n" +
                            "        return this;\n" +
                            "    }\n";
                } else if (Pattern.matches("control.+", Pages)) {

                    kelime = kelime + "public UserProfilPage " + Pages + "() {\n" +
                            "        if (isElementExist(UserProfilPageObjeRepo." + Pages + ")) {\n" +
                            "            control(isElementExist(UserProfilPageObjeRepo." + Pages + "), \" aaa.\", \" aaa değil.\");\n" +
                            "            childTest.log(Status.PASS, \"aaa .\");\n" +
                            "            UserProfilDBLayer.DB_" + Pages + "PassInsert();\n" +
                            "        } else {\n" +
                            "            childTest.log(Status.FAIL, \"aaa değil.\");\n" +
                            "            UserProfilDBLayer.DB_" + Pages + "FailInsert();\n" +
                            "            Assert.assertTrue(false);\n" +
                            "        }\n" +
                            "        return this;\n" +
                            "    }\n";

                } else if (Pattern.matches("enter.+", Pages)) {
                    kelime = kelime + "public AccountDashboardPage " + Pages + "(String Input) throws InterruptedException {\n" +
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
                    kelime = kelime + "//TODO:" + Pages;
                }
                InsertingFile.AppendLine("C:/Users/Kaan BOLDAN/IdeaProjects/Tüm işlemler/src/com/company/dosyasonudenemesi.txt",kelime);
            }
            StringSelection stringSelection = new StringSelection(kelime);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            System.out.println("Yapıştır");
        } else if (secim == 2) {
            for (String Pages : Page) {
                if (Pages == null) {
                    continue;
                } else if (Pattern.matches("enter.+", Pages)) {
                    kelime = kelime + " public static void DB_" + Pages + "PassInsertScenario() {\n" +
                            "        try (Connection connection = DriverManager.getConnection(connectionUrl);) {\n" +
                            "            Statement statement = connection.createStatement();\n" +
                            "            String queryPass = \"insert into dbo.UserProfilTestLog (Status,Timestamp,Details,Scenario) values ('PASS','\" + timestamp + \"','Arama butonuna hesap yazıldı.','Hesap Detay ekranında \\\"...\\\" tıklanıldığında kontrollerinin yapılması.')\";\n" +
                            "            statement.executeUpdate(queryPass);\n" +
                            "        } catch (SQLException e) {\n" +
                            "            e.printStackTrace();\n" +
                            "        }\n" +
                            "    }\n";
                    continue;

                }

                kelime = kelime + "public static void DB_" + Pages + "PassInsert() {\n" +
                        "        try (Connection connection = DriverManager.getConnection(connectionUrl);) {\n" +
                        "            Statement statement = connection.createStatement();\n" +
                        "            String queryPass = \"insert into dbo.UserProfilTestLog (Status,Timestamp,Details,Scenario) values ('PASS','\" + timestamp + \"','aaa','bbb')\";\n" +
                        "            statement.executeUpdate(queryPass);\n" +
                        "        } catch (SQLException e) {\n" +
                        "            e.printStackTrace();\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    public static void DB_" + Pages + "FailInsert() {\n" +
                        "        try (Connection connection = DriverManager.getConnection(connectionUrl);\n" +
                        "             Statement statement = connection.createStatement();) {\n" +
                        "            String queryFail = \"insert into dbo.UserProfilTestLog (Status,Timestamp,Details,Scenario) values ('FAIL','\" + timestamp + \"','ccc','ddd')\";\n" +
                        "            statement.executeUpdate(queryFail);\n" +
                        "        } catch (SQLException e) {\n" +
                        "            e.printStackTrace();\n" +
                        "        }\n" +
                        "    }\n";

            }
            StringSelection stringSelection = new StringSelection(kelime);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            System.out.println("Yapıştır");
        } else if (secim == 3) {
            for (String Pages : Page) {
                if (Pages == null) {
                    continue;
                } else {
                    kelime = kelime + "public static final By " + Pages + "=null;\n";
                }
            }
            StringSelection stringSelection = new StringSelection(kelime);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            System.out.println("Yapıştır");
        }
    }

}
