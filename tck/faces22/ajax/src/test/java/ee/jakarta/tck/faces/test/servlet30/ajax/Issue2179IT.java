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
import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.PartialViewContext;

public class Issue2179IT extends ITBaseHTMLUnitOnly {
    
    /**
     * @see PartialViewContext
     * @see ExceptionHandler
     * @see https://github.com/eclipse-ee4j/mojarra/issues/2183
     */
    @Test
    public void testEncodeException() throws Exception {
        boolean exceptionThrown = false;
        try {
            HtmlPage page = getPage("issue2179-page1.xhtml");
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    /**
     * @see PartialViewContext
     * @see ExceptionHandler
     * @see https://github.com/eclipse-ee4j/mojarra/issues/2183
     */
    @Test
    public void testDecodeException() throws Exception {
        HtmlPage page = getPage("issue2179-page2.xhtml");
        HtmlSubmitInput button = (HtmlSubmitInput)page.getElementById("form:submit");
        page = button.click();
        webClient.waitForBackgroundJavaScript(3000);
        assertTrue(page.asXml().contains("Name: form:submit Error: serverError"));
    }
}
