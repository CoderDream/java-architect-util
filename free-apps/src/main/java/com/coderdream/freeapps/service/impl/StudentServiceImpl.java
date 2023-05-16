package com.coderdream.freeapps.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.dto.Student;
import com.coderdream.freeapps.dto.StudentExport;
import com.coderdream.freeapps.mapper.StudentMapper;
import com.coderdream.freeapps.service.StudentService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    public List<Student> getAll(){
        return studentMapper.selectList(new QueryWrapper<>());
    }

    //创建单个excel的方法，多个excel的话，加几个这种方法就行
    public List<Object> createExcel() {
        List<Object> result=new ArrayList<Object>();
        List<Student> sList=getAll();
        List<StudentExport> exportList=new ArrayList<StudentExport>();
        for (Student student : sList) {
            StudentExport export=new StudentExport();
            export.setAge(student.getAge());
            export.setName(student.getName());
            export.setSex(student.getSex());
            exportList.add(export);
        }
        ExcelWriter writer=new ExcelUtil().getWriter();
        //添加表头对应的数据列
        writer.addHeaderAlias("name", "名字");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("sex", "性别");
        writer.write(exportList,true);
        //格式化文件名字模板
        String fileName = String.format("%s-%s.xls", "student", DateUtil.format(new Date(), "yyyyMMdd"));
        //写入流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writer.flush(out);
        //关闭
        writer.close();
        result.add(out);
        result.add(fileName);
        return result;
    }

    //循环导入excel的流，准备在Controller层生成zip包
    public void writeZos(List<ByteArrayOutputStream> bosList, ZipOutputStream zos,List<String> excelName) throws IOException {
        for (int i = 0; i < bosList.size(); i++) {
            //将多个excel都转成字节流写入
            zos.putNextEntry(new ZipEntry(excelName.get(i)));
            byte[] excelStream=bosList.get(i).toByteArray();
            zos.write(excelStream);
            //记得关闭
            zos.closeEntry();
        }
    }
}

