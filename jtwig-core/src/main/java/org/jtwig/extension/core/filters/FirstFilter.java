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

package org.jtwig.extension.core.filters;

import java.util.Collection;
import java.util.Map;
import org.jtwig.extension.api.filters.Filter;
import org.jtwig.extension.api.filters.FilterException;
import org.jtwig.types.Undefined;
import org.jtwig.util.TypeUtil;

public class FirstFilter implements Filter {

    @Override
    public Object evaluate(Object left, Object... args) throws FilterException {
        if (left == null || left instanceof Undefined) {
            return null;
        }
        if (left instanceof Collection) {
            if (((Collection)left).isEmpty()) {
                return null;
            }
            return ((Collection)left).iterator().next();
        }
        if (left instanceof Map) {
            if (((Map)left).isEmpty()) {
                return null;
            }
            return ((Map)left).values().iterator().next();
        }
        if (left instanceof CharSequence) {
            if (((CharSequence)left).length() == 0) {
                return null;
            }
            return ((CharSequence)left).charAt(0);
        }
        if (TypeUtil.isLong(left)) {
            return left.toString().charAt(0);
        }
        if (TypeUtil.isDecimal(left)) {
            return left.toString().charAt(0);
        }
        if (TypeUtil.isBoolean(left)) {
            return left;
        }
        throw new FilterException("Object of type "+left.getClass().getName()+" could not be converted to string");
    }
    
}