package org.action;

import java.util.Map;

import org.dao.DbService;
import org.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{
	private User user;//����һ��User��Ķ���

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	/*public String execute(){
		if(user.getUsername().equals("ky")&&user.getPassword().equals("123")){
			return SUCCESS;}
		else
			return ERROR;
	}*/
	
	//��½����login()
	public String login()throws Exception {
		/*if(user.getUsername().equals("ky")&&user.getPassword().equals("123")){
			System.out.print("name=ky");
			return SUCCESS;
		}
		if(user.getUsername().equals("zzzz")&&user.getPassword().equals("111")){
			System.out.print("name=zzzz");
			return SUCCESS;
		}
		else {
			System.out.print("name=error");
			return ERROR;
		}*/
		DbService dbService = new DbService();
		user = dbService.hasUser(user.getUsername(),user.getPassword());
		if(user.getUsername()==null) {
			this.addFieldError(user.getUsername(), "�û������벻��ȷ��");
			System.out.print("�û������벻��ȷ��\n");
			return "login_error";//��¼ʧ��
		} else {
			ActionContext actionContext = ActionContext.getContext();
			Map session = actionContext.getSession();
			session.put("user", user.getUsername());
		}
		return "login_success";//��½�ɹ�
	}
	
	//ע�᷽��regist()
	public String regist()throws Exception {
		User user_=new User();
		user_.setUsername(user.getUsername());
		user_.setPassword(user.getPassword());
		DbService dbService=new DbService();
		if(dbService.hasSameUser(user.getUsername())){
			this.addFieldError(user.getUsername(), "�û����Ѵ��ڣ�");
			System.out.print("�û����Ѵ��ڣ�\n");
			return "regist_error";//ע��ʧ��
		}
		else{
			dbService.addUser(user_);
		}
		return "regist_success";//ע��ɹ�
		}
	
}
