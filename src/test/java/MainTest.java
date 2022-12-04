import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

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
                                
                10000""".split("\n")).toList(), FileHandler.convertFileToStrings(FileHandler.readFile("challenge01-example.txt")));
    }

    @Test
    void Main_run1() throws URISyntaxException, IOException {
        List<Integer> exampleResult = Main.run01("challenge01-example.txt");
        assertEquals(24000, exampleResult.get(0));
        assertEquals(45000, exampleResult.get(1));
        List<Integer> result = Main.run01("challenge01-input.txt");
        assertEquals(72478, result.get(0));
        assertEquals(210367, result.get(1));
        System.out.println("-----");
        System.out.println(result);
        System.out.println("-----");
    }

    @Test
    void Main_run2() throws URISyntaxException, IOException {
        var exampleResult = Main.run02("challenge02-example.txt");
        assertEquals(15, exampleResult.get(0));
        assertEquals(12, exampleResult.get(1));
        var result = Main.run02("challenge02-input.txt");
        assertEquals(15422, result.get(0));
        assertEquals(15442, result.get(1));
        System.out.println("-----");
        System.out.println(result);
        System.out.println("-----");
    }

    @Test
    void Main_run3() throws URISyntaxException, IOException {
        var exampleResult = Main.run03("challenge03-example.txt");
        assertEquals(157, exampleResult.get(0));
        assertEquals(70, exampleResult.get(1));
        var result = Main.run03("challenge03-input.txt");
        assertEquals(8233, result.get(0));
        assertEquals(2821, result.get(1));
        System.out.println("-----");
        System.out.println(result);
        System.out.println("-----");
    }

    @Test
    void Main_run4() throws URISyntaxException, IOException {
        var exampleResult = Main.run04("challenge04-example.txt");
        assertEquals(2, exampleResult.get(0));
        assertEquals(4, exampleResult.get(1));
        var result = Main.run04("challenge04-input.txt");
        assertEquals(595, result.get(0));
        assertEquals(952, result.get(1));
        System.out.println("-----");
        System.out.println(result);
        System.out.println("-----");
    }
}
