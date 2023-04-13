package ma.enset.RSA.method1;

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
        AgentController client=agentContainer.createNewAgent("client","ma.enset.RSA.method1.ServerAgent",new Object[]{});
        client.start();
    }
}
