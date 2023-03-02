/*
 * Copyright (c) 2022, 2022 Contributors to Eclipse Foundation. All rights reserved.
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
package ee.jakarta.tck.faces.test.javaee8.websocket;

import static ee.jakarta.tck.faces.test.javaee8.websocket.Spec1396IT.waitUntilWebsocketIsOpened;
import static ee.jakarta.tck.faces.test.javaee8.websocket.Spec1396IT.waitUntilWebsocketIsPushed;
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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import jakarta.faces.component.UIWebsocket;

public class Issue4332IT extends ITBaseAll {

    @Before
    public void setUp() {
        webClient = new WebClient();
    }

    /**
     * @see UIWebsocket
     * @see https://github.com/eclipse-ee4j/mojarra/issues/4332
     */
    @Test
    @Ignore("Unstable, fails often")
    public void testWebsocketAfterPostback() throws Exception {
        webClient.setIncorrectnessListener((o, i) -> {}); // Suppress false JS errors on websocket URL.
        HtmlPage page = webClient.getPage(webUrl + "issue4332.xhtml");

        String pageSource = page.getWebResponse().getContentAsString();
        assertTrue(pageSource.contains("/jakarta.faces.push/push?"));

        waitUntilWebsocketIsOpened(page);

        HtmlSubmitInput postback = (HtmlSubmitInput) page.getHtmlElementById("form:postback");
        page = postback.click();

        pageSource = page.getWebResponse().getContentAsString();
        assertTrue(pageSource.contains("/jakarta.faces.push/push?"));

        waitUntilWebsocketIsOpened(page);

        HtmlSubmitInput button = (HtmlSubmitInput) page.getHtmlElementById("form:button");
        page = button.click();

        waitUntilWebsocketIsPushed(page);
        webClient.close(); // This will explicitly close websocket as well. HtmlUnit doesn't seem to like to leave it open before loading next page.
    }



}