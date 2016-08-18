package com.feifanuniv.management.utils;

import static org.junit.Assert.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.feifanuniv.management.entity.XueSheng;
import com.feifanuniv.management.utils.ReflectUtils;

public class ReflectUtilsTest {
	String s = "dada";
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAllFieldName() {
		String[] fileds=ReflectUtils.getAllFieldName(XueSheng.class);
			System.out.println(fileds);
			for (String string : fileds) {
				System.out.println(string);
			}
		
	}
 
	@Test
	public void testGetPropertyDes() {
		int a = 0;
		System.out.println(a);
		
	}

	@Test
	public void testUseFieldGetReadMehtod() {
		fail("Not yet implemented");
	}

}
