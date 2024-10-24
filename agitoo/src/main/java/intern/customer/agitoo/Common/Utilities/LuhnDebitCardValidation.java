package intern.customer.agitoo.Common.Utilities;

public class LuhnDebitCardValidation {

    public static boolean isValidLuhn (String cardNumber) {

        if (cardNumber == null || cardNumber.length () < 16) {
            return false;
        }

        int sum = 0;
        boolean alternate = false;

        for (int i = cardNumber.length () - 1; i >= 0; i--) {
            char c = cardNumber.charAt (i);
            if (!Character.isDigit (c)) {
                return false;
            }
            int n = Character.getNumericValue (c);

            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
