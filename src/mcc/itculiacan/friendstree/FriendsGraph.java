package mcc.itculiacan.friendstree;

import java.util.HashMap;

public class FriendsGraph {

    private static final String singleRelationRegex = "^[\\w]+-[\\w]+$";
    private static final String multipleRelationRegex = "^[\\w]+-([\\w]+,)*[\\w]+$";

    private static final String personLevelOneRegex = "^[\\w]+$";
    private static final String personNthLevelRegex = "^[\\w]+\\s[0-9]+$";

    private HashMap<String, Person> personHashMap = new HashMap<>();

    public void processCommand(String command) {

        if (command.matches(singleRelationRegex)) {

            String[] splitCommand = command.split("-");

            String personName = splitCommand[0];
            String friendName = splitCommand[1];

            Person person = getOrCreatePerson(personName);
            Person friend = getOrCreatePerson(friendName);

            person.addFriend(friend);

            System.out.println(person);

        } else if(command.matches(multipleRelationRegex)) {

            String[] splitCommand = command.split("-");

            String personName = splitCommand[0];
            String friendNames = splitCommand[1];

            String[] splitFriendNames = friendNames.split(",");

            Person person = getOrCreatePerson(personName);

            for (String friendName : splitFriendNames) {
                Person friend = getOrCreatePerson(friendName);
                person.addFriend(friend);
            }

            System.out.println(person);
        }

    }

    private void addFriend(Person person, Person friend) {
        person.addFriend(friend);
    }

    private void addFriends(Person person, Person... friends) {

        for (Person friend : friends) {
            person.addFriend(friend);
        }

    }

    private void getNthLevelFriends(Person person, int n) {

        person.getNthLevelFriends(n);
    }

    private Person getOrCreatePerson(String personName) {

        Person person;

        if (personHashMap.containsKey(personName)) {
            person = personHashMap.get(personName);
        } else {
            person = new Person(personName);
            personHashMap.put(personName, person);
        }

        return person;
    }

}
