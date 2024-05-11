import java.util.*;

public class HillCipher {

    private static final int[][] KEY_MATRIX = {{7, 8}, {11, 11}};
    private static final int MOD = 26; // Modulus for alphabet length

    public static void main(String[] args) {
        String plaintext = "BINARY";
        String key = "HILL";
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);
    }

    // Encrypt the plaintext
    private static String encrypt(String plaintext, String key) {
        int[][] keyMatrix = KEY_MATRIX; // Use the predefined key matrix
        int[] plaintextNums = convertToNumbers(plaintext);
        int[] encryptedNums = new int[plaintextNums.length];

        // Perform matrix multiplication
        for (int i = 0; i < plaintextNums.length; i += 2) {
            int[] block = Arrays.copyOfRange(plaintextNums, i, Math.min(i + 2, plaintextNums.length));
            int[] product = matrixMultiply(keyMatrix, block);
            for (int j = 0; j < product.length; j++) {
                encryptedNums[i + j] = product[j] % MOD;
            }
        }

        // Convert numerical values to characters
        StringBuilder ciphertext = new StringBuilder();
        for (int num : encryptedNums) {
            ciphertext.append((char) (num + 'A'));
        }
        return ciphertext.toString();
    }

    // Convert the plaintext characters to numerical values
    private static int[] convertToNumbers(String plaintext) {
        plaintext = plaintext.toUpperCase(); // Convert to uppercase
        plaintext = plaintext.replaceAll("\\s", ""); // Remove spaces
        if (plaintext.length() % 2 != 0) {
            // Padding if necessary
            int padding = 2 - (plaintext.length() % 2);
            plaintext += "X".repeat(padding);
        }
        int[] nums = new int[plaintext.length()];
        for (int i = 0; i < plaintext.length(); i++) {
            nums[i] = plaintext.charAt(i) - 'A';
        }
        return nums;
    }

    // Perform matrix multiplication
    private static int[] matrixMultiply(int[][] matrix, int[] vector) {
        int[] result = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j] * vector[j];
            }
            result[i] = sum;
        }
        return result;
    }
}

