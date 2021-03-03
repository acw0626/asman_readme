package taxiguider;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

import taxiguider.external.Taximanage;
import taxiguider.external.TaximanageService;

/**
 * Equivalent of a domain method annotated by <code>PrePersist</code>.
 * <p/>
 * This handler shows how to implement your custom UUID generation.
 * 
 * @author Tobias Trelle
 */
public class TaxicallRepositoryListener extends AbstractMongoEventListener<Taximanage> {

	@Override
	public void onBeforeSave(BeforeSaveEvent<Taximanage> event) {
		super.onBeforeSave(event);

		Taximanage taxiM = event.getSource();
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
	public void onAfterSave(AfterSaveEvent<Taximanage> event) {
		super.onAfterSave(event);
//      수리기사호출요청됨 수리기사호출요청됨 = new 수리기사호출요청됨();
//      BeanUtils.copyProperties(this, 수리기사호출요청됨);
//      수리기사호출요청됨.publishAfterCommit();
		Taximanage taxiM = event.getSource();
		System.out.println("휴대폰번호 " + taxiM.getTel());
		System.out.println("호출위치 " + taxiM.getLocation());
		System.out.println("호출상태 " + taxiM.getStatus());
		System.out.println("예상요금 " + taxiM.getCost());
		// Following code causes dependency to external APIs
		// it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
		if (taxiM.getTel() != null) {
			System.out.println("SEND###############################" + taxiM.getId());
			Taximanage txMgr = new Taximanage();

			txMgr.setOrderId(String.valueOf(taxiM.getId()));
			txMgr.setTel(taxiM.getTel());
			if (taxiM.getLocation() != null)
				txMgr.setLocation(taxiM.getLocation());
			if (taxiM.getStatus() != null)
				txMgr.setStatus(taxiM.getStatus());
			if (taxiM.getCost() != null)
				txMgr.setCost(taxiM.getCost());

			// mappings goes here
			TaxicallApplication.applicationContext.getBean(TaximanageService.class)
				.requestTaxiAssign(txMgr);
		}

//      호출취소됨 호출취소됨 = new 호출취소됨();
//      BeanUtils.copyProperties(this, 호출취소됨);
//      호출취소됨.publishAfterCommit();
	}

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Taximanage> event) {
		// super.onBeforeConvert(event);

	}

	@Override
	public void onAfterLoad(AfterLoadEvent<Taximanage> event) {

	}

	@Override
	public void onAfterDelete(AfterDeleteEvent<Taximanage> event) {

	}

	@Override
	public void onAfterConvert(AfterConvertEvent<Taximanage> event) {

	}

}