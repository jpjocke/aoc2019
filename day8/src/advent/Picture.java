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

    public void print() {
        int[][] pic = toImage();

        for (int i = 0; i < pic.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < pic[i].length; j++) {
                int val = pic[i][j];
                if (val == 0 || val == 1) {
                    sb.append(val == 0 ? " " : "*");
                }
            }


            System.out.println(sb);
        }
    }

    private int[][] toImage() {
        int[][] pic = new int[picture.get(0).size()][picture.get(0).get(0).length()];
        for (int i = 0; i < pic.length; i++) {
            for (int j = 0; j < pic[i].length; j++) {
                pic[i][j] = 2;
            }
        }

        for (int o = 0; o < picture.size(); o++) {
            List<Layer> layer = picture.get(o);
            for (int i = 0; i < layer.size(); i++) {
                for (int j = 0; j < layer.get(i).length(); j++) {
                    if (pic[i][j] == 2) {
                        pic[i][j] = layer.get(i).getDigitAt(j);
                    }
                }
            }
        }
        return pic;
    }
}
