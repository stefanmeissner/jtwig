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

import java.util.HashMap;
import org.jtwig.AbstractJtwigTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ReplaceFilterTest extends AbstractJtwigTest {
    
    @Test
    public void generalTests() throws Exception {
        theModel().withModelAttribute("replacements", new HashMap<String, Object>(){{
            put("%this%", "foo");
            put("%that%", "bar");
        }});
        assertEquals("I like foo and bar.", theResultOf(stringResource("{{ 'I like %this% and %that%.'|replace(replacements) }}")));
        assertEquals("I like foo and %other%.", theResultOf(stringResource("{{ 'I like %this% and %other%.'|replace(replacements) }}")));
        assertEquals("", theResultOf(stringResource("{{ 'I like %this% and %that%.'|replace }}")));
    }
    
}
