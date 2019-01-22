package ge.mgl.controller;

import ge.mgl.entities.GUser;
import ge.mgl.entities.GUserPasswordReset;
import ge.mgl.pojo.*;
import ge.mgl.security.SpringSecurityUser;
import ge.mgl.security.UserUtils;
import ge.mgl.service.GUserPasswordResetService;
import ge.mgl.service.GUserService;
import ge.mgl.utils.*;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static ge.mgl.utils.constants.Constants.ControllerCodes.*;

@RestController
@RequestMapping("/user")
public class GUserController {

    @Autowired
    private GUserService userService;

    @Autowired
    private GUserPasswordResetService userPasswordResetService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/req/psw/reset", method = RequestMethod.POST)
    public RequestResponse requestReset(@Valid @RequestBody UserRequestResetPOJO reset){
        boolean verify = VerifyCaptcha.verify(reset.getCaptcha());
        if(!verify){
            return  GeneralUtil.RequestError(messageSource.getMessage("auth.wrong.captcha", null, LocaleContextHolder.getLocale()));
        }
        GUser user = userService.loadUserByUsername(reset.getUsername());
        if(user == null){
            return  GeneralUtil.RequestError(messageSource.getMessage("auth.no.such.user", null, LocaleContextHolder.getLocale()));
        } else {
            String generatedToken = GeneralUtil.generateString(6);

            // Save psw for user
            GUserPasswordReset pswReset = new GUserPasswordReset();
            pswReset.setUser(user);
            pswReset.setHashCode(generatedToken);
            pswReset.setFinished(false);
            userPasswordResetService.create(pswReset);

            if(user.getPhone() != null){
                //SmsAPI.sendSMS(user.getPhone(), SMSMessages.VERIFICATION_SMS.replace("{0}", generatedToken));
            }

            StringBuilder msg = new StringBuilder();
            msg.append(SMSMessages.VERIFICATION_SMS.replace("{0}", generatedToken));
            try {
                mailSender.send(mimeMessage -> {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    message.setFrom("no-reply@goldex.ge");
                    message.setTo(user.getUsername()); // send to user email
                    message.setSubject("GOLDEX - Password Reset Request");
                    message.setText(msg.toString(), true);
                });

            } catch (Exception e){
            }
        }
        return GeneralUtil.RequestSuccess(messageSource.getMessage("reset.request.sent.mail", null, LocaleContextHolder.getLocale()));
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public RequestResponse resetPassword(@Valid @RequestBody UserPswResetPOJO pojo){
        boolean verify = VerifyCaptcha.verify(pojo.getCaptcha());
        if(!verify){
            return  GeneralUtil.RequestError(messageSource.getMessage("auth.wrong.captcha", null, LocaleContextHolder.getLocale()));
        }

        GUserPasswordReset passwordReset = userPasswordResetService.findByHashCode(pojo.getCode());
        if(passwordReset != null){
            passwordReset.setFinished(true);
            userPasswordResetService.update(passwordReset);

            String generatedToken = GeneralUtil.generateString(8);
            if(passwordReset.getUser() != null){
                GUser user = userService.findById(passwordReset.getUser().getId());
                if(user != null){
                    BCryptPasswordEncoder pswdEncoder = new BCryptPasswordEncoder(11);
                    String newPassword = pswdEncoder.encode(generatedToken);
                    user.setPassword(newPassword);
                    userService.save(user);
                }
            }

            if(passwordReset.getUser().getPhone() != null){
                //SmsAPI.sendSMS(passwordReset.getUser().getPhone(), SMSMessages.NEW_PASSWORD_FOR_USER.replace("{0}", generatedToken));
            }

            StringBuilder msg = new StringBuilder();
            msg.append(SMSMessages.NEW_PASSWORD_FOR_USER.replace("{0}", generatedToken));
            try {
                mailSender.send(mimeMessage -> {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    message.setFrom("no-reply@goldex.ge");
                    message.setTo(passwordReset.getUser().getUsername()); // send to user email
                    message.setSubject("GOLDEX - New Password");
                    message.setText(msg.toString(), true);
                });

            } catch (Exception e){
            }
        } else {
            return  GeneralUtil.RequestError(messageSource.getMessage("reset.no.such.code", null, LocaleContextHolder.getLocale()));
        }
        return GeneralUtil.RequestSuccess();
    }


    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST)
    public RequestResponse updateUserPassword(@Valid @RequestBody UserPswUpdatePOJO userPswUpdate) {
        SpringSecurityUser user = UserUtils.currentUser();
        if(user == null){
            return GeneralUtil.RequestError("Session Timed Out");
        }

        GUser result = userService.findById(user.getId());
        if(result == null){
            return GeneralUtil.RequestError("Session Timed Out");
        }

        BCryptPasswordEncoder cEncoder = new BCryptPasswordEncoder(11);
        if(!cEncoder.encode(userPswUpdate.getCurrentPassword()).equals(result.getPassword())){
            return  GeneralUtil.RequestError(messageSource.getMessage("auth.current.password", null, LocaleContextHolder.getLocale()));
        }

        if(!userPswUpdate.getNewPassword().equals(userPswUpdate.getRepeatPassword())){
            return  GeneralUtil.RequestError(messageSource.getMessage("auth.password.dont.match", null, LocaleContextHolder.getLocale()));
        }

        BCryptPasswordEncoder pswdEncoder = new BCryptPasswordEncoder(11);
        String newPassword = pswdEncoder.encode(userPswUpdate.getNewPassword());
        result.setPassword(newPassword);
        userService.save(result);
        return GeneralUtil.RequestSuccess();
    }


    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/updateUserProfile", method = RequestMethod.POST)
    public RequestResponse updateUserProfile(@Valid @RequestBody UserProfileUpdatePOJO userProfile){
        SpringSecurityUser user = UserUtils.currentUser();
        if(user == null){
            return GeneralUtil.RequestError("Session Timed Out");
        }

        GUser currentUser = userService.findById(user.getId());
        if(currentUser == null){
            return GeneralUtil.RequestError("Session Timed Out");
        }

        currentUser.setPhone(userProfile.getPhone());
        currentUser.setFirstNameEN(userProfile.getFirstNameEN());
        currentUser.setLastNameEN(userProfile.getLastNameEN());
        currentUser.setFirstNameGE(userProfile.getFirstNameGE());
        currentUser.setLastNameGE(userProfile.getLastNameGE());
        currentUser.setAddress1(userProfile.getAddress1());
        currentUser.setAddress2(userProfile.getAddress2());
        userService.save(currentUser);

        return GeneralUtil.RequestSuccess();
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + LIST, method = RequestMethod.GET)
    public PaginationAndFullSearchQueryResult getList(
            @RequestParam(required = false, defaultValue = STRING_EMPTY) String searchExpression,
            @RequestParam(required = false, defaultValue = STRING_EMPTY) String sortField,
            @RequestParam(required = false, defaultValue = IS_ASCENDING_DEFAULT_VALUE) boolean isAscending,
            @RequestParam(value = PAGE_NUMBER, required = false, defaultValue = PAGE_NUMBER_DEFAULT_VALUE) Integer pageNumber,
            @RequestParam(required = false, defaultValue = PAGE_SIZE_DEFAULT_VALUE) int pageSize) {
        return userService.getList(searchExpression, sortField, isAscending, pageNumber, pageSize);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + PUT, method = RequestMethod.POST)
    public RequestResponse save(@RequestBody GUser user) {
        userService.save(user);
        return GeneralUtil.RequestSuccess();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + DELETE, method = RequestMethod.POST)
    public RequestResponse delete(@RequestBody Long id) {
        boolean deleted = userService.delete(id);
        if (deleted) {
            return GeneralUtil.RequestSuccess();
        }
        return GeneralUtil.RequestError();
    }
}
