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

import java.math.BigDecimal;
import java.math.MathContext;
import org.jtwig.extension.api.operator.BinaryOperator;
import org.jtwig.parser.model.JtwigPosition;
import org.jtwig.render.RenderContext;
import org.jtwig.types.Undefined;
import org.jtwig.util.BigDecimalUtil;
import static org.jtwig.util.TypeUtil.isDecimal;
import static org.jtwig.util.TypeUtil.toDecimal;

public class BinaryExponentOperator extends BinaryOperator {

    public BinaryExponentOperator(String name, int precedence) {
        super(name, precedence);
    }

    @Override
    public Object render(RenderContext ctx, JtwigPosition pos, Object left, Object right) {
        if (left == null || left == Undefined.UNDEFINED || toDecimal(left).compareTo(BigDecimal.ZERO) == 0) {
            return 0L;
        }
        if (right == null || right == Undefined.UNDEFINED || toDecimal(right).compareTo(BigDecimal.ZERO) == 0) {
            return 1L;
        }
        BigDecimal result = BigDecimalUtil.exp(BigDecimalUtil.ln(toDecimal(left), 5).multiply(toDecimal(right)), 5).round(new MathContext(5));
        if (isDecimal(left) || isDecimal(right) || result.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0) {
            return result;
        }
        return result.longValue();
    }
}