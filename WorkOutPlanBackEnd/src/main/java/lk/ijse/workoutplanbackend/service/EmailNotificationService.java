package lk.ijse.workoutplanbackend.service;

public interface EmailNotificationService {

    void sendEmail(String to, String subject, String message);
}
