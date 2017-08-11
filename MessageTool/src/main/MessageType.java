package main;

/**
 * 定义消息类型,规则如下:
 * 
 * <pre>
 * 1.所有消息类型均为short类型，消息类型保证惟一
 * 2.系统内部消息以SYS_开头
 * 3.客户端发往GameServer的以CG_开头 
 * 4.GameServer发往客户端的以GC_开头 
 * 5.保留消息类型0-100,给系统内部一些特殊消息使用
 * 6.每个子系统或模块的消息类型定义应放在一起
 * </pre>
 * 
 */
public abstract class MessageType {
	/**
	 * 腾讯接入时候只能模拟http访问形式，所以客户端需要先发送HTTP包头 如下：<br/>
	 * tgw_l7_forward\r\nHost:app12345.qzoneapp.com:80\r\n\r\n<br/>
	 * 3,4字节中的'w_'用16进制表示是30559 77 5f
	 * */

	public static final short TENCENT_TGW = 0x775f;
	/** Flash socket 发送的policy request请求协议 "<policy" 中第3,4两个字节ol的16进制表示,28524 */
	public static final short FLASH_POLICY = 0x6f6c;
	public static final short MSG_UNKNOWN = 0;

	/* === 系统内部消息类型定义开始,范围0~100 === */
	public static final short SYS_SESSION_OPEN = 1;
	public static final short SYS_SESSION_CLOSE = 2;
	public static final short SYS_SCHEDULE = 3;
	public static final short SCHD_ASYNC_FINISH = 10;
	public static final short SCHD_PLAYER_ASYNC_FINISH = 11;
	public static final short SYS_TEST_MSG_LENGTH = 14;
	public static final short SYS_TEST_FLOOD_BYTE_ATTACK = 15;

	/* === 系统内部消息类型定义结束 === */

	// /////////////
	// 服务器内部状态
	// ////////////
	public static short STAUS_BEGIN = 400;

	private static short BASE_NUMBER = 500;
	/** 每个大系统分配的消息个数 */
	public static final short NUMBER_PER_SYS = 350;
	/** 每个子系统分配的消息个数 */
	public static final short NUMBER_PER_SUB_SYS = 53;

	// 需要向前合并的merge消息在此定义 保证messageType的正确性 直接输入数字
	public static short MERGE_BEGIN = 450;
	public static final short CG_PLATFORM_RECIEPT_VERIFY = 451;
	public static final short GC_PLATFORM_RECIEPT_VERIFY_RESULT = 452;

	// /////////////
	// 各模块通用的消息
	// ////////////
	public static short COMMON_BEGIN = BASE_NUMBER;
	public static final short CG_ADMIN_COMMAND = ++COMMON_BEGIN;
	public static final short GC_SYSTEM_MESSAGE = ++COMMON_BEGIN;
	public static final short GC_WAITING_START = ++COMMON_BEGIN;
	public static final short GC_WAITING_OVER = ++COMMON_BEGIN;
	public static final short GC_COMMON_ROLE_EFFECT = ++COMMON_BEGIN;
	public static final short GC_COMMON_CHARGE = ++COMMON_BEGIN;
	public static final short GC_PING = ++COMMON_BEGIN;
	public static final short CG_PING = ++COMMON_BEGIN;
	public static final short GC_SYSTEM_NOTICE = ++COMMON_BEGIN;
	public static final short CG_HANDSHAKE = ++COMMON_BEGIN;
	public static final short GC_HANDSHAKE = ++COMMON_BEGIN;
	public static final short GC_SHOW_OPTION_DLG = ++COMMON_BEGIN;
	public static final short CG_SELECT_OPTION = ++COMMON_BEGIN;
	public static final short GC_COMMON_ASK_AND_ANSWER_URL = ++COMMON_BEGIN;
	public static final short CG_JOIN_BATTLE = ++COMMON_BEGIN;
	public static final short CG_REQUEST_CONSUME = ++COMMON_BEGIN;
	public static final short CG_CONFIRM_CONSUME = ++COMMON_BEGIN;
	public static final short GC_CONSUME_COST = ++COMMON_BEGIN;
	public static final short CG_POP_MESSAGE = ++COMMON_BEGIN;
	public static final short GC_POP_MESSAGE = ++COMMON_BEGIN;
	public static final short CG_REQUEST_WEBURL = ++COMMON_BEGIN;
	public static final short GC_WEBURL_RESPONSE = ++COMMON_BEGIN;
	public static final short GC_ALERT_BOX = ++COMMON_BEGIN;
	public static final short GC_SYS_CONST = ++COMMON_BEGIN;// 游戏常量 一般指策划可配置的一些值
	public static final short CG_ALERT_BOX_CLICK = ++COMMON_BEGIN;
	public static final short GC_GAIN_NOTICE = ++COMMON_BEGIN;
	public static final short GC_FIRST_CHARGE_BONUS_STATE = ++COMMON_BEGIN;
	public static final short CG_DRAW_FIRST_CHARGE_BONUS = ++COMMON_BEGIN;
	public static final short GC_GAME_NOTIFICATION = ++COMMON_BEGIN;
	public static final short GC_SYSTEM_MESSAGES = ++COMMON_BEGIN ;
	public static final short GC_SYSTEM_FUNC_LIST = ++COMMON_BEGIN ;
	public static final short GC_SYSTEM_FUNC_CHANGE = ++COMMON_BEGIN;
	public static final short GC_PLAYER_RANK = ++COMMON_BEGIN;
	public static final short CG_PLAYER_RANK = ++COMMON_BEGIN;
	public static final short GC_LOGIC_OPERATE_FAIL = ++COMMON_BEGIN;
	public static final short CG_LIKE_PLAYER_RANK = ++COMMON_BEGIN;
	public static final short GC_PLAYER_RANK_CHANGE = ++COMMON_BEGIN;
	
	
	// /////////////
	// 玩家登录退出模块
	// ////////////
	public static short PLAYER_TEST_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_TEST_PLAYER_LOGIN = ++PLAYER_TEST_BEGIN;

	// /////////////
	// 玩家登录退出模块
	// ////////////
	public static short PLAYER_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_PLAYER_LOGIN = ++PLAYER_BEGIN;
	public static final short CG_PLAYER_COOKIE_LOGIN = ++PLAYER_BEGIN;
	public static final short GC_LOGIN_SUCCESS = ++PLAYER_BEGIN;
	public static final short CG_SELECT_ROLE = ++PLAYER_BEGIN;
	public static final short CG_CHANGE_NAME = ++PLAYER_BEGIN;
	public static final short GC_CHANGE_NAME = ++PLAYER_BEGIN;
	public static final short GC_ROLE_LIST = ++PLAYER_BEGIN;
	public static final short CG_CREATE_ROLE = ++PLAYER_BEGIN;
	public static final short CG_ROLE_ENTER = ++PLAYER_BEGIN;
	public static final short CG_LOGIN_GET_NAME = ++PLAYER_BEGIN;

	public static final short GC_LOGIN_GET_NAME = ++PLAYER_BEGIN;
	public static final short GC_RECOMMEND_COUNTRY = ++PLAYER_BEGIN;

	public static final short CG_INITROLE_TEMPLATE = ++PLAYER_BEGIN;
	public static final short GC_INITROLE_TEMPLATE = ++PLAYER_BEGIN;
	public static final short CG_MAINCITY_INFO = ++PLAYER_BEGIN;
	public static final short GC_MAINCITY_INFO = ++PLAYER_BEGIN;

	public static final short GC_SCENE_INFO = ++PLAYER_BEGIN;
	public static final short CG_ENTER_SCENE = ++PLAYER_BEGIN;
	public static final short CG_ENTER_GAME = ++PLAYER_BEGIN;
	public static final short GC_ENTER_GAME = ++PLAYER_BEGIN;
	public static final short GC_ENTER_SCENE = ++PLAYER_BEGIN;
	public static final short GC_FAILED_MSG = ++PLAYER_BEGIN;
	public static final short GC_NOTIFY_EXCEPTION = ++PLAYER_BEGIN;

	public static final short CG_PLAYER_CHARGE_DIAMOND = ++PLAYER_BEGIN;
	public static final short GC_PLAYER_CHARGE_DIAMOND = ++PLAYER_BEGIN;
	public static final short CG_PLAYER_QUERY_ACCOUNT = ++PLAYER_BEGIN;
	public static final short GC_PLAYER_QUERY_ACCOUNT = ++PLAYER_BEGIN;

	public static final short CG_COMMON_PLAYER_LOGIN = ++PLAYER_BEGIN;

	public static final short CG_PUBLIC_PLAYER_LOGIN = ++PLAYER_BEGIN;
	public static final short CG_APPCHARGE_KINDS = ++PLAYER_BEGIN;

	public static final short GC_OPEN_WEB_WINDOW = ++PLAYER_BEGIN;

	// 获取未使用快客
	public static final short CG_GET_UNUSED_QUICKER = ++PLAYER_BEGIN;
	// 获取未使用快客结果
	public static final short GC_GET_UNUSED_QUICKER_RESULT = ++PLAYER_BEGIN;
	// 快客创建
	public static final short CG_QUICKER_CREATE = ++PLAYER_BEGIN;
	// 快客创建结果
	public static final short GC_QUICKER_CREATE_RESULT = ++PLAYER_BEGIN;
	// 快客帮定
	public static final short CG_QUICKER_BAND = ++PLAYER_BEGIN;
	// 快客帮定结果
	public static final short CG_QUICKER_BIND = ++PLAYER_BEGIN;
	public static final short GC_LOGIN_FAILED = ++PLAYER_BEGIN;
	public static final short GC_QUICKER_BIND_RESULT = ++PLAYER_BEGIN;
	public static final short CG_IOS_PUSH_TOKEN = ++PLAYER_BEGIN;
	public static final short GC_CREATE_ROLE_RESULT = ++PLAYER_BEGIN;
	public static final short CG_REQUEST_VIP_PRIORITIES = ++PLAYER_BEGIN;
	public static final short GC_VIP_PRIORITIES = ++PLAYER_BEGIN;
	public static final short CG_REPORT_DEVICE = ++PLAYER_BEGIN;
	public static final short CG_THIRD_PLATFORM_SID_CHECK = ++PLAYER_BEGIN;
	public static final short CG_PLAYER_LOGIN_V2 = ++PLAYER_BEGIN;
	public static final short CG_CHAR_CONFIG = ++PLAYER_BEGIN;
	public static final short GC_CHAR_CONFIG = ++PLAYER_BEGIN;
	public static final short CG_WEB_URL = ++PLAYER_BEGIN;
	public static final short GC_WEB_URL = ++PLAYER_BEGIN;
	public static final short GC_LOGIN_NOTIC_FINISHED = ++PLAYER_BEGIN;
	
	// /////////////
	// 玩家角色基本属性和操作模块
	// ////////////
	public static short CHARACTER_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_ENTER_HOME = ++CHARACTER_BEGIN;
	public static final short GC_PROPERTY_CHANGED_NUMBER = ++CHARACTER_BEGIN;
	public static final short GC_PROPERTY_CHANGED_STRING = ++CHARACTER_BEGIN;
	public static final short CG_SHOW_CHARGE_PANEL = ++CHARACTER_BEGIN;
	public static final short GC_SHOW_CHARGE_PANEL = ++CHARACTER_BEGIN;
	public static final short CG_VIP_INFO = ++CHARACTER_BEGIN;
	public static final short GC_VIP_INFO = ++CHARACTER_BEGIN;
	public static final short GC_VIP_CONFIG = ++CHARACTER_BEGIN;
	public static final short CG_SHOW_SLAVE_DLG = ++CHARACTER_BEGIN;
	public static final short GC_SHOW_SLAVE_DLG = ++CHARACTER_BEGIN;
	public static final short CG_SHOW_MASTER = ++CHARACTER_BEGIN;
	public static final short GC_SHOW_MASTER = ++CHARACTER_BEGIN;
	public static final short CG_SHOW_SLAVE = ++CHARACTER_BEGIN;
	public static final short GC_SHOW_SLAVE = ++CHARACTER_BEGIN;
	public static final short CG_SLAVE_TORMENT = ++CHARACTER_BEGIN;
	public static final short GC_SLAVE_TORMENT = ++CHARACTER_BEGIN;
	public static final short CG_GIVE_UP_SLAVE = ++CHARACTER_BEGIN;
	public static final short GC_GET_OPENED_FUNC_LIST = ++CHARACTER_BEGIN;
	public static final short GC_WALLOW_OPEN_PANEL = ++CHARACTER_BEGIN;
	public static final short CG_WALLOW_ADD_INFO = ++CHARACTER_BEGIN;
	public static final short GC_WALLOW_ADD_INFO_RESULT = ++CHARACTER_BEGIN;
	public static final short CG_SET_CONSUME_CONFIRM = ++CHARACTER_BEGIN;
	public static final short CG_GET_SORT_LIST = ++CHARACTER_BEGIN;
	public static final short GC_ARENALEVEL_SORT_LIST = ++CHARACTER_BEGIN;
	public static final short GC_DEVELOPMENT_SORT_LIST = ++CHARACTER_BEGIN;
	public static final short GC_PRESTIGE_SORT_LIST = ++CHARACTER_BEGIN;
	public static final short GC_SW_SORT_LIST = ++CHARACTER_BEGIN;
	public static final short GC_UPDATE_TW_LINK = ++CHARACTER_BEGIN;
	public static final short CG_CLICK_TW_LINK = ++CHARACTER_BEGIN;
	public static final short GC_INVITE_COMMENT = ++CHARACTER_BEGIN;
	public static final short GC_CHARACTER_DETAIL_INFO = ++CHARACTER_BEGIN;

