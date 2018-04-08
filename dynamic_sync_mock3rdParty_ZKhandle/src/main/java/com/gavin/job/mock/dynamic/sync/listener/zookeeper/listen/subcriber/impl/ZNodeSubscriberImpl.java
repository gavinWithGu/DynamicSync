package com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.subcriber.impl;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang.StringUtils;

import com.gavin.job.dynamic.sync.common.utils.ZkUtils;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.ZkConfig;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.listener.impl.ConfigChangeListener;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.listener.impl.SubNodeChangeListener;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.subcriber.IZNodeSubscriber;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.notify.NodeHolder;

/**
 * 订阅者实现类，当订阅到zk数据或者子节点改变时，会触发
 * 
 */
public class ZNodeSubscriberImpl implements IZNodeSubscriber {
	private static ZkClient zkClient;
	private static String rootNode;

	static {
		rootNode = ZkConfig.SERVICE_DISCOVERY_LISTENER_ROOTPATH;
		zkClient = ZkClientWrapper.getInstance().getZkClient();
	}

	public ZNodeSubscriberImpl() {
	}

	public ZNodeSubscriberImpl(ZkClient zkClient, String rootNode) {

	}

	public void subscribe4SubChild(String key, SubNodeChangeListener listener) {
		String path = ZkUtils.getZkPath(this.rootNode, key);
		if (!this.zkClient.exists(path)) {
			throw new RuntimeException(
					"配置("
							+ path
							+ ")不存在, 必须先定义配置才能监听配置的变化, 请检查配置的key是否正确, 如果确认配置key正确, 那么需要保证先使用配置发布命令发布配置! ");
		}

		this.zkClient.subscribeChildChanges(path, new SubNodeListenerAdapter(
				listener));
	}

	@Override
	public void subscribe4ZNodeData(String key,
			ConfigChangeListener changeListener) {
		String path = ZkUtils.getZkPath(this.rootNode, key);
		if (!this.zkClient.exists(path)) {
			throw new RuntimeException(
					"配置("
							+ path
							+ ")不存在, 必须先定义配置才能监听配置的变化, 请检查配置的key是否正确, 如果确认配置key正确, 那么需要保证先使用配置发布命令发布配置! ");
		}

		this.zkClient.subscribeDataChanges(path, new DataListenerAdapter(
				changeListener));
	}

	/**
	 * 触发node data变化
	 * 
	 * @param path
	 * @param value
	 * @param configListener
	 */
	private void fireConfigChanged(String path, String value,
			ConfigChangeListener configListener) {
		configListener.configChanged(getKey(path), value);
	}

	/**
	 * 触发子节点变化监听
	 * 
	 * @param path
	 * @param value
	 * @param subNodeListener
	 */
	private void fireSubNodeChanged(String parentPath,
			List<String> currentChilds, SubNodeChangeListener subNodeListener) {
		subNodeListener.subNodeChanged(parentPath, currentChilds);
	}

	private String getKey(String path) {
		String key = path;

		if (!StringUtils.isEmpty(this.rootNode)) {
			key = path.replaceFirst(this.rootNode, "");
			if (key.startsWith("/")) {
				key = key.substring(1);
			}
		}

		return key;
	}

	public String getInitValue(String key) {
		String path = ZkUtils.getZkPath(this.rootNode, key);
		return (String) this.zkClient.readData(path);
	}

	public List<String> listKeys() {
		return this.zkClient.getChildren(this.rootNode);
	}

	/**
	 * 数据监听器适配类，当zk数据变化时，触发ConfigChangeListener
	 * 
	 */
	private class DataListenerAdapter implements IZkDataListener {
		private ConfigChangeListener configListener;

		public DataListenerAdapter(ConfigChangeListener configListener) {
			this.configListener = configListener;
		}

		public void handleDataChange(String s, Object obj) throws Exception {
			ZNodeSubscriberImpl.this.fireConfigChanged(s, (String) obj,
					this.configListener);
		}

		public void handleDataDeleted(String s) throws Exception {
		}
	}

	/**
	 * 数据监听器适配类，当zk sub child变化时，触发SubNodeChangeListener
	 * 
	 */
	private class SubNodeListenerAdapter implements IZkChildListener {

		private SubNodeChangeListener subNodeListener;

		public SubNodeListenerAdapter(SubNodeChangeListener configListener) {
			this.subNodeListener = configListener;
		}

		@Override
		public void handleChildChange(String parentPath,
				List<String> currentChilds) throws Exception {

			NodeHolder.getInstance().clear();//清除旧节点数据
			
			ZNodeSubscriberImpl.this.fireSubNodeChanged(parentPath, currentChilds,
					this.subNodeListener);
		}
	}

}