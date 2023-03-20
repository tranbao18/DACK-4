import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_test1 {
    @Test
    public void testIsPrimeNumber1() {
        test_1 demo = new test_1();
        int result = demo.Premium("Roadside", "Yes", 0, 5001, 100000, "");
        assertEquals(1471, result, "done1");
    }
    @Test
    public void testIsPrimeNumber2() {
        test_1 demo = new test_1();
        int result = demo.Premium("Roadside", "Yes", 0, 5001, 100, "");
        assertEquals(0, result, "done2");
    }

    @Test
    public void testIsPrimeNumber3() {
        test_1 demo = new test_1();
        int result = demo.Premium("Roadside", "Yes", 0, 5001, 99, "");
        assertEquals(0, result, "done3");
    }

    @Test
    public void testIsPrimeNumber4() {
        test_1 demo = new test_1();
        int result = demo.Premium("No Cover", "Yes", 0, 5001, 100000, "");
        assertEquals(771, result, "done4");
    }
    @Test
    public void testIsPrimeNumber5() {
        test_1 demo = new test_1();
        int result = demo.Premium("At home", "Yes", 0, 5001, 100000, "");
        assertEquals(2171, result, "done5");
    }
    @Test
    public void testIsPrimeNumber6() {
        test_1 demo = new test_1();
        int result = demo.Premium("European", "Yes", 0, 5001, 100000, "");
        assertEquals(2871, result, "done6");
    }
    @Test
    public void testIsPrimeNumber7() {
        test_1 demo = new test_1();
        int result = demo.Premium("Roadside", "No", 0, 5001, 100000, "");
        assertEquals(1450, result, "done7");
    }
    @Test
    public void testIsPrimeNumber8() {
        test_1 demo = new test_1();
        int result = demo.Premium("Roadside", "Yes", 1, 5001, 100000, "");
        assertEquals(2080, result, "done8");
    }

    @Test
    public void testIsPrimeNumber9() {
        test_1 demo = new test_1();
        int result = demo.Premium("Roadside", "Yes", 0, 5000, 100000, "");
        assertEquals(1421, result, "done9");
    }
}
