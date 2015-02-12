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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class ExtensionHolder {
    private final Collection<Extension> extensions = new ArrayList<>();
    private final Map<String, Object> globals = new HashMap<>();
    private final Collection<SimpleOperator> operators = new ArrayList<>();
    private final Collection<SimpleFilter> filters = new ArrayList<>();
    private final Collection<SimpleFunction> functions = new ArrayList<>();
    private final Collection<SimpleTest> tests = new ArrayList<>();
//    private final Collection<TokenParser> tokenParsers = new ArrayList<>();
//    private final Collection<NodeVisitor> nodeVisitors = new ArrayList<>();
    
    public ExtensionHolder addExtension(final Extension extension) {
        extensions.add(extension);
        return this;
    }
    public Extension getExtension(final String name) {
        for (Extension e : extensions) {
            if (StringUtils.equals(e.getName(), name)) {
                return e;
            }
        }
        return null;
    }
    public Collection<Extension> getExtensions() {
        return extensions;
    }
    
    public ExtensionHolder addGlobal(final String name, final Object value) {
        globals.put(name, value);
        return this;
    }
    public Object getGlobal(final String name) {
        if (globals.containsKey(name)) {
            return globals.get(name);
        }
        return null;
    }
    public Map<String, Object> getGlobals() {
        return globals;
    }
    
    public ExtensionHolder addOperator(final SimpleOperator operator) {
        operators.add(operator);
        return this;
    }
    public SimpleOperator getOperator(final String name) {
        for (SimpleOperator o : operators) {
            if (StringUtils.equals(o.getName(), name)) {
                return o;
            }
        }
        return null;
    }
    public Collection<SimpleOperator> getOperators() {
        return operators;
    }
    
    public ExtensionHolder addFilter(final SimpleFilter filter) {
        filters.add(filter);
        return this;
    }
    public SimpleFilter getFilter(final String name) {
        for (SimpleFilter f : filters) {
            if (StringUtils.equals(f.getName(), name)) {
                return f;
            }
        }
        return null;
    }
    public Collection<SimpleFilter> getFilters() {
        return filters;
    }
    
    public ExtensionHolder addFunction(final SimpleFunction function) {
        functions.add(function);
        return this;
    }
    public SimpleFunction getFunction(final String name) {
        for (SimpleFunction f : functions) {
            if (StringUtils.equals(f.getName(), name)) {
                return f;
            }
        }
        return null;
    }
    public Collection<SimpleFunction> getFunctions() {
        return functions;
    }
    
    public ExtensionHolder addTest(final SimpleTest test) {
        tests.add(test);
        return this;
    }
    public SimpleTest getTest(final String name) {
        for (SimpleTest t : tests) {
            if (StringUtils.equals(t.getName(), name)) {
                return t;
            }
        }
        return null;
    }
    public Collection<SimpleTest> getTests() {
        return tests;
    }
}