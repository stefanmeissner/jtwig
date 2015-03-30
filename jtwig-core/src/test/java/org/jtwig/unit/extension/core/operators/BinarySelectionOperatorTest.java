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

package org.jtwig.unit.extension.core.operators;

import org.jtwig.exception.CalculateException;
import org.jtwig.expressions.api.Expression;
import org.jtwig.expressions.model.Variable;
import org.jtwig.extension.core.operators.BinarySelectionOperator;
import org.jtwig.parser.model.JtwigPosition;
import org.jtwig.types.Undefined;
import org.jtwig.unit.AbstractJtwigTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class BinarySelectionOperatorTest extends AbstractJtwigTest {
    BinarySelectionOperator underTest = new BinarySelectionOperator(".", 0);
    JtwigPosition position = new JtwigPosition(resource, 1, 1);
    
    @Test
    public void generalTest() throws Exception {
        assertEquals(Undefined.UNDEFINED, underTest.render(renderContext, position, new TestClass(), new Variable.Compiled(position, "priv")));
        assertEquals("good", underTest.render(renderContext, position, new TestClass(),  new Variable.Compiled(position, "priv2")));
        assertEquals(Undefined.UNDEFINED, underTest.render(renderContext, position, new TestClass(),  new Variable.Compiled(position, "prot")));
        assertEquals("good", underTest.render(renderContext, position, new TestClass(),  new Variable.Compiled(position, "prot2")));
        assertEquals(Undefined.UNDEFINED, underTest.render(renderContext, position, new TestClass(),  new Variable.Compiled(position, "pack")));
        assertEquals("good", underTest.render(renderContext, position, new TestClass(),  new Variable.Compiled(position, "pack2")));
        assertEquals("good", underTest.render(renderContext, position, new TestClass(),  new Variable.Compiled(position, "pub")));
    }
    
    public static class TestClass {
        private final String priv = "bad";
        private final String priv2 = "good";
        protected final String prot = "bad";
        protected final String prot2 = "good";
        final String pack = "bad";
        final String pack2 = "good";
        public final String pub = "good";
        
        public String getPriv2() {
            return priv2;
        }
        public String getProt2() {
            return prot2;
        }
        public String getPack2() {
            return pack2;
        }
    }
}