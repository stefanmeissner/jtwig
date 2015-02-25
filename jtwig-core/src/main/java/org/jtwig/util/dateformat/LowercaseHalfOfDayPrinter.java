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

import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

public class LowercaseHalfOfDayPrinter extends AbstractPrinter {
    @Override
    public int estimatePrintedLength() {
        return 2;
    }

    @Override
    public String fromPartial(ReadablePartial partial, Locale locale) {
        return partial.getChronology().halfdayOfDay().getAsText(partial, locale).toLowerCase();
    }

    @Override
    public String fromInstant(long instant, Chronology chrono, int displayOffset, DateTimeZone displayZone, Locale locale) {
        DateTime dt = new DateTime(instant, DateTimeZone.UTC).withZoneRetainFields(displayZone);
        return dt.getChronology().halfdayOfDay().getAsText(dt.getMillis()).toLowerCase();
    }
    
}