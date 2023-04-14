package ma.enset.AES;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class ClientAgent extends Agent {
    @Override
    protected void setup() {
        String secret=(String) getArguments()[0];
        String message="Hello Server im the new client";
        try {
            SecretKey secretKey=new SecretKeySpec(secret.getBytes(),"AES");
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            byte [] cryptedMsg= cipher.doFinal(message.getBytes());
            String cryptedEncodedMsg = Base64.getEncoder().encodeToString(cryptedMsg);
            ACLMessage aclMessage=new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(new AID("server",AID.ISLOCALNAME));
            aclMessage.setContent(cryptedEncodedMsg);
            System.out.println("Crypted message : "+Arrays.toString(cryptedMsg));
            System.out.println("Crypted encoded message : "+cryptedEncodedMsg);
            send(aclMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
