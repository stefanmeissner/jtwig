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

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jtwig.extension.api.filters.Filter;
import org.jtwig.extension.api.filters.FilterException;
import org.jtwig.functions.annotations.JtwigFunction;
import org.jtwig.functions.annotations.Parameter;
import org.jtwig.types.Undefined;
import org.jtwig.util.TypeUtil;

public class LastFilter implements Filter {

    @Override
    public Object evaluate(Object left, Object... args) throws FilterException {
        if (left == null || left instanceof Undefined) {
            return null;
        }
        if (left instanceof List) {
            // Lists are easy, so let's handle them early
            if (((List)left).isEmpty()) {
                return null;
            }
            return ((List)left).get(((List)left).size()-1);
        }
        if (left instanceof Map) {
            left = ((Map)left).values();
        }
        if (left instanceof Collection) {
            if (((Collection)left).isEmpty()) {
                return null;
            }
            Iterator it = ((Collection)left).iterator();
            while (it.hasNext()) {
                Object obj = it.next();
                if (!it.hasNext()) {
                    return obj;
                }
            }
            return null;
        }
        if (left instanceof CharSequence) {
            if (((CharSequence)left).length() == 0) {
                return null;
            }
            return ((CharSequence)left).charAt(((CharSequence)left).length()-1);
        }
        if (TypeUtil.isLong(left)) {
            String str = TypeUtil.toLong(left).toString().trim();
            return str.charAt(str.length()-1);
        }
        if (TypeUtil.isDecimal(left)) {
            String str = TypeUtil.toDecimal(left).toString().trim();
            return str.charAt(str.length()-1);
        }
        if (TypeUtil.isBoolean(left)) {
            return left;
        }
        throw new FilterException("Object of type "+left.getClass().getName()+" could not be converted to string");
    }

    @JtwigFunction(name = "last")
    public Object last (@Parameter List input) {
        if (input.isEmpty()) return null;
        return input.get(input.size() - 1);
    }
    @JtwigFunction(name = "last")
    public Object last (@Parameter Map input) {
        if (input.isEmpty()) return null;
        Iterator iterator = input.keySet().iterator();
        Object key = iterator.next();
        while (iterator.hasNext()) key = iterator.next();
        return input.get(key);
    }
    @JtwigFunction(name = "last")
    public Object last (@Parameter Object input) {
        if (input == null) return input;
        if (input.getClass().isArray()) {
            int length = Array.getLength(input);
            if (length == 0) return null;
            return Array.get(input, length - 1);
        } else return input;
    }
    @JtwigFunction(name = "last")
    public Character last (@Parameter String input) {
        if (input.isEmpty()) return null;
        return input.charAt(input.length() - 1);
    }
    
}