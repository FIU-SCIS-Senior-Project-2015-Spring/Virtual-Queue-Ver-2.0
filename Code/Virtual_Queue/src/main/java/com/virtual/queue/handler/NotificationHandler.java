package com.virtual.queue.handler;

import com.virtual.queue.beans.NotificationInfo;
import com.virtual.queue.exception.NotificationException;

public interface NotificationHandler {
public void notifiyUser(NotificationInfo info) throws NotificationException;
}
