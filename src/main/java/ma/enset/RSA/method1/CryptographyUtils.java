package ma.enset.RSA.method1;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class CryptographyUtils {
    public static KeyPair generateRSAKeys() throws  Exception{
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair=keyPairGenerator.generateKeyPair();
        return keyPair;
    }
}
