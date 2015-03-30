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
import java.util.List;
import org.jtwig.extension.api.filters.Filter;
import org.jtwig.util.ObjectIterator;
import org.jtwig.util.TypeUtil;

public class BatchFilter implements Filter {

    @Override
    public Object evaluate(Object left, Object... args) {
        assert args.length >= 1;
        
        long groupSize = TypeUtil.toLong(args[0]);
        Object padding = args.length > 1 ? args[1] : null;
        
        ObjectIterator iterator = new ObjectIterator(left);
        List<List<Object>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            List<Object> batch = new ArrayList<>();
            for (int i = 0; i < groupSize; i++) {
                if (iterator.hasNext()) {
                    batch.add(iterator.next());
                } else if (padding != null) {
                    batch.add(padding);
                }
            }
            result.add(batch);
        }
        return result;
    }
}