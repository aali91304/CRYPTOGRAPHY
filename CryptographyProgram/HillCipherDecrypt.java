import java.util.*;

public class HillCipherDecrypt {
    private static final int[][] KEY_MATRIX = {{7, 8}, {11, 11}};
    private static final int MOD = 26; // Modulus for alphabet length

    public static void main(String[] args) {
        String ciphertext = "TVNNZJ";
        String key = "HILL";
        String plaintext = decrypt(ciphertext, key);
        System.out.println("Plaintext: " + plaintext);
    }

    // Decrypt the ciphertext
    private static String decrypt(String ciphertext, String key) {
        int[][] keyMatrix = KEY_MATRIX; // Use the predefined key matrix
        int[][] inverseKeyMatrix = calculateInverse(keyMatrix);
        if (inverseKeyMatrix == null) {
            System.out.println("Cannot decrypt with the provided key.");
            return "";
        }

        // Convert the ciphertext characters to numerical values
        int[] ciphertextNums = convertToNumbers(ciphertext);

        // Ensure ciphertext length is even, pad with 'X' if necessary
        if (ciphertextNums.length % 2 != 0) {
            ciphertext += 'X';
            ciphertextNums = convertToNumbers(ciphertext);
        }

        int[] decryptedNums = new int[ciphertextNums.length];

        // Perform matrix multiplication with the inverse key matrix
        for (int i = 0; i < ciphertextNums.length; i += 2) {
            int[] block = Arrays.copyOfRange(ciphertextNums, i, i + 2);
            int[] product = matrixMultiply(inverseKeyMatrix, block);
            for (int j = 0; j < product.length; j++) {
                decryptedNums[i + j] = product[j] % MOD;
            }
        }

        // Convert numerical values to characters
        StringBuilder plaintext = new StringBuilder();
        for (int num : decryptedNums) {
            plaintext.append((char) (num + 'A'));
        }
        return plaintext.toString();
    }

    // Calculate the inverse of the key matrix
    private static int[][] calculateInverse(int[][] matrix) {
        int det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        int detInv = -1;
        for (int i = 0; i < MOD; i++) {
            if ((det * i) % MOD == 1) {
                detInv = i;
                break;
            }
        }
        if (detInv == -1) {
            return null; // Inverse doesn't exist
        }

        int[][] inverse = new int[2][2];
        inverse[0][0] = (matrix[1][1] * detInv) % MOD;
        inverse[0][1] = (-matrix[0][1] * detInv + MOD) % MOD;
        inverse[1][0] = (-matrix[1][0] * detInv + MOD) % MOD;
        inverse[1][1] = (matrix[0][0] * detInv) % MOD;

        return inverse;
    }

    // Convert the ciphertext characters to numerical values
    private static int[] convertToNumbers(String ciphertext) {
        ciphertext = ciphertext.toUpperCase(); // Convert to uppercase
        int[] nums = new int[ciphertext.length()];
        for (int i = 0; i < ciphertext.length(); i++) {
            nums[i] = ciphertext.charAt(i) - 'A';
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
            result[i] = sum % MOD;
        }
        return result;
    }

}
