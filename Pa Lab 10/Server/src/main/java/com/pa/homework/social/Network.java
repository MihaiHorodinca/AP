package com.pa.homework.social;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Singleton
public class Network {
    public static List<User> users = new ArrayList<>();

    public static void register(String name){
        User user = new User(name);

        users.add(user);
    }

    public static boolean isUser(String name){
        for (User user : users){
            if (Objects.equals(user.name, name))
                return true;
        }
        return false;
    }

    public static int getUserIndex(String name){
        int index = 0;
        for (User user : users){
            if (Objects.equals(user.name, name))
                return index;
            index ++;
        }
        return -1;
    }

    public static synchronized void addFriends(String name, String friendsString){
        String[] friends = friendsString.split(" ");
        int userIndex = Network.getUserIndex(name);

        if (userIndex == -1) {
            System.out.println("No such user");
            return;
        }

        for(String friend : friends){
            if (Network.isUser(friend)){
                users.get(userIndex).addFriend(friend);
            }
        }
    }

    public static synchronized void sendMessage(String name, String message){
        String finalMessage = name+ ": " + message;
        int userIndex = Network.getUserIndex(name);

        if (userIndex == -1) {
            System.out.println("No such sender");
            return;
        }

        List<String> friends = Network.users.get(userIndex).friends;

        for (String friend : friends){
            int friendIndex = Network.getUserIndex(friend);

            if (friendIndex == -1) {
                System.out.println("No such friend");
                return;
            }

            Network.users.get(friendIndex).inbox.add(finalMessage);
        }

    }

    public static synchronized List<String> getInbox(String name){
        int userIndex = Network.getUserIndex(name);
        List<String> returnInbox = new ArrayList<>(Network.users.get(userIndex).inbox);

        Network.users.get(userIndex).inbox = new ArrayList<String>();

        return returnInbox;

    }
}
