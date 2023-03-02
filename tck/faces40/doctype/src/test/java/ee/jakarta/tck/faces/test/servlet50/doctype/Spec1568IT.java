/*
 * Copyright (c) 2021 Contributors to Eclipse Foundation.
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

package ee.jakarta.tck.faces.test.servlet50.doctype;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import ee.jakarta.tck.faces.test.util.arquillian.ITBaseAll;
import jakarta.faces.component.UIViewRoot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Spec1568IT extends ITBaseAll {

    @Before
    public void setUp() {
        webClient = new WebClient();
    }

    /**
     * @see UIViewRoot#getDoctype()
     * @see https://github.com/jakartaee/faces/issues/1568
     */
    @Test
    public void testHTML5() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "spec1568IT-HTML5.xhtml");

        assertEquals("Page is using HTML5 doctype", "<!DOCTYPE html>", getDoctype(page));
    }

    /**
     * @see UIViewRoot#getDoctype()
     * @see https://github.com/jakartaee/faces/issues/1568
     */
    @Test
    public void testHTML4Public() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "spec1568IT-HTML4-public.xhtml");

        assertEquals("Page is using XHTML4 transitional public doctype", "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">", getDoctype(page));
    }

    /**
     * @see UIViewRoot#getDoctype()
     * @see https://github.com/jakartaee/faces/issues/1568
     */
    @Test
    public void testHTML4System() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "spec1568IT-HTML4-system.xhtml");

        assertEquals("Page is using XHTML4 strict system doctype", "<!DOCTYPE html SYSTEM \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">", getDoctype(page));
    }

    private static String getDoctype(HtmlPage page) {
        return page.getWebResponse().getContentAsString().split("\n", 2)[0];
    }

}
