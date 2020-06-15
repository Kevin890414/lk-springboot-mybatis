package com.example.likui.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**    
 *
 * @Description:自定义注解
 * @ClassName:  MyAnnotation     
 * @version V1.0 
 * @author: likui     
 * @date: 2020年5月13日 下午5:20:19  
 *
 */ 
@Target({ElementType.METHOD, ElementType.TYPE})//注解修饰的目标
@Retention(RetentionPolicy.RUNTIME)//注解的生命周期
@Documented
public @interface MyAnnotation {

}
