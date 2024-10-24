package intern.customer.agitoo.Common.Utilities;

import java.security.SecureRandom;

public class TicketNumberGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int TICKET_NUMBER_LENGTH = 10; // Rastgele karakter uzunluğu
    private static final SecureRandom random = new SecureRandom ();

    public static String generateRandomPart () {

        StringBuilder sb = new StringBuilder (TICKET_NUMBER_LENGTH);

        for (int i = 0; i < TICKET_NUMBER_LENGTH; i++) {
            int index = random.nextInt (CHARACTERS.length ());
            sb.append (CHARACTERS.charAt (index));
        }
        return sb.toString ();
    }
}