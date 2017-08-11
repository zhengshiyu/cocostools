#!/usr/bin/python
#coding=utf-8

#excel创建

import os
import re
import xlrd
import sys


#创建excel  /Users/douzi/Desktop
from xlwt import *
from xlutils.copy import copy
from datetime import datetime
path = "/Users/douzi/Desktop/zhengshiyu2.xls"
if not os.path.exists(path):
	w = Workbook(encoding='utf-8')    #创建一个工作簿
	a = "郑诗雨"
	ws = w.add_sheet('一个表')     #创建一个工作表
	lines = [0,1,2]
	for x in range(1,300):
		for i in lines:
			ws.write(x - 1, i,a)
	w.save(path)
else:
	print("Hello world")