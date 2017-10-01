import java.util.stream.IntStream;

/**
 * @author 2258082R Stuart Reilly
 */
public class CreditCardChecker {

    public static void main(String[] args) {

        java.util.Scanner stdin = new java.util.Scanner(System.in);
        System.out.println("Enter the credit card number: ");
        String creditCard = stdin.next();

        //Check card type, exiting if invalid
        CardType cardType = CardType.fromString(creditCard);
        if (cardType == CardType.UNKNOWN) {
            System.err.println("Unknown card type");
            System.exit(-2);
        }

        System.out.println("Card type: " + cardType.toString());
        //Check if the length of the credit card number is valid for its card type
        if (cardType.possibleLens().noneMatch(len -> len == creditCard.length())) {
            System.err.println("Invalid length");
            System.exit(-3);
        }

        int sum = 0;
        //Reverse iteration as the check digit uses Luhn Algorithm
        for (int i = creditCard.length()-1; i > -1; i--) {
            char c = creditCard.charAt(i);
            if (Character.isDigit(c)) {
                int num = Character.getNumericValue(c);
                // Check if the current digit is an even distance from the end of the string
                if ((creditCard.length() - i) % 2 == 0) {
                    num *= 2;
                    if (num > 9) {
                        num -= 9;
                    }
                }
                sum += num;
            } else {
                System.err.println("Invalid character: " + c);
                System.exit(-1);
            }
        }

        if (sum % 10 == 0) {
            System.out.println("Card number is VALID");
        } else {
            System.err.println("Card number is INVALID");
        }

        stdin.close();
    }
}

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