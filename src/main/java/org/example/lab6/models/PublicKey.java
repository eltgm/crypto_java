package org.example.lab6.models;

public final class PublicKey {
    private final long n;
    private final long e;
    private final long eulerFunctionValue;

    public PublicKey(long n, long e, long eulerFunctionValue) {
        this.n = n;
        this.e = e;
        this.eulerFunctionValue = eulerFunctionValue;
    }

    public long getN() {
        return n;
    }

    public long getE() {
        return e;
    }

    public long getEulerFunctionValue() {
        return eulerFunctionValue;
    }
}
