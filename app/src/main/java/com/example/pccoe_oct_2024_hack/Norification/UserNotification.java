package com.example.pccoe_oct_2024_hack.Norification;

import android.content.Context;

public class UserNotification extends BaseNotification {

    private String userName;

    public UserNotification(Context context, String channelId, String userName) {
        super(context, channelId);
        this.userName = userName;
    }

    @Override
    protected NotificationContent getNotificationContent() {
        String title = "Welcome!";
        String message = "Hello, You have an new Notification.";
        return new NotificationContent(title, message);
    }
}
