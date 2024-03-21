/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.web.project.facet;

import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class WebFacetUtils {

    public static final IProjectFacet WEB_FACET = ProjectFacetsManager.getProjectFacet(IJ2EEFacetConstants.DYNAMIC_WEB);
    public static final IProjectFacet WEBFRAGMENT_FACET = ProjectFacetsManager.getProjectFacet(IJ2EEFacetConstants.WEBFRAGMENT);
	public static final IProjectFacetVersion WEB_22 = WEB_FACET.getVersion("2.2"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEB_23 = WEB_FACET.getVersion("2.3"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEB_24 = WEB_FACET.getVersion("2.4"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEB_25 = WEB_FACET.getVersion("2.5"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEB_30 = WEB_FACET.getVersion("3.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEB_31 = WEB_FACET.getVersion("3.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEB_40 = WEB_FACET.getVersion("4.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEB_50 = WEB_FACET.getVersion("5.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEB_60 = WEB_FACET.getVersion("6.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEBFRAGMENT_30 = WEBFRAGMENT_FACET.getVersion("3.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEBFRAGMENT_31 = WEBFRAGMENT_FACET.getVersion("3.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEBFRAGMENT_40 = WEBFRAGMENT_FACET.getVersion("4.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEBFRAGMENT_50 = WEBFRAGMENT_FACET.getVersion("5.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEBFRAGMENT_60 = WEBFRAGMENT_FACET.getVersion("6.0"); //$NON-NLS-1$
	
	public static final IProjectFacet WEB_XDOCLET_FACET = getWebDocletFacet();
	
	public static IProjectFacet getProjectFacet(String id) {
		try {
			return ProjectFacetsManager.getProjectFacet(id);
		} catch (IllegalArgumentException e) {
			return null;
		} 
	}
	
	private static IProjectFacet getWebDocletFacet() {
		try {
			return ProjectFacetsManager.getProjectFacet(IJ2EEFacetConstants.DYNAMIC_WEB_XDOCLET);
		} catch (IllegalArgumentException e) {
			// the web doclet facet is not defined
			return null;
		}
	}

}
