package com.sarkar.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.sarkar.dto.LoginFormDTO;
import com.sarkar.dto.RegisterFormDTO;
import com.sarkar.dto.ResetPwdFormDTO;
import com.sarkar.dto.UserDTO;

@Service
public interface UserService {
	
	public Map<Integer,String> getCountries();
	
	public Map<Integer,String> getStates(Integer countryId);
	
	public Map<Integer,String> getCities(Integer stateId);
	
	public boolean duplicateEmailCheck(String email);
	
	public boolean saveUser(RegisterFormDTO regFormDTO);
	
	public UserDTO login(LoginFormDTO loginFormDTO);

	public boolean resetPwd(ResetPwdFormDTO resetPwdFormDTO);
	
	
	
}