	public static final short UPDATE_VIPLEVEL = ++CHARACTER_BEGIN;
	public static final short CG_OPEN_STRENGTHEN = ++CHARACTER_BEGIN;
	public static final short GC_STRENGTHEN_UPDATE = ++CHARACTER_BEGIN;
	
	
	public static final short GC_MASTER_REPUTATION_LEVEL_UP = ++CHARACTER_BEGIN;
	public static final short CG_MASTER_CHANGE_ATK_TYPE = ++CHARACTER_BEGIN;
	public static final short GC_MASTER_CHANGE_ATK_TYPE_SUCC = ++CHARACTER_BEGIN;
	public static final short CG_MASTER_TALENT = ++CHARACTER_BEGIN;
	public static final short GC_MASTER_TALENT = ++CHARACTER_BEGIN;
	public static final short CG_MASTER_LEVELUP_TALENT = ++CHARACTER_BEGIN;
	public static final short GC_MASTER_LEVELUP_TALENT_SUCC = ++CHARACTER_BEGIN;
	public static final short CG_MASTER_RESET_TALENT = ++CHARACTER_BEGIN;
	public static final short GC_MASTER_RESET_TALENT_SUCC = ++CHARACTER_BEGIN;
	public static final short GC_MASTER_SKILL = ++CHARACTER_BEGIN;
	public static final short CG_MASTER_CHANGE_SKILL = ++CHARACTER_BEGIN;
	public static final short GC_MASTER_CHANGE_SKILL_SUCC = ++CHARACTER_BEGIN;
	public static final short CG_MASTER_CHANGE_NAME = ++CHARACTER_BEGIN;
	public static final short GC_MASTER_CHANGE_NAME_SUCC = ++CHARACTER_BEGIN;
	public static final short GC_MASTER_UPDATE_SKILL = ++CHARACTER_BEGIN;
	

	// 对其他玩家的查询
	public static final short CG_QUERY_OTHER_ROLE_INFO = ++CHARACTER_BEGIN;
	public static final short GC_OTHER_ROLE_INFO = ++CHARACTER_BEGIN;

	// 基本玩家行为操作
	public static final short CG_UPGRADE_BUILDING = ++CHARACTER_BEGIN;
	public static final short GC_UPGRADE_BUILDING = ++CHARACTER_BEGIN;

	// 冷却队列操作
	public static final short CG_KILL_CD_TIME = ++CHARACTER_BEGIN;
	public static final short CG_ADD_CD = ++CHARACTER_BEGIN;

	// 新手引导消息
	public static final short CG_SEL_TOUR_GUIDE = ++CHARACTER_BEGIN;
	public static final short CG_SHOW_FIRST_LOGIN_GUIDE = ++CHARACTER_BEGIN;
	public static final short CG_OCCUPY_HOME = ++CHARACTER_BEGIN;
	public static final short GC_SHOW_GUIDE = ++CHARACTER_BEGIN;
	public static final short CG_SHOW_TOUR_GUIDE = ++CHARACTER_BEGIN;
	public static final short GC_SHOW_TOUR_GUIDE = ++CHARACTER_BEGIN;

	// 购买军令
	public static final short CG_CHECK_BUY_TOKEN_PRICE = ++CHARACTER_BEGIN;
	public static final short GC_CHECK_BUY_TOKEN_PRICE = ++CHARACTER_BEGIN;
	public static final short CG_BUY_TOKEN = ++CHARACTER_BEGIN;

	// 限时礼包
	public static final short CG_GIFT_OPEN = ++CHARACTER_BEGIN;
	public static final short GC_GIFT_NEXT = ++CHARACTER_BEGIN;
	public static final short GC_GIFT_RECEIVE_SUCCESS = ++CHARACTER_BEGIN;
	public static final short GC_OPEN_FUNC_LIST = ++CHARACTER_BEGIN;

	public static final short GC_OPEN_NEW_FUNC_LIST = ++CHARACTER_BEGIN;
	public static final short GC_LEVEL_UP = ++CHARACTER_BEGIN;

	public static final short GC_CONDITION_CHAR_FUNC_STATES = ++CHARACTER_BEGIN;
	//每日数据
	public static final short GC_DAILY_DATA_INFO = ++CHARACTER_BEGIN;
	/** CD列表 */
	public static final short GC_CD_INFO_LIST = ++CHARACTER_BEGIN;
	public static final short GC_RECOVER_LIST = ++CHARACTER_BEGIN;//货币恢复信息
	public static final short GC_RECOVER_INFO = ++CHARACTER_BEGIN;//货币恢复信息

	public static final short CG_CHAR_OFFICE_UPDATE = ++CHARACTER_BEGIN;
	public static final short GC_WALLOW_CHANGE = ++CHARACTER_BEGIN;
	//首次使用功能集合
	public static final short GC_FIRST_FUNC_FLAG = ++CHARACTER_BEGIN;
	
	// 武将
	public static short HERO_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short GC_HERO_INFO = ++HERO_BEGIN;
	public static final short GC_HERO_LIST = ++HERO_BEGIN;
	public static final short GC_HERO_ADD = ++HERO_BEGIN;
	public static final short GC_HERO_DELETE = ++HERO_BEGIN;
	public static final short GC_HERO_OPEN_FUNC = ++HERO_BEGIN;
	public static final short CG_HERO_LEVEL_UP = ++HERO_BEGIN;
	public static final short CG_HERO_LEVEL_UP_GIFT = ++HERO_BEGIN;
	public static final short GC_HERO_LEVEL_UP_GIFT = ++HERO_BEGIN;
	public static final short CG_HERO_LEVEL_UP_FATE = ++HERO_BEGIN;
	public static final short GC_HERO_LEVEL_UP_FATE = ++HERO_BEGIN;
	public static final short CG_HERO_LEVEL_UP_TRAIN = ++HERO_BEGIN;
	public static final short GC_HERO_LEVEL_UP_TRAIN = ++HERO_BEGIN;
	public static final short CG_HERO_TRAIN_UPDATE = ++HERO_BEGIN;
	public static final short GC_HERO_COMBINE_UPDATE = ++HERO_BEGIN;
	public static final short CG_HERO_TRAIN_INFO = ++HERO_BEGIN;
	public static final short CG_HISTORY_HERO_LIST = ++HERO_BEGIN;
	public static final short GC_HISTORY_HERO_LIST = ++HERO_BEGIN;
	public static final short CG_COMPOSITION_HERO = ++HERO_BEGIN;
	public static final short CG_REBORN_HERO_INFO = ++HERO_BEGIN;
	public static final short GC_REBORN_HERO_INFO = ++HERO_BEGIN;
	public static final short GC_REBORN_HERO_RESULT = ++HERO_BEGIN;
	public static final short CG_SOLDIER_STAGE_LEVEL_UP = ++HERO_BEGIN;
	public static final short GC_HERO_UPDATE = ++HERO_BEGIN;
	public static final short GC_UPDATE_HERO_SKILL= ++HERO_BEGIN;
	public static final short GC_HERO_SNAP_FINISH= ++HERO_BEGIN;
	public static final short CG_WEAR_AWAKE_EQUIP= ++HERO_BEGIN;
	public static final short CG_AWAKE_LEVEL_UP= ++HERO_BEGIN;
	public static final short CG_MERGE_AWAKE_EQUIP= ++HERO_BEGIN;
	public static final short GC_MERGE_EQUIP_SUCC= ++HERO_BEGIN;
	public static final short CG_CHOOSE_BAGUA_MIRROR_HERO = ++HERO_BEGIN;
	public static final short GC_CHOOSE_BAGUA_MIRROR_HERO = ++HERO_BEGIN;
	public static final short CG_BAGUA_MIRROR_TRANSFORM = ++HERO_BEGIN;
	public static final short GC_BAGUA_MIRROR_TRANSFORM_SUCCESS = ++HERO_BEGIN;
	public static final short GC_TRANSFORM_LIST = ++HERO_BEGIN;
	public static final short GC_TRANSFORM_INFO = ++HERO_BEGIN;
	public static final short CG_TRANSFORM_LEVEL_UP = ++HERO_BEGIN;
	public static final short CG_EQUIP_TRANSFORM = ++HERO_BEGIN;
	public static final short CG_TRANSFORM_DECOMPOSE = ++HERO_BEGIN;

	// /////////////
	// 聊天功能
	// ////////////
	public static short CHAT_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short GC_CHAT_CHANNEL_LIST = ++CHAT_BEGIN;
	public static final short CG_CHAT_CHANNEL_CHANGE = ++CHAT_BEGIN;
	public static final short CG_CHAT_MSG = ++CHAT_BEGIN;
	public static final short GC_CHAT_MSG = ++CHAT_BEGIN;
	public static final short CG_REQUEST_CHAT_PLAYER_INFO = ++CHAT_BEGIN;
	public static final short GC_CHAT_PLAYER_INFO_RESULT = ++CHAT_BEGIN;
	public static final short GC_CHAT_BLACK_LIST = ++ CHAT_BEGIN;
	public static final short CG_CHAT_ADD_BLACK_LIST = ++ CHAT_BEGIN;
	public static final short GC_CHAT_BLACK_LIST_ADD_RESULT = ++ CHAT_BEGIN;
	public static final short CG_CHAT_REMOVE_BLACK_LIST = ++ CHAT_BEGIN;
	public static final short GC_CHAT_BLACK_LIST_REMOVE_RESULT = ++ CHAT_BEGIN;
	public static final short GC_CHAT_HISTORY = ++ CHAT_BEGIN;
	public static final short CG_WATCH_HERO_LIST = ++ CHAT_BEGIN;
	public static final short GC_WATCH_HERO_LIST = ++ CHAT_BEGIN;
	public static final short CG_START_DUEL = ++ CHAT_BEGIN;
	

	// /////////////
	// 场景功能
	// ////////////
	public static short SCENE_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_SHOW_SCENE_LIST = ++SCENE_BEGIN;
	public static final short GC_SHOW_SCENE_LIST = ++SCENE_BEGIN;
	public static final short CG_CLICK_SCENE = ++SCENE_BEGIN;
	public static final short GC_SHOW_CITY_SCENE_DLG = ++SCENE_BEGIN;
	public static final short CG_CLICK_SCENE_FUNC = ++SCENE_BEGIN;
	public static final short CG_ENTRY_SCENE = ++SCENE_BEGIN;
	public static final short GC_SEASON_INFO = ++SCENE_BEGIN;
	public static final short GC_SHOW_FARM_SCENE_DLG = ++SCENE_BEGIN;

	public static final short CG_REQUEST_RAID_INFO = ++SCENE_BEGIN;
	public static final short GC_RAID_INFO = ++SCENE_BEGIN;
	public static final short CG_REQUEST_RAID_CHAPTER_INFO = ++SCENE_BEGIN;
	public static final short GC_RAID_CHAPTER_INFO = ++SCENE_BEGIN;
	public static final short CG_JOIN_RAID = ++SCENE_BEGIN;

	public static final short GC_CALL_REINFORCE = ++SCENE_BEGIN;
	public static final short CG_CALL_REINFORCE = ++SCENE_BEGIN;
	
