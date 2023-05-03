
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
	
	private Scanner sc = new Scanner(System.in);
	private Crud crud = new Crud();
	
	public void MenuPrincipal() throws IOException {
		System.out.print("\nBem-vindo ao RPGFinder\n\n"
						+ "1 - Criar nova criatura.\n"
						+ "2 - Alterar criatura.\n"
						+ "3 - Visualizar criatura.\n"
						+ "4 - Deletar criatura\n"
						+ "5 - Sair\n\n"
						+ "Digite a opção: ");
		
		int escolha = sc.nextInt();
		
		while(true) {
			switch(escolha) {
				case 1:
					Selecionar("Menu Criação", 1);
					break;
				case 2:
					Selecionar("Menu Alterar", 2);
					break;
				case 3:
					Selecionar("Menu Visualizar", 3);
					break;
				case 4:
					Selecionar("Menu Deletar", 4);
					break;
				case 5:
					System.exit(0);
				default:
					System.out.println("Escolha inválida, tente novamente.");
			}
		}
		
	}
	
	public void Selecionar(String titulo, int v) throws IOException {
		System.out.print("\n"+ titulo + "\n" +
						"1 - Continuar\n"
						+ "2 - Voltar\n"
						+ "Escolha a opção: ");
		
		int escolha = sc.nextInt();
		
		switch(escolha) {
			case 1:
				switch(v) {
					case 1:
						MenuCriar();
						break;
					case 2:
						MenuAlterar();
						break;
					case 3:
						MenuVisualizar();
						break;
					case 4:
						MenuDeletar();
						break;
				}
				break;
				
			case 2:
				MenuPrincipal();
				break;
		}	
	}
	
	
	private void MenuCriar() {
		
		int id, hp, mp, qtAtq;
		String nome, tipo;
		String[] ataques;
		
		System.out.print("\nID: ");
		id = sc.nextInt();
		
		System.out.print("HP: ");
		hp = sc.nextInt();
		
		System.out.print("MP: ");
		mp = sc.nextInt();
		
		System.out.print("Nome: ");
		nome = sc.next();
		
		System.out.print("Tipo: ");
		tipo = sc.next();
		
		System.out.print("Quantidade de ataques: ");
		qtAtq = sc.nextInt();
		ataques = new String[qtAtq];
		
		for (int i = 0; i < qtAtq; ++i) {
			System.out.print("Nome do ataque " + (i+1) + ": ");
			ataques[i] = sc.next();
		}
		
		try {
			crud.CreateCreature(id, hp, mp, nome, tipo, ataques);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void MenuAlterar() throws IOException {
		
		crud.ReadCreature();
		
		System.out.print("\nO que deseja alterar?\n"
				+ "1 - HP\n"
				+ "2 - MP\n"
				+ "3 - Nome\n"
				+ "4 - Tipo\n"
				+ "5 - Ataques\n"
				+ "Digite a opção: ");
		int opcaoAlterar = sc.nextInt();
		
		System.out.print("\nDigite a alteração: ");
		sc.nextLine();
		String newValue = sc.nextLine();
		
		crud.AlterCreature(Crud.idSelecionar, opcaoAlterar, newValue);
		
	}
	
	private void MenuVisualizar() throws FileNotFoundException {
		crud.ReadCreature();
	}
	
	private void MenuDeletar() throws IOException {
		System.out.print("\nID para deletar: ");
		int idDeletar = sc.nextInt();
		crud.DeleteCreature(idDeletar);
	}
	
}
