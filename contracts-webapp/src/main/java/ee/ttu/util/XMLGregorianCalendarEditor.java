package ee.ttu.util;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Vahur Kaar on 9.11.2014.
 */
public class XMLGregorianCalendarEditor extends PropertyEditorSupport {

    private String dateFormat;

    public XMLGregorianCalendarEditor(String format) {
        dateFormat = format;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isNotEmpty(text)) {
            parseDate(text);
        }
    }

    private void parseDate(String text) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        try {
            setValue(XMLCalendarUtil.dateToXmlCalendar(simpleDateFormat.parse(text)));
        } catch (ParseException e) {
            throw new IllegalArgumentException(String.format("Failed to parse date input {%s}!", text));
        }
    }
}
