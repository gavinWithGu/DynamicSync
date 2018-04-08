package com.gavin.job.dynamic.sync.common.utils.log;

import com.gavin.job.dynamic.sync.common.utils.GeneralUtils;


/**
 * 日志工具类LogUtils
 * 
 * @author gavin
 * 
 */
public class LogUtils {
	
	public final static String MODULE_SERVICE = "Service";
	
	public final static String MODULE_CONTROLLER = "Controller";
	
	public final static String MODULE_COMMON = "Common";
	
	public final static String MODULE_WEICHAT = "wechat";
	public final static String MODULE_PORTAL = "portal";
	
	public final static String MODULE_NOTIFY = "Notify";
	
	
	// Log实现类
	private static Log serviceLogger;
	private static Log systemLogger;

	private static LogUtils instance = new LogUtils();

	public static LogUtils getInstance() {
		return instance;
	}

	static {
		systemLogger = new Log4jImpl("system");
		serviceLogger = new Log4jImpl("service");
	}

	/**
	 * 记录业务的debug日志
	 */
	public void debugService(String module, String msg, Object... args) {
		serviceLogger.debug(this.buildMsg(module, msg, args));
	}

	/**
	 * 记录业务的info日志
	 */
	public void infoService(String module, String msg, Object... args) {
		serviceLogger.info(this.buildMsg(module, msg, args));
	}

	/**
	 * 记录业务的错误日志
	 */
	public void errorService(String module, String msg, Object... args) {
		serviceLogger.error(this.buildMsg(module, msg, args));
	}

	/**
	 * 记录业务错误日志
	 */
	public void errorService(String module, Exception ex, Object... args) {
		StringBuilder logMsgBuffer = new StringBuilder(this.buildMsg(module,
				ex.getMessage(), args));

		logMsgBuffer.append(ex.getClass().getName());

		serviceLogger.error(logMsgBuffer.toString());
	}

	/**
	 * 记录系统debug日志
	 */
	public void debugSystem(String module, String msg, Object... args) {
		systemLogger.debug(this.buildMsg(module, msg, args));
	}

	/**
	 * 记录系统info日志
	 */
	public void infoSystem(String module, String msg, Object... args) {
		systemLogger.info(this.buildMsg(module, msg, args));
	}

	/**
	 * 记录系统error日志
	 */
	public void errorSystem(String module, String msg, Object... args) {
		systemLogger.error(this.buildMsg(module, msg, args));
	}

	/**
	 * 记录系统error日志
	 */
	public void errorSystem(String module, Throwable t, Object... args) {
		systemLogger.error(this.buildMsg(module, t.getMessage(), args), t);
	}

	/**
	 * 同时记录业务和系统错误日志
	 */
	public void errorServiceSystem(String module, String msg, Exception ex,
			Object... args) {
		errorService(module, msg, args);

		if (ex == null) {
			this.errorSystem(module, msg, args);
		} else {
			this.errorSystem(module, ex, args);
		}
	}

	/**
	 * 组装日志消息体
	 * 
	 * @param module
	 * @param msg
	 * @param args
	 * @return
	 */
	private String buildMsg(String module, String msg, Object... args) {
		StringBuilder logMsgBuffer = new StringBuilder();
		logMsgBuffer.append("|");
		logMsgBuffer.append(module);
		logMsgBuffer.append("|");
		logMsgBuffer.append(msg);

		if (GeneralUtils.isNotNull(args)) {
			logMsgBuffer.append("|arguments:");
			for (Object arg : args) {
				logMsgBuffer.append(arg);
				logMsgBuffer.append(",");
			}
		}
		return logMsgBuffer.toString();
	}

}