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
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import ee.jakarta.tck.faces.test.util.htmlunit.ITBaseHTMLUnitOnly;
import jakarta.faces.component.search.SearchKeywordResolver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Spec1238IT extends ITBaseHTMLUnitOnly {

    @Before
    public void setUp() {
        webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.setJavaScriptTimeout(120000);
    }

    /**
     * @see SearchKeywordResolver
     * @see https://github.com/jakartaee/faces/issues/1238
     */
    @Test
    public void test() throws Exception {
        webClient.setIncorrectnessListener((ignore, nothing) -> {});

        testSearchExpression();
    }

    public void testSearchExpression() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "spec1238.xhtml");
        webClient.waitForBackgroundJavaScript(60000);

        HtmlLabel label = (HtmlLabel) page.getHtmlElementById("label");
        HtmlTextInput input = (HtmlTextInput) page.getHtmlElementById("spec1238ITinput1");
        
        Assert.assertEquals(label.getAttribute("for"), input.getId());
        
        String onchange = input.getAttribute("onchange");

        if (onchange.contains("@this")) {
            Assert.assertFalse(onchange.contains("spec1238ITinput1"));
        }
        else {
            Assert.assertTrue(onchange.contains("spec1238ITinput1"));
        }

        Assert.assertTrue(onchange.contains("spec1238ITinput2"));
    }


}
