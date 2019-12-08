package advent;

import java.util.List;

public class Picture {
    List<List<Layer>> picture;

    public Picture(List<List<Layer>> picture) {
        this.picture = picture;
    }

    public int getLayers() {
        return picture.size();
    }

    public List<Layer> findLayersWithFewest(int digit) {
        System.out.println("counting " + digit);
        List<Layer> fewestLayer = null;
        int fewest = Integer.MAX_VALUE;
        for (int i = 0; i < picture.size(); i++) {
            int counter = 0;
            List<Layer> layer = picture.get(i);
            for (int j = 0; j < layer.size(); j++) {
                counter += layer.get(j).countDigits(digit);
            }
            System.out.println("Layer " + i + " had " + counter);
            if (counter < fewest) {
                System.out.println("FEWEST");
                fewest = counter;
                fewestLayer = layer;
            }
        }
        return fewestLayer;
    }

    public int multipleNumberOfABonLayerC(int a, int b, List<Layer> layer) {
        System.out.println("counting " + a + ", " + b);
        int as = 0;
        int bs = 0;
        for (int i = 0; i < layer.size(); i++) {
            System.out.println(layer.get(i));
            as += layer.get(i).countDigits(a);
            bs += layer.get(i).countDigits(b);
            System.out.println("as: " + as + ",bs: " + bs);
        }
        return as * bs;
    }
}
