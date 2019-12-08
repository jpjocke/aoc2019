package org.advent.util.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public static List<String> readFileAsStringsPerLine(String file) {
        List<String> items = new ArrayList<>();
        BufferedReader br = null;
        try {
           br  = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                items.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return items;
    }
}
