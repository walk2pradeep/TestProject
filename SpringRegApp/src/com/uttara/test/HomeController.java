package com.uttara.test;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@Autowired
	Service service;
	
	@RequestMapping("/")
	public String showHome()
	{
		//pre-processing for Home page view
		System.out.println("in HC->showHome()");
		return "Home";
				
	}
	
	@RequestMapping("/openLoginView")
	public String showLoginView()
	{
		System.out.println("in HC->showLoginView()");
		return "Login";
	}
	
	
	@RequestMapping("/openRegisterView")
	public String showRegisterView(Model model)
	{
		System.out.println("in HC->showRegisterView()");
		RegBean bean = new RegBean();
		//bean.setUname("Tambi");
		model.addAttribute("reg", bean);
		return "Register";
	}
	@RequestMapping("/openDisplayView")
	public String showDisplayUsers(Model model)
	{
		List<RegBean> users = service.getUserDetails();
		System.out.println(users);
		
		model.addAttribute("listUsers", users);
		return "DisplayUsers";
	}
	
	@RequestMapping("/register")
	public String register(@ModelAttribute("reg") @Valid RegBean bean, BindingResult result, Model model, HttpSession session)
	{
		System.out.println("in HC->register() "+bean);
		if(result.hasErrors())
		{
			return "Register";
		}
		else
		{
			String msg = service.register(bean);
			if(msg.equals("success"))
				
				return "Success";
			else
			{
				model.addAttribute("errorMsg", msg);
				return "Register";
			}
		}
	}
}





