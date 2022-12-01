import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    void runMain_shouldCompile() {
        Main.main(new String[0]);
        assertTrue(true);
    }

    @Test
    void Main_convertFileToStrings() throws URISyntaxException, IOException {
        assertEquals(Arrays.stream("""
                1000
                2000
                3000
                                
                4000
                                
                5000
                6000
                                
                7000
                8000
                9000
                                
                10000""".split("\n")).toList(), Main.convertFileToStrings(Main.readFile("example1.txt")));
    }

    @Test
    void Main_run1() throws URISyntaxException, IOException {
        int exampleResult = Main.run1("example1.txt");
        assertEquals(24000, exampleResult);
        int result = Main.run1("input1.txt");
        System.out.println("-----");
        System.out.println(result);
        System.out.println("-----");
    }
}
