/*
 * Copyright (c) 2021 Contributors to the Eclipse Foundation.
 * Copyright (c) 1997, 2021 Oracle and/or its affiliates. All rights reserved.
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

package ee.jakarta.tck.faces.test.javaee8.ajax;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import ee.jakarta.tck.faces.test.util.htmlunit.ITBaseHTMLUnitOnly;
import ee.jakarta.tck.faces.test.util.htmlunit.IgnoringIncorrectnessListener;
import jakarta.faces.context.PartialViewContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class Spec1412IT extends ITBaseHTMLUnitOnly {

    @Before
    public void setUp() {
        webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.setJavaScriptTimeout(120000);
    }

    /**
     * @see PartialViewContext#getEvalScripts()
     * @see https://github.com/jakartaee/faces/issues/1412
     */
    @Test
    public void testSpec1412() throws Exception {
        webClient.setIncorrectnessListener(new IgnoringIncorrectnessListener());

        HtmlPage page = webClient.getPage(webUrl + "spec1412.xhtml");
        assertTrue(!page.asNormalizedText().contains("Success!"));

        HtmlSubmitInput button = (HtmlSubmitInput) page.getHtmlElementById("form:button");
        assertTrue(button.asNormalizedText().equals("foo"));

        page = button.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.asNormalizedText().contains("Success!"));

        button = (HtmlSubmitInput) page.getHtmlElementById("form:button");
        assertTrue(button.asNormalizedText().equals("bar"));
    }


}
