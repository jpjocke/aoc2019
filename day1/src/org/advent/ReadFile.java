package org.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public List<Integer> getInput() {
        List<Integer> items = new ArrayList<>();
        BufferedReader br = null;
        try {
           br  = new BufferedReader(new FileReader("resources/input.txt"));
            String line = br.readLine();

            while (line != null) {
                Integer number = Integer.parseInt(line);
                items.add(number);
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
