#coding= utf-8
from flask import *
import os
import subprocess
#import sqlite3
import http
app = Flask(__name__)
@app.route('/')
def index():
    return render_template("login.html")

@app.route('/telnet',methods=['GET','POST'])
def telnet():
    return ("开始监听")

@app.route('/hello',methods=['GET','POST'])
def hello():
    if request.method == "POST":
        p = subprocess.Popen("java -jar /Users/douzi/Desktop/UITools.jar",shell=True,stdout= subprocess.PIPE)
        return p.stdout.read()

@app.route('/md5tool',methods=['GET','POST'])
def md5tool():
    if request.method == "POST":
        p = subprocess.Popen('java -jar /Users/douzi/Desktop/Md5Entry.jar /Users/douzi/Desktop/python/pythonweb/files/1.1.1/ /Users/douzi/Desktop/python/pythonweb/files/',shell=True,stdout= subprocess.PIPE)
        return p.stdout.read()

@app.route('/langutil',methods=['GET','POST'])
def langutil():
    if request.method == "POST":
        p = subprocess.Popen('java -jar /Users/douzi/Desktop/LangUtilsjar.jar',shell=True,stdout= subprocess.PIPE)
        return p.stdout.read()

@app.route('/lookfile',methods=['GET','POST'])
def lookfile():
    if request.method == "POST":
        os.system("cd /Users/douzi/Desktop")
        os.system("python -m http.server")
        return redirect('http://www.baidu.com')

@app.route('/download/<path:filename>')
def download(filename):
    print("files/" + filename)
    response = make_response(send_file("files/" + filename))
    response.headers["Content-Disposition"] = "attachment; filename=" + filename + ";"
    return response

if __name__ == '__main__':
    #app.debug = True
    app.run(host='0.0.0.0',port=5000)
    print ("resouce server is runing")
