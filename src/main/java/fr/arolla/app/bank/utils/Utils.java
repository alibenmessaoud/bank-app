package fr.arolla.app.bank.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Random;

public class Utils {

    public static final String RC = "\n";

    public static String generateBankNumber(){
        Random rand = new Random();
        StringBuilder result = new StringBuilder(Locale.getDefault().getCountry());
        for (int i = 0; i < 14; i++)
        {
            int n = rand.nextInt(10);
            result.append(Integer.toString(n));
        }
        return result.toString();
    }

    public static String getToken() {
        String charset = "abcdefghijkmnopqrstuvwxyz1234567890";
        StringBuilder token = new StringBuilder();
        for (int a = 1; a <= 8; a++) {
            token.append(charset.charAt(new Random().nextInt(charset.length())));
        }
        return token.toString();
    }

    public static String shortDate(Instant instant){
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    private Utils() { throw new IllegalStateException("Utility class"); }
}
