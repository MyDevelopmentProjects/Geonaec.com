package ge.mgl.controller;

import ge.mgl.pojo.ContactMailPOJO;
import ge.mgl.utils.GeneralUtil;
import ge.mgl.utils.RequestResponse;
import ge.mgl.utils.VerifyCaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import static ge.mgl.utils.constants.Constants.ControllerCodes.SLASH;

/**
 * Created by MJaniko on 3/12/2017.
 */
@Controller
@RequestMapping("/contact")
public class GContactController {

    private static final Logger log = LoggerFactory.getLogger(GContactController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.receiver}")
    private String sendMailTo;

    @RequestMapping(value = SLASH + "sendMail", method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse sendMail(@RequestBody ContactMailPOJO contact){
        boolean verify = VerifyCaptcha.verify(contact.getCaptcha());
        if(!verify){
            return  GeneralUtil.RequestError(messageSource.getMessage("auth.wrong.captcha", null, LocaleContextHolder.getLocale()));
        }
        StringBuilder msg = new StringBuilder();
        msg.append("სრული სახელი:").append(contact.getFullName()).append("<br>");
        msg.append("ელ.ფოსტა:").append(contact.getEmail()).append("<br>");
        msg.append("სათაური:").append(contact.getSubject()).append("<br>");
        msg.append("შეტყობინება:").append(contact.getMessage()).append("<br>");
        try {
            mailSender.send(mimeMessage -> {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("no-reply@naec.ge");
                message.setTo(sendMailTo); // send to user email
                message.setSubject("CONTACT-"+contact.getSubject());
                message.setText(msg.toString(), true);
            });

        } catch (Exception e){
            log.info("EROOR:", e);
        }
        String succesMSG = messageSource.getMessage("auth.contact.success", null, LocaleContextHolder.getLocale());
        return GeneralUtil.RequestSuccess(succesMSG);
    }

}
