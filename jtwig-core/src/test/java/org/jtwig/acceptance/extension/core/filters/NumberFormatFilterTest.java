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

package org.jtwig.acceptance.extension.core.filters;

import org.jtwig.AbstractJtwigTest;
import org.jtwig.functions.exceptions.FunctionException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NumberFormatFilterTest extends AbstractJtwigTest {
    
    @Test
    public void generalTests() throws Exception {
        assertEquals("1,234.57", theResultOf(stringResource("{{ '1234.5678'|number_format(2, '.', ',') }}")));
        assertEquals("1234.57", theResultOf(stringResource("{{ '1234.5678'|number_format(2, '.') }}")));
        assertEquals("1234.57", theResultOf(stringResource("{{ '1234.5678'|number_format(2) }}")));
        assertEquals("1234.568", theResultOf(stringResource("{{ '1234.5678'|number_format }}")));
    }
    
//    @Test
//    public void numberFormat() throws FunctionException {
//        assertEquals("1,234.57", underTest.numberFormat(1234.5678, 2, ".", ","));
//    }
//    @Test
//    public void numberFormatWithoutGrouping() throws FunctionException {
//        assertEquals("1234.57", underTest.numberFormat(1234.5678, 2, "."));
//    }
//
//    @Test
//    public void numberFormatWithoutDecimalSeparator() throws FunctionException {
//        assertEquals("1234.57", underTest.numberFormat(1234.5678, 2));
//    }
//    @Test
//    public void numberFormatDefault() throws FunctionException {
//        assertEquals("1234.568", underTest.numberFormat(1234.5678));
//    }
}