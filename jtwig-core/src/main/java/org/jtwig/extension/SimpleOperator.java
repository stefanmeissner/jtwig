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

public class SimpleOperator {
    private final String name;
    private final Callback callback; // !!! This should probably be built into the SimpleOperator class
    private final int precedence;
    private final Associativity associativity;
    
    public SimpleOperator(final String name, final Callback callback,
            final int precedence, final Associativity associativity) {
        this.name = name;
        this.callback = callback;
        this.precedence = precedence;
        this.associativity = associativity;
    }
    
    public String getName() {
        return name;
    }
    
    public enum Associativity {
        
    }
}