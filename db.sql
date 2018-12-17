create database provider_db;
use provider_db;

create table if not exists provider(
	id integer auto_increment primary key,
    name varchar(255) not null,
    address varchar(255) not null
);

insert into provider (name, address) values ('provider-teste-01', 'Araraquara');
insert into provider (name, address) values ('provider-teste-02', 'Araraquara');

select * from provider;

commit;


select * from mysql.user;


update user set password=PASSWORD("root") where User='root';