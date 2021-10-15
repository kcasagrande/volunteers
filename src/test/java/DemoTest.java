import org.example.volunteers.Demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoTest {

    private Function<String, Integer> dependency;

    @BeforeEach
    public void setUp() {
        dependency = (String string) -> 1;
    }

    @Test
    public void shouldAlwaysPass() {
        assertTrue(true);
    }

    @Test
    public void shouldReturnTheResultOfACallToTheDependency() {
        // Arrange
        Demo demo = new Demo(dependency);
        String input = "tdd";
        int expected = 1;

        // Act
        int actual = demo.run(input);

        // Assert
        assertEquals(expected, actual, "Ce message s'affiche si le test échoue");
    }
}