	// //////////////
	// 物品 //
	// //////////////
	public static short ITEM_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short GC_BAG_UPDATE = ++ITEM_BEGIN;
	public static final short GC_ITEM_UPDATE = ++ITEM_BEGIN;
	public static final short CG_MOVE_ITEM = ++ITEM_BEGIN;
	public static final short GC_REMOVE_ITEM = ++ITEM_BEGIN;
	public static final short GC_TAKE_OFF_EQUIP = ++ITEM_BEGIN;
	public static final short CG_USE_ITEM = ++ITEM_BEGIN;
	public static final short CG_BATCH_USE_ITEM = ++ITEM_BEGIN;
	public static final short GC_GAIN_ITEM_POPUP = ++ITEM_BEGIN;
	
	// equip //
	public static final short CG_ENHANCE_EQUIP = ++ITEM_BEGIN;
	public static final short GC_ENHANCE_EQUIP_RESULT = ++ITEM_BEGIN;
	public static final short CG_REFINE_EQUIP = ++ITEM_BEGIN;
	public static final short CG_EQUIP_COMPOSITION = ++ITEM_BEGIN;
	public static final short CG_TREASURE_ENHANCE = ++ITEM_BEGIN;
	public static final short CG_TREASURE_REFINE = ++ITEM_BEGIN;
	public static final short GC_EQUIP_COMPOSITION_RESULT = ++ITEM_BEGIN;
	public static final short CG_REQ_EQUIP_SMELT_OUTPUT = ++ITEM_BEGIN;
	public static final short GC_RESP_EQUIP_SMELT_OUTPUT = ++ITEM_BEGIN;
	public static final short CG_CONFIRM_EQUIP_SMELT = ++ITEM_BEGIN;
	public static final short CG_REQ_TREASURE_REBIRTH_OUTPUT = ++ITEM_BEGIN;
	public static final short GC_RESP_TREASURE_REBIRTH_OUTPUT = ++ITEM_BEGIN;
	public static final short CG_CONFIRM_TREASURE_REBIRTH = ++ITEM_BEGIN;
	public static final short GC_EQUIP_SMELT_SUCCESS = ++ITEM_BEGIN;
	public static final short GC_TREASURE_REBIRTH_SUCCESS = ++ITEM_BEGIN;
	public static final short CG_PEEK_EQUIP_REBIRTH_OUTPUT = ++ITEM_BEGIN;
	public static final short GC_PEEK_EQUIP_REBIRTH_OUTPUT = ++ITEM_BEGIN;
	public static final short CG_CONFIRM_EQUIP_REBIRTH = ++ITEM_BEGIN;
	public static final short CG_DECOMPOSE_ITEM = ++ITEM_BEGIN;
	
	// 宝    //
	
	// //////////////
	// 任务 //
	// //////////////
	public static short QUEST_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short GC_MAIN_QUEST_LIST = ++QUEST_BEGIN;
	public static final short GC_DAILY_QUEST_LIST = ++QUEST_BEGIN;
	public static final short GC_QUEST_UPDATE = ++QUEST_BEGIN;
	public static final short GC_QUEST_REMOVE = ++QUEST_BEGIN;
	public static final short CG_GET_QUEST_BONUS = ++QUEST_BEGIN;
	public static final short GC_NORMAL_QUEST_LIST = ++QUEST_BEGIN;
	public static final short GC_NORMAL_QUEST_UPDATE = ++QUEST_BEGIN;
	public static final short GC_NORMAL_QUEST_REMOVE = ++QUEST_BEGIN;
	public static final short CG_GET_NORMAL_QUEST_BONUS = ++QUEST_BEGIN;
	public static final short GC_WORLD_WAR_DAILY_QUEST_LIST = ++QUEST_BEGIN;

	/** 基础功能 */
	private static short BASE_FUNC = (BASE_NUMBER += NUMBER_PER_SYS);

	// //////////////
	// 关卡和组队 //
	// //////////////
	public static short MISSION_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_ENTER_MISSION_STAGE = ++MISSION_BEGIN;
	public static final short GC_ENTER_MISSION_STAGE = ++MISSION_BEGIN;
	public static final short CG_SHOW_ENEMY_RECORD = ++MISSION_BEGIN;
	public static final short GC_SHOW_ENEMY_RECORD = ++MISSION_BEGIN;
	public static final short CG_ATTACK_ENEMY = ++MISSION_BEGIN;
	public static final short CG_SHOW_TEAM_PANEL = ++MISSION_BEGIN;
	public static final short GC_SHOW_TEAM_PANEL = ++MISSION_BEGIN;
	public static final short CG_CREATE_TEAM = ++MISSION_BEGIN;
	public static final short CG_JOIN_TEAM = ++MISSION_BEGIN;
	public static final short CG_EXIT_TEAM = ++MISSION_BEGIN;
	public static final short CG_OPERATE_TEAM_MEMBER = ++MISSION_BEGIN;
	public static final short GC_TEAM_UPDATE = ++MISSION_BEGIN;
	public static final short CG_TEAM_ATTACK_ENEMY = ++MISSION_BEGIN;
	public static final short GC_TEAM_ATTACK_ENEMY = ++MISSION_BEGIN;
	public static final short CG_GET_MISSION_BONUS = ++MISSION_BEGIN;
	public static final short GC_GET_MISSION_BONUS = ++MISSION_BEGIN;
	public static final short CG_CHECK_FORCE_ATTACK_PRICE = ++MISSION_BEGIN;
	public static final short GC_CHECK_FORCE_ATTACK_PRICE = ++MISSION_BEGIN;
	public static final short CG_FORCE_ATTACK_ENEMY = ++MISSION_BEGIN;
	public static final short CG_SHOW_MISSION_TARGET_PANEL = ++MISSION_BEGIN;
	public static final short GC_SHOW_MISSION_TARGET_PANEL = ++MISSION_BEGIN;
	public static final short CG_QUERY_MISSION_DROP = ++MISSION_BEGIN;
	public static final short CG_PICK_MISSION_DROP = ++MISSION_BEGIN;
	public static final short GC_MISSION_DROP_LIST = ++MISSION_BEGIN;
	public static final short GC_LEGION_MISSION_STATE = ++MISSION_BEGIN;

	// /////////////////
	// 阵形系统
	// /////////////////
	private static short ARRAY_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_SHOW_ARRAY_PANEL = ++ARRAY_BEGIN;
	public static final short GC_SHOW_ARRAY_PANEL = ++ARRAY_BEGIN;
	public static final short CG_MOVE_HERO_IN_ARRAY = ++ARRAY_BEGIN;
	public static final short CG_SET_DEFAULT_ARRAY = ++ARRAY_BEGIN;
	public static final short CG_SET_ARRAY_COMMANDER = ++ARRAY_BEGIN;

	/** 奖励系统开始 */
	public static short PRIZE_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_PRIZE_PLATFORM_LIST = ++PRIZE_BEGIN;
	public static final short GC_PRIZE_PLATFORM_LIST = ++PRIZE_BEGIN;
	public static final short CG_PRIZE_PLATFORM_GET = ++PRIZE_BEGIN;
	public static final short GC_PRIZE_PLATFORM_GET_SUCCESS = ++PRIZE_BEGIN;
	public static final short CG_PRIZE_USER_LIST = ++PRIZE_BEGIN;
	public static final short GC_PRIZE_USER_LIST = ++PRIZE_BEGIN;
	public static final short CG_PRIZE_USER_GET = ++PRIZE_BEGIN;
	public static final short GC_PRIZE_USER_GET_SUCCESS = ++PRIZE_BEGIN;
	public static final short CG_TIME_PRIZE_LIST = ++PRIZE_BEGIN;
	public static final short GC_TIME_PRIZE_LIST = ++PRIZE_BEGIN;
	public static final short CG_TIME_PRIZE_GET = ++PRIZE_BEGIN;
	public static final short GC_TIME_PRIZE_GET_SUCCESS = ++PRIZE_BEGIN;
	public static final short CG_CONTINUOUSLY_PRIZE = ++PRIZE_BEGIN;
	public static final short GC_CONTINUOUSLY_PRIZE = ++PRIZE_BEGIN;
	public static final short CG_CONTINUOUSLY_PRIZE_GET = ++PRIZE_BEGIN;
	public static final short GC_CONTINUOUSLY_PRIZE_GET_SUCCESS = ++PRIZE_BEGIN;

	public static final short CG_REQUEST_DAILY_BONUS = ++PRIZE_BEGIN;
	public static final short GC_NOTICE_DAILY_BONUS = ++PRIZE_BEGIN;
	public static final short CG_ASK_DAILY_BONUS = ++PRIZE_BEGIN;
	public static final short GC_ASK_DAILY_BONUS = ++PRIZE_BEGIN;

	public static final short CG_PRIZE_LIST = ++PRIZE_BEGIN;
	public static final short GC_PRIZE_LIST = ++PRIZE_BEGIN;
	public static final short CG_GET_PRIZE = ++PRIZE_BEGIN;
	public static final short GC_GET_PRIZE = ++PRIZE_BEGIN;
	public static final short CG_GET_KING_REWARD = ++PRIZE_BEGIN;
	public static final short CG_REQUEST_KING_REWARDS = ++PRIZE_BEGIN;
	public static final short GC_GET_KING_REWARD_RESULT = ++PRIZE_BEGIN;
	public static final short GC_KING_REWARDS = ++PRIZE_BEGIN;
	public static final short CG_GET_TENCENT_PRIZE = ++PRIZE_BEGIN;
	public static final short CG_GET_TENCENT_PRIZE_LIST = ++PRIZE_BEGIN;
	public static final short GC_TENCENT_PRIZE_LIST = ++PRIZE_BEGIN;
	public static final short GC_GET_TENCENT_PRIZE_RESULT = ++PRIZE_BEGIN;

	/** ToolTips 客户端点击显示tooltips */
	public static short TOOLTIPS_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_TOOLTIPS_SHOW = ++TOOLTIPS_BEGIN;
	public static final short GC_TOOLTIPS_ITEM_SHOW = ++TOOLTIPS_BEGIN;
	public static final short GC_TOOLTIPS_HUNT_SHOW = ++TOOLTIPS_BEGIN;

	// /////////////////////////////
	// 玩家游戏设置
	// /////////////////////////////
	public static short GAMECONFIG_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_GAMECONFIG_PLANE = ++GAMECONFIG_BEGIN;
	public static final short GC_GAMECONFIG_PLANE = ++GAMECONFIG_BEGIN;
	public static final short CG_GAMECONFIG_UPDATE = ++GAMECONFIG_BEGIN;

	public static short SYS_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short GC_UPDATE_SYS_ACTIVITY = ++SYS_BEGIN;

	// //////////////
	// 战斗 //
	// //////////////
	private static short BATTLE_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_CHOOSE_ACTION = ++BATTLE_BEGIN;
	public static final short CG_START_BATTLE = ++BATTLE_BEGIN;
	public static final short CG_WAIT_FOR_CHOOSE_ACTION = ++BATTLE_BEGIN;
	public static final short GC_BATTLE_FIELD_INFO = ++BATTLE_BEGIN;
	public static final short GC_BATTLE_RESULT = ++BATTLE_BEGIN;
	public static final short GC_BATTLE_ROUND_REPORT = ++BATTLE_BEGIN;
	public static final short GC_CHOOSE_ACTION = ++BATTLE_BEGIN;
	public static final short GC_WAIT_FOR_OTHER_CHOOSE = ++BATTLE_BEGIN;
	public static final short CG_AUTO_CHOOSE = ++BATTLE_BEGIN;
	public static final short GC_AUTO_CHOOSE_RESULT = ++BATTLE_BEGIN;
	public static final short GC_BATTLE_TEST_MSG = ++BATTLE_BEGIN;
	public static final short CG_QUIT_BATTLE = ++BATTLE_BEGIN;
	public static final short GC_SGZ_BATTLE_REPORT = ++BATTLE_BEGIN;
	public static final short CG_NEXT_SGZ_BATTLE = ++BATTLE_BEGIN;
	public static final short GC_SGZ_BATTLE_ROUND_SLICE = ++BATTLE_BEGIN;
	public static final short GC_BATTLE_START = ++BATTLE_BEGIN;
	public static final short CG_BATTLE_REPORT_PANEL = ++BATTLE_BEGIN;
	public static final short GC_BATTLE_REPORT_PANEL = ++BATTLE_BEGIN;
	public static final short CG_PLAY_BATTLE_REPORT = ++BATTLE_BEGIN;
	public static final short GC_BATTLE_VS_INFO = ++BATTLE_BEGIN;
	public static final short CG_CLIENT_BATTLE_RESULT = ++BATTLE_BEGIN;
	public static final short CG_BATTLE_CANCEL = ++BATTLE_BEGIN;
	
