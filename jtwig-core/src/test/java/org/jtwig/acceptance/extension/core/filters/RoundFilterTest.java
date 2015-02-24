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
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RoundFilterTest extends AbstractJtwigTest {
    
    @Test
    public void generalTests() throws Exception {
        assertEquals("0", theResultOf(stringResource("{{ 'test'|round }}")));
        assertEquals("2", theResultOf(stringResource("{{ '1.5'|round }}")));
        assertEquals("2", theResultOf(stringResource("{{ '1.2'|round(0, 'ceil') }}")));
        assertEquals("1", theResultOf(stringResource("{{ '1.7'|round(0, 'floor') }}")));
        assertEquals("2", theResultOf(stringResource("{{ '2.2a'|round }}")));
        assertEquals("1", theResultOf(stringResource("{{ 1.2|round }}")));
        assertEquals("-1", theResultOf(stringResource("{{ -1.2|round }}")));
        assertEquals("1.2", theResultOf(stringResource("{{ 1.2345|round(1) }}")));
        assertEquals("1.3", theResultOf(stringResource("{{ 1.2345|round(1, 'ceil') }}")));
        assertEquals("1.235", theResultOf(stringResource("{{ 1.2345|round(3) }}")));
        assertEquals("1.234", theResultOf(stringResource("{{ 1.2345|round(3, 'floor') }}")));
        assertEquals("", theResultOf(stringResource("{{ ['a','b']|round }}")));
        assertEquals("", theResultOf(stringResource("{{ {'a':'1','b':'2'}|round }}")));
    }
    
}