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

package org.jtwig.acceptance.extension.core.tests;

import java.util.ArrayList;
import java.util.HashMap;
import org.jtwig.AbstractJtwigTest;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class EmptyTestTest extends AbstractJtwigTest {
    

    @Test
    public void isEmpty() throws Exception {
        assertTrue(underTest.isEmpty(new ArrayList<>()));
        assertTrue(underTest.isEmpty(new HashMap<>()));
        assertTrue(underTest.isEmpty(null));
        assertTrue(underTest.isEmpty(0));
    }

    @Test
    public void someOnNonEmptyIterator() throws Exception {
        assertFalse(underTest.isEmpty(nonEmptyIterator()));
    }

    @Test
    public void emptyOnEmptyIterator() throws Exception {
        assertTrue(underTest.isEmpty(emptyIterator()));
    }

    private Iterable<Object> nonEmptyIterator() {
        return new Iterable<Object>() {
            @Override
            public Iterator iterator() {
                return asList(1).iterator();
            }
        };
    }

    private Iterable<Object> emptyIterator() {
        return new Iterable<Object>() {
            @Override
            public Iterator iterator() {
                return new ArrayList<>().iterator();
            }
        };
    }
}
