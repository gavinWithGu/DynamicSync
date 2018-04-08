package com.gavin.job.dynamic.sync.listener.zookeeper.listen;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PropRepos {
	private static Map<String, String> configs = new ConcurrentHashMap<String, String>();

	private PropRepos(){}
	
	public static String getContent(String key) {
		return configs.get(key);
	}

	public  static void put(String key, String value) {
		configs.put(key, value);
	}

	public static Map<String, String> getAllConfigs() {
		return configs;
	}
	
	public static int count() {
		return configs.size();
	}


}
