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

package org.jtwig.acceptance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.jtwig.JtwigModelMap;
import org.jtwig.JtwigTemplate;
import org.junit.Test;

public class TernaryOperatorTest {
    @Test
    public void testTrue() throws Exception {
        JtwigModelMap model = new JtwigModelMap();

        String result = JtwigTemplate
            .inlineTemplate("{{ true ? 1 : 2 }}")
            .render(model);

        assertThat(result, is(equalTo("1")));
    }

    @Test
    public void testTemplateTrue() throws Exception {
        JtwigModelMap model = new JtwigModelMap();
        model.withModelAttribute("value", true);

        String result = JtwigTemplate
            .inlineTemplate("{{ value ? '1' : '2' }}")
            .render(model);

        assertThat(result, is(equalTo("1")));
    }

    @Test
     public void testFalse() throws Exception {
        JtwigModelMap model = new JtwigModelMap();

        String result = JtwigTemplate
            .inlineTemplate("{{ false ? 1 : 2 }}")
            .render(model);

        assertThat(result, is(equalTo("2")));
    }
}
