
#coding=utf-8

import re
import sys
import chardet
reload(sys)
sys.setdefaultencoding('utf-8')
desc = "我是正国人"
print chardet.detect(desc)
b = re.findall('[\x80-\xff].', desc)
print "this is svn test"
for k in b:
    print chardet.detect(k)
    print k.decode('windows-1252').encode('utf-8')




print sys.getfilesystemencoding()