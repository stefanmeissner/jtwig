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

package org.jtwig.extension.core.functions;

import java.io.ByteArrayOutputStream;
import org.jtwig.Environment;
import org.jtwig.content.api.Renderable;
import org.jtwig.content.model.Template;
import org.jtwig.exception.RenderException;
import org.jtwig.extension.api.functions.Function;
import org.jtwig.extension.api.functions.FunctionException;
import org.jtwig.render.RenderContext;

public class BlockFunction implements Function {

    @Override
    public Object evaluate(Environment env, RenderContext ctx, Object... args) throws FunctionException {
        if (args.length == 0 || args[0].toString().trim().isEmpty()) {
            throw new FunctionException("The block function requires a block name");
        }
        
        // Clone the RenderContext so that we can isolate the renderstream
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            RenderContext isolated = ctx.newRenderContext(baos);
            Template.Compiled template = ctx.getRenderingTemplate();
            Renderable block = template.getPrimordial().block(args[0].toString());
            if (block != null) {
                block.render(isolated);
            }
            return baos.toString();
        } catch (RenderException e) {
            throw new FunctionException("Unable to render the block.", e);
        }
    }
    
}
