package taxiguider;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import taxiguider.external.Taximanage;
import taxiguider.external.TaximanageService;

@Entity
@Table(name="Taxicall_table")
public class Taxicall {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String tel;
    private String location;
    private String status; //호출,호출중,호출확정,호출취소
    private Integer cost;
    
	
    @PostPersist
    public void onPostPersist(){
//        수리기사호출요청됨 수리기사호출요청됨 = new 수리기사호출요청됨();
//        BeanUtils.copyProperties(this, 수리기사호출요청됨);
//        수리기사호출요청됨.publishAfterCommit();
    	
    	System.out.println("휴대폰번호 " + getTel());
        System.out.println("호출위치 " + getLocation());
        System.out.println("호출상태 " + getStatus());
        System.out.println("예상요금 " + getCost());
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.   	
    	if(getTel() != null)
		{
    		System.out.println("SEND###############################" + getId());
			Taximanage txMgr = new Taximanage();
			txMgr.setId(getId());
			txMgr.setOrderId(String.valueOf(getId()));
			txMgr.setTel(getTel());
	        if(getLocation()!=null) 
	        	txMgr.setLocation(getLocation());
	        if(getStatus()!=null) 
	        	txMgr.setStatus(getStatus());
	        if(getCost()!=null) 
	        	txMgr.setCost(getCost());
	        
	        // mappings goes here
	        TaxicallApplication.applicationContext.getBean(TaximanageService.class)
	        	.requestTaxiAssign(txMgr);;
		}

    }

	@PreRemove
	public void onPreRemove(){
		TaxicallCancelled 호출취소됨 = new TaxicallCancelled();
		BeanUtils.copyProperties(this, 호출취소됨);
		호출취소됨.publishAfterCommit();

		//Following code causes dependency to external APIs
		// it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

		//수리기사관리 수리기사관리 = new 수리기사관리();
		// mappings goes here
		//수리기사관리.setId(getId());
		//수리기사관리.setOrderId(String.valueOf(getId()));
		//수리기사관리.set호출상태("호출취소");
		//수리기사관리.set고객휴대폰번호(get휴대폰번호());
		
		// mappings goes here
		//TaxicallApplication.applicationContext.getBean(수리기사관리Service.class).수리기사할당요청(수리기사관리);
	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}


}
