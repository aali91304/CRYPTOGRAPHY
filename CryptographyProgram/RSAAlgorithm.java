import java.util.Scanner;

public class RSAAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of p: ");
        int p = scanner.nextInt();

        System.out.print("Enter the value of q: ");
        int q = scanner.nextInt();

        System.out.print("Enter the message (M): ");
        int M = scanner.nextInt();

        int n = p * q;
        int phi = (p - 1) * (q - 1);

        int e = calculatePublicKey(phi);
        int d = calculatePrivateKey(phi, e);

        System.out.println("Public Key (e, n): (" + e + ", " + n + ")");
        System.out.println("Private Key (d, n): (" + d + ", " + n + ")");

        int C = encrypt(M, e, n);
        System.out.println("Encrypted message (C): " + C);

        int decryptedMessage = decrypt(C, d, n);
        System.out.println("Decrypted message: " + decryptedMessage);

        scanner.close();
    }

    private static int calculatePublicKey(int phi) {
        int e = 2;
        while (e < phi) {
            if (gcd(e, phi) == 1) {
                break;
            }
            e++;
        }
        return e;
    }

    private static int calculatePrivateKey(int phi, int e) {
        int k = 1;
        while (true) {
            int d = (1 + k * phi) / e;
            if ((d * e) % phi == 1) {
                return d;
            }
            k++;
        }
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private static int encrypt(int M, int e, int n) {
        return (int) (Math.pow(M, e) % n);
    }

    private static int decrypt(int C, int d, int n) {
        return (int) (Math.pow(C, d) % n);
    }
}
