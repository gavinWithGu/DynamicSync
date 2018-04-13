package com.gavin.job.dynamic.sync.core;

import java.util.HashMap;
import java.util.Map;

import com.gavin.job.dynamic.sync.common.constant.Constants.SERVER_NOTIFY_TYPE;
import com.gavin.job.dynamic.sync.common.constant.Constants.SERVIE_LISTEN_TYPE;
import com.gavin.job.dynamic.sync.common.core.listener.ServiceDiscoveryListener;
import com.gavin.job.dynamic.sync.common.core.notify.IServerNotify;
import com.gavin.job.dynamic.sync.common.utils.log.LogUtils;
import com.gavin.job.dynamic.sync.config.ConfigFileWrapper;
import com.gavin.job.dynamic.sync.listener.zookeeper.ZKNodeWrapper;
import com.gavin.job.dynamic.sync.listener.zookeeper.listen.listener.IZKNodeListener;

public class ServiceDiscoveryHolder {

	private static Map<String, ServiceDiscoveryListener> listenerHolder = new HashMap<String, ServiceDiscoveryListener>(); // <zookeeper,handler
	// class
	// instance>

	private static Map<String, IServerNotify> notifyHolder = new HashMap<String, IServerNotify>(); // <zookeeper,handler
	// class
	// instance>

	
	private static ServiceDiscoveryHolder instance = new ServiceDiscoveryHolder();

	static {
		init();
	}

	private ServiceDiscoveryHolder() {
	}

	public static ServiceDiscoveryHolder getInstance() {
		return instance;
	}

	/**
	 * 初始化服务类： <br>
	 * 1. 将系统自带的zookeeper装载 <br>
	 * 2. 读取配置文件，将指定的class反射加载 :TODO
	 * 
	 * @throws ClassNotFoundException
	 * 
	 */
	public static void init() {
		IZKNodeListener listener = ZKNodeWrapper.getInstance().getListener();

		if (SERVIE_LISTEN_TYPE.ZOOKEEPER
				.equals(ConfigFileWrapper.SERVICE_DISCOVERY_METHOD)) {
			listenerHolder.put(SERVIE_LISTEN_TYPE.ZOOKEEPER, listener);
		} else {
			// 类加载指定的服务发现实现类
			Class<ServiceDiscoveryListener> clazz;
			try {
				clazz = (Class<ServiceDiscoveryListener>) Class
						.forName(ConfigFileWrapper.SERVICE_DISCOVERY_METHOD);
				listenerHolder.put(ConfigFileWrapper.SERVICE_DISCOVERY_METHOD,
						clazz.newInstance());
			} catch (ClassNotFoundException e) {
				LogUtils.getInstance().errorSystem("Service Discovery",
						"Can not init service discovery", e);
				e.printStackTrace();
			} catch (InstantiationException e) {
				LogUtils.getInstance().errorSystem("Service Discovery",
						"Can not init service discovery", e);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				LogUtils.getInstance().errorSystem("Service Discovery",
						"Can not init service discovery", e);
				e.printStackTrace();
			}

		}
		
		if (SERVER_NOTIFY_TYPE.NGINX.equals(ConfigFileWrapper.SERVER_TYPE)) {
			notifyHolder.put(SERVER_NOTIFY_TYPE.NGINX, ZKNodeWrapper.getInstance().getNotify());
		}else {
			// 类加载指定的服务发现实现类
			Class<IServerNotify> clazz;
			try {
				clazz = (Class<IServerNotify>) Class
						.forName(ConfigFileWrapper.SERVER_TYPE);
				notifyHolder.put(ConfigFileWrapper.SERVER_TYPE, clazz.newInstance());
				
			} catch (ClassNotFoundException e) {
				LogUtils.getInstance().errorSystem("Service Discovery",
						"Can not init service discovery", e);
				e.printStackTrace();
			} catch (InstantiationException e) {
				LogUtils.getInstance().errorSystem("Service Discovery",
						"Can not init service discovery", e);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				LogUtils.getInstance().errorSystem("Service Discovery",
						"Can not init service discovery", e);
				e.printStackTrace();
			}
		}
	}

	public ServiceDiscoveryListener getListenerHandler(String name) {
		return listenerHolder.get(name.trim());
	}

	public IServerNotify getServerNotify(String name) {
		return notifyHolder.get(name.trim());
	}
}
