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

package ee.jakarta.tck.faces.test.javaee8.cdi;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import ee.jakarta.tck.faces.test.util.arquillian.ITBaseAll;
import jakarta.faces.annotation.ApplicationMap;
import jakarta.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Issue3722IT extends ITBaseAll {

    @Before
    public void setUp() {
        webClient = new WebClient();
    }


    /**
     * @see Inject
     * @see ApplicationMap
     * @see https://github.com/eclipse-ee4j/mojarra/issues/3726
     */
    @Test
    public void testInjectApplicationMap2() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/injectApplicationMap2.xhtml");
        assertTrue(page.asXml().contains("true"));
    }
}
