# Como Executar o Projeto

## Requisitos

* Java JDK 8 ou superior
* Eclipse (ou outra IDE Java)

## Executando o Projeto

1. Clone o repositório ou importe o projeto na IDE.
2. Execute a classe:

```java
br.edu.ifsuldeminas.sd.chat.view.ConnectionView
```

3. Escolha o modo de comunicação:

   * **P2P** (UDP ou TCP)
   * **Grupo (Multicast)**

4. Preencha os campos de conexão e clique em **Conectar**.

---

## Comunicação P2P

### UDP

Máquina 1:

* Protocolo: UDP
* Porta Local: 5000
* IP Remoto: IP da Máquina 2
* Porta Remota: 6000

Máquina 2:

* Protocolo: UDP
* Porta Local: 6000
* IP Remoto: IP da Máquina 1
* Porta Remota: 5000

### TCP

Máquina 1:

* Protocolo: TCP
* Porta Local: 5000
* IP Remoto: IP da Máquina 2
* Porta Remota: 6000

Máquina 2:

* Protocolo: TCP
* Porta Local: 6000
* IP Remoto: IP da Máquina 1
* Porta Remota: 5000

---

## Comunicação em Grupo (Multicast)

Todos os participantes devem utilizar:

* Modo: Grupo
* Endereço do Grupo: 230.0.0.1
* Porta: 5000

Exemplo:

Participante 1:

* Nome: Arielce

Participante 2:

* Nome: Rafael

Participante 3:

* Nome: Guilherme

As mensagens enviadas por qualquer participante serão recebidas por todos os membros do grupo.

---

## Observações

* Para comunicação entre computadores diferentes, todos devem estar na mesma rede.
* Pode ser necessário liberar o Java no Firewall do Windows.
* No modo Multicast, todos os participantes utilizam o mesmo endereço de grupo e a mesma porta.
* O recebimento de mensagens ocorre em uma thread separada para evitar bloqueio da interface gráfica.
