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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.*;

import static com.canoo.dp.impl.platform.core.PlatformConstants.REMOTING_DATE_FORMAT_PATTERN;
import static com.canoo.dp.impl.platform.core.PlatformConstants.TIMEZONE_UTC;
import static com.canoo.dolphin.converters.ValueFieldTypes.LOCAL_DATE_TIME_FIELD_TYPE;
import static org.apiguardian.api.API.Status.INTERNAL;

@API(since = "0.x", status = INTERNAL)
public class LocalDateTimeConverterFactory extends AbstractConverterFactory {

    private final static Converter CONVERTER = new LocalDateTimeConverter();

    @Override
    public boolean supportsType(Class<?> cls) {
        return LocalDateTime.class.isAssignableFrom(cls);
    }

    @Override
    public List<Class> getSupportedTypes() {
        return new ArrayList<>(Arrays.asList(LocalDateTime.class));
    }

    @Override
    public int getTypeIdentifier() {
        return LOCAL_DATE_TIME_FIELD_TYPE;
    }

    @Override
    public Converter getConverterForType(Class<?> cls) {
        return CONVERTER;
    }

    private static class LocalDateTimeConverter extends AbstractStringConverter<LocalDateTime> {

        private final DateFormat dateFormat;
        public LocalDateTimeConverter(){
            dateFormat = new SimpleDateFormat(REMOTING_DATE_FORMAT_PATTERN);
            dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE_UTC));
        }
        @Override
        public LocalDateTime convertFromDolphin(String value) throws ValueConverterException {
            if (value == null) {
                return null;
            }
            try {
                final Calendar result = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE_UTC));
                result.setTime(dateFormat.parse(value));
                return result.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            } catch (Exception e) {
                throw new ValueConverterException("Can not convert to LocalDateTime", e);
            }
        }

        @Override
        public String convertToDolphin(LocalDateTime value) throws ValueConverterException {
            if (value == null) {
                return null;
            }
            try {
                Date date = Date.from(value.toInstant(OffsetDateTime.now().getOffset()));
                return dateFormat.format(date);
            } catch (Exception e) {
                throw new ValueConverterException("Can not convert from LocalDateTime", e);
            }
        }
    }

}
