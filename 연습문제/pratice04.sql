-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
from salaries
where salary > (
	select avg(salary)
	from salaries
	);
    
    
-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 
-- 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
select *
from employees e , (
	select s.emp_no, s.salary, d.dept_name
	from departments d, salaries s, dept_emp de
	where s.to_date='9999-01-01' and de.to_date='9999-01-01' 
	and d.dept_no = de.dept_no and s.emp_no = de.emp_no
	group by dept_name
	) a
where e.emp_no = a.emp_no;


-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select e.emp_no, e.first_name, s.salary
from dept_emp ed, employees e, salaries s, (
	select d.dept_no,s.salary as 'sa'
	from salaries s, departments d, dept_emp de
	where s.emp_no = de.emp_no and de.dept_no = d.dept_no
	group by d.dept_name) a
where ed.emp_no = e.emp_no and e.emp_no = s.emp_no and a.dept_no = ed.dept_no
and s.salary > sa
order by s.salary asc;


-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select e.emp_no, e.first_name, d.dept_name, d.dept_no, a.first_name
from employees e, dept_emp de, departments d, (
	select dm.dept_no, e.first_name 
	from dept_manager dm, employees e
	where dm.emp_no = e.emp_no and dm.to_date='9999-01-01'
	) a
where e.emp_no = de.emp_no and de.dept_no = d.dept_no and d.dept_no = a.dept_no
order by emp_no;


-- 문제5.
-- 평균 연봉이 가장 높은 부서는(이름, 평균연봉)? 
select d.dept_name, avg(s.salary) as avg_salary
from salaries s, dept_emp de, departments d
where s.emp_no = de.emp_no and de.dept_no = d.dept_no and s.to_date='9999-01-01' and de.to_date='9999-01-01'
group by d.dept_name
order by avg_salary;

SELECT 
    d.dept_name, ROUND(AVG(b.salary)) AS avg_salary
FROM
    employees a,
    salaries b,
    dept_emp c,
    departments d
WHERE
    a.emp_no = b.emp_no
        AND a.emp_no = c.emp_no
        AND c.dept_no = d.dept_no
        AND b.to_date = '9999-01-01'
        AND c.to_date = '9999-01-01'
GROUP BY c.dept_no
HAVING avg_salary = (SELECT 
        MAX(avg_salary)
    FROM
        (SELECT 
            ROUND(AVG(b.salary)) AS avg_salary
        FROM
            employees a, salaries b, dept_emp c
        WHERE
            a.emp_no = b.emp_no
                AND a.emp_no = c.emp_no
                AND b.to_date = '9999-01-01'
                AND c.to_date = '9999-01-01'
        GROUP BY c.dept_no) a);

-- 문제6.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
select e.emp_no, e.first_name, t.title, s.salary
from dept_emp de, employees e, titles t, salaries s
where de.emp_no = e.emp_no and de.to_date='9999-01-01' and t.emp_no = e.emp_no and s.emp_no = e.emp_no 
and dept_no = 
	(select b.dept_no
	from (
		select a.dept_no as dept_no, max(a.avg_salary)
		from (
			select d.dept_no, d.dept_name, avg(s.salary) as avg_salary
			from salaries s, dept_emp de, departments d
			where s.emp_no = de.emp_no and de.dept_no = d.dept_no and s.to_date='9999-01-01' and de.to_date='9999-01-01'
			group by d.dept_name
			order by avg_salary
			) a
	) b)
order by salary desc;


-- 문제7.
-- 평균 연봉이 가장 높은 직책?
select t.title, avg(s.salary) as avg_salary
from titles t, salaries s
where t.emp_no = s.emp_no and t.to_date='9999-01-01' and s.to_date='9999-01-01'
group by t.title
having avg_salary = (
	select max(a.avg_salary)
	from (
		select t.title as title, avg(salary) as avg_salary
		from titles t, salaries s
		where t.emp_no = s.emp_no and t.to_date = '9999-01-01' and s.to_date = '9999-01-01'
		group by t.title
		order by avg_salary desc) a);


-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
select d.dept_name, concat(e.first_name,'',e.last_name) as 사원이름, s.salary, a.name, a.salary as '매니저 월급'
from employees e, salaries s, dept_emp de, departments d, (
	select dm.dept_no as dept_no, s.salary as salary, concat(e.first_name,' ',e.last_name) as name
	from employees e, dept_manager dm, salaries s
	where e.emp_no = dm.emp_no and e.emp_no = s.emp_no and dm.to_date='9999-01-01' and s.to_date='9999-01-01') a
where e.emp_no = s.emp_no and e.emp_no = de.emp_no and de.dept_no = d.dept_no 
and s.to_date='9999-01-01' and de.to_date='9999-01-01' and d.dept_no = a.dept_no and s.salary > a.salary
order by s.salary desc;

SELECT 
    f.dept_name AS '부서이름',
    a.first_name AS '사원이름',
    d.salary AS '연봉',
    g.first_name AS '매니저 이름',
    e.salary AS '매니저 연봉'
FROM
    employees a,
    dept_emp b,
    dept_manager c,
    salaries d,
    salaries e,
    departments f,
    employees g
WHERE
    a.emp_no = b.emp_no
        AND c.dept_no = b.dept_no
        AND a.emp_no = d.emp_no
        AND c.emp_no = e.emp_no
        AND c.dept_no = f.dept_no
        AND c.emp_no = g.emp_no
        AND b.to_date = '9999-01-01'
        AND c.to_date = '9999-01-01'
        AND d.to_date = '9999-01-01'
        AND e.to_date = '9999-01-01'
        AND d.salary > e.salary
        order by d.salary desc;