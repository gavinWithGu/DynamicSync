package com.gavin.job.dynamic.sync.listener.zookeeper.listen.subcriber.impl;

import org.I0Itec.zkclient.ZkClient;

import com.gavin.job.dynamic.sync.common.utils.ZkUtils;
import com.gavin.job.dynamic.sync.common.utils.log.LogUtils;
import com.gavin.job.dynamic.sync.listener.zookeeper.ZkConfig;

public class ZkClientWrapper {
	private static ZkClient zkClient;

	private static final ZkClientWrapper instance = new ZkClientWrapper();

	static {
		try {
			LogUtils.getInstance().infoSystem("ZkClientWrapper",
					"Begin connecting to zookeeper cluster......");
			zkClient = new ZkClient(ZkConfig.SERVICE_DISCOVERY_HOST,
					ZkConfig.SERVICE_DISCOVERY_CLIENT_TIMEOUT,
					ZkConfig.SERVICE_DISCOVERY_CLIENT_TIMEOUT,
					new ZkUtils.StringSerializer("UTF-8"));

			LogUtils.getInstance().infoSystem("ZkClientWrapper",
					"Connect  to zookeeper cluster successfully!");
			
			ZkConfig.setClusterAlive(true);
		} catch (Exception e) {
			LogUtils.getInstance().errorSystem("ZkClientWrapper",
					"Connect to zookeeper cluster error!!!", e);
			
			ZkConfig.setClusterAlive(false);
		}

	}

	private ZkClientWrapper() {
	}

	public static ZkClientWrapper getInstance() {
		return instance;
	}

	public ZkClient getZkClient() {
		return zkClient;
	}

}
