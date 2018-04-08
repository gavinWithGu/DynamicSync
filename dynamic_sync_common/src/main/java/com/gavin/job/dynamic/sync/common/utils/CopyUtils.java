package com.gavin.job.dynamic.sync.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

/**
 * 对象属性对拷贝工具类
 * @author gavin
 */
public class CopyUtils
{
	public static void copyProperty(Object destObj, Object sourceObj) throws IllegalAccessException, InvocationTargetException
	{
		ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
		BeanUtils.copyProperties(destObj, sourceObj);
	}
	
	public static void deepCopyList(List dest, List src) throws IllegalAccessException, InvocationTargetException
	{
		for (int i = 0; i < src.size(); i++)
		{
			Object obj = src.get(i);
			if (obj instanceof List)
			{
				dest.add(new ArrayList());
				deepCopyList((List) obj, (List) ((List) dest).get(i));
			} else
			{
				Object destObject = new Object();
				copyProperty(destObject, obj);
				dest.add(destObject);
			}
		}
	}
}
