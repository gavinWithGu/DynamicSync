/*    
 * Copyright (c) 2011 CoolCloudz, Inc.
 * All right reserved.
 *
 * �ļ���      Log4jImpl.java
 * ����:     Jacky Wang
 * �������ڣ� 2011-7-19 ����04:03:20
 * �汾��           
 *
 */
package com.gavin.job.dynamic.sync.common.utils.log;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jImpl implements Log
{
	private Logger	logger;

	public Log4jImpl(String name)
	{
		logger = Logger.getLogger(name);
	}

	public Log4jImpl(Class clazz)
	{
		logger = Logger.getLogger(clazz);
	}

	public Log4jImpl(Class clazz, Properties prop)
	{
		PropertyConfigurator.configure(prop);
		logger = Logger.getLogger(clazz);
	}

	public Log4jImpl(String name, Properties prop)
	{
		PropertyConfigurator.configure(prop);
		logger = Logger.getLogger(name);
	}

	public Log4jImpl(Class clazz, String prop)
	{
		PropertyConfigurator.configure(prop);
		logger = Logger.getLogger(clazz);
	}

	public Log4jImpl(String name, String prop)
	{
		PropertyConfigurator.configure(prop);
		logger = Logger.getLogger(name);
	}

	public void debug(String msg)
	{
		if (logger != null && logger.isDebugEnabled())
		{
			logger.debug(msg);
		}
	}

	public void debug(String msg, Throwable t)
	{
		if (logger != null && logger.isDebugEnabled())
		{
			logger.debug(msg, t);
		}
	}

	public void debug(String msg, Throwable t, Object[] args)
	{
		if (logger != null && logger.isDebugEnabled())
		{
			String argMsg = genArgMsg(args);
			msg = msg + "\n";
			msg = msg + "args: \n";
			msg = msg + argMsg;
			logger.debug(msg, t);
		}
	}

	public void error(String msg)
	{
		if (logger != null)
		{
			logger.error(msg);
		}
	}

	public void error(String msg, Throwable t)
	{
		if (logger != null)
		{
			logger.error(msg, t);
		}

	}

	public void error(String msg, Throwable t, Object[] args)
	{
		if (logger != null)
		{
			String argMsg = genArgMsg(args);
			msg = msg + "\n";
			msg = msg + "args: \n";
			msg = msg + argMsg;
			logger.error(msg, t);
		}
	}

	public void info(String msg)
	{
		if (logger != null && logger.isInfoEnabled())
		{
			logger.info(msg);
		}

	}

	public void info(String msg, Throwable t)
	{
		if (logger != null && logger.isInfoEnabled())
		{
			logger.info(msg, t);
		}

	}

	public void info(String msg, Throwable t, Object[] args)
	{
		if (logger != null && logger.isInfoEnabled())
		{
			String argMsg = genArgMsg(args);
			msg = msg + "\n";
			msg = msg + "args: \n";
			msg = msg + argMsg;
			logger.info(msg, t);
		}
	}

	private String genArgMsg(Object[] args)
	{
		if (args == null)
		{
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		for (Object arg : args)
		{
			if (arg != null)
			{
				String argMsg = arg.toString();
				if (arg.getClass() == Integer.class)
				{
					argMsg = Integer.toString(((Integer) arg).intValue());
				} else if (arg.getClass() == Integer[].class)
				{
					StringBuffer argBuf = new StringBuffer("[");
					Integer[] ins = (Integer[]) arg;
					for (int i = 0; i < ins.length; i++)
					{
						argBuf.append(Integer.toString(((Integer) ins[i]).intValue()));
						if (i != ins.length - 1)
						{
							argBuf.append(",");
						}
					}
					argBuf.append("]");
					argMsg = argBuf.toString();
				}
				buffer.append(arg.getClass().getName() + "-" + argMsg + "\n");
			}
		}
		return buffer.toString();
	}

	@Override
	public boolean isDebugEnabled()
	{
		if (logger != null)
		{
			return logger.isDebugEnabled();
		}
		return false;
	}

	@Override
	public boolean isInfoEnabled()
	{
		if (logger != null)
		{
			return logger.isInfoEnabled();
		}
		return false;
	}
}
