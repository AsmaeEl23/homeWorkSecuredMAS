package ma.enset.RSA.method2;

import jade.wrapper.AgentContainer;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;

public class ServerAgentContainer {
    public static void main(String[] args) throws Exception {
        Runtime instance =Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer= (AgentContainer) instance.createAgentContainer(profile);
        String encodedPk="MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAogDIFHjS1DPtPbt7e3kpn1DP014o11bmTouThJmQwXoV8ekKlK67d15cic+JTzmsbZVCcX6YzvBXVoICVU4lpQIDAQABAkAFB8cus43AXxhnyVGNRPbg5o59frf/78fY+oaLIIWZLFeR2ozHoXvPekD6sf9BPDcO6d2uiM1pbDJCKgQCIW+BAiEA5lI7ZMlKryekSb7/p4LWg7nuej4vm/QHMjflPxXwyoUCIQC0EKFdkDt8Rumxjm6FLnN501y81ASlxjQJBWsM8nIooQIhAN69oBr0YHnMCKIZSP0jF3oFEpC+GyTjdyIl1FqSSXBRAiAb4ntqxtw/aaflBD6fR0tAsXeqEldJ6MDEKN+kGKOPIQIhAJHMXa22BZ14aqvcbPp8BhFrJK+UaDWBvl612zpnYMm7";
        AgentController server=agentContainer.createNewAgent("server","ma.enset.RSA.method2.ServerAgent",new Object[]{encodedPk});
        server.start();
    }
}
