package cz.zcu.kiv.pia.utils;

public interface Encoder {

    /**
     * Returns hash of the passed text.
     * @param text
     * @return
     */
    String encode(String text);

    /**
     * Validates that the text is the plaintext form associated with the hash.
     * @param text plaintext form
     * @param hash hash for comparison
     * @return true of correct
     */
    boolean validate(String text, String hash);
    
}
