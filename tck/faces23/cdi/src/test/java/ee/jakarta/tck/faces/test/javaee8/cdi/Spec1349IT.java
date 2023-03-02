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

package ee.jakarta.tck.faces.test.javaee8.cdi;

import static java.lang.System.getProperty;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import ee.jakarta.tck.faces.test.util.arquillian.ITBaseAll;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

public class Spec1349IT extends ITBaseAll {

    @Before
    public void setUp() {
        webClient = new WebClient();
    }

    /**
     * @see Inject
     * @see FacesConverter
     * @see https://github.com/jakartaee/faces/issues/1349
     */
    @Test
    public void testInjectConverter() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/injectConverter.xhtml");
        assertTrue(page.getElementById("messages").getTextContent().contains("InjectConverter#getAsString() was called"));
        HtmlElement submit = page.getHtmlElementById("form:submit");
        page = submit.click();
        assertTrue(page.getElementById("messages").getTextContent().contains("InjectConverter#getAsObject() was called"));
    }

    /**
     * @see Inject
     * @see FacesConverter
     * @see https://github.com/jakartaee/faces/issues/1349
     */
    @Test
    public void testInjectConverter2() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/injectConverter2.xhtml");
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        HtmlElement submit = page.getHtmlElementById("form:submit");
        page = submit.click();
        assertTrue(page.asXml().contains("InjectConverter2 was called"));
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
    }

    /**
     * @see Inject
     * @see FacesConverter
     * @see https://github.com/jakartaee/faces/issues/1349
     */
    @Test
    public void testInjectConverter3() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/injectConverter3.xhtml");
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        HtmlElement submit = page.getHtmlElementById("form:submit");
        page = submit.click();
        assertTrue(page.asXml().contains("InjectConverter3 was called"));
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
    }
}
