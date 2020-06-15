package com.example.demo.controller.tree;
/*package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Node;
import com.example.demo.dao.NodeDao;

@RestController
public class NodeTreeController {
	
	@RequestMapping("/getNodeTree")
	public static List<Node>  getNodeTree() {
	    //将所有数据取出放进集合
	    List<Node> list = NodeDao.getAll();

	    List<Node> tree = getTree(list);

		return tree;
	}
	
	*//**
	 * 获取任意节点的树形结构
	 * 
	 * 整体思路：
	 * 		将每个节点对象存在缓存中（缓存对象为Map：key和 node对象 形式），并在缓存中
	 * 			找它的下级，封装到当前node的子节点列表中；
	 * 			找它的上级，封装到上级缓存对象的子节点列表中；
	 * 		最终每个节点都有它的上级、下级
	 * 
	 * @return
	 *//*
	@RequestMapping("/getEveryNodeTree")
	public static List<Node>  getEveryNodeTree() {
		// 获取所有的node 数据
		List<Node> list = NodeDao.getAll();
		
		// 定义缓存map（存的是所有资源）
		Map<String,Node> temp = new HashMap<String, Node>();
		
		for (Node node : list) {
			String id = node.getId();
			String parentId = node.getParentId();
			
			// 找子节点
			// 循环对象缓存，若上级为当前node，则把对象作为当前node的下级资源
			Collection<Node> nodeArr = temp.values();
			
			// 循环对象缓存时，找node对应的下级信息
			List<Node> children = new ArrayList<>();
			
			for (Node node1 : nodeArr) {
				if(node1.getParentId() != null && node1.getParentId().equals(id)) {
					if (children == null) {
						children = new ArrayList<Node>();
					}
					children.add(node1);
				}
			}
			
			node.setChildren(children);
			
			// 找父节点
			// 若当前node存在上级，查看当前node的上级是否在缓存中，若在缓存中，则把当前node保存在对应的上级对象下
			if (parentId != null && !parentId.equals("")) {
				// 从缓存map中取上级对象信息
				Node parentNode = temp.get(parentId);
				if (parentNode != null) {
					// 获取上级对象的子节点
					List<Node> parentItems = parentNode.getChildren();
					if (parentItems == null) {
						parentItems = new ArrayList<>();
					}
					parentItems.add(node);
					parentNode.setChildren(parentItems);
					
				}
			}
			// 当前node信息存放到缓存中
			temp.put(id, node);
		}
		
		List<Node> topList = new ArrayList<Node>();
		
		// 没传id时，查询整个树
		Collection<Node> nodeList = temp.values();
		for (Node node : nodeList) {
			String parentId = node.getParentId();
			if (parentId == null || parentId.equals("")) {
				topList.add(node);
			}
		}
		
		return topList;
	}
	
	*//**
	 * 组装树形结构
	 * @param list
	 * @return
	 *//*
	private static List<Node> getTree(List<Node> list) {
		
		// 获取根节点，即找出父节点为空的对象
        List<Node> topList = new ArrayList<Node>();
        for (Node tree : list) {
            if(tree.getParentId() == null || tree.getParentId().equals("") ){
            	topList.add(tree);
            }
        }
        
        // 将根节点从原始list移除，减少下次处理数据
        list.removeAll(topList);
        
        // 递归封装树
        fillTree(topList,list);
        
        return topList;
    }
 
	*//**
	 * 封装树
	 *
	 * @param topList 	要封装为树的顶级节点对象集合
	 * @param originalList 原始list数据
	 *//*
	public static void fillTree(List<Node> topList, List<Node> originalList) {
		// 遍历根节点
		for (Node node : topList) {
			// 封装根节点的子节点
			List<Node> children = fillChildren(node, originalList);
			if (children.isEmpty()) {
				continue;
			}
			
			// 将已组装的子节点从原始list移除，减少下次处理数据
			originalList.removeAll(children);
			
			// 封装子节点的 子节点，一致递归，直到最底层（子节点再没有 子节点）
			fillTree(children, originalList);
		}
	}
	    
    *//**
     * 封装子对象
     * @param parent 父对象
     * @param originalList 待处理对象集合
     *//*
    public static List<Node> fillChildren(Node parent, List<Node> originalList) {
        List<Node> childList = new ArrayList<Node>();
        String parentId = parent.getId();
        for (Node node : originalList) {
        	String childParentId = node.getParentId();
            if (parentId.equals(childParentId)) {
                childList.add(node);
            }
		}
        parent.setChildren(childList);
        return childList;
    }



}
*/