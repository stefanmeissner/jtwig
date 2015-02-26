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

package org.jtwig.acceptance.extension.core.tokenparsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.jtwig.AbstractJtwigTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ForExpressionTest extends AbstractJtwigTest {
    @Test
    public void generalTests() throws Exception {
        assertEquals("", theResultOf(stringResource("{{ for item in [] %}{{ item }}{% endfor %}")));
        assertEquals("abc", theResultOf(stringResource("{{ for item in ['a','b','c'] %}{{ item }}{% endfor %}")));
        assertEquals("333", theResultOf(stringResource("{{ for item in ['a','b','c'] %}{{ loop.length }}{% endfor %}")));
        assertEquals("0a", theResultOf(stringResource("{{ for key, value in ['a'] %}{{ key }}{{ value }}{% endfor %}")));
        assertEquals("alt", theResultOf(stringResource("{{ for key, value in [] %}{{ key }}{{ value }}{% else %}alt{% endfor %}")));
        assertEquals("one = 1|two = 2|three = 3|", theResultOf(stringResource("{% for key, value in {'one': '1', 'two': '2', 'three': '3' %}{{ key }} = {{ value }}|{% endfor %}")));
        assertEquals("ab", theResultOf(stringResource("{% for value in list %}{{ value }}{% else %}nothing{% endfor %}")));
    }

    @Test
    public void forLoopMustExposeTheLoopVariable () throws Exception {
        model.withModelAttribute("list", Arrays.asList("a","b","c","d","e"));
        withResource("{% for item in list %}" +
                "{% if loop.first %}First {% elseif loop.last %}Last{% else %}I: {{ loop.index0 }} R: {{ loop.revindex0 }} {% endif %}" +
                "{% endfor %}");
        assertThat(theResult(), is("First I: 1 R: 3 I: 2 R: 2 I: 3 R: 1 Last"));
    }

    @Test
    public void ensureProperLoopVariableIndexing () throws Exception {
        model.withModelAttribute("list", Arrays.asList("a","b","c","d","e"));
        withResource("{% for item in list %}" +
                "{{ loop.index0 }}{{ loop.index }}{{ loop.revindex0 }}{{ loop.revindex }} " +
                "{% endfor %}");
        assertThat(theResult(), is("0145 1234 2323 3412 4501 "));
    }
    
    @Test
    public void iterateOnSequence () throws Exception {
        model.withModelAttribute("start", 'b')
                .withModelAttribute("end", 'l');
        withResource("{% for value in start..end %}{{ value }}{% endfor %}");
        assertThat(theResult(), is("bcdefghijkl"));
    }
    
    public static class Obj {
        private final List<String> list = new ArrayList<String>(){{
            add("a");
            add("b");
        }};

        public List<String> getList(String name) {
            return list;
        }
    }
}
