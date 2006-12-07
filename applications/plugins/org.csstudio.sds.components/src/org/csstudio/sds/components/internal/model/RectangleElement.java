package org.csstudio.sds.components.internal.model;

import org.csstudio.sds.model.DataTypeEnum;
import org.csstudio.sds.model.DisplayModelElement;

/**
 * This class defines an rectangle model element.
 * 
 * @author Sven Wende & Alexander Will
 * @version $Revision$
 * 
 */
public final class RectangleElement extends DisplayModelElement {
	/**
	 * The ID of the fill grade property.
	 */
	public static final String PROP_FILL_PERCENTAGE = "rectangle.fillpercentage";

	/**
	 * The ID of this model element.
	 */
	public static final String ID = "element.rectangle";

	/**
	 * The default value of the height property.
	 */
	private static final int DEFAULT_HEIGHT = 10;

	/**
	 * The default value of the width property.
	 */
	private static final int DEFAULT_WIDTH = 20;

	/**
	 * The default value of the fill grade property.
	 */
	private static final double DEFAULT_FILL_GRADE = 100.0;

	/**
	 * Standard constructor.
	 */
	public RectangleElement() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeID() {
		return ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configureProperties() {
		addProperty(PROP_FILL_PERCENTAGE, "fill percentage",
				DataTypeEnum.DOUBLE, DEFAULT_FILL_GRADE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDoubleTestProperty() {
		return PROP_FILL_PERCENTAGE;
	}
}
