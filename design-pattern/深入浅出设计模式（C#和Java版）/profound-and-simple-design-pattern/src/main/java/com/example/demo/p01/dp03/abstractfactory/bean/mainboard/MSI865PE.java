package com.example.demo.p01.dp03.abstractfactory.bean.mainboard;

import com.example.demo.p01.dp03.abstractfactory.bean.cpu.CPU;

//主板微星MSI865PE,支持Intel的CPU:
public class MSI865PE implements MainBoard {
  public void Attach(CPU cpu) throws Exception{
    if (cpu.getClass ().toString ().endsWith("Intel")){
      System.out.println("MSI865PE");
    }
    else {
      throw new Exception("主板MSI865PE只能配Intel的CPU");
    }
  }
}
