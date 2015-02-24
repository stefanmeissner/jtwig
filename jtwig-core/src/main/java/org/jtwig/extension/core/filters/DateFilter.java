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

package org.jtwig.extension.core.filters;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;
import org.jtwig.extension.api.filters.Filter;

public class DateFilter implements Filter {

    @Override
    public Object evaluate(Object left, Object... args) {
        LocalDateTime ldt;
        if (left instanceof Date) {
            ldt = LocalDateTime.fromDateFields((Date)left);
        } else {
            ldt = LocalDateTime.parse(left.toString());
        }
        
        if (args.length >= 1) {
            return ldt.toString(DateTimeFormat.forPattern(args[0].toString()));
        }
        return ldt.toString(ISODateTimeFormat.dateHourMinuteSecond());
    }
}