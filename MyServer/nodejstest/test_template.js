/**
 * Created by douzi on 2017/5/31.
 */
var ejs = require("ejs")
var fs = require("fs")
var model = exports
model.do = function () {
    var str = fs.readFileSync(__dirname + '/template/test_01.ejs', 'utf8');
    var html = ejs.render(str, {title:"我的网页"});


    for(var i = 0 ;i < 5 ; i ++)
    {
        var filepath = 'template/sample' + i + '.lua';
        var writeStram = fs.createWriteStream(filepath);
        writeStram.write(html);
        writeStram.end();
        console.log("生成模板完毕" + i)
    }


}