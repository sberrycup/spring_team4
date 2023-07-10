SELECT * FROM java505_team4_db.food_member;

-- 유저 삭제
delete from food_member 
Where member_num = 6;

-- 유저 추가
insert into
	food_member(member_email, member_pw, member_name)
VALUES
	('test8', '1234', '박준성');
    
-- select 수정
SELECT
	member_num, member_email, member_name, admin_yn
FROM
	food_member
WHERE
	admin_yn != 'Y'
ORDER BY
	member_email;
    
UPDATE
	food_member
SET
	admin_yn = 'f'
WHERE
	member_num = 9;
    
-- userDetail
SELECT
  member_email, member_pw, member_name, member_profile_name, member_profile_path
FROM
  food_member
WHERE
  member_email = 'test1';
    
UPDATE
	food_member
SET
	member_pw = '12345', member_name = '내정보 수정하기'
WHERE
	member_num = 9;
    
-- 파일 업로드가 안됨
INSERT into
	food_member(member_profile_name, member_profile_path)
VALUES
	('001.jpg','C:\upload');

UPDATE
	food_member
SET
	member_profile_name = 'zczcxzcx', member_profile_path = 'dasd'
WHERE
	member_email = 'test1231244';
    
    
    
    
    
    