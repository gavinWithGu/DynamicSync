package com.gavin.job.dynamic.sync.common.core.notify;

/**
 * 服务异步通知基础接口:需要实现该接口,达到通知web服务器,新的主从关系的效果<br>
 * 
 * @author gavin
 * 
 */
public interface IServerNotify {
	/**
	 * 实现规范:<br>
	 * 1. 修改出配置文件:server.config.file.path指定的配置文件
	 * 2. 实现平滑热部署功能
	 */
	void notifyToServer();
}
