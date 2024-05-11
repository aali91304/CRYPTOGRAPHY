public class CaesarCipher {
    // Function to encrypt plaintext using Caesar cipher
    public static String encrypt(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);

            // Encrypt uppercase letters
            if (Character.isUpperCase(ch)) {
                char encryptedChar = (char) ((ch + shift - 'A') % 26 + 'A');
                ciphertext.append(encryptedChar);
            }
            // Encrypt lowercase letters
            else if (Character.isLowerCase(ch)) {
                char encryptedChar = (char) ((ch + shift - 'a') % 26 + 'a');
                ciphertext.append(encryptedChar);
            }
            // Ignore other characters
            else {
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();
    }

    // Function to decrypt ciphertext using Caesar cipher
    public static String decrypt(String ciphertext, int shift) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);

            // Decrypt uppercase letters
            if (Character.isUpperCase(ch)) {
                char decryptedChar = (char) ((ch - shift - 'A' + 26) % 26 + 'A');
                plaintext.append(decryptedChar);
            }
            // Decrypt lowercase letters
            else if (Character.isLowerCase(ch)) {
                char decryptedChar = (char) ((ch - shift - 'a' + 26) % 26 + 'a');
                plaintext.append(decryptedChar);
            }
            // Ignore other characters
            else {
                plaintext.append(ch);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "AKHTAR";
        int shift = 3; // Shift value

        String ciphertext = encrypt(plaintext, shift);
        System.out.println("Encrypttion : ");
        System.out.println("PLAINTEXT: " + plaintext);
        System.out.println("CIPHERTEXT: " + ciphertext);

        String decryptedText = decrypt(ciphertext, shift);
        System.out.println("Decryption : ");
        System.out.println("CIPHERTEXT: " + ciphertext);
        System.out.println("DECRYPTED TEXT: " + decryptedText);
    }
}
