package com.coderdream.freeapps.util.multithread;

import com.google.common.collect.Lists;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.concurrent.*;


/**
 * https://blog.csdn.net/zhanglu1236789/article/details/107199023 java.util.concurrent多线程并行处理返回处理结果（计算一个list集合）
 */
public class Test {

    /**
     * 多线程并发遍历list源数据 将每个线程处理结果返回
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //创建操作源数据 list集合
        List list = Lists.newArrayList();
        for (int i = 1; i <= 21; i++) {
            Student student = new Student(i, "test-" + i);
            list.add(student);
        }
        //创建线程池
        ExecutorService exec = Executors.newFixedThreadPool(10);
        CompletionService<List<Student>> cpiService = new ExecutorCompletionService<>(exec);
        int f = 0;
        int i = 0;

        //分发线程
        while (true) {
            f = f + 1;
            int g = (i + 10) > list.size() ? (list.size()) : (i + 10);

            testCallable callable = new testCallable(f, list.subList(i, g));
            if (!exec.isShutdown()) {
                cpiService.submit(callable);
            }
            i = (g);
            if (i >= (list.size())) {
                break;
            }
        }
        System.out.println("f:" + f + ";i:" + i + ";size:" + list.size());
        //获取线程处理结果
        for (int h = 0; h < f; h++) {
            List<Student> students = cpiService.take().get();
            for (Student student : students) {
                System.out.println("result-" + h + "-[" + student + "]");
            }
        }
        //关闭多线程池
        exec.shutdown();
    }

    @Getter
    @Setter
    static class Student {

        private int id;
        private String name;

        private Date birthday;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Student() {
        }

        @Override
        public String toString() {
            return id + "-student{" +
                "id=" + id +
                ", name='" + name + '\'' + ", birthday='" + birthday +
                '}';
        }
    }

    //创建多线程处理类
    static class testCallable implements Callable<List<Student>> {

        private int flag;
        private List<Student> students;

        public testCallable(int flag, List<Student> students) {
            this.flag = flag;
            this.students = students;
        }

        public testCallable() {
        }

        @Override
        public List<Student> call() throws Exception {
            students.stream().forEach(e -> printStudent(flag, e));
            return students;
        }

        public void printStudent(int i, Student student) {
            System.out.println("call-" + i + "-[" + student + "]");
            student.setBirthday(new Date());
        }
    }
}

