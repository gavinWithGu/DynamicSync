package com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.placeholderwrapper;

import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.gavin.job.dynamic.sync.common.utils.log.LogUtils;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.ZkConfig;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.PropRepos;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.notify.NodeHolder;

public class GeneralPropertyPlaceholder extends PropertyPlaceholderHelper {

	private String rootNode = ZkConfig.SERVICE_DISCOVERY_LISTENER_ROOTPATH;
	private ZooKeeper zk;
	private Stat stat = new Stat();

	private static final byte[] lock = new byte[0];

	private static final GeneralPropertyPlaceholder instance = new GeneralPropertyPlaceholder();

	private GeneralPropertyPlaceholder() {
	}

	public static GeneralPropertyPlaceholder getInstance() {
		return instance;
	}

	public void updateSubNode(String subNodePath, ZkClient zkClient) throws Exception {
		synchronized (lock) {
			// 获取并监听子节点变化
			String data = zkClient.readData(subNodePath, stat);
			
			int dotIndex = data.lastIndexOf(".");
			if(dotIndex>0){
				NodeHolder.getInstance().addSeed(data.substring(0, dotIndex),Long.valueOf(data.substring(dotIndex+1, data.length())));				
			}
		}
		LogUtils.getInstance().infoSystem("General Property Placeholder",
				"Server configs updated", "Total:", PropRepos.count(),
				"Detail:", PropRepos.getAllConfigs());
	}
	
	public void updateConfigs(String path, ZkClient zkClient) throws Exception {
		synchronized (lock) {
			// 获取并监听znode节点内容
			String data = zkClient.readData(rootNode + "/" + path, stat);

			// 获取每个子节点下关联的server地址
			Properties prop = this.parseFromString(data);

			for (Entry<Object, Object> element : prop.entrySet()) {
				PropRepos.put(element.getKey().toString(), element.getValue()
						.toString());
			}
			//TODO：可以定义配置文件常量类，读取PropRepos中的内容
		}
		LogUtils.getInstance().infoSystem("General Property Placeholder",
				"Server configs updated", "Total:", PropRepos.count(),
				"Detail:", PropRepos.getAllConfigs());
	}


	public void updateAllConfigs() throws Exception {
		synchronized (lock) {
			// 获取并监听子节点变化
			List<String> subList = zk.getChildren(rootNode, true);
			for (String subNode : subList) {
				// 获取每个子节点下关联的server地址
				byte[] dataByte = zk.getData(rootNode + "/" + subNode, false, stat);
				String data = new String(dataByte, "utf-8");
				System.out.println(data);
			}
		}
	}
}
