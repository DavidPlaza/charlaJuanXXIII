import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TextModifierTest {

    private TextModifier sut;

    @BeforeEach
    void initTest() {
        sut = new TextModifier();
    }

    @Test
    public void methodShoulWork() {
        //Given
        String text = "Hola que tal me llamo david plaza urda encantado";
        String finalText = "HolA QuE TaL ME LlamO DaviD PlazA UrdA EncantadO";

        //When
        String result = sut.doTextTransformation(text);

        //Then
        assertEquals(finalText, result);
        System.out.println(result);
    }

    @Test
    void shouldReturnHighestNumber() {
        // Given
        int expected = 10;
        int numbers[] = {1, 2, 3, 4, 5, expected, 7, 8};

        //when

        int result = sut.getHighestNumber(numbers);

        //Then

        assertEquals(expected, result);
    }

    @Test
    void shouldReturnArraySumTwo() {
        // Given
        int expected[] = {3, 4, 5, 6, 7, 8, 9};
        int numbers[] = {1, 2, 3, 4, 5, 6, 7};

        //when

        int[] result = sut.sumTwo(numbers);

        //Then

        assertArrayEquals(expected, result);
    }

}