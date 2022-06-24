package com.example.demo.p02.dp07.adapter.client;

import com.example.demo.p02.dp07.adapter.bean.*;
import com.example.demo.p02.dp07.adapter.bean.impl.Facility;
import com.example.demo.p02.dp07.adapter.bean.impl.Office;
import com.example.demo.p02.dp07.adapter.bean.impl.Workshop;

public class Client {
    static void jobs(Clean job) {
//        if (job instanceof Clean) {
//            ((Clean) job).makeClean();
//        }

        if (job != null) {
            job.makeClean();
        }
        if (job instanceof Extra) {
            ((Extra) job).takeCare();
        }
    }

    public static void main(String[] args) {
        Extra extra = new Facility();
        jobs(extra);
        Clean clean1 = new Office();
        Clean clean2 = new Workshop();
        jobs(clean1);
        jobs(clean2);
    }
}
/**
 * 清洁设备
 * 养护照料
 * 清洁办公室
 * 清洁工作室
 */