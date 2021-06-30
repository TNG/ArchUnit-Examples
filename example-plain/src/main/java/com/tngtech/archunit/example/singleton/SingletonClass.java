package com.tngtech.archunit.example.singleton;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;

/**
 * @author Per Lundberg
 */
public class SingletonClass {

    private static final Supplier<SingletonClass> INSTANCE = Suppliers.memoize( SingletonClass::new );

    private SingletonClass() {
        // Private constructor to prevent construction
    }

    public void doSomething() {
        throw new UnsupportedOperationException();
    }

    public static SingletonClass getInstance() {
        return INSTANCE.get();
    }
}
