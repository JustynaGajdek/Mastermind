package org.example;

import java.util.*;

//    Mastermind: zaimplementuj grę Mastermind, której zasady są następujące:
//    komputer losuje 3 cyfry (od 0 do 9).
//    Użytkownik ma za zadanie je odgadnąć, wpisując je w jednej linii, po spacji, np 1 2 3.
//    Następnie użytkownik dostaje informację zwrotną od komputera w formie trzech wyrazów.
//    HOT oznacza, że użytkownik trafił w cyfrę w odpowiednim miejscu,
//    WARM oznacza, że podana cyfra znajduje się w kombinacji, ale w innym miejscu,
//    a COLD oznacza, że cyfry nie ma w kombinacji.
//    Czyli, jeżeli komputer wymyśli kombinację 1 2 3, a użytkownik podał 1 3 4, to komunikat powinien być HOT WARM COLD.
//    Gra się toczy aż użytkownik odgadnie dokładnie kombinację, czyli dostanie komunikat HOT HOT HOT.
//    Po zaimplementowaniu głównej mechaniki gry, dodatkowym zadaniem jest zaimplementowanie poziomów trudności
//    (łatwy - 3 cyfry do odgadnięcia, średni - 4 cyfry, trudny - 5 cyfer) oraz ilości "żyć"
//    - jeżeli użytkownik nie odgadnie kombinacji np w 5 próbach, przegrywa.
public class App 
{
    public static void main( String[] args )
    {
        startGame();
    }

    public static void startGame(){
        System.out.println( "Welcome in Mastermind game!");
        System.out.println( "Try to guess the numbers drawn by the computer!");
        System.out.println("Choose difficulty level:");
        System.out.println("1. Easy (You have to guess 3 numbers and you have 5 lives)");
        System.out.println("2. Medium (You have to guess 4 numbers and you have 4 lives)");
        System.out.println("3. Hard (You have to guess 5 numbers and you have 3 lives)");
        Scanner scanner = new Scanner(System.in);
        int difficulty = scanner.nextInt();
        int lives;
        int numberOfDigits;
        switch (difficulty) {
            case 1:
                lives = 5;
                numberOfDigits = 3;
                break;
            case 2:
                lives = 4;
                numberOfDigits = 4;
                break;
            case 3:
                lives = 3;
                numberOfDigits = 5;
                break;
            default:
                lives = 7;
                numberOfDigits = 4;
                break;
        }
        System.out.println("The computer has just drawn " + numberOfDigits + " numbers. Try to guess them!");
        List<Integer> compNumbers = computerDraw(numberOfDigits);
        List<Integer> userNumbers;

                while (lives > 0) {
                    System.out.println("You have " + lives + " lives left.");
                    userNumbers = getUserNumbersList(numberOfDigits);
                    compNumbers = gameMechanic(compNumbers, userNumbers, numberOfDigits);

                    if (compNumbers.equals(userNumbers)) {
                        System.out.println("Congratulations! You guessed the numbers!");
                        break;
                    }
                    lives--;
                    if (lives == 0) {
                        System.out.println("You lost! No lives left. The numbers were: " + compNumbers);
                    }
                }
    }
    public static List<Integer> computerDraw(int numberOfDigits) {
        Random random = new Random();
        List<Integer> compNumbers = new ArrayList<>();
        while (compNumbers.size() < numberOfDigits) {
            int randomNumber = random.nextInt(10);
            if (!compNumbers.contains(randomNumber)) {
                compNumbers.add(randomNumber);
            }
        }
        return compNumbers;
    }
        public static List<Integer> getUserNumbersList(int numberOfDigits) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter" +  numberOfDigits + "unique numbers separated by a space:");
        String inPutLine = scanner.nextLine();
        String[] cleanUserNumbers = inPutLine.trim().split(" ");
        List<Integer> userNumbers = new ArrayList<>();
        String[] userNumbersString = inPutLine.trim().split(" ");
        for (String s : userNumbersString) {
            int number = Integer.parseInt(s);
            userNumbers.add(number);
        }
//            System.out.println("Users numbers:" + userNumbers);
            return userNumbers;
        }
        public static  List<Integer> gameMechanic (List<Integer> compNumbers, List<Integer> userNumbers, int numberOfDigits) {
        int hot = 0;
        int warm = 0;
        int cold = 0;
        for (Integer userNumber : userNumbers) {
            if (compNumbers.contains(userNumber)) {
                if (compNumbers.indexOf(userNumber) == userNumbers.indexOf(userNumber)) {
                    hot++;
                    System.out.print("HOT" + " ");
                } else {
                    warm++;
                    System.out.print("WARM" + " ");
                }
            } else {
                cold++;
                System.out.print( "COLD" + " ");
            }
        }
        System.out.println();

        if (hot == numberOfDigits) {
            System.out.println("You won!!");
        } else {
            System.out.println("You lost! Try again.");
            computerDraw(numberOfDigits);
        }
            return compNumbers;
    }
}














