package ma.enset.AES;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class ClientAgentContainer {
    public static void main(String[] args) throws Exception {
        Runtime instance =Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer= (AgentContainer) instance.createAgentContainer(profile);
        String secret="1234561234561234";//128 bits
        AgentController client=agentContainer.createNewAgent("client","ma.enset.AES.ClientAgent",new Object[]{secret});
        client.start();
    }
}
