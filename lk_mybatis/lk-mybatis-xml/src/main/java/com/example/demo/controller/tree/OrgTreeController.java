package com.example.demo.controller.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Org;
import com.example.demo.mybatis.mapper.OrgDao;
@RestController
@RequestMapping("/org")
public class OrgTreeController {
	
	/**
	 * 获取任意节点的树形结构
	 * 
	 * 整体思路：
	 * 		将每个节点对象存在缓存中（缓存对象为Map：key和 Org对象 形式），并在缓存中
	 * 			找它的下级，封装到当前Org的子节点列表中；
	 * 			找它的上级，封装到上级缓存对象的子节点列表中；
	 * 		最终每个节点都有它的上级、下级
	 * 
	 * @return
	 */
	@RequestMapping("/getEveryOrgTree")
	public static List<Org>  getEveryOrgTree() {
		// 获取所有的Org 数据
		List<Org> list = OrgDao.getAll();
		
		// 定义缓存map（存的是所有资源）
		Map<String,Org> temp = new HashMap<String, Org>();
		
		for (Org org : list) {
			String id = org.getOrgId();
			String parentOrgId = org.getParentOrgId();
			
			// 找子节点
			// 循环对象缓存，若上级为当前Org，则把对象作为当前Org的下级资源
			Collection<Org> orgArr = temp.values();
			
			// 循环对象缓存时，找Org对应的下级信息
			List<Org> childrens = new ArrayList<Org>();
			
			for (Org orgInfo : orgArr) {
				if(orgInfo.getParentOrgId() != null && orgInfo.getParentOrgId().equals(id)) {
					if (childrens == null) {
						childrens = new ArrayList<Org>();
					}
					childrens.add(orgInfo);
				}
			}
			
			org.setChildrens(childrens);
			
			// 找父节点
			// 若当前Org存在上级，查看当前Org的上级是否在缓存中，若在缓存中，则把当前Org保存在对应的上级对象下
			if (parentOrgId != null && !parentOrgId.equals("")) {
				// 从缓存map中取上级对象信息
				Org parentOrg = temp.get(parentOrgId);
				if (parentOrg != null) {
					// 获取上级对象的子节点
					List<Org> parentItems = parentOrg.getChildrens();
					if (parentItems == null) {
						parentItems = new ArrayList<Org>();
					}
					parentItems.add(org);
					parentOrg.setChildrens(parentItems);
					
				}
			}
			// 当前Org信息存放到缓存中
			temp.put(id, org);
		}
		
		List<Org> topList = new ArrayList<Org>();
		
		Collection<Org> orgList = temp.values();
		for (Org org : orgList) {
			String parentId = org.getParentOrgId();
			if (parentId == null || parentId.equals("")) {
				topList.add(org);
			}
		}
		
		return topList;
	}
	
	
	
	/**
	 * 递归查询完整机构树
	 * @return
	 */
	@RequestMapping("/getOrgTree")
	public static List<Org>  getOrgTree1() {
	    //将所有数据取出放进集合
	    List<Org> list = OrgDao.getAll();

	    List<Org> tree = getTree(list);

		return tree;
	}
	
	/**
	 * 组装树形结构
	 * @param list
	 * @return
	 */
	private static List<Org> getTree(List<Org> list) {
		
		// 获取根节点，即找出父节点为空的对象
        List<Org> topList = new ArrayList<Org>();
        for (Org org : list) {
            if(org.getParentOrgId() == null || org.getParentOrgId().equals("") ){
            	org.setNumberOfPlies(1);
            	topList.add(org);
            }
        }
        
        // 将根节点从原始list移除，减少下次处理数据
        list.removeAll(topList);
        
        // 递归封装树
        fillTree(topList,list);
        
        return topList;
    }
 
	/**
	 * 封装树
	 *
	 * @param topList 	要封装为树的顶级节点对象集合
	 * @param originalList 原始list数据
	 */
	public static void fillTree(List<Org> topList, List<Org> originalList) {
		// 遍历根节点
		for (Org org : topList) {
			
			// 封装根节点的子节点
			List<Org> children = fillChildren(org, originalList);
			if (children.isEmpty()) {
				continue;
			}
			
			// 将已组装的子节点从原始list移除，减少下次处理数据
			originalList.removeAll(children);
			
			// 封装子节点的 子节点，一致递归，直到最底层（子节点再没有 子节点）
			fillTree(children, originalList);
		}
	}
	    
	/**
     * 封装子对象
     * @param parent 父对象
     * @param originalList 待处理对象集合
     */
    public static List<Org> fillChildren(Org parent, List<Org> originalList) {
        List<Org> childList = new ArrayList<Org>();
        String parentId = parent.getOrgId();
        Integer level = parent.getNumberOfPlies();
        for (Org org : originalList) {
        	String childParentId = org.getParentOrgId();
            if (parentId.equals(childParentId)) {
            	org.setNumberOfPlies(level+1);
                childList.add(org);
            }
		}
        parent.setChildrens(childList);
        return childList;
    }

}
