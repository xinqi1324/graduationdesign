package com.xinqi.graduationdesign.service;

import com.xinqi.graduationdesign.entity.PsysDept;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * PsysDept 表数据服务层接口
 *
 */
public interface IPsysDeptService extends ISuperService<PsysDept> {
    boolean deleteDept(Long deptId) throws Exception;

}