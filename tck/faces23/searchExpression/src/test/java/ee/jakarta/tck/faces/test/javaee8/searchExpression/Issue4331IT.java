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

package ee.jakarta.tck.faces.test.javaee8.searchExpression;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import ee.jakarta.tck.faces.test.util.htmlunit.ITBaseHTMLUnitOnly;
import ee.jakarta.tck.faces.test.util.selenium.BaseArquilianRunner;
import jakarta.faces.application.Application;
import jakarta.faces.component.search.SearchKeywordResolver;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.net.URL;

import static java.lang.System.getProperty;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

public class Issue4331IT extends ITBaseHTMLUnitOnly {
    @Before
    public void setUp() {
        webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.setJavaScriptTimeout(120000);
    }

    /**
     * @see Application#addSearchKeywordResolver(SearchKeywordResolver)
     * @see https://github.com/eclipse-ee4j/mojarra/issues/4331
     */
    @Test
    public void test() throws Exception {
        webClient.setIncorrectnessListener((ignore, nothing) -> {});

        testCustomSearchKeywordResolverAddedViaFacesConfig();
    }

    public void testCustomSearchKeywordResolverAddedViaFacesConfig() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "issue4331.xhtml");
        webClient.waitForBackgroundJavaScript(60000);

        HtmlTextInput input = (HtmlTextInput) page.getHtmlElementById("input");
        Assert.assertFalse(input.getAttribute("onchange").contains("@custom"));
        Assert.assertTrue(input.getAttribute("onchange").contains("input"));
    }



}