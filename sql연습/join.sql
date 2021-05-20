use employees;
-- join

-- 예제1 employees 테이블과 titles 테이블를 join하여 직원의 이름과 직책을 모두 출력 하세요.
select *
from employees, titles
where employees.emp_no = titles.emp_no;	-- join

select e.first_name, t.title
from employees e, titles t
where e.emp_no = t.emp_no;

-- 예제2 employees 테이블과 titles 테이블를 join하여 직원의 이름과 직책을 출력하되 여성 엔지니어만 출력하세요. 
select e.emp_no, e.first_name, t.title, e.gender
from employees e, titles t
where e.emp_no = t.emp_no and e.gender = 'F' and t.title = 'Engineer';

-- 1) Natural join
-- 두 테이블에 공통 컬럼이 있으면 별다른 조건없이 묵시적으로 조인됨
-- 쓸일이 없음
select e.first_name, t.title
from employees e natural join titles t;
-- on.e.emp_no = b.emp_no; 이게 생략되어있음 Natural join 두 테이블에 같은 컬럼이 있으면 알아서 매칭

-- 2) 현재 근무하고 있는 직원의 이름과 타이틀, 월급을 출력하세요.
select count(*)
from titles t 
join salaries s on t.emp_no = s.emp_no;	-- 4638507

-- Natural join은 같은 컬럼을 다 묶기때문에 결론적으로 3개를 묶어 결과가 적게 나온다
select count(*)
from titles t join salaries s;	-- 

-- 2) join ~ using
select e.first_name, t.title
from employees e
join titles t using (emp_no);

-- 3) join ~ on 
select e.first_name, t.title
from employees e
join titles t on e.emp_no = t.emp_no;


-- outer join

-- insert into dept values(1,'총무');
-- insert into dept values(2,'개발');
-- insert into dept values(3,'영업');
-- insert into dept values(4,'기획');
select * from dept;

-- insert into emp values(1,'둘리',1);
-- insert into emp values(2,'마이콜',2);
-- insert into emp values(3,'또치',3);
-- insert into emp values(4,'길동',null);
select * from emp;

select e.name, ifnull(d.name, '없음') as '부서'
from emp e
left join dept d on e.dept_no = d.no;

select e.name, d.name
from emp e
right join dept d on e.dept_no = d.no;

-- 습문제 1:  현재 회사 상황을 반영한 직원별 근무부서를  사번, 직원 전체이름, 근무부서 형태로 출력해 보세요.
select a.emp_no, a.first_name, c.dept_name
from employees a, dept_emp b, departments c 
where a.emp_no = b.emp_no and b.dept_no = c.dept_no and b.to_date = '9999-01-01';

-- 실습문제 2:  현재 회사에서 지급되고 있는 급여체계를 반영한 결과를 출력하세요. 
-- 사번,  전체이름, 연봉  이런 형태로 출력하세요.    
select a.emp_no, a.first_name, b.salary
from employees a, salaries b
where a.emp_no = b.emp_no and b.to_date = '9999-01-01';
