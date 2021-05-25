package com.gavin.job.dynamic.sync.common.messagecode;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.gavin.job.dynamic.sync.common.utils.GeneralUtils;

public class MsgDescription {
	private static final Map<Integer, String> code2KeyMap = new HashMap<Integer, String>();

	private static final Logger logger = Logger.getLogger(MsgDescription.class);

	private static final Properties msgProperties = new Properties();

	static {
		Object o = new MsgCode();
		Field[] fields = o.getClass().getFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				String fieldName = new String();
				fieldName = fields[i].getName().replace("_", ".").toLowerCase();
				code2KeyMap.put((Integer) fields[i].get(o), fieldName);
			}
		} catch (Exception e) {
			logger.error("MsgDescription load msgCode error", e);
		}
	}

	static {
		InputStream in = null;
		try {
			in = MsgDescription.class.getClassLoader().getResourceAsStream("messages/messages.properties");
			msgProperties.load(in);
		} catch (Exception e) {
			logger.error("Load messages.properties error", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("Close inputStream of message.properties error", e);
				}
			}
		}
	}

	public static String getMsgDesc(int code, Object... params) {
		String result = "Error: Unknown message";
		String key = code2KeyMap.get(code);
		if (!GeneralUtils.isNullOrZeroLenght(key)) {
			String msg = msgProperties.getProperty(key);
			if (!GeneralUtils.isNullOrZeroLenght(msg)) {
				result = MessageFormat.format(msg, params);
			} else {
				result = key;
			}
		}
		return result;
	}

	public static String getMsgDesc(String code) {
		String result = "Error: Unknown message";
		String key = code2KeyMap.get(code);
		if (!GeneralUtils.isNullOrZeroLenght(key)) {
			result = msgProperties.getProperty(key);
		}
		return result;
	}

	public static String getMsgByKey(String key) {
		return msgProperties.getProperty(key);
	}
}
