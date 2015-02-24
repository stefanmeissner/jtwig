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

package org.jtwig.extension.core.tests;

import static java.lang.Class.forName;
import org.jtwig.extension.api.test.Test;

public class ConstantTest implements Test {

    @Override
    public boolean evaluate(Object... args) {
        assert args.length == 2;
        
        Object value = args[0];
        String constant = args[1].toString();
        
        int constantNamePosition = constant.lastIndexOf(".");
        if (constantNamePosition == -1)
//            throw new FunctionException(String.format("Invalid constant specified '%s'", constant));
            return false;

        String className = constant.substring(0, constantNamePosition);
        String constantName = constant.substring(constantNamePosition + 1);

        try {
            return args[0].equals(forName(className).getDeclaredField(constantName).get(null));
        } catch (Exception e) {
//            throw new FunctionException(String.format("Constant '%s' does not exist", constant));
            return false;
        }
    }

}