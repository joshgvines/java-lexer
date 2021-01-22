package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

public interface Node {

    Node right = null;
    Node left = null;

    Token getData();

    void setData(Token data);

}
