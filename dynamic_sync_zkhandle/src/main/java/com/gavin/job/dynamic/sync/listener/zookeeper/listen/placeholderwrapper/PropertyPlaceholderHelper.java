package com.gavin.job.dynamic.sync.listener.zookeeper.listen.placeholderwrapper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public abstract class PropertyPlaceholderHelper {

	public static Properties parseFromString(String value) {
		Properties props = new Properties();
		if (!StringUtils.isEmpty(value))
			try {
				props.load(new StringReader(value));
			} catch (IOException e) {
				e.printStackTrace();
			}
		return props;
	}
}
