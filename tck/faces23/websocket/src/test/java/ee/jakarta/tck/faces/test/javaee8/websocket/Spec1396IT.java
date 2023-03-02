/*
 * Copyright (c) 2021, 2022 Contributors to the Eclipse Foundation.
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

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import ee.jakarta.tck.faces.test.util.arquillian.ITBaseAll;
import jakarta.faces.component.UIWebsocket;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Spec1396IT extends ITBaseAll {


    @Before
    public void setUp() {
        webClient = new WebClient();
    }

    /**
     * @see UIWebsocket
     * @see https://github.com/jakartaee/faces/issues/1396
     */
    @Test
    public void testEnableWebsocketEndpoint() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "spec1396EnableWebsocketEndpoint.xhtml");
        assertTrue(page.getHtmlElementById("param").asNormalizedText().equals("true"));
    }

    /**
     * @see UIWebsocket
     * @see https://github.com/jakartaee/faces/issues/1396
     */
    @Test
    @Ignore("Unstable, fails often")
    public void testDefaultWebsocket() throws Exception {
        webClient.setIncorrectnessListener((o, i) -> {}); // Suppress false JS errors on websocket URL.
        HtmlPage page = webClient.getPage(webUrl + "spec1396DefaultWebsocket.xhtml");

        String pageSource = page.getWebResponse().getContentAsString();
        assertTrue(pageSource.contains(">faces.push.init("));
        assertTrue(pageSource.contains("/jakarta.faces.push/push?"));

        waitUntilWebsocketIsOpened(page);

        HtmlSubmitInput button = (HtmlSubmitInput) page.getHtmlElementById("form:button");
        page = button.click();

        waitUntilWebsocketIsPushed(page);
        webClient.close(); // This will explicitly close websocket as well. HtmlUnit doesn't seem to like to leave it open before loading next page.
    }

    /**
     * @see UIWebsocket#setUser(java.io.Serializable)
     * @see https://github.com/jakartaee/faces/issues/1396
     */
    @Test
    @Ignore("Unstable, fails often")
    public void testUserScopedWebsocket() throws Exception {
        webClient.setIncorrectnessListener((o, i) -> {}); // Suppress false JS errors on websocket URL.
        HtmlPage page = webClient.getPage(webUrl + "spec1396UserScopedWebsocket.xhtml");

        String pageSource = page.getWebResponse().getContentAsString();
        assertTrue(pageSource.contains(">faces.push.init("));
        assertTrue(pageSource.contains("/jakarta.faces.push/user?"));

        waitUntilWebsocketIsOpened(page);

        HtmlSubmitInput button = (HtmlSubmitInput) page.getHtmlElementById("form:button");
        page = button.click();

        waitUntilWebsocketIsPushed(page);
        webClient.close(); // This will explicitly close websocket as well. HtmlUnit doesn't seem to like to leave it open before loading next page.
    }

    /**
     * @see UIWebsocket#setScope(String)
     * @see https://github.com/jakartaee/faces/issues/1396
     */
    @Test
    @Ignore("Unstable, fails often")
    public void testViewScopedWebsocket() throws Exception {
        webClient.setIncorrectnessListener((o, i) -> {}); // Suppress false JS errors on websocket URL.
        HtmlPage page = webClient.getPage(webUrl + "spec1396ViewScopedWebsocket.xhtml");

        String pageSource = page.getWebResponse().getContentAsString();
        assertTrue(pageSource.contains(">faces.push.init("));
        assertTrue(pageSource.contains("/jakarta.faces.push/view?"));

        waitUntilWebsocketIsOpened(page);

        HtmlSubmitInput button = (HtmlSubmitInput) page.getHtmlElementById("form:button");
        page = button.click();

        waitUntilWebsocketIsPushed(page);
        webClient.close(); // This will explicitly close websocket as well. HtmlUnit doesn't seem to like to leave it open before loading next page.
    }

    /**
     * HtmlUnit is not capable of waiting until WS is opened. Hence this work around.
     */
    static void waitUntilWebsocketIsOpened(HtmlPage page) throws Exception {
        Predicate<HtmlPage> isWebsocketOpened = p -> "yes".equals(page.getElementById("opened").asNormalizedText());
        int retries = 10;

        while (!isWebsocketOpened.test(page) && retries --> 0) {
            Thread.sleep(300);
        }

        if (!isWebsocketOpened.test(page)) {
            fail("Failed to establish connection with websocket within 3 seconds.");
        }
    }

    /**
     * HtmlUnit is not capable of waiting until WS is pushed. Hence this work around.
     */
    static void waitUntilWebsocketIsPushed(HtmlPage page) throws Exception {
        Predicate<HtmlPage> isWebsocketPushed = p -> "yes".equals(page.getElementById("opened").asNormalizedText());
        int retries = 10;

        while (!isWebsocketPushed.test(page) && retries --> 0) {
            Thread.sleep(300);
        }

        if (!isWebsocketPushed.test(page)) {
            fail("Failed to retrieve push message from websocket within 3 seconds.");
        }
    }


}
