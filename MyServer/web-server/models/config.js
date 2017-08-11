/**
 * Created by douzi on 2017/5/18.
 */
require('path')
var  basedir = __dirname;

var commonds = {
    UITOOLCOMMOND: 'java -jar /Users/douzi/Desktop/unity-work-space/unity-pomelo-server/tools/UITools.jar',
    STARTRESSERVER: 'python3  /Users/douzi/Desktop/python/pythonweb/pythonweb.py',
    STARTPOMELOSERVER: 'node /Users/douzi/Desktop/unity-work-space/unity-pomelo-server/MyServer/game-server/app.js',
    SHOWALLFILES:"http-server",
    SHOWSTATICDIR:"cd /Users/douzi/Desktop/unity-work-space/unity-pomelo-server/MyServer/web-server/static",
    MD5COMMOND: 'java -jar /Users/douzi/Desktop/unity-work-space/unity-pomelo-server/tools/Md5Entry.jar /Users/douzi/Desktop/python/pythonweb/files/1.1.1/ /Users/douzi/Desktop/python/pythonweb/files/',
}




module.exports = commonds