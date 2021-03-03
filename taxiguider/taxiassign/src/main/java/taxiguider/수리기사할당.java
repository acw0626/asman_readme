package taxiguider;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="수리기사할당_table")
public class 수리기사할당 {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String 호출상태; //호출,호출중,호출확정,호출취소
    private String 수리기사번호;
    private String 수리기사기사이름;
    private String 수리기사기사전화번호;
    
    @PrePersist
    public void onPrePersist(){
    	System.out.println("==============수리기사할당================");


        //할당확인됨 할당확인됨 = new 할당확인됨();
        //BeanUtils.copyProperties(this, 할당확인됨);
        //할당확인됨.publishAfterCommit();


        //할당취소됨 할당취소됨 = new 할당취소됨();
        //BeanUtils.copyProperties(this, 할당취소됨);
        //할당취소됨.publishAfterCommit();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	public String get호출상태() {
		return 호출상태;
	}

	public void set호출상태(String 호출상태) {
		this.호출상태 = 호출상태;
	}


	public String get수리기사번호() {
		return 수리기사번호;
	}

	public void set수리기사번호(String 수리기사번호) {
		this.수리기사번호 = 수리기사번호;
	}


	public String get수리기사기사이름() {
		return 수리기사기사이름;
	}

	public void set수리기사기사이름(String 수리기사기사이름) {
		this.수리기사기사이름 = 수리기사기사이름;
	}


	public String get수리기사기사전화번호() {
		return 수리기사기사전화번호;
	}

	public void set수리기사기사전화번호(String 수리기사기사전화번호) {
		this.수리기사기사전화번호 = 수리기사기사전화번호;
	}



}
