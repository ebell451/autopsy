/*
 * Autopsy Forensic Browser
 *
 * Copyright 2021 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.mainui.datamodel;

import java.util.Objects;

/**
 * Key for accessing data about file MIME type from the DAO.
 */
public class FileTypeMimeSearchParams extends DataSourceFilteredSearchParams {

    private final String mimeType;

    public FileTypeMimeSearchParams(String mimeType, Long dataSourceId) {
        super(dataSourceId);
        this.mimeType = mimeType;
    }

    public FileTypeMimeSearchParams(String mimeType, Long dataSourceId, long startItem, Long maxResultsCount) {
        super(startItem, maxResultsCount, dataSourceId);
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.mimeType);
        hash = 97 * hash + super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileTypeMimeSearchParams other = (FileTypeMimeSearchParams) obj;
        if (!Objects.equals(this.mimeType, other.mimeType)) {
            return false;
        }
        return super.equals(obj);
    }

}
