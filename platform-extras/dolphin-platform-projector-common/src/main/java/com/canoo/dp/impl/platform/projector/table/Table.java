/*
 * Copyright 2015-2018 Canoo Engineering AG.
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
package com.canoo.dp.impl.platform.projector.table;

import com.canoo.dp.impl.platform.projector.base.Projectable;
import com.canoo.dp.impl.platform.projector.base.WithLayoutMetadata;
import com.canoo.dp.impl.platform.projector.base.WithMultiSelection;
import com.canoo.platform.remoting.ObservableList;
import com.canoo.platform.remoting.Property;
import com.canoo.platform.remoting.RemotingBean;

@RemotingBean
public interface Table<T> extends WithMultiSelection<T>, WithLayoutMetadata, Projectable {

    ObservableList<Column> getColumns();

    ObservableList<T> getItems();

    Property<Boolean> editableProperty();

    Property<Boolean> sortableProperty();

    default boolean isEditable() {
        Boolean val = editableProperty().get();
        if(val == null) {
            return false;
        }
        return val.booleanValue();
    }

    default void setEditable(boolean editable) {
        editableProperty().set(editable);
    }

    default boolean isSortable() {
        Boolean val = sortableProperty().get();
        if(val == null) {
            return false;
        }
        return val.booleanValue();
    }

    default void setSortable(boolean sortable) {
        sortableProperty().set(sortable);
    }
}
