package og.advent;

import org.advent.IntCode;
import org.advent.Util;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UtilTests {

    @Test
    public void toOps() {
        String input = "1,0,0,0,99";
        List<IntCode> ops = Util.toOperations(input);
        Assert.assertEquals(1, ops.get(0).getValue());
        Assert.assertEquals(0, ops.get(1).getValue());
        Assert.assertEquals(0, ops.get(2).getValue());
        Assert.assertEquals(0, ops.get(3).getValue());
        Assert.assertEquals(99, ops.get(4).getValue());
    }

    @Test
    public void toOps2() {
        String input = "2,3,0,3,99";
        List<IntCode> ops = Util.toOperations(input);
        Assert.assertEquals(2, ops.get(0).getValue());
        Assert.assertEquals(3, ops.get(1).getValue());
        Assert.assertEquals(0, ops.get(2).getValue());
        Assert.assertEquals(3, ops.get(3).getValue());
        Assert.assertEquals(99, ops.get(4).getValue());
    }
}
