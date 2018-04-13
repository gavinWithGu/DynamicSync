package com.gavin.job.dynamic.sync.main;

import com.gavin.job.dynamic.sync.common.core.callback.INotifyCallback;
import com.gavin.job.dynamic.sync.common.core.listener.ServiceDiscoveryListener;
import com.gavin.job.dynamic.sync.common.core.notify.IServerNotify;
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
							"Can not get ServiceDiscoveryListener instance,system will exit now",
							ConfigFileWrapper.SERVICE_DISCOVERY_METHOD);
			System.exit(-1);
		}

		final IServerNotify notify = ServiceDiscoveryHolder.getInstance().getServerNotify(ConfigFileWrapper.SERVER_TYPE);
		
		if (null == notify) {
			LogUtils.getInstance()
					.errorSystem(
							"Startup",
							"Can not get Notify Server instance,system will exit now",
							ConfigFileWrapper.SERVER_TYPE);
			System.exit(-1);
		}
		
		/**
		 * start up listener for service discovery
		 */
		listenerHandler.registerListener(new INotifyCallback() {
			@Override
			public void notifyCallback() {
				notify.notifyToServer();
			}
		}); 

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
