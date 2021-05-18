package com.gavin.job.dynamic.sync.common.core.listener;

import com.gavin.job.dynamic.sync.common.core.callback.INotifyCallback;

/**
 * 服务发现主接口:需要实现该接口,listen master的通知行为
 * 
 * @author gavin
 * 
 */
public interface ServiceDiscoveryListener {
	void registerListener(INotifyCallback callback);
}
