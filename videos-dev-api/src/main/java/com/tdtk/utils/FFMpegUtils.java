package com.tdtk.utils;

import jdk.internal.dynalink.beans.StaticClass;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ffmpeg转换工具类
 */
public class  FFMpegUtils {
    private static String ffmpegEXE = "D:\\ffmpeg\\bin\\ffmpeg.exe";//ffmpeg命令路径


    public FFMpegUtils() {
    }

    /**
     * 视频格式转换
     * @param videoInputPath
     * @param videoOutputPath
     * @throws Exception
     */
    public static void convertor(String videoInputPath,String videoOutputPath) throws Exception {
        //判断路径是否存在
        isPathExist(videoInputPath);
        isPathExist(videoOutputPath);
        //ffmpeg -i input.mp4 output.avi

        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-y");
        command.add(videoOutputPath);

        for ( String com:command){
            System.out.print(com+" ");

        }
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String readLine="";
        while ((readLine=bufferedReader.readLine())!=null){

        }

        if(bufferedReader!=null){
            bufferedReader.close();
        }
        if(inputStreamReader!=null){
            inputStreamReader.close();
        }
        if(errorStream!=null){
            errorStream.close();
        }
    }


    /**
     * 合并 merge
     * @param videoInputPath
     * @param audioInputPath
     * @param seconds
     * @param videoOutputPath
     * @throws Exception
     */
    public static void  videoMerge( String videoInputPath,String audioInputPath,Double seconds,String videoOutputPath )  throws Exception{
        //判断路径是否存在
        isPathExist(videoInputPath);
        isPathExist(videoOutputPath);
        //D:\ffmpeg\bin\ffmpeg.exe -i 10s.mp4 -i time001.mp3 -t 10 新视频.mp4

        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-i");
        command.add(audioInputPath);
        command.add("-t");
        command.add(String.valueOf(seconds));
        command.add("-y");
        command.add(videoOutputPath);

        for ( String com:command){
            System.out.print(com+" ");

        }
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String readLine="";
        while ((readLine=bufferedReader.readLine())!=null){

        }

        if(bufferedReader!=null){
            bufferedReader.close();
        }
        if(inputStreamReader!=null){
            inputStreamReader.close();
        }
        if(errorStream!=null){
            errorStream.close();
        }
    }


    /**
     * 分离出音频
     * @param videoInputPath
     * @param seconds
     * @param audioOutputPath
     * @throws Exception
     */
    public static void  audioSplit( String videoInputPath,Double seconds,String audioOutputPath )  throws Exception{
        //判断路径是否存在
        isPathExist(videoInputPath);
        isPathExist(audioOutputPath);

        // ffmpeg -i input_file -y output_file_audio

        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);

        //时间参数判断
        if (null!=seconds){
            command.add("-t");
            command.add(String.valueOf(seconds));
        }
        command.add("-y");
        command.add(audioOutputPath);

        for ( String com:command){
            System.out.print(com+" ");

        }
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String readLine="";
        while ((readLine=bufferedReader.readLine())!=null){

        }

