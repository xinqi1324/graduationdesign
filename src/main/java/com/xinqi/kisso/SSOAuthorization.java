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
package com.xinqi.kisso;

/**
 * <p>
 * SSO 权限授权接口
 * </p>
 * 
 * @author likun
 * @Date 2016-04-03
 */
public interface SSOAuthorization {

	boolean isPermitted(Token token, String permission);

}
