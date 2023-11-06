package com.coderdream.freeapps.util.video.ffmpeg.v2;

import java.util.HashMap;

/**
 * @author CoderDream
 */
public class Test {

    public static void main(String[] args) {
        ProcessExec ps = new ProcessExec();
        HashMap<String, String> dto=new HashMap<String, String>();
        dto.put("ffmpeg_path","G:\\Program Files\\FFmpeg\\bin\\ffmpeg.exe ");//必填:此处是ffmpeg.exe所在位置，也就FFmpeg文件夹bin目录下的ffmpeg.exe
        dto.put("input_path", "G:\\05如何将牛人身上的技能都榨干？.mp4");//必填；此处是你要处理的视频位置
        dto.put("video_converted_path", "G:\\video\\TEST1.mp4");//必填；此处是完成添加水印后输入视频的位置并重新命名该视频
        dto.put("logo", "D\\\\:/20160512155254687.png");//必填；此处是你要添加的水印位置,注意此处图片位置一定要加上转译符，否则识别不了盘符
        ps.execute(dto);

    }

}
