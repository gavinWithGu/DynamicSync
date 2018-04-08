package com.gavin.job.dynamic.sync.common.utils;

import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

public class TokenUtils {

	// token密钥
	public static final String pattern = "uit54321";
	
	
	public static void main(String args[]){
		String ds="getMyPatientList.do,getOrderTotalCount.do,changeOrderDetailStatus.do,updatePersonalInfo.do,feedback.do,getMyOrderStatistics.do,incomeDetail.do,getMyOrderList.do";
		System.out.println(ds.contains("changeOrderDetailStatus.do"));
	}
	
	/**
	 * 生成用户访问令牌
	 * @param userId
	 * 		访问日志分析需要
	 * @return
	 */
	public static String createToken(Integer userId) {
		String token = userId + ";" + getUUID();
		byte[] encrypt = XXTEA.encrypt(token.getBytes(), pattern.getBytes());
		return StringUtils.join(new Object[] { new String(Hex.encodeHex(encrypt)) }, "$");
	}
	
	/**
	 * 通过token 得到账号ID
	 * @param token
	 * @return
	 */
	public static Integer getUserIdFromToken(String token) {
		if(token == null || token.length() ==0){
			return null;
		}
		byte[] ms = null;
		try {
			ms = Hex.decodeHex(token.toCharArray());
		} catch (DecoderException e) {
			
		}
		byte[] bsource = XXTEA.decrypt(ms, pattern.getBytes());
		return Integer.parseInt(new String(bsource).split(";")[0]);
	}
	
	/**
	 * 产生一个全局唯一的序列标
	 * 
	 * @return 年（2位）＋月（2位）＋日（2痊）＋时（2位）＋分（2位）＋秒（2位）＋毫秒（3位）＋UUID（随机15位）
	 * @since 0.1
	 */
	public static String getUUID() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendar.getTime());
		StringBuffer u1 = new StringBuffer(formatNumber(calendar.get(Calendar.YEAR), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.MONTH) + 1, 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.DAY_OF_MONTH), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.HOUR_OF_DAY), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.MINUTE), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.SECOND), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.MILLISECOND), 3, '0'));
		String u2 = UUID.randomUUID().toString();
		u2 = u2.replaceAll("-", "");
		return u1.toString() + u2.substring(15);
	}
	
	/**
	 * 返回整形数的指定长度，指定填充因子的字符串
	 * 
	 * @param number
	 *            ,指定整形数
	 * @param destLength指定长度
	 * @param paddedChar指定填充因子
	 * @return 如果该整形数长度大于指定长度。截到一部分，如果小于指定长度，左填充指定填充因子
	 */
	public static String formatNumber(int number, int destLength, char paddedChar) {
		String oldString = String.valueOf(number);
		StringBuffer newString = new StringBuffer("");
		int oldLength = oldString.length();
		if (oldLength > destLength) {
			newString.append(oldString.substring(oldLength - destLength));
		} else if (oldLength == destLength) {
			newString.append(oldString);
		} else {
			for (int i = 0; i < destLength - oldLength; i++) {
				newString.append(paddedChar);
			}
			newString.append(oldString);
		}
		return newString.toString();
	}
	
}
