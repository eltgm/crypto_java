package org.example.lab6.models;

public final class PrivateKey {
    private final long n;
    private final long d;

    public PrivateKey(long n, long d) {
        this.n = n;
        this.d = d;
    }

    public long getN() {
        return n;
    }

    public long getD() {
        return d;
    }
}
