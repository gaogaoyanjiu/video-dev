package com.tdtk.utils;

import java.util.HashMap;
import java.util.Map;

public  class Contents {
//   http://jx.anlehe.com/?url=

//    电影 cat=all 默认，cat=100 爱情 ，cat=102 恐怖，cat=103 喜剧，cat=104 科幻，cat=105犯罪，cat=106 动作，cat=107 动画，cat=108 战争，cat=112 剧情，cat=113 奇幻，cat=115 悬疑，cat=123 惊悚
//    电视 cat=all 默认，cat=101 言情 ，cat=105 伦理 ，cat=109 喜剧 ，cat=108 悬疑 ，cat=111 都市 ，cat=100 偶像 ，cat=104 古装 ，cat=103 警匪 ，cat=112 历史 ，cat=106 武侠 ，cat=113 科幻 ，cat=117 神话 ，cat=115 动作 ，cat=114 情景
//    综艺 cat=all 默认，cat=101 选秀 ，cat=102 八卦 ，cat=103 访谈 ，cat=104 情感 ，cat=105 生活 ，cat=106 晚会 ，cat=107 搞笑 ，cat=108 音乐 ，cat=110 游戏 ，cat=111 少儿 ，cat=112 体育 ，cat=113 记实 ，cat=116 歌舞 ，cat=119 报道
//    动漫 cat=all 默认，cat=100 热血 ，cat=101 恋爱 ，cat=102 美少女 ，cat=103 运动 ，cat=104 校园 ，cat=105 搞笑 ，cat=106 幻想 ，cat=107 冒险 ，cat=108 悬疑 ，cat=109 魔幻 ，cat=110 动物，cat=111 少儿，cat=131 亲子，cat=114 益智


    //视频大类别
    private static Map<String,String> catalogVideoMap= new HashMap<>(0);

    //大类里面的细类别
    //电影类别
    private static Map<String,String> filmMap= new HashMap<>(0);
    //电视类别
    private static Map<String,String> tvplayMap= new HashMap<>(0);
    //综艺类别
    private static Map<String,String> vaMap= new HashMap<>(0);
    //动漫类别
    private static Map<String,String> cartoonMap= new HashMap<>(0);


    public static Map<String,String> getCatalogVideoMap(){
        catalogVideoMap.put("film","电影");
        catalogVideoMap.put("tvplay","电视");
        catalogVideoMap.put("va","综艺");
        catalogVideoMap.put("cartoon","动漫");

        return catalogVideoMap;
    }

    public static Map<String,String> getFilmMap(){
                filmMap.put("all","默认");
                filmMap.put("100","爱情");
                filmMap.put("102","恐怖");
                filmMap.put("103","喜剧");
                filmMap.put("104","科幻");
                filmMap.put("105","犯罪");
                filmMap.put("106","动作");
                filmMap.put("107","动画");
                filmMap.put("108","战争");
                filmMap.put("112","剧情");
                filmMap.put("113","奇幻");
                filmMap.put("115","悬疑");
                filmMap.put("123","惊悚");

        return filmMap;
    }

    public static Map<String,String> getTvplayMap(){
        return tvplayMap;
    }
    public static Map<String,String> getVaMap(){
        return vaMap;
    }
    public static Map<String,String> getCartoonMap(){
        return cartoonMap;
    }
}
