-- 집계 쿼리 : select 절에 그룹 함수가 적응된 경우

select avg(salary) from salaries where emp_no = '10060';

-- select 절에 그룹 함수가 있는 경우, 어떤 컬럼도 select 절에 올 수 없다.
-- emp_no는 아무런 의미가 없다. - 오류
select emp_no, avg(salary) from salaries;

-- query 실행 순서 숙지
-- (1) from : 접근 테이블 확인
-- (2) where : 조건에 맞는 row 선택 -> 임시 테이블
-- (3) 집계 : 
-- (4) projection

select avg(salary) from salaries where '10600';

select avg(salary)
from salaries where to_date = '9999-01-01';	-- 현재

-- group by에 참여하고 있는 컬럼은 select 절에 올 수 있다.
select emp_no,avg(salary)
from salaries group by emp_no;

-- having : 집계가 끝난 후 having 있으면 실행 후 order 실행
-- 집계 결과 임시 테이블에서 로우를 선택해야 하는 경우 
-- 이미 where절은 실행이 되었기 때문에 having절에서 조건을 달아야 한다.
select emp_no,avg(salary)
from salaries group by emp_no 
having avg(salary) > 60000;

-- order by
select emp_no,sum(salary)
from salaries 
group by emp_no 
having sum(salary) > 60000
order by sum(salary) asc;

-- 예제 :
-- salaries : 테이블에서
-- 문법적으로 오류
-- 의미적으로 맞다. (where 절 때문에) 
SELECT emp_no, avg(salary) , min(salary), max(salary)
FROM salaries
WHERE emp_no = '10060';

select * from salaries;

