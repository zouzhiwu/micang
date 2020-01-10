package com.demo.test;

public class ClazzDemo {
	public static void main(String[] args) {

        A a = new A();
        B b = new B();
        C c = new C();
        System.out.println(A.class.isAssignableFrom(a.getClass()));
        System.out.println(B.class.isAssignableFrom(b.getClass()));
        System.out.println(A.class.isAssignableFrom(c.getClass()));
    }
}

class A {
}

class B extends A {
}

class C extends B {
}
