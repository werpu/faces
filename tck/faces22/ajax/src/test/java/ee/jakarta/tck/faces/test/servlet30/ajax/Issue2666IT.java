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
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import ee.jakarta.tck.faces.test.util.htmlunit.ITBaseHTMLUnitOnly;
import jakarta.faces.component.behavior.AjaxBehavior;

public class Issue2666IT extends ITBaseHTMLUnitOnly {

    /**
     * @see AjaxBehavior
     * @see https://github.com/eclipse-ee4j/mojarra/issues/2670
     */
    @Test
    public void testRequestParamWithNoNameDoesNotExist() throws Exception {
        HtmlPage page = getPage("issue2666.xhtml");
        HtmlSubmitInput button = (HtmlSubmitInput) page.getElementById("form:submit");
        page = button.click();
        webClient.waitForBackgroundJavaScript(3000);

        // Assert the page does not display the request parameter name 'button' that 
        // is in the page without a 'name' attribute.
        assertTrue(page.asNormalizedText().contains("Request parameter name 'button' does not exist"));
    }
}
