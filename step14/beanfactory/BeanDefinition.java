package com.coupang.c4.step14.beanfactory;

public class BeanDefinition {
	private String beanName;
	private String classFullName;
	private Scope scope = Scope.SINGLETON;

    public BeanDefinition() {
    }

    public BeanDefinition(String beanName, String classFullName) {
        this.beanName = beanName;
        this.classFullName = classFullName;
    }

    public BeanDefinition(String beanName, String classFullName, Scope scope) {
        this.beanName = beanName;
        this.classFullName = classFullName;
        this.scope = scope;
    }

    public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getClassFullName() {
		return classFullName;
	}

	public void setClassFullName(String classFullName) {
		this.classFullName = classFullName;
	}
}
