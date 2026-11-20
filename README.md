# Portal Web de Serviços — ECL

Aplicação Web académica para a Escola do Comércio de Lisboa. O portal apresenta a oferta formativa, recebe pré-inscrições e permite comunicar e gerir avarias informáticas.

## Funcionalidades
- Página inicial e navegação do portal.
- Catálogo de cursos ativos, carregados do MySQL.
- Pré-inscrição com validação no servidor e gravação na base de dados.
- Registo de avarias com validação dos campos e gravação no MySQL.
- Área reservada, protegida por palavra-passe, para consulta e atualização do estado dos pedidos.

## Tecnologias
Java 17, Maven, Jakarta Servlet 6.0, JSP, Jetty 12, MySQL 8/MariaDB e JDBC.

## Preparar a base de dados
Executar o ficheiro `database/programa_ecl.sql` no MySQL Workbench. Inserir na tabela `cursos` apenas os cursos e descrições oficiais confirmados pela escola.

## Configurar as variáveis de ambiente
Configurar no computador que executa a aplicação:
- `ECL_DB_URL=jdbc:mysql://localhost:3306/portal_ecl?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Europe/Lisbon`
- `ECL_DB_USER=utilizador_mysql`
- `ECL_DB_PASSWORD=palavra-passe`
- `ECL_ADMIN_PASSWORD=palavra-passe-de-administrador`

Não guardar credenciais no GitHub. Usar uma palavra-passe forte para a área reservada.

## Executar localmente
Requisitos: JDK 17 ou superior, Maven e MySQL em execução.

```bash
mvn clean package
mvn jetty:run
```

Abrir `http://localhost:8080/`.

## Endereços
- Início: `/`
- Oferta formativa: `/cursos`
- Pré-inscrição: `/pre-inscricao`
- Formulário de avaria: `/validar-avaria`
- Área reservada / gestão: `/login` e `/pedidos`

## Acesso através da rede local
1. Ligar o computador servidor e os restantes computadores à mesma rede local.
2. Consultar o IPv4 do computador servidor com `ipconfig` (Windows).
3. Permitir a porta TCP 8080 na Firewall do Windows para a rede privada.
4. Iniciar a aplicação com Maven.
5. Nos outros computadores, abrir `http://IP-DO-SERVIDOR:8080/`.

O MySQL pode ficar no próprio computador servidor. Não abrir a porta 3306 à rede se não for necessário. O acesso pela LAN tem de ser testado na rede real da escola.

## Testes a realizar antes da apresentação
- Compilação: `mvn clean package`.
- Abertura da página inicial e navegação.
- Oferta formativa com cursos inseridos na tabela.
- Pré-inscrição válida e consulta do registo no MySQL.
- Pré-inscrição inválida (email, nome ou curso) sem gravação.
- Registo de avaria válida e consulta na área reservada.
- Formulário de avaria incompleto sem gravação.
- Área reservada: redirecionamento para login, palavra-passe incorreta e acesso autorizado.
- Alteração do estado de uma ocorrência.
- Acesso ao portal a partir de outro computador na rede local.

## Limitações / preparação
- Inserir os cursos e descrições oficiais fornecidos pela ECL.
- Definir as quatro variáveis de ambiente antes de iniciar a aplicação.
- Configurar a rede local e executar os testes num segundo computador.
- Usar dados fictícios na demonstração e preparar cópias de segurança da base de dados.

## Autor
Eduardo Martins Correia — 3.º ATIS  
Escola do Comércio de Lisboa
