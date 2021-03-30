package com.indomdi.com.core.service;


import com.indomdi.com.core.dto.ForgottenPasswordRequestDto;
import com.indomdi.com.core.config.AppParamsConfig;
import com.indomdi.com.core.converter.ForgottenUserPasswordConverter;
import com.indomdi.com.core.dao.ForgottenUserPasswordDao;
import com.indomdi.com.core.dao.UsersDao;
import com.indomdi.com.core.dto.ResetPasswordDto;
import com.indomdi.com.core.dto.ResetResponseDto;
import com.indomdi.com.core.exception.SignupUserException;
import com.indomdi.com.core.persistent.ForgottenUserPassword;
import com.indomdi.com.core.persistent.Users;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@Slf4j
public class LoginService extends BaseServiceImpl{

    @Autowired
    ForgottenUserPasswordConverter forgottenUserPasswordConverter;

    @Autowired
    private ForgottenUserPasswordDao forgottenUserPasswordDao;

    @Autowired
    private UsersDao usersDao;
    @Autowired
    private SendEmailService emailService;
    @Autowired
    private AppParamsConfig paramsConfig;

    @Value("classpath:templates/forgotten-password-body.template")
    private Resource forgottenPasswordBodyFile;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void forgottenPassword(ForgottenPasswordRequestDto dto) {
        try {
            //check email points to a valid user
            ForgottenUserPassword record = forgottenUserPasswordConverter.toForgottenpasswordUser(dto);
            forgottenUserPasswordDao.save(record);

            //send email
            emailService.sendFortgottenPassword(record, emailBody(record));
        } catch (Exception e) {
            log.error("Failed to process forgotten password request: {}", e.getMessage(), e);
        }
    }

    private String emailBody(ForgottenUserPassword userRecord) throws SignupUserException {
        try {
            final byte[] bytes = Files.readAllBytes(forgottenPasswordBodyFile.getFile().toPath());
            final String body = new String(bytes);

            return message(body, userRecord.getUsername(), userRecord.getEmail(), paramsConfig.getForgottenRedirectWebPage() + "?token="+userRecord.getSecureCode());
        } catch (final IOException e) {
            log.error(message("Failed to get signup body template: {}", e.getMessage()), e);
            throw new SignupUserException(e);
        }
    }

    @Transactional
    public ResetResponseDto resetPassword(@NonNull @Valid ResetPasswordDto dto) {
        ResetResponseDto response = new ResetResponseDto();

        Optional<ForgottenUserPassword> op = forgottenUserPasswordDao.findBySecureCode(dto.getSecureCode());
        if (!op.isPresent()) {
            response.setSuccess(false);
            response.setMessage("User not found");
            return response;
        }

        ForgottenUserPassword u = op.get();
        Optional<Users> op2 = usersDao.findByUsername(u.getUsername());
        if (!op2.isPresent()) {
            response.setSuccess(false);
            response.setMessage("User not found");
            return response;
        }

        Users user = op2.get();
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        usersDao.save(user);

        response.setSuccess(true);
        response.setMessage("Password successfully reset");

        return response;
    }
}
