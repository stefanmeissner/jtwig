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

import org.jtwig.JtwigTemplate;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SplitFilterTest {
    @Test
    public void generalTests() throws Exception {
        assertEquals("t,e,s,t", JtwigTemplate.inlineTemplate("{{ 'test'|split|join(',') }}").render());
        assertEquals("t,e,s,t", JtwigTemplate.inlineTemplate("{{ 't.e.s.t'|split('.')|join(',') }}").render());
        assertEquals("1,.,2,1", JtwigTemplate.inlineTemplate("{{ '1.21'|split|join(',') }}").render());
        assertEquals("2,.,2,a", JtwigTemplate.inlineTemplate("{{ '2.2a'|split|join(',') }}").render());
        assertEquals("1,.,2", JtwigTemplate.inlineTemplate("{{ 1.2|split|join(',') }}").render());
        assertEquals("-,1,.,2", JtwigTemplate.inlineTemplate("{{ (-1.2)|split|join(',') }}").render());

        assertEquals("", JtwigTemplate.inlineTemplate("{{ ['a','b','c']|split }}").render());
        assertEquals("", JtwigTemplate.inlineTemplate("{{ {'a':'1','b':'2','c':'3'}|split }}").render());
        assertEquals("", JtwigTemplate.inlineTemplate("{{ {'a':'1','b':'2','c':'3'}|split }}").render());
        assertEquals("", JtwigTemplate.inlineTemplate("{{ obj|split }}").render());
    }
}