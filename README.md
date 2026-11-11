# Intervenções informáticas ECL

## Autor

Eduardo Martins Correia — 3.º ATIS  
Escola de Comércio de Lisboa

## Sobre a aplicação

Aplicação web para registar problemas informáticos nas salas da escola e acompanhar o estado dos pedidos. Inclui também o fluxo de validação de uma comunicação de avaria pedido no módulo 10791: formulário POST, validação no servlet, retorno à JSP com erros e confirmação por JSP.

## Executar

É necessário ter Java 17 ou superior e Maven instalados.

```bash
mvn jetty:run
```

Abrir no navegador:

- Página inicial: http://localhost:8080/
- Formulário de avaria: http://localhost:8080/validar-avaria
- Gestão e acompanhamento de pedidos: http://localhost:8080/pedidos

## Percurso de validação

O formulário envia sala, tipo e descrição por POST para `/validar-avaria`. O servlet verifica os três campos no servidor e, se encontrar erros, encaminha o pedido de volta para a JSP do formulário, preservando os valores. Quando tudo está válido, encaminha para a JSP de confirmação. As JSPs escapam os valores com `Html.escapar` antes de os apresentar.
