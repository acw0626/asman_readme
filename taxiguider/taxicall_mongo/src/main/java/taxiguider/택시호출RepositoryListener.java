package taxiguider;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

import taxiguider.external.수리기사관리;
import taxiguider.external.수리기사관리Service;

/**
 * Equivalent of a domain method annotated by <code>PrePersist</code>.
 * <p/>
 * This handler shows how to implement your custom UUID generation.
 * 
 * @author Tobias Trelle
 */
public class 수리기사호출RepositoryListener extends AbstractMongoEventListener<수리기사관리> {

	@Override
	public void onBeforeSave(BeforeSaveEvent<수리기사관리> event) {
		super.onBeforeSave(event);

		수리기사관리 taxiM = event.getSource();
		Document d = event.getDocument();
		System.out.println("===============>>>>>>=======================" + taxiM.getId());
		if (taxiM.getId() == null) {
			// TODO use a better UUID generator in production
			d.put("_id", "" + (int) Math.floor(Math.random() * 100));
			// d.put("id","" + (int)Math.floor(Math.random()*100) );
			taxiM.setId((long)Math.floor(Math.random() * 100));
		}

	}

	@Override
	public void onAfterSave(AfterSaveEvent<수리기사관리> event) {
		super.onAfterSave(event);
//      수리기사호출요청됨 수리기사호출요청됨 = new 수리기사호출요청됨();
//      BeanUtils.copyProperties(this, 수리기사호출요청됨);
//      수리기사호출요청됨.publishAfterCommit();
		수리기사관리 taxiM = event.getSource();
		System.out.println("휴대폰번호 " + taxiM.get고객휴대폰번호());
		System.out.println("호출위치 " + taxiM.get호출위치());
		System.out.println("호출상태 " + taxiM.get호출상태());
		System.out.println("예상요금 " + taxiM.get예상요금());
		// Following code causes dependency to external APIs
		// it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
		if (taxiM.get고객휴대폰번호() != null) {
			System.out.println("SEND###############################" + taxiM.getId());
			수리기사관리 수리기사관리 = new 수리기사관리();

			수리기사관리.setOrderId(String.valueOf(taxiM.getId()));
			수리기사관리.set고객휴대폰번호(taxiM.get고객휴대폰번호());
			if (taxiM.get호출위치() != null)
				수리기사관리.set호출위치(taxiM.get호출위치());
			if (taxiM.get호출상태() != null)
				수리기사관리.set호출상태(taxiM.get호출상태());
			if (taxiM.get예상요금() != null)
				수리기사관리.set예상요금(taxiM.get예상요금());

			// mappings goes here
			TaxicallApplication.applicationContext.getBean(수리기사관리Service.class).수리기사할당요청(수리기사관리);
		}

//      호출취소됨 호출취소됨 = new 호출취소됨();
//      BeanUtils.copyProperties(this, 호출취소됨);
//      호출취소됨.publishAfterCommit();
	}

	@Override
	public void onBeforeConvert(BeforeConvertEvent<수리기사관리> event) {
		// super.onBeforeConvert(event);

	}

	@Override
	public void onAfterLoad(AfterLoadEvent<수리기사관리> event) {

	}

	@Override
	public void onAfterDelete(AfterDeleteEvent<수리기사관리> event) {

	}

	@Override
	public void onAfterConvert(AfterConvertEvent<수리기사관리> event) {

	}

}