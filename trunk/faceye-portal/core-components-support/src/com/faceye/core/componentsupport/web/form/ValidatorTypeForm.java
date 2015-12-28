package com.faceye.core.componentsupport.web.form;

import com.faceye.core.dao.hibernate.model.BaseObject;
/**
 *  
 * @author：宋海鹏
　* @Copy Right:早点网络
 * @System:早点网络支持系统
　* @Create Time:2007-9-22
 *　@Package And File Name:com.faceye.core.componentsupport.dao.model.ValidatorType.java
 * @Description:系统校验类型
 */
public class ValidatorTypeForm extends BaseObject {
/**
 * id：为本校验生成的唯一标识
 * name：本校验的中文名称
 */
	/**
	 * 校验函数名称
	 */
	private String functionName;
	/**
	 * 校验失败文字提示信息
	 */
	private String validatorMessage;
	
}
