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

package org.jtwig.expressions.model;

import org.jtwig.compile.CompileContext;
import org.jtwig.exception.CompileException;
import org.jtwig.expressions.api.CompilableExpression;
import org.jtwig.expressions.api.Expression;
import org.jtwig.extension.api.operator.Operator;
import org.jtwig.parser.model.JtwigPosition;

public class OperationUnary extends AbstractCompilableExpression {
//    public static class Builder implements CompilableExpression {
//        private JtwigPosition position;
//        private String operator;
//        private CompilableExpression operand;
//
//        public Builder withPosition(JtwigPosition position) {
//            this.position = position;
//            return this;
//        }
//
//        public Builder withOperator(String operator) {
//            this.operator = operator;
//            return this;
//        }
//
//        public Builder withOperand(CompilableExpression operand) {
//            this.operand = operand;
//            return this;
//        }
//
//        public OperationUnary build () {
//            return new OperationUnary(position, operator, operand);
//        }
//
//        @Override
//        public Expression compile(CompileContext context) throws CompileException {
//            return null;
//        }
//    }

    private final String operator;
    private CompilableExpression operand;

    public OperationUnary(JtwigPosition position, String operator) {
        super(position);
        this.operator = operator;
    }
    
    public OperationUnary withOperand(CompilableExpression operand) {
        this.operand = operand;
        return this;
    }

    @Override
    public Expression compile(CompileContext context) throws CompileException {
        Operator op = context.environment()
                .getExtensions()
                .getUnaryOperator(operator);
        if (op == null) {
            throw new CompileException(position()+": Could not find operator '"+operator+"'.");
        }
        return op.compile(context.environment(), position(), context, operand.compile(context));
    }
}
