/**
 * <copyright>
 * </copyright>
 *
 * $Id: ApplicationDeploymentDescriptor.java,v 1.1 2007/05/16 06:42:34 cbridgha Exp $
 */
package org.eclipse.jst.javaee.application;

import java.util.Map;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getApplication <em>Application</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplicationDeploymentDescriptor()
 * @extends JavaEEObject
 * @generated
 */
public interface ApplicationDeploymentDescriptor extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplicationDeploymentDescriptor_Mixed()
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplicationDeploymentDescriptor_XMLNSPrefixMap()
	 * @generated
	 */
	Map getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplicationDeploymentDescriptor_XSISchemaLocation()
	 * @generated
	 */
	Map getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Application</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	The application element is the root element of a Java EE
	 * 	application deployment descriptor.
	 * 
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Application</em>' containment reference.
	 * @see #setApplication(Application)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplicationDeploymentDescriptor_Application()
	 * @generated
	 */
	Application getApplication();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getApplication <em>Application</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Application</em>' containment reference.
	 * @see #getApplication()
	 * @generated
	 */
	void setApplication(Application value);

} // ApplicationDeploymentDescriptor