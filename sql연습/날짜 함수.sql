-- 함수 : 날짜 함수
-- CURDATE(), CURRENT_DATE
-- CURTIME(), CURRENT_TIME
select curdate(), current_date();	-- 2021-05-17
select curtime(), current_time();	-- 12:45:17

-- now vs sysdate
select now(), sysdate();	-- 2021-05-17 12:45:37
select now(), sleep(2), now();	-- now()는 쿼리가 들어갔을 때 시간으로 정해짐
select sysdate(), sleep(2), sysdate();	-- sysdate는 2초딜레이 지난 후에 시간이 나옴

-- date_format(date, format)
select date_format(now(), '%y년 %m월 %d일 %h시 %i분 %s초');	-- 21년 05월 17일 02시 10분 23초

-- period_diff 두 기간 사이의 차이 YYMM YYYYMM
-- 근무 개월 수를 출력
select first_name, last_name,
	period_diff(date_format(curdate(),'%y%m'),
    date_format(hire_date,'%y%m')) from employees;

select curdate();	-- 2021-05-17

-- date_add(=adddate), date_sub(subdate),
-- 날짜 date type(day, month, year) 형식으로 expr값을 더하거나 뺀다.
-- 예) 각 사원들의 근무 년 수가 5년이 되는 날은 언제 인가요
select first_name, hire_date, date_add(hire_date, interval 5 year) from employees;

-- cast
select cast('2021-05-07' as date);	-- 문자열 varchar을 타입 변경
select cast('12345' as int) + 10;
select now(), cast(now() as date);	-- date로 캐스팅해서 날짜까지만 뜸 2021-05-17
select cast(1-2 as unsigned);
select cast(cast(1-2 as unsigned) as signed);

-- mysql type
-- VARCHAR(가변), CHAR(크기 고정), text, CLOB 
-- int, integer, medium, big int, int(11)(100억)
-- float, double
-- date(날짜), datetime, time(시간만)
-- lob (Large OBject), CLOB, BLOB

