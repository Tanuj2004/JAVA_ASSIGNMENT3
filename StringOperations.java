
/***
 *Task 1: Valid Parentheses Combination Generator

 *Task 2: Digit Sum Loop(String)
               
 *Task 3: Consecutive Number Summer
            
*Task 4: Caesar Cipher with Shift Variability
           
*Task 5: Encoding Challenge with ASCII Conversion
           
*Owner-->TANUJ YADAV
*DATE-->09/09/2024
 */
import java.util.Scanner;
import java.util.Arrays;

public class StringOperations {
    public static void main(String[] args) {
        Scanner Inputfromuser = new Scanner(System.in);
        String input;

        do {
            System.out.println(Constants.INDEX);
            System.out.println(Constants.TASKNUMBER);
            input = Inputfromuser.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println(Constants.EXIT);
            } else {
                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println(Errors.INPUTERROR);
                    continue;
                }
                switch (taskNumber) {
                    case 1:
                        validParenthesesCombinationGenerator(Inputfromuser);
                        break;
                    case 2:
                        digitSumLoop(Inputfromuser);
                        break;
                    case 3:
                        consecutiveNumberSummer();
                        break;
                    case 4:
                        caesarCipherWithShiftVariability(Inputfromuser);
                        break;
                    case 5:
                     //   encodingChallenge(Inputfromuser);
                        break;
                    default:
                        System.out.println(Errors.INVALIDTASKNUMBER);
                        break;
                }
            }
        } while (!input.equalsIgnoreCase("exit"));

        Inputfromuser.close();
    }

    private static void validParenthesesCombinationGenerator(Scanner Inputstring) {
        System.out.println(Constants.TASK1EXECUTION);
        System.out.println(Constants.TASK1INPUT);
        String input = Inputstring.nextLine();
        generateCombinations("", input);
    }

    private static void generateCombinations(String current, String input) {
        if (current.length() == input.length()) {
            System.out.print(current + " ");
            return;
        }
        for (int i = 0; i < input.length(); i++) {
            generateCombinations(current + input.charAt(i), input);
        }
    }

    private static void digitSumLoop(Scanner InputDigit) {
        System.out.println(Constants.TASK2EXECUTION);
        System.out.print(Constants.TASK2INPUT);

        int number = InputDigit.nextInt();
        InputDigit.nextLine();
        if (number < 0) {
            System.out.println(Constants.PLEASEINPUT);
        } else {
            int result = digitSum(number);
            System.out.println(" Output: " + result);
        }
    }

    private static int digitSum(int number) {
        while (number >= 10) {
            number = sumOfDigits(number);
        }
        return number;
    }

    private static int sumOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    @SuppressWarnings("resource")
    private static void consecutiveNumberSummer() {
        System.out.println(Constants.TASK3EXECUTION);
        Scanner Inputnumber = new Scanner(System.in);
        System.out.print(Constants.TASK2INPUT);
        int number = Inputnumber.nextInt();
        if (number <= 0) {
            System.out.println(Constants.PLEASEINPUT);
            return;
        }
        boolean found = false;
        for (int start = 1; start < number; start++) {
            int sum = 0;
            for (int current = start; current < number; current++) {
                sum += current;
                if (sum == number) {
                    found = true;
                    printConsecutiveNumbers(start, current);
                }
                if (sum > number) {
                    break;
                }
            }
        }
        if (!found) {
            System.out.println(Errors.COMBINATIONERROR + number);
        }
        Inputnumber.close();
    }

    private static void printConsecutiveNumbers(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(i);
            if (i < end) {
                System.out.print(" + ");
            }
        }
        System.out.println();
    }

    private static void caesarCipherWithShiftVariability(Scanner Inputfromuser) {
        System.out.println(Constants.TASK4EXECUTION);
        System.out.print(Constants.INPUTSTRING);
        String input = Inputfromuser.nextLine();

        System.out.print(Constants.SHIFTINPUT);
        String[] shiftInput = Inputfromuser.nextLine().split(",");
        int[] shiftPattern = new int[shiftInput.length];
        for (int i = 0; i < shiftInput.length; i++) {
            shiftPattern[i] = 0;
            for (char c : shiftInput[i].toCharArray()) {
                shiftPattern[i] = shiftPattern[i] * 10 + (c - '0');
            }
        }
        String encryptedText = encryptWithCaesarCipher(input, shiftPattern);
        System.out.println("Input: " + input);
        System.out.println("Shift Pattern: " + Arrays.toString(shiftPattern));
        System.out.println("Output: " + encryptedText);
    }

    private static String encryptWithCaesarCipher(String input, int[] shiftPattern) {
        String encrypted = "";
        int shiftIndex = 0;
        for (char currentChar : input.toCharArray()) {
            if (Character.isLetter(currentChar)) {
                int shift = shiftPattern[shiftIndex % shiftPattern.length];
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                currentChar = (char) ((currentChar - base + shift) % 26 + base);
                shiftIndex++;
            }
            encrypted += currentChar;
        }

        return encrypted;
    }

    
}
