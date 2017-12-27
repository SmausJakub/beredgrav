package cz.zcu.kiv.pia.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordHashEncoder implements Encoder {

    @Override
    public String encode(String text) {
        try {
            return PasswordHash.createHash(text);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //TODO log!
            return null;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            //TODO log!
            return null;
        }
    }

    @Override
    public boolean validate(String text, String hash) {
        try {
            return PasswordHash.validatePassword(text, hash);
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //TODO log!
            return false;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            //TODO log!
            return false;
        }
    }
}
