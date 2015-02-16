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

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import static org.jtwig.util.TypeUtil.isDecimal;
import static org.jtwig.util.TypeUtil.isLong;
import static org.jtwig.util.TypeUtil.toDecimal;
import static org.jtwig.util.TypeUtil.toLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArrayUtil.class);
    
    public static Object range(Object start, Object end) {
        return range(start, end, 1);
    }
    public static Object range(Object start, Object end, final Number step) {
        if (isDecimal(start) || isDecimal(end) || isDecimal(step)) {
            return rangeDecimal(toDecimal(start), toDecimal(end), toDecimal(step));
        }
        if (isLong(start) || isLong(end)) {
            return rangeLong(toLong(start), toLong(end), toLong(step));
        }
        if ((start instanceof Object && !(start instanceof CharSequence)
                || (end instanceof Object && !(end instanceof CharSequence)))) {
            return rangeLong(toLong(start), toLong(end), toLong(step));
        }
        if (start == null || end == null || start.toString().isEmpty()
                || end.toString().isEmpty()) {
            return new long[]{0L};
        }
        return rangeChar(start.toString(), end.toString(), step.intValue());
    }
    private static BigDecimal[] rangeDecimal(BigDecimal start, BigDecimal end, BigDecimal step) {
        if (start.subtract(end).abs().compareTo(step.abs()) < 0) {
            LOGGER.warn("Step exceeds the specified range");
            return null;
        }
        if (start.compareTo(end) > 0) { // Swap direction if needed
            step = step.abs().negate();
        }
        
        BigDecimal intervals = start.subtract(end).abs().divide(step.abs(), new MathContext(1, RoundingMode.DOWN));
        
        BigDecimal current = start;
        BigDecimal[] result = new BigDecimal[intervals.intValue()+1];
        result[0] = start;
        for (int i = 1; i <= intervals.intValue(); i++) {
            result[i] = current = current.add(step);
        }
        return result;
    }
    private static long[] rangeLong(Long start, Long end, Long step) {
        if (Math.abs(start - end) < Math.abs(step)) {
            LOGGER.warn("Step exceeds the specified range");
            return null;
        }
        if (start > end) { // Swap direction if needed
            step = Math.abs(step) * -1;
        }
        
        int intervals = Double.valueOf(Math.floor(Math.abs(start - end) / Math.abs(step))).intValue();
        
        long current = start;
        long[] result = new long[intervals + 1];
        result[0] = start;
        for (int i = 1; i <= intervals; i++) {
            result[i] = current = current + step;
        }
        return result;
    }
    private static char[] rangeChar(String start, String end, int step) {
        // Twig only uses the first char from each string
        char startChar = start.charAt(0);
        char endChar = end.charAt(0);
        
        if (Math.abs(startChar - endChar) < Math.abs(step)) {
            LOGGER.warn("Step exceeds the specified range");
            return null;
        }
        if (startChar > endChar) {
            step = Math.abs(step) * -1;
        }
        
        int intervals = Double.valueOf(Math.floor(Math.abs(startChar - endChar) / Math.abs(step))).intValue();
        
        char current = startChar;
        char[] result = new char[intervals + 1];
        result[0] = startChar;
        for (int i = 1; i <= intervals; i++) {
            result[i] = current = (char)(current + step);
        }
        return result;
    }
}