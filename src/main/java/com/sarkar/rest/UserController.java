package com.sarkar.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sarkar.dto.LoginFormDTO;
import com.sarkar.dto.QuoteApiResponseDTO;
import com.sarkar.dto.RegisterFormDTO;
import com.sarkar.dto.ResetPwdFormDTO;
import com.sarkar.dto.UserDTO;
import com.sarkar.service.DashboardService;
import com.sarkar.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private DashboardService dashboardService;

	@GetMapping("/register")
	public String loadRegistrationPage(Model model) {

		Map<Integer, String> countriesMap = userService.getCountries();

		model.addAttribute("countries", countriesMap);

		RegisterFormDTO registerFormDTO = new RegisterFormDTO();

		model.addAttribute("registerForm", registerFormDTO);

		return "register";

	}

	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer, String> getStates(@PathVariable Integer countryId, Model model) {

		Map<Integer, String> statesMap = userService.getStates(countryId);
		

		model.addAttribute("states", statesMap);
		

		return statesMap;

	}

	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer, String> getCities(@PathVariable Integer stateId, Model model) {

		Map<Integer, String> cityMap = userService.getCities(stateId);

		model.addAttribute("cities", cityMap);

		return cityMap;

	}

	@PostMapping("/register")
	public String handleRegistration(RegisterFormDTO registerFormDTO, Model model) {

		boolean status = userService.duplicateEmailCheck(registerFormDTO.getEmail());
		if (status) {

			model.addAttribute("emsg", "Duplicate Email Found");

		} else {
			boolean saveUser = userService.saveUser(registerFormDTO);
			if (saveUser) {
				model.addAttribute("smsg", "Registration Done Successfully, Please check your email....!");
			} else {
				model.addAttribute("emsg", "Something Error Occured ");
			}
		}
		model.addAttribute("registerForm",new RegisterFormDTO());
		model.addAttribute("countries", userService.getCountries());

		return "register";

	}

	@GetMapping("/")
	public String index(Model model) {
		LoginFormDTO loginFormDTO = new LoginFormDTO();
		model.addAttribute("loginForm", loginFormDTO);
		return "login";
	}

	@PostMapping("/login")
	public String handleUserLogin(Model model, LoginFormDTO loginDTo) {

		UserDTO login = userService.login(loginDTo);
		if (login == null) {
			model.addAttribute("emsg", "Invalid Credentials");
			model.addAttribute("loginForm", new LoginFormDTO());

		} else {
			String pwdUpdated = login.getPwdUpdated();

			if ("Yes".equals(pwdUpdated)) {

				return "redirect:dashboard";

			} else {
				return "redirect:/reset/pwd?email=" + login.getEmail();

			}
		}
		return "login";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		QuoteApiResponseDTO quoted = dashboardService.getQuote();
		model.addAttribute("quote", quoted);
		return "dashboard";
	}

	@GetMapping("/reset/pwd")
	public String loadResetPwd(@RequestParam("email") String email, Model model) {

		ResetPwdFormDTO resetPwd = new ResetPwdFormDTO();
		model.addAttribute("resetpwd",resetPwd);
		resetPwd.setEmail(email);
 
		return "resetPwd";

	}
    @PostMapping("/resetPwd")
	public String handlePwdReset(ResetPwdFormDTO resetPwd, Model model) {
		boolean resetPwd2 = userService.resetPwd(resetPwd);
		if (resetPwd2) {
			return "redirect:dashboard";

		}
		return "resetPwd";
	}

}
