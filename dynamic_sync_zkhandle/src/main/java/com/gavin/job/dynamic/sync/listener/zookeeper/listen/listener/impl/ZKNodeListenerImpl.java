package com.gavin.job.dynamic.sync.listener.zookeeper.listen.listener.impl;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;

import com.gavin.job.dynamic.sync.common.core.notify.IServerNotify;
import com.gavin.job.dynamic.sync.common.utils.log.LogUtils;
import com.gavin.job.dynamic.sync.listener.zookeeper.ZKNodeWrapper;
import com.gavin.job.dynamic.sync.listener.zookeeper.ZkConfig;
import com.gavin.job.dynamic.sync.listener.zookeeper.listen.listener.IZKNodeListener;
import com.gavin.job.dynamic.sync.listener.zookeeper.listen.placeholderwrapper.GeneralPropertyPlaceholder;
import com.gavin.job.dynamic.sync.listener.zookeeper.listen.subcriber.IZNodeSubscriber;
import com.gavin.job.dynamic.sync.listener.zookeeper.listen.subcriber.impl.ZkClientWrapper;
import com.gavin.job.dynamic.sync.listener.zookeeper.listen.subcriber.impl.ZNodeSubscriberImpl;

public class ZKNodeListenerImpl implements
		IZKNodeListener {

	private IZNodeSubscriber zkConfig = new ZNodeSubscriberImpl();

	private ZkClient zkClient = ZkClientWrapper.getInstance().getZkClient();

	@Override
	public void registerListener() {
		for (final String path : ZkConfig.SERVICE_DISCOVERY_LISTENER_SUBPATH) {

			try {
				GeneralPropertyPlaceholder.getInstance().updateSubNode(path,
						zkClient);
			} catch (Exception e1) {
				LogUtils.getInstance().errorSystem("ZKNodeListenerImpl",
						"Update config properties error!", e1);
			}

			this.zkConfig.subscribe4SubChild(path, new SubNodeChangeListener() {
				public void subNodeChanged(String parentPath,
						List<String> currentChilds) {
					LogUtils.getInstance()
							.infoSystem(
									"Received zk child node Change notice",
									"Parent path", parentPath);
					
					try {
						
						for (String childPath : currentChilds) {
							LogUtils.getInstance().debugSystem("Handle child node Change","Parent path", parentPath,"Sub path",childPath);
							String path = parentPath + "/" + childPath;
							GeneralPropertyPlaceholder.getInstance().updateSubNode(path, zkClient);
						}

						// Notify the Server to change configuration
						IServerNotify notify = ZKNodeWrapper.getInstance().getNotify();
						notify.notifyToServer();
					} catch (Exception e) {
						LogUtils.getInstance().errorSystem("ZKNodeListenerImpl",
								"Update config properties error!", e);
					}
				}
			});
		}
	}
}

