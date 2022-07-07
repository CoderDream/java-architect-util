# 项目简介

将文件列表压缩成zip文件下载



### 调用





# 延伸阅读


1. [Java实现多文件压缩下载](https://cloud.tencent.com/developer/article/2020337)
2. [Java遍历文件夹下的所有文件](https://blog.csdn.net/DCFANS/article/details/92840542)
3. [MultipartFile转File](https://blog.csdn.net/u012279452/article/details/92840583)
4. [SpringBoot上传文件大小限制的配置](https://www.jianshu.com/p/0a00b697dfb4)
5. [前端模拟实现postman的send-and-download进行文件下载](https://blog.csdn.net/qq_45094682/article/details/119937772)

### 设置默认中文名称
```java
response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(downloadName, "utf-8"));
```

### Java实现多文件压缩下载

```java
/**
 * 将多个文件压缩到指定输出流中
 *
 * @param files 需要压缩的文件列表
 * @param outputStream  压缩到指定的输出流
 */
public static void compressZip(List<File> files, OutputStream outputStream) {
	// 包装成ZIP格式输出流
	try (ZipOutputStream zipOutStream = new ZipOutputStream(new BufferedOutputStream(outputStream))) {
		// 设置压缩方法
		zipOutStream.setMethod(ZipOutputStream.DEFLATED);
		// 将多文件循环写入压缩包
		for (int i = 0; i < files.size(); i++) {
			File file = files.get(i);
			FileInputStream filenputStream = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			filenputStream.read(data);
			// 添加ZipEntry，并ZipEntry中写入文件流，这里，加上i是防止要下载的文件有重名的导致下载失败
			zipOutStream.putNextEntry(new ZipEntry(i+ "-" + file.getName()));
			zipOutStream.write(data);
			filenputStream.close();
			zipOutStream.closeEntry();
		}
	} catch (IOException e) {
		log.error(CompressDownloadUtil.class.getName(), "downloadallfiles", e);
	}  finally {
		try {
			if (Objects.nonNull(outputStream)) {
				outputStream.close();
			}
		} catch (IOException e) {
			log.error(CompressDownloadUtil.class.getName(), "downloadallfiles", e);
		}
	}
}
```





### Java遍历文件夹下的所有文件

* 不包含文件夹

```java
String path = "D:\\JAVA";		//要遍历的路径
File file = new File(path);		//获取其file对象
File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
for(File f:fs){					//遍历File[]数组
	if(!f.isDirectory())		//若非目录(即文件)，则打印
		System.out.println(f);
}
```

* 包含文件夹

```java
public static void main(String[] args) {
	String path = "D:\\JAVA";		//要遍历的路径
	File file = new File(path);		//获取其file对象
	func(file);
}

private static void func(File file){
	File[] fs = file.listFiles();
	for(File f:fs){
		if(f.isDirectory())	//若是目录，则递归打印该目录下的文件
			func(f);
		if(f.isFile())		//若是文件，直接打印
			System.out.println(f);
	}
}
```





### MultipartFile转File

```java
public static File multipartFileToFile(MultipartFile file) throws Exception {

	File toFile = null;
	if (file.equals("") || file.getSize() <= 0) {
		file = null;
	} else {
		InputStream ins = null;
		ins = file.getInputStream();
		toFile = new File(file.getOriginalFilename());
		inputStreamToFile(ins, toFile);
		ins.close();
	}
	return toFile;
}
```






### SpringBoot上传文件大小限制的配置

```yaml
spring:
  servlet:
    multipart:
      max-file-size: 20MB #单个文件最大为20M
      max-request-size: 20MB #单次请求文件总数大小为20M
```

