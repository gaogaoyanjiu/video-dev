package com.tdtk.controller;

import com.tdtk.cofig.ResourceConfig;
import com.tdtk.enums.VideoStatusEnum;
import com.tdtk.pojo.Bgm;
import com.tdtk.pojo.Comments;
import com.tdtk.pojo.Videos;
import com.tdtk.service.BgmService;
import com.tdtk.service.VideoService;
import com.tdtk.utils.FFMpegUtils;
import com.tdtk.utils.JSONResult;
import com.tdtk.utils.PagedResult;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Api(value = "视频相关业务的接口",tags = "视频相关业务的Controller")
@RestController
@RequestMapping("/video")
public class VideoController extends BasicController{

	//[1] 上传文件临时路径：window路径：E:/videos_dev/{用户id}/video,linux路径：/videos_dev/{用户id}/temp_video
	private static final String tempUploadPath = File.separator + "{userId}" + File.separator + "video"+ File.separator + "temp_video";
	//[2] window路径：E:/videos_dev/{用户id}/video/tempCover,linux路径：/videos_dev/{用户id}/video/temp_cover
	private static final String tempNoMp3Path = File.separator + "{userId}" + File.separator + "video" + File.separator + "temp_cover";
	//[3] window路径：E:/videos_dev/{用户id}/video/tempCover,linux路径：/videos_dev/{用户id}/video/final_video
	private static final String finalUploadPath = File.separator + "{userId}" + File.separator + "video" + File.separator + "final_video";

	@Autowired
	private BgmService bgmService;

	@Autowired
	private VideoService videoService;

	@Autowired
	private ResourceConfig resourceConfig;


