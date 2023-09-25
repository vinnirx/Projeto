# Projeto
Oferta Logística

Documentação do Sistema de Cálculo de Frete
Objetivo
O objetivo principal deste sistema é calcular o custo de frete para os produtos que nossos clientes desejam comprar. Para fazer isso, o sistema leva em consideração os códigos de produtos (SKUs) e os Códigos de Endereçamento Postal (CEPs) fornecidos pelos clientes.
Requisitos do Sistema
O sistema atende aos seguintes requisitos:
• Calcular o frete com base nos SKUs e CEPs fornecidos.
• Verificar a disponibilidade de cada SKU por meio de uma API de estoque.
• Consultar o custo de frete por meio de uma API de cálculo de frete.
• Retornar o prazo de entrega, a data estimada de entrega, o valor do frete e
mensagens de erro, se aplicável.
Arquitetura Geral
O sistema é composto por três principais componentes:
• Cliente: A interface do cliente que envia solicitações de cálculo de frete ao sistema.
• Serviço de Cálculo de Frete: O componente principal que coordena todo o processo de cálculo de frete. Ele interage com o serviço de estoque e o serviço de cálculo de frete para obter informações necessárias.
• APIs de Estoque e Cálculo de Frete: APIs mockadas que fornecem informações de estoque e custo de frete. Essas APIs são simuladas, pois ainda estão em desenvolvimento real.
Fluxo de Funcionamento
O sistema opera da seguinte maneira:
• O cliente envia uma solicitação de cálculo de frete com uma lista de SKUs e um único CEP.
• O serviço de cálculo de frete verifica a disponibilidade de cada SKU consultando a API de estoque para cada SKU na lista.
• Se algum SKU não estiver disponível, o sistema retorna uma mensagem de erro informando quais SKUs não estão disponíveis.
• Se todos os SKUs estiverem disponíveis, o sistema consulta a API de cálculo de frete com o CEP fornecido pelo cliente.
• Se o CEP não for coberto por nenhuma transportadora, o sistema retorna uma mensagem informando que a entrega não é possível para o CEP fornecido.
• Se a API de cálculo de frete estiver indisponível ou demorar mais de 5 milissegundos para responder, o sistema toma uma ação de contorno e fornece um prazo e valor médio de frete parametrizado.
• O sistema calcula o prazo de entrega somando o maior prazo de expedição dos SKUs à resposta da API de cálculo de frete.
• O sistema retorna ao cliente o prazo de entrega, a data estimada de entrega, o valor do frete e mensagens de erro, se aplicável.
Métricas Importantes
Para manter o controle e aprimorar nosso sistema, coletamos métricas importantes, como:
• Número de pedidos não atendidos devido à indisponibilidade da API de Cálculo de Frete.
• Número de produtos não atendidos devido à indisponibilidade da API de Cálculo de Frete.
• Número de pedidos não atendidos devido à baixa performance da API de Cálculo de Frete.
• Número de produtos não atendidos devido à baixa performance da API de Cálculo de Frete.
• Total de solicitações de cálculo de frete.
• Total de solicitações por CEP.
Conclusão
Este sistema de cálculo de frete permite que a Tribo de Oferta Logística ofereça aos seus clientes uma estimativa precisa de custos e prazos de entrega. Ele é projetado para ser escalável e flexível, permitindo futuras adições de regras de cálculo, conforme necessário. O sistema contribui para uma experiência positiva do cliente ao fornecer informações de frete confiáveis e oportunas.
