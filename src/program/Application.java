package program;

import java.util.Locale;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		int qntClientes;
		int qntTipoUm = 0;
		int qntTipoDois = 0;
		int mediaMinutos = 0;
		double receitaTotal = 0;
		double porcentagem;
		double menorConta = 0.0;
		String nomeMenorConta = null;
		String telefoneMenorConta = null;

		// Leitura dos dados
		System.out.print("Informe a quantidade de clientes: ");
		qntClientes = sc.nextInt();

		String[] nomes = new String[qntClientes];
		String[] telefone = new String[qntClientes];
		int[] tipo = new int[qntClientes];
		int[] minutos = new int[qntClientes];
		double[] valorConta = new double[qntClientes];

		for (int i = 0; i < qntClientes; i++) {
			System.out.println("Dados do " + (i + 1) + "o. cliente: ");

			sc.nextLine();
			System.out.print("Nome: ");
			nomes[i] = sc.nextLine();

			System.out.print("Telefone: ");
			telefone[i] = sc.next();

			System.out.print("Tipo: ");
			tipo[i] = sc.nextInt();

			System.out.print("Minutos: ");
			minutos[i] = sc.nextInt();

			System.out.println("");
		}

		System.out.println("Infome o preco basico e excedente de cada tipo de conta: ");

		double[][] precos = new double[3][2];

		for (int i = 0; i < 3; i++) {
			System.out.print("Tipo " + i + ": ");
			for (int j = 0; j < 2; j++) {
				precos[i][j] = sc.nextDouble();
			}
		}

		// calculo e armazenamento do valor da conta
		for (int i = 0; i < qntClientes; i++) {
			for (int j = 0; j < 1; j++) {
				if (minutos[i] <= 90) {
					valorConta[i] = precos[tipo[i]][0];
				} else {
					valorConta[i] = precos[tipo[i]][0] + (precos[tipo[i]][1] * (minutos[i] - 90));
				}
			}
		}

		// Menu de opcoes

		int opcao = 0;
		do {
			System.out.println("");
			System.out.println("MENU DE OPÇÕES:");
			System.out.println("1) Relatorio de clientes");
			System.out.println("2) A receita total");
			System.out.println("3) Conta foi mais barata");
			System.out.println("4) Consumo medio de minutos por clientes de conta tipo 1.");
			System.out.println("5) Clientes que consumiram acima de 120 pulsos");
			System.out.println("6) A porcentagem de clientes tipo 2");
			System.out.println("7) Sair");
			System.out.print("Informe uma opcao: ");
			opcao = sc.nextInt();
			System.out.println("");

			switch (opcao) {
			case 1:
				for (int i = 0; i < qntClientes; i++) {
					System.out.println(nomes[i] + ", " + telefone[i] + ", tipo " + tipo[i] + ", Minutos: " + minutos[i]
							+ ", Conta = R$ " + String.format("%.2f", valorConta[i]));
				}
				break;
			case 2:
				for (int i = 0; i < qntClientes; i++) {
					receitaTotal += valorConta[i];
				}
				System.out.println("Receita total = R$ " + String.format("%.2f", receitaTotal));
				break;
			case 3:
				menorConta = valorConta[0];
				for (int i = 1; i < qntClientes; i++) {
					if (valorConta[i] < menorConta) {
						menorConta = valorConta[i];
						nomeMenorConta = nomes[i];
						telefoneMenorConta = telefone[i];
					}
				}
				System.out.println("Cliente com menor conta: " + nomeMenorConta + ", telefone: " + telefoneMenorConta);
				break;
			case 4:
				for (int i = 0; i < qntClientes; i++) {
					if (tipo[i] == 1) {
						qntTipoUm++;
						mediaMinutos += minutos[i];
					}
				}
				System.out.println("Media de minutos consumidos: " + mediaMinutos / qntTipoUm + " minutos");
				break;
			case 5:
				System.out.print("Clientes que consumiram mais de 120 minutos: ");
				for (int i = 0; i < qntClientes; i++) {
					if (minutos[i] > 120) {
						System.out.print(nomes[i] + " / ");
					}
				}
				System.out.println("");
				break;
			case 6:
				System.out.print("Porcentagem de clientes de conta tipo 2: ");
				for (int i = 0; i < qntClientes; i++) {
					if (tipo[i] == 2) {
						qntTipoDois++;
					}
				}
				porcentagem = (double) qntTipoDois / qntClientes * 100;
				System.out.printf("%.1f%%\n", porcentagem);
				break;
			}

		} while (opcao != 7);

		System.out.println("FIM DO PROGRAMA!");
		sc.close();
	}
}