/*
 * Copyright (c) 2022 Contributors to Eclipse Foundation.
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

package ee.jakarta.tck.faces.test.servlet60.externalContext;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ee.jakarta.tck.faces.test.util.arquillian.ITBase;
import jakarta.faces.context.ExternalContext;

public class Issue5165IT extends ITBase {

    /**
     * @see ExternalContext#addResponseCookie(String, String, java.util.Map)
     * @see https://github.com/eclipse-ee4j/mojarra/issues/5165
     */
    @Test
    public void testIssue5165() throws Exception {
        HtmlPage page = getPage("issue5165.xhtml");

        System.out.println(page.getWebResponse().getResponseHeaders()); // TODO
    }

}