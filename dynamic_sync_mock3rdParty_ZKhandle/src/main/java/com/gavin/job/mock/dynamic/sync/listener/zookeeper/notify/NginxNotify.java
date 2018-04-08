package com.gavin.job.mock.dynamic.sync.listener.zookeeper.notify;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import com.gavin.job.dynamic.sync.common.core.notify.IServerNotify;
import com.gavin.job.dynamic.sync.common.utils.CommonUtils;
import com.gavin.job.dynamic.sync.common.utils.log.LogUtils;
import com.gavin.job.mock.dynamic.sync.listener.zookeeper.ZkConfig;

public class NginxNotify extends NodeHolder implements IServerNotify {

	@Override
	public void notifyToServer() {
		try {

			LinkedHashMap<String, Long> holder = NodeHolder.holder;

			// 1. calculate the master node
			String masterNode = this.selectMaster(holder);

			if(null == masterNode){
				return;
			}
			// 2. update the config file
			this.updateConfigFile(masterNode);

			// 3. Reload nginx
			this.reload();
		} catch (Exception e) {
			LogUtils.getInstance().infoSystem("NginxNotify",
					"Error while do notify new master to nginx", e, null);
		}
	}

	private String selectMaster(LinkedHashMap<String, Long> holder) {
		// if holder contains only one node,the do file change directly
		if (holder.size() == 1) {
			return holder.keySet().iterator().next();
		} else {
			// Else, compute the master and then do file change:TODO
			for (String key : holder.keySet()) {
				// send http request to server to check wh
				return key;
			}
		}
		return null;
	}

	/**
	 * <p>
	 * Title: changeFile
	 * </p>
	 * <p>
	 * Description:读取文件
	 * </p>
	 * 
	 * @param file
	 * @throws Exception
	 * 
	 */
	public void replaceFileWithNewIP(File file, String upstreamName,
			String target) throws IOException {
		BufferedReader br = null;

		StringBuilder sb = new StringBuilder();
		try {
			if (!file.exists()) {
				LogUtils.getInstance().infoSystem("NginxNotify",
						"File does not exist");
				return;
			}
			FileReader fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			int flag = 0;
			for (String tmp = null; (tmp = br.readLine()) != null; tmp = null) {
				try {
					// 在这里的下一行开始做替换操作
					if (tmp.contains(upstreamName)) {
						flag = 1;
					} else if (flag == 1) { //Begin do replace
						String[] split = tmp.trim().split(" ");
						for (String str : split) {
							if (str.split(":").length == 2) {
								String ipAddress = str.split(":")[0];

								if(CommonUtils.isIP(ipAddress))
									tmp = tmp.replace(ipAddress, target);
							}

						}
					}
					sb.append(tmp);
					sb.append("\n");
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
			}
			
			// 文件的重新写入
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(
							ZkConfig.SERVER_CONFIG_FILE_PATH));
			bw.write(sb.toString());
			LogUtils.getInstance().infoSystem("NginxNotify",
					"File replace success", "new master",target);
			bw.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		}finally{
			if(null!=br)
				br.close();
		}
	}

	private void updateConfigFile(String masterNode) throws IOException {
		File file = new File(ZkConfig.SERVER_CONFIG_FILE_PATH);
		this.replaceFileWithNewIP(file, ZkConfig.SERVER_UPSTREAM_NAME,
				masterNode);
	}

	private void reload() throws IOException, InterruptedException {
		String[] cmds = { "/bin/sh", "-c", ZkConfig.SERVER_RELOAD_COMMAND };
		Process pro = Runtime.getRuntime().exec(cmds);
		pro.waitFor();
		InputStream in = pro.getInputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = read.readLine()) != null) {
			LogUtils.getInstance().infoSystem("NginxNotify",
					"Reload command result", ZkConfig.SERVER_RELOAD_COMMAND,
					line);
		}
	}
}
