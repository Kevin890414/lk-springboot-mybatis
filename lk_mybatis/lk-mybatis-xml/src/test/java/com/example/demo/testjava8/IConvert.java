package com.example.demo.testjava8;

@FunctionalInterface
public interface IConvert<F, T> {
	T convert(F form);
}