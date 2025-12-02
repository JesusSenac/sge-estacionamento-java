package view;


import controller.ControleEstacionamento;
import model.EstacionamentoModel;
import java.util.Scanner;

public class EstacionamentoView {
    public static void main(String[] args) {
        ControleEstacionamento controle = new ControleEstacionamento();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("=== Cadastro de Estacionamento ===");
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("CNPJ: ");
            int cnpj = sc.nextInt(); sc.nextLine();
            System.out.print("Nome Fantasia: ");
            String fantasia = sc.nextLine();
            System.out.print("Razão Social: ");
            String razao = sc.nextLine();
            System.out.print("Representante: ");
            String representante = sc.nextLine();
            System.out.print("ID Endereço: ");
            int idEnd = sc.nextInt();

            EstacionamentoModel est = new EstacionamentoModel(0, nome, cnpj, fantasia, razao, representante, idEnd);
            controle.adicionarEstacionamento(est);

            System.out.println("Estacionamento cadastrado com sucesso!");

            System.out.println("\n=== Lista de Estacionamentos ===");
            for (EstacionamentoModel e : controle.listarEstacionamentos()) {
                System.out.println(e.getIdEstaPk() + " - " + e.getEstaNome() + " (" + e.getEstaCnpj() + ")");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
