package cz.zcu.kiv.pia.web.servlet;



import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.kiv.pia.domain.User;
import cz.zcu.kiv.pia.domain.UserValidationException;
import cz.zcu.kiv.pia.manager.UserManager;


/**
 * Servlet handling user registration requests.
 *
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
public class Register extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String USERNAME_PARAMETER = "username";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String CONFIRM_PWD_PARAMETER = "password-check";
    private static final String SEX = "sex";
    private static final String MALE = "male";
    private static final String FEMALE = "female";
    private static final String MONTH = "birthday[month]";
    private static final String DAY = "birthday[day]";
    private static final String YEAR = "birthday[year]";
    private static final String CAPTCHA = "captcha";
    
    private static final String DEFAULT_AVATAR = "/img/avatars/default.png";
    
    private static final String USERNAME_FIELD = "usernameField";
    private static final String GENDER_MALE_FIELD = "genderMaleField";
    private static final String GENDER_FEMALE_FIELD = "genderFemaleField";
    private static final String AGREE_FIELD = "agreeField";
    private static final String CAPTCHA_FIELD = "captchaField";

    private static final String ERROR_ATTRIBUTE = "err";
    private static final String SUCCESS_ATTRIBUTE = "suc";

    private UserManager userManager;

    public Register(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	// register servlet handles the registration of a new user
    	
    	// get all params
    	
        String username = req.getParameter(USERNAME_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);
        String confirmPwd = req.getParameter(CONFIRM_PWD_PARAMETER);
        String gender = req.getParameter(SEX);
        String captcha = req.getParameter(CAPTCHA);
        
        int month = Integer.parseInt(req.getParameter(MONTH));
        int day = Integer.parseInt(req.getParameter(DAY));
        int year = Integer.parseInt(req.getParameter(YEAR));
        
        // check that the passwords match above all else
        if(!Objects.equals(password, confirmPwd)) {
            errorDispatch("V�mi zadan� hesla se neshoduj�!", req, resp);
            return;
        }

        // captcha equals or not?
            if (!captcha.equalsIgnoreCase(String.valueOf(4))) {
            	errorDispatch("Nespr�vn� vypln�n� kontroln�ho testu! Nejste n�hodou robot?", req, resp);
            	return;
            }
        
        
        // birthday starts as null
        Date birthday;
        Calendar cal = Calendar.getInstance();

        // the form sends zeros if it wasnt set - thus 3 zeroes means user did not set them
        if (month == 0 && day == 0 & year == 0) {
        	// user did not specify the date - let it be null
        	birthday = null;
        } else {
        	//otherwise user set at least some dates (preferrably all)
        	if (month == 0) {
        		errorDispatch("Nezadal jste m�s�c sv�ho narozen�!", req, resp);
        		return;
        	} else if (day == 0) {
        		errorDispatch("Nezadal jste den sv�ho narozen�!", req, resp);
        		return;
        	} else if (year == 0) {
        		errorDispatch("Nezadal jste rok sv�ho narozen�!", req, resp);
        		return;
        	} else {
        		// all ok
        		cal.set(year, month, day);
                birthday = cal.getTime();
        	}
        }

        // now try to register
        try {
            userManager.register(new User(username, password, birthday, gender, DEFAULT_AVATAR, new Date()));
            req.setAttribute(SUCCESS_ATTRIBUTE, "Registrace prob�hla �sp�n�! Nyn� se m��ete p�ihl�sit.");
            req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
        } catch (UserValidationException e) {
            errorDispatch(e.getMessage(), req, resp);
            return;
        }
    }

    private void errorDispatch(String err, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ERROR_ATTRIBUTE, err);
        req.setAttribute(USERNAME_FIELD, req.getParameter(USERNAME_PARAMETER));
        req.setAttribute(AGREE_FIELD, true);
        req.setAttribute(CAPTCHA_FIELD, req.getParameter(CAPTCHA));
        String gender = req.getParameter(SEX);
        if (gender != null) {
        	if (Objects.equals(gender, MALE)) {
        		req.setAttribute(GENDER_MALE_FIELD, true);
        	}
        	else if (Objects.equals(gender, FEMALE)){
        		req.setAttribute(GENDER_FEMALE_FIELD, true);
        	}
        }
        
        req.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(req, resp);
    }
}
