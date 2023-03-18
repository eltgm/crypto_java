package org.example.lab1.utils;

import static java.lang.Byte.parseByte;

public final class VigenereCipherUtils {
    public static final byte LATIN_ASCII_START = (byte) 97;
    public static final byte LATIN_ASCII_END = (byte) 122;
    public static final int LATIN_ALPHABET_SIZE = 26;

    private VigenereCipherUtils() {
    }

    public static char[][] createVigenereTable(int keyDigitsNumber, byte[] keyArray) {
        var vigenereTable = new char[keyDigitsNumber][LATIN_ALPHABET_SIZE];

        for (int i = 0; i < keyDigitsNumber; i++) {
            var tempLatinAsciiStart = LATIN_ASCII_START + keyArray[i];
            for (int j = 0; j < LATIN_ALPHABET_SIZE; j++) {
                if (tempLatinAsciiStart > LATIN_ASCII_END) {
                    tempLatinAsciiStart = LATIN_ASCII_START;
                }

                vigenereTable[i][j] = (char) tempLatinAsciiStart++;
            }
        }

        return vigenereTable;
    }

    public static byte[] calculateKeyArray(String keyStringValue) {
        int length = keyStringValue.length();
        byte[] keyArray = new byte[length];

        for (int i = 0; i < length; i++) {
            keyArray[i] = parseByte(keyStringValue.substring(i, i + 1));
        }

        return keyArray;
    }
}
