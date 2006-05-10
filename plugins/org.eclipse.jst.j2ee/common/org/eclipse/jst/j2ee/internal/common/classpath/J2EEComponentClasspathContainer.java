/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common.classpath;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * This classpath container is based on the Component references; not the manifest entries. Other
 * mechanisms are in place to ensure that the component references are updated when the manifest is
 * updated, and also to make sure the manifest is updated when the component references are updated.
 * 
 */
public class J2EEComponentClasspathContainer implements IClasspathContainer {

	public static final String CONTAINER_ID = "org.eclipse.jst.j2ee.internal.module.container"; //$NON-NLS-1$

	private IPath containerPath;
	private IJavaProject javaProject;
	private IClasspathEntry[] entries = new IClasspathEntry[0];

	private class LastUpdate {
		private int refCount = 0;
		private boolean[] isBinary = new boolean[refCount];
		private IPath[] paths = new IPath[refCount];
	}

	private LastUpdate lastUpdate = new LastUpdate();

	public J2EEComponentClasspathContainer(IPath path, IJavaProject javaProject) {
		this.containerPath = path;
		this.javaProject = javaProject;
		update();
	}

	protected void update() {
		IVirtualComponent component = ComponentCore.createComponent(javaProject.getProject());
		if (component == null) {
			return;
		}
		IVirtualReference[] refs = component.getReferences();
		IVirtualComponent comp = null;

		// avoid updating the container if references haven't changed
		if (refs.length == lastUpdate.refCount) {
			boolean refsChanged = false;
			for (int i = 0; i < lastUpdate.refCount && !refsChanged; i++) {
				comp = refs[i].getReferencedComponent();
				if (comp.isBinary() != lastUpdate.isBinary[i]) {
					refsChanged = true;
				} else {
					IPath path = null;
					if (comp.isBinary()) {
						VirtualArchiveComponent archiveComp = (VirtualArchiveComponent) comp;
						java.io.File diskFile = archiveComp.getUnderlyingDiskFile();
						if (diskFile.exists())
							path = new Path(diskFile.getAbsolutePath());
						else {
							IFile iFile = archiveComp.getUnderlyingWorkbenchFile();
							path = iFile.getFullPath();
						}
					} else {
						path = comp.getProject().getFullPath();
					}
					if (!path.equals(lastUpdate.paths[i])) {
						refsChanged = true;
					}
				}
			}
			if (!refsChanged) {
				return;
			}
		}

		lastUpdate.refCount = refs.length;
		lastUpdate.isBinary = new boolean[lastUpdate.refCount];
		lastUpdate.paths = new IPath[lastUpdate.refCount];

		boolean isWeb = J2EEProjectUtilities.isDynamicWebProject(component.getProject());
		boolean shouldAdd = true;

		List entriesList = new ArrayList();

		try {
			for (int i = 0; i < refs.length; i++) {
				comp = refs[i].getReferencedComponent();
				lastUpdate.isBinary[i] = comp.isBinary();
				if (comp.isBinary()) {
					VirtualArchiveComponent archiveComp = (VirtualArchiveComponent) comp;
					java.io.File diskFile = archiveComp.getUnderlyingDiskFile();
					if (diskFile.exists()) {
						lastUpdate.paths[i] = new Path(diskFile.getAbsolutePath());
						entriesList.add(JavaCore.newLibraryEntry(lastUpdate.paths[i], null, null));
					} else {
						IFile iFile = archiveComp.getUnderlyingWorkbenchFile();
						lastUpdate.paths[i] = iFile.getFullPath();
						shouldAdd = !(isWeb && refs[i].getRuntimePath().equals(J2EEComponentReferenceUpdater.WEBLIB));
						if(shouldAdd){
							entriesList.add(JavaCore.newLibraryEntry(lastUpdate.paths[i], null, null));
						}
					}
				} else {
					IProject project = comp.getProject();
					lastUpdate.paths[i] = project.getFullPath();
					entriesList.add(JavaCore.newProjectEntry(lastUpdate.paths[i], false));
				}
			}
		} finally {
			entries = new IClasspathEntry[entriesList.size()];
			for (int i = 0; i < entries.length; i++) {
				entries[i] = (IClasspathEntry) entriesList.get(i);
			}
		}
	}

	public void install() {
		final IJavaProject[] projects = new IJavaProject[]{javaProject};
		final IClasspathContainer[] conts = new IClasspathContainer[]{this};

		try {
			JavaCore.setClasspathContainer(containerPath, projects, conts, null);
		} catch (JavaModelException e) {
			Logger.getLogger().log(e);
		}
	}

	public void refresh() {
		update();
		install();
	}

	public IClasspathEntry[] getClasspathEntries() {
		return entries;
	}

	public String getDescription() {
		return J2EECommonMessages.J2EE_MODULE_CLASSPATH_CONTAINER_NAME;
	}

	public int getKind() {
		return K_APPLICATION;
	}

	public IPath getPath() {
		return containerPath;
	}
}