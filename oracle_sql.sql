//�û���
create table users(
   id number primary key,
   name nvarchar2(13),
   pssword nvarchar2(20),
   telep nvarchar2(20),
   addr  nvarchar2(30)
)

//�û�����
create sequence emp_usersq
increment by 1
start with 1

select * from users

//����û���Ϣ
insert into users values(emp_usersq.nextval,'tt','123','189888','�Ϻ�' );
insert into users(id,name,pssword,telep,addr) values(emp_usersq.nextval,'ff','123','189000','����' );
insert into users(id,name,pssword,telep,addr) values(emp_usersq.nextval,'jj','123','18934543','����' );
insert into users(id,name,pssword,telep,addr) values(emp_usersq.nextval,'kk','123','183435','������' );
insert into users values(emp_usersq.nextval,'aa','123','189888','�Ϻ�' );
insert into users(id,name,pssword,telep,addr) values(emp_usersq.nextval,'bb','123','189000','����' );
insert into users(id,name,pssword,telep,addr) values(emp_usersq.nextval,'cc','123','18934543','����' );
insert into users(id,name,pssword,telep,addr) values(emp_usersq.nextval,'dd','123','183435','������' );


delete from users


//�������Ա�
create table tensn(
   id number primary key,
   name nvarchar2(15)

)
//������������
create sequence em_tesnsque 
increment by 1
start with 1


select * from tensn 
//��Ӳ�����Ϣ
insert into tensn values(em_tesnsque.nextval,'fu')














