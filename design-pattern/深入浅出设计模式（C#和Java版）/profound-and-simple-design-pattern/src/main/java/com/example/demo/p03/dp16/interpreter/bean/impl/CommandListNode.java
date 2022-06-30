package com.example.demo.p03.dp16.interpreter.bean.impl;

import com.example.demo.p03.dp16.interpreter.bean.Node;

import java.util.*;

/**
 * 命令列表类，相应文法为:<command list> ::= <command>* END
 */
public class CommandListNode implements Node {
    private final Vector list = new Vector();

    public void parse(Context context) {
        while (true) {
            if (context.currentToken() == null) {
                System.err.println("Missing 'END'");
                break;
            } else if (context.currentToken().equals("END")) {
                context.skipToken("END");
                break;
            } else {
                Node commandNode = new CommandNode();
                commandNode.parse(context);
                list.add(commandNode);
            }
        }
    }

    public void execute() {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((CommandNode) it.next()).execute();
        }
    }
}
