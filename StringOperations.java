
/***
* Task 1: Valid Parentheses Combination Generator
* Task 2: Digit Sum Loop(String)             
* Task 3: Consecutive Number Summer           
* Task 4: Caesar Cipher with Shift Variability          
* Task 5: Encoding Challenge with ASCII Conversion
* Owner: Tanuj Yadav
* Date: 09/09/2024
*/
import java.util.Scanner;
import java.util.Arrays;
public class StringOperations {
    /***
     * This is the main method from where the execution starts
     * This method has switch cases which is used to perform different tasks
     * 
     */
    public static void main(String[] args) {
        Scanner inputFromUser = new Scanner(System.in);
        String userinput;
        do {
            System.out.println(Constants.INDEX);
            System.out.print(Constants.TASKNUMBER);
            userinput = inputFromUser.nextLine().trim();
            if (userinput.equalsIgnoreCase("exit")) {
                System.out.println(Constants.EXIT);
            } else {
                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(userinput);
                } catch (NumberFormatException e) {
                    System.out.println(Errors.INPUTERROR);
                    continue;
                }
                switch (taskNumber) {
                    case 1:
                        validParenthesesCombinationGenerator(inputFromUser);
                        break;
                    case 2:
                        digitSumLoop(inputFromUser);
                        break;
                    case 3:
                        findConsecutiveNumberSums(inputFromUser);
                        break;
                    case 4:
                        caesarCipherWithShiftVariability(inputFromUser);
                        break;
                    case 5:
                        encodingChallenge(inputFromUser);
                        break;
                    default:
                        System.out.println(Errors.INVALIDTASKNUMBER);
                        break;
                }
            }
        } while (!userinput.equalsIgnoreCase("exit"));
        inputFromUser.close();
    }
    /***
     * Task 1: Valid Parentheses Combination Generator
     * This method is used generates all valid combinations of the string pairs .
     * Each combination should be unique and well-formed.
     * It return all the possible combination
     */
    private static void validParenthesesCombinationGenerator(Scanner inputString) {
        boolean shouldContinue = true;
        do {
            System.out.println(Constants.TASK1EXECUTION);
            System.out.println(Constants.TASK1INPUT);
            String stringInput = inputString.nextLine().trim();
            if (stringInput.isEmpty()) {
                System.out.println(Errors.NOTEMPTYSTRING);
                continue;
            }
            generateCombinations("", stringInput);
            shouldContinue = askToContinue(inputString);
        } while (shouldContinue);
    }
    /***
     * The generateCombinations method is used to generate and print all possible
     * combinations of characters from a given input string
     */
    private static void generateCombinations(String currentCombination, String userinput) {
        if (currentCombination.length() == userinput.length()) {
            System.out.println(currentCombination + " ");
            return;
        }
        for (int i = 0; i < userinput.length(); i++) {
            generateCombinations(currentCombination + userinput.charAt(i), userinput);
        }
    }
    /***
     * Task 2: Digit Sum Loop(String)
     * It takes a number as input and replaces it with the sum of
     * its digits until the number is reduced to a single digit.
     * It return the sum of digits
     */
    private static void digitSumLoop(Scanner InputDigit) {
        boolean shouldContinue;
        do {
            System.out.println(Constants.TASK2EXECUTION);
            System.out.print(Constants.TASK2INPUT);
            int targetNumber = -1;
            while (targetNumber < 0) {
                if (InputDigit.hasNextInt()) {
                    targetNumber = InputDigit.nextInt();
                    InputDigit.nextLine(); // Consume the newline character
                    if (targetNumber < 0) {
                        System.out.println(Constants.PLEASEINPUT);
                        System.out.print(Constants.TASK2INPUT); // Ask for input again
                    }
                } else {
                    System.out.println(Errors.INTEGERONLY);
                    InputDigit.next(); // Consume the invalid input
                    System.out.print(Constants.TASK2INPUT); // Ask for input again
                }
            }
            int result = calculatedigitSum(targetNumber);
            System.out.println("Output: " + result);
            shouldContinue = askToContinue(InputDigit);
        } while (shouldContinue);
    }
    /***
     * The digitSum method reduces a number to a single digit by repeatedly summing
     * its digits until the result is less than 10
     * this method is used in digitsumloop() method
     */
    private static int calculatedigitSum(int number) {
        while (number >= 10) {
            number = getsumOfDigits(number);
        }
        return number;
    }
    /***
     * The sumOfDigits method calculates the sum of the digits of a given integer
     * The sumOfDigits method is used in digitSum method
     */
    private static int getsumOfDigits(int number) {
        int digitsum = 0;
        while (number > 0) {
            digitsum += number % 10;
            number /= 10;
        }
        return digitsum;
    }
    /***
     * Task 3: Consecutive Number Summer
     * this method is used to print all possible
     * combinations of consecutive natural numbers that sum up to the given number.
     * It return all the combination of natural number
     */
    private static void findConsecutiveNumberSums(Scanner Inputnumber) {
        boolean shouldContinue;
        do {
            System.out.println(Constants.TASK3EXECUTION);
            System.out.print(Constants.TASK2INPUT);
            int targetNumber = -1;
            while (targetNumber <= 0) {
                if (Inputnumber.hasNextInt()) {
                    targetNumber = Inputnumber.nextInt();
                    Inputnumber.nextLine();
                    if (targetNumber <= 0) {
                        System.out.println(Constants.PLEASEINPUT);
                        System.out.print(Constants.TASK2INPUT);
                    }
                } else {
                    System.out.println(Errors.INTEGERONLY);
                    Inputnumber.next();
                    System.out.print(Constants.TASK2INPUT);
                }
            }
            boolean foundConsecutiveSum = false;
            for (int startPoint = 1; startPoint <= targetNumber / 2; startPoint++) {
                int sum = 0;
                for (int currentNumber = startPoint; sum < targetNumber; currentNumber++) {
                    sum += currentNumber;
                    if (sum == targetNumber) {
                        foundConsecutiveSum = true;
                        printConsecutiveNumbers(startPoint, currentNumber);
                        break;
                    }
                }
            }
            if (!foundConsecutiveSum) {
                System.out.println(Errors.COMBINATIONERROR + targetNumber);
            }
            shouldContinue = askToContinue(Inputnumber);
        } while (shouldContinue);
    }
    
    /***
     * This method is used in the CONSECUTIVENUMBERSUMMER() method
     * The printConsecutiveNumbers method is used to display
     * a sequence of consecutive integers in a formatted way
     */
    private static void printConsecutiveNumbers(int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.print(i);
            if (i < endIndex) {
                System.out.print(" + ");
            }
        }
        System.out.println();
    }
    /***
     * Task 4: Caesar Cipher with Shift Variability
     * This method take an input string and a shift pattern array. For
     * each character in the string, apply the corresponding shift value from the
     * pattern array. If
     * the pattern array length is shorter than the input string, repeat the
     * pattern.
     * 
     */
    private static void caesarCipherWithShiftVariability(Scanner Inputfromuser) {
        boolean shouldContinue = true;
        do {
            System.out.println(Constants.TASK4EXECUTION);
            System.out.print(Constants.INPUTSTRING);
            String inputString = Inputfromuser.nextLine().trim();
            if (inputString.isEmpty()) {
                System.out.println(Errors.NOTEMPTYSTRING);
                continue;
            }
            int[] shiftPattern;
            while (true) {
                System.out.print(Constants.SHIFTINPUT);
                String[] shiftInput = Inputfromuser.nextLine().split(",");
                if (shiftInput.length == 0 || shiftInput[0].trim().isEmpty()) {
                    System.out.println(Errors.NOTEMPTYSHIFTVALUE);
                    continue;
                }
                shiftPattern = new int[shiftInput.length];
                boolean isPatternValid = true;
                for (int i = 0; i < shiftInput.length; i++) {
                    try {
                        int shift = Integer.parseInt(shiftInput[i].trim());
                        if (shift < 0) {
                            System.out.println(Errors.POSITIVEINTEGER);
                            isPatternValid = false;
                            break;
                        }
                        shiftPattern[i] = shift;
                    } catch (NumberFormatException e) {
                        System.out.println(Errors.INTEGERONLY);
                        isPatternValid = false;
                        break;
                    }
                }
                if (isPatternValid) {
                    break;
                }
            }
            String encryptedText = encryptWithCaesarCipher(inputString, shiftPattern);
            System.out.println("inputString: " + inputString);
            System.out.println("Shift Pattern: " + Arrays.toString(shiftPattern));
            System.out.println("Output: " + encryptedText);
            shouldContinue = askToContinue(Inputfromuser);
        } while (shouldContinue);
    }
    /***
     * This method is the part of task 4
     * The encryptWithCaesarCipher method performs encryption of a given string
     * using a Caesar cipher,
     * which is a substitution cipher where each letter in the plaintext is shifted
     * a certain number of places down or up the alphabet.
     * 
     */
    private static String encryptWithCaesarCipher(String userInput, int[] shiftPattern) {
        StringBuilder encryptedString = new StringBuilder();
        int shiftIndex = 0;
        for (char currentChar : userInput.toCharArray()) {
            if (Character.isLetter(currentChar)) {
                int shift = shiftPattern[shiftIndex % shiftPattern.length];
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                currentChar = (char) ((currentChar - base + shift) % 26 + base);
                shiftIndex++;
            } else if (Character.isDigit(currentChar)) {
                int shift = shiftPattern[shiftIndex % shiftPattern.length];
                currentChar = (char) ((currentChar - '0' + shift) % 10 + '0');
                shiftIndex++;
            }
            encryptedString.append(currentChar);
        }
        return encryptedString.toString();
    }
    /***
     * Task 5: Encoding Challenge with ASCII Conversion
     * It encodes the highest digits, based on the provided series, into their
     * corresponding ASCII characters.
     * As an input it only takes single digit
     */
  /***
 * Task 5: Encoding Challenge with ASCII Conversion
 * It encodes the nth highest digits, based on the provided series, into their
 * corresponding ASCII characters.
 * As an input, it only takes single digits.
 */
