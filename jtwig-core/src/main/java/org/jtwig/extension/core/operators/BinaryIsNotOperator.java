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

package org.jtwig.extension.core.operators;

import com.google.common.base.Function;
import org.jtwig.Environment;
import org.jtwig.compile.CompileContext;
import org.jtwig.exception.CalculateException;
import org.jtwig.exception.CompileException;
import org.jtwig.expressions.api.Expression;
import org.jtwig.expressions.model.Variable;
import org.jtwig.extension.model.TestCall;
import org.jtwig.parser.model.JtwigPosition;
import org.jtwig.parser.parboiled.JtwigExpressionParser;
import org.jtwig.render.RenderContext;
import static org.jtwig.util.TypeUtil.isBoolean;
import org.parboiled.Rule;

public class BinaryIsNotOperator extends BinaryIsOperator {
    public BinaryIsNotOperator(String name, int precedence) {
        super(name, precedence);
    }

    @Override
    public Expression compile(Environment env, JtwigPosition pos, CompileContext ctx, Object... args) throws CompileException {
        Expression left = (Expression)args[0];
        Expression right = (Expression)args[1];
        
        
        if (!(right instanceof TestCall.Compiled)) {
            throw new CompileException("Is operator expects a test as the right-side operand");
        }
        return ((TestCall.Compiled)right).withLeft(left);
    }

    @Override
    public Object render(RenderContext ctx, JtwigPosition pos, Object left, Object right) throws CalculateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Rule getRightSideRule(JtwigExpressionParser expr, Environment env) {
        String[] identifiers = env.getExtensions().getTests().keySet().toArray(new String[0]);
        return expr.callable(TestCall.class, identifiers);
    }
}