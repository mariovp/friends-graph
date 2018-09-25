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

        // <Persona, n máximo en que se encontró>
        HashMap<Person, Integer> hashMap = new HashMap<>();

        HashMap<Person, Integer> visitedFriends = getNthLevelFriends(n, hashMap);

        List<Person> friendsOfLevelN = new ArrayList<>();

        visitedFriends.forEach((person, integer) -> {
            if (integer == 1)
                friendsOfLevelN.add(person);
        });

        return friendsOfLevelN;
    }

    private HashMap<Person, Integer> getNthLevelFriends(int n, HashMap<Person, Integer> visitedFriends) {

        if (n > 1) {
            for (Person friend : friendList) {
                updateHashMap(visitedFriends, friend, n);
                friend.getNthLevelFriends(n - 1, visitedFriends);
            }

        } else {
            for (Person friend : friendList) {
                updateHashMap(visitedFriends, friend, n);
            }
        }

        return visitedFriends;
    }

    private void updateHashMap(HashMap<Person, Integer> visitedFriends, Person friend, int n) {

        if (visitedFriends.containsKey(friend)) {
            int countInMap = visitedFriends.get(friend);
            int updatedCount = Math.max(countInMap, n);

            visitedFriends.put(friend, updatedCount);
        } else {

            visitedFriends.put(friend, n);
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
