<center><img src="images/ensetLOGO.png">
<h1>Distributed multi-agent system and AI<br></h1>
<p><br><br>Asmae EL HYANI<br> Distributed System & Artificial Intelligence Master’s<br> ENSET Mohammedia</p>
</center>
<br><br><br>
<h2>Introduction</h2>
<p>In today's world, the demand for secure communication has increased more than ever before. People need to communicate with each other for various reasons, such as business, education, healthcare, etc., while ensuring the confidentiality and integrity of their messages. To overcome the limitations of traditional communication methods and ensure secure communication, the use of multi-agent systems has become popular. Multi-agent systems consist of several autonomous agents that interact with each other to achieve a common goal.

The aim of this project is to implement a multi-agent system that enables secure message sharing between a clientAgent and a serverAgent. The clientAgent is responsible for sending messages to the serverAgent, and the serverAgent is responsible for receiving and processing the messages. To ensure the confidentiality and integrity of the messages, they will be encrypted using RSA and AES algorithms.</p>
<center><img src="images/chatImg.png" width="300"></center>
<br><br><br><br>
<ol type="I">
  <h2><li >Sending a message encrypted with RSA</li></h2>
<img src="images/RSA1.png">
 <ol type="1">
<h3><li>ClientAgent</li></h3>
<p>Create a client agent that sends an encrypted message to a server agent using RSA encryption and the public key we had gotten from arguments.We used also base64 for convert a byte message to string, then send the message to server.</p>
<pre>
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
</pre>
    <h3><li>ServerAgent</li></h3>
<p>The server receive the message from the client, the message is on format string, so we converted to byte using base64, then decrypted the message using the private key we had gotten from arguments and write it on the console </p>
<pre>
public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        PrivateKey privateKey=(PrivateKey) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage recieve = receive();
                if(recieve!=null){
                    String crypteEncodeddMsg=recieve.getContent();
                    byte[] cryptedMsg= Base64.getDecoder().decode(crypteEncodeddMsg);
                    try {
                        Cipher cipher= Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE,privateKey);
                        byte[] decryptedMsg=cipher.doFinal(cryptedMsg);
                        System.out.println("Decrypted message : "+ decryptedMsg);
                        System.out.println("Message clear : "+new String(decryptedMsg));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
</pre>
<h3><li>SimpleAgentContainer</li></h3>
<p>The class is for creat two agents (client and server)</p>
<pre>
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
</pre>
<h3><li>Generate RSA keys</li></h3>
<p>The methode is for generate the RSA keys</p>
<pre>
public static KeyPair generateRSAKeys() throws  Exception{
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair=keyPairGenerator.generateKeyPair();
        return keyPair;
    }
</pre>
</ol>
  <h2><li >Sending a message encrypted with AES</li></h2>
</ol>