local BaseLogic = require("script.mvc.framework.control.BaseLogic")
local ${moduleName}Logic=class("${moduleName}Logic",BaseLogic)
function ${moduleName}Logic:ctor(...)
	${moduleName}Logic.super.ctor(self,...)
end
function ${moduleName}Logic:reset()
	self.view       = nil
	self.moduleName = nil
end

function ${moduleName}Logic:setView(view, moduleName)
	self.view = view
	self.moduleName = moduleName
end

function ${moduleName}Logic:updateView(argu)
    
end

function ${moduleName}Logic:show(argu)
	
end

function ${moduleName}Logic:hide()
	
end

function ${moduleName}Logic:requestMsg()
    --print("${moduleName}Logic.requestMsg()")
end

function ${moduleName}Logic:addCallBack()
    --print("${moduleName}Logic.addCallBack()")
end

function ${moduleName}Logic:addSlot()
    --print("${moduleName}Logic.addSlot()")
end

function ${moduleName}Logic:removeSlot()
    --print("${moduleName}Logic.removeSlot()")
end

function ${moduleName}Logic:destoryModule()
    --print("${moduleName}Logic.destoryModule()")
end


---------------------------------------button event----------------------------------------------------------------
<#list guiModelList as guiModel>
	<#if guiModel.isButton()>
	
function ${moduleName}Logic:on${guiModel.getFunctionName()}Click()
	print("on${guiModel.getFunctionName()}Click................")
end
<#elseif guiModel.isTouchEnable()>
function ${moduleName}Logic:on${guiModel.getFunctionName()}Click()
	print("on${guiModel.getFunctionName()}Click................")
end
	</#if>
</#list>

---------------------------------------singal function-------------------------------------------------------------
return ${moduleName}Logic