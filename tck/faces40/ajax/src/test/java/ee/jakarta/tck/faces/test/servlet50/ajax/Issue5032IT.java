/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
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

package ee.jakarta.tck.faces.test.servlet50.ajax;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import ee.jakarta.tck.faces.test.util.htmlunit.ITBaseHTMLUnitOnly;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.behavior.AjaxBehavior;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Issue5032IT extends ITBaseHTMLUnitOnly{

    @Before
    public void setUp() {
        webClient = new WebClient();
    }

    /**
     * @see AjaxBehavior#getExecute()
     * @see UIComponent#getCompositeComponentParent(UIComponent)
     * @see https://github.com/eclipse-ee4j/mojarra/issues/5032
     */
    @Test
    public void testImplicitThis() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "issue5032IT.xhtml");

        HtmlTextInput form1input2 = (HtmlTextInput) page.getElementById("form1:inputs:input2");
        form1input2.setValueAttribute("1");
        form1input2.fireEvent("change");
        webClient.waitForBackgroundJavaScript(3000);
        String form1messages = page.getElementById("form1:messages").asNormalizedText();
        assertEquals("there are no validation messages coming from required field form1:input1", "", form1messages);
    }

    /**
     * @see AjaxBehavior#getExecute()
     * @see UIComponent#getCompositeComponentParent(UIComponent)
     * @see https://github.com/eclipse-ee4j/mojarra/issues/5032
     */
    @Test
    public void testExplicitThis() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "issue5032IT.xhtml");

        HtmlTextInput form2input2 = (HtmlTextInput) page.getElementById("form2:inputs:input2");
        form2input2.setValueAttribute("1");
        form2input2.fireEvent("change");
        webClient.waitForBackgroundJavaScript(3000);
        String form2messages = page.getElementById("form2:messages").asNormalizedText();
        assertEquals("there are no validation messages coming from required field form2:input1", "", form2messages);
    }

}
