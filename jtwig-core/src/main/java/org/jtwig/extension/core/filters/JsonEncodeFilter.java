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

import org.jtwig.extension.api.filters.Filter;
import org.jtwig.functions.config.JsonConfiguration;
import org.jtwig.types.Undefined;

public class JsonEncodeFilter implements Filter {
    private final JsonConfiguration jsonConfiguration;
    
    public JsonEncodeFilter() {
        this(new JsonConfiguration());
    }
    public JsonEncodeFilter(JsonConfiguration jsonConfiguration) {
        this.jsonConfiguration = jsonConfiguration;
    }

    @Override
    public Object evaluate(Object left, Object... args) {
        if (left == null || left == Undefined.UNDEFINED) {
            return null;
        }
        return jsonConfiguration.jsonMapper().apply(left);
    }
    
}