package com.hz.dxf.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: Excel注解用于生成excel文件
 * @author Blossom
 * @time 2016年10月22日 下午4:59:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface Excel {

	//列名
	public String name() default "";
	
	//宽度
	public int width() default 20;
	
	//忽略改字段
	public boolean skip() default false;

}
