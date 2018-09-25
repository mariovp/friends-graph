package mcc.itculiacan.friendstree;

import java.util.*;

public class Person implements Iterable {

    private String name;
    private Set<Person> friendList = new HashSet<>();

    public Person(String name) {
        this.name = name;
    }

    public void addFriend(Person person) {
        friendList.add(person);
        person.addReciprocalFriend(this);
    }

    private void addReciprocalFriend(Person person) {
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
        return name+"\n"+friendList.size();
    }

    @Override
    public Iterator<Person> iterator() {
        return friendList.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
