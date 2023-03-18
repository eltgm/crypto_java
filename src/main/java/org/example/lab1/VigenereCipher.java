package org.example.lab1;

import org.example.lab1.utils.VigenereCipherUtils;

import static org.example.lab1.utils.VigenereCipherUtils.LATIN_ASCII_END;
import static org.example.lab1.utils.VigenereCipherUtils.LATIN_ASCII_START;

public class VigenereCipher {
    private final int keyDigitsNumber;
    private final char[][] vigenereTable;
    private final byte[] keyArray;

    public VigenereCipher(int key) {
        var keyStringValue = Integer.toString(key);
        this.keyDigitsNumber = keyStringValue.length();
        this.keyArray = VigenereCipherUtils.calculateKeyArray(keyStringValue);
        this.vigenereTable = VigenereCipherUtils.createVigenereTable(keyDigitsNumber, keyArray);
    }

    public String crypt(String message) {
        message = message.replaceAll("[^a-zA-Z]", "").toLowerCase();
        int messageLength = message.length();
        var encryptedMessageBuilder = new StringBuilder(messageLength);

        for (int i = 0; i < messageLength; i += keyDigitsNumber) {
            int substringEndIndex = i + keyDigitsNumber;
            if (substringEndIndex > messageLength) {
                substringEndIndex = messageLength;
            }

            String substring = message.substring(i, substringEndIndex);
            byte[] substringBytes = substring.getBytes();
            for (int j = 0; j < substringBytes.length; j++) {
                int newOffsetIndex = substringBytes[j] - LATIN_ASCII_START;
                encryptedMessageBuilder.append(this.vigenereTable[j][newOffsetIndex]);
            }
        }

        return encryptedMessageBuilder.toString();
    }

    public String decrypt(String encryptedMessage) {
        encryptedMessage = encryptedMessage.toLowerCase();
        int messageLength = encryptedMessage.length();
        var decryptedMessageBuilder = new StringBuilder(messageLength);

        for (int i = 0; i < messageLength; i += keyDigitsNumber) {
            int substringEndIndex = i + keyDigitsNumber;
            if (substringEndIndex > messageLength) {
                substringEndIndex = messageLength;
            }

            String substring = encryptedMessage.substring(i, substringEndIndex);
            byte[] substringBytes = substring.getBytes();
            for (int j = 0; j < substringBytes.length; j++) {
                int decryptSymbolAsciiIndex = substringBytes[j] - keyArray[j];
                if (decryptSymbolAsciiIndex < LATIN_ASCII_START) {
                    decryptSymbolAsciiIndex = LATIN_ASCII_END - (LATIN_ASCII_START - decryptSymbolAsciiIndex) + 1; //calculate ascii index of symbol from original alphabet
                }

                decryptedMessageBuilder.append((char) decryptSymbolAsciiIndex);
            }
        }

        return decryptedMessageBuilder.toString();
    }
}
