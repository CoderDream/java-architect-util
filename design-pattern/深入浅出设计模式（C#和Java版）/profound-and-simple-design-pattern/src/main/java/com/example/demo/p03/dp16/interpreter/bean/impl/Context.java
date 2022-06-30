package com.example.demo.p03.dp16.interpreter.bean.impl;


import java.util.StringTokenizer;

public class Context {
    private final StringTokenizer tokenizer;
    private String currentToken;

    public Context(String text) {
        tokenizer = new StringTokenizer(text);
        nextToken();
    }

    public String nextToken() {
        if (tokenizer.hasMoreTokens()) {
            currentToken = tokenizer.nextToken();
        } else {
            currentToken = null;
        }
        return currentToken;
    }

    public String currentToken() {
        return currentToken;
    }

    public void skipToken(String token) {
        if (!token.equals(currentToken)) {
            System.err.println("Warning: " + token + " is expected, but " +
                    currentToken + " is found.");
        }
        nextToken();
    }

    public int currentNumber() {
        int number = 0;
        try {
            number = Integer.parseInt(currentToken);
        } catch (NumberFormatException e) {
            System.err.println("Warning: " + e);
        }
        return number;
    }
}
