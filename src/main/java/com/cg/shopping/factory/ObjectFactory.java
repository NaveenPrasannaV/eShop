package com.cg.shopping.factory;

import java.util.function.Supplier;

public class ObjectFactory {

	/*
	 * This method will create an object that requested by other class
	 */
	public static <T> T createObject(Supplier<T> supplier) {
		return supplier.get();
	}

}
