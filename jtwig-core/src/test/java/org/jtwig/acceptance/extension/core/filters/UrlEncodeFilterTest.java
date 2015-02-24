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

import java.util.TreeMap;
import org.jtwig.AbstractJtwigTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UrlEncodeFilterTest extends AbstractJtwigTest {
    
    @Test
    public void generalTests() throws Exception {
        assertEquals("%20test%20", theResultOf(stringResource("{{ ' test '|url_encode }}")));
        assertEquals("1.21", theResultOf(stringResource("{{ '1.21'|url_encode }}")));
        assertEquals("2.2a", theResultOf(stringResource("{{ '2.2a'|url_encode }}")));
        assertEquals("1.2", theResultOf(stringResource("{{ 1.2|url_encode }}")));
        assertEquals("-1.2", theResultOf(stringResource("{{ (-1.2)|url_encode }}")));

        assertEquals("0=a&1=b&2=c", theResultOf(stringResource("{{ ['a','b','c']|url_encode }}")));
        assertEquals("a=1&b=2&c=3", theResultOf(stringResource("{{ {'a':'1','b':'2','c':'3'}|url_encode }}")));
        assertEquals("a=1&b=2&c=3", theResultOf(stringResource("{{ {'a':'1','b':'2','c':'3'}|url_encode }}")));
        assertEquals("", theResultOf(stringResource("{{ obj|url_encode }}")));
    }
}