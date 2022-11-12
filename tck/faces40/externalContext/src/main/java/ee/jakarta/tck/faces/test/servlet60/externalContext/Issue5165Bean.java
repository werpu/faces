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

import java.util.Map;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Issue5165Bean {

    public void onload() {
        var cookieName = "issue5165";
        var cookieValue = "cookieValue";
        var cookieProperties = Map.<String, Object>of("path", "/", "maxAge", -1, "SameSite", "Lax");
        FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(cookieName, cookieValue, cookieProperties);
    }
}
