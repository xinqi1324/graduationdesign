package com.xinqi.graduationdesign.common.enums;

import com.xinqi.framework.common.IEnum;

public enum UserType implements IEnum {
	ADMIN(1, "管理员"),
	EMPLOYEE(2, "员工"),
	DEPARTMENT_MANAGER(3, "部门经理"),
	GENERAL_MANAGER(4, "总经理"),
	HR(5, "人事部经理"),
	BOSS(6, "董事长");

	private final int key;
	private final String desc;

	UserType(final int key, final String desc) {
		this.key = key;
		this.desc = desc;
	}

	@Override
	public int key() {
		return this.key;
	}

	@Override
	public String desc() {
		return this.desc;
	}

}
