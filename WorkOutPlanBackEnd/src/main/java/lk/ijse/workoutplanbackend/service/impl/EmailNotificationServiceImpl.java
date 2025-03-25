package lk.ijse.workoutplanbackend.service.impl;

import lk.ijse.workoutplanbackend.entity.User;
import lk.ijse.workoutplanbackend.repo.UserRepository;
import lk.ijse.workoutplanbackend.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    // Scheduled to run every 2 months on the 1st day at 10 AM
    @Scheduled(cron = "0 0 10 1 */2 *")
    public void sendScheduledEmailToAllUsers() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            sendEmail(user.getEmail(), "Reminder: Check Your Weight and BMI",
                    "Hello,\\n\\nThis is a friendly reminder to check your weight and BMI this month.\\nStay healthy!\\n\\nBest regards,\\nTraining Studio Team");
        }

        System.out.println("Emails sent successfully to all users!");
    }
}

