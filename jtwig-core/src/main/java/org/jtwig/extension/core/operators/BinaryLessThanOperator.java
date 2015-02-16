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

import org.jtwig.extension.operator.BinaryOperator;
import org.jtwig.parser.model.JtwigPosition;
import org.jtwig.render.RenderContext;
import static org.jtwig.util.TypeUtil.*;

public class BinaryLessThanOperator extends BinaryOperator {

    public BinaryLessThanOperator(String name, int precedence) {
        super(name, precedence);
    }

    @Override
    public Boolean render(RenderContext ctx, JtwigPosition pos, Object left, Object right) {
        if (isDecimal(left) || isDecimal(right)) {
            return toDecimal(left).compareTo(toDecimal(right)) < 0;
        }
        return toLong(left) < toLong(right);
    }
    
}