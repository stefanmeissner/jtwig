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

package org.jtwig.extension.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.jtwig.extension.SimpleExtension;
import org.jtwig.extension.SimpleFilter;
import org.jtwig.extension.SimpleFunction;
import org.jtwig.extension.SimpleTest;
import org.jtwig.extension.api.tokenparser.TokenParser;
import org.jtwig.extension.core.filters.*;
import org.jtwig.extension.core.functions.*;
import org.jtwig.extension.core.operators.BinaryAdditionOperator;
import org.jtwig.extension.core.operators.BinaryAndOperator;
import org.jtwig.extension.core.operators.BinaryBitwiseAndOperator;
import org.jtwig.extension.core.operators.BinaryBitwiseOrOperator;
import org.jtwig.extension.core.operators.BinaryBitwiseXorOperator;
import org.jtwig.extension.core.operators.BinaryCompositionOperator;
import org.jtwig.extension.core.operators.BinaryConcatenationOperator;
import org.jtwig.extension.core.operators.BinaryDivisionOperator;
import org.jtwig.extension.core.operators.BinaryEndsWithOperator;
import org.jtwig.extension.core.operators.BinaryEqualOperator;
import org.jtwig.extension.core.operators.BinaryExponentOperator;
import org.jtwig.extension.core.operators.BinaryFloorDivisionOperator;
import org.jtwig.extension.core.operators.BinaryGreaterThanOperator;
import org.jtwig.extension.core.operators.BinaryGreaterThanOrEqualOperator;
import org.jtwig.extension.core.operators.BinaryInOperator;
import org.jtwig.extension.core.operators.BinaryIsNotOperator;
import org.jtwig.extension.core.operators.BinaryIsOperator;
import org.jtwig.extension.core.operators.UnaryIsNotOperator;
import org.jtwig.extension.core.operators.UnaryIsOperator;
import org.jtwig.extension.core.operators.BinaryLessThanOperator;
import org.jtwig.extension.core.operators.BinaryLessThanOrEqualOperator;
import org.jtwig.extension.core.operators.BinaryMatchesOperator;
import org.jtwig.extension.core.operators.BinaryModulusOperator;
import org.jtwig.extension.core.operators.BinaryMultiplicationOperator;
import org.jtwig.extension.core.operators.BinaryNotEqualOperator;
import org.jtwig.extension.core.operators.BinaryNotInOperator;
import org.jtwig.extension.core.operators.BinaryOrOperator;
import org.jtwig.extension.core.operators.BinaryRangeOperator;
import org.jtwig.extension.core.operators.BinarySelectionOperator;
import org.jtwig.extension.core.operators.BinaryStartsWithOperator;
import org.jtwig.extension.core.operators.BinarySubtractionOperator;
import org.jtwig.extension.core.operators.UnaryNegativeOperator;
import org.jtwig.extension.core.operators.UnaryNotOperator;
import org.jtwig.extension.core.operators.UnaryPositiveOperator;
import org.jtwig.extension.core.tests.*;
import org.jtwig.extension.core.tokenparsers.BlockDefinitionParser;
import org.jtwig.extension.core.tokenparsers.EmbedStatementParser;
import org.jtwig.extension.core.tokenparsers.ExtendsStatementParser;
import org.jtwig.extension.core.tokenparsers.ForStatementParser;
import org.jtwig.extension.core.tokenparsers.FromImportStatementParser;
import org.jtwig.extension.core.tokenparsers.IfStatementParser;
import org.jtwig.extension.core.tokenparsers.ImportStatementParser;
import org.jtwig.extension.core.tokenparsers.IncludeStatementParser;
import org.jtwig.extension.core.tokenparsers.MacroDefinitionParser;
import org.jtwig.extension.core.tokenparsers.SetStatementParser;
import org.jtwig.extension.api.operator.Operator;
import org.jtwig.extension.core.tokenparsers.CommentParser;
import org.jtwig.extension.core.tokenparsers.ConcurrentTag;
import org.jtwig.extension.core.tokenparsers.FilterTag;
import org.jtwig.extension.core.tokenparsers.SpacelessTag;

