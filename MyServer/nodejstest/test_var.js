/**
 * Created by douzi on 2017/5/31.
 */
var model = exports
model.do = function () {
    var name = "zhengshiyu"
    console.log(name)
    var list = {
        name:"zhengshiyu",
        age:10,
        tag:"nodejs"
    }
    list.classname = "node js test"
    console.log(list)
    var array = new Array()
    array[0] = "zhengshiyu"
    array[1] = "wangbeibe"
    array[2] = 100
    console.log(array)
    for(key in array)
    {
        console.log(array[key])
    }

//元素后边添加
    array.push("zhengshiyu")
    array.splice(0,0,"hahah")
    console.log(array)
    array.splice(10,0,"zhengshiyuhhhhhh")
    console.log(array)
    for(var index = 0; index < 10 ; index ++)
    {
        array.splice(array.length + 1,0,"zhengshiyuhhhhhh" + index)
    }
    console.log(array)
}


