package main;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;


/**
 * 
 * 客户端lua消息生成
 * 
 * @author yaoshuai
 *
 */
public class LuaMessageGenerator {

	private Map<String, Short> usedMsgType = new LinkedHashMap<String, Short>();
	// for cocos2dx use only
	private Map<String, Short> msgType2Num = new LinkedHashMap<String, Short>();
	//所有的message elements 按模块
	private Map<String, List<Element>> allElements= new LinkedHashMap<>();

	public static final LuaMessageGenerator luaGenerator = new LuaMessageGenerator();
	
	private Map<String, Element> macroList = new HashMap<>();
	
	private String templateFile = "gccg_lua_message_c.vm";
	private String messageTypeTemplate = "lua_message_type.vm";
	private String messageRequirelLuaTemlate = "lua_message_requirel.vm";
	private String messageTypeFuncMapTemplate ="lua_msg_type_func_map.vm";
	
	private String luaAboutFiles[] = new String[]{messageTypeTemplate, messageRequirelLuaTemlate, messageTypeFuncMapTemplate};
	private String luaAboutFilesName[] = new String[]{"MessageType.lua", "MessageRequire.lua", "MsgTypeFuncMap.lua"};
	
	public String resourcesPath;
	
	private LuaMessageGenerator() {
		this.initMsgType2Num();
	}

