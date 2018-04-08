package com.gavin.job.dynamic.sync.common.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;

/**
 * 公共的工具类
 * 
 * @author GuGuangyin
 */
public class GeneralUtils {
	/**
	 * 判断是否为空
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static boolean isNull(Object obj) {
		return (null == obj) ? true : false;
	}

	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	public static boolean isNullOrEmpty(Collection c) {
		if (c == null) {
			return true;
		}
		if (c.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断指定字符串是否为空或零长度
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static boolean isNullOrZeroLenght(String str) {
		return GenericValidator.isBlankOrNull(str);
	}

	/**
	 * 判断指定字符串是否为空或零长度
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static boolean isNotNullOrZeroLength(String str) {
		return !GenericValidator.isBlankOrNull(str);
	}

	/**
	 * 验证输入的邮箱格式是否符合
	 * 
	 * @param email
	 * @return 是否合法
	 */
	public static boolean emailFormat(String email) {
		boolean tag = true;
		final String pattern1 = "(\\w+)([\\-+.][\\w]+)*@(\\w[\\-\\w]*\\.){1,5}([A-Za-z]){2,6}";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/**
	 * 验证输入的账号格式是否符合
	 * 
	 * @param userId
	 * @return 是否合法
	 */
	public static boolean userIdFormat(String userId) {
		boolean tag = true;
		final String pattern1 = "^[a-zA-Z0-9_\\.]+@\\w+\\.\\w{2,4}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(userId);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/**
	 * 验证输入的用户账号格式是否符合
	 * 
	 * @param name
	 * @return 是否合法
	 */
	public static boolean userNameFormat(String name) {
		boolean tag = true;
		final String pattern1 = "^[a-zA-Z\\s\\u4e00-\\u9fa5]+$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(name);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/**
	 * 验证输入的地址格式是否符合
	 * 
	 * @param address
	 * @return 是否合法
	 */
	public static boolean addressFormat(String address) {
		boolean tag = true;
		final String pattern1 = "^[a-zA-Z0-9\\s\\u4e00-\\u9fa5]+$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(address);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/**
	 * 验证密码格式是否符合
	 * 
	 * @param password
	 * @return 是否合法
	 */
	public static boolean passwordFormat(String password) {
		boolean tag = true;
		final String pattern1 = "^[a-zA-Z0-9]{6,12}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(password);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/**
	 * 验证固定电话格式是否符合
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static boolean phoneFormat(String phone) {
		boolean tag = true;
		final String pattern1 = "\\d{3,4}-\\d{8}$|\\d{11}";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(phone);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/**
	 * 验证移动电话格式是否符合
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static boolean mobileFormat(String mobile) {
		boolean tag = true;
		final String pattern1 = "(^0?[1][358][0-9]{9}$)";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(mobile);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/**
	 * 验证身份证格式是否符合
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static boolean idCardFormat(String idCard) {
		boolean tag = true;
		final String pattern1 = "(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(idCard);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/**
	 * 验证营业执照格式是否符合
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static boolean businessNoFormat(String businessNo) {
		// TODO
		return true;
	}

	/**
	 * 把元转换为分
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public int convertYuanToFen(double yuan) {
		return new Double(yuan * 100).intValue();
	}

	/**
	 * 把分转换为元
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public double convertFenToYuan(int fen) {
		return new Integer(fen / 100).doubleValue();
	}

	public static double getFee(Object fee) {
		if (isNotNull(fee)) {
			return Double.valueOf(fee.toString());
		}
		return 0d;
	}

	/**
	 * double保留2位小数
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static double number2Scale(double value) {
		BigDecimal b = new BigDecimal(value);
		// 保留2位小数
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * String 数组转化为Integer数组
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public static Integer[] String2Integer(final String[] strs)
			throws Exception {
		if (strs != null) {
			Integer[] ids = new Integer[strs.length];
			try {
				for (int i = 0; i < strs.length; i++) {
					ids[i] = Integer.parseInt(strs[i]);
				}
				return ids;
			} catch (Exception e) {
				throw e;
			}
		}
		return null;
	}
}
