package ma.enset.RSA.method1;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;

public class ClientAgent extends Agent {
    @Override
    protected void setup() {
        PublicKey publicKey=(PublicKey)getArguments()[0];
        String message="Hello Server";
        try {
            //Algorithm
            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte [] cryptedMsg= cipher.doFinal(message.getBytes());
            String cryptedEncodedMsg = Base64.getEncoder().encodeToString(cryptedMsg);
            ACLMessage aclMessage=new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(new AID("server",AID.ISLOCALNAME));
            aclMessage.setContent(cryptedEncodedMsg);

            send(aclMessage);
            System.out.println("Crypted message : "+Arrays.toString(cryptedMsg));
            System.out.println("Crypted encoded message : "+cryptedEncodedMsg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
