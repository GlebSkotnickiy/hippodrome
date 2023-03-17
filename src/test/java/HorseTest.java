import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {

    @Test
    void nullNameException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    void nullNameText() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1, 1)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", "   ", "\t\t"
    })
    void isBlankException(String name) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", "   ", "\t\t"
    })
    void isBlankText(String name) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void negativeNumberException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 1));
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    void negativeNumberText() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void negativeSecondParameterException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 1));
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    void negativeSecondParameterText() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void negativeThirdParameterException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -1));
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    void negativeThirdParameterText() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        String name = "Klim Sanich";
        assertEquals(name, new Horse(name, 1, 1).getName());
    }

    @Test
    void getSpeed() {
        int speed = 3;
        assertEquals(speed, new Horse("name", speed, 1).getSpeed());
    }

    @Test
    void getDistance() {
        int distance = 10;
        assertEquals(distance, new Horse("name", 1, distance).getDistance());
    }

    @Test
    void getDistanceWithoutParameter() {
        assertEquals(0, new Horse("name", 1).getDistance());
    }

    @Test
    void moveGetRandom() {
        MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class);
        Horse horse = new Horse("name", 1, 1);
        horse.move();
        mockStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
    }

    @Test
    void moveGetRandomDouble() {
        MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class);
        mockStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.7);
        Horse horse = new Horse("name", 3, 7);
        double result = 7 + 3 * 0.7;
        horse.move();
        assertEquals(result, horse.getDistance());
    }
}
