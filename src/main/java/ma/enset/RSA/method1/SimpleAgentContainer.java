package ma.enset.RSA.method1;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class SimpleAgentContainer {
    public static void main(String[] args) throws Exception{
        Runtime instance =Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer= (AgentContainer) instance.createAgentContainer(profile);
        KeyPair keyPair = CryptographyUtils.generateRSAKeys();
        PrivateKey privateKey=keyPair.getPrivate();
        PublicKey publicKey=keyPair.getPublic();
        AgentController server=agentContainer.createNewAgent("server","ma.enset.RSA.method1.ServerAgent",new Object[]{privateKey});
        AgentController client=agentContainer.createNewAgent("client","ma.enset.RSA.method1.ClientAgent",new Object[]{publicKey});
        server.start();
        client.start();
    }
}
