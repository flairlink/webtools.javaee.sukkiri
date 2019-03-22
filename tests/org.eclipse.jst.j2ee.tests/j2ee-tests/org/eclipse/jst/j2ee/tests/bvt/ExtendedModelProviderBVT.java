/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.tests.bvt;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ExtendedModelProviderBVT extends TestSuite {

	public ExtendedModelProviderBVT() {
		super();
		addTest(org.eclipse.jst.common.annotations.tests.AnnotationProviderTest.suite());
		addTest(org.eclipse.jst.jee.model.mergers.tests.MergersTestSuite.suite());
	}
	
    public static Test suite(){
    	return new ExtendedModelProviderBVT();
    }
}
