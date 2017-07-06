package com.github.user.dao;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.print.PrintException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.github.user.domain.User;

/**
 * 数据类
 * @author Administrator
 *
 */
public class UserDao {
	private String path="E:/users.xml";
	/**
	 * 通过xpath查询得到element;
	 * 检验ele是否为空，为空返回null;
	 * 把ele数据封装到user对象中
	 * @param username
	 * @return
	 */
	public User findByUsername(String username){
		SAXReader reader=new SAXReader();
		try {
			Document doc=reader.read(path);
			//因为写错了一个方法名，调试了半天！！！selectSingleNode 写成selectNodes!!!
			Element ele= (Element) doc.selectSingleNode("//user[@username='"+username+"']");
			
			if(ele==null)return null;
			
			User user=new User();
			String attrUsername=ele.attributeValue("username");
			String attrPassword=ele.attributeValue("password");
			System.out.println(attrUsername);
			user.setUsername(attrUsername);
			user.setPassword(attrPassword);
			
			return user;
		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}
	}
	
	public void add(User user){
		/**
		 * 	1.得到Document
		 * 	2.通过Document得到根元素<users>
		 * 	3.使用参数user,转换成Element对象
		 *	4.把element添加到root中
		 *	5.保存DOcument
		 */
		SAXReader reader= new SAXReader();
		try {
			Document doc =reader.read(path);
			Element root=doc.getRootElement();
			Element userEle=root.addElement("user");
			userEle.addAttribute("username",user.getUsername());
			userEle.addAttribute("password", user.getPassword());
			
			/**
			 * 回写
			 */
			OutputFormat format = new OutputFormat("\t",true);//param 1.缩进格式 2.是否换行
			//清空原有的换行和缩进
			format.setTrimText(true);
			XMLWriter writer=new XMLWriter(new OutputStreamWriter(new FileOutputStream(path),"utf-8"),format);
			writer.write(doc);//保存Document对象
			writer.close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
