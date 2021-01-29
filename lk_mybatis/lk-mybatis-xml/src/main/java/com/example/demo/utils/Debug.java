package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Marker;


/**
 * Configurable Debug logging wrapper class
 *
 */
public final class Debug {

	
    public static final int ALWAYS = 0;
    public static final int VERBOSE = 1;
    public static final int TIMING = 2;
    public static final int INFO = 3;
    public static final int IMPORTANT = 4;
    public static final int WARNING = 5;
    public static final int ERROR = 6;
    public static final int FATAL = 7;

    private static final String[] levelProps = new String[8];
    
    private static final Map<String, Integer> levelStringMap = new HashMap<String, Integer>();

    private static final boolean levelOnCache[] = new boolean[8]; // this field is not thread safe

    private static final Map<Integer, Marker> levelsByMarkersMap = new HashMap<Integer, Marker>();
    private static final Map<String, Integer> markersByHighLevelMap = new HashMap<String, Integer>();
    private static final Map<String, Integer> markersByLowLevelMap = new HashMap<String, Integer>();
	
    static {
    	
        levelStringMap.put("verbose", Debug.VERBOSE);
        levelStringMap.put("timing", Debug.TIMING);
        levelStringMap.put("info", Debug.INFO);
        levelStringMap.put("important", Debug.IMPORTANT);
        levelStringMap.put("warning", Debug.WARNING);
        levelStringMap.put("error", Debug.ERROR);
        levelStringMap.put("fatal", Debug.FATAL);
        levelStringMap.put("always", Debug.ALWAYS);
        
        String levelPropsPrefix = "print.";
        
        levelProps[0] =""; 
    	for(String levelString:levelStringMap.keySet()) {
    			levelProps[levelStringMap.get(levelString)] = levelPropsPrefix+levelString;
    	}
    	

    } 	

    public static boolean isOn(int level) {
    	return levelOnCache[level];
    }

    public static void set(int level, boolean on) {
        levelOnCache[level] = on;
    }

    public static boolean get(int level) {
        return levelOnCache[level];
    }
    
    public static String getStr(Object... params) {
    	if(params == null|| params.length ==0) {
    		return null;
    	}
    	StringBuilder sb = new StringBuilder();
    	for(Object param:params) {
    		appendValue(sb,param);
    	}
    	return sb.toString();
    }
    
    private static void appendValue(StringBuilder sb, Object value) {
		if(value!=null &&value instanceof Map) {
			Map theParam = (Map)value;
			if(theParam.size()>0) {
				for(Object key:theParam.keySet()) {
					if("userLogin".equals(key)) {
						
					}else {
						sb.append(" ").append(key).append("=(");
						appendValue(sb,theParam.get(key));
						sb.append(") ");
					}
				}
			}
		}else {
			sb.append(" ").append(value);
		}		
    }
}
