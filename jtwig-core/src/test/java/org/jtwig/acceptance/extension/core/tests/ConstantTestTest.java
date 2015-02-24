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

package org.jtwig.acceptance.extension.core.tests;

import org.jtwig.AbstractJtwigTest;

public class ConstantTestTest extends AbstractJtwigTest {

    @Test
    public void constant() throws Exception {
        assertTrue(underTest.isEqualToConstant("value", getClass().getName() + ".STATIC"));
    }

    @Test
    public void invalidConstant() throws Exception {
        expectedException.expect(FunctionException.class);
        expectedException.expectMessage(equalTo("Invalid constant specified 'UnknownClass'"));

        underTest.isEqualToConstant("value", "UnknownClass");
    }

    @Test
    public void undefinedConstant() throws Exception {
        expectedException.expect(FunctionException.class);
        expectedException.expectMessage(equalTo("Constant 'UnknownClass.TEST' does not exist"));

        underTest.isEqualToConstant("value", "UnknownClass.TEST");
    }
}