package com.gavin.job.dynamic.sync.common.utils;

/**
 * 常用的数据类型枚举
 *
 */
public enum RegexType {
	
	NONE,
	SPECIALCHAR, //特殊字符
	CHINESE, //中文字符
	EMAIL, //邮件格式
	IP,  //ip格式
	NUMBER, //数字格式
	PHONENUMBER; //电话格式
	
}