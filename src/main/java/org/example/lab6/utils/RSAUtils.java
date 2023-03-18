package org.example.lab6.utils;

import org.example.lab6.models.PrivateKey;
import org.example.lab6.models.PublicKey;

public final class RSAUtils {
    private RSAUtils() {
    }

    public static PublicKey createPublicKey(long p, long q) {
        long n = p * q;
        long eulerFunctionValue = (p - 1) * (q - 1);
        long e = 3;

        while (findGCD(eulerFunctionValue, e) > 1) {
            e += 2;
        }

        return new PublicKey(n, e, eulerFunctionValue);
    }

    public static PrivateKey createPrivateKey(PublicKey publicKey) {
        return new PrivateKey(publicKey.getN(), modInverse(publicKey.getE(), publicKey.getEulerFunctionValue()));
    }

    public static long findGCD(long firstValue, long secondValue) {
        return secondValue == 0 ? Math.abs(firstValue) : findGCD(secondValue, firstValue % secondValue);
    }

    /**
     * Trying to find t from at ≡ 1 mod n
     * check
     * https://ru.wikipedia.org/wiki/%D0%A0%D0%B0%D1%81%D1%88%D0%B8%D1%80%D0%B5%D0%BD%D0%BD%D1%8B%D0%B9_%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%95%D0%B2%D0%BA%D0%BB%D0%B8%D0%B4%D0%B0
     *
     * @param e                  coefficient
     * @param eulerFunctionValue modulus
     * @return second coefficient
     */
    public static long modInverse(long e, long eulerFunctionValue) {
        long m0 = eulerFunctionValue;
        long y = 0, x = 1;

        if (eulerFunctionValue == 1)
            return 0;

        while (e > 1) {
            // q is quotient
            long q = e / eulerFunctionValue;

            long t = eulerFunctionValue;

            // m is remainder now, process
            // same as Euclid's algo
            eulerFunctionValue = e % eulerFunctionValue;
            e = t;
            t = y;

            // Update x and y
            y = x - q * y;
            x = t;
        }

        // Make x positive
        if (x < 0)
            x += m0;

        return x;
    }
}
