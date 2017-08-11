
#coding=utf-8
flag = False
name = 'luren'
if name == 'python':         # 判断变量否为'python'
    flag = True          # 条件成立时设置标志为真
    print 'welcome boss'    # 并输出欢迎信息
else:
    print name


age = 100
if age == 100:
	print "我100岁了"
elif age == 101:
	print "我没有100岁啊"

if age > 100 or age < 10:
	print "cjsdchjdiuh"
elif age == 100:
	print "zhengshiyu"

data = [100,1,100,1,1,12,3]
data1 = []
data2 = []
while len(data) > 0 :
	num = data.pop()
	if num % 2 == 0:
		data1.append(num)
	else:
		data2.append(num)

print data1
print data2


data = 100
while data == 101:
	print "zhengshiyu"
else:
		print "wangbeibei"





for index in [1,1,1,1,1]:
	print index




for index in "zhengshiyu":
	print index



