	/***
	 * 区域开始
	 */
	public static short AREA_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_ENTER_AREA = ++AREA_BEGIN;
	public static final short CG_SPEEDUP_UPGRADE_BUILDING = ++AREA_BEGIN;
	public static final short CG_QUICK_FINISH_BUILDING_UPGRADE = ++AREA_BEGIN;
	public static final short CG_START_AUTO_UPGRADE = ++AREA_BEGIN;
	public static final short CG_STOP_AUTO_UPGRADE = ++AREA_BEGIN;
	public static final short GC_AREA_BUILDING_LIST = ++AREA_BEGIN;
	public static final short GC_AREA_LIST = ++AREA_BEGIN;
	public static final short GC_BUILDING_INFO_UPDATE = ++AREA_BEGIN;
	public static final short GC_BUILDING_UPGRADE_QUEUE = ++AREA_BEGIN;
	public static final short CG_GET_AREA_LIST = ++AREA_BEGIN;
	public static final short CG_REQUEST_UPGRADE_QUEUE = ++AREA_BEGIN;
	public static final short CG_REQUEST_INCOME_MODE_LIST = ++AREA_BEGIN;
	public static final short CG_CHANGE_INCOME_MODE = ++AREA_BEGIN;
	public static final short GC_AREA_INCOME_MODE_UPDATE = ++AREA_BEGIN;
	public static final short GC_INCOME_MODE_LIST = ++AREA_BEGIN;
	public static final short GC_BUILDING_SPEEDUP = ++AREA_BEGIN;
	public static final short GC_BUILDING_ADD_CHARACTER_EXP = ++AREA_BEGIN;

	/**
	 * 训练相关
	 */
	public static short TRAIN_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_END_TRAINING = ++TRAIN_BEGIN;
	public static final short CG_START_TRAINING = ++TRAIN_BEGIN;
	public static final short GC_UPDATE_TRAIN = ++TRAIN_BEGIN;
	public static final short GC_END_ACCOUNT = ++TRAIN_BEGIN;
	public static final short GC_TRAIN = ++TRAIN_BEGIN;
	public static final short CG_TRAIN = ++TRAIN_BEGIN;
	public static final short GC_TRAIN_ADDEXP = ++TRAIN_BEGIN;

	/**
	 * 商店
	 */
	public static short SHOP_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_OPEN_SHOP = ++SHOP_BEGIN;
	public static final short CG_REFRESH_SHOP = ++SHOP_BEGIN;
	public static final short CG_BUY_SHOP_ITEM = ++SHOP_BEGIN;
	public static final short GC_SHOP_INFO = ++SHOP_BEGIN;
	public static final short GC_SHOP_POSITION_UPDATE = ++SHOP_BEGIN;
	
	public static final short CG_BATTLE_SHOP_INFO = ++SHOP_BEGIN;
	public static final short GC_BATTLE_SHOP_INFO = ++SHOP_BEGIN;
	public static final short CG_BATTLE_SHOP_BUY_ITEM = ++SHOP_BEGIN;
	public static final short GC_BATTLE_SHOP_UPDATE = ++SHOP_BEGIN;
	
	public static final short GC_SYS_BONUS_ADD = ++SHOP_BEGIN;
	public static final short CG_SYS_BONUS_INFO = ++SHOP_BEGIN;
	public static final short CG_SYS_BONUS_GAIN = ++SHOP_BEGIN;
	public static final short GC_SYS_BONUS_INFO = ++SHOP_BEGIN;
	public static final short GC_SYS_BONUS_REMOVE = ++SHOP_BEGIN;

	/** 商城 */
	public static short MALL_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short GC_MALL_SHOP_ITEM_UPDATE = ++MALL_BEGIN;
	public static final short CG_MALL_SHOP_BUY_ITEM = ++MALL_BEGIN;
	public static final short GC_HERO_NEW_SHOP_DISCOUNT_INFO = ++MALL_BEGIN;
	public static final short GC_HERO_SHOP_ITEM_UPDATE = ++MALL_BEGIN;
	public static final short CG_HERO_SHOP_BUY_ITEM = ++MALL_BEGIN;
	
	/**
	 * 皇城
	 */
	public static short PALACE_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_ATTACK_PALACE = ++PALACE_BEGIN;
	public static final short CG_ENTER_PALACES = ++PALACE_BEGIN;

	public static final short CG_SHOW_PALACE = ++PALACE_BEGIN;

	public static final short GC_ENTER_PALACES = ++PALACE_BEGIN;
	public static final short GC_SHOW_PALACE = ++PALACE_BEGIN;

	public static final short CG_APPLY_PALACE = ++PALACE_BEGIN;

	public static final short CG_GIVEUP_PALACE = ++PALACE_BEGIN;
	public static final short GC_UPDATE_PALACES = ++PALACE_BEGIN;

	public static final short CG_APPLY_LIST = ++PALACE_BEGIN;
	public static final short GC_APPLY_LIST = ++PALACE_BEGIN;

	public static final short CG_AGREE_APPLY = ++PALACE_BEGIN;

	public static final short CG_REFUSE_APPLY = ++PALACE_BEGIN;
	public static final short CG_DELETE_OFFICIAL = ++PALACE_BEGIN;

	public static final short CG_OFFICIAL_LIST = ++PALACE_BEGIN;

	public static final short GC_SALARY = ++PALACE_BEGIN;
	public static final short CG_GET_SALARY = ++PALACE_BEGIN;
	public static final short CG_GIVEUP_OFFICIAL = ++PALACE_BEGIN;
	public static final short GC_OFFICIAL_LIST = ++PALACE_BEGIN;
	public static final short GC_UPDATE_PALACE_STATE = ++PALACE_BEGIN;
	public static final short CG_PUBLISH_OFFICIAL_TOKEN = ++PALACE_BEGIN;
	public static final short CG_RECEIVE_OFFICIAL_TOKEN = ++PALACE_BEGIN;
	public static final short GC_NEW_OFFICIAL_TOKEN = ++PALACE_BEGIN;
	public static final short GC_OFFICIAL_TOKEN_LIST = ++PALACE_BEGIN;
	public static final short GC_OFFICIAL_TOKEN_EXPIRED = ++PALACE_BEGIN;
	public static final short CG_REQUEST_OFFICIAL_TOKEN = ++PALACE_BEGIN;
	public static final short GC_OFFICIAL_TOKEN = ++PALACE_BEGIN;

	/**
	 * 世界地图
	 */
	public static short WORLD_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_ENTER_WORLD = ++WORLD_BEGIN;
	public static final short CG_LEAVE_WORLD = ++WORLD_BEGIN;
	public static final short CG_OPEN_CHOOSE_COUNTRY_PANEL = ++WORLD_BEGIN;
	public static final short GC_CHOOSE_COUNTRY = ++WORLD_BEGIN;
	public static final short CG_CHOOSE_COUNTRY = ++WORLD_BEGIN;
	public static final short GC_WORLD_ACT_COUNTDOWN = ++WORLD_BEGIN;
	public static final short GC_CHARACTER_WORLD_INFO = ++WORLD_BEGIN;
	public static final short GC_WORLD_INFO_LIST = ++WORLD_BEGIN;
	public static final short GC_WORLD_COUNTRY_INFO = ++WORLD_BEGIN;
	public static final short GC_WORLD_CITY_INFO = ++WORLD_BEGIN;
	public static final short CG_START_MOVE = ++WORLD_BEGIN;
	public static final short CG_REQUEST_MOVE = ++WORLD_BEGIN;
	public static final short GC_START_MOVE = ++WORLD_BEGIN;
	public static final short CG_STOP_MOVE = ++WORLD_BEGIN;
	public static final short GC_STOP_MOVE = ++WORLD_BEGIN;
	public static final short CG_WORLD_CITY_DETAIL = ++WORLD_BEGIN;
	public static final short GC_WORLD_CITY_DETAIL = ++WORLD_BEGIN;
	public static final short CG_UPDATE_CITY_DEFLEVEL = ++WORLD_BEGIN;
	public static final short CG_ADD_CITY_DEFPOINT = ++WORLD_BEGIN;
	public static final short CG_PLACE_PHANTOM = ++WORLD_BEGIN;
	public static final short CG_VIEW_DEFENCERS = ++WORLD_BEGIN;
	public static final short GC_VIEW_DEFENCERS = ++WORLD_BEGIN;
	public static final short CG_ATTACK_CITY = ++WORLD_BEGIN;
	public static final short CG_WORLD_REVIVE = ++WORLD_BEGIN;
	public static final short CG_WORLD_RANK = ++WORLD_BEGIN;
	public static final short GC_WORLD_RANK = ++WORLD_BEGIN;
	public static final short CG_VIEW_LASTDAY_WORLD = ++WORLD_BEGIN;
	public static final short CG_DETECT_CITY = ++WORLD_BEGIN;
	public static final short GC_CITY_DETECT_LIST = ++WORLD_BEGIN;
	public static final short GC_CITY_DETECT_INFO = ++WORLD_BEGIN;
	public static final short CG_SYNC_CHARACTER_WORLD_INFO = ++WORLD_BEGIN;
	public static final short CG_WORLD_KING_BONUS = ++WORLD_BEGIN;
	public static final short GC_WORLD_KING_BONUS = ++WORLD_BEGIN;
	public static final short CG_BUY_ACTION_POINTS = ++WORLD_BEGIN;
	public static final short GC_CHOOSE_COUNTRY_SUCC = ++WORLD_BEGIN;
	public static final short GC_GAIN_CONTRIBUTE = ++WORLD_BEGIN;
	public static final short GC_WORLD_POP_NOTICE_INFO = ++WORLD_BEGIN;
	public static final short CG_WORLD_POP_NOTICE_PANEL = ++WORLD_BEGIN;
	public static final short GC_WORLD_POP_NOTICE_PANEL = ++WORLD_BEGIN;

	/**
	 * 资源相关(祭祀、集市、黑市)
	 */
	public static short MATERIAL_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_BUY_BAZAAR_ITEM = ++MATERIAL_BEGIN;
	public static final short CG_CLEAR_BLACK_MARKET_CD_TIME = ++MATERIAL_BEGIN;
	public static final short CG_REQUEST_BAZAAR_LIST = ++MATERIAL_BEGIN;
	public static final short CG_REQUEST_BLACK_MARKET_LIST = ++MATERIAL_BEGIN;
	public static final short CG_REQUEST_SACRIFICE_LIST = ++MATERIAL_BEGIN;
	public static final short CG_START_SACRIFICE = ++MATERIAL_BEGIN;
	public static final short GC_BLACK_MARKET_CD_TIME_UPDATE = ++MATERIAL_BEGIN;
	public static final short GC_BLACK_MARKET_LIST = ++MATERIAL_BEGIN;
	public static final short GC_BUY_BAZAAR_ITEM_RESULT = ++MATERIAL_BEGIN;
	public static final short GC_SACRIFICE_LIST = ++MATERIAL_BEGIN;
	public static final short GC_SACRIFICE_RESULT = ++MATERIAL_BEGIN;
	public static final short GC_BAZAAR_LIST = ++MATERIAL_BEGIN;
	public static final short CG_BLACK_MARKET_EXCHANGE = ++MATERIAL_BEGIN;
	public static final short GC_BLACK_MARKET_EXCHANGE = ++MATERIAL_BEGIN;
	public static final short CG_COUPON = ++MATERIAL_BEGIN;
	public static final short GC_COUPON_PANEL = ++MATERIAL_BEGIN;
	public static final short CG_COUPON_EXCHANGE = ++MATERIAL_BEGIN;
	public static final short CG_COUPON_EXCHANGE_BAT = ++MATERIAL_BEGIN;
	public static final short CG_START_TEN_SACRIFICE = ++MATERIAL_BEGIN;
	public static final short GC_COUPON_EXCHANGE = ++MATERIAL_BEGIN;

