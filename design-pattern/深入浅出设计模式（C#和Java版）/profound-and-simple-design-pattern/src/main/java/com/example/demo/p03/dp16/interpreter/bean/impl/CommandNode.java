package com.example.demo.p03.dp16.interpreter.bean.impl;

import com.example.demo.p03.dp16.interpreter.bean.Node;

/**
 * 命令类，相应的文法为:<command> ::= <repeat command> | <primitive command>
 */
public class CommandNode implements Node {
    private Node node;

    public void parse(Context context) {
        if (context.currentToken().equals("REPEAT")) {
            node = new RepeatCommandNode();
            node.parse(context);
        } else {
            node = new PrimitiveCommandNode();
            node.parse(context);
        }
    }

    public void execute() {
        node.execute();
    }

    public String toString() {
        return node.toString();
    }
}
