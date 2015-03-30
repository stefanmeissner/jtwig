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

package org.jtwig.extension.core.functions;

import java.util.Date;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;
import org.jtwig.Environment;
import org.jtwig.extension.api.functions.Function;
import org.jtwig.render.RenderContext;

public class DateFunction implements Function {

    @Override
    public DateTime evaluate(Environment env, RenderContext ctx, Object... args) {
        // Args: date, timezone
        if (args.length == 0) {
            return new DateTime();
        }
        
        TimeZone tz = TimeZone.getDefault();
        if (args.length > 1) {
            tz = TimeZone.getTimeZone(args[1].toString());
        }
        
        DateTime dt;
        if (args[0] instanceof Date) {
            dt = LocalDateTime.fromDateFields((Date)args[0]).toDateTime();
        } else if (args[0] instanceof DateTime) {
            return (DateTime)args[0];
        } else {
            dt = DateTime.parse(args[0].toString());;
        }
        
        return dt;
    }
    
}