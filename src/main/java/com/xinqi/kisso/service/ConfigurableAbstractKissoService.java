/**
 * Copyright (c) 2011-2014, likun (likunzero@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.xinqi.kisso.service;

import com.xinqi.kisso.SSOConfig;

/**
 * <p>
 * SSO 单点登录服务抽象实现类
 * </p>
 * 
 * @author likun
 * @Date 2015-12-03
 */
public class ConfigurableAbstractKissoService extends AbstractKissoService {

	public ConfigurableAbstractKissoService() {
		config = SSOConfig.getInstance();
	}
	
}
