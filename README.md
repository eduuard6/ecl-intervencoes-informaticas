# Portal Web de Serviços — ECL

Aplicação Web académica para a Escola do Comércio de Lisboa. Evolui a aplicação de intervenções informáticas e acrescenta o catálogo de cursos e as pré-inscrições.

## Funcionalidades
- Página inicial e navegação do portal.
- Catálogo de cursos ativos, carregados do MySQL.
- Pré-inscrição com validação no servidor e gravação na base de dados.
- Registo e acompanhamento de ocorrências/avarias.
- Atualização do estado e observação técnica dos pedidos.

## Tecnologias
Java 17, Maven, Jakarta Servlet 6.0, JSP, Jetty 12, MySQL 8/MariaDB e JDBC.

## Preparar a base de dados
Executar database/programa_ecl.sql no MySQL Workbench. Inserir na tabela cursos apenas os cursos e descrições oficiais confirmados pela escola.

## Configurar a ligação
Definir no servidor as variáveis de ambiente:
- ECL_DB_URL=jdbc:mysql://localhost:3306/portal_ecl?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Europe/Lisbon
- ECL_DB_USER=utilizador_mysql
- ECL_DB_PASSWORD=palavra-passe

Não guardar credenciais no GitHub.

## Executar
Requisitos: JDK 17 ou superior, Maven e MySQL em execução.

    mvn clean package
    mvn jetty:run

Abrir http://localhost:8080/.

## Endereços
- Início: /
- Oferta formativa: /cursos
- Pré-inscrição: /pre-inscricao
- Formulário de avaria: /validar-avaria
- Gestão de ocorrências: /pedidos

## Rede local
Iniciar o servidor numa máquina com IP fixo e permitir TCP 8080 na firewall. Os outros computadores acedem a http://IP-DO-SERVIDOR:8080/. A configuração da VM, firewall e rede tem de ser realizada e testada no ambiente da escola.

## Segurança e tarefas pendentes
A rota /pedidos ainda não tem autenticação. Não a exponhas fora de uma rede de testes controlada até implementar login e permissões. Configurar HTTPS, cópias de segurança e um utilizador MySQL com permissões mínimas antes de qualquer utilização real. Utilizar dados fictícios nos testes.
A oferta formativa não inclui cursos fictícios: inserir apenas informação oficial fornecida pela ECL.
A VM, firewall, endereço IP fixo, testes de acesso a partir de outro computador e cópias de segurança ainda têm de ser configurados e demonstrados no ambiente da escola.

## Autor
Eduardo Martins Correia — 3.º ATIS
Escola do Comércio de Lisboa