public class CoreJtwigExtension extends SimpleExtension {

    @Override
    public String getName() {
        return "core";
    }

    @Override
    public Map<String, Operator> getUnaryOperators() {
        return new HashMap<String, Operator>(){{
            put("not", new UnaryNotOperator("not", 50));
            put("is not", new UnaryIsNotOperator("is not", 100));
            put("is", new UnaryIsOperator("is", 105));
            put("-", new UnaryNegativeOperator("-", 500));
            put("+", new UnaryPositiveOperator("+", 500));
        }};
    }
    
    @Override
    public Map<String, Operator> getBinaryOperators() {
        return new HashMap<String, Operator>(){{
            // These aren't operators in Twig, but they function like it
            put(".", new BinarySelectionOperator(".", 5));
            put("|", new BinaryCompositionOperator("|", 5));
            
            // Twig operators
            put("or", new BinaryOrOperator("or", 10));
            put("and", new BinaryAndOperator("and", 15));
            put("b-or", new BinaryBitwiseOrOperator("b-or", 16));
            put("b-xor", new BinaryBitwiseXorOperator("b-xor", 17));
            put("b-and", new BinaryBitwiseAndOperator("b-and", 18));
            
            put("==", new BinaryEqualOperator("==", 20));
            put("!=", new BinaryNotEqualOperator("!=", 20));
            put("<", new BinaryLessThanOperator("<", 20));
            put(">", new BinaryGreaterThanOperator(">", 20));
            put("<=", new BinaryLessThanOrEqualOperator("<=", 20));
            put(">=", new BinaryGreaterThanOrEqualOperator(">=", 20));
            put("not in", new BinaryNotInOperator("not in", 20));
            put("in", new BinaryInOperator("in", 20));
            put("matches", new BinaryMatchesOperator("matches", 20));
            put("starts with", new BinaryStartsWithOperator("starts with", 20));
            put("ends with", new BinaryEndsWithOperator("ends with", 20));
            put("..", new BinaryRangeOperator("..", 25));
            
            put("+", new BinaryAdditionOperator("+", 30));
            put("-", new BinarySubtractionOperator("-", 30));
            
            put("~", new BinaryConcatenationOperator("~", 40));
            put("*", new BinaryMultiplicationOperator("*", 60));
            put("/", new BinaryDivisionOperator("/", 60));
            put("//", new BinaryFloorDivisionOperator("//", 60));
            put("%", new BinaryModulusOperator("%", 60));
            put("is", new BinaryIsOperator("is", 100));
            put("is not", new BinaryIsNotOperator("is not", 100));
            put("**", new BinaryExponentOperator("**", 200));
        }};
    }

    @Override
    public Map<String, SimpleFunction> getFunctions() {
        return new HashMap<String, SimpleFunction>(){{
            put("max", new SimpleFunction("max", new MaxFunction()));
            put("min", new SimpleFunction("min", new MinFunction()));
            put("range", new SimpleFunction("range", new RangeFunction()));
            put("constant", new SimpleFunction("constant", new ConstantFunction()));
            put("cycle", new SimpleFunction("cycle", new CycleFunction()));
            put("random", new SimpleFunction("random", new RandomFunction()));
            put("date", new SimpleFunction("date", new DateFunction()));
            put("include", new SimpleFunction("include", new IncludeFunction()));
            put("source", new SimpleFunction("source", new SourceFunction()));
        }};
    }

