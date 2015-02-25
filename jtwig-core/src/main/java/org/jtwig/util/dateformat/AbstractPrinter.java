/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jtwig.util.dateformat;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimePrinter;

public abstract class AbstractPrinter implements DateTimePrinter {

    @Override
    public void printTo(StringBuffer buf, long instant, Chronology chrono, int displayOffset, DateTimeZone displayZone, Locale locale) {
        buf.append(fromInstant(instant, chrono, displayOffset, displayZone, locale));
    }

    @Override
    public void printTo(Writer out, long instant, Chronology chrono, int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
        out.append(fromInstant(instant, chrono, displayOffset, displayZone, locale));
    }

    @Override
    public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
        buf.append(fromPartial(partial, locale));
    }

    @Override
    public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
        out.append(fromPartial(partial, locale));
    }
    
    public abstract String fromPartial(ReadablePartial partial, Locale locale);
    
    public String fromInstant(long instance, Chronology chrono, int displayOffset, DateTimeZone displayZone, Locale locale) {
        return fromPartial(new LocalDateTime(instance, displayZone), locale);
    }
    
}