package com.feifanuniv.management.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {

	public static  String[] getAllFieldName(Class<?> entityType) {
		Field[] field = entityType.getDeclaredFields();
		String[] fields= new String[field.length];
		for (int i = 0; i < fields.length; i++) {
			fields[i]=field[i].getName();
		}
		
		return fields;
	}
	public static PropertyDescriptor[] getPropertyDes(Class<?> courseType) throws IntrospectionException{
		BeanInfo beanInfo = Introspector.getBeanInfo(courseType);
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
		return descriptors;
	}
	
	public static Method useFieldGetReadMehtod(PropertyDescriptor[] descriptors,String field) throws Exception{

		for (PropertyDescriptor propertyDescriptor : descriptors) {
			if(propertyDescriptor.getName().equals(field)){
				return propertyDescriptor.getReadMethod();
			}
		}
		
		return null;
	}

}
