chat-rmi-d70
============

Projeto de referência adaptado para entendero básico programação de sistemas distribuídos com RMI. Trabalho da faculdade, disciplina de PPSD (Processamento Paralelo e Sistemas Distribuídos).

Código extraído do site: http://www.cs.swan.ac.uk/~csneal/InternetComputing/RMIChat.html

Para executar o projeto modelo, basta fazer o download das classes .java e colocar em um diretório.

Abra o terminal do seu SO, Compile as classes que contém o métodoMain. ($ ->  indica código feito no terminal)

$ javac Main.java
$ javac ClientChat.java

Depois, gere o stub do Main
$ rmic Main

Inicie o rmiregistry do JDK, no terminal, OBS: Digitei & para que o processo seja iniciado e eu não perca o console (terminal), o & é opcional, se não digitar o & vc deverá abrir um novo terminal:

$ rmiregistry&

Em seguida, inicie execute a classe Main.class e depois o ChatClient.class

$ java Main&
$ java ChatClient&

E execute mais uma vez o ChatClient.class para simular um bate-papo contigo mesmo.

$ java ChatClient&

Pronto, o projeto está sendo executado, vc simularáum bate papo usando a tecnologia de sistemas distribuídos Java, RMI.

@tgmarinho

