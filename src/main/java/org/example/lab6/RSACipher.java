package org.example.lab6;

import org.example.lab6.models.PrivateKey;
import org.example.lab6.models.PublicKey;

import java.math.BigInteger;

public final class RSACipher {

    private RSACipher() {
    }

    public static String crypt(String message, PublicKey publicKey) {
        return (new BigInteger(message.getBytes())).modPow(BigInteger.valueOf(publicKey.getE()), BigInteger.valueOf(publicKey.getN())).toString();
    }

    public static String decrypt(String encryptedMessageBytes, PrivateKey privateKey) {
        return new String((new BigInteger(encryptedMessageBytes)).modPow(BigInteger.valueOf(privateKey.getD()), BigInteger.valueOf(privateKey.getN())).toByteArray());
    }
}
