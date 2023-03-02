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

package ee.jakarta.tck.faces.test.servlet40.facelets;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import ee.jakarta.tck.faces.test.util.arquillian.ITBaseAll;
import jakarta.faces.component.UIData;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.regex.Pattern;

import static java.lang.System.getProperty;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;
import static org.junit.Assert.assertTrue;


public class Spec1103IT extends ITBaseAll {

    @Before
    public void setUp() {
        webClient = new WebClient();
    }

    /**
     * @see UIData
     * @see Iterable
     * @see https://github.com/jakartaee/faces/issues/1103
     */
    @Test
    public void testDataTableIterable() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/datatableIterable.xhtml");
        assertTrue(Pattern.matches("(?s).*START.*0.*1.*2.*END.*", page.asXml()));
    }

    /**
     * @see com.sun.faces.facelets.component.UIRepeat
     * @see Iterable
     * @see https://github.com/jakartaee/faces/issues/1103
     */
    @Test
    public void testUIRepeatIterable() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/uirepeatIterable.xhtml");
        assertTrue(Pattern.matches("(?s).*START.*0.*1.*2.*END.*", page.asXml()));
    }

    /**
     * @see com.sun.faces.facelets.component.UIRepeat
     * @see Collection
     * @see https://github.com/jakartaee/faces/issues/1103
     */
    @Test
    public void testUIRepeatCollection() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/uirepeatCollection.xhtml");
        assertTrue(Pattern.matches("(?s).*START.*1.*2.*3.*END.*", page.asXml()));
    }
}
