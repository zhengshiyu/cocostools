/**
 * Created by douzi on 2017/5/18.
 */
var express = require('express');
var router = express.Router();
var exec = require('child_process').exec;
var path = require('path')
var commonds = require('../models/config')
var fs = require("fs")
/* GET home page. */
router.get('/', function(req, res, next) {
    var result = res.render('index.ejs', {ip: req.ip,title:"我的网页"});
});

//UI生成
router.get('/uitools',function (req,res,next) {
    console.log("running UITools.jar please hold on!!!")
    exec(commonds.UITOOLCOMMOND, {encoding: 'utf8'}, function(error, stdout, stderr){
        res.send(stdout);
        console.log("exec ok")
    });
});

//MD5TOOLS
router.get('/md5tools',function (req,res,next) {
    console.log("running md5tools .jar please hold on!!!")
    exec(commonds.MD5COMMOND, {encoding: 'utf8'}, function(error, stdout, stderr){
        res.send(stdout);
        console.log("exec ok")
    });
});

router.get('/startresserver',function (req,res,next) {
    console.log("running startresserver please hold on!!!")
    exec(commonds.STARTRESSERVER, {encoding: 'utf8'}, function(error, stdout, stderr){
        res.send(stdout);
        console.log("exec ok")
    });
});

router.get('/startpomeloserver',function (req,res,next) {
    console.log("running startpomeloserver please hold on!!!")
    exec(commonds.STARTPOMELOSERVER, {encoding: 'utf8'}, function(error, stdout, stderr){
        res.send(stdout);
        console.log("exec ok")
    });
});


//文件下载
router.get('/file/*', function(req, res, next) {
    // 实现文件下载
    var baseurl = "web-server/static/"
    console.log(baseurl + req.params[0])
    res.download(baseurl + req.params[0])
});

//显示文件服务器
router.get('/showfiles', function(req, res, next) {
    // 实现文件下载
    exec(commonds.SHOWSTATICDIR, {encoding: 'utf8'}, function(error, stdout, stderr){
        res.send(stdout);
        console.log("cd to static files")
        exec(commonds.SHOWALLFILES, {encoding: 'utf8'}, function(error, stdout, stderr){
            console.log(error)
            res.send(stdout);
            console.log("exec ok")
        });
    });
});

//多语言提取
module.exports = router;