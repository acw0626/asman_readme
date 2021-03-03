
package taxiguider.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="taximanage", url="http://localhost:8082")
public interface 수리기사관리Service {

    @RequestMapping(method= RequestMethod.POST, path="/수리기사관리s")
    public void 수리기사할당요청(@RequestBody 수리기사관리 수리기사관리);

}