	/**
	 * 兵器相关
	 */
	public static short WEAPON_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_BUY_WEAPON_BLUEPRINT = ++WEAPON_BEGIN;
	public static final short CG_REQUEST_WEAPON_LIST = ++WEAPON_BEGIN;
	public static final short CG_START_MAKE_WEAPON = ++WEAPON_BEGIN;
	public static final short CG_START_MOLD = ++WEAPON_BEGIN;
	public static final short GC_MOLD_STEP_CHANGE = ++WEAPON_BEGIN;
	public static final short GC_WEAPON_LIST = ++WEAPON_BEGIN;
	public static final short GC_WEAPON_INFO_UPDATE = ++WEAPON_BEGIN;

	/**
	 * 科技开始
	 */
	public static short TECHNOLOGY_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_REQUEST_TECHNOLOGY_LIST = ++TECHNOLOGY_BEGIN;
	public static final short GC_TECHNOLOGY_ROOT_LIST = ++TECHNOLOGY_BEGIN;
	public static final short GC_TECHNOLOGY_LIST = ++TECHNOLOGY_BEGIN;
	public static final short CG_SELECT_TECHNOLOGY_ROOT_TYPE = ++TECHNOLOGY_BEGIN;
	public static final short GC_TECHNOLOGY_UPGRADE_QUEUE = ++TECHNOLOGY_BEGIN;
	public static final short CG_TECHNOLOGY_REQUEST_UPGRADE = ++TECHNOLOGY_BEGIN;
	public static final short GC_TECHNOLOGY_INFO_UPDATE = ++TECHNOLOGY_BEGIN;
	public static final short CG_TECHNOLOGY_UPGRADE_COMPLETED_IMMEDIATELY = ++TECHNOLOGY_BEGIN;

	/**
	 * 排行开始
	 */
	public static short RANK_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_RECEIVE_KILL_CHEST_BONUS = ++RANK_BEGIN;
	public static final short CG_RECEIVE_KILL_RANK_BONUS = ++RANK_BEGIN;
	public static final short CG_REQUEST_KILL_RANK = ++RANK_BEGIN;
	public static final short CG_REQUEST_OFFICIAL_RANK = ++RANK_BEGIN;
	public static final short GC_RECEIVE_KILL_CHEST_BONUS_SUCCESS = ++RANK_BEGIN;
	public static final short GC_RECEIVE_KILL_RANK_BONUS_SUCCESS = ++RANK_BEGIN;
	public static final short GC_OFFICIAL_RANK = ++RANK_BEGIN;
	public static final short GC_KILL_RANK = ++RANK_BEGIN;
	public static final short GC_SELF_OFFICIAL_RANK_INFO = ++RANK_BEGIN;
	public static final short CG_REQUEST_COMMON_RANK = ++RANK_BEGIN;
	public static final short GC_COMMON_RANK = ++RANK_BEGIN;
	public static final short CG_RECEIVE_COMMON_RANK_BONUS = ++RANK_BEGIN;
	public static final short GC_RECEIVE_COMMON_RANK_BONUS_SUCCESS = ++RANK_BEGIN;
	/**
	 * BUFF相关
	 */
	public static short BUFF_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_USE_BUFF = BUFF_BEGIN++;
	public static final short GC_BUFF_ADD = BUFF_BEGIN++;
	public static final short GC_BUFF_REMOVE = BUFF_BEGIN++;
	public static final short GC_CIVIL_BUFF_LIST = BUFF_BEGIN++;
	public static final short CG_REQUEST_CIVIL_LIST = BUFF_BEGIN++;
	/**
	 * 邮件开始
	 */
	public static short MAIL_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_OPEN_MAIL_PANEL = ++MAIL_BEGIN;
	public static final short GC_MAIL_LIST = ++MAIL_BEGIN;
	public static final short GC_MAIL_UPDATE = ++MAIL_BEGIN;
	public static final short CG_SEND_MAIL = ++MAIL_BEGIN;
	public static final short GC_HAS_NEW_MAIL = ++MAIL_BEGIN;

	/**
	 * 充值开始
	 */
	public static short CHARGE_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_IOS_CHARGE_CHECK = ++CHARGE_BEGIN;
	public static final short CG_IOS_CHARGE_KINDS = ++CHARGE_BEGIN;
	public static final short GC_IOS_CHARGE_CHECK = ++CHARGE_BEGIN;
	public static final short GC_IOS_CHARGE_KINDS = ++CHARGE_BEGIN;
	public static final short CG_CHARGE_EXCHANGE = ++CHARGE_BEGIN;
	public static final short CG_GET_ACCOUNT_BALANCE = ++CHARGE_BEGIN;
	public static final short CG_REQUEST_CHARGE_LIST = ++CHARGE_BEGIN;
	public static final short CG_REQUEST_PAY_URL = ++CHARGE_BEGIN;
	public static final short GC_ACCOUNT_BALANCE = ++CHARGE_BEGIN;
	public static final short GC_CHARGE_LIST = ++CHARGE_BEGIN;
	public static final short GC_PAY_URL_RESPONSE = ++CHARGE_BEGIN;
	public static final short CG_REQUEST_ORDER_ID = ++CHARGE_BEGIN;
	public static final short GC_ORDER_ID = ++CHARGE_BEGIN;
	public static final short CG_REQUEST_TW_MYCARD_CHARGE_INFO = ++CHARGE_BEGIN;
	public static final short GC_TW_MYCARD_CHARGE_INFO = ++CHARGE_BEGIN;
	public static final short CG_REFRESH_MYCARD_BALLANCE_INFO = ++CHARGE_BEGIN;
	public static final short GC_MONTH_CARD_STATUS_LIST = ++CHARGE_BEGIN;
	public static final short GC_MONTH_CARD_STATUS = ++CHARGE_BEGIN;
	public static final short CG_BUY_VIP_GIFT = ++CHARGE_BEGIN;
	public static final short CG_GET_VIP_DAILY_BONUS = ++CHARGE_BEGIN;
	public static final short CG_BUY_VIP_MALL_ITEM = ++CHARGE_BEGIN;
	public static final short CG_GET_MONTH_CARD = ++CHARGE_BEGIN;
	public static final short GC_FIRST_CHARGE_IDS = ++CHARGE_BEGIN;
	public static final short CG_REQUEST_LUXURY_DINNER_INFO = ++CHARGE_BEGIN;
	public static final short GC_LUXURY_DINNER_INFO = ++CHARGE_BEGIN;
	public static final short CG_CLAIM_LUXURY_DINNER_BONUS = ++CHARGE_BEGIN;

	/**
	 * 对话开始
	 */
	public static short DIALOGUE_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_REQUEST_DIALOGUE = ++DIALOGUE_BEGIN;
	public static final short GC_DIALOGUE_CONTENT = ++DIALOGUE_BEGIN;
	public static final short CG_REQUEST_BATTLEFAIL_DIALOGUE = ++DIALOGUE_BEGIN;

	/**
	 * 新手开始
	 */
	public static short NEWBIE_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_CLIENT_GUIDE_INFO = ++NEWBIE_BEGIN;
	public static final short GC_CLIENT_GUIDE_INFO = ++NEWBIE_BEGIN;
	

	public static short POLITICS_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_ADOPT_EVENT = ++POLITICS_BEGIN;
	public static final short CG_REQUEST_EVENT_INFO = ++POLITICS_BEGIN;
	public static final short GC_UPDATE_EVENT_INFO = ++POLITICS_BEGIN;
	public static final short CG_GIVE_LOYAL_REWARD = ++POLITICS_BEGIN;
	public static final short GC_UPDATE_LOYAL = ++POLITICS_BEGIN;
	public static final short GC_ADOPT_EVENT = ++POLITICS_BEGIN;

	private static short BONUS_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_CLOCK_BONUS = ++BONUS_BEGIN;
	public static final short GC_CLOCK_BONUS_COUNT = ++BONUS_BEGIN;
	public static final short GC_CLOCK_BONUS_TIME = ++BONUS_BEGIN;
	public static final short CG_DRAW_CLOCK_BONUS = ++BONUS_BEGIN;
	public static final short GC_CLOCK_BONUS_CONTENT = ++BONUS_BEGIN;

	private static short ACTIVITY_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_SEVEN_DAYS_ACTIVITY = ++ACTIVITY_BEGIN;
	public static final short GC_ACTIVITY_LIST = ++ACTIVITY_BEGIN;
	public static final short CG_GAIN_ACTIVITY_BONUS = ++ACTIVITY_BEGIN;
	public static final short GC_UPDATE_ACTIVITY = ++ACTIVITY_BEGIN;
	public static final short CG_GAIN_REGIEST_BONUS = ++ACTIVITY_BEGIN;
	public static final short GC_DAILY_REGIEST_INFO = ++ACTIVITY_BEGIN;
	public static final short CG_MAMMON_INFO = ++ACTIVITY_BEGIN;
	public static final short CG_GAIN_MAMMON = ++ACTIVITY_BEGIN;
	public static final short GC_MAMMON_INFO = ++ACTIVITY_BEGIN;
	public static final short CG_GAIN_REGIEST_INFO = ++ACTIVITY_BEGIN;
	public static final short GC_ACTIVITY_STATE = ++ACTIVITY_BEGIN;
	public static final short CG_ACTIVITY_INFO = ++ACTIVITY_BEGIN;
	public static final short GC_GAIN_BONUS_SUCCESS = ++ACTIVITY_BEGIN;

	// UNUSED
	// 消除投资CD后刷新投资面板替代发送该消息
	public static final short GC_UPDATE_INVEST_CD = ++ACTIVITY_BEGIN;
	public static final short CG_SHOW_EXPEDITION_PANEL = ++ACTIVITY_BEGIN;
	public static final short CG_REQUEST_SELF_COUNTRY_ACTIVITY_RANK = ++ACTIVITY_BEGIN;
	public static final short GC_SELF_COUNTRY_ACTIVITY_RANK = ++ACTIVITY_BEGIN;
	public static final short GC_GET_RANK_REWARD_SUCCESS = ++ACTIVITY_BEGIN;
	public static final short GC_INVEST_EXP_BONUS = ++ACTIVITY_BEGIN;
	public static final short GC_ACTIVITY_STOP = ++ACTIVITY_BEGIN;

	/**
	 * 运营活动
	 */
	private static short ENGAGE_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	// 领取过关斩将奖励
	public static final short CG_REWARD_CLEARANCE = ++ENGAGE_BEGIN;
	// 显示过关斩将面板
	public static final short GC_CLEARANCE_PANEL = ++ENGAGE_BEGIN;
	// 请求过关斩将面板
	public static final short CG_CLEARANCE_PANEL = ++ENGAGE_BEGIN;
	// 增加金币通知
	public static final short GC_ADD_MONEY_NOTICE = ++ENGAGE_BEGIN;
	// 请求显示首充面板
	public static final short CG_FIRST_CHARGE = ++ENGAGE_BEGIN;
	// 首充面板
	public static final short GC_FIRST_CHARGE_PANEL = ++ENGAGE_BEGIN;
	// 请求领取首充奖励
	public static final short CG_FIRST_CHARGE_PRIZE = ++ENGAGE_BEGIN;

	/**
	 * 策略活动
	 */
	private static short STRATEGY_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
	public static final short CG_STRATEGY_LIST = ++STRATEGY_BEGIN;
	public static final short GC_STRATEGY_LIST = ++STRATEGY_BEGIN;

	/**
	 * 竞技场
	 */
	private static short ARENA_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_ARENA_INFO = ++ARENA_BEGIN;
	public static final short GC_ARENA_INFO = ++ARENA_BEGIN;
	public static final short CG_CHALLENGE_BATTLE = ++ARENA_BEGIN;
	public static final short CG_TOP_RANK_LIST = ++ARENA_BEGIN;
	public static final short GC_TOP_RANK_LIST = ++ARENA_BEGIN;
	public static final short GC_RANK_CHANGE = ++ARENA_BEGIN;
	public static final short CG_SWEEP_ARENA = ++ARENA_BEGIN;
	public static final short GC_SWEEP_ARENA_RESULT = ++ARENA_BEGIN;
	

