package mcc.itculiacan.friendstree;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private List<Person> friendList = new ArrayList<>();
    private Person parentFriend;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Person parentFriend) {
        this(name);
        this.parentFriend = parentFriend;
        parentFriend.addDirectFriend(this);
    }

    public void addDirectFriend(Person person) {
        friendList.add(person);
    }

    public List<Person> getNthLevelFriends(int n) {

        List<Person> nLevelFriendList = new ArrayList<>();

        if (n > 1)
            for (Person friend : friendList)
                nLevelFriendList.addAll(friend.getNthLevelFriends(n - 1));
        else
            nLevelFriendList.addAll(friendList);

        return nLevelFriendList;
    }

    @Override
    public String toString() {
        return name;
    }
}
