package com.gx.cloud.common.constant;

import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;


/**
 * 
 * @Description 参数定义
 * @author Harry.Wu
 * @date 2015年11月26日 上午11:12:34
 */
public class ParameterCenter {

	/*基础文件存储根目录*/
	public static String FM_FILE_ROOT_PATH;
	public final static String FM_FILE_ROOT_PATH_WIN = "D:";
	public final static String FM_FILE_ROOT_PATH_CH = "mnt";

	static {
		char separatorChar = File.separatorChar;
		/*判断当前系统是否为windows  File.separatorChar  文件名分隔符，在Windows中为"/",unix中为"/"  */
		if(separatorChar == '\\'){
		/*windows*/
		FM_FILE_ROOT_PATH = FM_FILE_ROOT_PATH_WIN+File.separator+FM_FILE_ROOT_PATH_CH;
		}else {
			/*linux*/
			FM_FILE_ROOT_PATH = FM_FILE_ROOT_PATH_CH;
		}

	}
	
	
	
}

