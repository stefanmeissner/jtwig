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

package org.jtwig.extension.spring.filters;

import javax.servlet.http.HttpServletRequest;
import org.jtwig.extension.api.filters.Filter;
import org.jtwig.extension.api.filters.FilterException;
import org.jtwig.util.LocalThreadHolder;

public abstract class AbstractSpringFilter implements Filter {

    @Override
    public Object evaluate(Object left, Object... args) throws FilterException {
        HttpServletRequest request = LocalThreadHolder.getServletRequest();
        return evaluate(request, left, args);
    }
    
    public abstract Object evaluate(HttpServletRequest request, Object left, Object... args) throws FilterException;
    
}