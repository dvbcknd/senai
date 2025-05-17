# Documentação do Projeto de Agendamento de Eventos na Nuvem (AWS)

## 1. Plataforma Escolhida

- **Plataforma utilizada:** Amazon Web Services (AWS)
- **Serviço principal:** Amazon EC2 (Elastic Compute Cloud)
- **Motivo da escolha:**
    - Possibilidade de uso gratuito com laboratório temporário educacional (Amazon Academy), disponibilizado pelo professor Jefté.
    - Flexibilidade e controle total da instância virtual.

---

## 2. Configuração Inicial na Nuvem

- **Conta utilizada:** Acesso temporário via ambiente de laboratório da AWS Academy.
- **Instância EC2 criada:**
    - **Sistema operacional:** Amazon Linux
    - **Tipo:** `t2.micro`
    - **Região:** US East (N. Virginia)

- **Chave SSH:**
    - Gerada durante a criação da instância: `MinhaChaveSSH.pem`

- **Conexão com a instância (via Git Bash):**
  ```bash
  chmod 400 MinhaChaveSSH.pem
  ssh -i /caminho/para/MinhaChaveSSH.pem ec2-user@ec2-54-234-164-95.compute-1.amazonaws.com

---
## 3. Implantação do Sistema

- **Back-end Spring Boot:**
    -   Projeto Java com Spring Boot, Thymeleaf, Spring MVC e Spring Data JPA.
    -   Empacotado com Maven em um .jar: senai-0.0.1-SNAPSHOT.jar
    - Compilado com JDK versão 24, o que exigiu instalar o Java Corretto 19 na EC2.

- **Transferência do sistema:**
    - arquivo .jar transferido via scp:
  ```bash
    scp -i MinhaChaveSSH.pem senai-0.0.1-SNAPSHOT.jar ec2-54-234-164-95.compute-1.amazonaws.com

- **Execução**
    - Após configurar o Java 24: 
     ```bash
      java -jar senai-0.0.1-SNAPSHOT.jar

- **Banco de dados:**
    - Utilizado banco H2 em memória, embutido na aplicação.
- **Front-end:**
    - Thymeleaf servindo as páginas dinâmicas diretamente a partir do Spring Boot.
---

## 4. Testes na Nuvem

-   **Acesso via navegador:**
    - A aplicação foi acessada com sucesso via IP público:
      http://ec2-54-234-164-95.compute-1.amazonaws.com:8080/login
    - Página de login exibida corretamente.
    - Login bem-sucedido redirecionando para a tela de agendamento.

-   **Testes com Postman:**
    - Foi testado envio de requisições (GET, POST, DELETE etc.).
    - Autenticação exigida – requisições protegidas sem login retornam 401 Unauthorized.
    - Com autenticação, operações funcionam corretamente.

-   **Escalabilidade:**
    - A aplicação respondeu bem com múltiplas abas/navegadores simulando acessos simultâneos.
---
## 5. Segurança e Boas Práticas

- **Autenticação:**
    - O sistema exige login e senha para acessar a área de agendamento.
    - Endpoints protegidos por Spring Security.

-   **Firewall/Segurança na AWS:**
    - Porta 8080 liberada no Security Group da instância EC2.
    - Apenas acesso por IP público via HTTP permitido.

- **Logs:**
    - Aplicação exibe logs no terminal com status de requisições e eventos do sistema.
---
## Status do Projeto

-   Plataforma definida: AWS
-   Instância EC2 configurada e operante
-   Sistema Spring Boot empacotado e implantado
-   Aplicação funcional com autenticação e interface de agendamento
-   Testes feitos com Postman e navegador
- Segurança básica configurada