	private static short BLOC_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_CREATE_BLOC = ++BLOC_BEGIN;
	public static final short CG_REQUEST_BLOC = ++BLOC_BEGIN;
	public static final short CG_REQUEST_PANEL = ++BLOC_BEGIN;
	public static final short CG_REQUEST_ADDHP = ++BLOC_BEGIN;
	public static final short CG_REQUEST_INSPIRE = ++BLOC_BEGIN;
	public static final short CG_JOIN_BLOC = ++BLOC_BEGIN;
	public static final short CG_QUIT_BLOC = ++BLOC_BEGIN;
	public static final short CG_BLOC_MEMBERS = ++BLOC_BEGIN;
	public static final short CG_DETAIL_BLOC = ++BLOC_BEGIN;
	public static final short CG_KICK_HERO = ++BLOC_BEGIN;
	public static final short CG_EXPEDITION_BATTLE = ++BLOC_BEGIN;
	public static final short CG_DISMISS_BLOC = ++BLOC_BEGIN;
	public static final short GC_RESPONSE_PANEL = ++BLOC_BEGIN;
	public static final short GC_ALL_BLOC = ++BLOC_BEGIN;
	public static final short GC_UPDATE_BLOC = ++BLOC_BEGIN;
	public static final short GC_BLOC_MEMBERS = ++BLOC_BEGIN;
	public static final short GC_DETAIL_BLOC = ++BLOC_BEGIN;
	public static final short GC_DISMISS_BLOC = ++BLOC_BEGIN;
	public static final short GC_SHOW_BLOC_BUTTON = ++BLOC_BEGIN;
	public static final short GC_EXPEDITION_BATTLE = ++BLOC_BEGIN;
	

