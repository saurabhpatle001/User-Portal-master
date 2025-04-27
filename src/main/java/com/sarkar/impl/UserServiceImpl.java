package com.sarkar.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarkar.dto.LoginFormDTO;
import com.sarkar.dto.RegisterFormDTO;
import com.sarkar.dto.ResetPwdFormDTO;
import com.sarkar.dto.UserDTO;
import com.sarkar.entity.CityEntity;
import com.sarkar.entity.CountryEntity;
import com.sarkar.entity.StateEntity;
import com.sarkar.entity.UserEntity;
import com.sarkar.repo.CityRepo;
import com.sarkar.repo.CountryRepo;
import com.sarkar.repo.StateRepo;
import com.sarkar.repo.UserRepo;
import com.sarkar.service.EmailService;
import com.sarkar.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private EmailService emailService;

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryEntity> countriesList = countryRepo.findAll();
		Map<Integer, String> countryMap = new HashMap<>();

		countriesList.forEach(c -> {
			countryMap.put(c.getCountryId(), c.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StateEntity> stateList = stateRepo.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<>();

		stateList.forEach(s -> {
			stateMap.put(s.getStateId(), s.getStateName());
		});

		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {

		List<CityEntity> cityList = cityRepo.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		cityList.forEach(c -> {
			cityMap.put(c.getCityId(), c.getCityName());
		});

		return cityMap;
	}

	@Override
	public boolean duplicateEmailCheck(String email) {
		UserEntity byEmail = userRepo.findByEmail(email);

		if (byEmail != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean saveUser(RegisterFormDTO regFormDTO) {
		UserEntity userEntity = new UserEntity();

		BeanUtils.copyProperties(regFormDTO, userEntity);

		CountryEntity country = countryRepo.findById(regFormDTO.getCountryId()).orElseThrow(null);
		userEntity.setCountry(country);

		StateEntity state = stateRepo.findById(regFormDTO.getStateId()).orElseThrow(null);
		userEntity.setState(state);

		CityEntity city = cityRepo.findById(regFormDTO.getCityId()).orElseThrow(null);
		userEntity.setCity(city);

		String randomPwd = generateRandomPwd();

		userEntity.setPwd(randomPwd);
		userEntity.setPwdUpdated("No");

		UserEntity savedUser = userRepo.save(userEntity);

		if (null != savedUser.getUserId()) {
			String subject = "Your Account Created";
			String body = "Your Password To Login : " + randomPwd ;
			String to = regFormDTO.getEmail();
			emailService.sendEmail(subject, body, to);
		}

		return false;
	}

	@Override
	public UserDTO login(LoginFormDTO loginFormDTO) {
		UserEntity userEntity = userRepo.findByEmailAndPwd(loginFormDTO.getEmail(), loginFormDTO.getPwd());
		if (userEntity != null) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(userEntity, userDTO);
			return userDTO;
		}

		return null;
	}

	@Override
	public boolean resetPwd(ResetPwdFormDTO resetPwdDTO) {
		String email = resetPwdDTO.getEmail();
		UserEntity entity = userRepo.findByEmail(email);

		entity.setPwd(resetPwdDTO.getNewPwd());
		entity.setPwdUpdated("Yes");

		userRepo.save(entity);

		return true;
	}

	private String generateRandomPwd() {
		String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String number = "6372644$#%@#456*&%#$3435";

		String alphabets = upperCaseLetters + lowerCaseLetters + number;

		Random random = new Random();

		StringBuffer generatedPwd = new StringBuffer();

		for (int i = 0; i < 7; i++) {

			int randomIndex = random.nextInt(alphabets.length());
			generatedPwd.append(alphabets.charAt(randomIndex));

		}
		return generatedPwd.toString();

	}

}
