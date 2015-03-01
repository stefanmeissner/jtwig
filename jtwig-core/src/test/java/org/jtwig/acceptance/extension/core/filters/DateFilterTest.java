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

package org.jtwig.acceptance.extension.core.filters;

import java.util.Collections;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.jtwig.JtwigModelMap;
import org.jtwig.JtwigTemplate;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DateFilterTest {
    
    @Test
    public void generalTests() throws Exception {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime dt = new DateTime(2014, 4, 6, 14, 5, 8, 298, zone);
        JtwigModelMap model = new JtwigModelMap(Collections.singletonMap("d", (Object)dt));
        assertEquals("April 6, 2014 14:05", JtwigTemplate.inlineTemplate("{{ d|date }}").render(model));
        assertEquals("April 6, 2014 20:05", JtwigTemplate.inlineTemplate("{{ d|date('F j, Y H:i', 'Europe/Paris') }}").render(model));
        assertEquals("06/04/2014 14:05:08", JtwigTemplate.inlineTemplate("{{ d|date('d/m/Y H:i:s') }}").render(model));
    }
    
}