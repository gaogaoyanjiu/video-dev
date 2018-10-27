package com.tdtk.cofig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix="com.tdtk")
@PropertySource("classpath:resource.properties")
public class ResourceConfig {

	private String zookeeperServer;
	private String bgmServer;
	private String fileSpace;


	/*************************************************************************/
	private String userRedisSession;
	private String ffmpegServerDir;
	private String pageSize;
	/*************************************************************************/


	public String getZookeeperServer() {
		return zookeeperServer;
	}
	public void setZookeeperServer(String zookeeperServer) {
		this.zookeeperServer = zookeeperServer;
	}
	public String getBgmServer() {
		return bgmServer;
	}
	public void setBgmServer(String bgmServer) {
		this.bgmServer = bgmServer;
	}
	public String getFileSpace() {
		return fileSpace;
	}
	public void setFileSpace(String fileSpace) {
		this.fileSpace = fileSpace;
	}

	public String getUserRedisSession() {
		return userRedisSession;
	}

	public void setUserRedisSession(String userRedisSession) {
		this.userRedisSession = userRedisSession;
	}

	public String getFfmpegServerDir() {
		return ffmpegServerDir;
	}

	public void setFfmpegServerDir(String ffmpegServerDir) {
		this.ffmpegServerDir = ffmpegServerDir;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
}
