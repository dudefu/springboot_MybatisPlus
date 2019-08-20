package com.xinyi.xinfo.bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 节点
 * @author Administrator
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class Node<T> implements Serializable {
 
	protected Node<T> parentNode;
	protected T nodeEntity;
	protected List<Node<T>> childNodes;
	
	public void setParentNode(Node<T> parentNode) {
		this.parentNode = parentNode;
	}
	
	public Node<T> getParentNode() {
//		return parentNode;
		//因为操作父节点 jackson 无法转换成json 暂时屏蔽
		return null;
	}
 
	public void setNodeEntity(T nodeEntity) {
		this.nodeEntity = nodeEntity;
	}
	
	public T getNodeEntity() {
		return nodeEntity;
	}
	
	public void setChildNodes(List<Node<T>> childNodes) {
		this.childNodes = childNodes;
	}
	
	public List<Node<T>> getChildNodes() {
		return childNodes;
	}
	
	// 孩子节点排序  
	 public void sortChildren(String sortName) 
	 {  
		  // 对本层节点进行排序  
		  // 可根据不同的排序属性，传入不同的比较器，这里传入ID比较器 
		  NodeIDComparator nodeIDComparator = new NodeIDComparator();
		  nodeIDComparator.setSortName(sortName);
		  if(childNodes==null) 
		  {
			  return;
		  }
		  Collections.sort(childNodes, nodeIDComparator); 
		  // 对每个节点的下一层节点进行排序 
		  for(int i=0;i<childNodes.size();i++) 
		  {
			  if(childNodes.get(i)!=null) 
			  {
				  childNodes.get(i).sortChildren(sortName);  
			  }
		  }
	}  
	
	/** 
	 * 节点比较器 
	 */  
	class NodeIDComparator implements Comparator {  
	 public String sortName ;
	 
	 public String getSortName() {
		return sortName;
	 }

	public void setSortName(String sortName) {
		this.sortName = sortName;
	 }

	// 按照节点编号比较  
	 public int compare(Object o1, Object o2) {  
			if(o1 == null || o2 == null)
			{
				return 0;
			}
			Long value1 = 0L;
			Long value2 = 0L;
			try {
				 	// 通过属性获取对象的属性
			        Field field = ((Node) o1).getNodeEntity().getClass().getDeclaredField(this.sortName );
			        // 对象的属性的访问权限设置为可访问
			        field.setAccessible(true);
			        // 获取属性的对应的值
			        value1 = Long.parseLong(field.get(((Node) o1).getNodeEntity())==null?"0":field.get(((Node) o1).getNodeEntity()).toString());
			        // 通过属性获取对象的属性
			        Field field2 = ((Node) o2).getNodeEntity().getClass().getDeclaredField(this.sortName );
			        // 对象的属性的访问权限设置为可访问
			        field2.setAccessible(true);
			        // 获取属性的对应的值
			        value2 = Long.parseLong(field.get(((Node) o2).getNodeEntity())==null?"0":field.get(((Node) o2).getNodeEntity()).toString());
			} catch (Exception e) {
				e.printStackTrace();
			}  
			return (value1.compareTo(value2));
		 }   
	}

}
