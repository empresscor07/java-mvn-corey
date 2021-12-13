package net.yorksolutions.javamvn;
// Javascript dominant structure is the function
// Java dominant organizational structure is object-oriented
// Object - collection of variables and methods (functions) that we can easily create/copy and manipulate as a group
public class HelloWorld {

    // variable
    private int count = 0;
    int count2 = 0;
    public int count1 = 0;
    protected int count3 = 0;

    //method
    // visibility - what is allowed to see this field (variable or method)
    // public - anything
    // private - only the class
    // package (default) - private plus any other class in the same package
    // protected - private plus any class that extends this class

    public void myFirstMethod() {
        System.out.println("count = " + count);
    }

    public static int sum(int k) {
        if (k > 0) {
            return k + sum(k - 1);
        } else {
            return 0;
        }
    }
    //when running, this program follows these steps with recursion
    //10 + sum(9)
    //10 + ( 9 + sum(8) )
    //10 + ( 9 + ( 8 + sum(7) ) )
    //...
    //10 + 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 + sum(0)
    //10 + 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 + 0

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        int result = sum(10);
        System.out.println(result);

        // Declared a variable that can hold HelloWorld object
        // Created a HelloWorld object
        // Assigned the variable to that object
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.count = 4;
        helloWorld.count1 =10;
        helloWorld.myFirstMethod();

        HelloWorld helloWorld1 = new HelloWorld();
        helloWorld1.count1 = 6;
        helloWorld.myFirstMethod();

        // primitives
        int i; // -2bil to 2bil
        short s; // -2^15 to 2^15
        long l; // -2^63 to 2^63
        boolean b; // true or false
        float f; // 32 bit decimal number
        double d; //64 bit decimal number
        char c;
        byte by;
        Object obj; //references - can point to an object of that type or null


    }
}
