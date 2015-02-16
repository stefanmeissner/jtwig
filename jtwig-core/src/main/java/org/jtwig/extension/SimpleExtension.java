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

package org.jtwig.extension;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.jtwig.Environment;
import org.jtwig.extension.operator.Operator;

public abstract class SimpleExtension implements Extension {

    @Override
    public abstract String getName();

    @Override
    public void init(final Environment env) {}

    @Override
    public Map<String, Object> getGlobals() {
        return Collections.EMPTY_MAP;
    }

    @Override
    public Map<String, Operator> getUnaryOperators() {
        return Collections.EMPTY_MAP;
    }
    @Override
    public Map<String, Operator> getBinaryOperators() {
        return Collections.EMPTY_MAP;
    }

    @Override
    public Map<String, SimpleFunction> getFunctions() {
        return Collections.EMPTY_MAP;
    }

    @Override
    public Map<String, SimpleFilter> getFilters() {
        return Collections.EMPTY_MAP;
    }

    @Override
    public Map<String, SimpleTest> getTests() {
        return Collections.EMPTY_MAP;
    }

    @Override
    public Collection<TokenParser> getTokenParsers() {
        return Collections.EMPTY_LIST;
    }
    
    
    
}