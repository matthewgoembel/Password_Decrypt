import java.util.Random;

/**
 *   HW2crypto provides the encrypt method for encrypting a string
 *
 *   Usage:
 *   HW2crypto.encrypt(...)
 */
public class HW2crypto {
    /**
     * This method creates an encrypted string of the word provided
     *
     * @param word The word that needs to be encrypted
     * @return The encrypted word as a String
     */
    public static String encrypt(String word) {

        final long seed = 723637564;
        Random rnd = new Random(seed);

        StringBuilder plainText = new StringBuilder(word);
	// Calculates the # of chars needed to reach a length of 20 chars
        int size = 20  - plainText.length();
	// Retrieve the ASCII value of the last char in the input string
        int ascii = (int) (plainText.charAt(plainText.length() - 1));
	// Adds random chars to the plainText StringBuilder 
	// until it reaches a length of 20 chars
        for(int i=0;i<size;i++) {
	    // Generate random int and add the ASCII value of last char, %94 
	    // and +33 random int within ASCII range
            plainText.append((char) (((rnd.nextInt(Integer.MAX_VALUE) + ascii) % 94) + 33));
	    // update with new random int
            ascii += rnd.nextInt(200);
        }
	// New Stringbuilder to store encrypted result
        StringBuilder encryptedWord = new StringBuilder();
	// Retrieve the ASCII value of the last char in the input string
        ascii = (int) (plainText.charAt(plainText.length() - 1));
	// Loops through each char from plainText
        for (char c : plainText.toString().toCharArray()) {
	    // Adds ASCII value of the char to the current ascii value.
            ascii += (int) c;
	    // Generate random int and add the ASCII value of last char, %94 
	    // and +33 random int within ASCII range
            encryptedWord.append((char) (((rnd.nextInt(Integer.MAX_VALUE) + ascii) % 94) + 33));
            encryptedWord.append((char) (((rnd.nextInt(Integer.MAX_VALUE) + ascii) % 94) + 33));
        }
	// Return encrypted result
        return encryptedWord.toString();
    }

    public static void main(String[] args) {

        System.out.println(encrypt("cat"));
        System.out.println(encrypt("at"));
        System.out.println(encrypt("dog"));
        System.out.println(encrypt("and"));
        System.out.println(encrypt("tan"));
        System.out.println(encrypt("on"));
        System.out.println(encrypt("arr"));
        System.out.println(encrypt("ace"));
        System.out.println(encrypt("hex"));

   }
}
