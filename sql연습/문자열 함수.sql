-- select 연습
select dept_no, dept_name from departments;

-- concat, alias(as,생략가능)
select concat(first_name,':',last_name) as name, gender, hire_date from employees;
select concat(first_name,':',last_name) as 이름, gender as 성별, hire_date as 입사일 from employees;

-- distinct
select distinct(title) from titles;

-- 예제 1: titles 테이블에서 모든 직급의 이름 출력
select title from titles;

-- 예제 2: titles 테이블에서 직급은 어떤 것들이 있는지 직급이름을 한 번씩만

-- employees 테이블에서 직원의 전체이름, 성별, 입사일을 출력
select concat(first_name , ':' , last_name) as name, gender as 성별, hire_date as 입사일 from employees;

-- where 절 
-- 예제 : employees 테이블에서 1991년 이전에 입사한 직원의 이름, 성별, 입사일을 
select first_name, hire_date from employees where hire_date < '1991-01-01';

-- where 절 #2 논리연산자
-- 예제 : 1989년 이전에 입사한 여직원의 이름, 입사일을 출력
select first_name, hire_date from employees where gender='F' and hire_date < '1989-01-01';

-- 예제 : dept_emp 테이블에서 부서 번호가 d005나 d009에 속한 사원의 사번, 부서번호 출력
select emp_no, dept_no from dept_emp where dept_no in ('d005','d009');

-- 예제 : employees 테이블에서 1989년에 입사한 직원의 이름, 입사일을 출력  
select * from employees where hire_date like '1989%';

-- 남자 직원의 전체이름, 성별, 입사일 순으로 출력
select first_name, gender, hire_date from employees where gender = 'm' order by hire_date asc;

-- 직원들의 사번,월급을 사번, 월급순으로 출력하세요.
select emp_no, salary, from_date, to_date from salaries order by emp_no, salary;

-- 함수:문자열 함수
select upper('buSan'), upper('busan'), upper('Douzone');
select upper(first_name) from employees;

-- lower
select lower('buSan'), lower('busan'), lower('Douzone');
select lower(first_name) from employees;


-- substring(문장, index, length)
select substring('Happy Day', 3, 2);

-- 예제) 1989년 입사한 사원들의 이름, 입사일 출력
select first_name, hire_date from employees where '1989' = substring(hire_date,1, 4);

-- lpad, rpad
select lpad('1234', 10, '-');
select rpad('1234', 10, '-');

select emp_no, lpad(salary, 10, '*') from salaries where from_date like '2001-%';

-- trim, ltrim, rtrime (공백 없애기)
select concat('---', rtrim('   heloo   '), '---') rtrim, 
	   concat('---', ltrim('   heloo   '), '---') ltrim,
       concat('---', trim(both ' ' from '   heloo   '), '---') trim;
       
-- 함수 


