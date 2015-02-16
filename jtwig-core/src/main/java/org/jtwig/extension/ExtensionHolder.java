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
import org.jtwig.addons.Addon;
import org.jtwig.addons.concurrent.ConcurrentAddon;
import org.jtwig.addons.filter.FilterAddon;
import org.jtwig.addons.spaceless.SpacelessAddon;
import org.jtwig.extension.core.CoreJtwigExtension;
import org.jtwig.extension.operator.Operator;

/**
 * The ExtensionHolder contains all information pertaining to the registration
 * of extensions and individual globals, operators, filters, functions, tests,
 * token parsers, and node visitors. Note that, in accordance with
 * http://twig.sensiolabs.org/doc/advanced.html#overloading, individually
 * registered globals, filters, etc take precedence over those registered in
 * extensions. That is, extensions and their components are registered first,
 * followed by individual components.
 */
public class ExtensionHolder {
    private final Collection<Class<? extends Addon>> addons = new ArrayList<>();
    private final Collection<Extension> extensions = new ArrayList<>();
    private final Map<String, Object> globals = new HashMap<>();
    private final Map<String, Operator> unaryOperators = new HashMap<>();
    private final Map<String, Operator> binaryOperators = new HashMap<>();
    private final Map<String, SimpleFilter> filters = new HashMap<>();
    private final Map<String, SimpleFunction> functions = new HashMap<>();
    private final Map<String, SimpleTest> tests = new HashMap<>();
//    private final Collection<TokenParser> tokenParsers = new ArrayList<>();
//    private final Collection<NodeVisitor> nodeVisitors = new ArrayList<>();
    
    public ExtensionHolder() {
        this.addAddon(SpacelessAddon.class)
                .addAddon(FilterAddon.class)
                .addAddon(ConcurrentAddon.class)
        ;
        this.addExtension(new CoreJtwigExtension());
    }
    
    public ExtensionHolder addAddon (final Class<? extends Addon> addon) {
        addons.add(addon);
        return this;
    }
    public Collection<Class<? extends Addon>> getAddons() {
        return addons;
    }
    
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
        Map<String, Object> globals = getGlobals();
        if (globals.containsKey(name)) {
            return globals.get(name);
        }
        return null;
    }
    public Map<String, Object> getGlobals() {
        Map<String, Object> result = new HashMap<>();
        for (Extension ex : extensions) {
            result.putAll(ex.getGlobals());
        }
        result.putAll(globals);
        return result;
    }
    
    public ExtensionHolder addUnaryOperator(final Operator operator) {
        unaryOperators.put(operator.getName(), operator);
        return this;
    }
    public Operator getUnaryOperator(final String name) {
        Map<String, Operator> unaryOperators = getUnaryOperators();
        if (unaryOperators.containsKey(name)) {
            return unaryOperators.get(name);
        }
        return null;
    }
    public Map<String, Operator> getUnaryOperators() {
        Map<String, Operator> result = new HashMap<>();
        for (Extension ex : extensions) {
            result.putAll(ex.getUnaryOperators());
        }
        result.putAll(unaryOperators);
        return result;
    }
    public ExtensionHolder addBinaryOperator(final Operator operator) {
        binaryOperators.put(operator.getName(), operator);
        return this;
    }
    public Operator getBinaryOperator(final String name) {
        Map<String, Operator> binaryOperators = getBinaryOperators();
        if (binaryOperators.containsKey(name)) {
            return binaryOperators.get(name);
        }
        return null;
    }
    public Map<String, Operator> getBinaryOperators() {
        Map<String, Operator> result = new HashMap<>();
        for (Extension ex : extensions) {
            result.putAll(ex.getBinaryOperators());
        }
        result.putAll(binaryOperators);
        return result;
    }
    
    public ExtensionHolder addFilter(final SimpleFilter filter) {
        filters.put(filter.getName(), filter);
        return this;
    }
    public SimpleFilter getFilter(final String name) {
        Map<String, SimpleFilter> filters = getFilters();
        if (filters.containsKey(name)) {
            return filters.get(name);
        }
        return null;
    }
    public Map<String, SimpleFilter> getFilters() {
        Map<String, SimpleFilter> result = new HashMap<>();
        for (Extension ex : extensions) {
            result.putAll(ex.getFilters());
        }
        result.putAll(filters);
        return result;
    }
    
    
    public ExtensionHolder addFunction(final SimpleFunction function) {
        functions.put(function.getName(), function);
        return this;
    }
    public SimpleFunction getFunction(final String name) {
        Map<String, SimpleFunction> functions = getFunctions();
        if (functions.containsKey(name)) {
            return functions.get(name);
        }
        return null;
    }
    public Map<String, SimpleFunction> getFunctions() {
        Map<String, SimpleFunction> result = new HashMap<>();
        for (Extension ex : extensions) {
            result.putAll(ex.getFunctions());
        }
        result.putAll(functions);
        return result;
    }
    
    public ExtensionHolder addTest(final SimpleTest test) {
        tests.put(test.getName(), test);
        return this;
    }
    public SimpleTest getTest(final String name) {
        Map<String, SimpleTest> tests = getTests();
        if (tests.containsKey(name)) {
            return tests.get(name);
        }
        return null;
    }
    public Map<String, SimpleTest> getTests() {
        Map<String, SimpleTest> result = new HashMap<>();
        for (Extension ex : extensions) {
            result.putAll(ex.getTests());
        }
        result.putAll(tests);
        return result;
    }
}