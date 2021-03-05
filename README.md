![수리기사사진](https://user-images.githubusercontent.com/78134019/109895183-26270580-7cd2-11eb-90e8-71d39ec6c6b7.jpg)



# 서비스 시나리오

기능적 요구사항
1. 고객이 AS종류를 선택하고 픽업 요청한다.
2. 고객 위치 근처의 수리기사를 조회 후 수리기사를 할당 요청한다.
3. 할당요청된 수리기사중 하나를 자동할당 한다.
4. 할당 즉시, 고객에게 할당완료 정보를 전달 한다.
5. 고객은 수리기사호출을 취소 할 수 있다.
6. 호출이 취소 되면 해당 할당을 취소한다.
7. 고객은 상태를 중간중간 조회하고 카톡으로 받는다.

비기능적 요구사항
1. 트랜잭션
- 수리기사가 할당확인 되지 않으면 고객은 호출요청을 할 수 없다. Sync 호출
2. 장애격리
- 수리기사 할당요청은 할당확인 기능이 동작하지 않더라도, 365일 24시간 받을 수 있어야 한다 Async (event-driven), Eventual Consistency
- 고객 호출요청이 과중되면 수리기사 할당확인 요청을 잠시동안 받지 않고 잠시후에 하도록 유도한다 Circuit breaker, fallback
3. 성능
- 고객은 호출상태를 조회하고 할당/할당취소 여부를 카톡으로 확인 할 수 있어야 한다. CQRS, Event driven



# 체크포인트

1. Saga
1. CQRS
1. Correlation
1. Req/Resp
1. Gateway
1. Deploy/ Pipeline
1. Circuit Breaker
1. Autoscale (HPA)
1. Zero-downtime deploy (Readiness Probe)
1. Config Map/ Persistence Volume
1. Polyglot
1. Self-healing (Liveness Probe)


# 분석/설계



## Event Storming 결과
* MSAEz 로 모델링한 이벤트스토밍 결과: http://www.msaez.io/#/storming/U1jLaY3TG5S3MRZ68wt7Om4P24Y2/mine/6f6b5e2b22d32b44be8af339ca4fd7f7


### 이벤트 도출

![이벤트도출](https://user-images.githubusercontent.com/78134019/109895444-90d84100-7cd2-11eb-8562-d9a4fd23c771.jpg)

### 부적격 이벤트 탈락

![부적격이벤트도출](https://user-images.githubusercontent.com/78134019/109895478-9df53000-7cd2-11eb-8790-4c09ff3a0ff6.jpg)




- 과정중 도출된 잘못된 도메인 이벤트들을 걸러내는 작업을 수행함
- 고객이 AS종류를 선택됨:  UI 의 이벤트이지, 업무적인 의미의 이벤트가 아니라서 제외
- 가용 수리기사 조회됨 :  계획된 사업 범위 및 프로젝트에서 벗어서난다고 판단하여 제외


	

### 액터, 커맨드 부착하여 읽기 좋게
![액터커멘드부착](https://user-images.githubusercontent.com/78134019/109895510-aea5a600-7cd2-11eb-9149-fb94fd2ea709.jpg)



### 어그리게잇으로 묶기
![어그리게잇](https://user-images.githubusercontent.com/78134019/109895519-b2392d00-7cd2-11eb-843f-3bd2b9d3803b.jpg)



- 호출, 수리기사관리, 수리기사 할당 어그리게잇을 생성하고 그와 연결된 command 와 event 들에 의하여 트랜잭션이 유지되어야 하는 단위로 그들 끼리 묶어줌 
 


### 바운디드 컨텍스트로 묶기

![new_바운디드](https://user-images.githubusercontent.com/78134019/110055005-8172f980-7d9f-11eb-9460-7af400cdd3f8.jpg)



### 폴리시 부착 (괄호는 수행주체, 폴리시 부착을 둘째단계에서 해놔도 상관 없음. 전체 연계가 초기에 드러남)

![new_폴리시부착](https://user-images.githubusercontent.com/78134019/110055016-8768da80-7d9f-11eb-8b29-e5c6e3cdfbbb.jpg)


### 폴리시의 이동

![new_폴리시이동](https://user-images.githubusercontent.com/78134019/110055030-8cc62500-7d9f-11eb-9c15-465fbc4dabbc.jpg)



### 컨텍스트 매핑 (점선은 Pub/Sub, 실선은 Req/Resp)

![new_컨텍스트매핑](https://user-images.githubusercontent.com/78134019/110055041-918ad900-7d9f-11eb-839d-ff743448c2f9.jpg)







### 완성된 모형
![MSAEz결과](https://user-images.githubusercontent.com/78134019/109895602-d3018280-7cd2-11eb-9cec-4909972108b5.jpg)





### 기능적 요구사항 검증

![image](https://user-images.githubusercontent.com/78134019/109895633-e4e32580-7cd2-11eb-8466-ce49bd35d0bf.png)



- 고객이 수리기사를 호출요청한다.(ok)
- 수리기사 관리 시스템이 수리기사 할당을 요청한다.(ok)
- 수리기사 자동 할당이 완료된다.(ok)
- 호출상태 및 할당상태를 갱신한다.(ok)
- 고객에게 카톡 알림을 한다.(ok)



![image](https://user-images.githubusercontent.com/78134019/109895705-02b08a80-7cd3-11eb-8b39-79427278f81c.png)




- 고객이 수리기사를 호출취소요청한다.(ok)
- 수리기사 관리 시스템이 수리기사 할당 취소를 요청한다.(ok)
- 수리기사 할당이 취소된다.(ok)
- 취소상태로 갱신한다.(ok)
- 고객에게 카톡 알림을 한다.(ok)



![image](https://user-images.githubusercontent.com/78134019/109895717-07753e80-7cd3-11eb-9168-b249c5f905e8.png)


  
	- 고객이 호출진행내역을 볼 수 있어야 한다. (ok)


### 비기능 요구사항 검증
![비기능](https://user-images.githubusercontent.com/78134019/109895788-325f9280-7cd3-11eb-9632-59527872f185.jpg)



1) 마이크로 서비스를 넘나드는 시나리오에 대한 트랜잭션 처리 
   수리기사 할당요청이 완료되지 않은 호출요청 완료처리는 최종 할당이 되지 않는 경우 무한정 대기 등 대고객 서비스 및 신뢰도에 치명적 문제점이 있어 ACID 트랜잭션 적용. 
   호출요청 시 수리기사 할당요청에 대해서는 Request-Response 방식 처리 
2) 호출요청 완료시 할당확인 및 결과 전송: asmanage service 에서asAssign 마이크로서비스로 수리기사할당 요청이 전달되는 과정에 있어서 
  asAssign 마이크로 서비스가 별도의 배포주기를 가지기 때문에 Eventual Consistency 방식으로 트랜잭션 처리함. 
3) 나머지 모든 inter-microservice 트랜잭션: 호출상태, 할당/할당취소 여부 등 이벤트에 대해 카톡을 처리하는 등 데이터 일관성의 시점이 크리티컬하지 않은 모든 경우가 대부분이라 판단, 
Eventual Consistency 를 기본으로 채택함. 



## 헥사고날 아키텍처 다이어그램 도출 (Polyglot)

![핵사고날](https://user-images.githubusercontent.com/78134019/109895917-6aff6c00-7cd3-11eb-8f26-3e1a576a8edc.jpg)







# 구현:

서비스를 로컬에서 실행하는 방법은 아래와 같다 
각 서비스별로 bat 파일로 실행한다. 

```
<run_ascall.bat>
<run_asmanage.bat>
<run_asassign.bat>
<run_gateway.bat>
```
## DDD 의 적용
총 3개의 Domain 으로 관리되고 있으며, 수리기사요청(Ascall) , 수리기사관리(AsManage), 수리기사할당(AsAssign) 으로 구성된다. 

![msa보여주기](https://user-images.githubusercontent.com/78134019/110049218-c6ddf980-7d94-11eb-8b17-4dbae02202dd.jpg)





## 폴리글랏 퍼시스턴스

```
위치 : /asman>asmanage>pom.xml
```

![hsqldb](https://user-images.githubusercontent.com/78134019/109915773-be82b180-7cf5-11eb-9c50-68fdc3f4c385.jpg)


## 폴리글랏 프로그래밍 - 파이썬
```
위치 : asman/cutomer_py>policy-handler.py
```

![파이썬소스바꾸기](https://user-images.githubusercontent.com/78134019/110049279-e4ab5e80-7d94-11eb-92d9-fa44200b4956.jpg)



## 마이크로 서비스 호출 흐름

- ascall 서비스 호출처리
수리기사호출(ascall)->수리기사관리(asmanage) 간의 호출처리 됨.
수리기사 할당에서 수리기사를 할당하여 호출 확정 상태가 됨.
두 개의 호출 상태
를 만듬.
```
http localhost:8081/ascalls tel="01023456789" status="호출" cost=25500

http localhost:8081/ascalls tel="01023456789" status="호출" cost=25500
```

![new_call1](https://user-images.githubusercontent.com/78134019/109960345-00c6e580-7d2c-11eb-8bda-d63e52de98c5.jpg)

![new_call2](https://user-images.githubusercontent.com/78134019/109960361-058b9980-7d2c-11eb-842b-80757d46e085.jpg)



호출 완료되면 [호출확정]상태가 됨



![new_호출확정](https://user-images.githubusercontent.com/78134019/109964239-c3b12200-7d30-11eb-89a0-bf8ed39892e1.jpg)


- ascall 서비스 호출 취소 처리

```
http delete http://localhost:8081/ascalls/1
```
호출이 취소 되면 수리기사 호출이 하나가 삭제 됨
![new_호출취소](https://user-images.githubusercontent.com/78134019/109964325-de839680-7d30-11eb-9903-0c375174f88b.jpg)



*********************************************
수리기사관리에서는 해당 호출에 대해서 호출취소로 상태가 변경 됨.

```
http localhost:8082/asmanages/
```
![new_호출취소결과](https://user-images.githubusercontent.com/78134019/109964411-fd822880-7d30-11eb-9c08-da784a06e741.jpg)


*******************************************************
- 고객 메시지 서비스 처리
고객(customer)는 호출 확정과 할당 확정에 대한 메시지를 다음과 같이 받을 수 있으며,
할당 된 수리기사의 정보를 또한 확인 할 수 있다.
파이썬으로 구현 하였음.

![파이썬_결과](https://user-images.githubusercontent.com/78134019/109964472-1559ac80-7d31-11eb-9ad7-07be720ade76.jpg)



## Gateway 적용

서비스에 대한 하나의 접점을 만들기 위한 게이트웨이의 설정은 8080로 설정 하였으며, 다음 마이크로서비스에 대한 설정 입니다.
```
수리기사호출 서비스 : 8081
수리기사관리 서비스 : 8082
수리기사할당 서비스 : 8084
```

gateway > applitcation.yml 설정

![new_게이트웨이](https://user-images.githubusercontent.com/78134019/110058120-25ab6f00-7da5-11eb-8079-8ab4b10fbc59.jpg)




gateway 테스트

```
http localhost:8080/ascalls
-> gateway 를 호출하나 8081 로 호출됨
```

![gateway_2](https://user-images.githubusercontent.com/78134019/109980280-11368a80-7d43-11eb-9981-cc97e14a12df.jpg)







## 동기식 호출 과 Fallback 처리

수리기사호출(ascall)->수리기사관리(asmanage) 간의 호출은 동기식 일관성을 유지하는 트랜잭션으로 처리함.
수리기사호출 프로토콜은 이미 앞서 Rest Repository 에 의해 노출되어있는 REST 서비스를 FeignClient 를 이용하여 호출하도록 한다. 


```
# external > AsmanageService.java



package ascenter.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name="asmanage", url="http://localhost:8082")
public interface AsmanageService {

    @RequestMapping(method= RequestMethod.POST, path="/asmanages")
    public void reqAsmanAssign(@RequestBody Asmanage asmanage);

}

```


```
# external > AsmanageServiceFallback.java


package ascenter.external;

import org.springframework.stereotype.Component;

@Component
public class AsmanageServiceFallback implements AsmanageService {
	 

	@Override
	public void reqAsAssign(Asmanage asmanage) {
		// TODO Auto-generated method stub
		System.out.println("Circuit breaker has been opened. Fallback returned instead. " + asmanage.getId());
	}

}



```



- 수리기사호출을 하면 수리기사관리가 호출되도록..(전화번호가 있을때)
```
# Ascall.java

 @PostPersist
    public void onPostPersist(){
    	
    	System.out.println("휴대폰번호 " + getTel());
        System.out.println("호출위치 " + getLocation());
        System.out.println("호출상태 " + getStatus());
        System.out.println("예상요금 " + getCost());
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.   	
    	if(getTel() != null)
		{
    		System.out.println("SEND###############################" + getId());
			Asmanage txMgr = new Asmanage();
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
	        AscallApplication.applicationContext.getBean(AsmanageService.class)
	        	.requestAsAssign(txMgr);;
		}

```



- 동기식 호출 적용으로 수리기사 관리 시스템이 정상적이지 않으면 , 수리기사콜도 접수될 수 없음을 확인 
```
# 수리기사 관리 시스템 down 후 ascall 호출 


http localhost:8081/ascalls tel="01023456789" status="호출" cost=25500
```

![동기식ㄷㄷㄷ](https://user-images.githubusercontent.com/78134019/110060791-cf8cfa80-7da9-11eb-8652-96a380007f2b.jpg)



```
# 수리기사 관리 (asmanage) 재기동 후 주문하기

#주문하기(order)
http localhost:8081/ascalls tel="01023456789" status="호출" cost=25500
```

![new_ggg](https://user-images.githubusercontent.com/78134019/109972354-894c8280-7d3a-11eb-9042-2b2d0cfc2f84.jpg)


-서킷브레이크와 fallback

```
소스 변경후
@FeignClient(name="asmanage", url="http://localhost:8082", fallback = AsmanageServiceFallback.class)
public interface AsmanageService {

    @RequestMapping(method= RequestMethod.POST, path="/asmanages")
    public void reqAsmanAssign(@RequestBody Asmanage asmanage);

}
```
수리기사관리 시스템 down 후 수리기사호출 시스템을 호출 하면 



http localhost:8081/ascalls tel="01023456789" status="호출" cost=25500


서비스에서는 영향이 없으며, 다음과 같이 fallback 됩니다.


![akakkk](https://user-images.githubusercontent.com/78134019/110060248-f4cd3900-7da8-11eb-9493-866fcd939a74.jpg)


![fallback_2](https://user-images.githubusercontent.com/78134019/109972444-a1240680-7d3a-11eb-985a-604ef1f1e20e.jpg)




## 비동기식 호출 / 장애격리  / 성능

수리기사 관리 (Asmanage) 이후 수리기사 할당(AsAssign) 은 비동기식 처리이므로 , 수리기사 호출(Ascall) 의 서비스 호출에는 영향이 없다
 
고객이 수리기사 호출(Ascall) 후 상태가 [호출]->[호출중] 로 변경되고 할당이 완료되면 [호출확정] 로 변경이 되지만 , 수리 할당(AsAssign)이 정상적이지 않으므로 [호출]로 남아있음. 
--> (시간적 디커플링)

<수리기사 호출  Ascall>

![비동기1](https://user-images.githubusercontent.com/78134019/109982314-201e3c80-7d45-11eb-9f4d-c2b840125c1e.jpg)



<수리기사 할당이 정상적이지 않아 호출중으로 남아있음>

![비동기2](https://user-images.githubusercontent.com/78134019/109982333-23b1c380-7d45-11eb-9029-5587bf70ba12.jpg)




## 성능 조회 / View 조회
고객이 호출한 모든 정보는 조회가 가능하다. 

![파이썬_결과](https://user-images.githubusercontent.com/78134019/109976695-6a042400-7d3f-11eb-8ac2-f2c1ccf0fc55.jpg)





# 운영

## Deploy / Pipeline

- az login
```
[
  {
    "cloudName": "AzureCloud",
    "homeTenantId": "6011e3f8-2818-42ea-9a63-66e6acc13e33",
    "id": "4fbdf9d7-3acd-463f-a273-dac3c2e16663",
    "isDefault": true,
    "managedByTenants": [],
    "name": "3",
    "state": "Enabled",
    "tenantId": "6011e3f8-2818-42ea-9a63-66e6acc13e33",
    "user": {
      "name": "skuser12@gkn2021hotmail.onmicrosoft.com",
      "type": "user"
    }
  }
]
```




- 리소스그룹생성
```
그룹명 : skuser12-rsrcgrp
```


- 클러스터 생성
```
클러스터 명 : skuser12-aks
```

- 토큰 가져오기
```
az aks get-credentials --resource-group skuser12-rsrcgrp --name skuser12-aks
```
![토큰가져오기](https://user-images.githubusercontent.com/78134019/109939151-1e885080-7d14-11eb-86ce-54198625a227.jpg)


- aks에 acr 붙이기
```
az aks update -n skuser12-aks -g skuser12-rsrcgrp --attach-acr skuser12
```
![acr붙이기](https://user-images.githubusercontent.com/78134019/109940026-fbaa6c00-7d14-11eb-995f-f54babebf3f8.jpg)


-네임스페이스만들기
```
kubectl create ns asman
kubectl get ns
```
![ns확인](https://user-images.githubusercontent.com/78134019/109940542-89865700-7d15-11eb-8ab2-4a3777ef02d2.jpg)


-git 에서 소스 가져와서 패키징 작업 하기

```
root@labs--952692809:/home/project/personal/asman2/asman/gateway# mvn clean && mvn package
```
![ascall_mvn](https://user-images.githubusercontent.com/78134019/109953456-6d89b200-7d23-11eb-9bf0-abd1e8bd7a36.jpg)



```
root@labs--952692809:/home/project/personal/asman2/asman/ascall# mvn clean && mvn package
```
![asmanage_mvn](https://user-images.githubusercontent.com/78134019/109953471-737f9300-7d23-11eb-882f-6adc0a982b84.jpg)


```
root@labs--952692809:/home/project/personal/asman2/asman/asmanage# mvn clean && mvn package
```
![asassign_mvn](https://user-images.githubusercontent.com/78134019/109953496-79757400-7d23-11eb-8da6-93375efe7f05.jpg)



```

도커 이미지 만들어서 올리기

cd gateway
az acr build --registry skuser12 --image skuser12.azurecr.io/gateway:v1 .
az acr build --registry skuser12 --image skuser12.azurecr.io/gateway:v2 .
cd ..
cd ascall
az acr build --registry skuser12 --image skuser12.azurecr.io/ascall:v1 .
cd ..
cd asmanage
az acr build --registry skuser12 --image skuser12.azurecr.io/asmanage:v1 .
cd ..
cd asassign
az acr build --registry skuser12 --image skuser12.azurecr.io/asassign:v1 .
cd ..

```

-deployment.yml을 사용하여 배포 
- deployment.yml 편집
```
   spec:
      containers:
        - name: ascall
          image: skuser12.azurecr.io/ascall:v2
          ports:
            - containerPort: 8080
```
![deploy_yml](https://user-images.githubusercontent.com/78134019/109977421-252cbd00-7d40-11eb-84aa-12269b42c07b.jpg)


- deployment.yml로 서비스 배포
```
cd app
kubectl apply -f kubernetes/deployment.yml
```


![apply_customer](https://user-images.githubusercontent.com/78134019/109977575-4beaf380-7d40-11eb-97e4-dd0be2b102cd.jpg)



![apply_gateway](https://user-images.githubusercontent.com/78134019/109977596-50171100-7d40-11eb-8888-fc5c74f62d60.jpg)



![apply_asassign](https://user-images.githubusercontent.com/78134019/109977620-56a58880-7d40-11eb-85ce-144101bd63a8.jpg)



![apply_asmanage](https://user-images.githubusercontent.com/78134019/109977683-6a50ef00-7d40-11eb-8beb-b23a8394af88.jpg)


배포 후 확인
```
kubectl get all
```

![kubectlgetall](https://user-images.githubusercontent.com/78134019/109977855-99fff700-7d40-11eb-9714-93d4ed36eb72.jpg)

azure potal 확인

![finalll](https://user-images.githubusercontent.com/78134019/109978635-6d001400-7d41-11eb-96e3-a45665bb7956.jpg)


## 동기식 호출 / 서킷 브레이킹 / 장애격리

* 서킷 브레이킹 프레임워크의 선택: Spring FeignClient + Hystrix 옵션을 사용하여 구현함

- Hystrix 를 설정:  요청처리 쓰레드에서 처리시간이 610 밀리가 넘어서기 시작하여 어느정도 유지되면 CB 회로가 닫히도록 (요청을 빠르게 실패처리, 차단) 설정
```
hystrix:
  command:
    # 전역설정
    default:
      execution.isolation.thread.timeoutInMilliseconds: 610

```
![hystric](https://user-images.githubusercontent.com/78134019/110054187-fe9d6f00-7d9d-11eb-9478-65607f89ab2c.jpg)



부하테스트


* Siege Run

```
kubectl run siege --image=apexacme/siege-nginx -n asman
```
![seige](https://user-images.githubusercontent.com/78134019/110054291-31476780-7d9e-11eb-8d5b-24ce390746cd.jpg)


* 실행

```
kubectl exec -it pod/siege-5459b87f86-68jmv -c siege -n asman -- /bin/bash
```

*부하 실행

```
siege -c200 -t60S -r10 -v --content-type "application/json" 'http://52.231.28.169:8080/ascalls POST {"tel": "0101231234"}'
```



**오토스케일


변경

![2222](https://user-images.githubusercontent.com/78134019/110092596-18aa7200-7ddd-11eb-8173-5f00606d1fa6.jpg)

kubectl autoscale deploy ascall --min=1 --max=10 --cpu-percent=15

![111](https://user-images.githubusercontent.com/78134019/110092613-1d6f2600-7ddd-11eb-95f2-149c6cea19c7.jpg)


부하주기
siege -c200 -t60S -r10 -v --content-type "application/json" 'http://ascall:8080/ascalls/ POST {"tel": "1234567890", "cost":3000}'


결과

![333](https://user-images.githubusercontent.com/78134019/110092812-5a3b1d00-7ddd-11eb-83be-45877fcdd127.jpg)


모니터링 결과

![4444](https://user-images.githubusercontent.com/78134019/110092837-5f986780-7ddd-11eb-888e-f5705ad26af4.jpg)


다시 부하를 주고 테스트하면 Availability가 100 !!!

![66666](https://user-images.githubusercontent.com/78134019/110093106-ae460180-7ddd-11eb-8576-5593329b902b.jpg)






