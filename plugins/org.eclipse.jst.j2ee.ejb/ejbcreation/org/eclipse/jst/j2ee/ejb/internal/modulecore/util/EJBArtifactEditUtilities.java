package org.eclipse.jst.j2ee.ejb.internal.modulecore.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverter;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleWorkbenchURIConverterImpl;
import org.eclipse.jst.j2ee.internal.project.J2EEWorkbenchURIConverterImpl;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IFlexibleProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class EJBArtifactEditUtilities {

	public static EJBJarFile asEJBJarFile(IVirtualComponent virtualComponent, boolean shouldExportSource) throws OpenFailureException {

		/*IProject proj = getProject();
		if (proj == null)
			return null;

		if (isBinaryProject()) {
			String location = ((J2EEModuleWorkbenchURIConverterImpl) getJ2EEWorkbenchURIConverter()).getInputJARLocation().toOSString();
			ArchiveOptions options = new ArchiveOptions();
			options.setIsReadOnly(true);
			return getCommonArchiveFactory().openEJB11JarFile(options, location);
		}
		EJBProjectLoadStrategyImpl loader = new EJBProjectLoadStrategyImpl(proj);
		loader.setExportSource(shouldExportSource);
		loader.setResourceSet(this.getResourceSet());
		return getCommonArchiveFactory().openEJB11JarFile(loader, proj.getName());*/
		return null;
	}
	
	public static IVirtualComponent getEJBComponent(EnterpriseBean bean) {
		IProject project = ProjectUtilities.getProject(bean);
		IFlexibleProject flexProject = ComponentCore.createFlexibleProject(project);
		IVirtualComponent[] components = flexProject.getComponents();
		for (int i = 0; i < components.length; i++) {
			IVirtualComponent component = components[i];
			EJBArtifactEdit edit = null;
			try {
				if (component.getComponentTypeId().equals(IModuleConstants.JST_EJB_MODULE)) {
					edit = EJBArtifactEdit.getEJBArtifactEditForRead(component);
					EJBJar jar = edit.getEJBJar();
					if (jar.getEnterpriseBeanNamed(bean.getName()) != null)
						return component;
				}
			} finally {
				if (edit != null)
					edit.dispose();
			}
		}
		return null;
	}
	
	public static J2EEWorkbenchURIConverterImpl getJ2EEWorkbenchURIConverter(EJBArtifactEdit artifactEdit ) {
		WorkbenchURIConverter conv =  (WorkbenchURIConverter) artifactEdit.getDeploymentDescriptorResource().getResourceSet().getURIConverter();
		if (conv instanceof J2EEModuleWorkbenchURIConverterImpl)
			return (J2EEModuleWorkbenchURIConverterImpl) conv;
		return null;
	}
}

