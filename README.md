chat-rmi-d70
============

Projeto de refer√™ncia adaptado para entendero b√sico programa√√o de sistemas distribu√≠dos com RMI. Trabalh da faculdade, disciplina de PPSD (Processamento Paralelo e Sistemas Distribu√≠dos).

C√≥digo extr√do do site: http://www.cs.swan.ac.uk/~csneal/InternetComputing/RMIChat.html

Para executar o projeto modelo, basta fazer o download das classes .java e colocar em um diret√rio.

Abra o terminal do seu SO, Compile as classes que cont√m o m√todo Main. ($ ->  indica c√digo feito no terminal)

$ javac Main.java
$ javac ClientChat.java

Depois, gere o stub do Main
$ rmic Main

Inicie o rmiregistry do JDK, no terminal, OBS: Digitei & para que o processo seja iniciado e eu n√o perca o console (terminal), o & √ opcional, se n√o digitar o & vc dever√ abrir um novo terminal:

$ rmiregistry&

Em seguida, inicie execute a classe Main.class e depois o ChatClient.class

$ java Main&
$ java ChatClient&

E execute mais uma vez o ChatClient.class para simular um bate-papo contigo mesmo.

$ java ChatClient&

Pronto, o projeto est√° sendo executado, vc simular√°um bate papo usando a tecnologia de sistemas distribu√dos Java, RMI.

@tgmarinho

