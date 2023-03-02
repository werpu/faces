/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package ee.jakarta.tck.faces.test.servlet30.ajax; 

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import ee.jakarta.tck.faces.test.util.htmlunit.ITBaseHTMLUnitOnly;
import jakarta.faces.component.behavior.AjaxBehavior;

public class Issue2674IT extends ITBaseHTMLUnitOnly {

    /**
     * @see AjaxBehavior
     * @see https://github.com/eclipse-ee4j/mojarra/issues/2678
     */
    @Test
    public void testProgrammaticAjaxBehavior() throws Exception {
        HtmlPage page = getPage("issue2674.xhtml");
        HtmlTextInput input1 = (HtmlTextInput) page.getElementById("form:input1");
        assertTrue("input1 has programmatically added onfocus attribute", !input1.getAttribute("onfocus").isEmpty());
    }
}

