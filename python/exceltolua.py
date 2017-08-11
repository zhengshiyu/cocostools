#coding=utf-8

import xlrd

import sys
import types
reload(sys)
sys.setdefaultencoding('utf-8')

fileOutput = open('Configs.lua','w')

# 让py可以读取文件中的中文
import sys

# 可以在这里写一些固定的注释代码之类的
writeData = "-- @author:zhengshiyu\n\n\n"

workbook = xlrd.open_workbook('test.xlsx',"rb")


# for booksheet in workbook.sheets():
#     # print "Current Booksheet:[" + booksheet.name + "]"
#     writeData = writeData + 'SY' + booksheet.name + ' = {\n'

#     # 处理id和index的特殊行
        

#     for col in xrange(booksheet.ncols):
#         # 如果第二行为空，则此列为注释列
#         if not any(booksheet.cell(0,col).value) :
#             continue

#         # 第三行为参数类型定义
#         colType = booksheet.cell(2, col).value
#         for row in xrange(booksheet.nrows):
#             value = str(booksheet.cell(row, col).value)
#             if  row == 0 or row == 2:
#                 continue
#             elif  row == 1 :
#                 writeData = writeData + '\t' + '["' + value + '"]' + ' = ' + '{ '  
#             else :
#                 if   colType == "int" :
#                     writeData = writeData + str(int(booksheet.cell(row, col).value)) + ' , '
#                 else : 
#                     writeData = writeData + '"' + str(booksheet.cell(row, col).value) + '" , '
#         else :
#             writeData = writeData + '} ,\n'
#     else :
#         writeData = writeData + '}\n\n'
# else :
#     fileOutput.write(writeData)

# fileOutput.close()

def getdata():
    for booksheet in workbook.sheets():
        # print "Current Booksheet:[" + booksheet.name + "]"
        writeData = writeData + 'SY' + booksheet.name + ' = {\n'

        # 处理id和index的特殊行
            
        allkey = []
        for row in xrange(booksheet.nrows):
            # 如果第二行为空，则此列为注释列
            for col in xrange(booksheet.ncols):
                keys = booksheet.cell(row, col).value
                if row == 0:
                    allkey.append(keys)
                    #writeData = writeData + '\t' + '[' + value + ']' + ' = ' + '{ '
                else:
                    tag = ''
                    if col == 0:
                        writeData = writeData + '\t' + '[' + str(int(booksheet.cell(row, 0).value)) + ']' + ' = ' + '{'

                        if isinstance(keys,unicode):
                            writeData = writeData +  '\t' +  allkey[col] + '=' +  '"' + str(keys) + '"' + ',' + '\n'
                        else:
                            writeData = writeData +  '\t' +  allkey[col] + '=' + str(keys) + ',' + '\n'
                    else:
                        if isinstance(keys,unicode):
                            writeData = writeData +  '\t\t\t' +  allkey[col] + '=' +  '"' + str(keys) + '"' + ',' + '\n'
                        else:
                            writeData = writeData +  '\t\t\t' +  allkey[col] + '=' + str(keys) + ',' + '\n'
                    
            else :
                if row > 0:
                    writeData = writeData + '\t' + '} ,\n'
        else :
            writeData = writeData + '}\n\n'
    else :
        fileOutput.write(writeData)

    fileOutput.close()
    return writeData


if __name__ == '__main__':
    getdata()




