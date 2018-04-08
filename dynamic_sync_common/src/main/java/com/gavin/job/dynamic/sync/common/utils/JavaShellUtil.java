package com.gavin.job.dynamic.sync.common.utils;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * 在java程序中直接调用shell脚本
 * 
 * @author Gavin
 */
public class JavaShellUtil {

	private static Logger logger = Logger.getLogger("Java Shell");

	public static int executeShell(String shellCommand) throws IOException,
			Exception {
		logger.info("Start to execute command: " + shellCommand);
		int success = 0;

		try {
			Process pid = null;
			String[] cmd = { "/bin/bash", "-c", shellCommand };

			// 执行Shell命令
			pid = Runtime.getRuntime().exec(cmd);

			logger.info("Execute command finished: " + shellCommand);

//			if (GeneralUtils.isNotNull(pid)) {
//				pid.waitFor();
//				logger.info("Return value is: " + pid.exitValue());
//				success = pid.exitValue();
//			} else {
//				logger.info("No return process id: " + cmd.toString());
//			}

		} catch (Exception ioe) {
			ioe.printStackTrace();
			logger.info("Exception while execute Shell command: "
					+ ioe.getMessage());
			throw new Exception("Execute ip task error," + ioe.getMessage());
		} finally {
			logger.info("Finish execute and ready to return");
			logger.info("=================================" + "\n");
		}
		return success;
	}

	public static int executeShellV2(String shellCommand) throws IOException,
			Exception {
		logger.info("Start to execute command: " + shellCommand);
		int success = 0;

		try {
			Process pid = null;
			// 执行Shell命令
			pid = Runtime.getRuntime().exec(shellCommand);

			logger.info("Execute command finished: " + shellCommand);

			if (GeneralUtils.isNotNull(pid)) {
				pid.waitFor();
				logger.info("Return value is: " + pid.exitValue());
				success = pid.exitValue();
			} else {
				logger.info("No return process id: " + shellCommand.toString());
			}

		} catch (Exception ioe) {
			ioe.printStackTrace();
			logger.info("Exception while execute Shell command: "
					+ ioe.getMessage());
			throw new Exception("Execute ip task error," + ioe.getMessage());
		} finally {
			logger.info("Finish execute and ready to return");
			logger.info("=================================" + "\n");
		}
		return success;
	}
}