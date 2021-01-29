package com.example.demo.testjava8.stream;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.utils.UtilMisc;

/**
 * 类说明
 * 
 * @author likui
 * @since 2020年8月18日 下午2:57:02
 * @version 1.0
 *
 */
public class Java8StreamTest {
	
	/** 
	 * @Description: 合并两个list<map>,并将userId相同的其它属性合并  
	 * @Title: mergeList   
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws   
	 */  
	public static List<Map<String,Object>> mergeList(){
		List<Map<String,Object>> list1 = new ArrayList<>();
		list1.add(UtilMisc.toMap("userId", "100001","userName","唐僧"));
		list1.add(UtilMisc.toMap("userId", "100002","userName","八戒"));
		list1.add(UtilMisc.toMap("userId", "100003","userName","悟空"));
		list1.add(UtilMisc.toMap("userId", "100004","userName","沙僧"));
		
		List<Map<String,Object>> list2 = new ArrayList<>();
		list2.add(UtilMisc.toMap("userId", "100001","gender","男","age",20));
		list2.add(UtilMisc.toMap("userId", "100002","gender","雄","age",1000));
		list2.add(UtilMisc.toMap("userId", "100003","gender","雄","age",600));
		list2.add(UtilMisc.toMap("userId", "100004","gender","男","age",800));
		
		List<Map<String, Object>> list = list1.stream().map(m -> {
			list2.stream().filter(m2-> Objects.equals(m.get("userId"),m2.get("userId"))).forEach(m2-> {
				m.put("gender",m2.get("gender"));
				m.put("age",m2.get("age"));
			});
			return m;
		}).collect(Collectors.toList());
		

		return list;
	}
	
	public static Map<String,Map<String,Object>> mergeListToMap(){
		List<Map<String,Object>> list1 = new ArrayList<>();
		list1.add(UtilMisc.toMap("userId", "100001","userName","唐僧"));
		list1.add(UtilMisc.toMap("userId", "100002","userName","八戒"));
		list1.add(UtilMisc.toMap("userId", "100003","userName","悟空"));
		list1.add(UtilMisc.toMap("userId", "100004","userName","沙僧"));
		
		List<Map<String,Object>> list2 = new ArrayList<>();
		list2.add(UtilMisc.toMap("userId", "100001","gender","男","age",20));
		list2.add(UtilMisc.toMap("userId", "100002","gender","雄","age",1000));
		list2.add(UtilMisc.toMap("userId", "100003","gender","雄","age",600));
		list2.add(UtilMisc.toMap("userId", "100004","gender","男","age",800));
		
		List<Map<String,Object>> list3 = new ArrayList<>();
		list3.add(UtilMisc.toMap("userId", "100001","orgId","CBI000001"));
		list3.add(UtilMisc.toMap("userId", "100002","orgId","CBI000002"));
		list3.add(UtilMisc.toMap("userId", "100003","orgId","CBI000003"));
		list3.add(UtilMisc.toMap("userId", "100004","orgId","CBI000004"));
		
		
		Map<String, Map<String, Object>> collect = list1.stream().map(m -> {
			list2.stream().filter(m2-> Objects.equals(m.get("userId"),m2.get("userId"))).forEach(m2-> {
				m.put("gender",m2.get("gender"));
				m.put("age",m2.get("age"));
			});
			return m;
		}).collect(Collectors.toMap(m-> (String)m.get("userId"),m->m));
		

		return collect;
	}
	
	public static List<Map<String,Object>> sortList(){
		
		List<Map<String,Object>> list2 = new ArrayList<>();
		list2.add(UtilMisc.toMap("userId", "100001","gender","男","age",20));
		list2.add(UtilMisc.toMap("userId", "100002","gender","雄","age",1000));
		list2.add(UtilMisc.toMap("userId", "100003","gender","雄","age",600));
		list2.add(UtilMisc.toMap("userId", "100004","gender","男","age",800));
		
		list2.sort((m1,m2)-> {
			Integer age1 = (Integer)m1.get("age");
			Integer age2 = (Integer)m2.get("age");
			return age1 > age2 ? 1 : age1 == age2 ? 0 : -1;
		});
		
		
		return list2;
	}
	
	public static List<Map<String,Object>> mergeList2(){
		List<Map<String,Object>> list1 = new ArrayList<>();
		list1.add(UtilMisc.toMap("userId", "100001","userName","唐僧"));
		list1.add(UtilMisc.toMap("userId", "100002","userName","八戒"));
		list1.add(UtilMisc.toMap("userId", "100003","userName","悟空"));
		list1.add(UtilMisc.toMap("userId", "100004","userName","沙僧"));
		
		List<Map<String,Object>> list2 = new ArrayList<>();
		list2.add(UtilMisc.toMap("userId", "100001","gender","男","age",20));
		list2.add(UtilMisc.toMap("userId", "100002","gender","雄","age",1000));
		list2.add(UtilMisc.toMap("userId", "100003","gender","雄","age",600));
		list2.add(UtilMisc.toMap("userId", "100004","gender","男","age",800));
		
		List<Map<String, Object>> collect = Stream.of(list1,list2).flatMap(e-> e.stream()).collect(Collectors.toList());
		
		
		System.out.println(collect);

		return null;
	}
	
