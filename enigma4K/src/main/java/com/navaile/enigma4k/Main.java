/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma4k;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 *
 * @author vladpaln
 */
public class Main {
	
	// value is not known at compile time, so not inlined
    private static final String FLAG = new String("false");

    static void setFinalStatic(Class clazz, String fieldName, Object newValue)
			throws NoSuchFieldException, IllegalAccessException {

		Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);

		Field modifiers = field.getClass().getDeclaredField("modifiers");
			modifiers.setAccessible(true);
			modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);

		field.set(null, newValue);
    }

    public static void main(String... args) throws Exception {

        System.out.printf("Everything is %s%n", FLAG);
        setFinalStatic(Main.class, "FLAG", "true");
        System.out.printf("Everything is %s%n", FLAG);
    }	
}
