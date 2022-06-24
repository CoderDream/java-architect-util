package com.example.demo.p01.dp03.abstractfactory.bean.mainboard;

import com.example.demo.p01.dp03.abstractfactory.bean.cpu.CPU;

public interface MainBoard {
  public void Attach(CPU cpu) throws Exception;
}
