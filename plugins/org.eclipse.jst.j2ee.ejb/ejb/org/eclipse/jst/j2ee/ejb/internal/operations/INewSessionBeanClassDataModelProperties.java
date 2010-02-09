/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;

public interface INewSessionBeanClassDataModelProperties extends INewEnterpriseBeanClassDataModelProperties {
	
	/**
	 * Optional, List of BusinessInterface objects property containing all the
	 * qualified names of business interfaces the new session bean class should
	 * implement.
	 * 
	 * @see BusinessInterface
	 * @deprecated use {@link INewJavaClassDataModelProperties#INTERFACES}
	 *             instead. BUSINESS_INTERFACES is a redundancy of the INTERFACE
	 *             property. To be removed at least after 3.1
	 * 
	 */
	public static final String BUSINESS_INTERFACES = "INewSessionBeanClassDataModelProperties.BUSINESS_INTERFACES"; //$NON-NLS-1$
	
	public static final String REMOTE_BUSINESS_INTERFACE = "INewSessionBeanClassDataModelProperties.REMOTE_BUSINESS_INTERFACE"; //$NON-NLS-1$
	
	public static final String LOCAL_BUSINESS_INTERFACE = "INewSessionBeanClassDataModelProperties.LOCAL_BUSINESS_INTERFACE"; //$NON-NLS-1$

	public static final String REMOTE_HOME_INTERFACE = "INewSessionBeanClassDataModelProperties.REMOTE_HOME_INTERFACE"; //$NON-NLS-1$
	
	public static final String LOCAL_HOME_INTERFACE = "INewSessionBeanClassDataModelProperties.LOCAL_HOME_INTERFACE"; //$NON-NLS-1$
	
	public static final String LOCAL_COMPONENT_INTERFACE = "INewSessionBeanClassDataModelProperties.LOCAL_COMPONENT_INTERFACE"; //$NON-NLS-1$
	
	public static final String REMOTE_COMPONENT_INTERFACE = "INewSessionBeanClassDataModelProperties.REMOTE_COMPONENT_INTERFACE"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate a remote
	 * business interface. The default is false.
	 */
	public static final String REMOTE = "INewSessionBeanClassDataModelProperties.REMOTE"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate a local
	 * business interface. The default is true.
	 */
	public static final String LOCAL = "INewSessionBeanClassDataModelProperties.LOCAL"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate a EJB 2.x
	 * compatible local home and components interfaces. The default is false.
	 */
	public static final String LOCAL_HOME = "INewSessionBeanClassDataModelProperties.LOCAL_HOME"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate a EJB 2.x
	 * compatible remote home and components interfaces. The default is false.
	 */
	public static final String REMOTE_HOME = "INewSessionBeanClassDataModelProperties.REMOTE_HOME"; //$NON-NLS-1$

	/**
	 * Required, String property that determines the state type of the session
	 * bean. Valid values are the string representation of the
	 * <code>StateType<code> enumeration.
	 * 
	 * @see StateType
	 */
	public static final String STATE_TYPE = "INewSessionBeanClassDataModelProperties.STATE_TYPE"; //$NON-NLS-1$
	
}
