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

package org.jtwig.acceptance.extension.core.functions;

import org.jtwig.AbstractJtwigTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BlockFunctionTest extends AbstractJtwigTest {
    
    @Test
    public void ensureBlockFunctionWorksWithVariables() throws Exception {
        theModel().withModelAttribute("var", "title");
        assertEquals("title!", theResultOf(classpathResource("templates/acceptance/block/tested.twig")));
    }
    @Test
    public void ensureBlockFunctionWorksWithVariables2() throws Exception {
        theModel().withModelAttribute("var", "body");
        assertEquals("body!", theResultOf(classpathResource("templates/acceptance/block/tested.twig")));
    }
    
}