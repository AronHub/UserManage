//用户表
create table users(
   id number primary key,
   name nvarchar2(13),
   pssword nvarchar2(20),
   telep nvarchar2(20),
   addr  nvarchar2(30)
)

//用户序列
create sequence emp_usersq
increment by 1
start with 1

select * from users

//添加用户信息
insert into users values(emp_usersq.nextval,'tt','123','189888','上海' );
insert into users(id,name,pssword,telep,addr) values(emp_usersq.nextval,'ff','123','189000','北京' );
insert into users(id,name,pssword,telep,addr) values(emp_usersq.nextval,'jj','123','18934543','东京' );
insert into users(id,name,pssword,telep,addr) values(emp_usersq.nextval,'kk','123','183435','哈尔滨' );
insert into users values(emp_usersq.nextval,'aa','123','189888','上海' );
insert into users(id,name,pssword,telep,addr) values(emp_usersq.nextval,'bb','123','189000','北京' );
insert into users(id,name,pssword,telep,addr) values(emp_usersq.nextval,'cc','123','18934543','东京' );
insert into users(id,name,pssword,telep,addr) values(emp_usersq.nextval,'dd','123','183435','哈尔滨' );


delete from users


//创建测试表
create table tensn(
   id number primary key,
   name nvarchar2(15)

)
//创建测试序列
create sequence em_tesnsque 
increment by 1
start with 1


select * from tensn 
//添加测试信息
insert into tensn values(em_tesnsque.nextval,'fu')














