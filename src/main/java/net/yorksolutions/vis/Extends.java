package net.yorksolutions.vis;

import net.yorksolutions.javamvn.HelloWorld;

public class Extends extends HelloWorld {
    public void test() {
       // when you extend a class - create an object that is a superset
        // has all the stuff HelloWorld has plus anything you have created for this new class
        // so if i create a new HelloWorld object here cannot access its private variable
        count3 = 5;

        Extends e = new Extends();
        e.test();
        e.count1 =0;
        e.myFirstMethod();

        count1 = 0;
    }
}
