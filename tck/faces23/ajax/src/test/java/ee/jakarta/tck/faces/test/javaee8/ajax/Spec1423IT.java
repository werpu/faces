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

import static java.lang.System.getProperty;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import ee.jakarta.tck.faces.test.util.selenium.BaseArquillianRunner;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import ee.jakarta.tck.faces.test.util.htmlunit.IgnoringIncorrectnessListener;
import jakarta.faces.component.UIViewRoot;

@RunWith(BaseArquillianRunner.class)
public class Spec1423IT {

    @ArquillianResource
    private URL webUrl;
    private WebClient webClient;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return create(ZipImporter.class, getProperty("finalName") + ".war")
                .importFrom(new File("target/" + getProperty("finalName") + ".war"))
                .as(WebArchive.class);
    }

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

    @After
    public void tearDown() {
        webClient.close();
    }

}
