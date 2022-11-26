import java.util.ArrayList;
import java.util.List;

public class VigenereCypher {

    public String encrypt(String plainText, String key) {
        String keySameSize = generateKeySameSize(plainText, key);
        List<Integer> asciiPlainText = convertStringToAscii(plainText);
        List<Integer> asciiKey = convertStringToAscii(keySameSize);

        String resultString = "";
        for (int i = 0; i < asciiPlainText.size(); i++) {
            // Getting the new value and converting back to char
            // ASCII table contains 95 valid chars
            // Starting on 32 to 126

            // 32 value is the "zero", so we reduce the value to go to the first position
            // We use the modulo 95 to restart the calculation if it exceeds the limit
            // Then we add 32 to go to the first value again, since this is the first valid char on the ascii table
            int newCharAscii = (asciiPlainText.get(i) - 32 + asciiKey.get(i) - 32) % 95 + 32;
            char newChar = (char) newCharAscii;

            resultString += newChar;
        }

        return resultString;
    }

    public String decrypt(String encrypted, String key) {
        String keySameSize = generateKeySameSize(encrypted, key);
        List<Integer> asciiEncrypted = convertStringToAscii(encrypted);
        List<Integer> asciiKey = convertStringToAscii(keySameSize);

        String resultString = "";
        for (int i = 0; i < asciiEncrypted.size(); i++) {
            // Adding 95 to the subtraction result to get the valid range on the ascii table
            // Modulo 95 is calculated from the sum result to not exceed the limit
            // 32 is added to get the correct position from the valid chars on the ascii table
            int newCharAscii = ((asciiEncrypted.get(i) - asciiKey.get(i)) + 95) % 95 + 32;
            char newChar = (char) newCharAscii;

            resultString += newChar;
        }

        return resultString;
    }

    private List<Integer> convertStringToAscii(String givenString) {
        List<Integer> asciiChars = new ArrayList<>();
        for (char ch : givenString.toCharArray()) {
            int asciiValue = (int) ch;
            asciiChars.add(asciiValue);
        }

        return asciiChars;
    }

    private String generateKeySameSize(String text, String key) {
        int counter = 0;
        String resultKey = "";
        while (counter < text.length()) {
            for (char c : key.toCharArray()) {
                resultKey += c;
                counter++;

                // If the counter reaches the message/plain text length
                // it means the new key already have the same size
                if (counter >= text.length()) break;
            }
        }

        return resultKey;
    }


}
