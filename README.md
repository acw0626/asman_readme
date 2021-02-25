![image](https://user-images.githubusercontent.com/487999/79708354-29074a80-82fa-11ea-80df-0db3962fb453.png)

# �ýú�

�� ������ MSA/DDD/Event Storming/EDA �� �����ϴ� �м�/����/����/� ���ܰ踦 Ŀ���ϵ��� ������ �����Դϴ�.
�̴� Ŭ���� ����Ƽ�� ���ø����̼��� ���߿� �䱸�Ǵ� üũ����Ʈ���� ����ϱ� ���� ���� ����� �����մϴ�.
- üũ����Ʈ : https://workflowy.com/s/assessment-check-po/T5YrzcMewfo4J6LW


# Table of contents

- [���� - ���Ĺ��](#---)
  - [���� �ó�����](#����-�ó�����)
  - [üũ����Ʈ](#üũ����Ʈ)
  - [�м�/����](#�м�����)
  - [����:](#����-)
    - [DDD �� ����](#ddd-��-����)
    - [�����۶� �۽ý��Ͻ�](#�����۶�-�۽ý��Ͻ�)
    - [�����۶� ���α׷���](#�����۶�-���α׷���)
    - [����� ȣ�� �� Fallback ó��](#�����-ȣ��-��-Fallback-ó��)
    - [�񵿱�� ȣ�� �� Eventual Consistency](#�񵿱��-ȣ��-��-Eventual-Consistency)
  - [�](#�)
    - [CI/CD ����](#cicd����)
    - [����� ȣ�� / ��Ŷ �극��ŷ / ��ְݸ�](#�����-ȣ��-��Ŷ-�극��ŷ-��ְݸ�)
    - [���佺���� �ƿ�](#���佺����-�ƿ�)
    - [������ �����](#������-�����)
  - [�ű� ���� ������ �߰�](#�ű�-����-������-�߰�)
- [����](#����)

# ���� �ó�����

īī��T ������� - https://www.kakaomobility.com/contents/10

����� �䱸����

1. ���� �ýõ���� �����ϰ� �ýÿ����� ��û�Ѵ�.() = ���� �ýñ�� �Ҵ��� ��û�Ѵ�.
1. ���� ����� �����Ѵ�.
1. �ýñ������� ����ġ�� �� �������� �Բ� �ýñ�縦 �����Ѵ�.
1. �ýñ������� �� ��ġ���� ���� ����� �ýø� �����Ѵ�.
1. �ý������� �ش� �ýñ�翡�� �Ҵ������ �Ѵ�.
1. �ýñ��� �Ҵ������ Ȯ���ϰ�, �� ��ġ�� ����Ѵ�.
1. ���� �ýÿ����� ��� �� �� �ִ�.
1. ���� ��Ҵ� �Ҵ���� �� 10�� ���� ��� �� �� �ִ�.
1. ������ ��� �Ǹ� �ýñ�翡�� ��Ҿ˸��� ������.
1. ���� �ýñ�� ��ġ�� �߰��߰� ��ȸ �Ѵ�.

������ �䱸����
1. Ʈ�����
    1. ������ �Ϸ���� ������ �ýñ�縦 ������ �� ����.  Sync ȣ�� 
1. ��ְݸ�
    1. �ý� ������ �ýñ�� ���� ����� �������� �ʴ���, 365�� 24�ð� ���� �� �־�� �Ѵ� Async (event-driven), Eventual  Consistency
    1. �����ý����� ���ߵǸ� ����ڸ� ��õ��� ���� �ʰ� ������ ����Ŀ� �ϵ��� �����Ѵ� Circuit breaker, fallback
1. ����
    1. �ý� ��簡 �Ҵ� �� ���� ��� �ýñ���� ��ġ�� Ȯ�� �� �� �־�� �Ѵ�.  Event driven


# üũ����Ʈ

- �м� ����


  - �̺�Ʈ�����: 
    - ��ƼĿ ���� ��ü�� �ǹ̸� ����� �����Ͽ� ���� ��Ű��ó���� ���� ���迡 ������ �ݿ��ϰ� �ִ°�?
    - �� ������ �̺�Ʈ�� �ǹ��ִ� �������� ���ǵǾ��°�?
    - ��׸�����: Command�� Event ���� ACID Ʈ����� ������ Aggregate �� ����� �����°�?
    - ����� �䱸���װ� ������ �䱸������ ���� ���� �ݿ��Ͽ��°�?    

  - ���� ������, �ٿ��� ���ؽ�Ʈ �и�
    - ���� KPI �� ���ɻ�, ������ �����ֱ� � ����  Sub-domain �̳� Bounded Context �� ������ �и��Ͽ��� �� �и� ������ �ո����� ����� ����Ǵ°�?
      - ��� 3�� �̻� ���� �и�
    - �����۶� ����: �� ����ũ�� ���񽺵��� ���� ��ǥ�� ��� Ư���� ���� ������ ��� Stack �� ����� ������ �پ��ϰ� ä���Ͽ� �����Ͽ��°�?
    - ���� �ó����� �� ACID Ʈ������� ũ��Ƽ���� Use ���̽��� ���Ͽ� �����ϰ� ���񽺰� �����ϰ� ������ �и����� �ʾҴ°�?
  - ���ؽ�Ʈ ���� / �̺�Ʈ �帮�� ��Ű��ó 
    - ���� �߿伺��  �����ΰ� ������ ������ �� �ִ°�? (Core, Supporting, General Domain)
    - Request-Response ��İ� �̺�Ʈ �帮�� ����� �����Ͽ� ������ �� �ִ°�?
    - ��ְݸ�: ������ ���񽺸� ���� �Ͽ��� ���� ���񽺿� ������ ������ �����Ͽ��°�?
    - �ű� ���񽺸� �߰� �Ͽ����� ���� ������ �����ͺ��̽��� ������ ������ ����(�����ִ� ��Ű��ó)�� �� �ִ°�?
    - �̺�Ʈ�� �����ø� �����ϱ� ���� Correlation-key ������ ����� �����Ͽ��°�?

  - ���� ��Ű��ó
    - ���� ����� ���� ���� ��Ű��ó ���̾�׷��� ����� �׷ȴ°�?
    
- ����
  - [DDD] �м��ܰ迡���� ��ƼĿ�� ����� ���� ��Ű��ó�� ���� ����ü�� ���εǰ� ���ߵǾ��°�?
    - Entity Pattern �� Repository Pattern �� �����Ͽ� JPA �� ���Ͽ� ������ ���� ����͸� �����Ͽ��°�
    - [���� ��Ű��ó] REST Inbound adaptor �̿ܿ� gRPC ���� Inbound Adaptor �� �߰��Կ� �־ ������ ���� �ջ��� ���� �ʰ� ���ο� �������ݿ� ���� ����ü�� ������ų �� �ִ°�?
    - �м��ܰ迡���� �������ͽ� ������ (�������忡�� ���� ���) �� ����Ͽ� �ҽ��ڵ尡 �����Ǿ��°�?
  - Request-Response ����� ���� �߽� ��Ű��ó ����
    - ����ũ�� ���񽺰� Request-Response ȣ�⿡ �־� ��� ���񽺸� ��� ������� ã�Ƽ� ȣ�� �Ͽ��°�? (Service Discovery, REST, FeignClient)
    - ��Ŷ�극��Ŀ�� ���Ͽ�  ��ָ� �ݸ���ų �� �ִ°�?
  - �̺�Ʈ �帮�� ��Ű��ó�� ����
    - ī��ī�� �̿��Ͽ� PubSub ���� �ϳ� �̻��� ���񽺰� �����Ǿ��°�?
    - Correlation-key:  �� �̺�Ʈ �� (�޽���)�� ��� �����ø� ó���Ҷ� � �ǿ� ����� ó���������� �����ϱ� ���� Correlation-key ������ ����� ���� �Ͽ��°�?
    - Message Consumer ����ũ�μ��񽺰� ��ֻ�Ȳ���� ���Ź��� ���ߴ� ���� �̺�Ʈ���� �ٽ� ���Ź޾� ó���ϴ°�?
    - Scaling-out: Message Consumer ����ũ�μ����� Replica �� �߰������� �ߺ����� �̺�Ʈ�� ������ �� �ִ°�
    - CQRS: Materialized View �� �����Ͽ�, Ÿ ����ũ�μ����� ������ ������ ���پ���(Composite ���񽺳� ����SQL �� ����) �� �� ������ ȭ�� ������ ���� ��ȸ�� �����Ѱ�?

  - �����۶� �÷α׷���
    - �� ����ũ�� ���񽺵��� �ϳ��̻��� ������ ��� Stack ���� �����Ǿ��°�?
    - �� ����ũ�� ���񽺵��� ������ ����� ������ ���������� ä���ϰ� ������ ����� ���� (RDB, NoSQL, File System ��)�� �����Ͽ� �����Ͽ��°�?
  - API ����Ʈ����
    - API GW�� ���Ͽ� ����ũ�� ���񽺵��� �������� ������ �� �ִ°�?
    - ����Ʈ���̿� ��������(OAuth), JWT ��ū ������ ���Ͽ� ����ũ�μ��񽺵��� ��ȣ�� �� �ִ°�?
- �
  - SLA �ؼ�
    - ��������: Liveness Probe �� ���Ͽ� ��� ������ health ���°� ���������� ���ϵʿ� ���� ��� �Ӱ�ġ���� pod �� ����Ǵ� ���� ������ �� �ִ°�?
    - ��Ŷ�극��Ŀ, ����Ʈ���� ���� ���� ��ְݸ��� ����ȿ���� ���� �� �ִ°�?
    - ���佺���Ϸ� (HPA) �� �����Ͽ� Ȯ���� ��� �����Ѱ�?
    - ����͸�, �ٷ���: 
  - ������ � CI/CD (10)
    - Readiness Probe �� ������ Rolling update�� ���Ͽ� �ű� ������ ������ ���񽺸� ���� �� �ִ� �����϶� �űԹ����� ���񽺷� ��ȯ���� siege ������ ���� 
    - Contract Test :  �ڵ�ȭ�� ��� �׽�Ʈ�� ���Ͽ� ���� ������ API ������ݸ� �̸� ���� �����Ѱ�?


# �м�/����


## AS-IS ���� (Horizontally-Aligned)
  ![image](https://user-images.githubusercontent.com/487999/79684144-2a893200-826a-11ea-9a01-79927d3a0107.png)

## TO-BE ���� (Vertically-Aligned)
  ![image](https://user-images.githubusercontent.com/487999/79684159-3543c700-826a-11ea-8d5f-a3fc0c4cad87.png)


## Event Storming ���
* MSAEz �� �𵨸��� �̺�Ʈ����� ���:  http://www.msaez.io/#/storming/AYDToXSmsXY4cguxhLA9jTpgvGc2/mine/6e22a051f267ea10acaf7c1e22c6632a
![image](screenshots/�ýð��̵�_�̺�Ʈ�����_���.png "�ýð��̵�_�̺�Ʈ�����_���")

### �̺�Ʈ ����
![image](https://user-images.githubusercontent.com/487999/79683604-47bc0180-8266-11ea-9212-7e88c9bf9911.png)

### ������ �̺�Ʈ Ż��
![image](https://user-images.githubusercontent.com/487999/79683612-4b4f8880-8266-11ea-9519-7e084524a462.png)

    - ������ ����� �߸��� ������ �̺�Ʈ���� �ɷ����� �۾��� ������
        - �ֹ���>�޴�ī�װ����õ�, �ֹ���>�޴��˻��� :  UI �� �̺�Ʈ����, �������� �ǹ��� �̺�Ʈ�� �ƴ϶� ����

### ����, Ŀ�ǵ� �����Ͽ� �б� ����
![image](https://user-images.githubusercontent.com/487999/79683614-4ee30f80-8266-11ea-9a50-68cdff2dcc46.png)

### ��׸��������� ����
![image](https://user-images.githubusercontent.com/487999/79683618-52769680-8266-11ea-9c21-48d6812444ba.png)

    - app�� Order, store �� �ֹ�ó��, ������ �����̷��� �׿� ����� command �� event �鿡 ���Ͽ� Ʈ������� �����Ǿ�� �ϴ� ������ �׵� ���� ������

### �ٿ��� ���ؽ�Ʈ�� ����

![image](https://user-images.githubusercontent.com/487999/79683625-560a1d80-8266-11ea-9790-40d68a36d95d.png)

    - ������ ���� �и� 
        - Core Domain:  app(front), store : ����� �ȵ� �ٽ� �����̸�, ���� Up-time SLA ������ 99.999% ��ǥ, �����ֱ�� app �� ��� 1���� 1ȸ �̸�, store �� ��� 1���� 1ȸ �̸�
        - Supporting Domain:   marketing, customer : ������� �������� �����̸�, SLA ������ ���� 60% �̻� uptime ��ǥ, �����ֱ�� �� ���� �����̳� ǥ�� ������Ʈ �ֱⰡ 1���� �̹Ƿ� 1���� 1ȸ �̻��� �������� ��.
        - General Domain:   pay : �������񽺷� 3rd Party �ܺ� ���񽺸� ����ϴ� ���� ������� ���� (��ũ������ ���� ��ȯ�� ����)

### ������ ���� (��ȣ�� ������ü, ������ ������ ��°�ܰ迡�� �س��� ��� ����. ��ü ���谡 �ʱ⿡ �巯��)

![image](https://user-images.githubusercontent.com/487999/79683633-5aced180-8266-11ea-8f42-c769eb88dfb1.png)

### �������� �̵��� ���ؽ�Ʈ ���� (������ Pub/Sub, �Ǽ��� Req/Resp)

![image](https://user-images.githubusercontent.com/487999/79683641-5f938580-8266-11ea-9fdb-4e80ff6642fe.png)

### �ϼ��� 1�� ����

![image](https://user-images.githubusercontent.com/487999/79683646-63bfa300-8266-11ea-9bc5-c0b650507ac8.png)

    - View Model �߰�

### 1�� �ϼ����� ���� �����/������ �䱸������ Ŀ���ϴ��� ����

![image](https://user-images.githubusercontent.com/487999/79684167-3ecd2f00-826a-11ea-806a-957362d197e3.png)

    - ���� �޴��� �����Ͽ� �ֹ��Ѵ� (ok)
    - ���� �����Ѵ� (ok)
    - �ֹ��� �Ǹ� �ֹ� ������ �����������ο��� ���޵ȴ� (ok)
    - ���������� Ȯ���Ͽ� �丮�ؼ� ��� ����Ѵ� (ok)

![image](https://user-images.githubusercontent.com/487999/79684170-47256a00-826a-11ea-9777-e16fafff519a.png)
    - ���� �ֹ��� ����� �� �ִ� (ok)
    - �ֹ��� ��ҵǸ� ����� ��ҵȴ� (ok)
    - ���� �ֹ����¸� �߰��߰� ��ȸ�Ѵ� (View-green sticker �� �߰��� ok) 
    - �ֹ����°� �ٲ� �� ���� ī������ �˸��� ������ (?)


### �� ����

![image](https://user-images.githubusercontent.com/487999/79684176-4e4c7800-826a-11ea-8deb-b7b053e5d7c6.png)
    
    - ������ ���� ��� �䱸������ Ŀ����.

### ���� �䱸���׿� ���� ����

![image](https://user-images.githubusercontent.com/487999/79684184-5c9a9400-826a-11ea-8d87-2ed1e44f4562.png)

    - ����ũ�� ���񽺸� �ѳ���� �ó������� ���� Ʈ����� ó��
        - �� �ֹ��� ����ó��:  ������ �Ϸ���� ���� �ֹ��� ���� ���� �ʴ´ٴ� �濵���� ���� �ų�(?) �� ����, ACID Ʈ����� ����. �ֹ��ͷ�� ����ó���� ���ؼ��� Request-Response ��� ó��
        - ���� �Ϸ�� ���ֿ��� �� ���ó��:  App(front) ���� Store ����ũ�μ��񽺷� �ֹ���û�� ���޵Ǵ� ������ �־ Store ����ũ�� ���񽺰� ������ �����ֱ⸦ ������ ������ Eventual Consistency ������� Ʈ����� ó����.
        - ������ ��� inter-microservice Ʈ�����: �ֹ�����, ��޻��� �� ��� �̺�Ʈ�� ���� ī���� ó���ϴ� ��, ������ �ϰ����� ������ ũ��Ƽ������ ���� ��� ��찡 ��κ��̶� �Ǵ�, Eventual Consistency �� �⺻���� ä����.




## ���� ��Ű��ó ���̾�׷� ����
    
![image](https://user-images.githubusercontent.com/487999/79684772-eba9ab00-826e-11ea-9405-17e2bf39ec76.png)


    - Chris Richardson, MSA Patterns �����Ͽ� Inbound adaptor�� Outbound adaptor�� ������
    - ȣ����迡�� PubSub �� Req/Resp �� ������
    - ���� �����ΰ� �ٿ��� ���ؽ�Ʈ�� �и�:  �� ���� KPI ���� �Ʒ��� ���� ���� ���� ���丮�� ��������


# ����:

�м�/���� �ܰ迡�� ����� ���� ��Ű��ó�� ����, �� BC���� �뺯�Ǵ� ����ũ�� ���񽺵��� ��������Ʈ�� ���̼����� �����Ͽ���. ������ �� ���񽺸� ���ÿ��� �����ϴ� ����� �Ʒ��� ���� (������ ��Ʈ�ѹ��� 8081 ~ 808n �̴�)

```
cd app
mvn spring-boot:run

cd pay
mvn spring-boot:run 

cd store
mvn spring-boot:run  

cd customer
python policy-handler.py 
```

## DDD �� ����

- �� ���񽺳��� ����� �ٽ� Aggregate Root ��ü�� Entity �� �����Ͽ���: (���ô� pay ����ũ�� ����). �̶� ������ �������� ����ϴ� ��� (�������ͽ� ������)�� �״�� ����Ϸ��� ����ߴ�. ������, �Ϻ� ������ �־ ������ �ƴ� ���� ������ �Ұ����� ��찡 �ֱ� ������ ��� ����� ����� �ƴѰ� ����. (Maven pom.xml, Kafka�� topic id, FeignClient �� ���� id ���� �ѱ۷� �ĺ��ڸ� ����ϴ� ��� ������ �߻��ϴ� ���� Ȯ���Ͽ���)

```
package fooddelivery;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="�����̷�_table")
public class �����̷� {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String orderId;
    private Double �ݾ�;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Double get�ݾ�() {
        return �ݾ�;
    }

    public void set�ݾ�(Double �ݾ�) {
        this.�ݾ� = �ݾ�;
    }

}

```
- Entity Pattern �� Repository Pattern �� �����Ͽ� JPA �� ���Ͽ� �پ��� �����ͼҽ� ���� (RDB or NoSQL) �� ���� ������ ó���� ������ ������ ���� ����͸� �ڵ� �����ϱ� ���Ͽ� Spring Data REST �� RestRepository �� �����Ͽ���
```
package fooddelivery;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface �����̷�Repository extends PagingAndSortingRepository<�����̷�, Long>{
}
```
- ���� �� REST API �� �׽�Ʈ
```
# app ������ �ֹ�ó��
http localhost:8081/orders item="���"

# store ������ ���ó��
http localhost:8083/�ֹ�����s orderId=1

# �ֹ� ���� Ȯ��
http localhost:8081/orders/1

```


## �����۶� �۽ý��Ͻ�

������Ʈ (app) �� ���� Ư���� ���� ������� ���԰� ��ǰ ������ �پ��� �������� �����ؾ� �ϴ� Ư¡���� ���� RDB ���ٴ� Document DB / NoSQL �迭�� �����ͺ��̽��� Mongo DB �� ����ϱ�� �Ͽ���. �̸� ���� order �� ���𿡴� @Entity �� �ƴ� @Document �� ��ŷ�Ǿ�����, ���ٸ� �۾����� ������ Entity Pattern �� Repository Pattern ����� �����ͺ��̽� ��ǰ�� ���� (application.yml) ������ MongoDB �� �������״�

```
# Order.java

package fooddelivery;

@Document
public class Order {

    private String id; // mongo db ����ÿ� id �� ���������� key�� �ڵ� �߱޵Ǵ� �ʵ�� ������ @Id �� @GeneratedValue �� ���� �ʾƵ� �ȴ�.
    private String item;
    private Integer ����;

}


# �ֹ�Repository.java
package fooddelivery;

public interface �ֹ�Repository extends JpaRepository<Order, UUID>{
}

# application.yml

  data:
    mongodb:
      host: mongodb.default.svc.cluster.local
    database: mongo-example

```

## �����۶� ���α׷���

������ ����(customer)�� �ó������� �ֹ�����, ��޻��� ���濡 ���� ������ ī��޽��� ������ ����� ���� ��Ʈ�� �ش� ���� python �� �̿��Ͽ� �����ϱ�� �Ͽ���. �ش� ���̽� ����ü�� �� �̺�Ʈ�� �����Ͽ� ó���ϴ� Kafka consumer �� �����Ǿ��� �ڵ�� ������ ����:
```
from flask import Flask
from redis import Redis, RedisError
from kafka import KafkaConsumer
import os
import socket


# To consume latest messages and auto-commit offsets
consumer = KafkaConsumer('fooddelivery',
                         group_id='',
                         bootstrap_servers=['localhost:9092'])
for message in consumer:
    print ("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition,
                                          message.offset, message.key,
                                          message.value))

    # ī��ȣ�� API
```

���̼� ���ø����̼��� �������ϰ� �����ϱ� ���� ��Ŀ������ �Ʒ��� ���� (��ܰ迡�� �����ΰ�? �ƴϴ� ���� ������ �����ڰ� �����̴�. Immutable Image):
```
FROM python:2.7-slim
WORKDIR /app
ADD . /app
RUN pip install --trusted-host pypi.python.org -r requirements.txt
ENV NAME World
EXPOSE 8090
CMD ["python", "policy-handler.py"]
```


## ����� ȣ�� �� Fallback ó��

�м��ܰ迡���� ���� �� �ϳ��� �ֹ�(app)->����(pay) ���� ȣ���� ����� �ϰ����� �����ϴ� Ʈ��������� ó���ϱ�� �Ͽ���. ȣ�� ���������� �̹� �ռ� Rest Repository �� ���� ����Ǿ��ִ� REST ���񽺸� FeignClient �� �̿��Ͽ� ȣ���ϵ��� �Ѵ�. 

- �������񽺸� ȣ���ϱ� ���Ͽ� Stub�� (FeignClient) �� �̿��Ͽ� Service ���� �������̽� (Proxy) �� ���� 

```
# (app) �����̷�Service.java

package fooddelivery.external;

@FeignClient(name="pay", url="http://localhost:8082")//, fallback = �����̷�ServiceFallback.class)
public interface �����̷�Service {

    @RequestMapping(method= RequestMethod.POST, path="/�����̷�s")
    public void ����(@RequestBody �����̷� pay);

}
```

- �ֹ��� ���� ����(@PostPersist) ������ ��û�ϵ��� ó��
```
# Order.java (Entity)

    @PostPersist
    public void onPostPersist(){

        fooddelivery.external.�����̷� pay = new fooddelivery.external.�����̷�();
        pay.setOrderId(getOrderId());
        
        Application.applicationContext.getBean(fooddelivery.external.�����̷�Service.class)
                .����(pay);
    }
```

- ����� ȣ�⿡���� ȣ�� �ð��� ���� Ÿ�� Ŀ�ø��� �߻��ϸ�, ���� �ý����� ��ְ� ���� �ֹ��� ���޴´ٴ� ���� Ȯ��:


```
# ���� (pay) ���񽺸� ��� �������� (ctrl+c)

#�ֹ�ó��
http localhost:8081/orders item=��� storeId=1   #Fail
http localhost:8081/orders item=���� storeId=2   #Fail

#�������� ��⵿
cd ����
mvn spring-boot:run

#�ֹ�ó��
http localhost:8081/orders item=��� storeId=1   #Success
http localhost:8081/orders item=���� storeId=2   #Success
```

- ���� ������ ��û�ÿ� ���� ��ְ� ���̳� ó�� ������ �� �ִ�. (��Ŷ�극��Ŀ, ���� ó���� ��ܰ迡�� �����Ѵ�.)




## �񵿱�� ȣ�� / �ð��� ��Ŀ�ø� / ��ְݸ� / ���� (Eventual) �ϰ��� �׽�Ʈ


������ �̷���� �Ŀ� �����ý������� �̸� �˷��ִ� ������ ������� �ƴ϶� �� ��������� ó���Ͽ� ���� �ý����� ó���� ���Ͽ� �����ֹ��� ���ŷ ���� �ʾƵ��� ó���Ѵ�.
 
- �̸� ���Ͽ� �����̷¿� ����� ���� �Ŀ� ��ٷ� ���������� �Ǿ��ٴ� ������ �̺�Ʈ�� ī��ī�� �����Ѵ�(Publish)
 
```
package fooddelivery;

@Entity
@Table(name="�����̷�_table")
public class �����̷� {

 ...
    @PrePersist
    public void onPrePersist(){
        �������ε� �������ε� = new �������ε�();
        BeanUtils.copyProperties(this, �������ε�);
        �������ε�.publish();
    }

}
```
- ���� ���񽺿����� �������� �̺�Ʈ�� ���ؼ� �̸� �����Ͽ� �ڽ��� ��å�� ó���ϵ��� PolicyHandler �� �����Ѵ�:

```
package fooddelivery;

...

@Service
public class PolicyHandler{

    @StreamListener(KafkaProcessor.INPUT)
    public void whenever�������ε�_�ֹ���������(@Payload �������ε� �������ε�){

        if(�������ε�.isMe()){
            System.out.println("##### listener �ֹ��������� : " + �������ε�.toJson());
            // �ֹ� ������ �޾�����, �丮�� ���� �����ؾ���..
            
        }
    }

}

```
���� ������ ���ڸ�, ī�� ������ ���ִ� ��Ƽ�� �ް�, �丮�� ��ģ��, �ֹ� ���¸� UI�� �Է����״�, �켱 �ֹ������� DB�� �޾Ƴ��� ��, ���� ó���� �ش� Aggregate ������ �ϸ� �ǰڴ�.:
  
```
  @Autowired �ֹ�����Repository �ֹ�����Repository;
  
  @StreamListener(KafkaProcessor.INPUT)
  public void whenever�������ε�_�ֹ���������(@Payload �������ε� �������ε�){

      if(�������ε�.isMe()){
          ī������(" �ֹ��� �Ծ��! : " + �������ε�.toString(), �ֹ�.getStoreId());

          �ֹ����� �ֹ� = new �ֹ�����();
          �ֹ�.setId(�������ε�.getOrderId());
          �ֹ�����Repository.save(�ֹ�);
      }
  }

```

���� �ý����� �ֹ�/������ ������ �и��Ǿ�������, �̺�Ʈ ���ſ� ���� ó���Ǳ� ������, �����ý����� ���������� ���� ��� ������ ���¶� �ֹ��� �޴µ� ������ ����:
```
# ���� ���� (store) �� ��� �������� (ctrl+c)

#�ֹ�ó��
http localhost:8081/orders item=��� storeId=1   #Success
http localhost:8081/orders item=���� storeId=2   #Success

#�ֹ����� Ȯ��
http localhost:8080/orders     # �ֹ����� �ȹٲ� Ȯ��

#���� ���� �⵿
cd ����
mvn spring-boot:run

#�ֹ����� Ȯ��
http localhost:8080/orders     # ��� �ֹ��� ���°� "��۵�"���� Ȯ��
```


# �

## CI/CD ����


�� ����ü���� ������ source repository �� �����Ǿ���, ����� CI/CD �÷����� GCP�� ����Ͽ�����, pipeline build script �� �� ������Ʈ ���� ���Ͽ� cloudbuild.yml �� ���ԵǾ���.


## ����� ȣ�� / ��Ŷ �극��ŷ / ��ְݸ�

* ��Ŷ �극��ŷ �����ӿ�ũ�� ����: Spring FeignClient + Hystrix �ɼ��� ����Ͽ� ������

�ó������� �ܸ���(app)-->����(pay) ���� ������ RESTful Request/Response �� �����Ͽ� ������ �Ǿ��ְ�, ���� ��û�� ������ ��� CB �� ���Ͽ� ��ְݸ�.

- Hystrix �� ����:  ��ûó�� �����忡�� ó���ð��� 610 �и��� �Ѿ�� �����Ͽ� ������� �����Ǹ� CB ȸ�ΰ� �������� (��û�� ������ ����ó��, ����) ����
```
# application.yml

hystrix:
  command:
    # ��������
    default:
      execution.isolation.thread.timeoutInMilliseconds: 610

```

- ��ȣ�� ����(����:pay) �� ���� ���� ó�� - 400 �и����� ���� 220 �и� ���� �Դٰ��� �ϰ�
```
# (pay) �����̷�.java (Entity)

    @PrePersist
    public void onPrePersist(){  //�����̷��� ������ �� ������ �ð� ����

        ...
        
        try {
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
```

* �����׽��� siege ���� ���� ��Ŷ �극��Ŀ ���� Ȯ��:
- ���û���� 100��
- 60�� ���� �ǽ�

```
$ siege -c100 -t60S -r10 --content-type "application/json" 'http://localhost:8081/orders POST {"item": "chicken"}'

** SIEGE 4.0.5
** Preparing 100 concurrent users for battle.
The server is now under siege...

HTTP/1.1 201     0.68 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     0.68 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     0.70 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     0.70 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     0.73 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     0.75 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     0.77 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     0.97 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     0.81 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     0.87 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.12 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.16 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.17 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.26 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.25 secs:     207 bytes ==> POST http://localhost:8081/orders

* ��û�� �����Ͽ� CB�� ������ ��û�� ����

HTTP/1.1 500     1.29 secs:     248 bytes ==> POST http://localhost:8081/orders   
HTTP/1.1 500     1.24 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     1.23 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     1.42 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     2.08 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.29 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     1.24 secs:     248 bytes ==> POST http://localhost:8081/orders

* ��û�� ������� ������������, ������ �и� �ϵ��� ó���Ǿ���, ȸ�θ� �ݾ� ��û�� �ٽ� �ޱ� ����

HTTP/1.1 201     1.46 secs:     207 bytes ==> POST http://localhost:8081/orders  
HTTP/1.1 201     1.33 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.36 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.63 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.65 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.68 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.69 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.71 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.71 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.74 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.76 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     1.79 secs:     207 bytes ==> POST http://localhost:8081/orders

* �ٽ� ��û�� ���̱� �����Ͽ� �Ǵ� ó���ð��� 610 �и��� ��¦ �ѱ�� ���� => ȸ�� ���� => ��û ����ó��

HTTP/1.1 500     1.93 secs:     248 bytes ==> POST http://localhost:8081/orders    
HTTP/1.1 500     1.92 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     1.93 secs:     248 bytes ==> POST http://localhost:8081/orders

* �������� ���� ���� ȣ���� - (�Ǵ� (�������) ó���ð��� 610 �и� �̸����� ȸ��) => ��û ����

HTTP/1.1 201     2.24 secs:     207 bytes ==> POST http://localhost:8081/orders  
HTTP/1.1 201     2.32 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     2.16 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     2.19 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     2.19 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     2.19 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     2.21 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     2.29 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     2.30 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     2.38 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     2.59 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     2.61 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     2.62 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     2.64 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.01 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.27 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.33 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.45 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.52 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.57 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.69 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.70 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.69 secs:     207 bytes ==> POST http://localhost:8081/orders

* ���� �̷��� ������ ��� �ݺ��Ǹ鼭 �ý����� ���̳� �����̳� �ڿ� �Ҹ��� ���� ���� �� ���


HTTP/1.1 500     4.76 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.23 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.76 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.74 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.82 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.82 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.84 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.66 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     5.03 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.22 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.19 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.18 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.69 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.65 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     5.13 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.84 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.25 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.25 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.80 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.87 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.33 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.86 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.96 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.34 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 500     4.04 secs:     248 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.50 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.95 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.54 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     4.65 secs:     207 bytes ==> POST http://localhost:8081/orders


:
:

Transactions:		        1025 hits
Availability:		       63.55 %
Elapsed time:		       59.78 secs
Data transferred:	        0.34 MB
Response time:		        5.60 secs
Transaction rate:	       17.15 trans/sec
Throughput:		        0.01 MB/sec
Concurrency:		       96.02
Successful transactions:        1025
Failed transactions:	         588
Longest transaction:	        9.20
Shortest transaction:	        0.00

```
- ��ý����� ���� �ʰ� ���������� CB �� ���Ͽ� ������ ȸ�ΰ� ������ ������ �������鼭 �ڿ��� ��ȣ�ϰ� ������ ������. ������, 63.55% �� �����Ͽ���, 46%�� �����ߴٴ� ���� �� ��뼺�� �־� ���� �ʱ� ������ Retry ������ ���� Scale out (replica�� �ڵ��� �߰�,HPA) �� ���Ͽ� �ý����� Ȯ�� ���ִ� �ļ�ó���� �ʿ�.

- Retry �� ���� (istio)
- Availability �� ������ ���� Ȯ�� (siege)

### ���佺���� �ƿ�
�ռ� CB �� �ý����� �����ǰ� ��� �� �ְ� �������� ������� ��û�� 100% �޾Ƶ鿩���� ���߱� ������ �̿� ���� ����å���� �ڵ�ȭ�� Ȯ�� ����� �����ϰ��� �Ѵ�. 


- �������񽺿� ���� replica �� �������� �÷��ֵ��� HPA �� �����Ѵ�. ������ CPU ��뷮�� 15���θ� �Ѿ�� replica �� 10������ �÷��ش�:
```
kubectl autoscale deploy pay --min=1 --max=10 --cpu-percent=15
```
- CB ���� �ߴ� ��Ĵ�� ��ũ�ε带 2�� ���� �ɾ��ش�.
```
siege -c100 -t120S -r10 --content-type "application/json" 'http://localhost:8081/orders POST {"item": "chicken"}'
```
- ���佺������ ��� �ǰ� �ִ��� ����͸��� �ɾ�д�:
```
kubectl get deploy pay -w
```
- ������� �ð��� �帥 �� (�� 30��) ������ �ƿ��� �������� ���� Ȯ���� �� �ִ�:
```
NAME    DESIRED   CURRENT   UP-TO-DATE   AVAILABLE   AGE
pay     1         1         1            1           17s
pay     1         2         1            1           45s
pay     1         4         1            1           1m
:
```
- siege �� �α׸� ���Ƶ� ��ü���� �������� ������ ���� Ȯ�� �� �� �ִ�. 
```
Transactions:		        5078 hits
Availability:		       92.45 %
Elapsed time:		       120 secs
Data transferred:	        0.34 MB
Response time:		        5.60 secs
Transaction rate:	       17.15 trans/sec
Throughput:		        0.01 MB/sec
Concurrency:		       96.02
```


## ������ �����

* ���� ������ ������� 100% �Ǵ� ������ Ȯ���ϱ� ���ؼ� Autoscaler �̳� CB ������ ������

- seige �� �����۾� ������ ��ũ�ε带 ����͸� ��.
```
siege -c100 -t120S -r10 --content-type "application/json" 'http://localhost:8081/orders POST {"item": "chicken"}'

** SIEGE 4.0.5
** Preparing 100 concurrent users for battle.
The server is now under siege...

HTTP/1.1 201     0.68 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     0.68 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     0.70 secs:     207 bytes ==> POST http://localhost:8081/orders
HTTP/1.1 201     0.70 secs:     207 bytes ==> POST http://localhost:8081/orders
:

```

- ������������ ���� ����
```
kubectl set image ...
```

- seige �� ȭ������ �Ѿ�� Availability �� 100% �̸����� ���������� Ȯ��
```
Transactions:		        3078 hits
Availability:		       70.45 %
Elapsed time:		       120 secs
Data transferred:	        0.34 MB
Response time:		        5.60 secs
Transaction rate:	       17.15 trans/sec
Throughput:		        0.01 MB/sec
Concurrency:		       96.02

```
�����Ⱓ�� Availability �� ��� 100%���� 70% ��� �������� ���� Ȯ��. ������ �����Ƽ���� �����ϰ� ���� �÷��� ���񽺸� READY ���·� �ν��Ͽ� ���� ������ ������ ���̱� ����. �̸� �������� Readiness Probe �� ������:

```
# deployment.yaml �� readiness probe �� ����:


kubectl apply -f kubernetes/deployment.yaml
```

- ������ �ó������� ����� �� �� Availability Ȯ��:
```
Transactions:		        3078 hits
Availability:		       100 %
Elapsed time:		       120 secs
Data transferred:	        0.34 MB
Response time:		        5.60 secs
Transaction rate:	       17.15 trans/sec
Throughput:		        0.01 MB/sec
Concurrency:		       96.02

```

�����Ⱓ ���� Availability �� ��ȭ���� ������ ������ ������� ������ ������ Ȯ�ε�.


# �ű� ���� ������ �߰�

  ![image](https://user-images.githubusercontent.com/487999/79684133-1d6c4300-826a-11ea-94a2-602e61814ebf.png)


## ���������� �߰�
    - KPI: �ű� ���� ���Է� ����� ���� ���� �漺�� ���
    - ������ȹ ����ũ�� ����: ���� customer ����ũ�� ���񽺸� �μ��ϸ�, ���� ���� �� ���� ��õ ���� ���� ������ ����

## �̺�Ʈ ����� 
    ![image](https://user-images.githubusercontent.com/487999/79685356-2b729180-8273-11ea-9361-a434065f2249.png)


## ���� ��Ű��ó ��ȭ 

![image](https://user-images.githubusercontent.com/487999/79685243-1d704100-8272-11ea-8ef6-f4869c509996.png)

## ����  

������ ����ũ�� ���񽺿� ������ �߻���Ű�� �ʵ��� Inbund ��û�� REST �� �ƴ� Event �� Subscribe �ϴ� ������� ����. ���� ����ũ�� ���񽺿� ���Ͽ� ��Ű��ó�� ���� ����ũ�� ���񽺵��� �����ͺ��̽� ������ ������� �߰���. 

## ��� Retirement

Request/Response ������� �������� �ʾұ� ������ ���񽺰� ���̻� ���ʿ������� Deployment ���� ���ŵǸ� ���� ����ũ�� ���񽺿� � ���⵵ ���� ����.

* [��] ���� (pay) ����ũ�μ����� ��� API ��ȭ�� Retire �ÿ� app(�ֹ�) ����ũ�� ������ ������ �ʷ���:

��) API ��ȭ��
```
# Order.java (Entity)

    @PostPersist
    public void onPostPersist(){

        fooddelivery.external.�����̷� pay = new fooddelivery.external.�����̷�();
        pay.setOrderId(getOrderId());
        
        Application.applicationContext.getBean(fooddelivery.external.�����̷�Service.class)
                .����(pay);

                --> 

        Application.applicationContext.getBean(fooddelivery.external.�����̷�Service.class)
                .����2(pay);

    }
```

��) Retire ��
```
# Order.java (Entity)

    @PostPersist
    public void onPostPersist(){

        /**
        fooddelivery.external.�����̷� pay = new fooddelivery.external.�����̷�();
        pay.setOrderId(getOrderId());
        
        Application.applicationContext.getBean(fooddelivery.external.�����̷�Service.class)
                .����(pay);

        **/
    }
```

# ����

# ���� ��Ű��ó ���̾�׷� �ű�
    
![image](screenshots/haxagonal_arch_new.png "�űԴ��̾�׷�")

- �ڵ� ���� - http://www.msaez.io/
- ���� ���� - http://portal.azure.com
- ��Ŀ��� - https://hub.docker.com/?ref=login

- �� ����ũ�� ������ REST API �� �׽�Ʈ
```
# app ������ �ֹ�ó��
http localhost:8081/orders item="���"

# store ������ ���ó��
http localhost:8083/�ֹ�����s orderId=1

# �ֹ� ���� Ȯ��
http localhost:8081/orders/1

```

- ����Ʈ���̸� ���� REST API �� �׽�Ʈ
```
# app ������ �ֹ�ó��
http localhost:8088/orders item="���"

# store ������ ���ó��
http localhost:8088/�ֹ�����s orderId=1

# �ֹ� ���� Ȯ��
http localhost:8088/orders/1

```

 app_mongo�� ��� PostPersist �� �����ϱ� ���ؼ� AbstractMongoEventListener ���� ��� ���� OrderRepositoryListener Ŭ���� ���� ��
 id�� 0���� 99���� ���� ���ְ�, ���°� ���� ��� ���� ������ �޾� ������ �Ͽ���.
```
# OrderRepositoryListener.java (Listener)

    @Override
		public void onBeforeSave(BeforeSaveEvent<Order> event) {
			super.onBeforeSave(event);
			
			Order order = event.getSource();
			Document d = event.getDocument();
			
			if ( order.getId() == null ) {
				// TODO use a better UUID generator in production
				d.put("_id","" + (int)Math.floor(Math.random()*100) );
				//d.put("id","" + (int)Math.floor(Math.random()*100) );
			}
			
		}
		
		@Override
    public void onAfterSave(AfterSaveEvent<Order> event){
			super.onAfterSave(event);
			Order ordr = event.getSource();
			
	        //Following code causes dependency to external APIs
	        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
			//System.out.println("===========================STATUS==" + ordr.get����());
	        
			//if(!"��޽��۵�".equals(ordr.get����()))
			if(ordr.get����() == null)
			{
				fooddelivery.external.�����̷� �����̷� = new fooddelivery.external.�����̷�();
	
		        // this is Context Mapping (Anti-corruption Layer)
		        �����̷�.setOrderId(String.valueOf(ordr.getId()));
		        if(ordr.get����()!=null)
		            �����̷�.set�ݾ�(Double.valueOf(ordr.get����()));
		        
		        Application.applicationContext.getBean(fooddelivery.external.�����̷�Service.class)
		                .����(�����̷�);
			}
	
	
	    }
```

# ��Ŀ ����
    
![image](screenshots/��Ŀ����_General.png "��Ŀ����_General")

![image](screenshots/��Ŀ����_Resource.png "��Ŀ����_General")

![image](screenshots/��Ŀ����_Kubenetes.png "��Ŀ����_General")

# �н���õ����Ʈ:
http://msaschool.io
http://msaez.io
https://github.com/msa-ez