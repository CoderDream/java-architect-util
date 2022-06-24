package com.example.demo.p01.dp03.abstractfactory.bean.mainboard;

import com.example.demo.p01.dp03.abstractfactory.bean.cpu.CPU;

/**
 * 主板微星MSIK7N2G,支持AMD的CPU:
 */
public class MSIK7N2G implements MainBoard{
    public void Attach(CPU cpu) throws Exception{
      if (cpu.getClass ().toString ().endsWith("AMD")){
        System.out.println("MSIK7N2G");
      }
      else {
        throw new Exception("主板MSIK7N2G只能配AMD的CPU");
        }
    }
}