        if(bufferedReader!=null){
            bufferedReader.close();
        }
        if(inputStreamReader!=null){
            inputStreamReader.close();
        }
        if(errorStream!=null){
            errorStream.close();
        }
    }

    /**
     * 分离出视频
     * @param videoInputPath
     * @param seconds
     * @param videoOutputPath
     * @throws Exception
     */
    public static void  videoSplit( String videoInputPath,Double seconds,String videoOutputPath )  throws Exception{
        //判断路径是否存在
        isPathExist(videoInputPath);
        isPathExist(videoOutputPath);

        //D:\ffmpeg\bin\ffmpeg.exe -i 003_ldxy2_10s.mp4 -vcodec copy -an  8888.mp4

        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-vcodec");
        command.add("copy");
        command.add("-an");

        //时间参数判断
        if (null!=seconds){
            command.add("-t");
            command.add(String.valueOf(seconds));
        }
        command.add("-y");
        command.add(videoOutputPath);

        for ( String com:command){
            System.out.print(com+" ");

        }
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String readLine="";
        while ((readLine=bufferedReader.readLine())!=null){

        }

        if(bufferedReader!=null){
            bufferedReader.close();
        }
        if(inputStreamReader!=null){
            inputStreamReader.close();
        }
        if(errorStream!=null){
            errorStream.close();
        }
    }

    /**
     * 视频旋转角度：90、180、270、360
     * @param videoInputPath
     * @param seconds
     * @param rotate
     * @param videoOutputPath
     * @throws Exception
     */
    public static void  videoRotate( String videoInputPath,Double seconds,Double rotate,String videoOutputPath )  throws Exception{
        //判断路径是否存在
        isPathExist(videoInputPath);
        isPathExist(videoOutputPath);

        //ffmpeg -i 10s.mp4 -metadata:s:v rotate="90" -codec copy output.mp4

        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);

        //角度判断
        if (null !=rotate){
            command.add("-metadata:s:v");
            command.add("rotate="+rotate);
        }
        command.add("-codec");
        command.add("copy");

        //时间参数判断
        if (null!=seconds){
            command.add("-t");
            command.add(String.valueOf(seconds));
        }
        command.add("-y");
        command.add(videoOutputPath);

        for ( String com:command){
            System.out.print(com+" ");

        }
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String readLine="";
        while ((readLine=bufferedReader.readLine())!=null){

        }

        if(bufferedReader!=null){
            bufferedReader.close();
        }
        if(inputStreamReader!=null){
            inputStreamReader.close();
        }
        if(errorStream!=null){
            errorStream.close();
        }
    }


    /**
     * 视频切割
     * @param startTime
     * @param endTime
     * @param videoInputPath
     * @param videoOutputPath
     * @throws Exception
     */
    public static void  splitVideo( String startTime,String endTime,String videoInputPath,String videoOutputPath )  throws Exception{
        //判断路径是否存在
        isPathExist(videoInputPath);
        isPathExist(videoOutputPath);

        //ffmpeg -ss 00:00:00 -t 00:00:30 -i test.mp4 -vcodec copy -acodec copy output.mp4

        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);

        command.add("-ss");
        command.add(startTime);

        command.add("-t");
        command.add(endTime);

        command.add("-i");
        command.add(videoInputPath);

        command.add("-vcodec");
        command.add("copy");
        command.add("-acodec");
        command.add("copy");

        command.add("-y");
        command.add(videoOutputPath);

        for ( String com:command){
            System.out.print(com+" ");

        }
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String readLine="";
        while ((readLine=bufferedReader.readLine())!=null){

        }

        if(bufferedReader!=null){
            bufferedReader.close();
        }
        if(inputStreamReader!=null){
            inputStreamReader.close();
        }
        if(errorStream!=null){
            errorStream.close();
        }
    }


    /**
     * 截取封面图
     * @param videoInputPath
     * @param coverOutputPath
     * @throws IOException
     * @throws InterruptedException
     */
    public static void getCover(String videoInputPath, String coverOutputPath) throws IOException, InterruptedException {

        //判断路径是否存在
        isPathExist(videoInputPath);
        isPathExist(coverOutputPath);

//		ffmpeg.exe -ss 00:00:01 -i spring.mp4 -vframes 1 bb.jpg
        List<String> command = new java.util.ArrayList<String>();
        command.add(ffmpegEXE);

        // 指定截取第1秒
        command.add("-ss");
        command.add("00:00:01");

        command.add("-y");
        command.add("-i");
        command.add(videoInputPath);

        command.add("-vframes");
        command.add("1");

        command.add(coverOutputPath);

        for (String c : command) {
            System.out.print(c + " ");
        }

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ( (line = br.readLine()) != null ) {
        }

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
    }


    /**
     * 判断路径是否存在
     */
    public static void isPathExist(String path){
        File oF = new File(path);
        if (oF.getParentFile() != null || !oF.getParentFile().isDirectory()) {
            oF.getParentFile().mkdirs();
        }
    }

    public static void main(String[] args){
        FFMpegUtils ffMpegUtils = new FFMpegUtils();

        try {
            //[1]视频转换命令
            //ffMpegUtils.convertor("E:\\videos_dev\\bgm\\view001.mp4","E:\\videos_dev\\bgm\\测试.avi");

            //[2]音、视频合成命令
            //D:\ffmpeg\bin\ffmpeg.exe -i 10s.mp4 -i time001.mp3 -t 10 新视频.mp4
            //ffMpegUtils.videoMerge("C:\\Users\\Administrator\\Desktop\\bgm\\split\\004_ldxy2_10s.mp4","C:\\Users\\Administrator\\Desktop\\bgm\\split\\004_wmbyy_30.mp3",15d,"C:\\Users\\Administrator\\Desktop\\bgm\\split\\测试.mp4");
            //ffMpegUtils.videoMerge("C:\\Users\\Administrator\\Desktop\\bgm\\split\\003_ds_17s.mp4","C:\\Users\\Administrator\\Desktop\\bgm\\split\\003_ds_40.mp3",15d,"C:\\Users\\Administrator\\Desktop\\bgm\\split\\测试.mp4");

           //[3]将音频流从视频文件中分离出来
           // ffmpeg -i input_file -y output_file_audio　
            //ffMpegUtils.audioSplit("C:\\Users\\Administrator\\Desktop\\bgm\\split\\666.mp4",5d,"C:\\Users\\Administrator\\Desktop\\bgm\\split\\333.mp3");

            //[4]将视频流从视频文件中分离出来
            //D:\ffmpeg\bin\ffmpeg.exe -i 003_ldxy2_10s.mp4 -vcodec copy -an  8888.mp4
           // ffMpegUtils.videoSplit("C:\\Users\\Administrator\\Desktop\\bgm\\split\\666.mp4",5d,"C:\\Users\\Administrator\\Desktop\\bgm\\split\\333.mp4");

            //[5]旋转90度
            //ffmpeg -i 10s.mp4 -metadata:s:v rotate="90" -codec copy output.mp4
           // ffMpegUtils.videoRotate("C:\\Users\\Administrator\\Desktop\\bgm\\split\\666.mp4",15d,90d,"C:\\Users\\Administrator\\Desktop\\bgm\\split\\333.mp4");


            //[6]视频截取
            ffMpegUtils.splitVideo("00:26:53","01:00:00","G:\\BaiduNetdiskDownload\\第09章 开发短视频后台管理系统\\9.01.mp4","G:\\BaiduNetdiskDownload\\第09章 开发短视频后台管理系统\\66.mp4");


            //[7]截取封面图
            //ffMpegUtils.getCover("C:\\Users\\Administrator\\Desktop\\bgm\\split\\004_ldxy2_10s.mp4","C:\\Users\\Administrator\\Desktop\\bgm\\split\\测试.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
