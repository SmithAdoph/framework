package com.adoph.framework.test;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/3/20
 */
public class Vehicle {

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();

        B b = new B();
        C c = new C();
        D d = new D();

        a1.print(b);//A and A
        a1.print(c);//A and A
        a1.print(d);//A and D

        a2.print(c);//B and A
        a2.print(c);//B and A
        a2.print(d);//A and D

        b.print(b);//B and B
        b.print(c);//B and B
        b.print(d);//A and D

        d.print(b);//B and B
        d.print(c);//B and B
        d.print(d);//D and D  //(1)A and A a1.print(c);//(2)A and A a1.print(d);//(3)A and D a2.print(c);//(4)B and A a2.print(c);//(5)B and A a2.print(d);//(6)A and D b.print(b);//(7)B and B b.print(c);//(8)B and B b.print(d);//(9)A and D d.print(b);//(10)B and B d.print(c);//(11)B and B d.print(d);//(12)D and D

    }

}

class A {
    public void print(A a) {
        System.out.println("A and A");
    }

    public void print(D d) {
        System.out.println("A and D");
    }
}

class B extends A {
    public void print(B b) {
        System.out.println("B and B");
    }

    public void print(A a) {
        System.out.println("B and A");
    } /*//继承的 public void print(D d){ System.out.println("A and D"); }*/
}

class C extends B {
    public void print(C c) {
        System.out.println("C and C");
    }

    public void print(A a) {
        System.out.println("C and A");
    } /*//继承的 public void print(D d){ System.out.println("A and D"); } public void print(B b){ System.out.println("B and B"); }*/
}

class D extends B {
    public void print(D a) {
        System.out.println("D and D");
    }

    public void print(A a) {
        System.out.println("D and A");
    } /*//继承的 public void print(B b){ System.out.println("B and B"); }*/
}



