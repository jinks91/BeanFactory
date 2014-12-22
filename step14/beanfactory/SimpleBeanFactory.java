package com.coupang.c4.step14.beanfactory;

import com.coupang.c4.ResourceUtil;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class SimpleBeanFactory implements BeanFactory {

	private String propertyPath;
    private Map<String, Object> map;

	public SimpleBeanFactory(String propertyPath) throws IOException {
		this.propertyPath = propertyPath;
        map = new HashMap<String, Object>();

        getBeans();
	}

    private void getBeans() throws IOException {
        InputStream inputStream = ResourceUtil.resourceAsInputStream(this.propertyPath);
        String[] lines = ResourceUtil.readFully(inputStream);
        BeanDefinition beanDefinition;

        if(lines != null){
            for(String line : lines){
                String[] temp = line.split("=");
                addNewBean(new BeanDefinition(temp[0], temp[1]));
            }
        }
    }

    /* 클래스로 입력 */
    /* Sample1 instance = simpleBeanFactory.getInstance(Sample1.class); */
    public <T> T getInstance(Class<T> type) {

        String beanName = type.getSimpleName().toLowerCase();

        if(!map.containsKey(beanName)){
            System.out.println("make new Instance<T> : " +type.getName());
            addNewBean(new BeanDefinition(beanName, type.getName()));
        }

        T object = (T) map.get(beanName);

        return object;
    }


    /* beanName으로 입력 */
    /* Object instance2 = simpleBeanFactory.getInstance("sample2"); */
    public Object getInstance(String beanName) {

        if(map.containsKey(beanName)) {
            return map.get(beanName);
        } else {
            return null;
        }
    }

    /* map에 bean 추가 */
    @Override
    public void addNewBean(BeanDefinition beanDefinition) {
        Constructor constructor = null;
        try {
            constructor = Class.forName(beanDefinition.getClassFullName()).getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        constructor.setAccessible(true);

        try {
            map.put(beanDefinition.getBeanName(),constructor.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
