package com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.listener.impl;

/**
 * 监听器，监听配置的改变
 * 
 */
public abstract interface ConfigChangeListener {
	public abstract void configChanged(String paramString1, String paramString2);
}