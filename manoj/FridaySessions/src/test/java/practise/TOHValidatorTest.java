package practise;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class TOHValidatorTest {
    private TOHValidator toh = new TOHValidator();

    @Test
    public void test_with_Valid_Steps() {
        List<String> inputs = Arrays.asList("1:C", "2:B", "1:B", "3:C", "1:A", "2:C", "1:C");
        assertTrue(toh.areValidSteps(inputs));
    }

    @Test
    public void test_with_inValid_Steps() {
        List<String> inputs = Arrays.asList("2:C", "2:B", "1:C", "3:C", "1:A", "2:C", "1:C");
        assertFalse(toh.areValidSteps(inputs));

        List<String> inputs1 = Arrays.asList("1:C", "2:B", "1:C", "3:C", "1:A", "3:C", "1:C");
        assertFalse(toh.areValidSteps(inputs1));
    }

}
