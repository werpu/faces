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
import jakarta.faces.component.UIViewRoot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Spec1423IT extends ITBaseHTMLUnitOnly {

    @Before
    public void setUp() {
        webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.setJavaScriptTimeout(120000);
        webClient.setIncorrectnessListener(new IgnoringIncorrectnessListener());
    }

    /**
     * @see UIViewRoot#addComponentResource(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, String)
     * @see https://github.com/jakartaee/faces/issues/1423
     */
    @Test
    public void testSpec1423Basic() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "spec1423.xhtml");
        HtmlSubmitInput button;

        assertTrue(page.getHtmlElementById("scriptResult").asNormalizedText().isEmpty());
        assertTrue(page.getHtmlElementById("stylesheetResult").asNormalizedText().isEmpty());

        button = (HtmlSubmitInput) page.getHtmlElementById("form1:addViaHead");
        page = button.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.getHtmlElementById("scriptResult").asNormalizedText().equals("addedViaHead"));
        assertTrue(page.getHtmlElementById("stylesheetResult").asNormalizedText().isEmpty());
    }

    @Test
    public void testSpec1423() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "spec1423.xhtml");
        HtmlSubmitInput button;

        assertTrue(page.getHtmlElementById("scriptResult").asNormalizedText().isEmpty());
        assertTrue(page.getHtmlElementById("stylesheetResult").asNormalizedText().isEmpty());

        button = (HtmlSubmitInput) page.getHtmlElementById("form1:addViaHead");
        page = button.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.getHtmlElementById("scriptResult").asNormalizedText().equals("addedViaHead"));
        assertTrue(page.getHtmlElementById("stylesheetResult").asNormalizedText().isEmpty());

        button = (HtmlSubmitInput) page.getHtmlElementById("form2:addViaInclude");
        page = button.click();
        webClient.waitForBackgroundJavaScript(60000);

        assertTrue(page.getHtmlElementById("scriptResult").asNormalizedText().equals("addedViaInclude"));
        assertTrue(page.getHtmlElementById("stylesheetResult").asNormalizedText().equals("rgb(255, 0, 0)"));

        button = (HtmlSubmitInput) page.getHtmlElementById("form1:addViaBody");
        page = button.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.getHtmlElementById("scriptResult").asNormalizedText().equals("addedViaBody"));
        assertTrue(page.getHtmlElementById("stylesheetResult").asNormalizedText().equals("rgb(255, 0, 0)"));

        button = (HtmlSubmitInput) page.getHtmlElementById("form2:addViaInclude");
        page = button.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.getHtmlElementById("scriptResult").asNormalizedText().equals("addedViaBody"));
        assertTrue(page.getHtmlElementById("stylesheetResult").asNormalizedText().equals("rgb(255, 0, 0)"));

        button = (HtmlSubmitInput) page.getHtmlElementById("form1:addProgrammatically");
        page = button.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.getHtmlElementById("scriptResult").asNormalizedText().equals("addedProgrammatically"));
        assertTrue(page.getHtmlElementById("stylesheetResult").asNormalizedText().equals("rgb(0, 255, 0)"));

        button = (HtmlSubmitInput) page.getHtmlElementById("form1:addViaHead");
        page = button.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.getHtmlElementById("scriptResult").asNormalizedText().equals("addedProgrammatically"));
        assertTrue(page.getHtmlElementById("stylesheetResult").asNormalizedText().equals("rgb(0, 255, 0)"));

        button = (HtmlSubmitInput) page.getHtmlElementById("form1:addViaBody");
        page = button.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.getHtmlElementById("scriptResult").asNormalizedText().equals("addedProgrammatically"));
        assertTrue(page.getHtmlElementById("stylesheetResult").asNormalizedText().equals("rgb(0, 255, 0)"));

        button = (HtmlSubmitInput) page.getHtmlElementById("form2:addViaInclude");
        page = button.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.getHtmlElementById("scriptResult").asNormalizedText().equals("addedProgrammatically"));
        assertTrue(page.getHtmlElementById("stylesheetResult").asNormalizedText().equals("rgb(0, 255, 0)"));
    }



}
