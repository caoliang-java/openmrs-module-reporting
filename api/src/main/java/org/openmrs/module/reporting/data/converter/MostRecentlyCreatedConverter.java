/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.reporting.data.converter;

import org.openmrs.Auditable;
import org.openmrs.util.OpenmrsUtil;

import java.util.Collection;

/**
 * Gets the most-recently-created element of a collection of Auditable.
 */
public class MostRecentlyCreatedConverter implements DataConverter {

    private Class<?> typeOfItem;

    public MostRecentlyCreatedConverter(Class<?> typeOfItem) {
        this.typeOfItem = typeOfItem;
    }

    @Override
    public Object convert(Object original) {
        Collection c = (Collection) original;
        if (c == null) {
            return null;
        }
        Auditable latest = null;
        for (Object o : c) {
            if (o instanceof Auditable) {
                Auditable candidate = (Auditable) o;
                if (latest == null || OpenmrsUtil.compare(latest.getDateCreated(), candidate.getDateCreated()) < 0) {
                    latest = candidate;
                }
            }
        }
        return latest;
    }

    /**
     * Should really be Collection<org.openmrs.Auditable>
     * @return
     */
    @Override
    public Class<?> getInputDataType() {
        return Collection.class;
    }

    @Override
    public Class<?> getDataType() {
        return typeOfItem;
    }
}
