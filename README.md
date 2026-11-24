# jogo-da-velha-java
Jogo da Velha em Java
Jogo da Velha para dois jogadores, executado no terminal. Os jogadores alternam entre os símbolos X e O.

Requisitos
JDK instalado.
Terminal aberto na pasta principal do projeto.
Compilar e executar
No terminal do VS Code, na pasta do projeto, execute:

javac -d out src/Main.java

Depois, inicie o jogo:

java -cp out Main

Como jogar
Na sua vez, informe a linha e a coluna desejadas, usando números de 1 a 3. Por exemplo, linha 2 e coluna 3.

O que o programa verifica
Entradas e coordenadas inválidas.
Tentativas de jogar em uma casa ocupada.
Vitória por linha, coluna ou diagonal.
Empate quando o tabuleiro fica cheio sem vencedor.
Diagrama de classes
O diagrama está em docs/diagram-de-classes.png
