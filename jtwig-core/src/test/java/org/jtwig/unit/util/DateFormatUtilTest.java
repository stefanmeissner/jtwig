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

package org.jtwig.unit.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.jtwig.util.DateFormatUtil;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DateFormatUtilTest {
    @Test
    public void testOutputFormatConversions() throws Exception {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime dt = new DateTime(2014, 4, 6, 14, 5, 8, 298, zone);
        assertEquals("Sun, Apr 6th, 2014 2:05:08 pm", DateFormatUtil.output("D, M jS, Y g:i:s a").print(dt));
        assertEquals("2014/04/06 14:05:08", DateFormatUtil.output("y/m/d H:i:s").print(dt));
        assertEquals("Sun: 0 7", DateFormatUtil.output("D: w N").print(dt));
        assertEquals("30 0", DateFormatUtil.output("t L").print(dt));
        assertEquals("795 1", DateFormatUtil.output("B I").print(dt));
    }
}
