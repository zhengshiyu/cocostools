#coding:utf-8

import os, sys, json, hashlib
import time
import zipfile


g_old_file_maps = {}
g_files_maps = {}

# 生成文件的md5值
def genmd5(file_path):

    global g_files_maps
    res_file = open(file_path, "rb")
    file_content = res_file.read(os.path.getsize(file_path))
    res_file.close()

    # print "%-30s   %s" % (file_key, md5_str)

    return hashlib.md5(file_content).hexdigest().upper()

# 根据文件名生成 key值，格式为：[path_filename]
i = 0
def genkey(file_path):
    global i
    i = i + 1
    print("%4d: %s" % (i, file_path))
    file_key = file_path
    begin_pos = file_key.find("res")
    
    # 从res后面开始截取文件路径
    if begin_pos > 0:
        begin_pos = begin_pos + len("res") + 1
        file_key = file_key[begin_pos:]

    # 将'/'替换为'_'
    file_key = file_key.replace("/", "__")
    file_key = file_key.replace("\\", "__")

    return file_key

# 文件夹遍历
def traverse(file_path):
    
    global g_files_maps

    for item in os.listdir(file_path):
        sub_path = os.path.join(file_path, item)

        # 如果是文件夹，递归遍历
        if os.path.isdir(sub_path):
            traverse(sub_path)
        else:
        	# 根据后缀过滤文件
            if (sub_path[-4:].lower() == ".png") or \
                (sub_path[-6:].lower() == ".plist") or \
                (sub_path[-4:].lower() == ".fnt") or \
                (sub_path[-4:].lower() == ".zip") or \
                (sub_path[-4:].lower() == ".mp3") or \
                (sub_path[-5:].lower() == ".ccbi"):
        

                file_key = genkey(sub_path)
                file_md5 = genmd5(sub_path)
                        
                g_files_maps[file_key] = file_md5


#   开始
def start(dir_path):

#   文件映射【名称:md5】
    global g_files_maps
    g_files_maps = {}

#   遍历目录
    traverse(dir_path)

#   生成json字符串
    json_str = json.dumps(g_files_maps, sort_keys=False, indent=4)

#   写入文件
    f = open("files_md5.json", "w")
    f.write(json_str)
    f.close()

if __name__ == '__main__':
    start("../wuxia/res")
    print("------- over --------")
