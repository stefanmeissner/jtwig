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

import org.jtwig.Environment;
import org.jtwig.compile.CompileContext;
import org.jtwig.exception.CompileException;
import org.jtwig.expressions.api.Expression;
import org.jtwig.expressions.model.FunctionElement;
import org.jtwig.expressions.model.Variable;
import org.jtwig.extension.operator.BinaryOperator;
import org.jtwig.parser.model.JtwigPosition;
import org.jtwig.render.RenderContext;

public class BinaryCompositionOperator extends BinaryOperator {

    public BinaryCompositionOperator(String name, int precedence) {
        super(name, precedence);
    }

    @Override
    public Expression compile(Environment env, JtwigPosition pos, CompileContext ctx, Object... args) throws CompileException {
        assert args.length == 2;

        final Expression left = (Expression)args[0];
        final Expression right = (Expression)args[1];
        if (right instanceof Variable.Compiled) {
            return function(((Variable.Compiled) right).toFunction(), left);
        }
        if (right instanceof FunctionElement.Compiled) {
            return function((FunctionElement.Compiled) right, left);
        }
        throw new CompileException(pos + ": Composition always requires a function to execute as the right argument");
    }
    
    private Expression function(FunctionElement.Compiled compiled, Expression left) {
        return compiled.cloneAndAddArgument(left);
    }

    @Override
    public Object render(RenderContext ctx, JtwigPosition pos, Object left, Object right) {
        throw new UnsupportedOperationException();
    }
    
}