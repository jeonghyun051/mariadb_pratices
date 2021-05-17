-- 테이블 삭제하기
drop table pet;

-- 업데이트
update pet set death = '2020-01-01' where name ='Bowser';

-- 테이블 만들기
create table pet (
	name varchar(20),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

-- schema 확인
desc pet;

-- 조회
select * from pet;
select name, owner, species, gender from pet;

-- 데이터 넣기(생성, create)
insert into pet values ('성탄이', '김정현', 'dog', 'm', '2018-05-11', null);

-- 데이터 삭제 (delete)
delete from pet where name = '성탄이';

-- 조회 연습1 : where 
-- 1990년 이후에 태어난 아이들은?
select * from pet where birth > '1990-12-31'; 

-- 주인이 Gwen인 동물
select * from pet where owner = 'Gwen';

-- null 다루기1 : 살아있는 애들은?
select * from pet where death is null;

-- 조회 연습2 : order by
select * from pet order by birth desc;

select * from pet;

-- like 검색 (패턴 매칭) : 이름이 b로 시작하는 아이들중에 이름이 6자인애?
select * from pet where name like 'b_____';

-- count 
select count(*) from pet;
select count(*) from pet where death is not null;