	private static short DRILL_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);

	public static final short CG_ADD_EXP = ++DRILL_BEGIN;
	public static final short CG_DRILL_PANEL = ++DRILL_BEGIN;
	public static final short CG_ENHANCE_DRILL = ++DRILL_BEGIN;
	public static final short CG_MOVE_DRILL = ++DRILL_BEGIN;
	public static final short CG_ACTIVATE_DRILL = ++DRILL_BEGIN;

	public static final short GC_ALL_DRILL = ++DRILL_BEGIN;
	public static final short GC_DRILL_ADDEXP = ++DRILL_BEGIN;
	public static final short GC_UPDATE_DRILL = ++DRILL_BEGIN;
	public static final short GC_DRILL_UPDATE_PANEL = ++DRILL_BEGIN;

	private static short INTENTION_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_INTENTION_PANEL = ++INTENTION_BEGIN;
	public static final short GC_INTENTION_PANEL = ++INTENTION_BEGIN;
	public static final short CG_INTENTION_GROUP = ++INTENTION_BEGIN;
	public static final short GC_INTENTION_GROUP_OPEN_SERVER = ++INTENTION_BEGIN;
	public static final short CG_INTENTION_OPEN_SERVER_DETAIL = ++INTENTION_BEGIN;
	public static final short GC_INTENTION_DETAIL = ++INTENTION_BEGIN;
	public static final short CG_INTENTION_REWARD = ++INTENTION_BEGIN;

	private static short GIFT_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_EXCHANGE_GIFT = ++GIFT_BEGIN;

	private static short TOWER_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_TOWER = ++TOWER_BEGIN;
	public static final short GC_TOWER = ++TOWER_BEGIN;
	public static final short CG_ALTER_TOWER_FLOOR = ++TOWER_BEGIN;
	public static final short CG_ALTER_TOWER_BARRIER = ++TOWER_BEGIN;
	public static final short GC_TOWER_BARRIER = ++TOWER_BEGIN;
	public static final short CG_CHALLENGE_TOWER_BARRIER = ++TOWER_BEGIN;
	public static final short CG_SWEEP_TOWER_BARRIER = ++TOWER_BEGIN;
	public static final short GC_SWEEP_TOWER_BARRIER = ++TOWER_BEGIN;
	public static final short CG_TOWER_PERMIT = ++TOWER_BEGIN;
	public static final short GC_TOWER_PERMIT = ++TOWER_BEGIN;

	/**
	 * 副将
	 */
	private static short CARD_HERO_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_LEAD_CARD_HERO = ++CARD_HERO_BEGIN;
	public static final short CG_UNLEAD_CARD_HERO = ++CARD_HERO_BEGIN;
	public static final short CG_CARD_HERO_SKILLS = ++CARD_HERO_BEGIN;
	public static final short CG_CHOOSE_CARD_HERO_SKILLS = ++CARD_HERO_BEGIN;
	public static final short CG_SELL_CARD_HERO = ++CARD_HERO_BEGIN;
	public static final short GC_CARD_HERO_LIST = ++CARD_HERO_BEGIN;
	public static final short GC_CARD_HERO = ++CARD_HERO_BEGIN;
	public static final short GC_DELETE_CARD_HERO = ++CARD_HERO_BEGIN;
	public static final short GC_CARD_HERO_SKILLS = ++CARD_HERO_BEGIN;
	public static final short GC_CHOOSE_CARD_HERO_SKILLS = ++CARD_HERO_BEGIN;
	public static final short CG_CARD_HERO_PANEL = ++CARD_HERO_BEGIN;
	public static final short CG_CARD_HERO_FREE = ++CARD_HERO_BEGIN;
	public static final short GC_CARD_HERO_PANEL = ++CARD_HERO_BEGIN;
	public static final short GC_CARD_HERO_PERSON_LIST = ++CARD_HERO_BEGIN;
	public static final short GC_CARD_HERO_PERSON_SINGLE = ++CARD_HERO_BEGIN;
	public static final short GC_CARD_HERO_WORLD_SINGLE = ++CARD_HERO_BEGIN;
	public static final short GC_CARD_HERO_GET = ++CARD_HERO_BEGIN;
	public static final short CG_CARDS_TOTAL_PROPS = ++CARD_HERO_BEGIN;
	public static final short GC_CARDS_TOTAL_PROPS = ++CARD_HERO_BEGIN;
	public static final short GC_CARD_HERO_PANEL_UPDATE = ++CARD_HERO_BEGIN;

	/** 主城相关 */
	private static short MAIN_CITY_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short GC_MAIN_CITY_INFO = ++MAIN_CITY_BEGIN;
	public static final short GC_SINGLE_BUILD_INFO = ++MAIN_CITY_BEGIN;
	public static final short CG_ENTER_BUILDING = ++MAIN_CITY_BEGIN;
	public static final short GC_ENGER_STRONGHOLD_INFO = ++MAIN_CITY_BEGIN;
	public static final short GC_ENGER_SILVERMINE_INFO = ++MAIN_CITY_BEGIN;
	public static final short GC_ENGER_SILVERLIBRARY_INFO = ++MAIN_CITY_BEGIN;
	public static final short GC_ENGER_FARMLAND_INFO = ++MAIN_CITY_BEGIN;
	public static final short GC_ENGER_FARMGRANARY_INFO = ++MAIN_CITY_BEGIN;
	public static final short GC_ENGER_MILITARYCAMP_INFO = ++MAIN_CITY_BEGIN;
	public static final short CG_GATHER_OUTPUT_RESOURCE = ++MAIN_CITY_BEGIN;
	public static final short GC_GATHER_OUTPUT_RESOURCE_RESULT = ++MAIN_CITY_BEGIN;
	public static final short CG_ENTER_UPDATE_PANEL = ++MAIN_CITY_BEGIN;
	public static final short GC_STRONGHOLD_UPDATE_PANEL = ++MAIN_CITY_BEGIN;
	public static final short GC_SILVERMINE_UPDATE_PANEL = ++MAIN_CITY_BEGIN;
	public static final short GC_SILVERLIBRARY_UPDATE_PANEL = ++MAIN_CITY_BEGIN;
	public static final short GC_FARMLAND_UPDATE_PANEL = ++MAIN_CITY_BEGIN;
	public static final short GC_FARMGRANARY_UPDATE_PANEL = ++MAIN_CITY_BEGIN;
	public static final short GC_MILITARYCAMP_UPDATE_PANEL = ++MAIN_CITY_BEGIN;
	public static final short GC_DRILLFIELD_UPDATE_PANEL = ++MAIN_CITY_BEGIN;
	public static final short CG_BEGIN_UPDATA_BUILDING = ++MAIN_CITY_BEGIN;
	public static final short GC_UPDATE_BUILDING_INFO = ++MAIN_CITY_BEGIN;
	public static final short GC_BUILDING_UPDATA_QUEUE = ++MAIN_CITY_BEGIN;
	public static final short GC_INCREASED_BUILDING_INFO = ++MAIN_CITY_BEGIN;
	public static final short CG_GRANARY_RPURCHASE_FOOD = ++MAIN_CITY_BEGIN;
	public static final short GC_GRANARY_RPURCHASE_FOOD = ++MAIN_CITY_BEGIN;
	public static final short CG_RECRUITING_SOLDIERS = ++MAIN_CITY_BEGIN;
	public static final short CG_REQUEST_BUILDING_OUTPUT = ++MAIN_CITY_BEGIN;
	public static final short GC_RESPONSE_BUILDING_OUTPUT = ++MAIN_CITY_BEGIN;
	public static final short GC_IMPERIALCOLLEGE_UPDATE_PANEL = ++MAIN_CITY_BEGIN;
	public static final short CG_SET_AUTO_REPLENISH_SOLDIER= ++MAIN_CITY_BEGIN;
	public static final short CG_DIVINE_ASTROLOGY = ++MAIN_CITY_BEGIN;
	public static final short GC_DIVINE_ASTROLOGY_RESULT = ++MAIN_CITY_BEGIN;
	public static final short CG_CHOOSE_STAR_VALUSE_HERO = ++MAIN_CITY_BEGIN;
	
	/** 副本吧相关 */
	private static short RAID_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_ATTACK_RAID= ++RAID_BEGIN;
	public static final short CG_GET_STAR_BONUS= ++RAID_BEGIN;
	public static final short CG_REQUEST_STAR_RANK= ++RAID_BEGIN;
	public static final short CG_RESET_RAID= ++RAID_BEGIN;
	public static final short CG_SWIPE_RAID= ++RAID_BEGIN;
	public static final short GC_CHAPTER_INFO= ++RAID_BEGIN;
	public static final short GC_RAID_DATA= ++RAID_BEGIN;
	public static final short GC_STAR_RANK_LIST= ++RAID_BEGIN;
	public static final short CG_GET_RAID_BONUS= ++RAID_BEGIN;
	public static final short GC_SWIPE_RESULT= ++RAID_BEGIN;
	public static final short GC_CHAPTER_STAR_CHANGE= ++RAID_BEGIN;
	public static final short CG_ATTACK_DAILY_RAID = ++RAID_BEGIN;
	public static final short CG_BUY_DAILY_RAID_TIMES = ++RAID_BEGIN;
	public static final short GC_DAILY_RAID_PROP = ++RAID_BEGIN;
	public static final short CG_RAID_PASS_RECORDS = ++RAID_BEGIN;
	public static final short GC_RAID_PASS_RECORDS = ++RAID_BEGIN;
	public static final short CG_VIEW_RAID_PASS_RECORD = ++RAID_BEGIN;
	public static final short GC_DAILY_NEW_RAID_STATE = ++RAID_BEGIN;
	public static final short CG_DAILY_NEW_RAID_CHALLENGE = ++RAID_BEGIN;
	public static final short CG_FINISH_CHAPTER_BONUS = ++RAID_BEGIN;
	public static final short GC_FOREIGN_ENEMY_INVADE = ++RAID_BEGIN;
	public static final short CG_ATTACK_FOREIGN_ENEMY = ++RAID_BEGIN;
	
	
	/** 布阵 */
	private static short BATTLE_FORMATION_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_BATTLE_FORMATION = ++BATTLE_FORMATION_BEGIN;
	public static final short GC_BATTLE_COLUMN_LIST = ++BATTLE_FORMATION_BEGIN;
	public static final short CG_CHANGE_BATTLE_FORMATION = ++BATTLE_FORMATION_BEGIN;
	public static final short GC_BATTLE_FORMATION_LIST = ++BATTLE_FORMATION_BEGIN;
	public static final short CG_CHANGE_COLUMN_HERO = ++BATTLE_FORMATION_BEGIN;
	public static final short GC_BATTLE_COLUMN_UPDATE = ++BATTLE_FORMATION_BEGIN;
	public static final short GC_BATTLE_FORMATION_PANEL = ++BATTLE_FORMATION_BEGIN;
	public static final short CG_BATTLE_FORMATION_REBORN = ++BATTLE_FORMATION_BEGIN;
	public static final short CG_BATTLE_FORMATION_LEVEL_UP = ++BATTLE_FORMATION_BEGIN;
	public static final short CG_BATTLE_FORMATION_ENABLE = ++BATTLE_FORMATION_BEGIN;
	public static final short GC_BATTLE_FORMATION_ENABLED = ++BATTLE_FORMATION_BEGIN;
	public static final short CG_CHANGE_ASSISTANT_HERO = ++BATTLE_FORMATION_BEGIN;
	
	/** 年代 */
	private static short ERA_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short GC_KALENDAR_INFO = ++ERA_BEGIN;
	
	private static short GAME_FUNC_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short GC_GAME_FUNC_LIST = ++GAME_FUNC_BEGIN;
	public static final short GC_GAME_FUNC_CHANGE = ++GAME_FUNC_BEGIN;
	
	private static short RECRUIT_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_RECRUIT_TYPE = ++RECRUIT_BEGIN;
	public static final short CG_RECRUIT_INFO = ++RECRUIT_BEGIN;
	public static final short GC_RECRUIT_RESULT = ++RECRUIT_BEGIN;
	public static final short GC_RECRUIT_INFO = ++RECRUIT_BEGIN;
	public static final short CG_RECRUIT_RESOVLE_FLAG = ++RECRUIT_BEGIN;
	public static final short CG_PREVIEW_HERO = ++RECRUIT_BEGIN ;
	public static final short GC_PREVIEW_HERO = ++RECRUIT_BEGIN ;
	
	private static short KINGDOM_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short GC_KINGDOM_INFO = ++KINGDOM_BEGIN;
	public static final short CG_ATTACK_KINGDOM = ++KINGDOM_BEGIN;
	public static final short GC_ATTACK_KINGDOM_SUCCESS = ++KINGDOM_BEGIN;
	public static final short CG_GET_CHAPTER_BONUS = ++KINGDOM_BEGIN;
	public static final short GC_CHOOSE_EXTRA_PROPS = ++KINGDOM_BEGIN;
	public static final short CG_CHOOSE_EXTRA_PROPS = ++KINGDOM_BEGIN;
	public static final short GC_KINGDOM_FINISH_BONUS = ++KINGDOM_BEGIN;
	public static final short CG_BUY_FINISH_BONUS = ++KINGDOM_BEGIN;
	public static final short GC_UPDATE_STATUS = ++KINGDOM_BEGIN;
	public static final short CG_SWIPE_KINGDOM = ++KINGDOM_BEGIN;
	public static final short GC_SWIPE_KINGDOM_RESULT = ++KINGDOM_BEGIN;
	public static final short GC_ALL_KINGDOM_PROPS = ++KINGDOM_BEGIN;
	public static final short CG_RESET_KINGDOM = ++KINGDOM_BEGIN;
	public static final short CG_KINGDOM_ELITE_CHALLENGE = ++KINGDOM_BEGIN;
	public static final short CG_KINGDOM_ELITE_BUY_CHALLENGE_COUNT = ++KINGDOM_BEGIN;
	
	
	private static short TREASURE_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short GC_TREASURE_PIECES_LIST = ++TREASURE_BEGIN;
	public static final short GC_TREASURE_FORMULA = ++TREASURE_BEGIN;
	public static final short GC_REMOVE_TREASURE_FORMULA = ++TREASURE_BEGIN;
	public static final short GC_NEW_TREASURE_PIECE = ++TREASURE_BEGIN;
	public static final short GC_CREATE_TREASURE_SUCC = ++TREASURE_BEGIN;
	public static final short CG_ROB_TREASURE_LIST = ++TREASURE_BEGIN;
	public static final short GC_ROB_TREASURE_LIST = ++TREASURE_BEGIN;
	public static final short CG_ROB_TREASURE = ++TREASURE_BEGIN;
	public static final short CG_CREATE_TREASURE = ++TREASURE_BEGIN;
	public static final short CG_SWIPE_TREASURE = ++TREASURE_BEGIN;
	public static final short GC_SWIPE_TREASURE = ++TREASURE_BEGIN;
	public static final short GC_CANT_ROB_TIME = ++TREASURE_BEGIN;
	public static final short CG_INSTANT_ROB_TREASURE = ++TREASURE_BEGIN;
	public static final short GC_INSTANT_ROB_TREASURE = ++TREASURE_BEGIN;
	public static final short CG_TREASURE_TRANSFER = ++TREASURE_BEGIN;
	public static final short GC_TREASURE_TRANSFER_SUCC = ++TREASURE_BEGIN;
	
	/** 好友  */
	private static short FRIEND_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_FRIEND_RECOMMEND = ++FRIEND_BEGIN;
	public static final short GC_FRIEND_RECOMMEND_LIST = ++FRIEND_BEGIN;
	public static final short CG_APPLY_ADD_FRIEND = ++FRIEND_BEGIN;
	public static final short CG_REJECT_ADD_FRIEND_APPLY = ++FRIEND_BEGIN;
	public static final short GC_FRIEND_APPLY_LIST_DELETE = ++FRIEND_BEGIN;
	public static final short CG_ACCEPT_ADD_FRIEND_APPLY = ++FRIEND_BEGIN;
	public static final short CG_OPEN_FRIEND_LIST = ++FRIEND_BEGIN;
	public static final short GC_FRIEND_LIST_INFO = ++FRIEND_BEGIN;
	public static final short CG_DELETE_FRIEND = ++FRIEND_BEGIN;
	public static final short GC_FRIEND_DELETE_RESULT = ++FRIEND_BEGIN;
	public static final short CG_SEND_ENERGY_DAN = ++FRIEND_BEGIN;
	public static final short CG_DRAW_ENERGY_DAN = ++FRIEND_BEGIN;
	public static final short CG_DRAW_ENERGY_DAN_ONE_KEY = ++FRIEND_BEGIN;
	public static final short GC_FRIEND_INFO_UPDATE = ++FRIEND_BEGIN;
	public static final short CG_FRIEND_APPLY_LIST = ++FRIEND_BEGIN;
	public static final short GC_FRIEND_APPLY_LIST = ++FRIEND_BEGIN;
	public static final short GC_FRIEND_APPLY_LIST_UPDATE = ++FRIEND_BEGIN;
	
	/** 联盟 */
	private static short GUILD_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short GC_REQUEST_GUILD_LIST = ++GUILD_BEGIN;
	public static final short GC_APPLY_GUILD_LIST = ++GUILD_BEGIN;
	public static final short CG_REQUEST_GUILD_LIST = ++GUILD_BEGIN;
	public static final short CG_APPLY_GUILD = ++GUILD_BEGIN;
	public static final short GC_APPLY_GUILD_SUCC = ++GUILD_BEGIN;
	public static final short CG_CANCEL_APPLY_GUILD = ++GUILD_BEGIN;
	public static final short GC_CANCEL_APPLY_GUILD_SUCC = ++GUILD_BEGIN;
	public static final short CG_CREATE_GUILD = ++GUILD_BEGIN;
	public static final short GC_CREATE_GUILD_SUCC = ++GUILD_BEGIN;
	public static final short GC_GUILD_RED_POINT_INFO = ++GUILD_BEGIN;
	public static final short CG_GUILD_PRAY_PANEL = ++GUILD_BEGIN;
	public static final short GC_GUILD_PRAY_PANEL = ++GUILD_BEGIN;
	public static final short CG_GUILD_DETAIL_PANEL = ++GUILD_BEGIN;
	public static final short GC_GUILD_DETAIL_PANEL = ++GUILD_BEGIN;
	public static final short CG_GUILD_MEMBER_PANEL = ++GUILD_BEGIN;
	public static final short GC_GUILD_MEMBER_PANEL = ++GUILD_BEGIN;
	public static final short CG_GUILD_NOTICE_PANEL = ++GUILD_BEGIN;
	public static final short GC_GUILD_NOTICE_PANEL= ++GUILD_BEGIN;
	public static final short CG_GUILD_PRAY= ++GUILD_BEGIN;
	public static final short GC_GUILD_PRAY_SUCCESS= ++GUILD_BEGIN;
	public static final short CG_GUILD_APPLY_PANEL= ++GUILD_BEGIN;
	public static final short GC_GUILD_APPLY_PANEL= ++GUILD_BEGIN;
	public static final short CG_PROCESS_GUILD_APPLY= ++GUILD_BEGIN;
	public static final short GC_PROCESS_GUILD_APPLY_SUCC= ++GUILD_BEGIN;
	public static final short CG_DISMISS_GUILD= ++GUILD_BEGIN;
	public static final short GC_DISMISS_GUILD_SUCC= ++GUILD_BEGIN;
	public static final short CG_GRANT_GUILD_PRIVILGE= ++GUILD_BEGIN;
	public static final short GC_GUILD_PRIVILGE_CHANGE= ++GUILD_BEGIN;
	public static final short CG_QUIT_GUILD= ++GUILD_BEGIN;
	public static final short GC_QUIT_GUILD_SUCC= ++GUILD_BEGIN;
	public static final short CG_KICK_GUILD_MEMBER= ++GUILD_BEGIN;
	public static final short GC_KICK_GUILD_MEMBER_SUCC= ++GUILD_BEGIN;
	public static final short CG_SEARCH_GUILD= ++GUILD_BEGIN;
	public static final short GC_SEARCH_GUILD_RESULT= ++GUILD_BEGIN;
	public static final short CG_CHANGE_ANNOUNCE= ++GUILD_BEGIN;
	public static final short GC_ANNOUNCE_CHANGE= ++GUILD_BEGIN;
	public static final short CG_GET_GUILD_PRAY_BONUS = ++GUILD_BEGIN;
	public static final short GC_GUILD_REFRESH_SHOP = ++GUILD_BEGIN;
	public static final short CG_BUY_GUILD_REFRESH_ITEM = ++GUILD_BEGIN;
	public static final short CG_OPEN_GUILD_REFRESH_SHOP = ++GUILD_BEGIN;
	public static final short GC_UPDATE_GUILD_REFRESH_ITEM = ++GUILD_BEGIN;
	public static final short GC_GET_GUILD_PRAY_BONUS_SUCC = ++GUILD_BEGIN;
	public static final short GC_UPDATE_GUILD_RED_POINT = ++GUILD_BEGIN;
	public static final short CG_REQ_GUILD_LEVEL_RANK = ++GUILD_BEGIN;
	public static final short GC_RESP_GUILD_LEVEL_RANK = ++GUILD_BEGIN;
	public static final short CG_REQ_GUILD_RAID_RANK = ++GUILD_BEGIN;
	public static final short GC_RESP_GUILD_RAID_RANK = ++GUILD_BEGIN;
	public static final short CG_GUILD_SKILLS = ++GUILD_BEGIN;
	public static final short GC_GUILD_SKILLS = ++GUILD_BEGIN;
	public static final short CG_GUILD_SKILL_UPDATE = ++GUILD_BEGIN;
	public static final short GC_GUILD_SKILL_UPDATE = ++GUILD_BEGIN;
	
	/** 叛军 */
	private static short REBEL_BEGIN = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_REQ_REBEL_PANEL_MSG = ++REBEL_BEGIN;
	public static final short GC_REBEL_PANEL_INFO_MSG = ++REBEL_BEGIN;
	public static final short GC_FIND_REBEL_PANEL_MSG = ++REBEL_BEGIN;
	public static final short CG_ATTACK_REBEL = ++REBEL_BEGIN;
	public static final short GC_REBEL_DEAD_UPDATE = ++REBEL_BEGIN;
	public static final short GC_ACCUMULATIVE_DEMAGE_BONUS = ++REBEL_BEGIN;
	public static final short CG_RECEIVE_ACCUMULATIVE_DEMAGE_BONUS = ++REBEL_BEGIN;
	public static final short GC_RECEIVE_ACCUMULATIVE_DEMAGE_BONUS = ++REBEL_BEGIN;
	public static final short CG_REBEL_ACCUMULATIVE_DAMAGE_RANKING = ++REBEL_BEGIN;
	public static final short CG_REBEL_MAX_DAMAGE_RANKING = ++REBEL_BEGIN;
	public static final short GC_REBEL_ACCUMULATIVE_DAMAGE_RANKING = ++REBEL_BEGIN;
	public static final short GC_REBEL_MAX_DAMAGE_RANKING = ++REBEL_BEGIN;
	public static final short CG_SHARE_REBEL_ARMY = ++REBEL_BEGIN;
	public static final short GC_SHARE_REBEL_ARMY_SECCESSED = ++REBEL_BEGIN;
	public static final short GC_REBEL_ATTACK_RESULT = ++REBEL_BEGIN;
	
	/** 护送 */
	private static short CONVOY_BASE = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short GC_CONVOY_CITY_LIST = ++CONVOY_BASE;
	public static final short GC_CONVOY_CITY_CHANGE = ++CONVOY_BASE;
	public static final short CG_ATTACK_CONVOY_CITY = ++CONVOY_BASE;
	public static final short CG_START_CONVOY = ++CONVOY_BASE;
	public static final short CG_REQUEST_CONVOY_DETAIL = ++CONVOY_BASE;
	public static final short GC_REQUEST_CONVOY_DETAIL = ++CONVOY_BASE;
	public static final short CG_GET_CONVOY_BONUS = ++CONVOY_BASE;
	public static final short CG_ALL_CONVOY_BONUS_PANEL = ++CONVOY_BASE;
	public static final short GC_ALL_CONVOY_BONUS_PANEL = ++CONVOY_BASE;
	public static final short CG_GET_ALL_CONVOY_BONUS = ++CONVOY_BASE;
	public static final short CG_CHECK_FRIEND_CONVOY_RED_POINT = ++CONVOY_BASE;
	public static final short GC_FRIEND_CONVOY_RED_POINT = ++CONVOY_BASE;
	public static final short CG_OPEN_CONVOY_FRIEND_LIST = ++CONVOY_BASE;
	public static final short GC_CONVOY_FRIEND_LIST = ++CONVOY_BASE;
	public static final short CG_VIEW_FRIEND_CONVOY = ++CONVOY_BASE;
	public static final short GC_VIEW_FRIEND_CONVOY = ++CONVOY_BASE;
	public static final short GC_FRIEND_CONVOY_CITY_LIST = ++CONVOY_BASE;
	public static final short CG_HELP_FRIEND_CONVOY = ++CONVOY_BASE;
	public static final short GC_HELP_FRIEND_CONVOY_SUCC = ++CONVOY_BASE;
	public static final short CG_CHECK_CONVOY_CITY = ++CONVOY_BASE;
	public static final short CG_CONVOY_SKILL_LIST = ++CONVOY_BASE;
	public static final short GC_CONVOY_SKILL_LIST = ++CONVOY_BASE;
	public static final short CG_CONVOY_SKILL_UPDATE = ++CONVOY_BASE;
	public static final short GC_CONVOY_SKILL_UPDATE = ++CONVOY_BASE;
	
	/** 护送粮草 */
	private static short DEFENCE_GRANARY = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_GRANARY_ENTER = ++DEFENCE_GRANARY;
	public static final short CG_GRANARY_BATTLE = ++DEFENCE_GRANARY;
	public static final short CG_GRANARY_EXCITE = ++DEFENCE_GRANARY;
	public static final short GC_GRANARY_INFO_LIST = ++DEFENCE_GRANARY;
	public static final short GC_GRANARY_SELF_INFO = ++DEFENCE_GRANARY;
	public static final short CG_GRANARY_BATTLE_RECORD = ++DEFENCE_GRANARY;
	public static final short GC_GRANARY_BATTLE_RECORD = ++DEFENCE_GRANARY;
	public static final short CG_GRANARY_GAIN_VALUE_BONUS = ++DEFENCE_GRANARY;
	public static final short GC_GRANARY_GAIN_VALUE_BONUS = ++DEFENCE_GRANARY;
	public static final short CG_GRANARY_GAIN_RANK_BONUS = ++DEFENCE_GRANARY;
	public static final short GC_GRANARY_RANK_INFO = ++DEFENCE_GRANARY;
	public static final short GC_GRANARY_GAIN_STATE_UPDATE = ++DEFENCE_GRANARY;
	public static final short GC_GRANARY_FIELD_UPDATE = ++DEFENCE_GRANARY;
	public static final short CG_GRANARY_CHANGE = ++DEFENCE_GRANARY;
	public static final short CG_GRANARY_RANK_INFO = ++DEFENCE_GRANARY;
	public static final short CG_GRANARY_EXIT = ++DEFENCE_GRANARY;
	public static final short CG_GRANARY_BUY_ROB_COUNT = ++DEFENCE_GRANARY;
	public static final short GC_GRANARY_SELF_UPDATE = ++DEFENCE_GRANARY;
	
	/** 世界boss */
	private static short HULAO_BIG_WAR_BASE = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_OPEN_HULAO_BIGWAR_PANEL_MSG = ++HULAO_BIG_WAR_BASE;
	public static final short CG_CLOSE_HULAO_BIGWAR_PANEL_MSG = ++HULAO_BIG_WAR_BASE;
	public static final short GC_HULAO_BOSS_INFO = ++HULAO_BIG_WAR_BASE;
	public static final short CG_REQUEST_HULAO_RANK = ++HULAO_BIG_WAR_BASE;
	public static final short GC_HULAO_RANKING = ++HULAO_BIG_WAR_BASE;
	public static final short GC_HULAO_WUSHUANG_BONUS_STATE = ++HULAO_BIG_WAR_BASE;
	public static final short CG_DRAW_WUSHUANG_BONUS = ++HULAO_BIG_WAR_BASE;
	public static final short GC_HULAO_WUSHUANG_BONUS_STATE_UPDATE = ++HULAO_BIG_WAR_BASE;
	public static final short CG_ATTACK_HULAO_BOSS = ++HULAO_BIG_WAR_BASE;
	public static final short CG_OPEN_BONUS_LIST = ++HULAO_BIG_WAR_BASE;
	public static final short GC_HULAO_ACTIVITY_BONUS_INFO = ++HULAO_BIG_WAR_BASE;
	public static final short CG_SHULAO_ACTIVITY_BONUS_GAIN = ++HULAO_BIG_WAR_BASE;
	public static final short GC_HULAO_ACTIVITY_BONUS_REMOVE = ++HULAO_BIG_WAR_BASE;
	public static final short CG_HULAO_ACHIEVEENT_BONUS_STATE = ++HULAO_BIG_WAR_BASE;
	public static final short GC_HULAO_BOSS_HP_UPDATE = ++HULAO_BIG_WAR_BASE;
	public static final short CG_REQ_INSPIRING_INFO = ++HULAO_BIG_WAR_BASE;
	public static final short GC_INSPIRING_INFO = ++HULAO_BIG_WAR_BASE;
	public static final short CG_INSPIRING_ACTIVATE = ++HULAO_BIG_WAR_BASE;
	public static final short GC_HULAO_BIG_WAR_REPORT_PANEL = ++HULAO_BIG_WAR_BASE;
	public static final short GC_BATTLE_REPORT_UPDATE = ++HULAO_BIG_WAR_BASE;
	public static final short CG_BUY_HULAO_BATTLE_TIME = ++HULAO_BIG_WAR_BASE;
	public static final short GC_HULAO_HAVE_NEW_REPORT = ++HULAO_BIG_WAR_BASE;
	public static final short CG_REQ_BIG_WAR_REPORT = ++HULAO_BIG_WAR_BASE;
	public static final short GC_HULAO_CHARGE_TIME_RECOVER_INFO = ++HULAO_BIG_WAR_BASE;
	public static final short GC_HULAO_ACTIVITY_REMAIN_TIMES = ++HULAO_BIG_WAR_BASE;
	public static final short GC_HULAO_RED_SPOT_MSG = ++HULAO_BIG_WAR_BASE;
	
	/** 开服基金 */
	private static short FOUNDATION_BASE = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short GC_FOUNDATION_GET_BONUS_LIST = ++FOUNDATION_BASE;
	public static final short CG_BUY_FOUNDATION = ++FOUNDATION_BASE;
	public static final short CG_FOUNDATION_SERVER_COUNT = ++FOUNDATION_BASE;
	public static final short GC_FOUNDATION_SERVER_COUNT = ++FOUNDATION_BASE;
	public static final short CG_FOUNDATION_GET_BONUS = ++FOUNDATION_BASE;
	public static final short GC_FOUNDATION_GET_BONUS = ++FOUNDATION_BASE;
	

	/** 联盟副本 */
	private static short GUILD_RAID_BASE = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_OPEN_GUILD_RAID_PANEL = ++GUILD_RAID_BASE ;
	public static final short GC_OPEN_GUILD_RAID_PANEL = ++GUILD_RAID_BASE ;
	public static final short CG_RESET_CHAPTER = ++GUILD_RAID_BASE ;
	public static final short CG_OPEN_FINISH_CHAPTER_PANEL = ++GUILD_RAID_BASE ;
	public static final short GC_OPEN_FINISH_CHAPTER_PANEL = ++GUILD_RAID_BASE ;
	public static final short CG_OPEN_CHAPTER_PANEL = ++GUILD_RAID_BASE ;
	public static final short GC_OPEN_CHAPTER_PANEL = ++GUILD_RAID_BASE ;
	public static final short CG_BUY_CHALLENGE_COUNT = ++GUILD_RAID_BASE ;
	public static final short GC_BUY_CHALLENGE_COUNT = ++GUILD_RAID_BASE ;
	public static final short CG_START_CHALLENGE = ++GUILD_RAID_BASE ;
	public static final short GC_START_CHALLENGE = ++GUILD_RAID_BASE ;
	public static final short CG_OPEN_TEAM_BOX_PANEL = ++GUILD_RAID_BASE ;
	public static final short GC_OPEN_TEAM_BOX_PANEL = ++GUILD_RAID_BASE ;
	public static final short CG_PREVIEW_TEAM_BOX = ++GUILD_RAID_BASE ;
	public static final short CG_RECEIVE_FIRST_PASS_CHAPTER = ++GUILD_RAID_BASE ;
	public static final short CG_RECEIVE_TEAM_BOX_BONUS = ++GUILD_RAID_BASE ;
	public static final short GC_PREVIEW_TEAM_BOX = ++GUILD_RAID_BASE;
	public static final short CG_MAX_DAMAGE_RANK_PANEL = ++GUILD_RAID_BASE;
	public static final short GC_MAX_DAMAGE_RANK_PANEL = ++GUILD_RAID_BASE ;
	
	/**
	 * 名将傅本
	 */
	public static short FAMOUS_BASE = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short GC_FAMOUS_RAID_DATA = ++FAMOUS_BASE;
	public static final short CG_ATTACK_FAMOUS_RAID = ++FAMOUS_BASE;
	public static final short GC_FAMOUS_RAID_INFO = ++FAMOUS_BASE;
	public static final short GC_FAMOUS_CHAPTER_INFO = ++FAMOUS_BASE;
	public static final short CG_GET_RAID_BOX_BONUS = ++FAMOUS_BASE;
	public static final short CG_CHALLENGE_CAMP_RAID = ++FAMOUS_BASE;
	
	/**
	 * 限时优惠
	 */
	
	public static short LIMIT_BUY_BASE = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_REQUEST_LIMIT_BUY = ++LIMIT_BUY_BASE;
	public static final short GC_LIMIT_BUY = ++LIMIT_BUY_BASE;
	public static final short CG_BUY_LIMIT_SHOP_ITEM = ++LIMIT_BUY_BASE;
	public static final short CG_CLAIM_LIMIT_SHOP_BONUS = ++LIMIT_BUY_BASE;
	public static final short GC_LIMIT_BUY_CHARGE_ID = ++LIMIT_BUY_BASE;

	/** 团购 */
	public static short GROUP_BUY_BASE = (BASE_NUMBER += NUMBER_PER_SUB_SYS);
	public static final short CG_OPEN_PANEL = ++GROUP_BUY_BASE ;
	public static final short GC_GROUP_BUY_INFO = ++GROUP_BUY_BASE ;
	public static final short CG_BUY_GROUP_ITEM = ++GROUP_BUY_BASE ;
	public static final short GC_UPDATE_GROUP_ITEM = ++GROUP_BUY_BASE ;
	public static final short CG_OPEN_NUM_PANEL = ++GROUP_BUY_BASE ;
	public static final short GC_OPEN_NUM_PANEL = ++GROUP_BUY_BASE ;
	public static final short CG_RECEIVE_BUY_NUM_BONUS = ++GROUP_BUY_BASE ;
	public static final short CG_RECEIVE_PRICE_DIFF = ++GROUP_BUY_BASE;
	
}


