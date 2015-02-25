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

import org.jtwig.AbstractJtwigTest;
import org.jtwig.exception.RenderException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DateModifyFilterTest extends AbstractJtwigTest {
    @Test
    public void addDay() throws Exception {
        assertEquals("2011-01-02 00:00:00", theResultOf(stringResource("{{ '2011-01-01T00:00:00'|date_modify('+1 day')|date('y-m-d H:i:s') }}")));
        assertEquals("2011-01-02", theResultOf(stringResource("{{ '2011-01-01'|date_modify('+1 day')|date('y-m-d') }}")));
    }

    @Test
    public void addMonth() throws Exception {
        assertEquals("2011-02-01 00:00:00", theResultOf(stringResource("{{ '2011-01-01T00:00:00'|date_modify('+1 month')|date('y-m-d H:i:s') }}")));
        assertEquals("2011-02-01", theResultOf(stringResource("{{ '2011-01-01'|date_modify('+1 month')|date('y-m-d') }}")));
    }

    @Test
    public void addYear() throws Exception {
        assertEquals("2012-01-01 00:00:00", theResultOf(stringResource("{{ '2011-01-01T00:00:00'|date_modify('+1 year')|date('y-m-d H:i:s') }}")));
        assertEquals("2012-01-01", theResultOf(stringResource("{{ '2011-01-01'|date_modify('+1 year')|date('y-m-d') }}")));
    }

    @Test
    public void addMinute() throws Exception {
        assertEquals("2011-01-01 00:01:00", theResultOf(stringResource("{{ '2011-01-01T00:00:00'|date_modify('+1 minute')|date('y-m-d H:i:s') }}")));
    }

    @Test
    public void addSecond() throws Exception {
        assertEquals("2011-01-01 00:00:01", theResultOf(stringResource("{{ '2011-01-01T00:00:00'|date_modify('+1 second')|date('y-m-d H:i:s') }}")));
    }

    @Test
    public void addHour() throws Exception {
        assertEquals("2011-01-01 01:00:00", theResultOf(stringResource("{{ '2011-01-01T00:00:00'|date_modify('+1 hour')|date('y-m-d H:i:s') }}")));
    }

    @Test(expected = RenderException.class)
    public void addUnknown() throws Exception {
        theResultOf(stringResource("{{ '2011-01-01T00:00:00'|date_modify('+1 unknown')|date('y-m-d H:i:s') }}"));
    }
}