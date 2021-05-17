-- 함수 : 수학 함수 

-- abs() 절대값 
select abs(-1), abs(1);	-- 1 1

-- mod() 나머지 값
select mod(10,3);	-- 1

-- floor(x) : x보다 크지 않은 가장 큰 정수를 반환
select floor(3.14);	-- 3

-- ceil(x) : x보다 큰 정수중에 가장 작은 정수를 반환
select ceil(3.14);	-- 4

-- round(x) : x에 가장 근접한 정수 반환
-- round(x, d) : x 값 중에서 소수점 d자리의 가장 근접한 실수 반환
select round(3.5);	-- 4
select round(1.298, 1);	-- 1.3

-- pow(x,y), power(x,y) x의 y승을 반환
select power(2,10), power(10,3);	-- 1024 , 1000

-- sign(x) : x가 양수이면 1, 음수이면 -1, 0이면 0
select sign(2), sign(-2), sign(0);	-- 1 , -1 , 0

-- greatest(x,y, ....), least(x,y, ....)
select greatest(10,40,20,30), least(10,240,20,30); -- 40 , 10
select greatest('b','A','C');	-- C

