module(...,package.seeall)
--define the layerTag\controlPath\viewPath of module

TitleBar = {
    layerTag    = l_tagEnum.game.uiTag,
    controlPath = "script.oyeahgame.globle.control.TitleBarControl",
    viewPath    = "image/globle/TitleBar.csb",
    plist		={},
    isHide      = false,
    bgType      = l_bgEnum.BG_FULL,
    openSound   = l_soundEnum.OpenMusic_WINDOW,
    closeSound  = l_soundEnum.CloseMusic_CLOSE
}

HomeMain = {
    layerTag    = l_tagEnum.game.uiTag,
    controlPath = "script.oyeahgame.mainscene.control.HomeMainControl",
    viewPath    = "image/homemain/HomeMain.csb",
    plist		={},
    isHide      = false,
    bgType      = l_bgEnum.BG_FULL,
    openSound   = l_soundEnum.OpenMusic_WINDOW,
    closeSound  = l_soundEnum.CloseMusic_CLOSE
}

ChatView = {
    layerTag    = l_tagEnum.game.uiTag,
    controlPath = "script.oyeahgame.mainscene.control.ChatViewControl",
    viewPath    = "image/homemain/ChatView.csb",
    plist		={},
    isHide      = false,
    bgType      = l_bgEnum.BG_FULL,
    openSound   = l_soundEnum.OpenMusic_WINDOW,
    closeSound  = l_soundEnum.CloseMusic_CLOSE
}


