package com.github.sevntu.checkstyle.domain;

public class InputDependenciesOrderInconsistency5 {

    void a1() { }

    void a() {
        a3(); //3
        a2(); //2
    }

    void a2() { }

    void a3() { }
}
