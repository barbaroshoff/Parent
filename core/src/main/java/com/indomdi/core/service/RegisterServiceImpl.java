package com.indomdi.core.service;

import com.indomdi.core.config.AppParamsConfig;
import com.indomdi.core.converter.RegisterUserConverter;
import com.indomdi.core.dao.AuthoritiesDao;
import com.indomdi.core.dao.RegisterUserDao;
import com.indomdi.core.dao.UsersDao;
import com.indomdi.core.dto.RegisterResponseDto;
import com.indomdi.core.dto.RegisterUserDto;
import com.indomdi.core.exception.RegisterUserException;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.indomdi.core.persistent.Authorities;
import com.indomdi.core.persistent.RegisterUser;
import com.indomdi.core.persistent.Users;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class RegisterServiceImpl extends BaseServiceImpl implements RegisterService{
    @Autowired
    private SendEmailService emailService;
    @Autowired
    private RegisterUserDao signupUserDao;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private AuthoritiesDao authoritiesDao;

    @Autowired
    private RegisterUserConverter signupUserConverter;

    @Autowired
    private AppParamsConfig paramsConfig;

    @Value("classpath:templates/signup-email-body.template")
    private Resource signupBodyFile;

    private final Logger log = LoggerFactory.getLogger(getClass());


    @Override
    @Transactional
    public RegisterResponseDto signup(@NonNull RegisterUserDto userDto) throws RegisterUserException {
        RegisterUser user = signupUserConverter.toSignupUser(userDto);

        signupUserDao.save(user);

        emailService.sendSignupEmail(user, signupBody(user, baseUrl()));

        final RegisterResponseDto dto = new RegisterResponseDto();
        dto.setSuccess(true);
        dto.setMessage(message("A confirmation email was sent out to email {} for user {}. "
                + "Please check the email box and send back confirmation to finish the user registration!", userDto.getEmail(), userDto.getUsername()));
        return dto;
    }

    private String baseUrl() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();
        url = url.substring(0, url.lastIndexOf("/") + 1);
        return url;
    }

    private String signupBody(RegisterUser user, String baseUrl) throws RegisterUserException {
        try {
            final byte[] bytes = Files.readAllBytes(signupBodyFile.getFile().toPath());
            final String body = new String(bytes);

            return message(body, user.getUsername(), user.getEmail(), pendingPeriod(), returnAddress(user, baseUrl));
        } catch (final IOException e) {
            log.error(message("Failed to get signup body template: {}", e.getMessage()), e);
            throw new RegisterUserException(e);
        }
    }

    private String pendingPeriod() {
        final Long value = Math.round(paramsConfig.getSignupKeepPendingUser() / 1000d / 3600);
        return value.toString();
    }

    private String returnAddress(RegisterUser user, String baseUrl) {
        return baseUrl + "confirm/" + user.getSecureCode();
    }

    @Override
    @Transactional
    public RegisterResponseDto validateSecureCode(@NonNull String secureCode) {
        final Optional<RegisterUser> op = signupUserDao.findBySecureCode(secureCode);
        if (!op.isPresent()) {
            final RegisterResponseDto response = new RegisterResponseDto();
            response.setSuccess(false);
            response.setMessage("Unknown validation code provided...");

            return response;
        }

        final RegisterUser signupUser = op.get();

        log.info("Validate security code: {}", secureCode);
        if (signupUser.getCreated().plusSeconds(paramsConfig.getSignupKeepPendingUser() / 1000).isBefore(LocalDateTime.now())) {
            final RegisterResponseDto response = new RegisterResponseDto();
            response.setSuccess(false);
            response.setMessage("Invalid security code provided...");

            return response;
        }

        Users user = signupUserConverter.toUser(signupUser);
        user = usersDao.save(user);
        final Authorities authority = signupUserConverter.autorityForUser(user);
        authoritiesDao.save(authority);
        signupUserDao.delete(signupUser);

        final RegisterResponseDto response = new RegisterResponseDto();
        response.setSuccess(true);
        response.setMessage("Secure code validation successful...");
        return response;
    }

    @Override
    @Transactional
    public void deleteUser(String userName) {
        Users users = usersDao.findByUsername(userName).orElse(null);
        usersDao.delete(users);
    }
}
