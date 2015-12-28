package com.faceye.core.service.security.web.controller;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.faceye.core.service.security.model.User;
import com.faceye.core.service.security.service.iface.IUserService;
import com.faceye.core.service.security.web.form.UserForm;
import com.faceye.core.web.controller.ActionTemplate;

public class UserAction extends ActionTemplate {
	private IUserService userService = null;

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		UserForm userForm = (UserForm) form;
		ActionMessages messages = new ActionMessages();
		PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		Map params=super.getRequestParameterMap(request);
		User user=null;
		if(params.containsKey("id")){
			if(StringUtils.isEmpty(params.get("id").toString())){
				user=new User();
			}else{
				user=(User)super.getEntity(User.class, params.get("id").toString());
			}
		}else{
			user=new User();
		}
		user.setUsername(params.get("name").toString());
		user.setEmail(params.get("name").toString());
		user.setAccountExpired(true);
		user.setAccountLocked(false);
		user.setCredentialsExpired(true);
		user.setEnabled(true);
	    

		//User user = (User) super.copyForm2Model(new User(), form);
		String userOldPassword=user.getPassword();
        copyForm2Model(user,form);
			if (StringUtils.isEmpty(user.getId())) {
			if (this.getUserService().isNotUnique(user, "username")) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"security.user.name.existed",user.getUsername()));
				if(!messages.isEmpty()){
					super.saveMessages(request, messages);
					return super.globalMessage(mapping, form, request, response);
					
				}
			}
		}
		if(!params.get("password").toString()
				.equals(userOldPassword)){
          user.setPassword(passwordEncoder.encodePassword(user.getPassword(),
						null));
		}
		this.getUserService().saveOrUpdateUser(user);
		messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("security.user.register.success",user.getUsername()));
		if(!messages.isEmpty()){
			super.saveMessages(request, messages);
//			return super.message(mapping, form, request, response);
			return super.globalMessage(mapping, form, request, response);
		}
		return null;
	}

	public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
          Serializable id=super.getHttp().getEntityId(request);
		  User user=(User) super.getEntity(User.class,id);
		  UserForm userForm=(UserForm) super.copyModel2Form(form,user);
		  userForm.setRePassword(user.getPassword());
		  return mapping.findForward("save");
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserService getUserService() {
		return userService;
	}

	@Override
	protected void onInitEntity(HttpServletRequest request, Object o, ActionForm form) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form, Object o) {
		// TODO Auto-generated method stub
		UserForm uf=(UserForm)form;
		
		
	}
}
