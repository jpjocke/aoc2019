package com.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public static List<String> getInput() {
        List<String> items = new ArrayList<>();
        BufferedReader br = null;
        try {
           br  = new BufferedReader(new FileReader("/home/jocke/jProjects/advent/day6/resources/day6_input.txt"));
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
