傻逼王贝贝我的网页

1 var fs = require('fs');
2 var txt = "以上程序使用fs.readFileSync从源路径读取文件内容，并使用fs.writeFileSync将文件内容写入目标路径。";
3
4 //写入文件
5 fs.writeFile('message.txt', txt, function (err) {
6     if (err) throw err;
7     console.log('It\'s saved!'); //文件被保存
8 });
9
10 //读取文件
11 fs.readFile('message.txt', 'utf8', function (err, data) {
12     if (err) throw err;
13     console.log(data);
14 });