package com.pawan.boot.service;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.boot.domain.UserAccount;
import com.pawan.boot.entity.CityMasterEntity;
import com.pawan.boot.entity.CounttryMasterEntity;
import com.pawan.boot.entity.StateMasterEntity;
import com.pawan.boot.entity.UserAccountEntity;
import com.pawan.boot.repository.CityMasterRepository;
import com.pawan.boot.repository.CountryMasterRepository;
import com.pawan.boot.repository.StateMasterRepository;
import com.pawan.boot.repository.UserAccountRepository;
import com.pawan.boot.utils.EmailUtil;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepo;
	@Autowired
	private CountryMasterRepository countryRepo;
	@Autowired
	private StateMasterRepository stateRepo;

	@Autowired
	private CityMasterRepository cityRepo;
	@Autowired
	private EmailUtil utils;

	@Override
	public String loginCheck(String email, String pwd) {
		UserAccountEntity entity = userAccountRepo.findByEmailAndPwd(email, pwd);
		
		
		if(entity==null) {
			
			return "invalid credentials";
		}
		else if(entity.getStatus().equals("LOCKED")) {
			
			return "your account is locked";
		}
		else {
			return "valid";
		}
		
	}

	@Override
	public Map<Integer, String> loadCountries() {
		List<CounttryMasterEntity> entitiesList = countryRepo.findAll();
		Map<Integer, String> countryMap = new HashMap<>();
		entitiesList.forEach(entities -> {
			countryMap.put(entities.getCountryId(), entities.getCountryName());

		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStatesByCountryId(Integer countryId) {

		List<StateMasterEntity> entitiesList = stateRepo.findByCountryId(countryId);

		Map<Integer, String> stateMap = new HashMap<Integer, String>();

		entitiesList.forEach(entities -> {
			stateMap.put(entities.getStateId(), entities.getStateName());

		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCitiesByStateId(Integer stateId) {
		List<CityMasterEntity> entitiesList = cityRepo.findByStateId(stateId);

		Map<Integer, String> cityMap = new HashMap<>();
		entitiesList.forEach(entities -> {
			cityMap.put(entities.getCityId(), entities.getCityName());

		});
		return cityMap;
	}

	@Override
	public boolean isUniqueEmail(String email) {
		// TODO Auto-generated method stub
		UserAccountEntity userAccountEntity = userAccountRepo.findByEmail(email);
		return userAccountEntity != null ? false : true;
	}

	@Override
	public String generateTempPwd() {
		// TODO Auto-generated method stub

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(6);

		for (int i = 0; i < 6; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();

	}

	@Override
	public boolean saveUserAccount(UserAccount account) {

		account.setStatus("LOCKED");
		account.setPwd(generateTempPwd());
		UserAccountEntity entity = new UserAccountEntity();
		BeanUtils.copyProperties(account, entity);
		UserAccountEntity savedEntity = userAccountRepo.save(entity);
		if (savedEntity.getUserId() != null) {

			String to = account.getEmail();
			String subject = "Registration Successfull";
			String body = getRegSuccMailBody(account);

			sendRegSuccMsg(to, subject, body);
			return true;
		}
		return false;
	}

	@Override
	public String getRegSuccMailBody(UserAccount account) {
		String fileName = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";

		String mailBody = "";
		Path path = null;
		try {
			path = Paths.get(fileName, "");
			Stream<String> lines = Files.lines(path);
			List<String> listLines = lines.map(
					line -> line.replace("{FNAME}", account.getFirstName()).replace("{LNAME}", account.getLastName())
							.replace("{TEMP-PWD}", account.getPwd()).replace("{EMAIL}", account.getEmail()))
					.collect(Collectors.toList());
			mailBody = String.join("", listLines);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mailBody;
	}

	@Override
	public boolean sendRegSuccMsg(String to, String subject, String body) {

		return utils.seendMail(to, subject, body);
	}

	@Override
	public boolean isTempPwdVaild(String email, String password) {
		UserAccountEntity entity = userAccountRepo.findByEmailAndPwd(email, password);
		return entity != null ? true : false;
	}

	@Override
	public boolean unlockAccount(String email, String pwd) {
		UserAccountEntity entity = userAccountRepo.findByEmail(email);
		entity.setStatus("UNLOCKED");
		entity.setPwd(pwd);
		UserAccountEntity isSavedEntity = userAccountRepo.save(entity);
		return isSavedEntity.getUserId() != null ? true : false;
	}

	@Override
	public String recoverPwd(String email) {
		UserAccountEntity entity=userAccountRepo.findByEmail(email);
		if(entity!=null) {
			UserAccount acc=new UserAccount();
			BeanUtils.copyProperties(entity, acc);
			String body=getRecoverPwdEmailBody(acc);
			String to=acc.getEmail();
			String subject="Recover Password";
			sendPwdToEmail(email, subject, body);
			return "password sent to email";
		}
		else {
			
			return "please enter vaild email id";
		}
		
	}

	@Override
	public String getRecoverPwdEmailBody(UserAccount account) {

		String fileName = "PASSWORD-RECOVER-EMAIL-BODY-TEMPLATE2.txt";

		String mailBody = "";
		Path path = null;
		try {
			path = Paths.get(fileName, "");
			Stream<String> lines = Files.lines(path);
			List<String> listLines = lines.map(
					line -> line
					 .replace("{FNAME}", account.getFirstName())
					 .replace("{LNAME}", account.getLastName())
					 .replace("{PWD}", account.getPwd()))
					 .collect(Collectors.toList());
			mailBody = String.join("", listLines);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mailBody;
		
		
	}

	@Override
	public String sendPwdToEmail(String email, String subject, String body) {
		boolean isSent=utils.seendMail(email, subject, body);
		if(isSent) {
			
			return "mail-sent successfully";
		}
		return "failed to sent mail";
	}

}
