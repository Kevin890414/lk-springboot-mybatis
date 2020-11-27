package com.example.demo.utils;

import java.util.Map;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

/**
* map操作工具类
* @author likui 
* @since  2020年11月26日 上午9:23:15
* @version 1.0
*
*/
public class MapUtil {
	
	/**
     * 比较两个map的差异，并 取交集、差集等，需依赖google jar包
     * <dependency>
	 *      <groupId>com.google.guava</groupId>
	 *      <artifactId>guava</artifactId>
	 *      <version>21.0</version>
	 * </dependency>
	 *  
	 *  Maps.difference(Map, Map)用来比较两个Map以获取所有不同点。该方法返回MapDifference对象
	 *  以下操作 原map数据不变
     * 
     */
    public static void test(Map<String,Object> map1, Map<String,Object> map2) {
        MapDifference<String, Object> difference = Maps.difference(map1, map2);
        // 是否有差异，返回boolean
        boolean areEqual = difference.areEqual();
        System.out.println("比较两个Map是否有差异:" + areEqual);
        
        // 两个map的交集
        Map<String, Object> entriesInCommon = difference.entriesInCommon();
        System.out.println("两个map都有的部分（交集）===：" + entriesInCommon);
        
        // 键相同但是值不同值映射项。返回的Map的值类型为MapDifference.ValueDifference，以表示左右两个不同的值
        Map<String, MapDifference.ValueDifference<Object>> entriesDiffering = difference.entriesDiffering();
        System.out.println("键相同但是值不同值映射项===：" + entriesDiffering);
        
        // 键只存在于左边Map的映射项
        Map<String, Object> onlyOnLeft = difference.entriesOnlyOnLeft();
        System.out.println("键只存在于左边Map的映射项:" + onlyOnLeft);
        
        // 键只存在于右边Map的映射项
        Map<String, Object> entriesOnlyOnRight = difference.entriesOnlyOnRight();
        System.out.println("键只存在于右边Map的映射项:" + entriesOnlyOnRight);
    }

}
