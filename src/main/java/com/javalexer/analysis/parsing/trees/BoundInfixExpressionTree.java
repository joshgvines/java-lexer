package com.javalexer.analysis.parsing.trees;

import com.javalexer.analysis.parsing.nodes.AbsNode;
import com.javalexer.analysis.semantics.NodeTypeBinder;
import com.javalexer.analysis.semantics.nodes.AbsBoundNode;
import com.javalexer.analysis.semantics.nodes.BoundLiteralNode;

public class BoundInfixExpressionTree {

    private AbsBoundNode root;

    public BoundInfixExpressionTree() {
        this.root = null;
    }

    public BoundInfixExpressionTree(AbsBoundNode root) {
        this.root = root;
    }

    public boolean buildTree(AbsNode AST_Root) throws Exception {
        NodeTypeBinder nodeTypeBinder = new NodeTypeBinder();
        root = nodeTypeBinder.bind(AST_Root);
        return (root != null);
    }

    public AbsBoundNode getRoot() {
        return root;
    }

    public boolean isLeaf(AbsBoundNode absBoundNode) {
        return (absBoundNode instanceof BoundLiteralNode);
    }

}
