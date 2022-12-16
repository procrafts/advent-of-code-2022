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

    @Test
    void Main_run5() throws URISyntaxException, IOException {
        var exampleResult = Main.run05("challenge05-example.txt");
        assertEquals("CMZ", exampleResult.get(0));
        assertEquals("MCD", exampleResult.get(1));
        var result = Main.run05("challenge05-input.txt");
        assertEquals("RFFFWBPNS", result.get(0));
        assertEquals("CQQBBJFCS", result.get(1));
        System.out.println("-----");
        System.out.println(result);
        System.out.println("-----");
    }

    @Test
    void Main_run6() throws URISyntaxException, IOException {
        var exampleResult = Main.run06("challenge06-example.txt");
        assertEquals(32, exampleResult.get(0));
        assertEquals(101, exampleResult.get(1));
        var result = Main.run06("challenge06-input.txt");
        assertEquals(1356, result.get(0));
        assertEquals(2564, result.get(1));
        System.out.println("-----");
        System.out.println(result);
        System.out.println("-----");
    }

    @Test
    void Main_run7() throws URISyntaxException, IOException {
        var exampleResult = Main.run07("challenge07-example.txt");
        assertEquals(95437, exampleResult.get(0));
        assertEquals(24933642, exampleResult.get(1));
        var result = Main.run07("challenge07-input.txt");
        assertEquals(1350966, result.get(0));
        assertEquals(6296435, result.get(1));
        System.out.println("-----");
        System.out.println(result);
        System.out.println("-----");
    }

    @Test
    void Main_run8() throws URISyntaxException, IOException {
        var exampleResult = Main.run08("challenge08-example.txt");
        assertEquals(21, exampleResult.get(0));
        assertEquals(8, exampleResult.get(1));
        var result = Main.run08("challenge08-input.txt");
        assertEquals(1679, result.get(0));
        assertEquals(536625, result.get(1));
        System.out.println("-----");
        System.out.println(result);
        System.out.println("-----");
    }

    @Test
    void Main_run9() throws URISyntaxException, IOException {
        var exampleResult = Main.run09("challenge09-example.txt");
        assertEquals(13, exampleResult.get(0));
        assertEquals(1, exampleResult.get(1));
        var result = Main.run09("challenge09-input.txt");
        assertEquals(6503, result.get(0));
        assertEquals(2724, result.get(1));
        System.out.println("-----");
        System.out.println(result);
        System.out.println("-----");
    }
}
