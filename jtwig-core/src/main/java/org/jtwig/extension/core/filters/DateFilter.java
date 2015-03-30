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

import com.crosstreelabs.phpfunctions.DateFormatUtil;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.jtwig.extension.api.filters.Filter;
import org.jtwig.extension.core.functions.DateFunction;

public class DateFilter implements Filter {

    @Override
    public Object evaluate(Object left, Object... args) {
        DateTimeZone zone = DateTimeZone.getDefault();
        if (args.length > 1) {
            zone = DateTimeZone.forID(args[1].toString());
        }
        
        DateTime dt = new DateFunction().evaluate(null, null, left, zone)
                .withZone(zone);
        // TODO Move the default format somewhere central
        DateTimeFormatter formatter = DateFormatUtil.output(args.length > 0 ? args[0].toString() : "F j, Y H:i");
        
        return dt.toString(formatter);
    }
}