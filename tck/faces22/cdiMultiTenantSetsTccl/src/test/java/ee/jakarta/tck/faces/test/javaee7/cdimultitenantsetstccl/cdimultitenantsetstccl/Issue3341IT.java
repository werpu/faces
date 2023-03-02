/*
 * Copyright (c) 2021 Contributors to the Eclipse Foundation.
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
package ee.jakarta.tck.faces.test.javaee7.cdimultitenantsetstccl.cdimultitenantsetstccl;

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

import jakarta.faces.FactoryFinder;

@Ignore("Successful challenge via https://github.com/jakartaee/faces/issues/1679")
public class Issue3341IT extends ITBaseAll {


    /**
     * Setup before testing.
     */
    @Before
    public void setUp() {
        webClient = new WebClient();
    }

    /**
     * @see FactoryFinder
     * @see https://github.com/eclipse-ee4j/mojarra/issues/3345
     */
    @Test
    public void testTCCLReplacementResilience() throws Exception {
        HtmlPage page = webClient.getPage(webUrl);

        String pageText = page.getBody().asNormalizedText();

        // If the BeforeFilter is configured to
        if (pageText.matches("(?s).*SUCCESS.*")) {
            assertTrue(true);
        } else {
            assertTrue(pageText.matches("(?s).*Duke.*submit.*"));
            assertTrue(pageText.matches("(?s).*First name:\\s*Duke.*"));
            assertTrue(pageText.matches("(?s).*BeforeServlet init found Lifecycle:\\s*TRUE.*"));
            assertTrue(pageText.matches("(?s).*BeforeServlet init found FacesContext:\\sTRUE.*"));
            assertTrue(pageText.matches("(?s).*BeforeServlet request found Lifecycle:\\s*TRUE.*"));
            // Yes, the FacesContext.getCurrentInstance() should not be found
            // because this is in a Filter before the run of the FacesServlet.service().
            assertTrue(pageText.matches("(?s).*BeforeServlet request found FacesContext:\\s*FALSE.*"));
        }
    }
}
