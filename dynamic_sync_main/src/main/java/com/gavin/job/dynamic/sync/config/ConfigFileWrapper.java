package com.gavin.job.dynamic.sync.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gavin.job.dynamic.sync.common.utils.log.LogUtils;

public class ConfigFileWrapper {

	private static Properties propsSystem = new Properties();

	public static String SERVICE_DISCOVERY_METHOD;
	public static String SERVICE_DISCOVERY_HOST;
	public static String SERVICE_DISCOVERY_CLIENT_TIMEOUT;
	public static String SERVER_TYPE;
	public static String SERVICE_DISCOVERY_LISTENER_ROOTPATH;
	public static String SERVICE_DISCOVERY_LISTENER_SUBPATH;

	public static String SERVER_RELOAD_COMMAND;
	public static String SERVER_CONFIG_FILE_PATH;
	public static String SERVER_UPSTREAM_NAME;
	
	static{
		loadFromLocal();
	}
	
	public static void loadFromLocal() {
		InputStream inSystem = ConfigFileWrapper.class.getClassLoader()
				.getResourceAsStream("config.properties");

		try {
			propsSystem.load(inSystem);

			SERVICE_DISCOVERY_METHOD = propsSystem.getProperty("service.discovery.method");
			
			SERVICE_DISCOVERY_HOST = propsSystem.getProperty("service.discovery.host");
			SERVICE_DISCOVERY_CLIENT_TIMEOUT = propsSystem.getProperty("service.discovery.client.timeout");
			SERVICE_DISCOVERY_LISTENER_ROOTPATH = propsSystem.getProperty("service.discovery.listener.rootpath");
			SERVICE_DISCOVERY_LISTENER_SUBPATH = propsSystem.getProperty("service.discovery.listener.subpath");
			SERVER_TYPE = propsSystem.getProperty("server.type");
			SERVER_RELOAD_COMMAND = propsSystem.getProperty("server.reload.command");
			SERVER_CONFIG_FILE_PATH = propsSystem.getProperty("server.config.file.path");
			SERVER_UPSTREAM_NAME = propsSystem.getProperty("server.upstream.name");
			
		} catch (Exception ex) {
			LogUtils.getInstance().errorSystem("ConfigFileUtil",
					"Error loading properties from Local file system!", ex);
		} finally {
			if (inSystem != null) {
				try {
					inSystem.close();
				} catch (IOException e) {

				}
			}
		}
	}

}
