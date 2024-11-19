import pymysql
conn = pymysql.connect(host='127.0.0.1', user='root', password='mysql', db='classdb', charset='utf8')

# 커서 생성
cur = conn.cursor()

# 테이블 만들기
cur.execute("CREATE TABLE IF NOT EXISTS students ( id char(8), name char(15), email char(30) )")

# 데이터 입력
cur.execute("INSERT INTO students VALUES ( '32191111', 'John', 'john@naver.com' )")
cur.execute("INSERT INTO students VALUES ( '32202222', 'Kim', 'kim@daum.com' )")
cur.execute("INSERT INTO students VALUES ( '32183333', 'Lee', 'lee@outlook.com' )")
cur.execute("INSERT INTO students VALUES ( '32194444', 'Park', 'park@gmail.com' )")

# 입력한 데이터 저장
conn.commit()

# MYSQL 연결 종료
conn.close()