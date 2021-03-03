
package taxiguider.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="taximanage", url="http://taximanage:8080")
public interface TaxiManagementService {

    @RequestMapping(method= RequestMethod.GET, path="/taxiManagements")
    public void 수리기사할당요청(@RequestBody TaxiManagement taxiManagement);

}