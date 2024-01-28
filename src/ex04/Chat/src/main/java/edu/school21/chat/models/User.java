package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private long id;
    private String login;
    private String password;
    private List<Chat> chatsOwner = new ArrayList<>();

    public long getId() {
        return id;
    }
    public User() {}

    public User(long id, String login, String password, List<Chat> chatsOwner, List<Chat> activeChats) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.chatsOwner = chatsOwner;
        this.activeChats = activeChats;
    }

    public User(User user) {
        this.id = user.id;
        this.login = user.login;
        this.password = user.password;
        this.chatsOwner = user.chatsOwner;
        this.activeChats = user.activeChats;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chat> getChatsOwner() {
        return chatsOwner;
    }

    public void setChatsOwner(List<Chat> chatsOwner) {
        this.chatsOwner = chatsOwner;
    }

    public List<Chat> getActiveChats() {
        return activeChats;
    }

    public void setActiveChats(List<Chat> activeChats) {
        this.activeChats = activeChats;
    }

    private List<Chat> activeChats = new ArrayList<>();

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
