package com.example.demo.controller.tree;
/*package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Menu;
import com.example.demo.dao.MenuDao;

@RestController
public class MenuTreeController {
	
	@RequestMapping("/getMenuTree")
	public static List<Menu>  getMenuTree() throws Exception {
	    //将所有数据取出放进集合
	    List<Menu> list = MenuDao.getAll();

	    List<Menu> tree = toTree(list);

		return tree;
	}
	
	*//**
	 * 获取树形结构方式一
	 * 
	 * 获取任意节点的树形结构
	 * 
	 * 整体思路：
	 * 		将每个节点对象存在缓存中（缓存对象为Map：key和 node对象 形式），并在缓存中
	 * 			找它的下级，封装到当前node的子节点列表中；
	 * 			找它的上级，封装到上级缓存对象的子节点列表中；
	 * 		最终每个节点都有它的上级、下级
	 * 
	 * 集合排序：
	 * 		方式一：	.stream().sorted(Comparator.comparing(Menu::getMenuId).reversed()).collect(Collectors.toList())
	 * 					返回新的有序集合，原集合顺序不变。.reversed() 倒序
	 * 
	 *		方式二：	.sort...：返回void，原集合顺序变为有序
	 * @return
	 *//*
	@RequestMapping("/getEveryMenuTree")
	public static List<Menu>  getEveryMenuTree() {
		// 获取所有的Menu 数据
		List<Menu> list = MenuDao.getAll();
		
		// 定义缓存map（存的是所有资源）
		Map<String,Menu> temp = new HashMap<String, Menu>();
		
		for (Menu menu : list) {
			String id = menu.getMenuId();
			String parentId = menu.getParentId();
			
			// 找子节点
			// 循环对象缓存，若上级为当前Menu，则把对象作为当前Menu的下级资源,
			Collection<Menu> menuArr = temp.values();
			
			// 循环对象缓存时，找Menu对应的下级信息
			List<Menu> children = new ArrayList<>();
			
			for (Menu menu1 : menuArr) {
				if(menu1.getParentId() != null && menu1.getParentId().equals(id)) {
					if (children == null) {
						children = new ArrayList<Menu>();
					}
					children.add(menu1);
				}
			}
			// 子节点列表排序
			if (children.size() > 1) {
				children.sort((menu1, menu2) -> menu2.getMenuId().compareTo(menu1.getMenuId()));
//			
//			children = children.stream()
//					.sorted(Comparator.comparing(Menu::getMenuId).reversed())
//					.collect(Collectors.toList());
			}

			
			menu.setChildren(children);
			
			// 找父节点
			// 若当前Menu存在上级，查看当前Menu的上级是否在缓存中，若在缓存中，则把当前Menu保存在对应的上级对象下
			if (parentId != null && !parentId.equals("")) {
				// 从缓存map中取上级对象信息
				Menu parentMenu = temp.get(parentId);
				if (parentMenu != null) {
					// 获取上级对象的子节点
					List<Menu> parentItems = parentMenu.getChildren();
					if (parentItems == null) {
						parentItems = new ArrayList<>();
					}
					parentItems.add(menu);
					
					// 子节点列表排序
					if (parentItems.size() > 1) {
						parentItems.sort((menu1, menu2) -> menu1.getMenuId().compareTo(menu1.getMenuId()));
//					
//					parentItems = parentItems.stream()
//							.sorted(Comparator.comparing(Menu::getMenuId).reversed())
//							.collect(Collectors.toList());
					}
					
					parentMenu.setChildren(parentItems);
					
				}
			}
			// 当前Menu信息存放到缓存中
			temp.put(id, menu);
		}
		
		List<Menu> topList = new ArrayList<Menu>();
		
		// 没传id时，查询整个树
		Collection<Menu> menuList = temp.values();
		for (Menu menu : menuList) {
			String parentId = menu.getParentId();
			if (parentId == null || parentId.equals("")) {
				topList.add(menu);
			}
		}
		
		if (topList.size() > 1) {
			
			topList.sort((menu1, menu2) -> menu2.getMenuId().compareTo(menu1.getMenuId()));
			
//			topList = topList.stream()
//					.sorted(Comparator.comparing(Menu::getMenuId).reversed())
//					.collect(Collectors.toList());
		}
		
		return topList;
	}
	
	
	*//**
	 * 获取树形结构方式二
	 * 
	 * 	递归
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 *//*
	private static List<Menu> toTree(List<Menu> list) throws Exception {
		
		// 获取根节点，即找出父节点为空的对象
        List<Menu> treeList = new ArrayList<Menu>();
        for (Menu tree : list) {
            if(tree.getParentId() == null || tree.getParentId().equals("") ){
                treeList.add(tree);
            }
        }
        
        // 将根节点从原始list移除，减少下次处理数据
        list.removeAll(treeList);
        
        // 递归封装树
        fillTree(treeList,list);
        return treeList;
    }
 
	*//**
	 * 封装树
	 *
	 * @param parentList 要封装为树的父对象集合
	 * @param originalList 原始list数据
	 *//*
	public static void fillTree(List<Menu> parentList, List<Menu> originalList) throws Exception {
	    for (int i = 0; i < parentList.size(); i++) {
	        List<Menu> children = fillChildren(parentList.get(i), originalList);
	        if (children.isEmpty()) {
	            continue;
	        }
	        originalList.removeAll(children);
	        fillTree(children, originalList);
	    }
	}
	    
    *//**
     * 封装子对象
     *
     * @param parent 父对象
     * @param originalList 待处理对象集合
     *//*
    public static List<Menu> fillChildren(Menu parent, List<Menu> originalList) throws Exception {
        List<Menu> childList = new ArrayList<Menu>();
        String parentId = parent.getMenuId();
        for (int i = 0; i < originalList.size(); i++) {
        	Menu menu = originalList.get(i);
            String childParentId = menu.getParentId();
            if (parentId.equals(childParentId)) {
                childList.add(menu);
            }
        }
        parent.setChildren(childList);
        return childList;
    }


}
*/