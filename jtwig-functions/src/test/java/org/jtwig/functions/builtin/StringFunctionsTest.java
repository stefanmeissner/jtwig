/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jtwig.functions.builtin;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class StringFunctionsTest {
    @Rule public ExpectedException expectedException = ExpectedException.none();

    StringFunctions underTest = new StringFunctions();











    @Test
    public void splitExecute() throws Exception {
        assertThat(underTest.split("a,b,c", ","), allOf(
                hasItem("a"),
                hasItem("b"),
                hasItem("c"),
                not(hasItem("d"))
        ));
    }

    @Test
    public void titleExecute() throws Exception {
        assertEquals(underTest.title("my first car"), "My First Car");
    }


    @Test
    public void trimShouldGiveTrimmedString() throws Exception {
        assertEquals(underTest.trim(" abc "), "abc");
    }

    @Test
    public void trimShouldGiveStringInUppercase2() throws Exception {
        assertEquals(underTest.trim("abc"), "abc");
    }

    @Test
    public void nullPointer() throws Exception {
        assertNull(underTest.trim(null));
    }


    @Test
    public void shouldGiveStringInUppercase() throws Exception {
        assertEquals(underTest.upper("abc"), "ABC");
    }

    @Test
    public void shouldGiveStringInUppercase2() throws Exception {
        assertEquals(underTest.upper("ABC"), "ABC");
    }



    @Test
    public void convertFromUtf8ToIso8859_1() throws Exception {
        assertArrayEquals(
                new String("hello".getBytes(), "ISO-8859-1").getBytes(),
                underTest.convertEncoding("hello", "UTF-8", "ISO-8859-1").getBytes());
    }
}
