package com.coupang.c4.step14.beanfactory;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface BeanFactory extends BeanRegistry {
	<T> T getInstance(Class<T> type) throws NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException;
	Object getInstance(String beanName) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
