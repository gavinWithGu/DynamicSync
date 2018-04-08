package com.gavin.job.dynamic.sync.common.utils.log;

/**
 */
public interface Log {
	boolean isDebugEnabled();

	boolean isInfoEnabled();

	public void debug(String msg);

	public void debug(String msg, Throwable t);

	public void debug(String msg, Throwable t, Object[] args);

	public void error(String msg);

	public void error(String msg, Throwable t);

	public void error(String msg, Throwable t, Object[] args);

	public void info(String msg);

	public void info(String msg, Throwable t);

	public void info(String msg, Throwable t, Object[] args);
}
