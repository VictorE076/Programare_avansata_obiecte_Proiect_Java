package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

class Message {
    public static String WelcomeMessage(String message)
    {
        return message;
    }
}

public class Main {

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println(Message.WelcomeMessage("\n\nHey there!\nThanks for joining/ordering. We're thrilled to have you.\n\n"));
    }
}