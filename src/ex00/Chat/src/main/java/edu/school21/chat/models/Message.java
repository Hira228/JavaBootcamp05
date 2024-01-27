package edu.school21.chat.models;

import java.sql.Date;

public class Message {
    private long id;
    private long authorMessage;
    private long idRoom;
    private String text;
    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (authorMessage != message.authorMessage) return false;
        if (idRoom != message.idRoom) return false;
        if (text != null ? !text.equals(message.text) : message.text != null) return false;
        return date != null ? date.equals(message.date) : message.date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (authorMessage ^ (authorMessage >>> 32));
        result = 31 * result + (int) (idRoom ^ (idRoom >>> 32));
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", authorMessage=" + authorMessage +
                ", idRoom=" + idRoom +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
