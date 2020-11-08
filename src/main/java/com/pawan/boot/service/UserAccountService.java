package com.pawan.boot.service;

import java.util.Map;

import com.pawan.boot.domain.UserAccount;

public interface UserAccountService {

	public String loginCheck(String email, String pwd);

	public Map<Integer, String> loadCountries();

	public Map<Integer, String> loadStatesByCountryId(Integer countryId);

	public Map<Integer, String> loadCitiesByStateId(Integer stateId);

	public boolean isUniqueEmail(String email);

	public String generateTempPwd();

	public boolean saveUserAccount(UserAccount account);

	public String getRegSuccMailBody(UserAccount account);

	public boolean sendRegSuccMsg(String email, String body, String subject);

	public boolean unlockAccount(String email, String pwd);

	public boolean isTempPwdVaild(String email, String password);

	public String recoverPwd(String email);

	public String getRecoverPwdEmailBody(UserAccount account);

	public String sendPwdToEmail(String email,String subject,String body);

}
