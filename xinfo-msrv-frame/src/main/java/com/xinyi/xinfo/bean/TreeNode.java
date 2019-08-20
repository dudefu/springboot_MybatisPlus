package com.xinyi.xinfo.bean;

import java.util.ArrayList;

/**
 * 树结构
 * @author Administrator
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class TreeNode<T> extends Node<T>{
	
	public TreeNode() {
	}
	
	public TreeNode(T nodeEntity) {
		super();
		super.nodeEntity = nodeEntity;
	}
 
	public void addChildNode(Node<T> childNode) {
		childNode.setParentNode(this);
		if(childNodes == null) {
			childNodes = new ArrayList<Node<T>>();
		} 
		this.childNodes.add(childNode);
	}
 
	public void removeChildNode(Node<T> childNode) {
		if(childNodes != null) {
			childNodes.remove(childNode);
		}
		
	}
	
	public void display(Node<T> treeNode, int depth) {
		StringBuilder sb = new StringBuilder("");  
        for(int i = 0; i < depth; i++) {  
            sb.append("-");  
        }  
        if(treeNode.parentNode == null) {
        	System.out.println(treeNode.getNodeEntity().toString());
        }
        for(Node<T> childNode : treeNode.getChildNodes()) { 
        	System.out.println(new String(sb) + childNode.getNodeEntity().toString());
        	if(childNode.getChildNodes() != null) {
        		display(childNode, depth + 2);
        	}
        } 
		
	}
 
}
