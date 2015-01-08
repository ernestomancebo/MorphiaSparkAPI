package com.flectosystems.morphiasparkapi;

public class test {

    public static void main(String... args) {
SingleClass instance1 = new SingleClass();
        SingleClass instance2 = SingleClass().;
    }
}

class SingleClass {
    SingleClass instance = null;

    public SingleClass() {
        System.out.print("Instance of SingleClass");
    }

    public SingleClass getInstance() {
        if (null == instance)
            instance = this;
        return instance;
    }

}