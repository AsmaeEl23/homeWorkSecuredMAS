package ma.enset.AES;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class ServerAgentContainer {
    public static void main(String[] args) throws Exception {
        Runtime instance =Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer= (AgentContainer) instance.createAgentContainer(profile);
        String secret="1234561234561234";//128 bits
        AgentController server=agentContainer.createNewAgent("server","ma.enset.AES.ServerAgent",new Object[]{secret});
        server.start();
    }
}
