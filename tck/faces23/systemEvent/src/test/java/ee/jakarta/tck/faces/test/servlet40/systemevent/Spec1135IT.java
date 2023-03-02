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

package ee.jakarta.tck.faces.test.servlet40.systemevent;

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
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import jakarta.faces.event.PostRenderViewEvent;

public class Spec1135IT extends ITBaseAll {

    @Before
    public void setUp() {
        webClient = new WebClient();
    }

    /**
     * @see PostRenderViewEvent
     * @see https://github.com/jakartaee/faces/issues/1135
     */
    @Test
    public void testPreRenderViewEvent() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/postRenderViewEvent.xhtml");
        String response = page.getWebResponse().getContentAsString();

        int prePos = response.indexOf("<!-- pre -->");
        int htmlStartPos = response.indexOf("<html");
        int htmlEndPos = response.indexOf("</html>");
        int postPos = response.indexOf("<!-- post -->");

        // All of the above fragments must have been rendered
        assertTrue(prePos != -1 && htmlStartPos != -1 && htmlEndPos != -1 && postPos != -1);

        // Assert that the fragments are rendered in the right order
        assertTrue(prePos < htmlStartPos && htmlStartPos < htmlEndPos && htmlEndPos < postPos);
    }
}
