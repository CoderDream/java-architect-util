# subtitle-merge
字幕去重、合并和单行字幕

使用方法:

1. 执行命令, java -jar subtitle-merge-1.0-SNAPSHOT.jar 上方字幕文件名 下方字幕文件名 合并文件名

```
java -jar subtitle-merge-1.0-SNAPSHOT.jar Subtitle_1.srt Subtitle_5.srt Subtitle_merge.srt
```

- Subtitle_1.srt

```
1
00:00:02,20 --> 00:00:04,53
["Daydreamer" by Aurora playing]

2
00:01:21,88 --> 00:01:25,51
Good morning. And welcome to WWDC.

3
00:01:21,88 --> 00:01:25,51
Good morning. And welcome to WWDC.

4
00:01:25,58 --> 00:01:31,62
WWDC is an incredibly important event
to Apple, our developers and our users.

5
00:01:25,58 --> 00:01:31,62
WWDC is an incredibly important event
to Apple, our developers and our users.

6
00:01:32,02 --> 00:01:35,32
It's here that we bring
some of our biggest innovations to life.
```

- Subtitle_5.srt

```
1
00:01:21,88 --> 00:01:25,51
早上好 欢迎参加 WWDC 全球开发者大会

2
00:01:21,88 --> 00:01:25,51
早上好 欢迎参加 WWDC 全球开发者大会

3
00:01:25,58 --> 00:01:31,62
WWDC 全球开发者大会对于 Apple
我们的开发者以及用户而言极其重要

4
00:01:25,58 --> 00:01:31,62
WWDC 全球开发者大会对于 Apple
我们的开发者以及用户而言极其重要

5
00:01:32,02 --> 00:01:35,32
我们正是在这里实现了一些最棒的创新

6
00:01:35,82 --> 00:01:37,46
我们不会停下创新的脚步
```

- Subtitle_merge.srt

```
1
00:00:02,20 --> 00:00:04,53
["Daydreamer" by Aurora playing]

2
00:01:21,88 --> 00:01:25,51
Good morning. And welcome to WWDC.
早上好 欢迎参加 WWDC 全球开发者大会

4
00:01:25,58 --> 00:01:31,62
WWDC is an incredibly important event to Apple, our developers and our users.
WWDC 全球开发者大会对于 Apple 我们的开发者以及用户而言极其重要

6
00:01:32,02 --> 00:01:35,32
It's here that we bring some of our biggest innovations to life.
我们正是在这里实现了一些最棒的创新
```



### mkv字幕提取

gMKVExtractGUI

把文件拖拽到输入框，勾选字幕轨道，然后选[Extrat]

 ![image-20220704181012928](images\image-20220704181012928.png)

### mp4字幕提取工具

My MP4Box GUI

Demux 页签 -> Select file... [Open] ，勾选字幕

 ![image-20220704180451100](images\image-20220704180451100.png)







# END
