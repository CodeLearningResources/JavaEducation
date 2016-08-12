package Inheritance;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by mikim on 2016-07-27.
 */
public class HelloWorld {

    public HelloWorld() {
    }

    public static void printHelloWorld() {
        // Print "Hello, World", using reflection to look up the 'out' field of java.lang.System and
        // Using invoke to call the println method.

        try {
            Class<?> systemClass = System.class;
            Field outField = systemClass.getDeclaredField("out");

            // Type of 'out' field is printStreamClass
            Class<?> printStreamClass = outField.getType();

            // Since method call is of the form: print(String)
            Method printlnMethod = printStreamClass.getDeclaredMethod("println", String.class);

            // println(String) is an instance method. Need to invoke it on an instance ('out' field)
            // 'out' field is static so can pass null to Field#get(object)
            Object object = outField.get(null);
            printlnMethod.invoke(object, "Hello, World");

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloWorld.printHelloWorld();
    }
}
