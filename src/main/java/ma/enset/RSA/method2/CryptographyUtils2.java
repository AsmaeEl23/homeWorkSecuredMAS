package ma.enset.RSA.method2;

import ma.enset.RSA.method1.CryptographyUtils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class CryptographyUtils2 {
    public static void main(String[] args) throws Exception{
        KeyPair keyPair=CryptographyUtils.generateRSAKeys();
        PrivateKey privateKey=keyPair.getPrivate();
        PublicKey publicKey=keyPair.getPublic();
        String encodedPK = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String encodedPpK = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("------------private key-------------");
        System.out.println(encodedPK);
        System.out.println("------------public key-------------");
        System.out.println(encodedPpK);
    }
}
