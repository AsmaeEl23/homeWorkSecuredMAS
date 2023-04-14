package ma.enset.AES;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        String secret=(String) getArguments()[0];

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage recieve = receive();
                if(recieve!=null){
                    String crypteEncodeddMsg=recieve.getContent();
                    byte[] cryptedMsg= Base64.getDecoder().decode(crypteEncodeddMsg);
                    try {
                        SecretKey secretKey=new SecretKeySpec(secret.getBytes(),"AES");
                        Cipher cipher=Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE,secretKey);
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