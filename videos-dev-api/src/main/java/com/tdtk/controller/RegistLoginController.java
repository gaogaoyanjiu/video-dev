package com.tdtk.controller;

import com.tdtk.cofig.ResourceConfig;
import com.tdtk.pojo.Users;
import com.tdtk.pojo.vo.UsersVO;
import com.tdtk.service.UserService;
import com.tdtk.utils.JSONResult;
import com.tdtk.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Api(value = "注册和登陆业务的接口",tags = "注册和登陆的Controller")
@RestController
public class RegistLoginController extends BasicController{

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceConfig resourceConfig;

    @ApiOperation(value="用户注册", notes="用户注册的接口")
	@PostMapping("/regist")
	public JSONResult regist(@RequestBody Users user) throws Exception {

		// 1. 判断用户名和密码必须不为空
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			return JSONResult.errorMsg("用户名和密码不能为空...");
		}

		// 2. 判断用户名是否存在
		boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());

		// 3. 保存用户，注册信息
		if (!usernameIsExist) {
			user.setNickname(user.getUsername());
            String password = user.getPassword();
            user.setPassword(MD5Utils.getMD5Str(password));
			user.setFansCounts(0);
			user.setReceiveLikeCounts(0);
			user.setFollowCounts(0);
			userService.saveUser(user);
		} else {
			return JSONResult.errorMsg("用户名已经存在，请换一个再试");
		}

        user.setPassword("");
        System.out.println(user);

        UsersVO usersVO = setUserRedisSessionToken(user);
        return JSONResult.ok(usersVO);
	}

    /**
     * 保存session到redis
     * @param userModel
     * @return
     */
    public UsersVO setUserRedisSessionToken(Users userModel) {

        String USER_REDIS_SESSION = resourceConfig.getUserRedisSession();

        String uniqueToken = UUID.randomUUID().toString().replace("-","");
        redis.set(USER_REDIS_SESSION + ":" + userModel.getId(), uniqueToken, 1000 * 60 * 30);

        UsersVO userVO = new UsersVO();
        BeanUtils.copyProperties(userModel, userVO);
        userVO.setUserToken(uniqueToken);
        return userVO;
    }


    @ApiOperation(value="用户登录", notes="用户登录的接口")
    @PostMapping("/login")
    public JSONResult login(@RequestBody Users user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();

        //Thread.sleep(3000);

        // 1. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("用户名或密码不能为空...");
        }

        // 2. 判断用户是否存在
        Users userResult = userService.queryUserForLogin(username,
                MD5Utils.getMD5Str(user.getPassword()));

        // 3. 返回
        if (userResult != null) {
            userResult.setPassword("");
            System.out.println(userResult);
            UsersVO usersVO = setUserRedisSessionToken(userResult);
            return JSONResult.ok(usersVO);
        } else {
            return JSONResult.errorMsg("用户名或密码不正确, 请重试...");
        }
    }


    @ApiOperation(value="用户注销", notes="用户注销的接口")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "String",paramType = "query")
    @PostMapping("/logout")
    public JSONResult logout(String userId) throws Exception {
        String USER_REDIS_SESSION = resourceConfig.getUserRedisSession();

        redis.del(USER_REDIS_SESSION + ":" + userId);
            return JSONResult.ok("用户注销成功！！！");

    }

}
