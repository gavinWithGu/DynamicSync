package com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.listener.impl;

import java.util.List;

/**
 * 监听器，监听子节点的改变
 * 
 */
public abstract interface SubNodeChangeListener {
	public abstract void subNodeChanged(String parentPath,
			List<String> currentChilds);
}