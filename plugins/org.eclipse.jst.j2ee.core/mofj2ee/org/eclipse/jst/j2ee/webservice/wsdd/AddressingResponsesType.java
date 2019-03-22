/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.wsdd;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Addressing Responses Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getAddressingResponsesType()
 * @model
 * @generated
 */
public final class AddressingResponsesType extends AbstractEnumerator {
	/**
	 * The '<em><b>ANONYMOUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ANONYMOUS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ANONYMOUS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ANONYMOUS = 0;

	/**
	 * The '<em><b>NONANONYMOUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONANONYMOUS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONANONYMOUS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONANONYMOUS = 1;

	/**
	 * The '<em><b>ALL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALL = 2;

	/**
	 * The '<em><b>ANONYMOUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ANONYMOUS
	 * @generated
	 * @ordered
	 */
	public static final AddressingResponsesType ANONYMOUS_LITERAL = new AddressingResponsesType(ANONYMOUS, "ANONYMOUS", "ANONYMOUS"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>NONANONYMOUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONANONYMOUS
	 * @generated
	 * @ordered
	 */
	public static final AddressingResponsesType NONANONYMOUS_LITERAL = new AddressingResponsesType(NONANONYMOUS, "NONANONYMOUS", "NONANONYMOUS"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>ALL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALL
	 * @generated
	 * @ordered
	 */
	public static final AddressingResponsesType ALL_LITERAL = new AddressingResponsesType(ALL, "ALL", "ALL"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Addressing Responses Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AddressingResponsesType[] VALUES_ARRAY =
		new AddressingResponsesType[] {
			ANONYMOUS_LITERAL,
			NONANONYMOUS_LITERAL,
			ALL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Addressing Responses Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Addressing Responses Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AddressingResponsesType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AddressingResponsesType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Addressing Responses Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AddressingResponsesType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AddressingResponsesType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Addressing Responses Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AddressingResponsesType get(int value) {
		switch (value) {
			case ANONYMOUS: return ANONYMOUS_LITERAL;
			case NONANONYMOUS: return NONANONYMOUS_LITERAL;
			case ALL: return ALL_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private AddressingResponsesType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //AddressingResponsesType
