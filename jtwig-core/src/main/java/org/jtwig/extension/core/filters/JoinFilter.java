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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.jtwig.extension.api.filters.Filter;
import org.jtwig.types.Undefined;
import org.jtwig.util.ObjectIterator;

public class JoinFilter implements Filter {

    @Override
    public Object evaluate(Object left, Object... args) {
        if (left == null || left == Undefined.UNDEFINED) {
            return null;
        }
        
        if (left instanceof CharSequence) {
            return left;
        }
        
        if (!(left instanceof Collection) && !(left instanceof Map)
                && !left.getClass().isArray()) {
            return null;
        }
        
        List<String> pieces = new ArrayList<>();
        ObjectIterator iterator = new ObjectIterator(left);
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (next == null) {
                pieces.add("");
            } else {
                pieces.add(next.toString());
            }
        }
        return StringUtils.join(pieces, args.length > 0 ? args[0].toString() : "");
    }
    
}