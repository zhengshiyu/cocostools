/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.lz.generate;

import com.lz.model.GuiModel;
import com.lz.model.ModuleConfig;
import com.lz.util.Constants;
import com.lz.util.FileUtil;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class BaseGenerate {
	protected String inputFile;
	protected String outputFile;
	protected String moduleName;
	protected String folderName;
	protected String Name;
	protected List<GuiModel> guiModelList;

	public void init(String folderName, String moduleName, String inputFile) {
		this.folderName = folderName;
		this.moduleName = moduleName;
		this.inputFile = inputFile;
		this.guiModelList = new ArrayList();
	}

	private void loadWidgetTree(MorphDynaBean widgetTree) {
		GuiModel guiModel = new GuiModel();
		guiModel.setHasName(true);

		String classname = (String) widgetTree.get("classname");

		List children = (List) widgetTree.get("children");
		MorphDynaBean options = (MorphDynaBean) widgetTree.get("options");
		Integer tag = (Integer) options.get("tag");
		String name = (String) options.get("name");
		if ((name == null) || (name.equals("")) || (name.equals("null"))) {
			name = classname.substring(0, 1).toLowerCase() + classname.substring(1, classname.length()) + tag;
			guiModel.setHasName(false);
		}
		guiModel.setClassname(classname);
		guiModel.setName(name);
		guiModel.setTag(tag.intValue());
		this.guiModelList.add(guiModel);
		for (int i = 0; i < children.size(); i++) {
			MorphDynaBean morphDynaBean = (MorphDynaBean)children.get(i);
			loadWidgetTree(morphDynaBean);
		}
	}

	public void readCocosJson(ModuleConfig module) {
		String jsonStr = FileUtil.readFileToStr(this.inputFile);
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		Object beanObj = JSONObject.toBean(jsonObject, Map.class);

		Map map = (Map) beanObj;
		MorphDynaBean widgetTree = (MorphDynaBean) map.get("widgetTree");
		loadWidgetTree(widgetTree);
		System.out.println("guiModelList.size()=" + this.guiModelList.size());
	}

	public void readCocosCsd(ModuleConfig moduleCnf) {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder db = dbf.newDocumentBuilder();

      Document doc = db.parse(this.inputFile);
      NodeList dogList = doc.getElementsByTagName("PropertyGroup");
      Node dog = dogList.item(0);
      Element elem = (Element)dog;
      System.out.println("classname" + elem.getAttribute("Type"));
      GuiModel guiModel = new GuiModel();
      guiModel.setHasName(true);
      guiModel.setClassname(elem.getAttribute("Type"));
      guiModel.setName(elem.getAttribute("Name"));
      guiModel.setTag(0);
      //this.guiModelList.add(guiModel);

      dogList = doc.getElementsByTagName("AbstractNodeData");

      int length = dogList.getLength();
      for (int i = 0; i < length; ++i) {
        dog = dogList.item(i);

        elem = (Element)dog;

        guiModel = new GuiModel();
        guiModel.setHasName(true);
        guiModel.setParentName(getParentName(elem));
        guiModel.setClassname(elem.getAttribute("ctype").replace(
          "ObjectData", ""));
        guiModel.setName(elem.getAttribute("Name"));
        guiModel.setTouchEnable(elem.getAttribute("TouchEnable"));
        guiModel.setTag(Integer.parseInt(elem.getAttribute("Tag")));
        setImageList(moduleCnf, elem);
        if ("ProjectNode".equals(guiModel.getClassname())) {
          NodeList list = elem.getChildNodes();
          for (int j = list.getLength() - 1; j >= 0; --j) {
            dog = list.item(j);
            if ("FileData".equals(dog.getNodeName())) {
              elem = (Element)dog;
              guiModel.setControlClass(elem.getAttribute("Path")
                .replace(".csd", ""));
              this.guiModelList.add(guiModel);
              //label415: break label415:
              label415: break;
            }
          }
        } else {
          this.guiModelList.add(guiModel);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("guiModelList.size()=" + this.guiModelList.size()); }

	protected void setImageList(ModuleConfig moduleCnf, Element elem) {
		NodeList list = elem.getChildNodes();

		for (int j = list.getLength() - 1; j >= 0; --j) {
			Node dog = list.item(j);
			if ("FileData".equals(dog.getNodeName())) {
				elem = (Element) dog;
				String path = elem.getAttribute("Path");
				if (path.startsWith("image/ui/")) {
					String[] paths = path.split("/");
					if (paths.length <= 1)
						return;
					moduleCnf.setPlist(paths[2]);

					return;
				}
				if (!(path.endsWith("csd")))
					return;
				readSubCsd(moduleCnf, Constants.jsonPath + path);

				return;
			}
		}
	}

	protected void readSubCsd(ModuleConfig moduleCnf, String inputFile) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(inputFile);
			NodeList dogList = doc.getElementsByTagName("PropertyGroup");
			Node dog = dogList.item(0);
			Element elem = (Element) dog;
			dogList = doc.getElementsByTagName("NodeObjectData");

			int length = dogList.getLength();
			for (int i = 0; i < length; ++i) {
				dog = dogList.item(i);
				elem = (Element) dog;
				setImageList(moduleCnf, elem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getParentName(Element elem) {
		Element parent;
		try {
			parent = (Element) elem.getParentNode();
		} catch (Exception e) {
			parent = null;
		}
		String parentName;
		if (parent != null)
			if (parent.hasAttribute("Name")) {
				parentName = parent.getAttribute("Name");
				if (parentName.equals(""))
					parentName = "self";
				else {
					parentName = "self." + parentName;
				}
				if ("ObjectData".equals(parent.getTagName()))
					parentName = "self";
			} else {
				parentName = getParentName(parent);
			}
		else {
			parentName = "self";
		}
		return parentName;
	}

	public abstract void writeLuaCode();
}