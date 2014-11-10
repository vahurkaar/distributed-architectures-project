package ee.ttu.web.servlet.jsp.tag.form;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.web.servlet.tags.form.TagWriter;


public class InputTag extends org.springframework.web.servlet.tags.form.InputTag {

    private Boolean writeAsLabel;

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {

        // if only input value is required
        if (BooleanUtils.isTrue(getWriteAsLabel())) {
            try {
                String valueToWrite = getDisplayString(getBoundValue(), getPropertyEditor());
                this.pageContext.getOut().write(valueToWrite);
            } catch (IOException e) {
                // Ignore
            }
            return SKIP_BODY;
        }

        // Draw input element with wrapper
        else {
            // Input tag start
            return super.writeTagContent(tagWriter);
        }
    }

    public void setWriteAsLabel(Boolean writeAsLabel) {
        this.writeAsLabel = writeAsLabel;
    }

    public Boolean getWriteAsLabel() {
        return writeAsLabel;
    }
}