    @Override
    public Map<String, SimpleFilter> getFilters() {
        return new HashMap<String, SimpleFilter>(){{
            // Formatting
            put("date", new SimpleFilter("date", new DateFilter()));
            put("date_modify", new SimpleFilter("date_modify", new DateModifyFilter()));
            put("format", new SimpleFilter("format", new FormatFilter()));
            put("replace", new SimpleFilter("replace", new ReplaceFilter()));
            put("number_format", new SimpleFilter("number_format", new NumberFormatFilter()));
            put("abs", new SimpleFilter("abs", new AbsFilter()));
            put("round", new SimpleFilter("round", new RoundFilter()));

            // Encoding
            put("url_encode", new SimpleFilter("url_encode", new UrlEncodeFilter()));
            put("json_encode", new SimpleFilter("json_encode", new JsonEncodeFilter()));
            put("convert_encoding", new SimpleFilter("convert_encoding", new ConvertEncodingFilter()));

            // String filters
            put("title", new SimpleFilter("title", new TitleFilter()));
            put("capitalize", new SimpleFilter("capitalize", new CapitalizeFilter()));
            put("upper", new SimpleFilter("upper", new UpperFilter()));
            put("lower", new SimpleFilter("lower", new LowerFilter()));
            put("striptags", new SimpleFilter("striptags", new StripTagsFilter()));
            put("trim", new SimpleFilter("trim", new TrimFilter()));
            put("nl2br", new SimpleFilter("nl2br", new Newline2BreakFilter()));

            // Array helpers
            put("join", new SimpleFilter("join", new JoinFilter()));
            put("split", new SimpleFilter("split", new SplitFilter()));
            put("sort", new SimpleFilter("sort", new SortFilter()));
            put("merge", new SimpleFilter("merge", new MergeFilter()));
            put("batch", new SimpleFilter("batch", new BatchFilter()));

            // String/array filters
            put("reverse", new SimpleFilter("reverse", new ReverseFilter()));
            put("length", new SimpleFilter("length", new LengthFilter()));
            put("slice", new SimpleFilter("slice", new SliceFilter()));
            put("first", new SimpleFilter("first", new FirstFilter()));
            put("last", new SimpleFilter("last", new LastFilter()));

            // Iteration and runtime
            put("default", new SimpleFilter("default", new DefaultFilter()));
            put("keys", new SimpleFilter("keys", new KeysFilter()));

            // Escaping
            put("escape", new SimpleFilter("escape", new EscapeFilter()));
            put("e", new SimpleFilter("e", new EscapeFilter()));
        }};
    }
    
    @Override
    public Map<String, SimpleTest> getTests() {
        return new HashMap<String, SimpleTest>(){{
            put("even", new SimpleTest("even", new EvenTest()));
            put("odd", new SimpleTest("odd", new OddTest()));
            put("defined", new SimpleTest("defined", new DefinedTest()));
            put("sameas", new SimpleTest("sameas", new SameAsTest()));
            put("same as", new SimpleTest("same as", new SameAsTest()));
            put("none", new SimpleTest("none", new NoneTest()));
            put("null", new SimpleTest("null", new NullTest()));
            put("divisibleby", new SimpleTest("divisibleby", new DivisibleByTest()));
            put("divisible by", new SimpleTest("divisible by", new DivisibleByTest()));
            put("constant", new SimpleTest("constant", new ConstantTest()));
            put("empty", new SimpleTest("empty", new EmptyTest()));
            put("iterable", new SimpleTest("iterable", new IterableTest()));
        }};
    }

    @Override
    public Collection<Class<? extends TokenParser>> getTokenParsers() {
        return Arrays.asList(
                ExtendsStatementParser.class,
                IncludeStatementParser.class,
                
                BlockDefinitionParser.class,
                MacroDefinitionParser.class,
                ImportStatementParser.class,
                FromImportStatementParser.class,
                EmbedStatementParser.class,
                CommentParser.class,
                
                ForStatementParser.class,
//                new DoTokenParser(),
                IfStatementParser.class,
                
//                UseTokenParser.class,
                SetStatementParser.class,
//                SpacelessTokenParser.class,
//                FlushTokenParser.class,
                
                ConcurrentTag.class,
                SpacelessTag.class,
                FilterTag.class
        );
    }
    
}