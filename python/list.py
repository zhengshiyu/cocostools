data = ["zhengshiyu",1,1,1,1,"wangbeiubei"]
print data



del data[1]
print data
del data[0]
print data

print (1 in data)
print len(data)
del data[len(data) - 1]
print data
print min(data)
print max(data)

data = (1,1,1,1,1)
print list(data)
data2 = [1,1,1,1,1]
print cmp(data,data2)
print(data2.count(1))


data = {"name":"name"}
print data["name"]



