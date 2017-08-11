class zhengshiyu(object):
	
	def __init__(self, arg):
		super(zhengshiyu, self).__init__()
		self.name = arg
		print "init"
	def getName(self):
			print self.name	



person = zhengshiyu("zhengshiyu")
person.getName()


class wangbeibei(zhengshiyu):
	"""docstring for ClassName"""
	def __init__(self, arg):
		super(zhengshiyu, self).__init__()
		self.name = arg




demo = wangbeibei("wangbeibei")
demo.getName()
