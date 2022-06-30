package com.example.demo.p03.dp16.interpreter.bean.impl;

import com.example.demo.p03.dp16.interpreter.bean.Node;

/**
 * <program> ::= PROGRAM <command list>
 */
public class ProgramNode implements Node {
  private Node commandListNode;
  public void parse (Context context) {
    context.skipToken ("PROGRAM");
    commandListNode = new CommandListNode ();
    commandListNode.parse (context);
  }

  public void execute() { commandListNode.execute(); }

  public String toString () {
    return "[PROGRAM " + commandListNode + "]";
  }
}