public static void encodingChallenge(Scanner Inputfromuser) {
    boolean shouldContinue;
    do {
        System.out.println(Constants.TASK5EXECUTION);
        boolean validInput = false;
        while (!validInput) {
            System.out.println(Constants.INPUTARRAY);
            String[] digitString = Inputfromuser.nextLine().split(",");
            int[] digitsArray = new int[digitString.length];
            boolean hasInvalidDigits = false;
            for (int i = 0; i < digitString.length; i++) {
                if (digitString[i].trim().length() != 1 || !Character.isDigit(digitString[i].trim().charAt(0))) {
                    System.err.println(Errors.SINGLEDIGITARRAY);
                    hasInvalidDigits = true;
                    break;
                }
                digitsArray[i] = digitString[i].trim().charAt(0) - '0';
            }
            if (hasInvalidDigits) {
                continue;
            }
            System.out.println(Constants.INPUTSERIES);
            String[] seriesStrings = Inputfromuser.nextLine().split(",");
            boolean hasInvalidSeries = false;
            StringBuilder encodedString = new StringBuilder();
            for (String seriesString : seriesStrings) {
                String trimmedInput = seriesString.trim();
                if (trimmedInput.length() != 1 || !Character.isDigit(trimmedInput.charAt(0))) {
                    System.err.println(Errors.SINGLEDIGITSERIES);
                    hasInvalidSeries = true;
                    break;
                }
                int seriesValue = trimmedInput.charAt(0) - '0';
                if (seriesValue <= 0 || seriesValue > digitsArray.length) {
                    System.err.println(Errors.NEGATIVESERIES);
                    hasInvalidSeries = true;
                    break;
                }
                int nthHighestDigit = findNthHighestDigitUsingSelectionSort(digitsArray, seriesValue);
                encodedString.append((char) ('0' + nthHighestDigit));
            }
            if (hasInvalidSeries) {
                continue;
            }
            System.out.println("Output: " + encodedString.toString());
            validInput = true;
        }
        shouldContinue = askToContinue(Inputfromuser);
    } while (shouldContinue);
}
/***
 * This method finds the nth highest digit in the array using selection sort logic.
 */
private static int findNthHighestDigitUsingSelectionSort(int[] digits, int n) {
    for (int i = 0; i < n; i++) {
        int maxIndex = i;
        for (int j = i + 1; j < digits.length; j++) {
            if (digits[j] > digits[maxIndex]) {
                maxIndex = j;
            }
        }
        int temporaryDigit = digits[maxIndex];
        digits[maxIndex] = digits[i];
        digits[i] = temporaryDigit;
    }
    return digits[n - 1];
}
    /***
     * This method is used to ask from the user if they have to continue the current
     * task or move to next task
     */
    private static boolean askToContinue(Scanner Inputfromuser) {
        System.out.println(Constants.CONTINUETASK);
        String userResponse = Inputfromuser.nextLine().trim();
        return userResponse.equalsIgnoreCase("yes");
    }
}
