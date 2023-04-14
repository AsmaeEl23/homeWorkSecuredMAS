package ma.enset.RSA.method2;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.util.Base64;

public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        PrivateKey privateKey=(PrivateKey) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage recieve = receive();
                if(recieve!=null){
                    String crypteEncodeddMsg=recieve.getContent();
                    byte[] cryptedMsg= Base64.getDecoder().decode(crypteEncodeddMsg);
                    try {
                        Cipher cipher= Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE,privateKey);
                        byte[] decryptedMsg=cipher.doFinal(cryptedMsg);
                        System.out.println("Decrypted message : "+ decryptedMsg);
                        System.out.println("Message clear : "+new String(decryptedMsg));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
