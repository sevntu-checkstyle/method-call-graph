package com.github.sevntu.checkstyle.domain;

public class InputDependenciesOrderInconsistency1 {

    void a() {
        a1(); //1
        a2(); //2
    }

    void a1() { }

    void a2() { }
}
