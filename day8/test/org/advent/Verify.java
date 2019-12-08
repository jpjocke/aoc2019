package org.advent;

import advent.Picture;
import advent.Layer;
import advent.PixelParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class Verify {

    @Test
    public void imageOne() {
        String image = "123456789012";
        Picture picture = PixelParser.parse(image, 3, 2);
        Assert.assertEquals(2, picture.getLayers());
    }

    @Test
    public void imageOneMultiply() {
        String image = "103406333012";
        Picture picture = PixelParser.parse(image, 3, 2);
        List<Layer> fewest = picture.findLayersWithFewest(0);
        int multiply1 = picture.multipleNumberOfABonLayerC(1,2, fewest);
        Assert.assertEquals(1, multiply1);

        int multiply2 = picture.multipleNumberOfABonLayerC(1,3, fewest);
        Assert.assertEquals(3, multiply2);

    }
}
