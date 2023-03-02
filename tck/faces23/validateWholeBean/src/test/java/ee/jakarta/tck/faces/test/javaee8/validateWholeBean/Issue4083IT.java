/*
 * Copyright (c) 2021, 2023 Contributors to the Eclipse Foundation.
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

package ee.jakarta.tck.faces.test.javaee8.validateWholeBean;

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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;


public class Issue4083IT extends ITBaseAll {

    @Before
    public void setUp() {
        webClient = new WebClient();
    }

    public Issue4083IT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * @see com.sun.faces.ext.component.UIValidateWholeBean
     * @see https://github.com/eclipse-ee4j/mojarra/issues/4087
     */
    @Test
    public void testValidateWholeBean() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/Issue4083.xhtml");

        HtmlPasswordInput password1 = (HtmlPasswordInput) page.getHtmlElementById("password1");
        password1.setValueAttribute("mike@!2016GO");

        HtmlPasswordInput password2 = (HtmlPasswordInput) page.getHtmlElementById("password2");
        password2.setValueAttribute("mike@!2015G0");

        HtmlSubmitInput submit = (HtmlSubmitInput) page.getHtmlElementById("submit");
        HtmlPage page1 = submit.click();

        assertTrue("Validation message not found!", page1.getElementById("err").getElementsByTagName("li").get(0).asNormalizedText().contains("Password fields must match"));
    }
}
