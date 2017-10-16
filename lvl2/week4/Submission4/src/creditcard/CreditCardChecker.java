package creditcard;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Checks if credit card numbers are valid.
 * @author 2258082R Stuart Reilly
 */
@SuppressWarnings("Duplicates")
public class CreditCardChecker {

	/**
	 * Validates the given credit card number. This method is fail-fast, meaning it will throw the specified exceptions before any processing
	 * is carried out.
	 * @param creditCard credit card number to check
	 * @return Return true if the given credit card number is valid, false otherwise
	 * @throws IllegalCardFormatException Thrown if the given credit card number contains any non-digit characters (as defined by 
	 * <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#isDigit-char-">Character.isDigit</a>)
	 * @throws IllegalCardLengthException Thrown if the given credit card number is not the correct length for its type
	 */
    public boolean validate(String creditCard) throws IllegalCardFormatException, IllegalCardLengthException {
        //Ensure the card number length is possibly valid, throwing an exception if it is not
        if (creditCard.length() > 19 || creditCard.length() < 13) {
            throw new IllegalCardLengthException("must be between 13 and 19 characters");
        }
        //Find the first non-digit character, throwing an exception if there is one
        OptionalInt possibleInvalid = creditCard.codePoints().filter(c -> !Character.isDigit(c)).findFirst();
        if (possibleInvalid.isPresent()) {
            throw new IllegalCardFormatException("unexpected character: "+ Arrays.toString(Character.toChars(possibleInvalid.getAsInt())));
        }

        //Check card type, exiting if invalid
        CardType cardType = CardType.fromString(creditCard);
        if (cardType == CardType.UNKNOWN) {
            return false;
        }

        //Check if the length of the credit card number is valid for its card type
        if (cardType.possibleLens().noneMatch(len -> len == creditCard.length())) {
            throw new IllegalCardLengthException("given card number was [" +
                    creditCard.length() +
                    "] in length, but should be [" +
                    cardType.possibleLens().mapToObj(String::valueOf).collect(Collectors.joining(",")) +
                    ']');
        }

        int sum = 0;
        //Reverse iteration as the check digit uses Luhn Algorithm
        for (int i = creditCard.length()-1; i > -1; i--) {
            int num = Character.getNumericValue(creditCard.charAt(i));
            // Check if the current digit is an even distance from the end of the string
            if ((creditCard.length() - i) % 2 == 0) {
                num *= 2;
                if (num > 9) {
                    num -= 9;
                }
            }
            sum += num;
        }

        return sum % 10 == 0;
    }
}

@SuppressWarnings("Duplicates")
enum CardType {
    VISA(new int[]{13, 16, 19}),
    AMERICAN_EXPRESS(new int[]{15}),
    MASTERCARD(new int[]{16}),
    UNKNOWN(new int[]{-1});

    int[] possibleLens;

    CardType(int[] possibleLens) {
        this.possibleLens = possibleLens;
    }

    /**
     * Returns a new CardType from the given String.
     * The following CardTypes are returned from the following starting pattern to the given String:
     * <ul>
     *     <li>"4" - VISA</li>
     *     <li>"3[47]" - AMERICAN_EXPRESS</li>
     *     <li>"5[1..5]" - MASTERCARD</li>
     *     <li>All other strings - UNKNOWN</li>
     * </ul>
     * @param creditCard The credit card number
     * @return The type of credit card
     */
    public static CardType fromString(String creditCard) {
        if (creditCard.charAt(0) == '4') {
            return VISA;
        } else if (creditCard.charAt(0) == '3' && (creditCard.charAt(1) == '4' || creditCard.charAt(1) == '7')) {
            return AMERICAN_EXPRESS;
        } else if (creditCard.charAt(0) == '5' && (creditCard.charAt(1) >= '1' && creditCard.charAt(1) <= '5')) {
            return MASTERCARD;
        } else {
            return UNKNOWN;
        }
    }

    /**
     * Returns a stream of all possible credit card number lengths for this CardType.
     * The following streams are returned:
     * <ul>
     *     <li>VISA - [13, 165, 19]</li>
     *     <li>AMERICAN_EXPRESS - [15]</li>
     *     <li>MASTERCARD - [16]</li>
     *     <li>UNKNOWN - [-1]</li>
     * </ul>
     * @return Stream of possible card number lengths
     */
    public IntStream possibleLens() {
        return IntStream.of(this.possibleLens);
    }

    @Override
    public String toString() {
        switch (this) {
            case VISA: return "Visa";
            case MASTERCARD: return "MasterCard";
            case AMERICAN_EXPRESS: return "American Express";
            case UNKNOWN: return "Unknown";
            default: return null; //Cannot be reached
        }
    }
}