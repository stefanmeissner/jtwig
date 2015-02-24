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

public class FirstFilterTest extends AbstractJtwigTest {
    
    @Test
    public void generalTests() throws Exception {
        assertEquals("5", theResultOf(stringResource("{{ 5678|first }}")));
        assertEquals("5", theResultOf(stringResource("{{ 5.678|first }}")));
        assertEquals("t", theResultOf(stringResource("{{ 'test'|first }}")));
        assertEquals("a", theResultOf(stringResource("{{ ['a','b','c']|first }}")));
        assertEquals("a", theResultOf(stringResource("{{ {'1':'a','2':'b','3':'c'}|first }}")));
        assertEquals("", theResultOf(stringResource("{{ ''|first }}")));
        assertEquals("", theResultOf(stringResource("{{ null|first }}")));
        assertEquals("1", theResultOf(stringResource("{{ true|first }}")));
    }
    
//    @Test
//    public void first() throws Exception {
//        assertEquals(underTest.first("test"), (Character) 't');
//    }
//    @Test
//    public void firstWithEmptyString() throws Exception {
//        assertNull(underTest.first(""));
//    }
//
//    @Test
//    public void first() throws Exception {
//        assertEquals(underTest.first(new String[]{"a","b","c"}), "a");
//        assertEquals(underTest.first(null), null);
//        assertEquals(underTest.first(true), true);
//    }
}