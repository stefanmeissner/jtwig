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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ArrayUtilTest {
    @Test
    public void testRange() throws Exception {
        assertArrayEquals(new Character[]{'a','b','c'}, (Character[])ArrayUtil.range("a", "c"));

        assertArrayEquals(new Long[]{1L,2L,3L}, (Long[])ArrayUtil.range(1, 3));
        assertArrayEquals(new Long[]{1L,2L,3L}, (Long[])ArrayUtil.range("1", 3));
        assertArrayEquals(new Long[]{1L,2L,3L}, (Long[])ArrayUtil.range(1, "3"));
        assertArrayEquals(new Long[]{1L,2L,3L}, (Long[])ArrayUtil.range("1", "3"));

        assertArrayEquals(new Long[]{0L,1L,2L,3L}, (Long[])ArrayUtil.range("a", 3));
        assertArrayEquals(new Long[]{1L,0L}, (Long[])ArrayUtil.range(1, "c"));

        assertArrayEquals(new Long[]{0L,1L,2L,3L}, (Long[])ArrayUtil.range(null, 3));
        assertArrayEquals(new Long[]{3L,2L,1L,0L}, (Long[])ArrayUtil.range(3, null));
        assertArrayEquals(new Long[]{0L}, (Long[])ArrayUtil.range(null, "c"));
        assertArrayEquals(new Long[]{0L}, (Long[])ArrayUtil.range("c", null));

        assertArrayEquals(new Long[]{1L,2L,3L}, (Long[])ArrayUtil.range(new Object(), 3));
        assertArrayEquals(new Long[]{3L,2L,1L}, (Long[])ArrayUtil.range(3, new Object()));
        assertArrayEquals(new Long[]{1L,0L}, (Long[])ArrayUtil.range(new Object(), "c"));
        assertArrayEquals(new Long[]{0L,1L}, (Long[])ArrayUtil.range("c", new Object()));

        assertNull(ArrayUtil.range(1.1, 1.3));
        assertArrayEquals(new BigDecimal[]{new BigDecimal("1.1"), new BigDecimal("2.1")}, (BigDecimal[])ArrayUtil.range(1.1, 2.3));
        assertArrayEquals(new BigDecimal[]{new BigDecimal("1.1"), new BigDecimal("2.1")}, (BigDecimal[])ArrayUtil.range("1.1", 2.3));
        assertArrayEquals(new BigDecimal[]{new BigDecimal("1.1"), new BigDecimal("0.1")}, (BigDecimal[])ArrayUtil.range("1.1", "d"));

        assertArrayEquals(new BigDecimal[]{new BigDecimal("3.1"), new BigDecimal("4.3"), new BigDecimal("5.5"), new BigDecimal("6.7")}, (BigDecimal[])ArrayUtil.range(3.1, 7.2, 1.2));
        assertArrayEquals(new Character[]{'Z','[','\\',']','^','_','`','a'}, (Character[])ArrayUtil.range("Z", "a"));
        assertArrayEquals(new Long[]{0L}, (Long[])ArrayUtil.range("AZ", ""));
        
        assertNull(ArrayUtil.range(1,2,3));
        assertNull(ArrayUtil.range('a','b',3));
    }
    
    @Test
    public void testToArrays() throws Exception {
        assertTrue(ArrayUtil.toArray(new byte[]{0xe}) instanceof Byte[]);
        assertFalse(!ArrayUtil.toArray(new byte[]{0xe}).getClass().getComponentType().isPrimitive());
        
        assertTrue(ArrayUtil.toArray(new short[]{5}) instanceof Short[]);
        assertFalse(!ArrayUtil.toArray(new short[]{5}).getClass().getComponentType().isPrimitive());
        
        assertTrue(ArrayUtil.toArray(new int[]{5}) instanceof Integer[]);
        assertFalse(!ArrayUtil.toArray(new int[]{5}).getClass().getComponentType().isPrimitive());
        
        assertTrue(ArrayUtil.toArray(new long[]{5L}) instanceof Long[]);
        assertFalse(!ArrayUtil.toArray(new long[]{5L}).getClass().getComponentType().isPrimitive());
        
        assertTrue(ArrayUtil.toArray(new boolean[]{false}) instanceof Boolean[]);
        assertFalse(!ArrayUtil.toArray(new boolean[]{false}).getClass().getComponentType().isPrimitive());
        
        assertTrue(ArrayUtil.toArray(new char[]{0xe}) instanceof Character[]);
        assertFalse(!ArrayUtil.toArray(new char[]{0xe}).getClass().getComponentType().isPrimitive());
        
        assertTrue(ArrayUtil.toArray(new float[]{1F}) instanceof Float[]);
        assertFalse(!ArrayUtil.toArray(new float[]{1F}).getClass().getComponentType().isPrimitive());
        
        assertTrue(ArrayUtil.toArray(new double[]{1D}) instanceof Double[]);
        assertFalse(!ArrayUtil.toArray(new double[]{1D}).getClass().getComponentType().isPrimitive());
        
        assertTrue(ArrayUtil.toArray(new Object[]{new Object()}) instanceof Double[]);
        assertFalse(!ArrayUtil.toArray(new double[]{1D}).getClass().getComponentType().isPrimitive());
        
        assertTrue(ArrayUtil.toArray((Object)new Object[]{new Object()}) instanceof Double[]);
        assertFalse(!ArrayUtil.toArray((Object)new double[]{1D}).getClass().getComponentType().isPrimitive());
    }
}