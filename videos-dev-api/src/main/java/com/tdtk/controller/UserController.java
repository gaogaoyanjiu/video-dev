package com.tdtk.controller;

import com.tdtk.cofig.ResourceConfig;
import com.tdtk.pojo.Users;
import com.tdtk.pojo.UsersReport;
import com.tdtk.pojo.vo.PublisherVideo;
import com.tdtk.pojo.vo.UsersVO;
import com.tdtk.service.UserService;
import com.tdtk.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Api(value = "用户相关业务的接口",tags = "用户相关业务的Controller")
@RestController
@RequestMapping("/user")
public class UserController extends BasicController{

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceConfig resourceConfig;

    @ApiOperation(value="用户上传头像", notes="用户上传头像的接口")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "String",paramType = "query")
    @PostMapping("/uploadFace")
    public JSONResult uploadFace(String userId,@RequestParam("file") MultipartFile[] files) throws Exception {

        // 文件保存的命名空间
        String FILE_SPACE = resourceConfig.getFileSpace();

        if (StringUtils.isNotBlank(userId)){
            JSONResult.errorMsg("用户id不能为空...");
        }
        // 文件保存的命名空间
        String fileSpace = FILE_SPACE;
        // 保存到数据库中的相对路径
        String uploadPathDB = File.separator + userId + File.separator + "face";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        try {
            if(files !=null && files.length>0){
                String fileName = files[0].getOriginalFilename();
                 if (StringUtils.isNotBlank(fileName)){

                     //文件上传的最终保存路径
                     String finalFacePath=fileSpace + uploadPathDB + File.separator + fileName;
                     //数据库保存的相对路径
                     uploadPathDB+=( File.separator +fileName);

                     File outFile = new File(finalFacePath);
                     if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                         // 创建父文件夹
                         outFile.getParentFile().mkdirs();
                     }
                     fileOutputStream = new FileOutputStream(outFile);
                     inputStream = files[0].getInputStream();
                     IOUtils.copy(inputStream, fileOutputStream);
                 }
            }else{
               return JSONResult.errorMsg("上传出错...");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传出错...");
        } finally {
            if(fileOutputStream!=null){
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        Users users = new Users();
        users.setId(userId);
        users.setFaceImage(uploadPathDB);
        userService.updateUserInfo(users);

        return JSONResult.ok(uploadPathDB);

    }




    @ApiOperation(value="查询用户信息", notes="查询用户信息的接口")
    @ApiImplicitParam(name="userId", value="用户id", required=true,
            dataType="String", paramType="query")
    @PostMapping("/query")
    public JSONResult query(String userId, String fanId) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("用户id不能为空...");
        }

        Users userInfo = userService.queryUserInfo(userId);
        UsersVO userVO = new UsersVO();
        BeanUtils.copyProperties(userInfo, userVO);

        userVO.setFollow(userService.queryIfFollow(userId, fanId));

        return JSONResult.ok(userVO);
    }


    @ApiOperation(value="查询视频发布者信息", notes="查询视频发布者信息的接口")
    @PostMapping("/queryPublisher")
    public JSONResult queryPublisher(String loginUserId, String videoId,
                                          String publishUserId) throws Exception {

        if (StringUtils.isBlank(publishUserId)) {
            return JSONResult.errorMsg("");
        }

        // 1. 查询视频发布者的信息
        Users userInfo = userService.queryUserInfo(publishUserId);
        UsersVO publisher = new UsersVO();
        BeanUtils.copyProperties(userInfo, publisher);

        // 2. 查询当前登录者和视频的收藏关系
        boolean userLikeVideo = userService.isUserLikeVideo(loginUserId, videoId);

        PublisherVideo bean = new PublisherVideo();
        bean.setPublisher(publisher);
        bean.setUserLikeVideo(userLikeVideo);

        return JSONResult.ok(bean);
    }

    @ApiOperation(value="添加关注", notes="添加关注的接口")
    @PostMapping("/beyourfans")
    public JSONResult beyourfans(String userId, String fanId) throws Exception {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(fanId)) {
            return JSONResult.errorMsg("");
        }

        userService.saveUserFanRelation(userId, fanId);

        return JSONResult.ok("关注成功...");
    }


    @ApiOperation(value="取消关注", notes="取消关注的接口")
    @PostMapping("/dontbeyourfans")
    public JSONResult dontbeyourfans(String userId, String fanId) throws Exception {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(fanId)) {
            return JSONResult.errorMsg("");
        }

        userService.deleteUserFanRelation(userId, fanId);

        return JSONResult.ok("取消关注成功...");
    }


    @PostMapping("/reportUser")
    public JSONResult reportUser(@RequestBody UsersReport usersReport) throws Exception {

        // 保存举报信息
        userService.reportUser(usersReport);

        return JSONResult.ok("举报成功...有你平台变得更美好...");
    }
}
