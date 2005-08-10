/*******************************************************************************
 * Copyright (c) 2002, 2003,2004,2005 Eteration Bilisim A.S.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Eteration Bilisim A.S. - initial API and implementation
 *     Naci Dai
 * For more information on eteration, please see
 * <http://www.eteration.com/>.
 ***************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.provider;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IMessageDrivenBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.ISessionBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.ModelPlugin;
import org.eclipse.jst.j2ee.ejb.annotation.internal.provider.IAnnotationProvider;
import org.eclipse.jst.j2ee.ejb.annotation.internal.provider.IEJBGenerator;
import org.eclipse.jst.j2ee.ejb.annotations.internal.classgen.EjbBuilder;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.EjbEmitter;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.EmitterUtilities;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.MessageDrivenEjbEmitter;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.SessionEjbEmitter;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletRuntime;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class XDocletAnnotationProvider implements IAnnotationProvider, IEJBGenerator {

	public boolean isEjbAnnotationProvider() {
		return true;
	}

	public boolean isServletAnnotationProvider() {
		return true;
	}

	public boolean isWebServiceAnnotationProvider() {
		return false;
	}

	public boolean isValid() {
		XDocletRuntime runtime = new XDocletRuntime();
		runtime.setHome(XDocletPreferenceStore.getProperty(XDocletPreferenceStore.XDOCLETHOME));
		runtime.setVersion(XDocletPreferenceStore.getProperty(XDocletPreferenceStore.XDOCLETVERSION));
		return runtime.isValid(XDocletPreferenceStore.getProperty(XDocletPreferenceStore.XDOCLETVERSION));
	}

	public String getName() {
		return "XDocletAnnotionProvider"; //$NON-NLS-1$
	}

	public void generateSession(ISessionBean delegate, IProgressMonitor monitor) throws CoreException, InterruptedException {
		
		IDataModel dataModel = delegate.getDataModel();

		
		
			String comment = ""; //$NON-NLS-1$
			String stub = ""; //$NON-NLS-1$
			String method=""; //$NON-NLS-1$

			IConfigurationElement preferredAnnotation = EmitterUtilities.findEmitter("XDoclet"); //$NON-NLS-1$
			
			try {
				EjbEmitter ejbEmitter = new SessionEjbEmitter(preferredAnnotation);
				ejbEmitter.setMonitor(monitor);
				comment = ejbEmitter.emitTypeComment(delegate);
				stub = ejbEmitter.emitTypeStub(delegate);
				method = ejbEmitter.emitInterfaceMethods(delegate);
				ejbEmitter.deleteProject();
			}catch (CoreException e) {
				throw e;
			} catch (Exception e) {
				throw new CoreException(new Status(IStatus.ERROR,ModelPlugin.PLUGINID,0,"Session EJB Emitters Failed",e));
			}

			
		
			EjbBuilder ejbBuilder = new EjbBuilder();
			ejbBuilder.setConfigurationElement(preferredAnnotation);
			ejbBuilder.setMonitor(monitor);
			ejbBuilder.setPackageFragmentRoot((IPackageFragmentRoot)dataModel.getProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE_FRAGMENT_ROOT));
			ejbBuilder.setEnterpriseBeanDelegate(delegate);
			ejbBuilder.setTypeName(dataModel.getStringProperty(INewJavaClassDataModelProperties.CLASS_NAME));
			ejbBuilder.setPackageName(dataModel.getStringProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE));
				
			ejbBuilder.setTypeComment(comment);
			ejbBuilder.setTypeStub(stub);
			ejbBuilder.setMethodStub(method);
			ejbBuilder.setFields("");
				
			ejbBuilder.createType();
			
			IType bean = ejbBuilder.getCreatedType();
			IResource javaFile = bean.getCorrespondingResource();
			IProject project = (IProject) dataModel.getProperty(INewJavaClassDataModelProperties.PROJECT);
			initializeBuilder(monitor, preferredAnnotation,javaFile, project);
			project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
		
		
	}

	public void generateMessageDriven(IMessageDrivenBean delegate, IProgressMonitor monitor) throws CoreException, InterruptedException {

			IDataModel dataModel = delegate.getDataModel();

			String comment = "";
			String stub = "";
			String method = "";
			String fields = "";
			IConfigurationElement emitterConfiguration = EmitterUtilities.findEmitter("XDoclet");

			try {
				EjbEmitter ejbEmitter = new MessageDrivenEjbEmitter(emitterConfiguration);
				ejbEmitter.setMonitor(monitor);
				fields = ejbEmitter.emitFields(delegate);
				comment = ejbEmitter.emitTypeComment(delegate);
				stub = ejbEmitter.emitTypeStub(delegate);
				method = ejbEmitter.emitInterfaceMethods(delegate);
				ejbEmitter.deleteProject();
			} catch (CoreException e) {
				throw e;
			} catch (Exception e) {
				throw new CoreException(new Status(IStatus.ERROR, ModelPlugin.PLUGINID, 0, "MessageDriven EJB Emitters Failed", e));
			}

			EjbBuilder ejbBuilder = new EjbBuilder();
			ejbBuilder.setConfigurationElement(emitterConfiguration);
			ejbBuilder.setMonitor(monitor);
			ejbBuilder.setPackageFragmentRoot((IPackageFragmentRoot)dataModel.getProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE_FRAGMENT_ROOT));
			ejbBuilder.setEnterpriseBeanDelegate(delegate);
			ejbBuilder.setTypeName(dataModel.getStringProperty(INewJavaClassDataModelProperties.CLASS_NAME));
			ejbBuilder.setPackageName(dataModel.getStringProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE));

			ejbBuilder.setTypeComment(comment);
			ejbBuilder.setTypeStub(stub);
			ejbBuilder.setMethodStub(method);
			ejbBuilder.setFields(fields);

			ejbBuilder.createType();
			IType bean = ejbBuilder.getCreatedType();
			IResource javaFile = bean.getCorrespondingResource();
			IProject project = (IProject) dataModel.getProperty(INewJavaClassDataModelProperties.PROJECT);

			initializeBuilder(monitor, emitterConfiguration, javaFile, project);
			project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);

		}

		protected void initializeBuilder(IProgressMonitor monitor, IConfigurationElement emitterConfiguration, IResource javaFile,
				IProject project) throws CoreException {
			EmitterUtilities.addAnnotationBuilderToProject(emitterConfiguration, project);
		}

}
