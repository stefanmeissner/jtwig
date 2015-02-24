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

public class SliceFilterTest extends AbstractJtwigTest {
    
    @Test
    public void generalTest() throws Exception {
        assertEquals("t", theResultOf(stringResource("{{ 'test'|slice(0,1) }}")));
        assertEquals("te", theResultOf(stringResource("{{ 'test'|slice(0,2) }}")));
        assertEquals("es", theResultOf(stringResource("{{ 'test'|slice(1,2) }}")));
        assertEquals(".2", theResultOf(stringResource("{{ '1.21'|slice(1,2) }}")));
        assertEquals("2a", theResultOf(stringResource("{{ '2.2a'|slice(2,3) }}")));
        assertEquals(".2", theResultOf(stringResource("{{ 1.2|slice(1,2) }}")));
//        assertEquals("-0.2", theResultOf(stringResource("{{ -1.2|slice(1,2) }}"))); // Seems to be a unary operator issue
        assertEquals("1.", theResultOf(stringResource("{{ (-1.2)|slice(1,2) }}")));
        assertEquals("bc", theResultOf(stringResource("{{ ['a','b','c']|slice(1,2)|join }}")));
        assertEquals("23", theResultOf(stringResource("{{ {'a':'1','b':'2','c':'3'}|slice(1,2)|join }}")));
        assertEquals("bc", theResultOf(stringResource("{{ {'a':'1','b':'2','c':'3'}|slice(1,2)|keys|join }}")));
    }
    
    @Test
    public void testNegativeBounds() throws Exception {
        assertEquals("st", theResultOf(stringResource("{{ 'test'|slice(-2) }}")));
        assertEquals("s", theResultOf(stringResource("{{ 'test'|slice(-2,-1) }}")));
        assertEquals("s", theResultOf(stringResource("{{ 'test'|slice(-2,1) }}")));
        assertEquals("21", theResultOf(stringResource("{{ '1.21'|slice(-2) }}")));
        assertEquals("2a", theResultOf(stringResource("{{ '2.2a'|slice(-2) }}")));
        assertEquals("2a", theResultOf(stringResource("{{ '2.2a'|slice(-2) }}")));
        assertEquals(".2", theResultOf(stringResource("{{ 1.2|slice(-2) }}")));
//        assertEquals("-0.2", theResultOf(stringResource("{{ -1.2|slice(-2) }}")));
        assertEquals(".2", theResultOf(stringResource("{{ (-1.2)|slice(-2) }}")));
        assertEquals("bc", theResultOf(stringResource("{{ ['a','b','c']|slice(-2)|join }}")));
        assertEquals("23", theResultOf(stringResource("{{ {'a':'1','b':'2','c':'3'}|slice(-2)|join }}")));
        assertEquals("bc", theResultOf(stringResource("{{ {'a':'1','b':'2','c':'3'}|slice(-2)|keys|join }}")));
        assertEquals("ab", theResultOf(stringResource("{{ {'a':'1','b':'2','c':'3'}|slice(-3,-1)|keys|join }}")));
    }
    
}