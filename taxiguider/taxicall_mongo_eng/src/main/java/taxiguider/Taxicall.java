package taxiguider;

import org.springframework.data.annotation.Id;

//@Entity
//@Table(name="수리기사호출_table")
public class Taxicall {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private String id;
    private String tel;
    private String location;
    private String status; //호출,호출중,호출확정,호출취소
    private Integer cost;
    
	
//    @PostPersist
//    public void onPostPersist(){
//        수리기사호출요청됨 수리기사호출요청됨 = new 수리기사호출요청됨();
//        BeanUtils.copyProperties(this, 수리기사호출요청됨);
//        수리기사호출요청됨.publishAfterCommit();
    	
//    	System.out.println("휴대폰번호 " + get휴대폰번호());
 //       System.out.println("호출위치 " + get호출위치());
//        System.out.println("호출상태 " + get호출상태());
//        System.out.println("예상요금 " + get예상요금());
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.   	
//    	if(get휴대폰번호() != null)
//		{
//   		System.out.println("SEND###############################" + getId());
//			수리기사관리 수리기사관리 = new 수리기사관리();
//	        
//			수리기사관리.setOrderId(getId());
//	        수리기사관리.set고객휴대폰번호(get휴대폰번호());
//	        if(get호출위치()!=null) 
//	        	수리기사관리.set호출위치(get호출위치());
//	        if(get호출상태()!=null) 
//	        	수리기사관리.set호출상태(get호출상태());
//	        if(get예상요금()!=null) 
//	        	수리기사관리.set예상요금(get예상요금());
//	        
//	        // mappings goes here
//	        TaxicallApplication.applicationContext.getBean(수리기사관리Service.class).수리기사할당요청(수리기사관리);
//		}
    	
//        호출취소됨 호출취소됨 = new 호출취소됨();
//        BeanUtils.copyProperties(this, 호출취소됨);
//        호출취소됨.publishAfterCommit();
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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
