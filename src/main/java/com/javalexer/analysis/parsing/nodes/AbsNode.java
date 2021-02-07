package com.javalexer.analysis.parsing.nodes;

import com.javalexer.enums.NodeType;

public abstract class AbsNode {
    private NodeType nodeType;

    public AbsNode(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public NodeType getNodeType() {
        return nodeType;
    }
}
