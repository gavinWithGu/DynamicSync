package com.gavin.job.dynamic.sync.common.messagecode;

public interface IMsgCode {

	public static final int MSGCODE = 0;

	/** ******************** success ********************** */
	public static final int BASE_MSGCODE_SUCCESS = MSGCODE + 10000;


	/** ******************** error ********************** */
	public static final int BASE_MSGCODE_ERROR = MSGCODE + 20000;
	
}
