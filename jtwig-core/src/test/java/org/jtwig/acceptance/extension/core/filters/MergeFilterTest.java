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

public class MergeFilterTest extends AbstractJtwigTest {
    
    @Test
    public void generalTests() throws Exception {
        assertEquals("ab12", theResultOf(stringResource("{{ ['a','b']|merge([1,2])|join }}")));
        assertEquals("ab01", theResultOf(stringResource("{{ {'a':'1','b':'2'}|merge([1,2])|keys|join }}")));
        assertEquals("1212", theResultOf(stringResource("{{ {'a':'1','b':'2'}|merge([1,2])|join }}")));
        assertEquals("abcd", theResultOf(stringResource("{{ {'a':'1','b':'2'}|merge({'c':'3','d':'4'})|keys|join }}")));
        assertEquals("1234", theResultOf(stringResource("{{ {'a':'1','b':'2'}|merge({'c':'3','d':'4'})|join }}")));
        assertEquals("abcd", theResultOf(stringResource("{{ ['a','b']|merge(['c','d'],['e','f'])|join }}")));
    }
    
}