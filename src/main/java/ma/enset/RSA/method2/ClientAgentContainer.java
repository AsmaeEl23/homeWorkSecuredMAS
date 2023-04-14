package ma.enset.RSA.method2;

import jade.wrapper.AgentContainer;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;

public class ClientAgentContainer {
    public static void main(String[] args) throws Exception {
        Runtime instance =Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer= (AgentContainer) instance.createAgentContainer(profile);
        String encodedPbk="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKIAyBR40tQz7T27e3t5KZ9Qz9NeKNdW5k6Lk4SZkMF6FfHpCpSuu3deXInPiU85rG2VQnF+mM7wV1aCAlVOJaUCAwEAAQ==";
        AgentController client=agentContainer.createNewAgent("client","ma.enset.RSA.method2.ClientAgent",new Object[]{encodedPbk});
        client.start();
    }
}
