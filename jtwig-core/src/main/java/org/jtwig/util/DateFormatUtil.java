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

package org.jtwig.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.jtwig.util.dateformat.DayOfWeekPrinter;
import org.jtwig.util.dateformat.DaysInMonthPrinter;
import org.jtwig.util.dateformat.IsDaylightSavingsTimePrinter;
import org.jtwig.util.dateformat.IsLeapYearPrinter;
import org.jtwig.util.dateformat.LowercaseHalfOfDayPrinter;
import org.jtwig.util.dateformat.OrdinalDayOfWeekSuffixPrinter;
import org.jtwig.util.dateformat.SwatchInternetTimePrinter;

/**
 * Converts between Joda's date formatting style and PHP's date formatting
 * style.
 */
public class DateFormatUtil {
    
    enum OUTPUT_FORMAT_SYMBOL {
        d, D, j, l, N, S, w, z,
        W,
        F, m, M, n, t,
        L, o, Y, y,
        a, A, B, g, G, h, H, i, s, u,
        e, I, O, P, T, Z,
        c, r, U;
        
        public static String[] tokens() {
            OUTPUT_FORMAT_SYMBOL[] values = values();
            String[] result = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                result[i] = values[i].name();
            }
            return result;
        }
        public static boolean contains(String test) {

            for (OUTPUT_FORMAT_SYMBOL c : values()) {
                if (c.name().equals(test)) {
                    return true;
                }
            }

            return false;
        }
    }
    public static DateTimeFormatter output(final String format) {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        for (String token : new Tokenizer(format, OUTPUT_FORMAT_SYMBOL.tokens())) {
            if (!OUTPUT_FORMAT_SYMBOL.contains(token)) {
                builder.appendLiteral(token);
                continue;
            }
            
            switch(OUTPUT_FORMAT_SYMBOL.valueOf(token)) {
                case d:
                    builder.appendDayOfMonth(2);
                    break;
                case D:
                    builder.appendDayOfWeekShortText();
                    break;
                case j:
                    builder.appendDayOfMonth(1);
                    break;
                case l:
                    builder.appendDayOfWeekText();
                    break;
                case N:
                    builder.append(new DayOfWeekPrinter());
                    break;
                case S:
                    builder.append(new OrdinalDayOfWeekSuffixPrinter());
                    break;
                case w:
                    builder.append(new DayOfWeekPrinter(false));
                    break;
                case z:
                    builder.appendDayOfYear(1);
                    break;
                case W:
                    builder.appendWeekOfWeekyear(1);
                    break;
                case F:
                    builder.appendMonthOfYearText();
                    break;
                case m:
                    builder.appendMonthOfYear(2);
                    break;
                case M:
                    builder.appendMonthOfYearShortText();
                    break;
                case n:
                    builder.appendMonthOfYear(1);
                    break;
                case t:
                    builder.append(new DaysInMonthPrinter());
                    break;
                case L:
                    builder.append(new IsLeapYearPrinter());
                    break;
                case o:
                    throw new UnsupportedOperationException("'o' not yet supported");
                case Y:
                    builder.appendYear(4, 4);
                    break;
                case y:
                    builder.appendYear(2, 2);
                    break;
                case a:
                    builder.append(new LowercaseHalfOfDayPrinter());
                    break;
                case A:
                    builder.appendHalfdayOfDayText();
                    break;
                case B:
                    builder.append(new SwatchInternetTimePrinter());
                    break;
                case g:
                    builder.appendHourOfHalfday(1);
                    break;
                case G:
                    builder.appendHourOfDay(1);
                    break;
                case h:
                    builder.appendHourOfHalfday(2);
                    break;
                case H:
                    builder.appendHourOfDay(2);
                    break;
                case i:
                    builder.appendMinuteOfHour(2);
                    break;
                case s:
                    builder.appendSecondOfMinute(2);
                    break;
                case u:
                    builder.appendMillisOfSecond(6);
                    break;
                case e:
                    builder.appendTimeZoneName();
                    break;
                case I:
                    builder.append(new IsDaylightSavingsTimePrinter());
                    break;
                case O:
                    builder.appendTimeZoneOffset(null, false, 2, 4);
                    break;
                case P:
                    builder.appendTimeZoneOffset(null, true, 2, 4);
                    break;
                case T:
                    builder.appendTimeZoneShortName();
                     break;
                case Z:
                    throw new UnsupportedOperationException("'Z' not yet supported");
                case c:
                    throw new UnsupportedOperationException("'c' not yet supported");
                case r:
                    throw new UnsupportedOperationException("'r' not yet supported");
                case U:
                    throw new UnsupportedOperationException("'U' not yet supported");
                default:
                    builder.appendLiteral(token);
                    break;
            }
        }
        return builder.toFormatter();
    }
//    public static String timeToJoda(final String php) {
//    }
//    public static String dateToJoda(final String php) {
//        
//    }
//    public static String compoundToJoda(final String php) {
//        
//    }
    
    public static class Tokenizer implements Iterable<String>, Iterator<String> {
        private final String[] knownTokens;
        private final String tokenized;
        private final String[] tokens;
        private int pointer = 0;
        
        public Tokenizer(final String tokenized, final String[] knownTokens) {
            this.tokenized = tokenized;
            this.knownTokens = knownTokens;
            
            // Tokenize
            List<String> tokens = new ArrayList<>();
            String tmp = tokenized;
            while (tmp.length() > 0) {
                int nextKnownTokenIdx = StringUtils.indexOfAny(tmp, knownTokens);
                String nextKnownToken = tmp.substring(nextKnownTokenIdx, nextKnownTokenIdx+1);
                if (nextKnownTokenIdx == -1) {
                    tokens.add(tmp);
                    tmp = "";
                    continue;
                }
                if (nextKnownTokenIdx == 0) {
                    tokens.add(""+nextKnownToken);
                    tmp = tmp.substring(nextKnownToken.length());
                    continue;
                }
                tokens.add(tmp.substring(0, nextKnownTokenIdx));
                tmp = tmp.substring(nextKnownTokenIdx);
                tokens.add(nextKnownToken);
                tmp = tmp.substring(nextKnownToken.length());
            }
            this.tokens = tokens.toArray(new String[tokens.size()]);
        }

        //~ Iterable impl ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        @Override
        public Iterator<String> iterator() {
            return this;
        }

        //~ Iterator impl ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        @Override
        public boolean hasNext() {
            return pointer < tokens.length;
        }

        @Override
        public String next() {
            String tmp = tokens[pointer];
            pointer++;
            return tmp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}