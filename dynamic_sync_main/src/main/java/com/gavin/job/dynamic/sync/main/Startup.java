package com.gavin.job.dynamic.sync.main;

import com.gavin.job.dynamic.sync.common.core.listener.ServiceDiscoveryListener;
import com.gavin.job.dynamic.sync.common.utils.log.LogUtils;
import com.gavin.job.dynamic.sync.config.ConfigFileWrapper;
import com.gavin.job.dynamic.sync.core.ServiceDiscoveryHolder;

public class Startup {

	final static byte[] lock = new byte[0];

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ServiceDiscoveryListener listenerHandler = ServiceDiscoveryHolder
				.getInstance().getListenerHandler(
						ConfigFileWrapper.SERVICE_DISCOVERY_METHOD);

		if (null == listenerHandler) {
			LogUtils.getInstance()
					.errorSystem(
							"Startup",
							"Can not get ServiceDiscoveryListener instance,service discovery method not exist",
							ConfigFileWrapper.SERVICE_DISCOVERY_METHOD);
			System.exit(-1);
		}

		listenerHandler.registerListener(); //start up listener for service discovery

		// 无限期等待
		while (true) {
			synchronized (lock) {
				LogUtils.getInstance().infoSystem("StartUp main",
						"Startup successfully, start to work...");

				try {
					lock.wait();  //Wait in main thread
				} catch (InterruptedException e) {
					e.printStackTrace();
					LogUtils.getInstance().errorSystem("StartUp main", e);
				}

			}
		}

	}

}
