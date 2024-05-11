import java.util.*;

public class PlayfairCipher {

    private static final char[][] PLAYFAIR_MATRIX = new char[5][5];

    public static void main(String[] args) {
        String plaintext = "COMMUNICATION";
        String keyword = "COMPUTER";
        String ciphertext = encrypt(plaintext, keyword);
        System.out.println("Encryption:");
        System.out.println("Ciphertext: " + ciphertext);

        String decryptedText = decrypt(ciphertext, keyword);
        System.out.println("Decryption : ");
        System.out.println("Decrypted text: " + decryptedText);
    }

    // Generate the Playfair matrix
    private static void generatePlayfairMatrix(String keyword) {
        String key = keyword.replaceAll("\\s", ""); // Remove spaces
        key = key.toUpperCase(); // Convert to uppercase
        key = key.replaceAll("J", "I"); // Replace J with I

        // Construct the matrix
        Set<Character> uniqueChars = new LinkedHashSet<>();
        for (char c : key.toCharArray()) {
            if (!uniqueChars.contains(c)) {
                uniqueChars.add(c);
            }
        }

        int row = 0, col = 0;
        for (char c : uniqueChars) {
            PLAYFAIR_MATRIX[row][col] = c;
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }

        // Fill remaining characters
        char ch = 'A';
        while (row < 5) {
            if (!uniqueChars.contains(ch) && ch != 'J') {
                PLAYFAIR_MATRIX[row][col] = ch;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
            ch++;
        }
    }

    // Encrypt the plaintext
    private static String encrypt(String plaintext, String keyword) {
        generatePlayfairMatrix(keyword);
        plaintext = preparePlainText(plaintext);
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 2) {
            char first = plaintext.charAt(i);
            char second = plaintext.charAt(i + 1);
            int[] coordsFirst = findCoordinates(first);
            int[] coordsSecond = findCoordinates(second);
            if (coordsFirst[0] == coordsSecond[0]) { // Same row
                ciphertext.append(PLAYFAIR_MATRIX[coordsFirst[0]][(coordsFirst[1] + 1) % 5]);
                ciphertext.append(PLAYFAIR_MATRIX[coordsSecond[0]][(coordsSecond[1] + 1) % 5]);
            } else if (coordsFirst[1] == coordsSecond[1]) { // Same column
                ciphertext.append(PLAYFAIR_MATRIX[(coordsFirst[0] + 1) % 5][coordsFirst[1]]);
                ciphertext.append(PLAYFAIR_MATRIX[(coordsSecond[0] + 1) % 5][coordsSecond[1]]);
            } else { // Forming a rectangle
                ciphertext.append(PLAYFAIR_MATRIX[coordsFirst[0]][coordsSecond[1]]);
                ciphertext.append(PLAYFAIR_MATRIX[coordsSecond[0]][coordsFirst[1]]);
            }
        }
        return ciphertext.toString();
    }

    // Decrypt the ciphertext
    private static String decrypt(String ciphertext, String keyword) {
        generatePlayfairMatrix(keyword);
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 2) {
            char first = ciphertext.charAt(i);
            char second = ciphertext.charAt(i + 1);
            int[] coordsFirst = findCoordinates(first);
            int[] coordsSecond = findCoordinates(second);
            if (coordsFirst[0] == coordsSecond[0]) { // Same row
                plaintext.append(PLAYFAIR_MATRIX[coordsFirst[0]][(coordsFirst[1] + 4) % 5]);
                plaintext.append(PLAYFAIR_MATRIX[coordsSecond[0]][(coordsSecond[1] + 4) % 5]);
            } else if (coordsFirst[1] == coordsSecond[1]) { // Same column
                plaintext.append(PLAYFAIR_MATRIX[(coordsFirst[0] + 4) % 5][coordsFirst[1]]);
                plaintext.append(PLAYFAIR_MATRIX[(coordsSecond[0] + 4) % 5][coordsSecond[1]]);
            } else { // Forming a rectangle
                plaintext.append(PLAYFAIR_MATRIX[coordsFirst[0]][coordsSecond[1]]);
                plaintext.append(PLAYFAIR_MATRIX[coordsSecond[0]][coordsFirst[1]]);
            }
        }
        return plaintext.toString();
    }

    // Prepare the plaintext by removing non-alphabetic characters and adjusting length
    private static String preparePlainText(String plaintext) {
        plaintext = plaintext.toUpperCase().replaceAll("\\s", ""); // Convert to uppercase and remove spaces
        StringBuilder preparedText = new StringBuilder(plaintext);
        for (int i = 0; i < preparedText.length(); i += 2) {
            if (i + 1 == preparedText.length()) {
                preparedText.append('X');
            } else if (preparedText.charAt(i) == preparedText.charAt(i + 1)) {
                preparedText.insert(i + 1, 'X');
            }
        }
        return preparedText.toString();
    }

    // Find coordinates of a character in the Playfair matrix
    private static int[] findCoordinates(char ch) {
        int[] coords = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (PLAYFAIR_MATRIX[i][j] == ch) {
                    coords[0] = i;
                    coords[1] = j;
                    return coords;
                }
            }
        }
        return coords;
    }
}
