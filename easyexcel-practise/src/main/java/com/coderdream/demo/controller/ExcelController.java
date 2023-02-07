package com.coderdream.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.coderdream.demo.dto.BankInfoDto;
import com.coderdream.demo.dto.EasyExcelDemoDto;
import com.coderdream.demo.listener.ExcelListener;
import com.coderdream.demo.util.EasyExcelUtil;
import com.coderdream.demo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 对象类型元数据管理
 */
@Slf4j
@Api(tags = "对象类型元数据操作")
@RestController
@RequestMapping("api/excel")
public class ExcelController {

    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    /**
     * easyExcel 无模板导入解析
     *
     * @param excelFile
     * @return
     * @throws IOException
     */
    @PostMapping("/readNoEntity")
    public Result<List<Object>> noEntity(@RequestParam("file") MultipartFile excelFile) throws IOException {
        // sheet 读取第几个sheet的数据,索引从0开始
        List<Object> list = EasyExcel.read(excelFile.getInputStream()).sheet(0).doReadSync();
        return Result.buildResult(Result.Status.OK, list);
    }

    /**
     *   @RequestMapping("/all")
     *     public Result<List<User>> getAll() {
     *         List<User> list = userService.list();
     *         return Result.buildResult(Result.Status.OK, list);
     *     }
     */

    /**
     * easyExcel 有模板导入解析
     *
     * @param excelFile
     * @return
     * @throws IOException
     */
    @PostMapping("/readToEntity")
    public Result<List<EasyExcelDemoDto>> toEntity(@RequestParam("file") MultipartFile excelFile) throws IOException {
        List<EasyExcelDemoDto> list = EasyExcel.read(excelFile.getInputStream(), EasyExcelDemoDto.class, null).sheet(0).doReadSync();
        return Result.buildResult(Result.Status.OK, list);
    }

    @PostMapping("/readToEntityListener")
    public Result<List<EasyExcelDemoDto>> toEntityListener(@RequestParam("file") MultipartFile excelFile) throws IOException {
        // 每次执行,listener必须重新定义new
        ExcelListener listener = new ExcelListener();
        EasyExcel.read(excelFile.getInputStream(), EasyExcelDemoDto.class, listener).sheet(0).doRead();
        return Result.buildResult(Result.Status.OK, listener.getDataList());
    }

    @RequestMapping("/readSheetToEntity")
    public Result<List<EasyExcelDemoDto>> readSheetToEntity(@RequestParam("file") MultipartFile excelFile) throws IOException {
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

    /**
     * 有模板
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = UUID.randomUUID().toString();
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        //表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 头部背景颜色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        //内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy
                = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        EasyExcel.write(response.getOutputStream(), EasyExcelDemoDto.class).sheet("sheet1")
                .registerWriteHandler(horizontalCellStyleStrategy).doWrite(dataTemplate());
    }

    protected List<?> dataTemplate() {
        List<EasyExcelDemoDto> rowList = new ArrayList<>();
        EasyExcelDemoDto cell = new EasyExcelDemoDto();
        EasyExcelDemoDto cell2 = new EasyExcelDemoDto();
        cell.setName("name1");
        cell.setSex("sex1");
        cell.setAge(1);
        cell2.setName("name2");
        cell2.setSex("sex");
        cell2.setAge(2);

        rowList.add(cell);
        rowList.add(cell2);
        return rowList;
    }

    @Resource
    ResourceLoader resourceLoader;

    /**
     * 有模板
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/dataExportByTemplate")
    public void dataExportByTemplate(HttpServletResponse response) throws Exception {

        List<BankInfoDto> listData = new ArrayList<>();

        listData.add(new BankInfoDto("小1", "建设银行", "100001", new BigDecimal("60000.20"), "1200001"));
        listData.add(new BankInfoDto("小2", "招商银行", "200002", new BigDecimal("70000.20"), "2200001"));
        listData.add(new BankInfoDto("小3", "农业银行", "300003", new BigDecimal("80000.20"), "3200001"));
        listData.add(new BankInfoDto("小4", "邮政银行", "400004", new BigDecimal("90000.20"), "4200001"));
        listData.add(new BankInfoDto("小5", "中国银行", "500005", new BigDecimal("100000.20"), "52000001"));

        Map<String, String> mapData = new HashMap<>();
        mapData.put("size", String.valueOf(listData.size()));
        mapData.put("total", "666666");

        org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:templates/template.xlsx");
        //获1.txt的取相对路径
        String path = resource.getFile().getPath();

        String fileName = path;// ExcelController.class.getResource("/").getPath() + "templates" + File.separator + "template.xlsx";
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("######");
        } else {
            System.out.println(file.getAbsolutePath());
        }

        EasyExcelUtil.dataExportByTemplate(fileName, mapData, listData, "模板", response);
    }

//    @Autowired
//    private ArchiveDocsFacade archiveDocsFacade;
//
//    @ApiOperation(value = "导出我的材料池")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", name = "query", value = "姓名/J号", dataType = "String")
//    })
//    @GetMapping(value = "/exportMyArchiveList")
//    public void exportMyArchiveList(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
//        String fileName = URLEncoder.encode("材料列表", "UTF-8");
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
//        OutputStream outputStream = response.getOutputStream();
//
//        TableTagBean ttb = TableTagBean.getFromExt(request);
//        List<ArchiveDocsDTO> list = archiveDocsFacade.listArchiveDocs(ttb);
//
//        List<ArchiveDwVO> list2 = new ArrayList<ArchiveDwVO>();
//        ArchiveDwVO vo  = new ArchiveDwVO();
//        vo.setDwName("单位一");
//        list2.add(vo);
//        vo  = new ArchiveDwVO();
//        vo.setDwName("单位二");
//        list2.add(vo);
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        // 获取模板路径
//        String templateFileName = FileUtil.getPath() + "excel/exportArchiveList2.xls";
//        // 创建输出的excel对象
//        final ExcelWriter write = EasyExcel.write(outputStream).withTemplate(templateFileName).build();
//        // 创建第一个sheel页
//        final WriteSheet sheet1 = EasyExcel.writerSheet(0,"材料列表1").build();
//        // 创建第二个sheel页
//        final WriteSheet sheet2 = EasyExcel.writerSheet(1,"材料列表2").build();
//
//        // 设置列表横向展示
//        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
//        // 使用数据横向展示的示例
//        // write.fill(list,fillConfig,sheet1);
//
//        write.fill(list, sheet1);
//        map.put("total", "材料列表1");
//        write.fill(map, sheet1);
//
//        write.fill(list, sheet2);
//        map.put("total", "材料列表2");
//        write.fill(map, sheet2);
//
//        // 关闭流
//        write.finish();
//    }

//    public Result test(Map map, List list) throws IOException {
//        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("static/" + "test.xlsx");
//        String fileName = UUID.randomUUID().toString() + "_" + "test.xlsx";
//        String downloadPath = RuoYiConfig.getDownloadPath() + fileName;
//        OutputStream out = new FileOutputStream(downloadPath);
//        ExcelWriter excelWriter = EasyExcel.write(out).withTemplate(inputStream).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        excelWriter.fill(list, writeSheet);
//        excelWriter.fill(map, writeSheet);
//        excelWriter.finish();
//        return Result.buildResult("");// AjaxResult.success(fileName);
//    }


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
}
