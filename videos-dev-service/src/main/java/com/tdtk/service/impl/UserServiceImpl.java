package com.tdtk.service.impl;

import com.tdtk.mapper.UsersFansMapper;
import com.tdtk.mapper.UsersLikeVideosMapper;
import com.tdtk.mapper.UsersReportMapper;
import com.tdtk.pojo.UsersFans;
import com.tdtk.pojo.UsersLikeVideos;
import com.tdtk.pojo.UsersReport;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tdtk.mapper.UsersMapper;
import com.tdtk.pojo.Users;

import com.tdtk.service.UserService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper userMapper;

	@Autowired
	private UsersLikeVideosMapper usersLikeVideosMapper;

	@Autowired
	private UsersFansMapper usersFansMapper;



	@Autowired
	private UsersReportMapper usersReportMapper;


    @Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public boolean queryUsernameIsExist(String username) {
        Users users = new Users();
        users.setUsername(username);
        Users result = userMapper.selectOne(users);
        return result==null? false:true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void saveUser(Users user) {
        IdWorker idWorker = new IdWorker(0);
        user.setId(idWorker.nextId()+"");
        System.out.println(idWorker.nextId());
		userMapper.insert(user);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Users queryUserForLogin(String username, String password) {
		
		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("username", username);
		criteria.andEqualTo("password", password);

		Users result = userMapper.selectOneByExample(userExample);
		
		return result;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Users queryUserInfo(String userId) {
		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("id", userId);
		Users user = userMapper.selectOneByExample(userExample);
		return user;
	}

	@Override
	public void updateUserInfo(Users users) {

		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("id",users.getId());
		userMapper.updateByExampleSelective(users,userExample);

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public boolean isUserLikeVideo(String userId, String videoId) {

		if (StringUtils.isBlank(userId) || StringUtils.isBlank(videoId)) {
			return false;
		}

		Example example = new Example(UsersLikeVideos.class);
		Criteria criteria = example.createCriteria();

		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("videoId", videoId);

		List<UsersLikeVideos> list = usersLikeVideosMapper.selectByExample(example);

		if (list != null && list.size() >0) {
			return true;//收藏
		}

		return false;//未收藏
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void saveUserFanRelation(String userId, String fanId) {

		IdWorker idWorker = new IdWorker(0);
		String relId =idWorker.nextId()+"";

		//String relId = sid.nextShort();

		UsersFans userFan = new UsersFans();
		userFan.setId(relId);
		userFan.setUserId(userId);
		userFan.setFanId(fanId);

		usersFansMapper.insert(userFan);//维护用户和粉丝的关系表

		userMapper.addFansCount(userId);  //增加粉丝数量
		userMapper.addFollersCount(fanId); //增加关注数量

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteUserFanRelation(String userId, String fanId) {

		Example example = new Example(UsersFans.class);
		Criteria criteria = example.createCriteria();

		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("fanId", fanId);

		usersFansMapper.deleteByExample(example);//维护用户和粉丝的关系表-删除数据

		userMapper.reduceFansCount(userId);//减少粉丝数量
		userMapper.reduceFollersCount(fanId);//减少关注数量

	}


	@Override
	public boolean queryIfFollow(String userId, String fanId) {

		Example example = new Example(UsersFans.class);
		Criteria criteria = example.createCriteria();

		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("fanId", fanId);

		List<UsersFans> list = usersFansMapper.selectByExample(example);

		if (list != null && !list.isEmpty() && list.size() > 0) {
			return true;
		}

		return false;
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void reportUser(UsersReport userReport) {
		IdWorker idWorker = new IdWorker(0);
		String urId =idWorker.nextId()+"";
//		String urId = sid.nextShort();
		userReport.setId(urId);
		userReport.setCreateDate(new Date());

		usersReportMapper.insert(userReport);
	}
}

