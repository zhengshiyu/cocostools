/**
 * Created by shiyu1 on 2017/5/4.
 */
module.exports = function(app) {
    return new Handler(app);
};

var Handler = function(app) {
    this.app = app;
};

/**
 * New client entry.
 *
 * @param  {Object}   msg     request message
 * @param  {Object}   session current session object
 * @param  {Function} next    next step callback
 * @return {Void}
 */
Handler.prototype.entry = function(msg, session, next) {
    console.log("game response is ok.");
    console.log(msg);
    console.log("this is my first server");
    next(null, {code: 200, msg: 'game response is ok.',type:"type"});
};

/**
 *
 * @param msg
 * @param session
 * @param next
 */
Handler.prototype.getName = function(msg, session, next) {
    console.log("game response is ok.")
    console.log(msg)
    var person={
        firstname : "John",
        lastname  : "Doe",
        id        :  5566,
        person    :{
            name:"wangbeibei",
            age:100
        }
    };
    console.log(JSON.stringify(person))
    next(null, {code: 200, msg: JSON.stringify(person)})
};

/**
 * New client entry.
 *
 * @param  {Object}   msg     request message
 * @param  {Object}   session current session object
 * @param  {Function} next    next step callback
 * @return {Void}
 */
Handler.prototype.login = function(msg,session,next)
{
    console.log("began to login")
    console.debug(msg);
    //console.log(msg.username);
    next(null, {code: 200, msg: 'this is my first server'});
}


