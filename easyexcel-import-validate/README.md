# easyexcel-import-validate
根据阿里的easyexcel通过validation+正则实现excel导入校验
包含了简单的导入导入导出demo，所涉及到的都是web的导入导出

### Postman 调用接口

#### 正常

 ![image-20221114101632070](https://raw.githubusercontent.com/CoderDream/java-architect-util/main/easyexcel-import-validate/assets/image-20221114101632070.png)

#### 有出错记录

* 请求时选【Send and Download】

 ![image-20221114101757687](https://raw.githubusercontent.com/CoderDream/java-architect-util/main/easyexcel-import-validate/assets/image-20221114101757687.png)

 ![image-20221114101913083](https://raw.githubusercontent.com/CoderDream/java-architect-util/main/easyexcel-import-validate/assets/image-20221114101913083.png)

#### 记录详情

![image-20221114102015335](https://raw.githubusercontent.com/CoderDream/java-architect-util/main/easyexcel-import-validate/assets/image-20221114102015335.png)



## 文件保存到本地

### 修改源码

* application.yml

```xml
upload:
  windowsPath: D:\\data\\upload\\temp
  linuxPath: /file

file:
  location: local
```

* UserRest.java

```java
    @Value("${upload.linuxPath}")
    private String uploadPath;

    @Value("${upload.windowsPath}")
    private String uploadWindowsPath;


    @Value("${file.location}")
    private String fileLocation;


    @PostMapping("/importExcel")
    public Result importExcel(HttpServletResponse response, @RequestParam MultipartFile file) throws IOException {
        if (isWindows()) {
            uploadPath = uploadWindowsPath;
        }

        //上传目录地址
        String uploadDir = uploadPath + File.separatorChar;

        EasyExcelListener easyExcelListener = new EasyExcelListener(userService, UserExcelDto.class);
        EasyExcelFactory.read(file.getInputStream(), UserExcelDto.class, easyExcelListener).sheet().doRead();
        List<ExcelCheckErrDto<UserExcelDto>> errList = easyExcelListener.getErrList();
        if (!errList.isEmpty()) {//如果包含错误信息就导出错误信息
            List<UserExcelErrDto> excelErrDtos = errList.stream().map(excelCheckErrDto -> {
                UserExcelErrDto userExcelErrDto = JSON.parseObject(JSON.toJSONString(excelCheckErrDto.getT()), UserExcelErrDto.class);
                userExcelErrDto.setErrMsg(excelCheckErrDto.getErrMsg());
                return userExcelErrDto;
            }).collect(Collectors.toList());
            if(fileLocation.equals("web")) {
                EasyExcelUtils.webWriteExcel(response, excelErrDtos, UserExcelErrDto.class, "用户导入错误信息");
            } else {
                EasyExcelUtils.localWriteExcel(uploadDir, excelErrDtos, UserExcelErrDto.class, "用户导入错误信息","用户导入错误信息");
            }
        }
        return addSucResult();
    }

    public static boolean isWindows() {
        return System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS");
    }
```

* EasyExcelUtils.java

```java
    public static void localWriteExcel(String uploadDir, List objects, Class clazz, String fileName, String sheetName) throws IOException {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为白
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        //建立文件对象
        File file = new File(uploadDir + fileName + ".xlsx");
        OutputStream outputStream = new FileOutputStream(file);//response.getOutputStream();
        try {
            EasyExcelFactory.write(outputStream, clazz).registerWriteHandler(horizontalCellStyleStrategy).sheet(sheetName).doWrite(objects);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
        }
    }
```

### 出错信息文件存放位置

 ![image-20221114110345593](https://raw.githubusercontent.com/CoderDream/java-architect-util/main/easyexcel-import-validate/assets/image-20221114110345593.png)
