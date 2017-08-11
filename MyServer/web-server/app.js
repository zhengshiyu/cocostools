//code by shiyu
var express = require('express');
var path = require('path');
var index = require('./routes/index')
var app = express();
app.set('view engine', 'ejs');
app.set('views', __dirname + "/views")
app.use("/",index)

var server = app.listen(3000, function () {
    var host = server.address().address
    var port = server.address().port
    console.log("应用实例，访问地址为 http://%s:%s", "127.0.0.1", "3000")
})




