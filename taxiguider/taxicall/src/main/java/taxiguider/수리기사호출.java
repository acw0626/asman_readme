package taxiguider;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import taxiguider.external.수리기사관리;
import taxiguider.external.수리기사관리Service;

@Entity
@Table(name="수리기사호출_table")
public class 수리기사호출 {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String 휴대폰번호;
    private String 호출위치;
    private String 호출상태; //호출,호출중,호출확정,호출취소
    private Integer 예상요금;
    
	
    @PostPersist
    public void onPostPersist(){
//        수리기사호출요청됨 수리기사호출요청됨 = new 수리기사호출요청됨();
//        BeanUtils.copyProperties(this, 수리기사호출요청됨);
//        수리기사호출요청됨.publishAfterCommit();
    	
    	System.out.println("휴대폰번호 " + get휴대폰번호());
        System.out.println("호출위치 " + get호출위치());
        System.out.println("호출상태 " + get호출상태());
        System.out.println("예상요금 " + get예상요금());
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.   	
    	if(get휴대폰번호() != null)
		{
    		System.out.println("SEND###############################" + getId());
			수리기사관리 수리기사관리 = new 수리기사관리();
			수리기사관리.setId(getId());
			수리기사관리.setOrderId(String.valueOf(getId()));
	        수리기사관리.set고객휴대폰번호(get휴대폰번호());
	        if(get호출위치()!=null) 
	        	수리기사관리.set호출위치(get호출위치());
	        if(get호출상태()!=null) 
	        	수리기사관리.set호출상태(get호출상태());
	        if(get예상요금()!=null) 
	        	수리기사관리.set예상요금(get예상요금());
	        
	        // mappings goes here
	        TaxicallApplication.applicationContext.getBean(수리기사관리Service.class).수리기사할당요청(수리기사관리);
		}

    }

	@PreRemove
	public void onPreRemove(){
		호출취소됨 호출취소됨 = new 호출취소됨();
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


	public String get휴대폰번호() {
		return 휴대폰번호;
	}


	public void set휴대폰번호(String 휴대폰번호) {
		this.휴대폰번호 = 휴대폰번호;
	}


	public String get호출위치() {
		return 호출위치;
	}


	public void set호출위치(String 호출위치) {
		this.호출위치 = 호출위치;
	}


	public String get호출상태() {
		return 호출상태;
	}


	public void set호출상태(String 호출상태) {
		this.호출상태 = 호출상태;
	}


	public Integer get예상요금() {
		return 예상요금;
	}


	public void set예상요금(Integer 예상요금) {
		this.예상요금 = 예상요금;
	}


}
