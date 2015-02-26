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
import org.jtwig.extension.api.filters.Filter;
import org.jtwig.extension.api.functions.Function;
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
import org.jtwig.extension.api.test.Test;
import org.jtwig.extension.core.tests.*;
import org.jtwig.extension.core.tokenparsers.CommentParser;
import org.jtwig.extension.core.tokenparsers.ConcurrentTag;
import org.jtwig.extension.core.tokenparsers.FilterTag;
import org.jtwig.extension.core.tokenparsers.SpacelessTag;
import org.jtwig.extension.core.tokenparsers.VerbatimTag;

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
            put("-", new UnaryNegativeOperator("-", 3));
            put("+", new UnaryPositiveOperator("+", 3));
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
    public Map<String, Function> getFunctions() {
        return new HashMap<String, Function>(){{
            put("attribute", new AttributeFunction());
            put("block", new BlockFunction());
            put("constant", new ConstantFunction());
            put("cycle", new CycleFunction());
            put("date", new DateFunction());
            put("dump", new DumpFunction());
            put("include", new IncludeFunction());
            put("max", new MaxFunction());
            put("min", new MinFunction());
            put("parent", new ParentFunction());
            put("random", new RandomFunction());
            put("range", new RangeFunction());
            put("source", new SourceFunction());
            put("template_from_string", new TemplateFromStringFunction());
        }};
    }

    @Override
    public Map<String, Filter> getFilters() {
        return new HashMap<String, Filter>(){{
            // Formatting
            put("date", new DateFilter());
            put("date_modify", new DateModifyFilter());
            put("format", new FormatFilter());
            put("replace", new ReplaceFilter());
            put("number_format", new NumberFormatFilter());
            put("abs", new AbsFilter());
            put("round", new RoundFilter());

            // Encoding
            put("url_encode", new UrlEncodeFilter());
            put("json_encode", new JsonEncodeFilter());
            put("convert_encoding", new ConvertEncodingFilter());

            // String filters
            put("title", new TitleFilter());
            put("capitalize", new CapitalizeFilter());
            put("upper", new UpperFilter());
            put("lower", new LowerFilter());
            put("striptags", new StripTagsFilter());
            put("trim", new TrimFilter());
            put("nl2br", new Newline2BreakFilter());

            // Array helpers
            put("join", new JoinFilter());
            put("split", new SplitFilter());
            put("sort", new SortFilter());
            put("merge", new MergeFilter());
            put("batch", new BatchFilter());

            // String/array filters
            put("reverse", new ReverseFilter());
            put("length", new LengthFilter());
            put("slice", new SliceFilter());
            put("first", new FirstFilter());
            put("last", new LastFilter());

            // Iteration and runtime
            put("default", new DefaultFilter());
            put("keys", new KeysFilter());

            // Escaping
            put("escape", new EscapeFilter());
            put("e", new EscapeFilter());
        }};
    }
    
    @Override
    public Map<String, Test> getTests() {
        return new HashMap<String, Test>(){{
            put("even", new EvenTest());
            put("odd", new OddTest());
            put("defined", new DefinedTest());
//            put("sameas", new SameAsTest());
//            put("same as", new SameAsTest());
            put("none", new NoneTest());
            put("null", new NullTest());
            put("divisibleby", new DivisibleByTest());
            put("divisible by", new DivisibleByTest());
            put("constant", new ConstantTest());
            put("empty", new EmptyTest());
            put("iterable", new IterableTest());
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
                FilterTag.class,
                VerbatimTag.class
        );
    }
    
}