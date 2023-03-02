/*
 * Copyright (c) 2021 Contributors to the Eclipse Foundation.
 * Copyright (c) 1997, 2020 Oracle and/or its affiliates. All rights reserved.
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

package ee.jakarta.tck.faces.test.javaee6.viewParamNullValueAjax;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import ee.jakarta.tck.faces.test.util.htmlunit.ITBaseHTMLUnitOnly;
import jakarta.faces.component.UIViewParameter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class Issue4550IT extends ITBaseHTMLUnitOnly {

    private static String TEST_STRING = "Test Rhuan";

    @Before
    public void setUp() {
        webClient = new WebClient();
    }

    /**
     * @see UIViewParameter
     * @see https://github.com/eclipse-ee4j/mojarra/issues/4550
     */
    @Test
    public void testViewParamNullValueAjax() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/viewparam-nullvalue-ajax.xhtml");

        // Ajax submit click
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getHtmlElementById("form:ajaxCommandButton");
        assertNotNull(submit);
        page = (HtmlPage) submit.click();
        String pageAsText = page.asNormalizedText();
        assertTrue(pageAsText.contains(TEST_STRING));

        // Ajax submit click
        submit = (HtmlSubmitInput) page.getHtmlElementById("form:ajaxCommandButton");
        assertNotNull(submit);
        page = (HtmlPage) submit.click();
        pageAsText = page.asNormalizedText();
        assertTrue(pageAsText.contains(TEST_STRING));

        // Non Ajax submit click
        submit = (HtmlSubmitInput) page.getHtmlElementById("form:commandButton");
        assertNotNull(submit);
        page = (HtmlPage) submit.click();
        pageAsText = page.asNormalizedText();
        assertTrue(pageAsText.contains(TEST_STRING));
    }

}
