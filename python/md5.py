#coding:utf-8

import os, sys, json, hashlib
import time
import zipfile

global genmd5
# 生成文件的md5值
def genmd5(file_path):

    global g_files_maps
    res_file = open(file_path, "rb")
    file_content = res_file.read(os.path.getsize(file_path))
    res_file.close()

    # print "%-30s   %s" % (file_key, md5_str)

    return hashlib.md5(file_content).hexdigest().upper()


a = genmd5("test.xlsx")
print a