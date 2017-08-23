module(...,package.seeall)
--define the layerTag\controlPath\viewPath of module

<#list modules as module>
${module.moduleName} = {
    layerTag    = l_tagEnum.game.uiTag,
    controlPath = "script.oyeahgame.${module.folderName}.control.${module.moduleName}Control",
    viewPath    = "image/${module.jsonFileName}",
    plist		={${module.plist}},
    isHide      = false,
    bgType      = l_bgEnum.BG_FULL,
    openSound   = l_soundEnum.OpenMusic_WINDOW,
    closeSound  = l_soundEnum.CloseMusic_CLOSE
}

</#list>

