package com.xinqi.graduationdesign.common;

import com.xinqi.kisso.common.util.EnvUtil;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.ConfigGenerator;

/**
 * 
 * 自动生成映射工具类
 * 
 * @author likun
 *
 */
public class AutoGeneratorHelper {

	/**
	 * 
	 * 测试 run 执行
	 * 
	 * <p>
	 * 配置方法查看 {@link ConfigGenerator}
	 * </p>
	 * 
	 */
	public static void main( String[] args ) {
		ConfigGenerator cg = new ConfigGenerator();
		cg.setEntityPackage("com.xinqi.graduationdesign.entity");
		cg.setMapperPackage("com.xinqi.graduationdesign.mapper");
		cg.setServicePackage("com.xinqi.graduationdesign.service");
		cg.setSuperServiceImpl("com.xinqi.graduationdesign.service.support.BaseServiceImpl");
		cg.setIdType(IdType.ID_WORKER);
		if (EnvUtil.isLinux()) {
			cg.setSaveDir("/Users/likun/graduationdesign/");
		} else {
			cg.setSaveDir("d:/graduationdesign/");
		}
		cg.setDbDriverName("com.mysql.jdbc.Driver");
		cg.setDbUser("root");
		cg.setDbPassword("root");
		cg.setDbUrl("jdbc:mysql://localhost/graduationdesign?characterEncoding=utf8");
		cg.setDbPrefix(false);
		AutoGenerator.run(cg);
	}
	
}
