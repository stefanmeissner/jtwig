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

package org.jtwig.extension.core.functions;

import org.apache.commons.lang3.ArrayUtils;
import org.jtwig.Environment;
import org.jtwig.compile.CompileContext;
import org.jtwig.exception.CompileException;
import org.jtwig.extension.Callback;
import org.jtwig.functions.annotations.JtwigFunction;
import org.jtwig.functions.annotations.Parameter;
import static org.jtwig.functions.util.ObjectUtils.compare;
import org.jtwig.parser.model.JtwigPosition;
import org.jtwig.parser.parboiled.JtwigExpressionParser;
import org.parboiled.Rule;

public class MaxFunction implements Callback {

    @Override
    public Object invoke(final Environment env,
            final JtwigPosition pos, final CompileContext ctx,
            Object... args) throws CompileException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rule getRightSideRule(JtwigExpressionParser expr) {
        return null;
    }
    
    @JtwigFunction(name = "max")
    public Object max (@Parameter Object ... values) {
        Object result = values[0];
        values = ArrayUtils.remove(values, 0);
        for(Object value : values) {
            int cmp = compare(result, value);
            if(cmp < 0) {
                result = value;
            }
        }
        return result;
    }
    
}