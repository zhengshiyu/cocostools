#coding:utf-8
from flask import Flask
import md5
app = Flask(__name__)
 
@app.route("/zhengshiyu")
def hello():
    return "我的第一个网页程序" + genmd5("test.xlsx")
 
if __name__ == "__main__":
    app.run()