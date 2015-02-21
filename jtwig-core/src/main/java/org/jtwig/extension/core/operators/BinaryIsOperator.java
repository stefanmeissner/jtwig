/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.jtwig.extension.core.operators;

import com.google.common.base.Function;
import org.jtwig.Environment;
import org.jtwig.compile.CompileContext;
import org.jtwig.exception.CalculateException;
import org.jtwig.exception.CompileException;
import org.jtwig.expressions.api.Expression;
import org.jtwig.expressions.model.FunctionElement;
import org.jtwig.expressions.model.Variable;
import org.jtwig.extension.api.operator.BinaryOperator;
import org.jtwig.parser.model.JtwigPosition;
import org.jtwig.parser.parboiled.JtwigExpressionParser;
import org.jtwig.render.RenderContext;
import static org.jtwig.util.TypeUtil.*;
import org.parboiled.Rule;

public class BinaryIsOperator extends BinaryOperator {
    private final CompositionExpressionFactory delegate = new CompositionExpressionFactory();
    private final Function<Object, Object> function = isTrueFunction();

    public BinaryIsOperator(String name, int precedence) {
        super(name, precedence);
    }

    @Override
    public Expression compile(Environment env, JtwigPosition pos, CompileContext ctx, Object... args) throws CompileException {
        Expression left = (Expression)args[0];
        Expression right = (Expression)args[1];
        
        return new Compiled(delegate.expression(pos, left, right), function);
    }

    @Override
    public Object render(RenderContext ctx, JtwigPosition pos, Object left, Object right) throws CalculateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rule getRightSideRule(JtwigExpressionParser expr) {
        return expr.FirstOf(
                expr.functionWithBrackets(),
                expr.functionWithTwoWordsAsName(),
                expr.variable()
        );
    }
    
    
    
    
    
    
    

//    @Override
//    public Boolean render(RenderContext ctx, JtwigPosition pos, Object left, Object right) {
//        return toBoolean(left) == toBoolean(right);
//    }
    
    
    
    private static Function<Object, Object> isTrueFunction() {
        return new Function<Object, Object>() {
            @Override
            public Object apply(Object input) {
                return isBoolean(input);
            }
        };
    }
    

    private class Compiled implements Expression {

        private final Expression expression;
        private final Function<Object, Object> function;

        public Compiled(Expression expression, Function<Object, Object> function) {
            this.expression = expression;
            this.function = function;
        }

        @Override
        public Object calculate(RenderContext context) throws CalculateException {
            return function.apply(expression.calculate(context));
        }
    }
    


    public class CompositionExpressionFactory {

        public Expression expression(JtwigPosition position, Expression left, Expression right) throws CompileException {
            if (right instanceof Variable.Compiled) {
                return function(((Variable.Compiled) right).toFunction(), left);
            } else if (right instanceof FunctionElement.Compiled) {
                return function((FunctionElement.Compiled) right, left);
            } else {
                throw new CompileException(position + ": Composition always requires a function to execute as the right argument");
            }
        }

        private Expression function(FunctionElement.Compiled compiled, Expression left) {
            return compiled.cloneAndAddArgument(left);
        }
    }
}
