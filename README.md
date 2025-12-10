# sge-estacionamento-java
Este é um projeto de sistema de gerenciamento de estacionamento desenvolvido em Java, utilizando a arquitetura Model-View-Controller (MVC) para separação de responsabilidades e a biblioteca Swing para a interface gráfica de usuário (GUI). O acesso e persistência de dados são realizados através de JDBC (Java Database Connectivity), seguindo o padrão Data Access Object (DAO).
-----
Tecnologias Utilizadas
Componente        Tecnologia             Descrição
Linguagem         Java (JDK 8+)          Linguagem principal do projeto.
Frontend (View)   Java Swing             Biblioteca nativa para a construção da interface gráfica.
Backend (Padrão)  MVC                    Arquitetura Model-View-Controller para organização do código.
Acesso a Dados    JDBC                   Utilizado para conectar e interagir com o banco de dados.
PersistênciaDAO   (Data Access Object)   Padrão para abstrair e isolar a camada de acesso a dados.
Banco de Dados    MySQL 8 (Configurável) O projeto utiliza JDBC, sendo flexível para diferentes SGBDs.
-----

Funcionalidades Principais
O sistema permite o gerenciamento completo das operações diárias de um estacionamento:

Cadastro de Veículos: Registrar entrada de novos veículos com placa, modelo e proprietário.

Controle de Vagas: Visualização em tempo real das vagas ocupadas e disponíveis.

Registro de Saída: Calcular o tempo de permanência e o valor a ser pago (com base em tarifas horárias).

Gerenciamento de Clientes: Cadastro e consulta de clientes mensalista.

Relatórios: Geração de relatórios básicos de faturamento e ocupação.
-----
Visão Geral
Classe: jlogin
Pacote: view
Tipo: JFrame (janela Swing)
Propósito:
Tela de login simples que captura usuário e senha e valida credenciais estáticas (admin / 123). Em caso de sucesso, fecha a janela atual e instancia a tela principal MainTableScreen. -> É a tela principal do sistema onde será realizado o CRUD dos veículos no sistema . 
-
Responsabilidades

Renderizar a interface gráfica de login com campos Usuário e Senha.
Tratar o evento de clique no botão Entrar.
Validar credenciais e iniciar a próxima tela (MainTableScreen) em caso de sucesso.
Exibir mensagens de erro quando login falhar.
OBS:
nesta versão de demostração usaremos o Usuário = admin  e a senha = 123
-
Componentes (UI)

Utilizamos a Biblioteca Swing do java para a criação da interface gráfica. 
-

# jlogin (Tela de Login - Swing)

## Objetivo
Tela de login para o sistema "Estacionamento SENAC PI". Valida credenciais estáticas e abre a tela principal.

## Requisitos
- Java 8+
- Swing (javax.swing)
