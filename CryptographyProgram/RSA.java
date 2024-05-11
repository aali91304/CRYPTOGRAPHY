import java.math.BigInteger;

public class RSA {
    public static void main(String[] args) {
        BigInteger p = new BigInteger("11");
        BigInteger q = new BigInteger("7");
        BigInteger M = new BigInteger("8");
        BigInteger e = new BigInteger("17");

        // Calculate n = p*q
        BigInteger n = p.multiply(q);

        // Calculate phi(n) = (p-1)*(q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Calculate d = e^-1 mod phi(n)
        BigInteger d = e.modInverse(phi);

        // Encrypt: C = M^e mod n
        BigInteger C = M.modPow(e, n);

        System.out.println("n = " + n);
        System.out.println("phi(n) = " + phi);
        System.out.println("d = " + d);
        System.out.println("C = " + C);
    }
}



//2.RSA PROGRAM

//import java.util.Scanner;
//import java.math.BigInteger;
//
//public class RSA {
//    // Function to compute the greatest common divisor (GCD) of two numbers
//    private static BigInteger gcd(BigInteger a, BigInteger b) {
//        if (b.equals(BigInteger.ZERO))
//            return a;
//        return gcd(b, a.mod(b));
//    }
//
//    // Function to compute modular multiplicative inverse using Extended Euclidean Algorithm
//    private static BigInteger modInverse(BigInteger a, BigInteger m) {
//        BigInteger m0 = m;
//        BigInteger y = BigInteger.ZERO;
//        BigInteger x = BigInteger.ONE;
//
//        if (m.equals(BigInteger.ONE))
//            return BigInteger.ZERO;
//
//        while (a.compareTo(BigInteger.ONE) > 0) {
//            // q is quotient
//            BigInteger q = a.divide(m);
//
//            BigInteger t = m;
//
//            // m is remainder now, process same as Euclid's algorithm
//            m = a.mod(m);
//            a = t;
//            t = y;
//
//            // Update x and y
//            y = x.subtract(q.multiply(y));
//            x = t;
//        }
//
//        // Make x positive
//        if (x.compareTo(BigInteger.ZERO) < 0)
//            x = x.add(m0);
//
//        return x;
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter prime number p: ");
//        BigInteger p = scanner.nextBigInteger();
//
//        System.out.print("Enter prime number q: ");
//        BigInteger q = scanner.nextBigInteger();
//
//        System.out.print("Enter message M: ");
//        BigInteger M = scanner.nextBigInteger();
//
//        // Compute n = p*q
//        BigInteger n = p.multiply(q);
//
//        // Compute φ(n) = (p-1)*(q-1)
//        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
//
//        // Choose e such that 1 < e < φ(n) and gcd(e, φ(n)) = 1
//        BigInteger e = BigInteger.valueOf(2);
//        while (e.compareTo(phi) < 0 && gcd(e, phi).intValue() != 1) {
//            e = e.add(BigInteger.ONE);
//        }
//
//        // Compute d = e^(-1) mod φ(n)
//        BigInteger d = modInverse(e, phi);
//
//        // Compute C = M^e mod n
//        BigInteger C = M.modPow(e, n);
//
//        // Print public and private keys along with encrypted message
//        System.out.println("Public Key (e, n): (" + e + ", " + n + ")");
//        System.out.println("Private Key (d, n): (" + d + ", " + n + ")");
//        System.out.println("Encrypted Message (C): " + C);
//
//        scanner.close();
//    }
//}
