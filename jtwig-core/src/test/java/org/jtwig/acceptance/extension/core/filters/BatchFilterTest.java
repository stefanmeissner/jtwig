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

import java.util.Arrays;
import java.util.HashMap;
import org.jtwig.AbstractJtwigTest;
import org.junit.Test;
import static org.junit.Assert.*;

public class BatchFilterTest extends AbstractJtwigTest {
    @Test
    public void generalTests() throws Exception {
        theModel().withModelAttribute("list", Arrays.asList('a','b','c','d','e','f','g'));
        theModel().withModelAttribute("map", new HashMap<String, String>(){{
            put("1", "a");
            put("2", "b");
        }});
        assertEquals("g,none,none", theResultOf(stringResource("{{ list|batch(3, 'none')[2] }}")));
        assertEquals("a,b,none", theResultOf(stringResource("{{ map|batch(3, 'none')[2] }}")));
    }
}