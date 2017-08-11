 # -*- coding: UTF-8 -*- 
 #导入模块
from wxpy import *
# 初始化机器人，扫码登陆
bot = Bot()

# 搜索名称含有 "游否" 的男性深圳好友
my_friend = bot.friends().search('海洋')[0]
#wxpy_groups = bot.groups().search('好大一个家')[0]

@bot.register(my_friend)
def handle_message(msg):
	print(msg)
	my_friend.send(msg)
embed()