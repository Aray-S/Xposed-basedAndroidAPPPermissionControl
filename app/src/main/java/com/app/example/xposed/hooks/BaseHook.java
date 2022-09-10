package com.app.example.xposed.hooks;

public abstract class BaseHook {
    protected static ClassLoader classLoader;


    protected static Class<?> getClassForName(String name) throws ClassNotFoundException {
        return Class.forName(name, false, classLoader);
    }

    protected static Class<?> getClassForNameWithField(String name, String field) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return getClassForName(name).getField(field).get(null).getClass();
    }

}
