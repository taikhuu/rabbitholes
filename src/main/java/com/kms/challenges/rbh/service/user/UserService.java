package com.kms.challenges.rbh.service.user;

import com.kms.challenges.rbh.domain.User;
import com.kms.challenges.rbh.model.RegistrationForm;

public interface UserService {

    User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
}
