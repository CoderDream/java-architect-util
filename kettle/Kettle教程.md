

### 使用Kettle实现数据实时增量同步





#### 准备数据

```sql
INSERT DELAYED INTO `im_message` ( `sender`, `send_time`, `receiver`, `content`, `is_read`, `read_time` ) SELECT
`sender`,
`send_time`,
`receiver`,
`content`,
`is_read`,
`read_time` 
FROM
	`im_message2` 
WHERE
	`send_time` < '2018-05-22 00:00:00' 
ORDER BY
	`send_time` 
	LIMIT 100;
```





#### 原始表输入

```sql
SELECT
	id,
	sender,
	send_time,
	receiver,
	content,
	is_read,
	read_time 
FROM
	im_message 
WHERE
	send_time >= date_sub( str_to_date( '${TIME_STAMP}', '%Y-%m-%d %H:%i:%s' ), INTERVAL $ { ROLL_BACK_DAYS } DAY );
```







###  Kettle增量同步变动数据



#### 需求

最近在用kettle同步数据的时候，有增量同步的需求。

之前也遇到过这个需求，查阅了很多文章，也试了很多方法，都没有实现我所需的简洁的方式。

这回在我一次次尝试无果的情况下，突然间的灵光一闪，让我豁然开朗，原来你就在我眼前。

写下这篇文章，让更多的人的时间得到节省。

时间是最稀缺的资源，更多的时间应该花在更有意义的事情上。

 

软件相关：

使用软件	kettle
软件版本	7.1
实现功能	使用kettle增量同步数据
修改日期	2018年11月6日



#### 操作过程

有TEST_A （左图）和TEST_B（右图）两张数据表，两张表结构相同（抱歉，图没截取规整，但不影响内容表达）。

​    ![](assets\20181106225725934.png) ![](assets\20181106225750616.png) 

ID字段均为唯一主键，TEST_A中自增，NUMBER类型，LASTUPDATEON字段表示该行数据最近插入或者修改的时间，DATE类型非空。

假设TEST_A为源数据表，TEST_B为目标表。

TEST_A中的历史数据变更时相应行的LASTUPDATEON字段值会变为数据更新时的时间。

 

根据以上信息，总结出如下增量更新步骤：

1）取TEST_B中LASTUPDATEON字段的最大值，这里为了方便起见，假设这个最大值为max_date_a；

2）取TEST_A中LASTUPDATEON字段大于max_date_a的所有数据行 rows；

3）以rows 数据的ID做对比同步到TEST_B表，如果ID值在TEST_B中存在，则更新除ID字段外的所有字段；

     如果ID值在TEST_B中不存在，则插入整行数据（类似 Oracle中的 MERGE INTO）。

 



#### Kettle操作

最终效果图    ![](assets\20181106231244753.png)

1）如上图所示，需要两个表输入和一个插入/更新，并将三个步骤间的线连接好。

2）MAX_DATE步骤中，配置好数据库连接，连接到TEST_B，SQL如下（注意结尾没有分号 ';'）

```
SELECT MAX(LASTUPDATEDON) FROM TEST_B
```

其他配置默认，点击预览，看到类似下图数据表示这一步成功。然后点 “确定”。

  ![](assets\20181106231718955.png)

3）在select_a步骤中，同样配置好数据库连接，连接到TEST_A表，SQL如下（同样结尾没有分号 ';'，大于号后边写问号'?'替换上一步的值）

```
SELECT * FROM TEST_A WHERE LASTUPDATEDON > ?
```

然后在“从步骤输入数据”中选择上一个步骤的名称，如此可将上一个步骤获取的最大时间作为问号位置的值，数据类型仍然为时间类型。

4）然后勾选“执行每一行”，这是为了select_a步骤在MAX_DATE执行完后才执行，从而获取时间大值(2020年4月1日改，这里不选“执行每一行”也可以，Kettle长期使用的经验)。点击“确定”，此时前两 个步骤间的连线上会多出一个感叹号图案，正常。

![](assets\20181106232650123.png)

 

5）在insert_b中，首先配置好“数据库连接”，连接到“目标表”test_b。

6）在下图中的①区域，点击“获取更新字段”，然后在出现的很多行字段中，只留下ID字段行，删除其余字段行（因为根据文章描述该步骤应该比较ID字段来进行同步数据）。①区域作用是配置比较的字段。

7）在②区域点击“获取和更新字段”，然后找到在①区域中被比较的字段，将其“更新”下的值改为“N”，表示更新时不更新该字段，但会在满足插入条件（前文“增量更新步骤”中已描述清楚本文的插入条件）时插入该字段，其他字段也会被插入。点击“确定”。

 ![](assets\20181106234025326.png)

8）一切设置好之后，点击①的运行三角形，然后点击②的“启动”，执行增量同步。 ![](assets\20181106235333127.png)

 

9）执行结果，如图三个步骤都有绿色对号，并且“步骤度量”表格中有相应的数值表示数据变动则说明增量更新成功。![](assets\20181106235510766.png)

 

增量同步结果验证：

以下三张表分别为 同步前TEST_A、同步前TEST_B和同步后TEST_B ，分别对应于图test_a、test_b和test_b_res。

同步前TEST_A和同步前TEST_B数据作比较，

1）ID为1和2的数据是完全相同的；

2）ID为3的数据的LASTUPDATEDON字段，在test_a中秒数为16，在test_b中秒数为06，两者不同；

3）test_a比test_b多出一行ID为4的数据。

 

同步前TEST_A和同步后TEST_B比较，

1）ID为1和2的数据是完全相同的；

2）ID为3的数据的LASTUPDATEDON字段，在test_a中秒数为16，在test_b_res中秒数为16，两者相同；

3）test_a和test_b_res都有ID为4的数据完全相同的数据行。

 

#### 检验结果

增量同步后，TEST_A的数据与TEST_B的数据完全相同，增量同步成功。


test_a

  ![](assets\20181106235638476.png)

test_b

  ![](assets\20181106235831689.png)

test_b_res

  ![](assets\2018110623591116.png)

The end.
————————————————
版权声明：本文为CSDN博主「MaiXiaochai」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/maixiaochai/article/details/83795703