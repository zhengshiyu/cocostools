#coding=utf-8
fo = open("zhengshiyu.txt","wb")
print fo.name
print fo.closed
print fo.mode
fo.write("zhengshiyu")


fo = open("zhengshiyu.txt","r+")
print fo.read()



import os
os.rename("zhengshiyu.txt","wangbeibei.txt")
fo = open("wangbeibei.txt","r+")
print fo.read()
fo.close()
os.remove("wangbeibei.txt")



print os.getcwd()