	/**   
	 * @Description: 测试使用flatMap
	 * @Title: testFlatMap   
	 * @return: void      
	 * @throws   
	 */  
	public static void testFlatMap(){
        List<String> teamIndia = Arrays.asList("Virat", "Dhoni", "Jadeja");
        List<String> teamAustralia = Arrays.asList("Warner", "Watson", "Smith");
        List<String> teamEngland = Arrays.asList("Alex", "Bell", "Broad");
        List<String> teamNewZeland = Arrays.asList("Kane", "Nathan", "Vettori");
        List<String> teamSouthAfrica = Arrays.asList("AB", "Amla", "Faf");
        List<String> teamWestIndies = Arrays.asList("Sammy", "Gayle", "Narine");
        List<String> teamSriLanka = Arrays.asList("Mahela", "Sanga", "Dilshan");
        List<String> teamPakistan = Arrays.asList("Misbah", "Afridi", "Shehzad");
        
        List<List<String>> playersInWorldCup2016 = new ArrayList<>();
        playersInWorldCup2016.add(teamIndia);
        playersInWorldCup2016.add(teamAustralia);
        playersInWorldCup2016.add(teamEngland);
        playersInWorldCup2016.add(teamNewZeland);
        playersInWorldCup2016.add(teamSouthAfrica);
        playersInWorldCup2016.add(teamWestIndies);
        playersInWorldCup2016.add(teamSriLanka);
        playersInWorldCup2016.add(teamPakistan);
        
        // Let's print all players before Java 8
        List<String> listOfAllPlayers = new ArrayList<>();
        
        for(List<String> team : playersInWorldCup2016){
            for(String name : team){
                listOfAllPlayers.add(name);
            }
        }
        
        System.out.println("Players playing in world cup 2016");
        System.out.println(listOfAllPlayers);
        
        
        // Now let's do this in Java 8 using FlatMap
        List<String> flatMapList = playersInWorldCup2016.stream()
                                                        .flatMap(pList -> pList.stream())
                                                        .collect(Collectors.toList());
        
        System.out.println("List of all Players using Java 8");
        System.out.println(flatMapList);
	}

	
//	public static List<Map<String,Object>> sumListMapKey(){
//		
//		List<Map<String,Object>> list = new ArrayList<>();
//		list.add(UtilMisc.toMap("userId", "100001","gender","男","age",20,"money",10));
//		list.add(UtilMisc.toMap("userId", "100002","gender","雄","age",1000,"money",20));
//		list.add(UtilMisc.toMap("userId", "100003","gender","雄","age",600,"money",30));
//		list.add(UtilMisc.toMap("userId", "100001","gender","男","age",800,"money",40));
//		list.add(UtilMisc.toMap("userId", "100002","gender","男","age",800,"money",50));
//		
//		list.stream().map(m-> {
//			list.stream().filter(m2-> Objects.equals(m.get("userId"),m2.get("userId"))).forEach(m2->{
//				Integer money1 = (Integer)m.get("money");
//				Integer money2 = (Integer)m2.get("money");
//				m.put("money", money1+money2);
//				m2.put("money", money1+money2);
//			});
//		});
//		
//		return list;
//	}
	
	public static String getName() {
		return "123";
	}
	
	public static String formatName(String name) {
		
		
		long count = name.chars().filter(e-> Character.isLowerCase(e)).count();
		
		
		return name+"456";
	}
	
	
	public static void main(String[] args) {
//		FileInputStream inputStream = null;
//		ByteArrayOutputStream byteOut = null;
//		try {
//			inputStream = new FileInputStream("");
//			byteOut = new ByteArrayOutputStream();
//			
//			byte[] bt = new byte[1024];
//			
//			for (int i = inputStream.read(bt); i != -1;) {
//				byteOut.write(bt, 0, i);
//			}
//			
//			byteOut.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (inputStream != null) {
//					inputStream.close();
//				}
//				if (byteOut != null) {
//					byteOut.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		System.out.println(mergeList());
//		System.out.println(mergeListToMap());
		
//		System.out.println(sumListMapKey());
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime minusDays = now.minusDays(544);
		System.out.println(minusDays);
		
//		testFlatMap();
		
		
	}
}
