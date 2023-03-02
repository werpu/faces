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

package ee.jakarta.tck.faces.test.faces40.javapages;

import static java.lang.System.getProperty;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

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
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import ee.jakarta.tck.faces.test.util.htmlunit.DebugOptions;

import jakarta.faces.annotation.View;
import jakarta.faces.view.facelets.Facelet;

public class Spec1581IT extends ITBaseAll {

    @Before
    public void setUp() {
        webClient = new WebClient();
        DebugOptions.setDebugOptions(webClient);
    }

    /**
     * @see Facelet
     * @see View
     * @see https://github.com/jakartaee/faces/issues/1581
     */
    @Test
    public void test() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "hello.xhtml");

        System.out.println(page.asXml());

        HtmlInput button = (HtmlInput) page.getElementById("form:button");
        page = button.click();

        System.out.println(page.asXml());

    }

}
