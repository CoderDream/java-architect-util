





### 1. Excel数据

 ![image-20230131104454191](assets\image-20230131104454191.png)







### 2.1 无模板



```
localhost:8080/api/excel/readNoEntity
```



 ![image-20230131104314861](assets\image-20230131104314861.png)



### 2.2 有模板

- 模板 ExcelProperty注解对应excel第一列名





 ![image-20230131105121272](assets\image-20230131105121272.png)



### 2.3 自定义监听

- 继承AnalysisEventListener

```java
package com.coderdream.demo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.coderdream.demo.dto.EasyExcelDemoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener<EasyExcelDemoDto> {

    private static final Logger logger = LoggerFactory.getLogger(ExcelListener.class);

    public List<EasyExcelDemoDto> dataList = new ArrayList<>();

    // 每读取一行回调当前方法
    @Override
    public void invoke(EasyExcelDemoDto easyExcelTestDto, AnalysisContext analysisContext) {
        logger.info("read item: {}", easyExcelTestDto);
        dataList.add(easyExcelTestDto);
    }

    // excel数据读取完毕回调
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        logger.info("{}条数据，开始存储数据库！", dataList.size());
        logger.info("所有数据解析完成！");
    }

    public List<EasyExcelDemoDto> getDataList() {
        return dataList;
    }

}
```





```java
    @PostMapping("/readToEntityListener")
    public Result<List<EasyExcelDemoDto>> toEntityListener(@RequestParam("file") MultipartFile excelFile) throws IOException {
        // 每次执行,listener必须重新定义new
        ExcelListener listener = new ExcelListener();
        EasyExcel.read(excelFile.getInputStream(), EasyExcelDemoDto.class, listener).sheet(0).doRead();
        return Result.buildResult(Result.Status.OK, listener.getDataList());
    }
```





这里使用的是ExcelReaderSheetBuilder类的doRead()方法。doRead()和doReadSync()区别在于doReadSync()里面配了一个自定义的监听，并且返回读取到excel数据的List集合



 ![image-20230131105819296](assets\image-20230131105819296.png)

### 2.4 多个sheet的读取方法

```java
@RequestMapping("/readSheetToEntity")
public Result<List<EasyExcelDemoDto>>  readSheetToEntity(@RequestParam("file") MultipartFile excelFile) throws IOException {
    List<List<EasyExcelDemoDto>> resultList = new ArrayList<>();
    Result result = Result.buildResult(Result.Status.OK, resultList);
    ExcelListener listener = new ExcelListener();
    ExcelReaderBuilder builder = EasyExcel.read(excelFile.getInputStream(), EasyExcelDemoDto.class, listener);
    ExcelReader reader = builder.build();
    //sheet集合
    List<ReadSheet> sheets = reader.excelExecutor().sheetList();
    for (ReadSheet sheet : sheets) {
        // 共用监听器，解析之前需要清空
        listener.getDataList().clear();
        //读取每一个sheet的内容
        logger.info("sheet name:{}", sheet.getSheetName());
        reader.read(sheet);
        List<EasyExcelDemoDto> current = listener.getDataList();
        resultList.add(current);
        logger.info("content:{}", current);
    }
    reader.finish();
    return result;
}
```



 ![image-20230131110534081](assets\image-20230131110534081.png)



## 3 导出

### 3.1 无模板

- 代码

  ```java
  @RequestMapping("/download")
  public void download(HttpServletResponse response) throws Exception {
      response.setContentType("application/vnd.ms-excel");
      response.setCharacterEncoding("utf-8");
      // 随机生成文件名
      String fileName = UUID.randomUUID().toString();
      response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
      // sheet(0) 参数表示sheet名称
      EasyExcel.write(response.getOutputStream()).sheet(0).doWrite(data());
  }
  
  private List<?> data() {
      List<List<String>> rowList = new ArrayList<>();
      List<String> cell = new ArrayList<>();
      cell.add("cell1");
      cell.add("cell2");
      cell.add("cell3");
      List<String> cell2 = new ArrayList<>();
      cell2.add("中文");
      cell2.add("大哥");
      cell2.add("cell23");
      cell2.add("cell24");
      rowList.add(cell);
      rowList.add(cell2);
      return rowList;
  }
  ```

  

- 内容

   ![image-20230131111124782](assets\image-20230131111124782.png)

 

### 3.2 有模板

- 代码

  ```java
  @PostMapping("/exportBankCardInfo")
  @ApiOperation("银行卡号导出")
  public void exportBankCardInfo(HttpServletResponse response) throws IOException {
      // 设置日期格式化，用于生成文件名称
  //        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分"));
      // 文件模板
      InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("templates/template.xlsx");
      // 生成文件名称
      String fileName = "BankInfo.xlsx";// dateStr + "BankInfo.xlsx";
  
      // 查询数据：
      List<BankInfoDto> listData = new ArrayList<>();
  
      listData.add(new BankInfoDto("小1", "建设银行", "100001", new BigDecimal("60000.22"), "1200001"));
      listData.add(new BankInfoDto("小2", "招商银行", "200002", new BigDecimal("70020.23"), "2200001"));
      listData.add(new BankInfoDto("小3", "农业银行", "300003", new BigDecimal("80040.24"), "3200001"));
      listData.add(new BankInfoDto("小4", "邮政银行", "400004", new BigDecimal("90060.25"), "4200001"));
      listData.add(new BankInfoDto("小5", "中国银行", "500005", new BigDecimal("70080.26"), "5200001"));
  
      Map<String, Object> mapData = new HashMap<>();
      mapData.put("size", String.valueOf(listData.size()));
  
      BigDecimal sumMoney = listData.stream().map(BankInfoDto::getBalance).reduce(BigDecimal.ZERO,BigDecimal::add);
      System.out.println("总金额： " + sumMoney.setScale(2,BigDecimal.ROUND_HALF_UP)); // 参数1：代表小数点后位数
  //        参数2：	BigDecimal.ROUND_HALF_UP 四舍五入
  //        BigDecimal.ROUND_DOWN 直接删除多余小数位
      mapData.put("total", sumMoney.setScale(2,BigDecimal.ROUND_HALF_UP));
  
      response.setCharacterEncoding("UTF-8");
      response.setHeader("Content-Type", "application/vnd.ms-excel");
      response.setHeader("Content-Disposition",
              "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
  
      //读取excel
      ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(inputStream).build();
      // 第一个sheet
      WriteSheet writeSheet = EasyExcel.writerSheet(0).build();
      if (Objects.nonNull(mapData)) {
          excelWriter.fill(mapData, writeSheet);
      }
      if (CollectionUtils.isNotEmpty(listData)) {
          excelWriter.fill(listData, writeSheet);
      }
      excelWriter.finish();
  }
  ```

* 模板

  | 总余额  |             | 总条数    |            |              |
  | ------- | ----------- | --------- | ---------- | ------------ |
  | {total} |             | {size}    |            |              |
  | 姓名    | 银行        | 银行卡    | 余额       | 账号         |
  | {.name} | {.bankName} | {.cardNo} | {.balance} | {.accountNo} |

* 导出文件

  ![image-20230131143655672](assets\image-20230131143655672.png)







### 参考文档

1. [Java工具类Result＜T＞](https://blog.csdn.net/u012313361/article/details/108102743)
2. [SpringBoot集成easyExcel实现按模板导出](https://cloud.tencent.com/developer/article/2059612)
3. [BigDecimal保留2位小数的处理](https://blog.csdn.net/LLLLLiSHI/article/details/88575850)









# END
