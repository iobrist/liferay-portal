/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.upgrade.util;

import com.liferay.portal.kernel.upgrade.StagnantRowException;
import com.liferay.portal.kernel.upgrade.util.ValueMapper;

/**
 * @author Brian Wing Shun Chan
 */
public class LazyPKUpgradeColumnImpl extends PKUpgradeColumnImpl {

	public LazyPKUpgradeColumnImpl(String name) {
		super(name, true);
	}

	public LazyPKUpgradeColumnImpl(String name, Integer oldColumnType) {
		super(name, oldColumnType, true);
	}

	@Override
	public Object getNewValue(Object oldValue) throws Exception {
		ValueMapper valueMapper = getValueMapper();

		Long newValue = null;

		try {
			newValue = (Long)valueMapper.getNewValue(oldValue);
		}
		catch (StagnantRowException sre) {
			newValue = new Long(increment());

			valueMapper.mapValue(oldValue, newValue);
		}

		return newValue;
	}

}