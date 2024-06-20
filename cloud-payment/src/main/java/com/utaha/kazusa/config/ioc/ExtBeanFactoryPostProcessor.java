package com.utaha.kazusa.config.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Dept.1
 * BeanDefinition
 * BeanDefinitionMap存储bean的定义信息（key为BeanName，Value为BeanDefinition）
 * BeanFactoryPostProcessor
 * 扩展方法--Bean定义后置增强器（可修改bean的定义信息）
 */
//@Component
public class ExtBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("扩展方法--可进行修改beanDefinition的定义信息");
    }
}