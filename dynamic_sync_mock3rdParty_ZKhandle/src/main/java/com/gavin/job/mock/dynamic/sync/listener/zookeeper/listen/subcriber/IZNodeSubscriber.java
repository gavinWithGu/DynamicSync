package com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.subcriber;

import java.util.List;

import com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.listener.impl.ConfigChangeListener;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.listener.impl.SubNodeChangeListener;

/**
 * 配置改变的订阅者，在每一個zk node上订阅一個监听器：指定znode与child node监听
 */
public abstract interface IZNodeSubscriber {
	public abstract String getInitValue(String paramString);

	public abstract void subscribe4SubChild(String paramString,
			SubNodeChangeListener changeListener);
	
	public abstract void subscribe4ZNodeData(String paramString,
			ConfigChangeListener changeListener);

	public abstract List<String> listKeys();
}