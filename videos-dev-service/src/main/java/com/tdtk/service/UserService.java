package com.tdtk.service;

import com.tdtk.pojo.Users;
import com.tdtk.pojo.UsersReport;

public interface UserService {
	
	/**
	 * @Description: 判断用户名是否存在
	 */
	public boolean queryUsernameIsExist(String username);
	
	/**
	 * @Description: 保存用户(用户注册)
	 */
	public void saveUser(Users user);
	
	/**
	 * @Description: 用户登录，根据用户名和密码查询用户
	 */
	public Users queryUserForLogin(String username, String password);
	

	/**
	 * @Description: 查询用户信息
	 */
	public Users queryUserInfo(String userId);


	/**
	 * @Description: 修改用户信息
	 */
	public void updateUserInfo(Users users);

	/**
	 * @Description: 查询用户是否喜欢收藏视频
	 */
	public boolean isUserLikeVideo(String userId, String videoId);


	/**
	 * @Description: 添加关注
	 */
	public void saveUserFanRelation(String userId, String fanId);

	/**
	 * @Description: 取消关注
	 */
	public void deleteUserFanRelation(String userId, String fanId);



	/**
	 * @Description: 查询用户是否关注
	 */
	public boolean queryIfFollow(String userId, String fanId);

	/**
	 * @Description: 举报用户
	 */
	public void reportUser(UsersReport userReport);
}
