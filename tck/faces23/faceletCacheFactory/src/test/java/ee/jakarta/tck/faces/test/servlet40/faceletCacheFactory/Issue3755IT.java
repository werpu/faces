/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2020 Contributors to Eclipse Foundation.
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

package ee.jakarta.tck.faces.test.servlet40.faceletCacheFactory;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import ee.jakarta.tck.faces.test.util.arquillian.ITBaseAll;
import jakarta.faces.view.facelets.FaceletCacheFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Issue3755IT extends ITBaseAll {

    @Before
    public void setUp() {
        webClient = new WebClient();
    }

    /**
     * @see FaceletCacheFactory
     * @see https://github.com/eclipse-ee4j/mojarra/issues/3759
     */
    @Test
    public void testCustomFactory() throws Exception {
        HtmlPage page = webClient.getPage(webUrl);
        HtmlTextInput input = page.getHtmlElementById("input");
        String inputText = "" + System.currentTimeMillis();
        input.setText(inputText);
        HtmlSubmitInput submit = page.getHtmlElementById("submit");
        page = submit.click();

        String pageText = page.getBody().asNormalizedText();
        assertTrue(pageText.contains("output: " + inputText));
        assertTrue(pageText.matches("(?s).*message.\\d+.*"));
    }
}
