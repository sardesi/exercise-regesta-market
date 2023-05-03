package com.regesta.exercise.regestamarket.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.regesta.exercise.regestamarket.constant.LangException;
import com.regesta.exercise.regestamarket.model.Result;
import com.regesta.exercise.regestamarket.model.dto.MarketUser;
import com.regesta.exercise.regestamarket.model.entity.User;
import com.regesta.exercise.regestamarket.security.JWTAuthenticationProvider;
import com.regesta.exercise.regestamarket.utils.CryptoUtils;
import com.regesta.exercise.regestamarket.utils.FileUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class LoginService {
	
	public static final String UNAUTHORIZED = "unauthorized";

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired(required=true)
	@Qualifier(value="provider")
	private JWTAuthenticationProvider authenticationProvider;

	@Autowired(required=true)
	private UserService userService;
	
	/**
	 * Given an user information checks if the given password is correct and returns a JWT token containing the user info in the claims section, so that only the token containing these
	 * information will be passed, preventing potential security issues.
	 * @param login The MarketUser to check. Mail and password need to be populated.
	 * @param rememberMe If true a token with a ten year lifespan will be generated. If false the lifespan will be of only 8 hours.
	 * @param response The http response of the operation.
	 * @return A mpa containing the JWT token built from the user data.
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> login(MarketUser login, boolean rememberMe, HttpServletResponse response) {
		
		Map<String, Object> result = new HashMap<>();
		
		Result autenticaLdap = autenticazione(login.getMail(), login.getPassword());
		if(autenticaLdap.isResult()) {
			
			User user = userService.getByMail(login.getMail());
			
			String hexToken = buildHexToken(user, login, rememberMe);
			result.put("token", hexToken);
			result.putAll((HashMap<String, Object>)(autenticaLdap.getData()));
			
			Integer jwtHash = hexToken.hashCode();
			response.addCookie(new Cookie("loginhash", String.valueOf(jwtHash)));
			
		} else {
			
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			result.put("token", UNAUTHORIZED);
			result.put("reason", autenticaLdap.getMessages());
			
			if(autenticaLdap.getData() != null) {
				result.putAll((HashMap<String, Object>)(autenticaLdap.getData()));
			}
			
		}
		
		return result;
		
	}
	
	/**
	 * Generates a token with the user information in the claims.
	 * @param user The user recovered from the database.
	 * @param login The MarketUesr in input.
	 * @param rememberMe If true a token with a ten year lifespan will be generated. If false the lifespan will be of only 8 hours.
	 * @return
	 */
	public String buildHexToken(User user, MarketUser login, boolean rememberMe) {
		
		if(user != null) {
			
			login.setId(user.getId());
			login.setMail(user.getMail());
			login.setName(user.getName());
			login.setSurname(user.getSurname());
			login.setLanguage(user.getLanguage());
			
		}
		
		login.setPassword("");
		
		return authenticationProvider.generateToken(login, rememberMe);
		
	}

	/**
	 * Checks if the credentials provided are correct by encrypting the password and checking with the one saved (encrypted) on the DB.
	 * @param mail The mail of the user to check.
	 * @param password The literal password inserted by the user.
	 * @return The result of the check.
	 * @throws SecurityException
	 */
	public Result autenticazione(String mail, String password) throws SecurityException {

		boolean esito = false;
		String message = null;
		
		User user = userService.getByMail(mail);
		if (user == null) {
			
			esito = false;
			message = LangException.WRONG_CREDENTIALS.toString();
			
		} else {
			
            String encryptedLoginPassword = encryptPassword(password, mail);
            if (user.getPassword().equals(encryptedLoginPassword)) {
                esito = true;
                message = "Credenziali applicative recuperate con successo";
            } else {
                esito = false;
                message = LangException.WRONG_CREDENTIALS.toString();
            }
            
		}

		Result result = new Result(esito, message);
		result.setData(new HashMap<String, Object>());

		return result;
		
	}

	/**
	 * Encrypt the password provided by converting the concatenation of the same password and the email into a bytearray and using an crypting algorithm via an utility.
	 * By entering the password itself in the encryption phase, the decrypted password will not be recoverable even by those who have access to the DB and know the encryption method.
	 * @param password The password to encrypt.
	 * @param mail The mail of the user.
	 * @return The encrypted password.
	 */
	public static String encryptPassword(String password, String mail) {

		String encryptedPassword = "";
		String md5 = FileUtils.md5((password + mail).getBytes());

		try {
			encryptedPassword = CryptoUtils.encryptToString( password, md5 );
		} catch(Exception e) {
			logger.error( "Errore in fase di cifratura", e );
		}

		return encryptedPassword;
		
	}

// Per generare password in caso di inserimenti spot.
	public static void main(String[] args) throws Exception {
		
		System.out.println(encryptPassword("password", "market.user@regestaitalia.it"));
		System.out.println("6A482F18B0CA03EE2CCFBF080E35C883");
		
	}

}
