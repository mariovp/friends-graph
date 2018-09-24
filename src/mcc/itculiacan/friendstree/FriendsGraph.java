package mcc.itculiacan.friendstree;

import java.util.HashMap;

public class FriendsGraph {

    private static final String singleRelationRegex = "^[\\w]+-[\\w]+$";
    private static final String multipleRelationRegex = "^[\\w]+-([\\w]+,)*[\\w]+$";

    private static final String personLevelOneRegex = "^[\\w]+$";
    private static final String personNthLevelRegex = "^[\\w]+\\s[0-9]+$";

    private HashMap<String, Person> personHashMap = new HashMap<>();

    public void processCommand(String command) {



    }

}
