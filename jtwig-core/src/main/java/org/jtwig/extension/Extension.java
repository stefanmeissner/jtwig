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
import java.util.Map;
import org.jtwig.Environment;

public interface Extension {
    /**
     * Returns the name of the extension.
     * @return 
     */
    String getName();
    /**
     * A place for the extension to perform any initialization procedures it
     * requires.
     * @param env The environment under which the extension is registered.
     */
    void init(Environment env);
    /**
     * Returns a list of global variables registered by the extension.
     * @return A map of global variables, indexed by name.
     */
    Map<String, Object> getGlobals();
    /**
     * Returns a collection of operators registered by the extension.
     * @return A collection of operators.
     */
    Collection<SimpleOperator> getOperators();
    /**
     * Returns a collection of functions registered by the extension.
     * @return A collection of functions.
     */
    Collection<SimpleFunction> getFunctions();
    /**
     * Returns a collection of filters registered by the extension.
     * @return A collection of filters.
     */
    Collection<SimpleFilter> getFilters();
    /**
     * Returns a collection of tests registered by the extension.
     * @return A collection of tests.
     */
    Collection<SimpleTest> getTests();
//    /**
//     * Returns a collection of token parser instances registered by the
//     * extension.
//     * @return A collection of token parser instances.
//     */
//    Collection<TokenParser> getTokenParsers();
//    /**
//     * Returns a collection of node visitor instances registered by the
//     * extension.
//     * @return A collection of node visitor instances.
//     */
//    Collection<NodeVisitor> getNodeVisitors();

}