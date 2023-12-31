package com.project225160694002.Ecomcombo.services;

import com.project225160694002.Ecomcombo.apis.model.LoginBody;
import com.project225160694002.Ecomcombo.apis.model.RegistrationBody;
import com.project225160694002.Ecomcombo.exceptions.UserAlreadyExistsException;
import com.project225160694002.Ecomcombo.model.LocalUser;
import com.project225160694002.Ecomcombo.model.dao.LocalUserDAO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private LocalUserDAO localUserDAO;
    private EncryptionService encryptionService;
    private JWTService jwtService;
    public UserService(LocalUserDAO localUserDAO, EncryptionService encryptionService, JWTService jwtService){
        this.localUserDAO=localUserDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }
    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {

        if(localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() &&
                localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        }

        LocalUser user=new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        user.setUsername(registrationBody.getUsername());

        return localUserDAO.save(user);

    }
    public String loginUser(LoginBody loginBody){
        Optional<LocalUser> opUser=localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if(opUser.isPresent()){
            LocalUser user=opUser.get();
            if(encryptionService.verifyPassword(loginBody.getPassword(),user.getPassword())){
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
