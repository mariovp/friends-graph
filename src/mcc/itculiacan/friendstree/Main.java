package mcc.itculiacan.friendstree;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FriendsGraph friendsGraph = new FriendsGraph();

        friendsGraph.initGraph();

        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\nEscriba una instrucción: ");
            String command = sc.nextLine();

            if (command.equals("exit")) {
                break;
            }

            friendsGraph.processCommand(command);
        }

    }
}
