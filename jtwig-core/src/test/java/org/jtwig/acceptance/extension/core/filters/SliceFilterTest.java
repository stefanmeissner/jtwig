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

public class SliceFilterTest {
    
    @Test
    public void generalTest() throws Exception {
        assertEquals("t", JtwigTemplate.inlineTemplate("{{ 'test'|slice(0,1) }}").render());
        assertEquals("te", JtwigTemplate.inlineTemplate("{{ 'test'|slice(0,2) }}").render());
        assertEquals("es", JtwigTemplate.inlineTemplate("{{ 'test'|slice(1,2) }}").render());
        assertEquals(".2", JtwigTemplate.inlineTemplate("{{ '1.21'|slice(1,2) }}").render());
        assertEquals("2a", JtwigTemplate.inlineTemplate("{{ '2.2a'|slice(2,3) }}").render());
        assertEquals(".2", JtwigTemplate.inlineTemplate("{{ 1.2|slice(1,2) }}").render());
//        assertEquals("-0.2", JtwigTemplate.inlineTemplate("{{ -1.2|slice(1,2) }}").render()); // Seems to be a unary operator issue
        assertEquals("1.", JtwigTemplate.inlineTemplate("{{ (-1.2)|slice(1,2) }}").render());
        assertEquals("bc", JtwigTemplate.inlineTemplate("{{ ['a','b','c']|slice(1,2)|join }}").render());
        assertEquals("23", JtwigTemplate.inlineTemplate("{{ {'a':'1','b':'2','c':'3'}|slice(1,2)|join }}").render());
        assertEquals("bc", JtwigTemplate.inlineTemplate("{{ {'a':'1','b':'2','c':'3'}|slice(1,2)|keys|join }}").render());
    }
    
    @Test
    public void testNegativeBounds() throws Exception {
        assertEquals("st", JtwigTemplate.inlineTemplate("{{ 'test'|slice(-2) }}").render());
        assertEquals("s", JtwigTemplate.inlineTemplate("{{ 'test'|slice(-2,-1) }}").render());
        assertEquals("s", JtwigTemplate.inlineTemplate("{{ 'test'|slice(-2,1) }}").render());
        assertEquals("21", JtwigTemplate.inlineTemplate("{{ '1.21'|slice(-2) }}").render());
        assertEquals("2a", JtwigTemplate.inlineTemplate("{{ '2.2a'|slice(-2) }}").render());
        assertEquals("2a", JtwigTemplate.inlineTemplate("{{ '2.2a'|slice(-2) }}").render());
        assertEquals(".2", JtwigTemplate.inlineTemplate("{{ 1.2|slice(-2) }}").render());
//        assertEquals("-0.2", JtwigTemplate.inlineTemplate("{{ -1.2|slice(-2) }}").render());
        assertEquals(".2", JtwigTemplate.inlineTemplate("{{ (-1.2)|slice(-2) }}").render());
        assertEquals("bc", JtwigTemplate.inlineTemplate("{{ ['a','b','c']|slice(-2)|join }}").render());
        assertEquals("23", JtwigTemplate.inlineTemplate("{{ {'a':'1','b':'2','c':'3'}|slice(-2)|join }}").render());
        assertEquals("bc", JtwigTemplate.inlineTemplate("{{ {'a':'1','b':'2','c':'3'}|slice(-2)|keys|join }}").render());
        assertEquals("ab", JtwigTemplate.inlineTemplate("{{ {'a':'1','b':'2','c':'3'}|slice(-3,-1)|keys|join }}").render());
    }
    
}