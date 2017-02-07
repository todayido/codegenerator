package util;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class LowerFirstCharacter implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, Map map, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		
		// Check if no parameters were given:  
        if (!map.isEmpty()) {  
            throw new TemplateModelException("This directive doesn't allow parameters.");  
        }  
        if (loopVars.length != 0) {  
            throw new TemplateModelException("This directive doesn't allow loop variables.");  
        }  
          
        // If there is non-empty nested content:  
        if (body != null) {  
            // Executes the nested body. Same as <#nested> in FTL, except  
            // that we use our own writer instead of the current output writer.  
            body.render(new LowerCaseFilterWriter(env.getOut()));  
        } else {  
            throw new RuntimeException("missing body");  
        }  

	}
	
	private static class LowerCaseFilterWriter extends Writer {  
        
        private final Writer out;  
             
        LowerCaseFilterWriter (Writer out) {  
            this.out = out;  
        }  
  
        public void write(char[] cbuf, int off, int len)  
                throws IOException {  
            cbuf[0] = Character.toLowerCase(cbuf[0]);  
            out.write(String.valueOf(cbuf).trim());///���ұ߿ո�ȥ��  
        }  
  
        public void flush() throws IOException {  
            out.flush();  
        }  
  
        public void close() throws IOException {  
            out.close();  
        }  
    }  

}
