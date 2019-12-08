package advent;


import org.advent.util.file.ReadFile;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        String input = ReadFile.readFileAsStringsPerLine("/home/jocke/jProjects/advent/day8/resources/day8_in.txt").get(0);

        Picture picture = PixelParser.parse(input, 25, 6);

        List<Layer> fewest = picture.findLayersWithFewest(0);

        int result = picture.multipleNumberOfABonLayerC(1, 2, fewest);
        System.out.println("Result is: " + result);

        picture.print();
    }

}
