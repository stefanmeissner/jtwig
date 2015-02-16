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

package org.jtwig.unit.util;

import java.math.BigDecimal;
import org.jtwig.util.ArrayUtil;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ArrayUtilTest {
    @Test
    public void testRange() throws Exception {
        assertArrayEquals(new char[]{'a','b','c'}, (char[])ArrayUtil.range("a", "c"));

        assertArrayEquals(new long[]{1,2,3}, (long[])ArrayUtil.range(1, 3));
        assertArrayEquals(new long[]{1,2,3}, (long[])ArrayUtil.range("1", 3));
        assertArrayEquals(new long[]{1,2,3}, (long[])ArrayUtil.range(1, "3"));
        assertArrayEquals(new long[]{1,2,3}, (long[])ArrayUtil.range("1", "3"));

        assertArrayEquals(new long[]{0,1,2,3}, (long[])ArrayUtil.range("a", 3));
        assertArrayEquals(new long[]{1,0}, (long[])ArrayUtil.range(1, "c"));

        assertArrayEquals(new long[]{0,1,2,3}, (long[])ArrayUtil.range(null, 3));
        assertArrayEquals(new long[]{3,2,1,0}, (long[])ArrayUtil.range(3, null));
        assertArrayEquals(new long[]{0}, (long[])ArrayUtil.range(null, "c"));
        assertArrayEquals(new long[]{0}, (long[])ArrayUtil.range("c", null));

        assertArrayEquals(new long[]{1,2,3}, (long[])ArrayUtil.range(new Object(), 3));
        assertArrayEquals(new long[]{3,2,1}, (long[])ArrayUtil.range(3, new Object()));
        assertArrayEquals(new long[]{1,0}, (long[])ArrayUtil.range(new Object(), "c"));
        assertArrayEquals(new long[]{0,1}, (long[])ArrayUtil.range("c", new Object()));

        assertNull(ArrayUtil.range(1.1, 1.3));
        assertArrayEquals(new BigDecimal[]{new BigDecimal("1.1"), new BigDecimal("2.1")}, (BigDecimal[])ArrayUtil.range(1.1, 2.3));
        assertArrayEquals(new BigDecimal[]{new BigDecimal("1.1"), new BigDecimal("2.1")}, (BigDecimal[])ArrayUtil.range("1.1", 2.3));
        assertArrayEquals(new BigDecimal[]{new BigDecimal("1.1"), new BigDecimal("0.1")}, (BigDecimal[])ArrayUtil.range("1.1", "d"));

        assertArrayEquals(new BigDecimal[]{new BigDecimal("3.1"), new BigDecimal("4.3"), new BigDecimal("5.5"), new BigDecimal("6.7")}, (BigDecimal[])ArrayUtil.range(3.1, 7.2, 1.2));
        assertArrayEquals(new char[]{'Z','[','\\',']','^','_','`','a'}, (char[])ArrayUtil.range("Z", "a"));
        assertArrayEquals(new long[]{0L}, (long[])ArrayUtil.range("AZ", ""));
    }
}