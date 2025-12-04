# sge-estacionamento-java
Sistema de gestão de estacionamento desenvolvido pelos alunos do curso de Programador de Sistemas do SENAC
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

JPanel contentPane: container principal.
JPanel panel: painel interno com fundo Color.LIGHT_GRAY.
JLabel lblNewLabel: rótulo “Usuário”.
JTextField textFieldUsuario: campo de texto para nome de usuário.
JLabel lblNewLabel_1: rótulo “Senha”.
JPasswordField passwordFieldSenha: campo de senha.
JButton btnNewButton: botão “Entrar”.
JLabel lblNewLabel_2: título “Estacionamento SENAC PI”.
-
