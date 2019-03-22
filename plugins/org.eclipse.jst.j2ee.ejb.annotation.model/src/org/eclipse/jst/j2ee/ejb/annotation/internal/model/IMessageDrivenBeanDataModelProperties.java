/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;

public interface IMessageDrivenBeanDataModelProperties extends IEnterpriseBeanClassDataModelProperties, IAnnotationsDataModel {

	public static final String DESTINATIONNAME = "MessageDrivenBeanDataModel.DESTINATIONNAME"; //$NON-NLS-1$
	public static final String DESTINATIONTYPE = "MessageDrivenBeanDataModel.DESTINATIONTYPE"; //$NON-NLS-1$

	public final static String EJB_INTERFACES = "MessageDrivenBeanDataModel.EJB_INTERFACES"; //$NON-NLS-1$
													
}
