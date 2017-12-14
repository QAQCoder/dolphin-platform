/*
 * Copyright 2015-2017 Canoo Engineering AG.
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
package com.canoo.dolphin.converters;

import com.canoo.platform.remoting.spi.converter.Converter;
import com.canoo.platform.remoting.spi.converter.ValueConverterException;
import com.canoo.dp.impl.remoting.converters.AbstractConverterFactory;
import com.canoo.dp.impl.remoting.converters.AbstractStringConverter;
import org.apiguardian.api.API;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.canoo.dolphin.converters.ValueFieldTypes.DURATION_FIELD_TYPE;
import static org.apiguardian.api.API.Status.INTERNAL;

@API(since = "0.x", status = INTERNAL)
public class DurationConverterFactory extends AbstractConverterFactory {

    private final static Converter CONVERTER = new DurationConverter();

    @Override
    public boolean supportsType(Class<?> cls) {
        return Duration.class.isAssignableFrom(cls);
    }

    @Override
    public List<Class> getSupportedTypes() {
        return new ArrayList<>(Arrays.asList(Duration.class));
    }

    @Override
    public int getTypeIdentifier() {
        return DURATION_FIELD_TYPE;
    }

    @Override
    public Converter getConverterForType(Class<?> cls) {
        return CONVERTER;
    }

    private static class DurationConverter extends AbstractStringConverter<Duration> {

        @Override
        public Duration convertFromDolphin(String value) throws ValueConverterException {
            if (value == null) {
                return null;
            }
            try {
                return Duration.parse(value);
            } catch (Exception e) {
                throw new ValueConverterException("Can not convert to Duration", e);
            }
        }

        @Override
        public String convertToDolphin(Duration value) throws ValueConverterException {
            if (value == null) {
                return null;
            }
            try {
                return value.toString();
            } catch (Exception e) {
                throw new ValueConverterException("Can not convert from Duration", e);
            }
        }
    }

}
