/*******************************************************************************
 * Copyright (c) 2007, 2021 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_FILTER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_FILTER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_FILTER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_HTTP_FILTER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_GENERIC_FILTER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_GENERIC_FILTER;

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class FilterSupertypesValidator extends AbstractSupertypesValidator {
	public static boolean isGenericFilterSuperclass(IDataModel dataModel) {
		if (QUALIFIED_JAKARTA_HTTP_FILTER.equals(getSuperclass(dataModel)))
			return true;
		
		if (QUALIFIED_HTTP_FILTER.equals(getSuperclass(dataModel)))
			return true;

		if (QUALIFIED_JAKARTA_GENERIC_FILTER.equals(getSuperclass(dataModel)))
			return true;

		if (QUALIFIED_GENERIC_FILTER.equals(getSuperclass(dataModel)))
			return true;
		
		if (hasSuperclass(dataModel, getSuperclass(dataModel), QUALIFIED_JAKARTA_GENERIC_FILTER))
			return true;

		if (hasSuperclass(dataModel, getSuperclass(dataModel), QUALIFIED_GENERIC_FILTER))
			return true;
		
		return false;
	}

	public static boolean isHttpFilterSuperclass(IDataModel dataModel) {
		if (QUALIFIED_JAKARTA_HTTP_FILTER.equals(getSuperclass(dataModel)))
			return true;

		if (QUALIFIED_HTTP_FILTER.equals(getSuperclass(dataModel)))
			return true;

		if (hasSuperclass(dataModel, getSuperclass(dataModel), QUALIFIED_JAKARTA_HTTP_FILTER))
			return true;

		if (hasSuperclass(dataModel, getSuperclass(dataModel), QUALIFIED_HTTP_FILTER))
			return true;

		return false;
	}

	public static boolean isFilterSuperclass(IDataModel dataModel) {
		if (QUALIFIED_JAKARTA_HTTP_FILTER.equals(getSuperclass(dataModel)))
			return true;
		
		if (QUALIFIED_HTTP_FILTER.equals(getSuperclass(dataModel)))
			return true;
		
		if (QUALIFIED_JAKARTA_GENERIC_FILTER.equals(getSuperclass(dataModel)))
			return true;
		
		if (QUALIFIED_GENERIC_FILTER.equals(getSuperclass(dataModel)))
			return true;
		
		if (getInterfaces(dataModel).contains(QUALIFIED_JAKARTA_FILTER))
			return true;
		
		if (getInterfaces(dataModel).contains(QUALIFIED_FILTER))
			return true;
		
		if (hasSuperInterface(dataModel, getSuperclass(dataModel), QUALIFIED_JAKARTA_FILTER))
			return true;
		
		if (hasSuperInterface(dataModel, getSuperclass(dataModel), QUALIFIED_FILTER))
			return true;
		
		for (Object iface : getInterfaces(dataModel)) {
			if (hasSuperInterface(dataModel, (String) iface, QUALIFIED_JAKARTA_FILTER)) 
				return true;
			if (hasSuperInterface(dataModel, (String) iface, QUALIFIED_FILTER)) 
				return true;
		}
		
		return false;
	}
}
