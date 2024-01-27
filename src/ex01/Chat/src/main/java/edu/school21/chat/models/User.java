package edu.school21.chat.models;

import java.util.List;

public class User {
    private long id;
    private String login;
    private String password;
    private List<Chat> chatsOwner;
    private List<Chat> activeChats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (chatsOwner != null ? !chatsOwner.equals(user.chatsOwner) : user.chatsOwner != null) return false;
        return activeChats != null ? activeChats.equals(user.activeChats) : user.activeChats == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (chatsOwner != null ? chatsOwner.hashCode() : 0);
        result = 31 * result + (activeChats != null ? activeChats.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", chatsOwner=" + chatsOwner +
                ", activeChats=" + activeChats +
                '}';
    }
}
