/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jtwig.functions.builtin;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class ObjectFunctionsTest {
    private ObjectFunctions underTest = new ObjectFunctions();

    @Test
    public void toDouble() throws Exception {
        assertEquals(underTest.toDouble(1), (Double) 1.0);
    }

    @Test
    public void toInt() throws Exception {
        assertEquals(underTest.toInt(2.0), (Integer) 2);
    }

    @Test
    public void last() throws Exception {
        assertEquals(underTest.last(new String[]{"a","b","c"}), "c");
        assertEquals(underTest.last(null), null);
        assertEquals(underTest.last(true), true);
    }
}
