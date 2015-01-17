package ee.ttu

import grails.validation.Validateable
import org.apache.commons.collections.ListUtils
import org.apache.commons.collections.Factory

/**
 * Created by Vahur Kaar on 11.11.2014.
 */
@Validateable
class CustomerCommand {

    List<CustomerAddress> addresses = ListUtils.lazyList([], {new CustomerAddress()} as Factory)


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomerCommand{");
        sb.append("id=").append(id);
        sb.append(", addresses=").append(addresses);
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
