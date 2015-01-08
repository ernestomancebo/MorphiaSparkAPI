package com.flectosystems.morphiasparkapi;

import java.lang.reflect.Method;

public class test {

    public static void main(String... args) {

        // Just testing reflection
        try {
            Class applicationClass = Class.forName("com.flectosystems.morphiasparkapi.SingleClass");
            Method method = applicationClass.getDeclaredMethod("getInstance", new Class[0]);
            System.out.println("Method: " + method);
            SingleClass s = (SingleClass) method.invoke(applicationClass, new Object[0]);
            s.printInstance();

            SingleClass s2 = (SingleClass) method.invoke(null, null);
            s2.printInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class SingleClass {

    static SingleClass instance = new SingleClass();

    private SingleClass() {
    }

    void printInstance() {
        System.out.println("Instance of SingleClass " + this.toString());
    }

    public static SingleClass getInstance() {
//        if (null == instance)
//            instance = this;
        return instance;
    }
}