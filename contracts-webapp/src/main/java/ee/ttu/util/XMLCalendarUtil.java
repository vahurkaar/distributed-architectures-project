package ee.ttu.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vahur Kaar on 24.09.2014.
 */
public class XMLCalendarUtil {

    public static Date xmlCalendarToDate(XMLGregorianCalendar calendar) {
        if (calendar != null) {
            return calendar.toGregorianCalendar().getTime();
        }

        return null;
    }

    public static XMLGregorianCalendar dateToXmlCalendar(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            try {
                XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                xmlGregorianCalendar.setDay(calendar.get(Calendar.DAY_OF_MONTH));
                xmlGregorianCalendar.setMonth(calendar.get(Calendar.MONTH) + 1);
                xmlGregorianCalendar.setYear(calendar.get(Calendar.YEAR));
                return xmlGregorianCalendar;
            } catch (DatatypeConfigurationException e) {
                return null;
            }
        }

        return null;
    }

}
