package com.github.sevntu.checkstyle.domain;

public class InputMethodCallsInLambda {

    private Runnable r = () -> {dependency();};

    public void m() {
        final Runnable r = () -> dependency();
    }

    public void dependency() {}
}