	@ApiOperation(value = "上传短视频接口",notes = "上传短视频的接口")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "userId",value = "用户的id",required = true,dataType = "String",paramType = "form"),
	@ApiImplicitParam(name = "bgmId",value = "背景音乐的id",required = false,dataType = "String",paramType = "form"),
	@ApiImplicitParam(name = "videoSeconds",value = "视频的长度",required = true,dataType = "String",paramType = "form"),
	@ApiImplicitParam(name = "videoWidth",value = "视频宽度",required = true,dataType = "String",paramType = "form"),
	@ApiImplicitParam(name = "videoHeight",value = "视频高度",required = true,dataType = "String",paramType = "form"),
	@ApiImplicitParam(name = "desc",value = "视频描述",required = false,dataType = "String",paramType = "form")
	})
	@PostMapping( value = "/upload" ,headers = "content-type=multipart/form-data")
	public JSONResult upload(HttpServletRequest request, String userId, String bgmId,
							 Double videoSeconds, int videoWidth,int videoHeight, String desc,
							 @ApiParam(value = "短视频",required = true) MultipartFile file) throws Exception {

		// 文件保存的命名空间
		String FILE_SPACE = resourceConfig.getFileSpace();
		// 每页分页的记录数
		Integer PAGE_SIZE = Integer.valueOf(resourceConfig.getPageSize());

		//获取项目路径
		String basePath = request.getSession().getServletContext().getRealPath("");
		System.out.println(basePath);

		if (StringUtils.isBlank(userId)){
			return JSONResult.errorMsg("用户id不能为空...");
		}

		//(1) 上传到磁盘目录
	    String tempUploadDir = FILE_SPACE +tempUploadPath.replace("{userId}",userId);
	    //String tempUploadDB = tempUploadPath.replace("{userId}",userId);

       //(2) 在磁盘通过ffmpeg转换后的去掉mp3的目录
		String tempNoMp3PathDir = FILE_SPACE +tempNoMp3Path.replace("{userId}",userId);
		//String tempNoMp3PathDB = tempNoMp3Path.replace("{userId}",userId);

		//(3) 最终封面磁盘保存路径
		String  tempCoverPathDir= FILE_SPACE + finalUploadPath.replace("{userId}",userId);
		//(3) 最终数据库路径
		String  tempCoverPathDB= finalUploadPath.replace("{userId}",userId);

		//(4) 最终磁盘保存路径
		String finalUploadPathDir = FILE_SPACE + finalUploadPath.replace("{userId}",userId);
		//(4) 最终数据库保存路径
		String finalUploadPathDB = finalUploadPath.replace("{userId}",userId);

		String tempUploadVideo="";
		String videoOutputName="";

		//随机生成的文件名称
		String randomFileName = UUID.randomUUID().toString().replace("-","");
		videoOutputName =  File.separator + randomFileName + ".mp4";
		tempCoverPathDir += File.separator + randomFileName + ".jpg";
/******************************************************** 1 上传视频到临时目录  start **********************************************************/
		if(file !=null ){
				String fileName = file.getOriginalFilename();
				// abc.mp4
				//String arrayFilenameItem[] =  fileName.split("\\.");
				//String fileNamePrefix = "";
//				for (int i = 0 ; i < arrayFilenameItem.length-1 ; i ++) {
//					fileNamePrefix += arrayFilenameItem[i];
//				}
				// fix bug: 解决小程序端OK，PC端不OK的bug，原因：PC端和小程序端对临时视频的命名不同
				String fileNamePrefix = fileName.split("\\.")[0];

				if (StringUtils.isNotBlank(fileName)){
					//文件上传磁盘的最终保存路径====磁盘
					tempUploadVideo = tempUploadDir + File.separator +fileName;
					//封面保存目录===手机端有问题，废弃使用ffmpeg方式获取封面
//					tempCoverPathDB = tempCoverPathDB + File.separator + fileNamePrefix + ".jpg"; //内容：xxx/xxx/xxx/xxx.jpg
					// [1] 创建目录===并上传文件到临时目录
					mkFileDir(file.getInputStream(),tempUploadVideo);
				}
			}else{
				return JSONResult.errorMsg("上传出错...");
			}
/******************************************************** 1 上传视频到临时目录  end **********************************************************/

/******************************************************** 2 去出原来的音频    start **********************************************************/
		// 判断bgmId是否为空，如果不为空，
		// 那就查询bgm的信息，并且合并视频，生产新的视频
		if (StringUtils.isNotBlank(bgmId)){
			Bgm bgm = bgmService.queryBgmById(bgmId);
			String mp3InputPath = FILE_SPACE + bgm.getPath();

			// 1、视频转换的临时目录
			String tempNoMp3Path = tempNoMp3PathDir + File.separator + videoOutputName;
			// 2、生成目录
			//File file3 = new File(tempNoMp3PathDir);
			//FileInputStream fileInputStream = new FileInputStream(file3);

			//mkFileDir(null,tempNoMp3PathDir);
			//mkFileDir(fileInputStream,finalUploadPathDir);

			//[1] 提取视频====去掉音频
			FFMpegUtils.videoSplit(tempUploadVideo,videoSeconds,tempNoMp3Path);
			// 2 合成音频 + 视频
			finalUploadPathDir +=  videoOutputName;
			FFMpegUtils.videoMerge(tempNoMp3Path, mp3InputPath, videoSeconds, finalUploadPathDir);
		}else{
			finalUploadPathDir += videoOutputName;
			//判断
//			FFMpegUtils.isPathExist(finalUploadPathDir);
//			FFMpegUtils.isPathExist(tempUploadDir);
			File file3 = new File(tempUploadVideo);
			FileInputStream fileInputStream = new FileInputStream(file3);
			mkFileDir(fileInputStream,finalUploadPathDir);
		}
/******************************************************** 2 去出原来的音频  end **********************************************************/

/******************************************************** 3 对视频进行截图  start **********************************************************/

		// 对视频进行截图
		FFMpegUtils.getCover(finalUploadPathDir , tempCoverPathDir );
		// 保存视频信息到数据库
		Videos video = new Videos();
		video.setAudioId(bgmId);
		video.setUserId(userId);
		video.setVideoSeconds((float)((double)videoSeconds));
		video.setVideoHeight(videoHeight);
		video.setVideoWidth(videoWidth);
		video.setVideoDesc(desc);

		String videoPath = finalUploadPathDir.replace(FILE_SPACE,"").replace("\\","/");
		String coverPath = tempCoverPathDir.replace(FILE_SPACE,"").replace("\\","/");
		video.setVideoPath(videoPath);
		video.setCoverPath(coverPath);
		video.setStatus(VideoStatusEnum.SUCCESS.value);
		video.setCreateTime(new Date());
		//数据库保存的相对路径      ====数据库
		String videoId = videoService.saveVideo(video);

/******************************************************** 3 对视频进行截图  start **********************************************************/

	//	return JSONResult.ok();//返回id，用于下一步更新视频封面
		return JSONResult.ok(videoId);//返回id，用于下一步更新视频封面

	}


	@ApiOperation(value="上传封面", notes="上传封面的接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId",value = "用户的id",required = true,dataType = "String",paramType = "form"),
			@ApiImplicitParam(name = "videoId",value = "视频主键id",required = true,dataType = "String",paramType = "form"),
	})
	@PostMapping( value = "/uploadCover" ,headers = "content-type=multipart/form-data")
	public JSONResult uploadCover(HttpServletRequest request, String userId, String videoId,
								  @ApiParam(value = "视频封面",required = true)
										  MultipartFile file) throws Exception {

		// 文件保存的命名空间
		String FILE_SPACE = resourceConfig.getFileSpace();

		String basePath = request.getSession().getServletContext().getRealPath("");
		System.out.println(basePath);

		if (StringUtils.isBlank(videoId)||StringUtils.isBlank(userId)){
			return JSONResult.errorMsg("视频主键id和用户id不能为空...");
		}

		// 保存到数据库中的相对路径
		String uploadPathDB = "/" + userId + "/video";
		String coverPathDB = "/" + userId + "/video";

		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		// 文件上传的最终保存路径
		String finalCoverPath = "";

		try {
			if(file !=null ){
				String fileName = file.getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)){
					//文件上传的最终保存路径
					finalCoverPath=FILE_SPACE + uploadPathDB +("/cover/" +fileName);
					//数据库保存的相对路径
					uploadPathDB+=("/cover/"+fileName);

					//	coverPathDB = coverPathDB + "/" + fileNamePrefix + ".jpg";

					File outFile = new File(finalCoverPath);
					if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
						// 创建父文件夹
						outFile.getParentFile().mkdirs();
					}
					fileOutputStream = new FileOutputStream(outFile);
					inputStream = file.getInputStream();
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
		videoService.updateVideo(videoId, uploadPathDB);

		return JSONResult.ok();

	}




	/**
	 * @Description: 分页和搜索查询视频列表
	 * isSaveRecord：1 - 需要保存
	 * 				 0 - 不需要保存 ，或者为空的时候
	 */
	@ApiOperation(value="视频列表,同时完成热搜词的保存", notes="视频列表的接口")
	@PostMapping(value="/showAll")
	public JSONResult showAll(@RequestBody Videos video, Integer isSaveRecord,
							  Integer page, Integer pageSize) throws Exception {

		// 每页分页的记录数
		Integer PAGE_SIZE = Integer.valueOf(resourceConfig.getPageSize());

		if (page == null){page = 1;}
		if (pageSize == null) {pageSize = PAGE_SIZE; }
		PagedResult result = videoService.getAllVideos(video, isSaveRecord, page, pageSize);
		return JSONResult.ok(result);
	}

	@ApiOperation(value="热搜词查询", notes="热搜词查询的接口")
	@PostMapping( value = "/hot")
	public JSONResult hot(){
        List<String> list = videoService.getHotWords();
        return JSONResult.ok(list);
	}

	@ApiOperation(value="用户收藏", notes="用户收藏的接口")
	@PostMapping(value="/userLike")
	public JSONResult userLike(String userId, String videoId, String videoCreaterId)
			throws Exception {
		videoService.userLikeVideo(userId, videoId, videoCreaterId);
		return JSONResult.ok();
	}
	@ApiOperation(value="取消用户收藏", notes="取消用户收藏的接口")
	@PostMapping(value="/userUnLike")
	public JSONResult userUnLike(String userId, String videoId, String videoCreaterId) throws Exception {
		videoService.userUnLikeVideo(userId, videoId, videoCreaterId);
		return JSONResult.ok();
	}

	/**
	 * @Description: 我关注的人发的视频
	 */
	@ApiOperation(value="我关注的人发的视频", notes="我关注的人发的视频的接口")
	@PostMapping("/showMyFollow")
	public JSONResult showMyFollow(String userId, Integer page) throws Exception {

		if (StringUtils.isBlank(userId)) {
			return JSONResult.ok();
		}
		if (page == null) {
			page = 1;
		}
		int pageSize = 6;
		PagedResult videosList = videoService.queryMyFollowVideos(userId, page, pageSize);
		return JSONResult.ok(videosList);
	}

	/**
	 * @Description: 我收藏(点赞)过的视频列表
	 */
	@ApiOperation(value="我收藏(点赞)过的视频列表的视频", notes="我收藏(点赞)过的视频列表的接口")
	@PostMapping("/showMyLike")
	public JSONResult showMyLike(String userId, Integer page, Integer pageSize) throws Exception {
		if (StringUtils.isBlank(userId)) { return JSONResult.ok(); }
		if (page == null) {page = 1; }
		if (pageSize == null) {pageSize = 6; }
		PagedResult videosList = videoService.queryMyLikeVideos(userId, page, pageSize);
		return JSONResult.ok(videosList);
	}

	//保存评论
	@PostMapping("/saveComment")
	public JSONResult saveComment(@RequestBody Comments comment,
									   String fatherCommentId, String toUserId) throws Exception {
		comment.setFatherCommentId(fatherCommentId);
		comment.setToUserId(toUserId);
		videoService.saveComment(comment);
		return JSONResult.ok();
	}

	@ApiOperation(value="获取视频描述", notes="获取视频描的接口")
	@PostMapping("/getVideoComments")
	public JSONResult getVideoComments(String videoId, Integer page, Integer pageSize) throws Exception {

		if (StringUtils.isBlank(videoId)) {
			return JSONResult.ok();
		}
		// 分页查询视频列表，时间顺序倒序排序
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		//获取所有评论
		PagedResult list = videoService.getAllComments(videoId, page, pageSize);
		return JSONResult.ok(list);
	}
	//创建目录
	public void mkFileDir(InputStream inStream,String tempUploadVideo) throws Exception {
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = inStream;

		try {
			File outFile = new File(tempUploadVideo);
			// [1] 创建目录
			if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
				outFile.getParentFile().mkdirs();
			}
			fileOutputStream = new FileOutputStream(outFile);
			//inputStream = file.getInputStream();
			// [2] 文件拷贝
			if (inStream != null){
				IOUtils.copy(inputStream, fileOutputStream);
			}

		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(inputStream!=null){
				inputStream.close();
			}
			if(fileOutputStream!=null){
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		}
	}

}
