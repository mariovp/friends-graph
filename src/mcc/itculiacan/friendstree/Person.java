package mcc.itculiacan.friendstree;

import java.util.*;

public class Person implements Iterable {

    private String name;
    private Set<Person> friendList = new HashSet<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addFriend(Person person) {
        friendList.add(person);
        person.addReciprocalFriend(this);
    }

    private void addReciprocalFriend(Person person) {
        friendList.add(person);
    }

    public List<Person> getNthLevelFriends(int n) {

        HashMap<Person, Integer> nLevelFriendList = new HashMap<>();

        HashMap<Person, Integer> frecuencyMap = getNthLevelFriends(n, nLevelFriendList);

        List<Person> friendsOfLevelN = new ArrayList<>();

        frecuencyMap.forEach((person, integer) -> {
            if (integer == 1)
                friendsOfLevelN.add(person);
        });

        return friendsOfLevelN;
    }

    public HashMap<Person, Integer> getNthLevelFriends(int n, HashMap<Person, Integer> nLevelFriendList) {

        if (n > 1) {
            for (Person friend : friendList) {
                updateHashMap(nLevelFriendList, friend, n);
                friend.getNthLevelFriends(n - 1, nLevelFriendList);
            }

        } else {
            for (Person friend : friendList) {
                updateHashMap(nLevelFriendList, friend, n);
            }
        }

        return nLevelFriendList;
    }

    private void updateHashMap(HashMap<Person, Integer> nLevelFriendList, Person friend, int n) {

        if (nLevelFriendList.containsKey(friend)) {
            int countInMap = nLevelFriendList.get(friend);
            int updatedCount = Math.max(countInMap, n);

            nLevelFriendList.put(friend, updatedCount);
        } else {

            nLevelFriendList.put(friend, n);
        }

    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(name).append("\t[");

        for (Person person : friendList) {
            stringBuilder.append(person.name).append(", ");
        }
        stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());

        stringBuilder.append("]");

        return stringBuilder.toString();
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
