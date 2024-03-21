/*******************************************************************************
 * Copyright (c) 2007, 2022 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.ABSTRACT_METHODS;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CLASS_NAME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.SUPERCLASS;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.DESTROY;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.DO_FILTER;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.FILTER_MAPPINGS;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.INIT;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.INIT_PARAM;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.DISPLAY_NAME;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.ASYNC_SUPPORT;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_FILTER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_FILTER;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.web.IServletConstants;
import org.eclipse.jst.j2ee.web.validation.UrlPattern;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class NewFilterClassDataModelProvider extends
		NewWebClassDataModelProvider {

	/**
	 * String array of the default, minimum required fully qualified Filter
	 * interfaces
	 */
	private final static String[] FILTER_INTERFACES = { QUALIFIED_FILTER }; 
	private final static String[] JAKARTA_FILTER_INTERFACES = { QUALIFIED_JAKARTA_FILTER };
	
	@Override
	public boolean isPropertyEnabled(String propertyName) {
		if (ABSTRACT_METHODS.equals(propertyName)) {
			return FilterSupertypesValidator.isFilterSuperclass(model);
		} else if (INIT.equals(propertyName) || 
				DESTROY.equals(propertyName) ||
				DO_FILTER.equals(propertyName)) {
			boolean genericFilter = FilterSupertypesValidator.isFilterSuperclass(model);
			boolean inherit = model.getBooleanProperty(ABSTRACT_METHODS);
			return genericFilter && inherit;
		}

		// Otherwise return super implementation
		return super.isPropertyEnabled(propertyName);
	}
    
	/**
	 * Subclasses may extend this method to provide their own default operation
	 * for this data model provider. This implementation uses the
	 * AddFilterOperation to drive the filter creation. It will not return null.
	 * 
	 * @see IDataModel#getDefaultOperation()
	 * 
	 * @return IDataModelOperation AddFilterOperation
	 */
    @Override
	public IDataModelOperation getDefaultOperation() {
		return new AddFilterOperation(getDataModel());
	}

	/**
	 * Subclasses may extend this method to add their own data model's
	 * properties as valid base properties.
	 * 
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
    @Override
	public Set getPropertyNames() {
		// Add filter specific properties defined in this data model
		Set propertyNames = super.getPropertyNames();
		
		propertyNames.add(INIT);
		propertyNames.add(DESTROY);
		propertyNames.add(DO_FILTER);
		propertyNames.add(INIT_PARAM);
        propertyNames.add(FILTER_MAPPINGS);
        propertyNames.add(ASYNC_SUPPORT);
        
		return propertyNames;
	}

	/**
	 * Subclasses may extend this method to provide their own default values for
	 * any of the properties in the data model hierarchy. This method does not
	 * accept a null parameter. It may return null. This implementation sets
	 * annotation use to be true, and to generate a filter with doFilter.
	 * 
	 * @see NewJavaClassDataModelProvider#getDefaultProperty(String)
	 * @see IDataModelProvider#getDefaultProperty(String)
	 * 
	 * @param propertyName
	 * @return Object default value of property
	 */
    @Override
	public Object getDefaultProperty(String propertyName) {
		// Generate a init and destroy methods by default only if a class
		// not extending HttpFilter
		if (propertyName.equals(DESTROY) || propertyName.equals(INIT)) {
			if (!(FilterSupertypesValidator.isHttpFilterSuperclass(model))) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		}

        if (propertyName.equals(DO_FILTER))
            return Boolean.TRUE;
		else if (propertyName.equals(FILTER_MAPPINGS))
			return getDefaultFilterMapping();
		else if (propertyName.equals(INTERFACES)) {
			if (projectUsesJakartaPackages()) {
				return getJakartaFilterInterfaces();
			}
			return getFilterInterfaces();
		}
		else if (propertyName.equals(SUPERCLASS)) {
			if (projectUsesJakartaPackages()) {
				return IServletConstants.QUALIFIED_JAKARTA_HTTP_FILTER;
			}
			return IServletConstants.QUALIFIED_HTTP_FILTER;
		}
        
		// Otherwise check super for default value for property
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * Returns the default Url Mapping depending upon the display name of the
	 * Filter
	 * 
	 * @return List containting the default Url Mapping
	 */
	private Object getDefaultFilterMapping() {
		List filterMappings = null;
		String text = (String) getProperty(DISPLAY_NAME);
		if (text != null) {
		    filterMappings = new ArrayList();
		    filterMappings.add(new FilterMappingItem(FilterMappingItem.URL_PATTERN, "/" + text)); //$NON-NLS-1$
		}
		return filterMappings;
	}

	/**
	 * Subclasses may extend this method to provide their own validation on any
	 * of the valid data model properties in the hierarchy. This implementation
	 * adds validation for the init params, filter mappings, display name, and
	 * existing class fields specific to the filter java class creation. It does
	 * not accept a null parameter. This method will not return null.
	 * 
	 * @see NewJavaClassDataModelProvider#validate(String)
	 * 
	 * @param propertyName
	 * @return IStatus is property value valid?
	 */
	@Override
	public IStatus validate(String propertyName) {
		// If our default is the superclass, we know it is ok
		if (propertyName.equals(SUPERCLASS) && "".equals(getStringProperty(propertyName))) //$NON-NLS-1$
			return WebPlugin.OK_STATUS;
		// check superclass
		if (propertyName.equals(SUPERCLASS)) 
			return validateSuperClassName(getStringProperty(propertyName));
		// Validate init params
		if (propertyName.equals(INIT_PARAM))
			return validateInitParamList((List) getProperty(propertyName));
        // Validate url pattern and servlet name mappings
        if (propertyName.equals(FILTER_MAPPINGS))
            return validateFilterMappingList((List) getProperty(FILTER_MAPPINGS));
		// Validate the filter name in DD
		if (propertyName.equals(DISPLAY_NAME))
			return validateDisplayName(getStringProperty(propertyName));
		
		// Otherwise defer to super to validate the property
		return super.validate(propertyName);
	}

	/**
	 * Subclasses may extend this method to provide their own validation of the specified java
	 * classname. This implementation will ensure the class name is not set to Servlet and then will
	 * forward on to the NewJavaClassDataModel to validate the class name as valid java. This method
	 * does not accept null as a parameter. It will not return null. 
	 * It will check if the super class extends the javax.servlet.Servlet interface also.
	 * 
	 * @param className
	 * @return IStatus is java classname valid?
	 */
	protected IStatus validateSuperClassName(String superclassName) {
		//If the servlet implements javax.servlet.Servlet, we do not need a super class
		if (FilterSupertypesValidator.isFilterSuperclass(model))
			return WTPCommonPlugin.OK_STATUS;
		
		// Check the super class as a java class
		IStatus status = null;
		if (superclassName.trim().length() > 0) {
			status = super.validate(SUPERCLASS);
			if (status.getSeverity() == IStatus.ERROR)
				return status;
		}
				
		return status;
	}

	/**
	 * This method is intended for internal use only. It will be used to
	 * validate the init params list to ensure there are not any duplicates.
	 * This method will accept a null paramter. It will not return null.
	 * 
	 * @see NewFilterClassDataModelProvider#validate(String)
	 * 
	 * @param prop
	 * @return IStatus is init params list valid?
	 */
	private IStatus validateInitParamList(List prop) {
		if (prop != null && !prop.isEmpty()) {
			// Ensure there are not duplicate entries in the list
			boolean dup = hasDuplicatesInStringArrayList(prop);
			if (dup) {
				String msg = WebMessages.ERR_DUPLICATED_INIT_PARAMETER;
				return WebPlugin.createStatus(IStatus.ERROR, msg);
			}
		}
		// Return OK
		return WebPlugin.OK_STATUS;
	}

	/**
	 * This method is intended for internal use only. This will validate the
	 * filter mappings list and ensure there are not duplicate entries. It will
	 * accept a null parameter. It will not return null.
	 * 
	 * @see NewFilterClassDataModelProvider#validate(String)
	 * 
	 * @param prop
	 * @return IStatus is filter mapping list valid?
	 */
	private IStatus validateFilterMappingList(List prop) {
		if (prop != null && !prop.isEmpty()) {
			// Ensure there are not duplicates in the mapping list
			boolean dup = hasDuplicatesInFilterMappingItemList(prop);
			if (dup) {
				String msg = WebMessages.ERR_DUPLICATED_URL_MAPPING;
				return WebPlugin.createStatus(IStatus.ERROR, msg);
			}
			String isValidValue = validateValue(prop);
			if (isValidValue != null && isValidValue.length() > 0) {
				NLS.bind(WebMessages.ERR_URL_PATTERN_INVALID, isValidValue);
				String resourceString = WebMessages.getResourceString(WebMessages.ERR_URL_PATTERN_INVALID, new String[]{isValidValue});
				return WebPlugin.createStatus(IStatus.ERROR, resourceString);
			}
		} else {
			String msg = WebMessages.ERR_FILTER_MAPPING_EMPTY;
			return WebPlugin.createStatus(IStatus.ERROR, msg);
		}
		// Return OK
		return WebPlugin.OK_STATUS;
	}
	
	private boolean hasDuplicatesInFilterMappingItemList(List<IFilterMappingItem> input) {
		// If list is null or empty return false
		if (input == null) return false;
		int n = input.size();
		boolean dup = false;
		// nested for loops to check each element to see if other elements are the same
		for (int i = 0; i < n; i++) {
			IFilterMappingItem item = input.get(i);
	        for (int j = i + 1; j < n; j++) {
	            IFilterMappingItem item2 = input.get(j);
                if (item.getName().equals(item2.getName()) && 
                		item.getMappingType() == item2.getMappingType()) {
                    dup = true;
                    break;
                }
            }
            if (dup) break;
		}
		// Return boolean status for duplicates
		return dup;
	}

	/**
	 * This method is intended for internal use only. It provides a simple
	 * algorithm for detecting if there are invalid pattern's value in a list.
	 * It will accept a null parameter.
	 * 
	 * @see NewFilterClassDataModelProvider#validateFilterMappingList(List)
	 * 
	 * @param input
	 * @return String first invalid pattern's value
	 */
	private String validateValue(List prop) {
		if (prop == null) {
			return ""; //$NON-NLS-1$
		}
		int size = prop.size();
		for (int i = 0; i < size; i++) {
			IFilterMappingItem filterMappingValue = (IFilterMappingItem) prop.get(i);
			if (filterMappingValue.isUrlPatternType() && 
					!UrlPattern.isValid(filterMappingValue.getName()))
				return filterMappingValue.getName();
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * This method will return the list of filter interfaces to be implemented
	 * for the new filter java class. It will initialize the list using lazy
	 * initialization to the minimum interfaces required by the data model
	 * FILTER_INTERFACES. This method will not return null.
	 * 
	 * @see #FILTER_INTERFACES
	 * 
	 * @return List of filter interfaces to be implemented
	 */
	private List getFilterInterfaces() {
		if (interfaceList == null) {
			interfaceList = new ArrayList<String>(FILTER_INTERFACES.length);
			// Add minimum required list of filter interfaces to be implemented
			for (int i = 0; i < FILTER_INTERFACES.length; i++) {
				interfaceList.add(FILTER_INTERFACES[i]);
			}
			// Remove the javax.servlet.Filter interface from the list if the
			// superclass already implements it
			if (FilterSupertypesValidator.isFilterSuperclass(model)) {
				interfaceList.remove(IServletConstants.QUALIFIED_FILTER);
			}
		}
		// Return interface list
		return interfaceList;
	}

	/**
	 * This method will return the list of filter interfaces to be implemented
	 * for the new filter java class. It will initialize the list using lazy
	 * initialization to the minimum interfaces required by the data model
	 * FILTER_INTERFACES. This method will not return null.
	 * 
	 * @see #FILTER_INTERFACES
	 * 
	 * @return List of filter interfaces to be implemented
	 */
	private List<String> getJakartaFilterInterfaces() {
		if (jakartaInterfaceList == null) {
			jakartaInterfaceList = new ArrayList<String>(JAKARTA_FILTER_INTERFACES.length);
			// Add minimum required list of filter interfaces to be implemented
			for (int i = 0; i < JAKARTA_FILTER_INTERFACES.length; i++) {
				jakartaInterfaceList.add(JAKARTA_FILTER_INTERFACES[i]);
			}
			// Remove the jakarta.servlet.Filter interface from the list if the
			// superclass already implements it
			if (FilterSupertypesValidator.isFilterSuperclass(model)) {
				jakartaInterfaceList.remove(IServletConstants.QUALIFIED_JAKARTA_FILTER);
			}
		}
		// Return interface list
		return jakartaInterfaceList;
	}

	/**
	 * This method is intended for internal use only. This will validate whether
	 * the display name selected is a valid display name for the filter in the
	 * specified web application. It will make sure the name is not empty and
	 * that it doesn't already exist in the web app. This method will accept
	 * null as a parameter. It will not return null.
	 * 
	 * @see NewFilterClassDataModelProvider#validate(String)
	 * 
	 * @param prop
	 * @return IStatus is filter display name valid?
	 */
	private IStatus validateDisplayName(String prop) {
		// Ensure the filter display name is not null or empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = WebMessages.ERR_DISPLAY_NAME_EMPTY;
			return WebPlugin.createStatus(IStatus.ERROR, msg);
		}
		if (getTargetProject() == null || getTargetComponent() == null)
			return WebPlugin.OK_STATUS;
		
		IModelProvider provider = ModelProviderManager.getModelProvider(getTargetProject());
		Object mObj = provider.getModelObject();
		if (mObj instanceof org.eclipse.jst.j2ee.webapplication.WebApp) {
			org.eclipse.jst.j2ee.webapplication.WebApp webApp = (org.eclipse.jst.j2ee.webapplication.WebApp) mObj;

			List filters = webApp.getFilters();
			boolean exists = false;
			// Ensure the display does not already exist in the web application
			if (filters != null && !filters.isEmpty()) {
				for (int i = 0; i < filters.size(); i++) {
					String name = ((org.eclipse.jst.j2ee.webapplication.Filter) filters.get(i)).getName();
					if (prop.equals(name))
						exists = true;
				}
			}
			// If the filter name already exists, throw an error
			if (exists) {
				String msg = WebMessages.getResourceString(WebMessages.ERR_FILTER_NAME_EXIST, new String[]{prop});
				return WebPlugin.createStatus(IStatus.ERROR, msg);
			}			
		} else if (mObj instanceof org.eclipse.jst.javaee.web.WebApp) {
			org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) mObj;

			List filters = webApp.getFilters();
			boolean exists = false;
			// Ensure the display does not already exist in the web application
			if (filters != null && !filters.isEmpty()) {
				for (int i = 0; i < filters.size(); i++) {
					String name = ((org.eclipse.jst.javaee.web.Filter) filters.get(i)).getFilterName();
					if (prop.equals(name))
						exists = true;
				}
			}
			// If the filter name already exists, throw an error
			if (exists) {
				String msg = WebMessages.getResourceString(WebMessages.ERR_FILTER_NAME_EXIST, new String[] {prop});
				return WebPlugin.createStatus(IStatus.ERROR, msg);
			}			
		}
		
		// Otherwise, return OK
		return WebPlugin.OK_STATUS;
	}

	@Override
	public boolean propertySet(String propertyName, Object propertyValue) {
		if (DISPLAY_NAME.equals(propertyName) || CLASS_NAME.equals(propertyName)) {
			model.notifyPropertyChange(FILTER_MAPPINGS, IDataModel.DEFAULT_CHG);
		} 
		
		boolean result = false;		
		if (SUPERCLASS.equals(propertyName)) {
			model.notifyPropertyChange(ABSTRACT_METHODS, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(INIT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(DESTROY, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(DO_FILTER, IDataModel.ENABLE_CHG);
			
			if (!hasSuperClass()) {
				model.setProperty(ABSTRACT_METHODS, null);
				model.setProperty(INIT, null);
				model.setProperty(DESTROY, null);
				model.setProperty(DO_FILTER, null);
			}
			
			model.notifyPropertyChange(ABSTRACT_METHODS, IDataModel.DEFAULT_CHG);
			model.notifyPropertyChange(INIT, IDataModel.DEFAULT_CHG);
			model.notifyPropertyChange(DESTROY, IDataModel.DEFAULT_CHG);
			model.notifyPropertyChange(DO_FILTER, IDataModel.DEFAULT_CHG);
			
			if (!FilterSupertypesValidator.isFilterSuperclass(model)) {
				List ifaces = (List) model.getProperty(INTERFACES);
				if (projectUsesJakartaPackages()) {
					ifaces.add(QUALIFIED_JAKARTA_FILTER);
				} else {
					ifaces.add(QUALIFIED_FILTER);
				}
			}
		}
		
		return result || super.propertySet(propertyName, propertyValue);
	}

}
