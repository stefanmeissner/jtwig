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

public class BatchFilterTest extends AbstractJtwigTest {
    @Test
    public void generalTests() throws Exception {
        assertEquals("g,none,none", theResultOf(stringResource("{{ ['a','b','c','d','e','f','g']|batch(3, 'none')[2] }}")));
        assertEquals("a,b,none", theResultOf(stringResource("{{ {'1':'a','2':'b'}|batch(3, 'none')[2] }}")));
    }
    
//    @Test
//    public void batch() throws Exception {
//        withResource("{{ batch([1,2,3,4], 3) }}");
//        then(theResult(), is(equalTo("[[1, 2, 3], [4]]")));
//    }
//
//    @Test
//    public void batchWithPadding() throws Exception {
//        withResource("{{ batch([1,2,3,4], 3, 1) }}");
//        then(theResult(), is(equalTo("[[1, 2, 3], [4, 1, 1]]")));
//    }
}