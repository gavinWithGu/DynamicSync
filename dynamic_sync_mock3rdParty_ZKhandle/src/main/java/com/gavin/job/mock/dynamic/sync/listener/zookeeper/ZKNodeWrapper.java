package com.gavin.job.mock.dynamic.sync.listener.zookeeper;

import com.gavin.job.dynamic.sync.common.core.notify.IServerNotify;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.listener.IZKNodeListener;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.listener.impl.ZKNodeListener;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.notify.NginxNotify;

public class ZKNodeWrapper {
	private static IZKNodeListener listener;

	private static IServerNotify serverNotify;

	
	private static final ZKNodeWrapper instance = new ZKNodeWrapper();

	static {
		listener = new ZKNodeListener();
		
		serverNotify = new NginxNotify();
	}

	private ZKNodeWrapper() {
	}

	public static ZKNodeWrapper getInstance() {
		return instance;
	}

	public IZKNodeListener getListener() {
		return listener;
	}

	public IServerNotify getNotify() {
		return serverNotify;
	}
}
