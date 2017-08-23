local BaseControl 			= require("script.mvc.framework.control.BaseControl")
local l_logic            	= require("script.oyeahgame.${folderName}.logic.${moduleName}Logic")
local l_moduleConfig    = require("script.mvc.instance.view.ModuleConfig")
l_moduleConfig = l_moduleConfig["moduleConfigAuto"]
local ${moduleName}Control 	= class("${moduleName}Control",BaseControl)
function ${moduleName}Control:ctor(name)
	if not name then
		name = "${moduleName}"
	end
	${moduleName}Control.super.ctor(self,name)
end
function ${moduleName}Control:createView(argu)
	--print("${moduleName}Control:createView()")
	<#list guiModelList as guiModel>
	<#if guiModel.getClassname()=="ProjectNode">	
	self.${guiModel.getName()} = require(l_moduleConfig.${guiModel.getControlClass()}.controlPath).new(l_framework.seekNodeByName(self, "${guiModel.getName()}"))
	self.${guiModel.getName()}.name="${moduleName}"
	self.${guiModel.getName()}:init()
		<#elseif guiModel.isHasName()>	
	self.${guiModel.getName()} = l_framework.seekNodeByName(${guiModel.getParentName()}, "${guiModel.getName()}")
		<#else>
	self.${guiModel.getName()} = l_framework.seekNodeByTag(${guiModel.getParentName()}, "${guiModel.getTag()}")
		</#if>
			<#if guiModel.isButton() >
	l_lang.setString(self.${guiModel.getName()},"${moduleName}_${guiModel.getName()}")
		<#elseif guiModel.isLabel()>
	l_lang.setString(self.${guiModel.getName()},"${moduleName}_${guiModel.getName()}")
		</#if>
	</#list>
	
	self.logic = l_logic.new()
    self.argu = argu
    self.logic:setView(self, self.name)
    ${moduleName}Control.super.createView(self,argu)
end
function ${moduleName}Control:onEnterTransitionFinish()
	${moduleName}Control.super.onEnterTransitionFinish(self)
	if self.isTransitionFinished then
		self.logic:updateView(self.argu)
	end
end

function ${moduleName}Control:reset()
	--print("${moduleName}Control:reset()")
	self.logic:reset()
end

function ${moduleName}Control:requestMsg()
	--print("${moduleName}Control:requestMsg()")
	self.logic:requestMsg()
end

function ${moduleName}Control:addSlot()
	--print("${moduleName}Control:addSlot()")
	self.logic:addSlot()
end

function ${moduleName}Control:removeSlot()
	--print("${moduleName}Control:removeSlot()")
	self.logic:removeSlot()
end

function ${moduleName}Control:addCallBack()
	--print("${moduleName}Control:addCallBack()")
	<#list guiModelList as guiModel>
		<#if guiModel.isButton()>
	l_framework.bindEventCallBack(self.logic,self.${guiModel.getName()},  self.logic.on${guiModel.getFunctionName()}Click, ccui.TouchEventType.ended)
		<#elseif guiModel.isTouchEnable()>
	l_framework.bindEventCallBack(self.logic,self.${guiModel.getName()},self.logic.on${guiModel.getFunctionName()}Click, ccui.TouchEventType.ended)
		</#if>
	</#list>
	self.logic:addCallBack()
end

function ${moduleName}Control:destoryModule()
	--print("${moduleName}Control:destoryModule()")
	<#list reversalguiModelList as guiModel>
	<#if guiModel.getClassname()=="ProjectNode">	
	self.${guiModel.getName()}:destoryModule()
	</#if>
	</#list>
	self.logic:destoryModule()
	${moduleName}Control.super.destoryModule(self)
end

function ${moduleName}Control:show(argu)
	self:setVisible(true)
	self.logic:show(argu)
end

function ${moduleName}Control:hide()
	self:setVisible(false)
	self.logic:hide()
end
return ${moduleName}Control