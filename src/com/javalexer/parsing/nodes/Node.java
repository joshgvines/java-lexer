package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

public interface Node {

    Token getData();

    void setData(Token data);

}
