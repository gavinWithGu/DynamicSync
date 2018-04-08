package com.gavin.job.mock.dynamic.sync.listener.zookeeper.notify;

import java.util.LinkedHashMap;

public class NodeHolder {
	protected static LinkedHashMap<String, Long> holder = new LinkedHashMap<String, Long>();
	private static final NodeHolder instance = new NodeHolder();

	protected NodeHolder() {
	}

	public static NodeHolder getInstance() {
		return instance;
	}

	public void addSeed(String name, long weight) {
		holder.put(name, weight);
	}
	
	public void clear() {
		holder.clear();
	}
}
