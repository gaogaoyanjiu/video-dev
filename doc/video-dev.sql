
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bgm
-- ----------------------------
DROP TABLE IF EXISTS `bgm`;
CREATE TABLE `bgm` (
  `id` varchar(64) NOT NULL,
  `author` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL COMMENT '播放地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of bgm
-- ----------------------------
INSERT INTO `bgm` VALUES ('18102624D6234G0H', '音乐001', '音乐001', '/upload/videos_bgm/001.mp3');
INSERT INTO `bgm` VALUES ('181026255BSB5Y5P', '音乐002', '音乐002', '/upload/videos_bgm/002测试.mp3');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` varchar(20) NOT NULL,
  `father_comment_id` varchar(20) DEFAULT NULL,
  `to_user_id` varchar(20) DEFAULT NULL,
  `video_id` varchar(20) NOT NULL COMMENT '视频id',
  `from_user_id` varchar(20) NOT NULL COMMENT '留言者，评论的用户id',
  `comment` text NOT NULL COMMENT '评论内容',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程评论表';

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('320143672450482176', 'undefined', 'undefined', '317434935453417472', '316593590182608896', '非常vv', '2018-10-26 16:34:57');
INSERT INTO `comments` VALUES ('320143695890350080', 'undefined', 'undefined', '317434935453417472', '316593590182608896', '巡考', '2018-10-26 16:35:08');
INSERT INTO `comments` VALUES ('320173990555418624', 'undefined', 'undefined', '317454629434556416', '316593590182608896', 'awdas', '2018-10-26 20:35:53');
INSERT INTO `comments` VALUES ('320174025479290880', 'undefined', 'undefined', '317454629434556416', '316593590182608896', '阿瑟東撒的', '2018-10-26 20:36:10');

-- ----------------------------
-- Table structure for search_records
-- ----------------------------
DROP TABLE IF EXISTS `search_records`;
CREATE TABLE `search_records` (
  `id` varchar(64) NOT NULL,
  `content` varchar(255) NOT NULL COMMENT '搜索的内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频搜索的记录表';

-- ----------------------------
-- Records of search_records
------------------------------

INSERT INTO `search_records` VALUES ('317228158319329280', 'java');
INSERT INTO `search_records` VALUES ('317228290433613824', 'java');
INSERT INTO `search_records` VALUES ('317228409834962944', 'java');
INSERT INTO `search_records` VALUES ('317228595856539648', 'java');
INSERT INTO `search_records` VALUES ('317213433814581248', 'java');
INSERT INTO `search_records` VALUES ('317229129619472384', 'zookeeper');
INSERT INTO `search_records` VALUES ('317229402419101696', 'zookeeper');
INSERT INTO `search_records` VALUES ('317229545245638656', 'zookeeper');
INSERT INTO `search_records` VALUES ('317229622980771840', 'zookeeper');
INSERT INTO `search_records` VALUES ('317236591537946624', 'zookeeper');
INSERT INTO `search_records` VALUES ('317236937446391808', '大数据');
INSERT INTO `search_records` VALUES ('317236955412692992', '大数据');
INSERT INTO `search_records` VALUES ('317241039458926592', '大数据');
INSERT INTO `search_records` VALUES ('317241387628101632', '大数据');
INSERT INTO `search_records` VALUES ('317241422346452992', '大数据');
INSERT INTO `search_records` VALUES ('5', 'springboot');
INSERT INTO `search_records` VALUES ('6', 'springboot');
INSERT INTO `search_records` VALUES ('7', 'springboot');
INSERT INTO `search_records` VALUES ('8', 'springboot');
INSERT INTO `search_records` VALUES ('9', 'springboot');

-- ----------------------------
-- Table structure for tv_play
-- ----------------------------
DROP TABLE IF EXISTS `tv_play`;
CREATE TABLE `tv_play` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tv_name` varchar(100) DEFAULT NULL COMMENT '电视剧名称',
  `tv_episodes` varchar(20) DEFAULT NULL COMMENT '剧集',
  `tv_createtime` datetime NOT NULL COMMENT '创建时间',
  `tv_updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `tv_isdel` char(1) DEFAULT NULL COMMENT '是否删除(1是，0否)',
  `tv_publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `tv_type` varchar(20) DEFAULT NULL COMMENT '影片分类',
  `tv_is_recommend` char(1) DEFAULT NULL COMMENT '是否推荐',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电视剧表';

-- ----------------------------
-- Records of tv_play
-- ----------------------------

-- ----------------------------
-- Table structure for t_film
-- ----------------------------
DROP TABLE IF EXISTS `t_film`;
CREATE TABLE `t_film` (
  `id` varchar(255) NOT NULL,
  `actor` varchar(255) DEFAULT NULL,
  `catalog_name` varchar(255) DEFAULT NULL,
  `catalog_id` varchar(255) DEFAULT NULL,
  `evaluation` double NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_use` int(11) NOT NULL,
  `loc_name` varchar(255) DEFAULT NULL,
  `loc_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `on_decade` varchar(255) DEFAULT NULL,
  `plot` text,
  `resolution` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `sub_class_name` varchar(255) DEFAULT NULL,
  `sub_class_id` varchar(255) DEFAULT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  `type_id` varchar(255) DEFAULT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  `is_vip` int(11) DEFAULT '0',
  `page_link` varchar(255) DEFAULT NULL COMMENT 'a标签链接地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电影主表';

-- ----------------------------
-- Records of t_film
-- ----------------------------
INSERT INTO `t_film` VALUES ('320107081858285568', '张三', '电影', 'film', '8.8', 'public/static/upload/filmPic/333333.jpg', '1', '美国', 'f39c979857a4c8c50157a9002ff9001c', '肖申克的救赎5', '2008', '<p>终身监禁的惩罚无疑注定了安迪接下来灰暗绝望的人生。</p>', '1008', '全集', '动作片', 'f3988888', '爱情片', 'f3955555555', '2018-09-30 08:00:00', '0', '111/222/333.mp4');

-- ----------------------------
-- Table structure for t_film_res
-- ----------------------------
DROP TABLE IF EXISTS `t_film_res`;
CREATE TABLE `t_film_res` (
  `id` int(11) DEFAULT NULL,
  `film_id` varchar(255) DEFAULT NULL,
  `link_type` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `episodes` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电影资源表';

-- ----------------------------
-- Records of t_film_res
-- ----------------------------
INSERT INTO `t_film_res` VALUES ('1222', '320107081858285568', 'film', '/film/1313213124.mp4', '1');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(64) NOT NULL,
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `face_image` varchar(255) DEFAULT NULL COMMENT '我的头像，如果没有默认给一张',
  `nickname` varchar(20) NOT NULL COMMENT '昵称',
  `fans_counts` int(11) DEFAULT '0' COMMENT '我的粉丝数量',
  `follow_counts` int(11) DEFAULT '0' COMMENT '我关注的人总数',
  `receive_like_counts` int(11) DEFAULT '0' COMMENT '我接受到的赞美/收藏 的数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('180425CFA4RB6T0H', 'zhangsan', 'kU8h64TG/bK2Y91vRT9lyg==', '/180425CFA4RB6T0H/face/111.jpg', 'zhangsan', '0', '0', '0');
INSERT INTO `users` VALUES ('316593590182608896', 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', '/316593590182608896/face/tmp_1fe09b8a07ec94a4ea1813dd3b0ebf0df5d1c25f3aff7add.jpg', 'admin', '0', '0', '2');
INSERT INTO `users` VALUES ('320175118349238272', 'test', 'CY9rzUYh03PK3k6DJie09g==', null, 'test', '0', '0', '0');

-- ----------------------------
-- Table structure for users_fans
-- ----------------------------
DROP TABLE IF EXISTS `users_fans`;
CREATE TABLE `users_fans` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL COMMENT '用户',
  `fan_id` varchar(64) NOT NULL COMMENT '粉丝',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`fan_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户粉丝关联关系表';

-- ----------------------------
-- Records of users_fans
-- ----------------------------

-- ----------------------------
-- Table structure for users_like_videos
-- ----------------------------
DROP TABLE IF EXISTS `users_like_videos`;
CREATE TABLE `users_like_videos` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL COMMENT '用户',
  `video_id` varchar(64) NOT NULL COMMENT '视频',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_video_rel` (`user_id`,`video_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户喜欢的/赞过的视频';

-- ----------------------------
-- Records of users_like_videos
-- ----------------------------
INSERT INTO `users_like_videos` VALUES ('317438905427165184', '316593590182608896', '317436266744381440');
INSERT INTO `users_like_videos` VALUES ('320174100928528384', '316593590182608896', '317454629434556416');

-- ----------------------------
-- Table structure for users_report
-- ----------------------------
DROP TABLE IF EXISTS `users_report`;
CREATE TABLE `users_report` (
  `id` varchar(64) NOT NULL,
  `deal_user_id` varchar(64) NOT NULL COMMENT '被举报用户id',
  `deal_video_id` varchar(64) NOT NULL,
  `title` varchar(128) NOT NULL COMMENT '类型标题，让用户选择，详情见 枚举',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `userid` varchar(64) NOT NULL COMMENT '举报人的id',
  `create_date` datetime NOT NULL COMMENT '举报时间',
  `is_del` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='举报用户表';

-- ----------------------------
-- Records of users_report
-- ----------------------------
INSERT INTO `users_report` VALUES ('180521FZ501ABDYW', '180425CFA4RB6T0H', '317434935453417472', '引人不适', '不合时宜的视频', '316593590182608896', '2018-05-21 20:58:35', '0');
INSERT INTO `users_report` VALUES ('180521FZK44ZRWX4', '180425CFA4RB6T0H', '317436378520485888', '引人不适', '不合时宜的视频', '316593590182608896', '2018-05-21 20:59:53', '0');
INSERT INTO `users_report` VALUES ('180521FZR1TYRTXP', '180425CFA4RB6T0H', '317436266744381440', '辱骂谩骂', '不合时宜的视频', '316593590182608896', '2018-05-21 21:00:18', '0');

-- ----------------------------
-- Table structure for videos
-- ----------------------------
DROP TABLE IF EXISTS `videos`;
CREATE TABLE `videos` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL COMMENT '发布者id',
  `audio_id` varchar(64) DEFAULT NULL COMMENT '用户使用音频的信息',
  `video_desc` varchar(128) DEFAULT NULL COMMENT '视频描述',
  `video_path` varchar(255) NOT NULL COMMENT '视频存放的路径',
  `video_seconds` float(6,2) DEFAULT NULL COMMENT '视频秒数',
  `video_width` int(6) DEFAULT NULL COMMENT '视频宽度',
  `video_height` int(6) DEFAULT NULL COMMENT '视频高度',
  `cover_path` varchar(255) DEFAULT NULL COMMENT '视频封面图',
  `like_counts` bigint(20) NOT NULL DEFAULT '0' COMMENT '喜欢/赞美的数量',
  `status` int(1) NOT NULL COMMENT '视频状态：\r\n1、发布成功\r\n2、禁止播放，管理员操作',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频信息表';

-- ----------------------------
-- Records of videos
-- ----------------------------
INSERT INTO `videos` VALUES ('317434935453417472', '180425CFA4RB6T0H', '', '测试123', '/316593590182608896/video/tmp_289a8d765fa9de8e144c0351c76a0ec7e1991334fec863d4.mp4', '15.00', '272', '480', '/316593590182608896/video/cover/tmp_289a8d765fa9de8e144c0351c76a0ec7e1991334fec863d4.jpg', '0', '1', '2018-10-11 17:47:51');
INSERT INTO `videos` VALUES ('317439468919324672', '316593590182608896', '', '', '/316593590182608896/video/tmp_db59aa249ef36415440cfcfcdef921088ea8731219edcd2f.mp4', '19.00', '480', '272', '/316593590182608896/video/cover/tmp_db59aa249ef36415440cfcfcdef921088ea8731219edcd2f.jpg', '0', '1', '2018-10-11 18:23:52');
