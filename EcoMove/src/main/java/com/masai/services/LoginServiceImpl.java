package com.masai.services;

import com.masai.DTO.AdminLoginDTO;
import com.masai.DTO.UserLoginDTO;
import com.masai.entities.Admin;
import com.masai.entities.CurrentAdminSession;
import com.masai.entities.CurrentUserSession;
import com.masai.entities.User;
import com.masai.exceptions.LoginException;
import com.masai.respository.AdminRepo;
import com.masai.respository.AdminSessionDao;
import com.masai.respository.UserDao;
import com.masai.respository.UserSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userRepo;

    @Autowired
    private UserSessionDao userSessionDao;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private AdminSessionDao adminSessionDao;


    @Override
    public CurrentAdminSession logIntoAdminAccount(AdminLoginDTO dto) throws LoginException {
        Admin existingAdmin = adminRepo.findByMobileNumber(dto.getMobileNumber());

        if (existingAdmin == null) throw new LoginException("This is not valid mobile number...!");


        Optional<CurrentAdminSession> validAdminSessionOpt = adminSessionDao.findById(existingAdmin.getAdminId());


        if (validAdminSessionOpt.isPresent()) {

            throw new LoginException("Admin already Log-In with this number..!" + validAdminSessionOpt);
        }

        if (existingAdmin.getAdminPassword().equals(dto.getAdminPassword())) {

            UUID uuid = UUID.randomUUID();

            String key = uuid.toString().replaceAll("-", "").substring(0,6);

            CurrentAdminSession currentAdminSession = new CurrentAdminSession(existingAdmin.getAdminId(), key, LocalDateTime.now());

            adminSessionDao.save(currentAdminSession);

            return currentAdminSession;
        } else
            throw new LoginException("Please Enter a valid password!");
    }

    @Override
    public String logOutFromAdminAccount(String key) throws LoginException {
        CurrentAdminSession validAdminSession = adminSessionDao.findByUuid(key);

        if (validAdminSession == null) {
            throw new LoginException("Admin Not Logged In with this number" + validAdminSession);

        }

        adminSessionDao.delete(validAdminSession);

        return "Admin Logged Out Succesfully...!";
    }

    @Override
    public CurrentUserSession logIntoUserAccount(UserLoginDTO dto) throws LoginException {
        User existingUser = userRepo.findByMobileNumber(dto.getMobileNumber());

        if (existingUser == null) throw new LoginException("This is not valid mobile number...!");


        Optional<CurrentUserSession> validUserSessionOpt = userSessionDao.findById(existingUser.getUserId());


        if (validUserSessionOpt.isPresent()) {

            throw new LoginException("user already Log-In with this number..!" + validUserSessionOpt);
        }

        if (existingUser.getPassword().equals(dto.getPassword())) {
            UUID uuid = UUID.randomUUID();

            String key = uuid.toString().replaceAll("-", "").substring(0,6);

            CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getUserId(), key, LocalDateTime.now());

            userSessionDao.save(currentUserSession);

            return currentUserSession;
        } else
            throw new LoginException("Please Enter a valid password...!");
    }

    @Override
    public String logOutFromUserAccount(String key) throws LoginException {
        CurrentUserSession validUserSession = userSessionDao.findByUuid(key);

        if (validUserSession == null) {
            throw new LoginException("user Not Logged In with this number" + validUserSession);

        }

        userSessionDao.delete(validUserSession);

        return "User Logged Out !";
    }
}
