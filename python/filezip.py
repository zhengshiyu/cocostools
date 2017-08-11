#!/usr/bin/env python
# encoding: utf-8


"""
@version: ??
@author: zhengshiyu
@time: 2017/2/26 PM5:33
"""


#coding=utf-8
import zipfile
import os



def mikzip(souce_dir,output_filename):
    zipf = zipfile.ZipFile(output_filename,"w")
    pre_len = len(os.path.dirname(souce_dir))
    for parent,dirnames,filenames in os.walk(souce_dir):
        for filename in filenames:
            pathfile = os.path.join(parent,filename)
            arcname = pathfile[pre_len:].strip(os.path.sep)  # 相对路径
            zipf.write(pathfile, arcname)
    zipf.close()
class Main():
    def __init__(self):
        pass


if __name__ == '__main__':
    mikzip("/Users/douzi/Desktop/pythondemo","zip.zip")