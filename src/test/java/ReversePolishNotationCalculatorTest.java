import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReversePolishNotationCalculatorTest {

    ReversePolishNotationCalculator reversePolishNotationCalculator = new ReversePolishNotationCalculator();

    @Test
    public void shouldCalculateAddition() {
        //проверка операций +, -, *
        int result = reversePolishNotationCalculator.calculatePolishNotation("4 3 +");
        assertEquals(7, result, "4 3 + = 7 (result = " + result + ")");

        result = reversePolishNotationCalculator.calculatePolishNotation("4 3 -");
        assertEquals(1, result, "4 3 - = 1 (result = " + result + ")");

        result = reversePolishNotationCalculator.calculatePolishNotation("4 3 *");
        assertEquals(12, result, "4 3 * = 12 (result = " + result + ")");

        //проверка пустого значения
        assertThrows(
                NoSuchElementException.class,
                () -> reversePolishNotationCalculator.calculatePolishNotation(""),
                "Пустая строка должна возвращать ошибку NoSuchElementException"
        );

        //проверка совместной работы операций
        result = reversePolishNotationCalculator.calculatePolishNotation("1 17 3 4 * - +");
        assertEquals(6, result, "1 17 3 4 * - + = 6 (result = " + result + ")");

        //работа с отрицательным числом
        result = reversePolishNotationCalculator.calculatePolishNotation("-1 10 1 -5 * + +");
        assertEquals(4, result, "-1 10 1 -5 * + + = 4 (result = " + result + ")");

        //работа с лишними проблеми
        result = reversePolishNotationCalculator.calculatePolishNotation(" -1   10    1 -5  * +    +  ");
        assertEquals(4, result, " -1   10    1 -5  * +    +   = 4 (result = " + result + ")");

        //проверка неверных символов
        assertThrows(
                NumberFormatException.class,
                () -> reversePolishNotationCalculator.calculatePolishNotation("4 3 /"),
                "Неверный символ 4 3 / должен возвращать ошибку NumberFormatException"
        );

        assertThrows(
                NumberFormatException.class,
                () -> reversePolishNotationCalculator.calculatePolishNotation("4 3 1 = *"),
                "Неверный символ 4 3 1 = * должен возвращать ошибку NumberFormatException"
        );
    }
}
