package ma.enset;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;

public class MainContainer {
    public static void main(String[] args) throws Exception {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.GUI,"true");
        AgentContainer agentContainer=instance.createAgentContainer(profile);
        agentContainer.start();
    }
}
