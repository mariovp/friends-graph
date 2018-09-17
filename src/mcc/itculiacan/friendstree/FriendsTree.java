package mcc.itculiacan.friendstree;

public class FriendsTree {

    public void fillTree() {

        //Level 0 (root)
        var person1 = new Person("1");

        // Level 1
        var person2 = new Person("2", person1);
        var person3 = new Person("3", person1);

        // Level 2
        var person4 = new Person("4", person2);
        var person5 = new Person("5", person2);
        var person6 = new Person("6", person2);

        var person7 = new Person("7", person3);
        var person8 = new Person("8", person3);

        //Level 3
        var person9 = new Person("9", person4);
        var person10 = new Person("10", person4);
        var person11 = new Person("11", person4);

        var person12 = new Person("12", person5);
        var person13 = new Person("13", person5);

        var person14 = new Person("14", person6);

        var person15 = new Person("15", person7);
        var person16 = new Person("16", person7);

        var person17 = new Person("17", person8);
        var person18 = new Person("18", person8);
        var person19 = new Person("19", person8);

        var levelFriends = person1.getNthLevelFriends(3);

        System.out.println(levelFriends);

    }

}
