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

import ee.jakarta.tck.faces.test.util.htmlunit.ITBaseHTMLUnitOnly;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import jakarta.faces.component.behavior.AjaxBehavior;

public class Issue2574IT extends ITBaseHTMLUnitOnly {

    /**
     * @see AjaxBehavior
     * @see https://github.com/eclipse-ee4j/mojarra/issues/2578
     */
    @Test
    public void testOtherScriptType() throws Exception {
        HtmlPage page = getPage("issue2574.xhtml");
        HtmlSubmitInput button1 = (HtmlSubmitInput)page.getElementById("form:refresh");
        page = button1.click();
        webClient.waitForBackgroundJavaScript(3000);
        assertTrue(page.asXml().contains("This script must not be eval'ed on ajax response."));
    }
}
