package ma.enset.RSA.method2;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class ClientAgent extends Agent {
    @Override
    protected void setup() {
        String encodedPbk=(String) getArguments()[0];
        byte[] decodedPbk=Base64.getDecoder().decode(encodedPbk);
        String message="Hello Server im the new client";
        try {
            KeyFactory keyFactory=KeyFactory.getInstance("RSA");
            PublicKey publicKey=keyFactory.generatePublic(new X509EncodedKeySpec(decodedPbk));
            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
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
