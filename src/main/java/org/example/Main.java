package org.example;

import org.example.lab1.VigenereCipher;
import org.example.lab6.RSACipher;
import org.example.lab6.models.PrivateKey;
import org.example.lab6.models.PublicKey;
import org.example.lab6.utils.RSAUtils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var labChooserScanner = new Scanner(System.in);
        System.out.println("Enter lab number - ");
        var labNumber = labChooserScanner.nextInt();

        if (labNumber == 1) {
            executeLab1();
        } else if (labNumber == 6) {
            executeLab6();
        }
    }

    private static void executeLab1() {
        var scanner = new Scanner(System.in);
        System.out.println("************************************************\n" +
                "************************************************\n" +
                "******NOTE: this program will not work with:****\n" +
                "1. Cyrillic symbols*****************************\n" +
                "2. Special symbols (whitespaces, dot, comma etc)\n\n" +
                "All messages and keys transform to lower case" +
                "************************************************\n" +
                "************************************************\n\n\n");

        int key = 0;
        boolean isKeyRead = false;
        while (!isKeyRead) {
            System.out.println("Enter key (only number) - ");
            try {
                key = Integer.parseInt(scanner.nextLine());
                isKeyRead = true;
            } catch (NumberFormatException ex) {
                System.err.println("Incorrect key. Enter correct key\n");
            }
        }
        var vigenereCipher = new VigenereCipher(key);

        System.out.println("Enter message - ");
        String encryptedMessage = scanner.nextLine();

        String crypt = vigenereCipher.crypt(encryptedMessage);
        System.out.println("Crypted message - " + crypt);
        scanner.close();

        System.out.println("Decrypted message is - " + vigenereCipher.decrypt(crypt));
    }

    private static void executeLab6() {
        var r = new SecureRandom();
        var p = new BigInteger(24, 100, r); // certainty - точность проверки числа на простоту.
        // Значение 100 гарантирует нам что полученное число точно будет простым
        var q = new BigInteger(24, 100, r);
        PublicKey publicKey = RSAUtils.createPublicKey(p.longValue(), q.longValue());
        PrivateKey privateKey = RSAUtils.createPrivateKey(publicKey);

        System.out.println("Enter message(message cannot be larger, than 6 symbols) - ");
        var messageScanner = new Scanner(System.in);

        String message = messageScanner.nextLine();
        System.out.println("Plaintext before cipher: " + message);
        String crypt = RSACipher.crypt(message, publicKey);

        System.out.println("Ciphertext: " + crypt);

        String decrypt = RSACipher.decrypt(crypt, privateKey);
        System.out.println("Plaintext after decrypt: " + decrypt);
    }
}