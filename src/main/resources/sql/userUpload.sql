#Oracle
create table upload(
seq NUMBER primary key,
imageName varchar2(50),
imageContent varchar2(4000),
imageFileName varchar2(100) not null,
imageOriginFileName varchar2(100) not null);

create sequence SEQ_USERUPLOAD nocylce noache;


#MySQL
create table upload(
seq int(10) primary key auto_increment,
imageName varchar(50),
imageContent varchar(4000),
imageFileName varchar(100) not null,
imageOriginalFileName varchar(100) not null);
