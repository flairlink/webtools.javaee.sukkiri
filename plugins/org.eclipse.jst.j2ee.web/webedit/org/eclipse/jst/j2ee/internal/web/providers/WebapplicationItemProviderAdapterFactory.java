/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.providers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.jst.j2ee.common.internal.provider.ListenerItemProvider;
import org.eclipse.jst.j2ee.webapplication.internal.util.WebapplicationAdapterFactory;


/**
 * This is the factory that is used to provide the interfaces needed to support
 * { @link org.eclipse.jface.viewer.ContentViewer}s. The adapters generated by this factory convert
 * MOF adapter notificiations into {@link org.eclipse.jface.DomainEvent}s. The adapters also
 * support property sheets, see {@link com.ibm.itp.ui.api.propertysheet}.
 */
public class WebapplicationItemProviderAdapterFactory extends WebapplicationAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {

	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 */
	protected ComposedAdapterFactory parentAdapterFactory;
	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by
	 * { @link #isFactoryForType isFactoryForType}.
	 */
	protected Collection supportedTypes = new ArrayList();
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.jst.j2ee.internal.internal.webapplication.WebApp}
	 * instances.
	 */
	protected WebAppItemProvider webAppItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.ContextParam}instances.
	 */
	protected ContextParamItemProvider contextParamItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.ErrorPage}instances.
	 */
	protected ErrorPageItemProvider errorPageItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.ExceptionTypeErrorPage}instances.
	 */
	protected ExceptionTypeErrorPageItemProvider exceptionTypeErrorPageItemProvider;
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.jst.j2ee.internal.internal.webapplication.Filter}
	 * instances.
	 */
	protected FilterItemProvider filterItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.FilterMapping}instances.
	 */
	protected FilterMappingItemProvider filterMappingItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.ErrorCodeErrorPage}instances.
	 */
	protected ErrorCodeErrorPageItemProvider errorCodeErrorPageItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.WelcomeFileList}instances.
	 */
	protected WelcomeFileListItemProvider welcomeFileListItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.WelcomeFile}instances.
	 */
	protected WelcomeFileItemProvider welcomeFileItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.TagLibRef}instances.
	 */
	protected TagLibRefItemProvider tagLibRefItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.TagLibRefType}instances.
	 */
	protected TagLibRefTypeItemProvider tagLibRefTypeItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.SecurityConstraint}instances.
	 */
	protected SecurityConstraintItemProvider securityConstraintItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.WebResourceCollection}instances.
	 */
	protected WebResourceCollectionItemProvider webResourceCollectionItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.URLPatternType}instances.
	 */
	protected URLPatternTypeItemProvider uRLPatternTypeItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.HTTPMethodType}instances.
	 */
	protected HTTPMethodTypeItemProvider hTTPMethodTypeItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.AuthConstraint}instances.
	 */
	protected AuthConstraintItemProvider authConstraintItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.UserDataConstraint}instances.
	 */
	protected UserDataConstraintItemProvider userDataConstraintItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.LoginConfig}instances.
	 */
	protected LoginConfigItemProvider loginConfigItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.FormLoginConfig}instances.
	 */
	protected FormLoginConfigItemProvider formLoginConfigItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.MimeMapping}instances.
	 */
	protected MimeMappingItemProvider mimeMappingItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.SessionConfig}instances.
	 */
	protected SessionConfigItemProvider sessionConfigItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.ServletMapping}instances.
	 */
	protected ServletMappingItemProvider servletMappingItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.Servlet}instances.
	 */
	protected ServletItemProvider servletItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.WebType}instances.
	 */
	protected WebTypeItemProvider webTypeItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.ServletType}instances.
	 */
	protected ServletTypeItemProvider servletTypeItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.JSPType}instances.
	 */
	protected JSPTypeItemProvider jSPTypeItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.InitParam}instances.
	 */
	protected InitParamItemProvider initParamItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.Listener}instances.
	 */
	protected ListenerItemProvider listenerItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.RoleNameType}instances.
	 */
	protected RoleNameTypeItemProvider roleNameTypeItemProvider;
	protected Disposable disposable = new Disposable();

	/**
	 * This constructs an instance from a domain notifier.
	 */
	public WebapplicationItemProviderAdapterFactory() {
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemPropertySource.class);
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(ITableItemLabelProvider.class);
	}

	@Override
	public Adapter adapt(Notifier target, Object adapterKey) {
		return super.adapt(target, this);
	}

	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class) || (((Class) type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	@Override
	public Adapter adaptNew(Notifier target, Object adapterType) {
		Adapter adapter = super.adaptNew(target, adapterType);
		disposable.add(adapter);
		return adapter;
	}

	/**
	 * This adds a listener. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.AuthConstraint}.
	 */
	@Override
	public Adapter createAuthConstraintAdapter() {
		if (authConstraintItemProvider == null) {
			authConstraintItemProvider = new AuthConstraintItemProvider(this);
		}

		return authConstraintItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.ContextParam}.
	 */
	@Override
	public Adapter createContextParamAdapter() {
		if (contextParamItemProvider == null) {
			contextParamItemProvider = new ContextParamItemProvider(this);
		}

		return contextParamItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.ErrorCodeErrorPage}.
	 */
	@Override
	public Adapter createErrorCodeErrorPageAdapter() {
		if (errorCodeErrorPageItemProvider == null) {
			errorCodeErrorPageItemProvider = new ErrorCodeErrorPageItemProvider(this);
		}

		return errorCodeErrorPageItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.ErrorPage}.
	 */
	@Override
	public Adapter createErrorPageAdapter() {
		if (errorPageItemProvider == null) {
			errorPageItemProvider = new ErrorPageItemProvider(this);
		}

		return errorPageItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.ExceptionTypeErrorPage}.
	 */
	@Override
	public Adapter createExceptionTypeErrorPageAdapter() {
		if (exceptionTypeErrorPageItemProvider == null) {
			exceptionTypeErrorPageItemProvider = new ExceptionTypeErrorPageItemProvider(this);
		}

		return exceptionTypeErrorPageItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.Filter}.
	 */
	@Override
	public Adapter createFilterAdapter() {
		if (filterItemProvider == null) {
			filterItemProvider = new FilterItemProvider(this);
		}

		return filterItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.LocalEncodingMappingList}instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected LocalEncodingMappingListItemProvider localEncodingMappingListItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.LocalEncodingMappingList}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createLocalEncodingMappingListAdapter() {
		if (localEncodingMappingListItemProvider == null) {
			localEncodingMappingListItemProvider = new LocalEncodingMappingListItemProvider(this);
		}

		return localEncodingMappingListItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.LocalEncodingMapping}instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected LocalEncodingMappingItemProvider localEncodingMappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.LocalEncodingMapping}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createLocalEncodingMappingAdapter() {
		if (localEncodingMappingItemProvider == null) {
			localEncodingMappingItemProvider = new LocalEncodingMappingItemProvider(this);
		}

		return localEncodingMappingItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.FilterMapping}.
	 */
	@Override
	public Adapter createFilterMappingAdapter() {
		if (filterMappingItemProvider == null) {
			filterMappingItemProvider = new FilterMappingItemProvider(this);
		}

		return filterMappingItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.FormLoginConfig}.
	 */
	@Override
	public Adapter createFormLoginConfigAdapter() {
		if (formLoginConfigItemProvider == null) {
			formLoginConfigItemProvider = new FormLoginConfigItemProvider(this);
		}

		return formLoginConfigItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.HTTPMethodType}.
	 */
	@Override
	public Adapter createHTTPMethodTypeAdapter() {
		if (hTTPMethodTypeItemProvider == null) {
			hTTPMethodTypeItemProvider = new HTTPMethodTypeItemProvider(this);
		}

		return hTTPMethodTypeItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.InitParam}.
	 */
	@Override
	public Adapter createInitParamAdapter() {
		if (initParamItemProvider == null) {
			initParamItemProvider = new InitParamItemProvider(this);
		}

		return initParamItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.Listener}.
	 */
	@Override
	public Adapter createListenerAdapter() {
		if (listenerItemProvider == null) {
			listenerItemProvider = new ListenerItemProvider(this);
		}

		return listenerItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.JSPType}.
	 */
	@Override
	public Adapter createJSPTypeAdapter() {
		if (jSPTypeItemProvider == null) {
			jSPTypeItemProvider = new JSPTypeItemProvider(this);
		}

		return jSPTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.URLPatternType}instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected URLPatternTypeItemProvider urlPatternTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.LoginConfig}.
	 */
	@Override
	public Adapter createLoginConfigAdapter() {
		if (loginConfigItemProvider == null) {
			loginConfigItemProvider = new LoginConfigItemProvider(this);
		}

		return loginConfigItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.MimeMapping}.
	 */
	@Override
	public Adapter createMimeMappingAdapter() {
		if (mimeMappingItemProvider == null) {
			mimeMappingItemProvider = new MimeMappingItemProvider(this);
		}

		return mimeMappingItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.RoleNameType}.
	 */
	@Override
	public Adapter createRoleNameTypeAdapter() {
		if (roleNameTypeItemProvider == null) {
			roleNameTypeItemProvider = new RoleNameTypeItemProvider(this);
		}

		return roleNameTypeItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.SecurityConstraint}.
	 */
	@Override
	public Adapter createSecurityConstraintAdapter() {
		if (securityConstraintItemProvider == null) {
			securityConstraintItemProvider = new SecurityConstraintItemProvider(this);
		}

		return securityConstraintItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.Servlet}.
	 */
	@Override
	public Adapter createServletAdapter() {
		if (servletItemProvider == null) {
			servletItemProvider = new ServletItemProvider(this);
		}

		return servletItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.ServletMapping}.
	 */
	@Override
	public Adapter createServletMappingAdapter() {
		if (servletMappingItemProvider == null) {
			servletMappingItemProvider = new ServletMappingItemProvider(this);
		}

		return servletMappingItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.ServletType}.
	 */
	@Override
	public Adapter createServletTypeAdapter() {
		if (servletTypeItemProvider == null) {
			servletTypeItemProvider = new ServletTypeItemProvider(this);
		}

		return servletTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.JSPType}instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected JSPTypeItemProvider jspTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.SessionConfig}.
	 */
	@Override
	public Adapter createSessionConfigAdapter() {
		if (sessionConfigItemProvider == null) {
			sessionConfigItemProvider = new SessionConfigItemProvider(this);
		}

		return sessionConfigItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.TagLibRef}.
	 */
	@Override
	public Adapter createTagLibRefAdapter() {
		if (tagLibRefItemProvider == null) {
			tagLibRefItemProvider = new TagLibRefItemProvider(this);
		}

		return tagLibRefItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.TagLibRef}.
	 */
	@Override
	public Adapter createTagLibRefTypeAdapter() {
		if (tagLibRefTypeItemProvider == null) {
			tagLibRefTypeItemProvider = new TagLibRefTypeItemProvider(this);
		}

		return tagLibRefTypeItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.URLPatternType}.
	 */
	@Override
	public Adapter createURLPatternTypeAdapter() {
		if (uRLPatternTypeItemProvider == null) {
			uRLPatternTypeItemProvider = new URLPatternTypeItemProvider(this);
		}

		return uRLPatternTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webapplication.HTTPMethodType}instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected HTTPMethodTypeItemProvider httpMethodTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.UserDataConstraint}.
	 */
	@Override
	public Adapter createUserDataConstraintAdapter() {
		if (userDataConstraintItemProvider == null) {
			userDataConstraintItemProvider = new UserDataConstraintItemProvider(this);
		}

		return userDataConstraintItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.WebApp}.
	 */
	@Override
	public Adapter createWebAppAdapter() {
		if (webAppItemProvider == null) {
			webAppItemProvider = new WebAppItemProvider(this);
		}

		return webAppItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.WebResourceCollection}.
	 */
	@Override
	public Adapter createWebResourceCollectionAdapter() {
		if (webResourceCollectionItemProvider == null) {
			webResourceCollectionItemProvider = new WebResourceCollectionItemProvider(this);
		}

		return webResourceCollectionItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.WebType}.
	 */
	@Override
	public Adapter createWebTypeAdapter() {
		if (webTypeItemProvider == null) {
			webTypeItemProvider = new WebTypeItemProvider(this);
		}

		return webTypeItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.WelcomeFile}.
	 */
	@Override
	public Adapter createWelcomeFileAdapter() {
		if (welcomeFileItemProvider == null) {
			welcomeFileItemProvider = new WelcomeFileItemProvider(this);
		}

		return welcomeFileItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.WelcomeFileList}.
	 */
	@Override
	public Adapter createWelcomeFileListAdapter() {
		if (welcomeFileListItemProvider == null) {
			welcomeFileListItemProvider = new WelcomeFileListItemProvider(this);
		}

		return welcomeFileListItemProvider;
	}

	public void dispose() {
		disposable.dispose();
	}

	/**
	 * This returns the root adapter factory that contains the factory.
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return (parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory());
	}

	@Override
	public boolean isFactoryForType(Object type) {
		return super.isFactoryForType(type) || supportedTypes.contains(type);
	}

	/**
	 * This removes a listener.
	 * 
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier}and to {@link #parentAdapterFactory}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This sets the composed adapter factory that contains the factory.
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}
}
