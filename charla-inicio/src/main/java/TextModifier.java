import java.util.Arrays;
import java.util.stream.Collectors;

public class TextModifier {

    public String doTextTransformation(String text) {
        return Arrays.stream(text.split(" ")).map(this::wordConverter).collect(Collectors.joining(" "));
    }

    private String wordConverter(String word) {
        return word.substring(0,1).toUpperCase()
            .concat(word.substring(1, word.length() - 1).toLowerCase())
            .concat(word.substring (word.length()-1).toUpperCase());
    }

    public int getHighestNumber(int[] numbers) {
        return Arrays.stream(numbers).max().getAsInt();
    }

    public int[] sumTwo(int[] numbers) {
        return Arrays.stream(numbers).map(operand -> operand += 2).toArray();
    }
}