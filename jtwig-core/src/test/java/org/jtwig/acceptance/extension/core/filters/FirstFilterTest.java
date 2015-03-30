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

public class FirstFilterTest {
    
    @Test
    public void generalTests() throws Exception {
        assertEquals("5", JtwigTemplate.inlineTemplate("{{ 5678|first }}").render());
        assertEquals("5", JtwigTemplate.inlineTemplate("{{ 5.678|first }}").render());
        assertEquals("t", JtwigTemplate.inlineTemplate("{{ 'test'|first }}").render());
        assertEquals("a", JtwigTemplate.inlineTemplate("{{ ['a','b','c']|first }}").render());
        assertEquals("a", JtwigTemplate.inlineTemplate("{{ {'1':'a','2':'b','3':'c'}|first }}").render());
        assertEquals("", JtwigTemplate.inlineTemplate("{{ ''|first }}").render());
        assertEquals("", JtwigTemplate.inlineTemplate("{{ null|first }}").render());
        assertEquals("1", JtwigTemplate.inlineTemplate("{{ true|first }}").render());
    }
    
}