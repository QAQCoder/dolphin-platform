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
package com.canoo.dp.impl.platform.server.metrics.noop;

import com.canoo.dp.impl.platform.server.metrics.AbstractMeter;
import com.canoo.dp.impl.platform.server.metrics.MeterIdentifier;
import com.canoo.platform.metrics.types.Gauge;

public class NoopGauge extends AbstractMeter implements Gauge {

    public NoopGauge(final MeterIdentifier identifier, final AutoCloseable closeable) {
        super(identifier, closeable);
    }

    @Override
    public void setValue(final double value) {

    }
}