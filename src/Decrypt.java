import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/*
 * Author: Matthew Goembel, mgoembel2022@my.fit.edu
 * Course: CSE 2010
 * Section: 02, Fall 2023
 * Description: The program starts by reading in a command line file that contains lines
 * of encrypted characters. Each line is then read and sent to one of the two methods:
 *
 * decrypt, which uses recursion and runs in O(26^n), and decryptStack which uses stacks,
 * and runs in the same time because both are brute-force approaches.
 * Both methods go through each character of the alphabet generating passwords
 * character by character and checks if they match the target, exploring all permutations
 * in a systematic way.
 *
 * GroupHelp: Modified the alphabet to be in order of the most frequently used characters
 * towards the front and the less frequently used characters in the back.
 */

public class Decrypt {
   // All the possible characters in the alphabet
   static final String letters = "eariotnslcudpbfghjkmqvwxyz";  // In order of frequency
   // Max length of the password
   static int maxLength;

   public static void main(String[] args) {
      final String inputFileName = args[0];
      ArrayList<String> passwordList = new ArrayList<>();
      // Pass through file reader to read every line
      try (BufferedReader file = new BufferedReader(new FileReader(inputFileName))) {
         // Get tne first line length and store
         maxLength = Integer.parseInt(file.readLine());
         String encryptedPassword;
         // For each line, call the decrypt method
         while ((encryptedPassword = file.readLine()) != null) {
            passwordList.add(encryptedPassword);
         }
         /** Uncomment/comment the method you would like to use */
         for (String p: passwordList) {
            //decrypt(p, "", 0);  // Uses recursion: Current line, unassigned password, length of unassigned pass.
            decryptStack(p);      // Uses stacks: Current line
         }
         // Need file error catch
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * The decrypt method makes use of the encrypt method, by reading the
    * line of encrypted characters and setting that as the target, then
    * encrypt each letter of the alphabet, checking for the same encryption
    * until we have a completed length password
    * @param target the letter we are trying to decode
    * @param current the string to hold the password we decode
    * @param length holds the current length of the string we are decoding
    */
   private static void decrypt (final String target, final String current, final int length) {
      // If the length is equal or more than the max password length return all
      if (length >= maxLength) {
         return;
      }
      // Check each character of the lowercase letter of the alphabet
      for (char c : letters.toCharArray()) {
         // Add the char to the password string
         final String password = current + c;
         // Encrypt the current password builder string
         final String encrypted = HW2crypto.encrypt(password);
         // Check if the encrypted string matches the target
         if (encrypted.equals(target)) {
            // Print the generated password and go to next
            System.out.println(password);
            return;
         }
         // Call decrypt again to decrypt the next char in the password (length + 1)
         decrypt(target, password, length + 1);
      }
   }

   /**
    * DecryptStack also solves the same problem, but by
    * implementing stacks instead. The method loops until
    * the target is found, by encrypting each char until
    * the newly encrypted password matches the target
    * @param target the encrypted password we are trying to decrypt
    */
   private static void decryptStack (final String target) {
      // Stack for the password and stack for length of the pass.
      Stack<String> password = new Stack<>();
      Stack<Integer> length = new Stack<>();
      // Set the base values for both stacks
      password.push("");
      length.push(0);
      // Iterate while the stack is not empty
      while (!password.isEmpty()) {
         // Remove the top element off of each stack
         final String currentPassword = password.pop();
         final int currentLength = length.pop();
         // Skip is the password is greater than the maxLength
         if (currentLength >= maxLength) {
            continue;
         }
         // Check each character of the lowercase letter of the alphabet
         for (char c: letters.toCharArray()) {
            // Add the current letter to the new password
            final String newPassword = currentPassword + c;
            // Call the encrypt method to encrypt the password
            final String encrypted = HW2crypto.encrypt(newPassword);
            // Check is the encrypted password matches the target
            if (encrypted.equals(target)) {
               System.out.println(newPassword); // Print out the decrypted password
               return;
            }
            // Otherwise add the newPassword to the password stack and continue decrypting
            password.push(newPassword);
            // Increase the length of the stack by one, to look for the next char
            length.push(currentLength + 1);
         }

      }
   }
}
