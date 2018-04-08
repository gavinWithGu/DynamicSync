package com.gavin.job.dynamic.sync.common.constant;


/**
 * @ClassName Constants
 * @Description 常量定义类
 */

public interface Constants {
	/**
	 * 表示','字符.
	 */
	char CHAR_COMMA = ',';
	/**
	 * 表示'.'字符.
	 */
	char CHAR_DOT = '.';
	/**
	 * 表示'/'字符.
	 */
	char CHAR_FORWARDSLASH = '/';
	/**
	 * 表示'\\'字符.
	 */
	char CHAR_BACKSLASH = '\\';
	/**
	 * 表示'\'字符.
	 */
	char CHAR_POUND = '#';
	/**
	 * 表示'-'字符.
	 */
	char CHAR_DASH = '-';
	/**
	 * 表示'_'字符.
	 */
	char CHAR_UNDERLINE = '_';
	/**
	 * 表示'&'字符.
	 */
	char CHAR_AND = '&';
	/**
	 * 表示'*'字符.
	 */
	char CHAR_STAR = '*';
	/**
	 * 表示'@'字符.
	 */
	char CHAR_AT = '@';
	/**
	 * 表示'$'字符.
	 */
	char CHAR_DOLLAR = '$';
	/**
	 * 表示"$"字符.
	 */
	char CHAR_PERCENT = '%';
	/**
	 * 表示","字符串.
	 */
	String STRING_COMMA = ",";
	/**
	 * 表示"."字符串.
	 */
	String STRING_DOT = ".";
	/**
	 * 表示'/'字符.
	 */
	String STRING_FORWARDSLASH = "/";
	/**
	 * 表示"\"字符.
	 */
	String STRING_BACKSLASH = "\\";
	/**
	 * 表示"#"字符.
	 */
	String STRING_POUND1 = "#";
	String STRING_POUND2 = "##";
	/**
	 * 表示"999999999999"字符.
	 */
	String STRING_ROOTTYPE = "999999999999";
	/**
	 * 表示"-"字符.
	 */
	String STRING_DASH = "-";
	/**
	 * 表示"_"字符.
	 */
	String STRING_UNDERLINE = "_";
	/**
	 * 表示"&"字符.
	 */
	String STRING_AND = "&";
	/**
	 * 表示"*"字符.
	 */
	String STRING_STAR = "*";
	/**
	 * 表示"@"字符.
	 */
	String STRING_AT = "@";
	/**
	 * 表示"$"字符.
	 */
	String STRING_DOLLAR = "$";
	/**
	 * 表示"$"字符.
	 */
	String STRING_PERCENT = "%";
	/**
	 * 表示"/"字符.
	 */
	String STRING_FORWARD_SLASH = "/";
	/**
	 * 表示"\"字符.
	 */
	String STRING_BACK_SLASH = "\\";
	/**
	 * 表示"--"字符.
	 */
	String STRING_DOUBLE_DASH = "--";
	/**
	 * 表示";"字符.
	 */
	String STRING_SEMICOLON = ";";


	/**
	 * 编码方式常量。
	 */
	String ENCODING_GBK = "GBK";
	String ENCODING_UTF8 = "UTF-8";
	/**
	 * 数据库类型常量。
	 */
	String DATABASE_DB2 = "DB2";
	String DATABASE_MSSQL = "MSSQL";
	String DATABASE_SYBASE = "SYBASE";
	String DATABASE_ORACLE = "ORACLE";
	String DATABASE_INFORMIX = "INFORMIX";
	String DATABASE_HIBERNATE = "HIBERNATE";
	String DATABASE_DERBY = "DERBY";
	

	String TOKEN = "token";

	
	public interface RECORD_STATUS {
		byte NOT_YET = 0;
		byte SUCCESS = 1;
	}
	
	
	public interface SERVER_NOTIFY_TYPE {
		String NGINX = "nginx";
	}
	
	public interface SERVIE_LISTEN_TYPE {
		String ZOOKEEPER = "zookeeper";
	}
}
