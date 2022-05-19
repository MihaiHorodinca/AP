package com.pa.homework.social;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String name;
    public List<String> friends;
    public List<String> inbox;

    User (String name){
        this.name = name;
        friends = new ArrayList<String>();
        inbox = new ArrayList<String>();
    }

    public void addFriend(String name){
        System.out.println(friends);
        friends.add(name);
        System.out.println(friends);
    }
}
