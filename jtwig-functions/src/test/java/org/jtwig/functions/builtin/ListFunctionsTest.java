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

import org.jtwig.functions.exceptions.FunctionException;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;

public class ListFunctionsTest {
    ListFunctions underTest = new ListFunctions();


    @Test
    public void first() throws Exception {
        assertEquals(underTest.first(asList(new String[]{"a", "b"})), "a");
    }


    @Test
    public void batchTest() throws Exception {
        List<List<Object>> result = underTest.batch(new String[]{"a", "b", "c", "d", "e", "f"}, 3);
        assertThat(result.get(0).size(), is(3));
        assertThat(result.get(0), hasItem("a"));
        assertThat(result.get(0), hasItem("b"));
        assertThat(result.get(0), hasItem("c"));
        assertThat(result.get(1).size(), is(3));
        assertThat(result.get(1), hasItem("d"));
        assertThat(result.get(1), hasItem("e"));
        assertThat(result.get(1), hasItem("f"));
    }


    @Test
    public void batchWithNotMultipleSize() throws Exception {
        List<List<Object>> result = underTest.batch(new String[]{"a", "b", "c", "d", "e", "f", "g"}, 3);
        assertThat(result.get(0).size(), is(3));
        assertThat(result.get(0), hasItem("a"));
        assertThat(result.get(0), hasItem("b"));
        assertThat(result.get(0), hasItem("c"));
        assertThat(result.get(1).size(), is(3));
        assertThat(result.get(1), hasItem("d"));
        assertThat(result.get(1), hasItem("e"));
        assertThat(result.get(1), hasItem("f"));
        assertThat(result.get(2).size(), is(1));
    }

    @Test
    public void batchWithNotMultipleSizeWithDefault() throws Exception {
        List<List<Object>> result = underTest.batch(new String[]{"a", "b", "c", "d", "e", "f", "g"}, 3, "h");
        assertThat(result.get(0).size(), is(3));
        assertThat(result.get(0), hasItem("a"));
        assertThat(result.get(0), hasItem("b"));
        assertThat(result.get(0), hasItem("c"));
        assertThat(result.get(1).size(), is(3));
        assertThat(result.get(1), hasItem("d"));
        assertThat(result.get(1), hasItem("e"));
        assertThat(result.get(1), hasItem("f"));
        assertThat(result.get(2).size(), is(3));
    }


    @Test
    public void concatenateShouldNotIncludeNullValues () throws FunctionException {
        String result = underTest.concatenate("Hi! ", null, "My name is Twig!");
        assertThat(result, is("Hi! My name is Twig!"));
    }

    @Test
    public void concatenateShouldGiveEmptyStringIfExecutedWithoutArguments () throws FunctionException {
        String result = underTest.concatenate();
        assertThat(result, is(""));
    }



    @Test
    public void maxPrefersIntegersOverCharacters() throws Exception {
        Object result = underTest.max(1,2,'a','e');
        assertEquals(2, result);
    }
    @Test
    public void maxPrefersLowercase() throws Exception {
        Object result = underTest.max('a','A','b');
        assertEquals('b', result);
    }
    @Test
    public void maxWorksAlphabetically() throws Exception {
        Object result = underTest.max("hello","help",'z');
        assertEquals('z', result);
    }
    @Test
    public void minPrefersCharsOverIntegers() throws Exception {
        Object result = underTest.min(1,2,'a','e');
        assertEquals('a', result);
    }
    @Test
    public void minPrefersUppercase() throws Exception {
        Object result = underTest.min('a','A');
        assertEquals('A', result);
    }
    @Test
    public void minWorksAlphabetically() throws Exception {
        Object result = underTest.min("hello","help",'z');
        assertEquals("hello", result);
    }
}