	public void createLuaMessage(List<Element> element, String module,
			String outputPath) {
		List<MessageBean> _messageList = new ArrayList<MessageBean>();
		for (Element _aMssage : element) {
			MessageBean _mb = this.buildMessageFrom(_aMssage);
			if (_mb != null) {
				_messageList.add(_mb);
			}
		}
		// 写到lua
		for (MessageBean message : _messageList) {
			
			System.out.println(message.toString());
			String outFile;
			if (message.isCg()) {
				outFile = outputPath + "/client2server/" + message.getName() + "Message.lua";
			} else {
				outFile = outputPath + "/server2client/" + message.getName() + "Message.lua";
			}
			// 生成
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void createOtherLua(String outputPath) {
		TreeSet<MessageTypeBean> _ts = new TreeSet<>();
		for (Map.Entry<String, Short> _entry : usedMsgType.entrySet()) {
			MessageTypeBean _tb = new MessageTypeBean();
			_tb.setType(_entry.getKey());
			_tb.setId(_entry.getValue());
			_tb.patchUpName();
			_ts.add(_tb);
		}
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * 从excel模板读成类的形式
	 * 
	 * @param element
	 * @return
	 */
	private MessageBean buildMessageFrom(Element element) {
		MessageBean _message = new MessageBean();
		_message.setType(element.getAttributeValue("type"));
		_message.setName(element.getAttributeValue("type"));
		_message.setId(getMessageIdOfType(_message.getType()));
		String _messageComment = element.getAttributeValue("comment");
		_message.setComment(_messageComment == null ? "这个家伙很懒什么注释都没有留下" : _messageComment);
		@SuppressWarnings("unchecked")
		List<Element> _fildsList = element.getChildren();
		_message.setFields(buildFiledRecursion(_fildsList));
		_message.patchUp();
		return _message;
	}

	public Short getMessageIdOfType(String messageType) {
		if (messageType == null || messageType.equals("")) {
			throw new RuntimeException("消息不存在" + messageType);
		}
		if (!msgType2Num.containsKey(messageType)) {
			throw new RuntimeException("消息不存在,是不是没有更新MessageType,类消息名" + messageType);
		}
		usedMsgType.put(messageType, this.msgType2Num.get(messageType));
		return this.msgType2Num.get(messageType);
	}

	@SuppressWarnings("unchecked")
	private List<FieldBean> buildFiledRecursion(List<Element> fElements) {
		List<FieldBean> fieldsList = new ArrayList<>(fElements.size());
		for (Element fElement : fElements) {
			FieldBean _fb = new FieldBean();
			fieldsList.add(_fb);
			_fb.setType(fElement.getAttributeValue("type"));
			_fb.setName(fElement.getAttributeValue("name"));
			String comment = fElement.getAttributeValue("comment");
			_fb.setComment(comment == null ? "" : comment);
			_fb.setList("true".equals(fElement.getAttributeValue("list")));
			if (fElement.getChildren() == null || fElement.getChildren().isEmpty()) {
				continue;
			} else {
				_fb.setFields(this.buildFiledRecursion(fElement.getChildren()));
			}
		}
		return fieldsList;
	}
	/**
	 * 暂时只读了一层的filed如果有多层嵌套要改成低柜 地柜 递归
	 * 
	 * @param fElements
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<FieldBean> buildFileFrom(List<Element> fElements) {
		if (fElements == null || fElements.isEmpty()) {
			return new ArrayList<>(0);
		}
		List<FieldBean> _fieldsOfMessage = new ArrayList<>();
		for (Element fElement : fElements) {
			FieldBean _topFb = new FieldBean();
			_topFb.setType(fElement.getAttributeValue("type"));
			_topFb.setName(fElement.getAttributeValue("name"));
			_topFb.setComment(fElement.getAttributeValue("comment") == null ? ""
					: fElement.getAttributeValue("comment"));
			List<Element> _subFields = fElement.getChildren("field");
			for (Element _subField : _subFields) {
				FieldBean _subFb = new FieldBean();
				_subFb.setType(_subField.getAttributeValue("type"));
				_subFb.setName(_subField.getAttributeValue("name"));
				_subFb.setComment(_subField.getAttributeValue("comment") == null ? ""
						: _subField.getAttributeValue("comment"));
				_topFb.addFieldBean(_subFb);
			}
			_fieldsOfMessage.add(_topFb);
		}
		return _fieldsOfMessage;
	}


	private void initMsgType2Num() {
		Class<MessageType> _messageTypeClazz = MessageType.class;
		Field fields[] = _messageTypeClazz.getDeclaredFields();
		for (Field field : fields) {
			String fName = field.getName();
			if (fName.length() <= 3) {
				continue;
			}
			if (!fName.startsWith("GC_") && !fName.startsWith("CG_")) {
				continue;
			}
			if ((field.getModifiers() & Modifier.STATIC) == 0) {
				continue;
			}
			try {
				Short _messageId = (short) field.get(null);
				if (msgType2Num.containsKey(field.get(field.getName()))) {
					throw new RuntimeException("重复messageName"
							+ field.getName());
				}
				if (msgType2Num.values().contains(_messageId)) {
					throw new RuntimeException("重复id" + field.getName()
							+ " id=" + _messageId);
				}
				msgType2Num.put(field.getName(), _messageId);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		
		String xmlPath;
		String toPath;
		boolean needPickXml = false;
		
		System.err.println("参数们 :" + Arrays.toString(args));
		
		if (args.length != 0) {
			System.out.println("useage:args[0] = xml's path, args[1] = outpath");
			return;
		} else {
			//xmlPath  = args[0].trim();
			//toPath = args[1].trim();
			
			xmlPath  = "/Users/douzi/Desktop/xml";
			toPath  = "/Users/douzi/Desktop/message";
			if (args.length > 2) {
				needPickXml = args[2].trim().equals("true");
			}
		}
		LuaMessageGenerator generator = LuaMessageGenerator.luaGenerator;
		if (needPickXml) {
			generator.drawNeedMessageXmls();
		}
		generator.loadMacros(xmlPath);
		generator.loadXmls(xmlPath);
		generator.generate(toPath);
	}

	private void generate(String toPath) {
		for (Map.Entry<String, List<Element>> moudlEmelents : this.allElements.entrySet()) {
			this.createLuaMessage(moudlEmelents.getValue(), moudlEmelents.getKey(), toPath);
		}
		//生成messageType
		createOtherLua(toPath);
	}

	private void loadXmls(String from) throws Exception {
		File xmlPath = new File(from);
		File[] xmls = xmlPath.listFiles(new XmlFileFilter());
		if (xmls == null || xmls.length == 0) {
			throw new IllegalArgumentException("file:" + from + " does not exists xml files!");
		}
		
		for (File _file : xmls) {
			SAXBuilder builder = new SAXBuilder();
			Document _doc = builder.build(_file);
			Element _root = _doc.getRootElement();
			String _module = _root.getAttributeValue("module");// 所属模块
			@SuppressWarnings("unchecked")
			List<Element> _messages = _root.getChildren("message",
					Namespace.getNamespace("http://cn.kx.lj.message"));// 消息体定义
			for (Element _msgElement : _messages) {
				this.deplyMergeMacros(_msgElement);
			}
			//替换宏
			allElements.put(_module, _messages);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void deplyMergeMacros(Element msgElement) {
		if (msgElement.getChildren() == null || msgElement.getChildren().isEmpty()) {
			return;
		}
		List<Element> _fel = msgElement.getChildren();
		this.deplyMergeMacros(_fel);
	}

	@SuppressWarnings("unchecked")
	private void deplyMergeMacros(List<Element> fieldElementList) {
		for (Element fieldElement : fieldElementList) {
			if (fieldElement.getAttributeValue("macro") != null) {
				this.doReplaceMacros(fieldElement, fieldElement.getAttributeValue("macro"));
			}
			if (fieldElement.getChildren() == null || fieldElement.getChildren().isEmpty()) {
				continue;
			} else {
				this.deplyMergeMacros(fieldElement.getChildren());
			}
		}
	}

	public void loadMacros(String xmlPath) {
		SAXBuilder builder = new SAXBuilder();
		Document _doc = null;
		try {
			_doc = builder.build(xmlPath + File.separator + "macros.xml");
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		Element _root = _doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> elements = _root.getChildren("macro",
				Namespace.getNamespace("http://cn.kx.lj.message"));
		for (Element element : elements) {
			macroList.put(element.getAttributeValue("id"), element);
		}
		//下面递归替换了 这里不用了
//		this.mergeMacros();
	}
	
	private void mergeMacros() {
		for (Element _element : macroList.values()) {
			for (Object _e : _element.getChildren("field", Namespace.getNamespace("http://cn.kx.lj.message"))) {
				Element _elementField = (Element) _e;
				String _innerMacroId = _elementField.getAttributeValue("macro");
				if(_elementField.getAttributeValue("macro") != null) {
					this.doReplaceMacros(_elementField, _innerMacroId);
				}
			}
		}
	}

	private boolean doReplaceMacros(Element elementField, String innerMacroId) {
		if (!this.macroList.containsKey(innerMacroId)) {
			System.out.println("macro id not exist! id = " + innerMacroId);
			return false;
		}
		Element macro = macroList.get(innerMacroId);
		Element cloneMacro = (Element) macro.clone();
		elementField.addContent(cloneMacro.removeContent());
		return true;
	}

	class XmlFileFilter implements FileFilter{
		private Pattern pattern;
		
		public XmlFileFilter() {
			pattern = Pattern.compile(".*\\.xml");
		}
		
		@Override
		public boolean accept(File file) {
			return pattern.matcher(file.getName()).find();
		}
	}

	public Object drawNeedMessageXmls() {
		ScriptEngine _scriptEngine = (ScriptEngine) new ScriptEngineManager()
				.getEngineByName("JavaScript");
		_scriptEngine.put("engine", new DrawFieldEngine());
		InputStream _inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("./msg/message_generator.js");
		try {
			return _scriptEngine.eval(new InputStreamReader(_inputStream));
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 我记得这个是吧用到的xml拷贝出来的一个方法 
	 * 貌似
	 * 
	 * @author yaoshuai
	 *
	 */
	public class DrawFieldEngine {
		public void createMessageFiles(String fileName) {
			InputStream _inputStream = this.getClass().getClassLoader()
					.getResourceAsStream("./msg/model/" + fileName);
			ByteArrayOutputStream _bao = new ByteArrayOutputStream();
			BufferedInputStream _bi = new BufferedInputStream(_inputStream);
			byte[] buffer = new byte[1024];
			int readLength = 0;
			try {
				while ((readLength = _bi.read(buffer, 0, buffer.length)) > 0) {
					_bao.write(buffer, 0, readLength);
				}

				File f = new File("./xml");
				f.mkdir();
				byte[] _byteArray = _bao.toByteArray();
				FileOutputStream _fos = new FileOutputStream("./xml/" + fileName);
				_fos.write(_byteArray);
				_fos.flush();
				_fos.close();
				_bao.close();
				_inputStream.close();
				System.err.println(fileName + " generator OK!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
