package com.stratapps.xamplify.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVUtil {

    public static String generateCSV(List<String[]> rows) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = new File("test_data_" + timestamp + ".csv");

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("FIRSTNAME,LASTNAME,COMPANY,JOBTITLE,EMAILID,ADDRESS,CITY,STATE,ZIP CODE,COUNTRY,MOBILE NUMBER");
            for (String[] row : rows) {
                writer.println(String.join(",", row));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CSV", e);
        }

        return file.getAbsolutePath();
    }
}
