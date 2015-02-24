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
import org.jtwig.exception.CalculateException;
import org.jtwig.exception.CompileException;
import org.jtwig.expressions.api.Expression;
import org.jtwig.expressions.model.FunctionElement;
import org.jtwig.expressions.model.Variable;
import org.jtwig.extension.api.operator.BinaryOperator;
import org.jtwig.parser.model.JtwigPosition;
import org.jtwig.parser.parboiled.JtwigExpressionParser;
import org.jtwig.render.RenderContext;
import org.jtwig.types.Undefined;
import static org.jtwig.types.Undefined.UNDEFINED;
import org.jtwig.util.ObjectExtractor;
import org.parboiled.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinarySelectionOperator extends BinaryOperator {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinarySelectionOperator.class);
    
    public BinarySelectionOperator(final String name, final int precedence) {
        super(name, precedence);
    }
    
    @Override
    public Rule getRightSideRule(JtwigExpressionParser expr, Environment env) {
        return expr.FirstOf(
                expr.functionWithBrackets(),
                expr.mapEntry(),
                expr.variable()
        );
    }

    @Override
    public Expression compile(final Environment env, final JtwigPosition pos,
            final CompileContext ctx, final Object... args)
            throws CompileException {
        assert args.length == 2;

        final Expression left = (Expression)args[0];
        final Expression right = (Expression)args[1];
        return new BinaryCallback(pos, left, right);
    }
    
    @Override
    public Object render(RenderContext ctx, JtwigPosition pos, Object left, Object right) throws CalculateException {
        String name;
        
        // Check the right side first, since that's a syntax issue and arguably
        // more important
        if (right instanceof Variable.Compiled) {
            name = ((Variable.Compiled)right).name();
        } else if (right instanceof FunctionElement.Compiled) {
            name = ((FunctionElement.Compiled)right).name();
        } else {
            throw new CalculateException("Selection operator must be given a variable/function as right argument");
        }
        
        if (left == null) {
            LOGGER.warn("Left hand argument is null. Right side is "+name);
            if (!ctx.environment().isStrictMode()) {
                return Undefined.UNDEFINED;
            }
            throw new CalculateException(String.format(pos + ": Impossible to access attribute/method '%s' on %s", name, getName(left)));
        }
        ObjectExtractor extractor = new ObjectExtractor(ctx, left);
        try {
            if (right instanceof Variable.Compiled) {
                return ((Variable.Compiled) right).extract(extractor);
            }
            if (right instanceof FunctionElement.Compiled) {
                return ((FunctionElement.Compiled) right).extract(ctx, extractor);
            }
            throw new CalculateException("Selection operator must be given a variable/function as right argument");
        } catch (ObjectExtractor.ExtractException e) {
            throw new CalculateException(e);
        }
    }
    
    protected static String getName(final Object calculate) {
        if (calculate == null) {
            return "null";
        }
        if (calculate == Undefined.UNDEFINED) {
            return "undefined";
        }
        return "unknown";
    }
    
    public class BinaryCallback implements Expression {
        protected final JtwigPosition pos;
        protected final Expression left;
        protected final Expression right;
        
        public BinaryCallback(final JtwigPosition pos, final Expression left, final Expression right) {
            this.pos = pos;
            this.left = left;
            this.right = right;
        }

        @Override
        public Object calculate(final RenderContext context) throws CalculateException {
            Object calculatedLeft = null;
            try {
                calculatedLeft = left.calculate(context);
            } catch (CalculateException ex) {}

            if (calculatedLeft == UNDEFINED)
                calculatedLeft = null;
            return BinarySelectionOperator.this.render(context, pos, calculatedLeft, right);
        }
        
    }
    
}