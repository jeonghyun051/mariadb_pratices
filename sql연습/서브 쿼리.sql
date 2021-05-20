-- subquery
-- 1) from절의 서브쿼리
select now() as n, sysdate() as b, 3+1 as c;

select s.n, s.b, s.c
from (select now() as n, sysdate() as b, 3+1 as c) s;

-- 2) where


-- 예제 현재 Fai Bale이 근무하는 부서에서 근무하는 직원의 사번, 전체 이름을 출력해보세요
select *
from employees
where dept_emp;

select * from departments;
select * from dept_emp;
select b.dept_no from employees a, dept_emp b where a.emp_no = b.emp_no and .first_name = 'Fai' and a.last_name = 'Bale';

select *
from employees a, dept_emp b
where a.emp_no = b.emp_no 
and b.to_date = '9999-01-01' and concat(a.first_name,' ',a.last_name) = 'Fai Bale';

select a.emp_no, a.first_name
from employees a, dept_emp b
where a.emp_no = b.emp_no
and b.dept_no = (select dept_no
				from employees a, dept_emp b
				where a.emp_no = b.emp_no 
				and b.to_date = '9999-01-01' and concat(a.first_name,' ',a.last_name) = 'Fai Bale');
                
                
-- 2-1) where 절의 서브쿼리 : 단일행
-- 단일행 연산자: =, >, <, <==, >==, <>, !=

-- 현재 전체사원의 평균 연봉보다 적은 급여를 받는 사원의  이름, 급여를 나타내세요.
select e.first_name, s.salary 
from salaries s, employees e 
where s.emp_no = e.emp_no 
and s.to_date='9999-01-01' 
and s.salary < (select avg(salary) from salaries where to_date = '9999-01-01')
order by s.salary desc;

select avg(salary) from salaries where to_date = '9999-01-01';	-- 72012.2359

-- 실습문제 2: 현재 가장적은 평균 급여를 받고 있는 직책의 평균 급여를 구하세요   
select * from dept_manager;
select * from salaries;
select * from titles;
select * from employees;

select min(b.직책별평균급여) 
from (select title, avg(salary) as '직책별평균급여' from titles t, salaries s where t.emp_no = s.emp_no group by title) b;

-- 1)
select x.title, min(x.t)
from ( 
select title, avg(salary) as t 
from titles t, salaries s 
where t.emp_no = s.emp_no and t.to_date='9999-01-01' 
group by title) x;

-- 2)
select min(a.avg_salary)
from (
	select title, avg(b.salary) as avg_salary
	from titles a, salaries b
	where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
	group by a.title
	order by avg_salary asc
) a;

-- 3) sol: top-k
select title, avg(b.salary) as avg_salary
from titles a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by a.title
order by avg_salary asc
limit 0,1;

select title, avg(b.salary) as avg_salary
from titles a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by a.title
having round(avg_salary) = (
	select min(a.avg_salary)
	from (
		select title, avg(b.salary) as avg_salary
		from titles a, salaries b
		where a.emp_no = b.emp_no
		and a.to_date = '9999-01-01'
		and b.to_date = '9999-01-01'
		group by a.title
) a);

-- sol2
-- 2) 
select min(a.avg_salary)
from (
	select title, avg(b.salary) as avg_salary
	from titles a, salaries b
	where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
	group by a.title
) a;

-- 2-2) where 절의 서브쿼리 : 다중행
-- 복수행 연산자 : in,not in, any, all
-- 단일행 연산자: =, >, <, >=, <=

-- any 사용법 
-- 1. =any : in과 동일
-- 2. >any, >=any:최소값
-- 3. <any, <=any:최대값
-- 4. <>any : not in 과 동일

-- all 사용법
-- 1. =all
-- 2. >all, >=all 최대값
-- 3. <all, <=call : 최소값

-- 예제 현재 급여가 50000 이상인 직원 이름 출력
select a.first_name, b.salary
from employees a, salaries b
where a.emp_no = b.emp_no 
	and b.to_date = '9999-01-01' 
	and (emp_no, salary) 
	in(
		select emp_no, salary
		from salaries
		where to_date = '9999-01-01'
		and salary > 50000
		)
order by b.salary asc;

select emp_no, salary
from salaries
where to_date = '9999-01-01'
and salary > 50000;

select * from salaries;

-- sol2 
select a.first_name, b.salary
from employees a, salaries b
where a.emp_no = b.emp_no and b.to_date = '9999-01-01' and b.salary > 50000
order by b.salary asc;

-- 실습문제4 각 부서별로 최고 월급을 받는 직원의 이름과 월급을 출력하시오
-- sol 1번 where subquery =any 
-- sol 2번 subquery 
select *
from salaries;
select * from departments;
select * from dept_emp;
select * from dept_manager;
select * from salaries;
select * from employees;

select a.w, e.first_name, a.q
from employees e , (
select s.emp_no as b, d.dept_name as w,de.dept_no, max(salary) as q 
from departments d, salaries s, dept_emp de
where de.emp_no = s.emp_no and de.dept_no = d.dept_no
group by dept_name) a
where e.emp_no = a.b;

select a.dept_no, max(b.salary)
from dept_emp a, salaries b
where a.emp_no = b.emp_no and a.to_date = '9999-01-01' and b.to_date = '9999-01-01'
group by a.dept_no;

select a.dept_no, c.first_name, b.salary 
from dept_emp a, salaries b, employees c
where a.emp_no = b.emp_no
and b.emp_no = c.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
and (a.dept_no, b.salary) =any(select a.dept_no, max(b.salary)
from dept_emp a, salaries b
where a.emp_no = b.emp_no and a.to_date = '9999-01-01' and b.to_date = '9999-01-01'
group by a.dept_no);

select a.dept_no, c.first_name, b.salary 
from dept_emp a, salaries b, employees c;

SELECT *   
   FROM employees 
   where emp_no =any ( SELECT emp_no FROM salaries WHERE salary < 50000 AND to_date = '9999-01-01' );
