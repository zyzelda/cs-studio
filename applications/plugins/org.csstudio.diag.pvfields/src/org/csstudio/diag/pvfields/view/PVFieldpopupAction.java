/*******************************************************************************
 * Copyright (c) 2010 Oak Ridge National Laboratory.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.csstudio.diag.pvfields.view;

import org.csstudio.platform.model.IProcessVariable;
import org.csstudio.platform.ui.internal.dataexchange.ProcessVariablePopupAction;

/** Handle activation of Fields Table from the object contrib. context menu.
 *  @author Kay Kasemir
 *  @author Helge Rickens
 */
public class PVFieldpopupAction extends ProcessVariablePopupAction
{
    /** @see org.csstudio.data.exchange.ProcessVariablePopupAction#handlePVs(]) */
    @Override
    public void handlePVs(IProcessVariable[] pv_names)
    {
        if (pv_names.length < 1)
            return;
        PVFieldsView.activateWithPV(pv_names[0]);
    }
}
