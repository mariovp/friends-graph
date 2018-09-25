package mcc.itculiacan.friendstree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FriendsGraph {

    private static final String singleRelationRegex = "^[\\w]+-[\\w]+$";
    private static final String multipleRelationRegex = "^[\\w]+-([\\w]+,)*[\\w]+$";

    private static final String personLevelOneRegex = "^[\\w]+$";
    private static final String personNthLevelRegex = "^[\\w]+\\s[0-9]+$";

    private HashMap<String, Person> personHashMap = new HashMap<>();

    public void initGraph() {

        System.out.println("Inicializando grafo...\n");

        List<String> initCommandsList = new ArrayList<>();

        initCommandsList.add("Oscar-Omar,Cesar,Pedro");
        initCommandsList.add("Omar-Juan,Pablo,Jose");
        initCommandsList.add("Cesar-Minerva");
        initCommandsList.add("Jose-Efrain,Eduardo");
        initCommandsList.add("Efrain-Fernando");
        initCommandsList.add("Pedro-Miguel");

        for (String command : initCommandsList) {
            processCommand(command);
        }

        System.out.println("\n¡Grafo inicializado correctamente!\n");
    }

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

        } else if (command.matches(personLevelOneRegex)) {

            String personName = command.trim();

            Person person = getOrCreatePerson(personName);

            printNthLevelFriends(person, 1);

        } else if (command.matches(personNthLevelRegex)) {

            String[] splitCommand = command.split(" ");

            String personName = splitCommand[0];
            String level = splitCommand[1];

            int n = Integer.parseInt(level);

            Person person = getOrCreatePerson(personName);

            printNthLevelFriends(person, n);
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

    private void printNthLevelFriends(Person person, int n) {
        List<Person> levelFriends = person.getNthLevelFriends(n);

        System.out.println("Amigos de nivel "+n+" de "+person.getName()+":");

        for (Person friend : levelFriends) {
            System.out.println(friend.getName());
        }

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
