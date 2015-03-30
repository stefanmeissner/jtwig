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

package org.jtwig.extension.core.operators;

import java.nio.charset.Charset;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.jtwig.extension.api.operator.BinaryOperator;
import org.jtwig.parser.model.JtwigPosition;
import org.jtwig.render.RenderContext;
import static org.jtwig.util.TypeUtil.*;

public abstract class AbstractBitwiseOperator extends BinaryOperator {
    protected boolean pad = false;
    protected boolean trim = false;

    public AbstractBitwiseOperator(String name, int precedence) {
        super(name, precedence);
    }

    @Override
    public Object render(RenderContext ctx, JtwigPosition pos, Object left, Object right) {
        if (left instanceof Number || right instanceof Number) {
            return calculate(toLong(left), toLong(right));
        }
        int width;
        if (trim) {
            width = Math.min(left.toString().length(), right.toString().length());
        } else {
            width = Math.max(left.toString().length(), right.toString().length());
        }
        return binToString(calculate(toBinary(left, width), toBinary(right, width)));
    }
    
    public abstract Long calculate(Long left, Long right);
    
    protected Long toBinary(Object obj, int width) {
        if (!(obj instanceof String || obj instanceof Number)) {
            return 1L; // Twig seems to default to 1 for non-bitwiseable stuff (like objects)
        }
        
        byte[] source = obj.toString().getBytes(Charset.forName("UTF-8"));
        if (pad || trim) {
            source = Arrays.copyOf(source,width);
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : source) {
           int val = b;
           for (int i = 0; i < 8; i++) {
              sb.append((val & 128) == 0 ? 0 : 1);
              val <<= 1;
           }
        }
        return Long.valueOf(sb.toString(), 2);
    }
    
    protected static String binToString(Long l) {
        String source = Long.toBinaryString(l);
        int mod = source.length() % 8;
        if (mod != 0) {
            source = StringUtils.repeat("0", 8-mod)+source;
        }
        
        byte[] result = new byte[source.length()/8];

        for (int i = 0; i < source.length()/8; i++) {
            int a = Integer.parseInt(source.substring(8*i,(i+1)*8),2);
            result[i] = (byte)(a & 0xFF);
        }
        return new String(result);
    }
}