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
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

public class SwatchInternetTimePrinter extends AbstractPrinter {

    @Override
    public String fromPartial(ReadablePartial partial, Locale locale) {
        // (UTC+1seconds + (UTC+1minutes * 60) + (UTC+1hours * 3600)) / 86.4
        
        // NOTE: As far as I can tell, we can't get timezone info when given a
        // partial, so we assume the time is given according the default time
        // zone. - Thomas Wilson
        
        int localSeconds = partial.get(DateTimeFieldType.secondOfMinute());
        int localMinutes = partial.get(DateTimeFieldType.minuteOfHour());
        int localHours = partial.get(DateTimeFieldType.hourOfDay());
        DateTime dt = new DateTime(2000, 1, 1, localHours, localMinutes, localSeconds, DateTimeZone.getDefault())
                .withZone(DateTimeZone.forOffsetHours(1));
        return fromDateTime(dt);
    }

    @Override
    public String fromInstant(long instant, Chronology chrono, int displayOffset, DateTimeZone displayZone, Locale locale) {
        DateTime dt = new DateTime(instant, DateTimeZone.UTC)
                .withZoneRetainFields(displayZone)
                .withZone(DateTimeZone.forOffsetHours(1));
        return fromDateTime(dt);
    }
    
    protected String fromDateTime(final DateTime dt) {
        return (int)((dt.getSecondOfMinute() + (dt.getMinuteOfHour() * 60) + (dt.getHourOfDay() * 3600)) / 86.4)+"";
    }

    @Override
    public int estimatePrintedLength() {
        return 3;
    }
    
}