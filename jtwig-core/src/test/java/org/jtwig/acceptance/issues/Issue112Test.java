/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.jtwig.acceptance.issues;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.jtwig.AbstractJtwigTest;
import org.jtwig.exception.CalculateException;
import static org.jtwig.util.SyntacticSugar.given;
import static org.jtwig.util.SyntacticSugar.then;
import static org.jtwig.util.matchers.ExceptionMatcher.exception;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Issue112Test extends AbstractJtwigTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    // 1.A
    @Test
    public void outputNonexistentVarReturnsEmpty() throws Exception {
        given(theEnvironment().setStrictMode(false));
        withResource("{{ nonexistent }}");
        then(theResult(), is(equalTo("")));
    }

    // 1.B
    @Test
    public void outputNonexistentVarThrowsException() throws Exception {
        expectedException.expect(exception().withInnerException(exception().ofType(CalculateException.class).message(endsWith("Variable 'nonexistent' does not exist"))));

        given(theEnvironment().setStrictMode(true));
        withResource("{{ nonexistent }}");
        render();
    }

    // 2.A
    @Test
    public void selectionExampleWithStrictValidation() throws Exception {
        expectedException.expect(exception().withInnerException(exception().ofType(CalculateException.class).message(endsWith(" -> Line 1, column 16: Impossible to access attribute/method 'length' on null"))));
        
        given(theEnvironment().setStrictMode(true));
        withResource("{{ undefinedVar.length }}");
        render();
    }

    // 2.B
    @Test
    public void nestedSelectionExampleWithStrictValidation() throws Exception {
        expectedException.expect(exception().withInnerException(exception().ofType(CalculateException.class).message(endsWith(" -> Line 1, column 16: Impossible to access attribute/method 'another' on null"))));
        
        given(theEnvironment().setStrictMode(true));
        withResource("{{ undefinedVar.length.another }}");
        render();
    }

    // 3.A
    @Test
    public void operationExampleWithoutStrictValidation() throws Exception {
        given(theEnvironment().setStrictMode(false));
        withResource("{{ undefinedVar + 3 }}");
        then(theResult(), is(equalTo("3")));
    }

    // 3.B
    @Test
    public void operationExampleWithStrictValidation() throws Exception {
        expectedException.expect(exception().withInnerException(exception().ofType(CalculateException.class).message(endsWith(" -> Line 1, column 16: Variable 'undefinedVar' does not exist"))));
        
        given(theEnvironment().setStrictMode(true));
        withResource("{{ undefinedVar + 3 }}");
        then(theResult(), is(equalTo("3")));
    }

    // 3.C
    @Test
    public void operationWithNonexistentVarThrowsException() throws Exception {
        expectedException.expect(exception().withInnerException(exception().ofType(CalculateException.class).message(endsWith("Variable 'b' does not exist"))));

        given(theEnvironment().setStrictMode(true));
        withResource("{% set a = 5 %}{{ a - b  }}");
        render();
    }

    // 3.D
    @Test
    public void subtractOperationWithNullVarInStrictMode() throws Exception {
        given(theEnvironment().setStrictMode(true));
        withResource("{% set a = 5 %}{% set b = null %}{{ a - b }}");
        then(theResult(), is(equalTo("5")));
    }

    // 3.E
    @Test
    public void subtractOperationWithNullVarInNonStrictMode() throws Exception {
        given(theEnvironment().setStrictMode(false));
        withResource("{% set a = 5 %}{% set b = null %}{{ a - b }}");
        then(theResult(), is(equalTo("5")));
    }

    // 3.F
    @Test
    public void divOperationWithNullVarInStrictMode() throws Exception {
        // Even with strict variables on, Twig does not crap out on division by
        // zero. It raises a notice in the logs, and returns null. There is no
        // difference in behaviour between strict and non-strict mode
        given(theEnvironment().setStrictMode(true));
        withResource("{% set a = 5 %}{% set b = null %}{{ a / b }}");
        then(theResult(), is(equalTo("")));
    }

    // 4.A
    @Test
    public void outputNullVarReturnsEmpty() throws Exception {
        given(theEnvironment().setStrictMode(false));
        withResource("{% set nothing = null %}{{ nothing }}");
        then(theResult(), is(equalTo("")));
    }

    // 4.B
    @Test
    public void outputNullVarThrowsException() throws Exception {
        given(theEnvironment().setStrictMode(true));
        withResource("{% set nothing = null %}{{ nothing }}");
        then(theResult(), is(equalTo("")));
    }

    @Test
    public void booleanValuesShouldBePrintItsIntegerRepresentation() throws Exception {
        given(theEnvironment().setStrictMode(false));
        assertEquals("1", theResultOf(stringResource("{{ true }}")));
        assertEquals("0", theResultOf(stringResource("{{ false }}")));
    }

    @Test
    public void comparisonBetweenUndefinedAndZero() throws Exception {
        given(theEnvironment().setStrictMode(false));
        withResource("{{ nothing == 0 }}");
        then(theResult(), is(equalTo("1")));
    }

    @Test
    public void comparisonBetweenUndefinedAndNull() throws Exception {
        given(theEnvironment().setStrictMode(false));
        withResource("{{ nothing == null }}");
        then(theResult(), is(equalTo("1")));
    }
}
