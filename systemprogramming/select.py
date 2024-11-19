

import pymysql
conn = pymysql.connect(host='127.0.0.1', user='root', password='mysql', db='classdb', charset='utf8')

cur = conn.cursor()

cur.execute("SELECT * FROM students")

print("%8s\t%15s\t%30s" % ("ID", "Name", "E-mail"))

while (True):
    row = cur.fetchone()
    if row == None:
        break
    print("%8s\t%15s\t%30s" % (row[0], row[1], row[2]))

conn.close()