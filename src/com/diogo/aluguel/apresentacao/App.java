package com.diogo.aluguel.apresentacao;

import java.util.ArrayList;
import java.util.Scanner;

import com.diogo.aluguel.model.Veiculo;
import com.diogo.aluguel.persistencia.*;

public class App {

    static UsuarioRepository usuarioRepository = new UsuarioRepository();
    static VeiculoRepository veiculoRepository = new VeiculoRepository();

    public static void main(String[] args) {
        // app loop
        Scanner sc = new Scanner(System.in);
        Boolean i = true;
        do {
            System.out.println();
            if (usuarioRepository.getUsuariologado() == null) {
                i = menu_deslogado(sc);
            } else {
                i = menu_logado(sc);
            }

        } while (i);
        sc.close();
    }

    public static Boolean menu_deslogado(Scanner scanner) {
        System.out.println("Tela inicial");

        System.out.println("1. Cadastro de novo usuário");
        System.out.println("2. Login");
        System.out.println("3. Sair");

        // perguntando a opção
        String opcao = readLine(scanner);

        System.out.println();

        if (opcao.equals("1")) {
            System.out.println("Cadastro de novo usuário");
            System.out.print("Digite o nome de usuário: ");
            String nome = readLine(scanner);
            System.out.print("Digite o e-mail: ");
            String email = readLine(scanner);
            System.out.print("Digite a senha: ");
            String senha = readLine(scanner);

            usuarioRepository.addUsuario(nome, email, senha);
            usuarioRepository.login(email, senha);
        } else if (opcao.equals("2")) {
            System.out.println("Login");
            System.out.print("Digite o e-mail: ");
            String email = readLine(scanner);
            System.out.print("Digite a senha: ");
            String senha = readLine(scanner);

            Boolean loginStatus = usuarioRepository.login(email, senha);
            if (!loginStatus) {
                System.out.println("E-mail ou senha incorretos");
                return true;
            }
        } else if (opcao.equals("3")) {
            return false;
        } else {
            System.out.println("Opção inválida");
        }

        // conectado! avisar
        System.out.println("\nConectado como " + usuarioRepository.getUsuariologado().getNome() + ". Boas vindas!");
        return true;
    }

    public static Boolean menu_logado(Scanner scanner) {
        System.out.println("Menu:");
        System.out.println("1. Meus veículos");
        System.out.println("2. Encontrar veículos");
        System.out.println("3. Meus alugados");
        System.out.println("4. Logout");

        // perguntando a opção
        String opcao = readLine(scanner);

        System.out.println();

        if (opcao.equals("4")) {
            System.out.println("Logout");
            usuarioRepository.logout();
        } else if (opcao.equals("1")) {
            System.out.println("Meus veículos");
            System.out.println();
            ArrayList<Veiculo> veiculos_do_usuario = veiculoRepository
                    .veiculos_do_proprietario(usuarioRepository.getUsuariologado());
            if (veiculos_do_usuario.size() == 0) {
                System.out.println("Você não tem veículos cadastrados");
            } else {
                System.out.println("Veículos cadastrados:");
                for (Veiculo veiculo : veiculos_do_usuario) {
                    System.out.println(veiculo.getId() + " - " + veiculo.getModelo() + " - "
                            + veiculo.getDisponivel_para_aluguel_string() + " - proprietário: "
                            + veiculo.getProprietario().getNome());
                }
                System.out.println();
            }

            System.out.println("Para cadastrar um novo veículo, digite 0.");
            System.out.println("Para voltar ao menu, pressione enter.");

            // perguntando a opção
            String opcao2 = readLine(scanner);

            System.out.println();
            if (opcao2.equals("novo") || opcao2.equals("0")) {
                veiculoRepository.criar_veiculos(scanner, usuarioRepository.getUsuariologado());
                System.out.println("Veículo cadastrado com sucesso!");
            } else if (opcao2.equals("")) {
                return true;
            } else {
                veiculoRepository.informacoes_veiculo(veiculos_do_usuario.get(Integer.parseInt(opcao2) - 1));
                System.out.println("Deseja editar este veículo? (s/n)");
                if (readLine(scanner).equals("s")) {
                    veiculoRepository.editar_veiculo(scanner, veiculos_do_usuario.get(Integer.parseInt(opcao2) - 1));
                    System.out.println("Veículo editado com sucesso!");
                }

            }

        } else if (opcao.equals("2")) {
            System.out.println("Encontrar veículos");
            // lista de veiculos_anunciados

            ArrayList<Veiculo> veiculos_anunciados = veiculoRepository.veiculos_anunciados();

            if (veiculos_anunciados.size() == 0) {
                System.out.println("Não há veículos anunciados");
            } else {
                System.out.println("Veículos anunciados:");
                for (Veiculo veiculo : veiculos_anunciados) {
                    System.out.println(veiculo.getId() + " - " + veiculo.getModelo() + " - "
                            + veiculo.getDisponivel_para_aluguel_string());
                }
                System.out.println();
                System.out.println("Para alugar um veículo, digite o id dele.");
                System.out.println("Para voltar ao menu, digite 0.");

                // perguntando a opção
                String opcao2 = readLine(scanner);
                if (opcao2.equals("0")) {
                    return true;
                }
                Veiculo veiculo = veiculos_anunciados.get(Integer.parseInt(opcao2) - 1);

                System.out.println(
                        "Você deseja alugar o veículo " + veiculo.getId() + " - " + veiculo.getModelo() + "? (s/n)");

                if (readLine(scanner).equals("s")) {
                    if (veiculo.getProprietario().getId() == usuarioRepository.getUsuariologado().getId()) {
                        System.out.println("Você não pode alugar o seu próprio veículo");
                        return true;
                    } else {
                        veiculoRepository.alugar_veiculo(veiculo, usuarioRepository.getUsuariologado());
                        System.out.println("Veículo alugado com sucesso!");
                    }
                } else {
                    System.out.println("Veículo não alugado");
                }
            }
        } else if (opcao.equals("3")) {
            System.out.println("Meus alugados");
            ArrayList<Veiculo> veiculos_alugados = veiculoRepository
                    .veiculos_alugados(usuarioRepository.getUsuariologado());

            if (veiculos_alugados.size() == 0) {
                System.out.println("Você não tem veículos alugados");
            } else {
                System.out.println();
                System.out.println("Veículos alugados:");
                for (Veiculo veiculo : veiculos_alugados) {
                    System.out.println(veiculo.getId() + " - " + veiculo.getModelo() + " - "
                            + veiculo.getDisponivel_para_aluguel_string());
                }

            }
            System.out.println();
            System.out.println("Para devolver um veículo, digite o id dele.");
            System.out.println("Para voltar ao menu, digite 0.");

            // perguntando a opção
            String opcao2 = readLine(scanner);

            System.out.println();
            if (!opcao2.equals("0")) {
                Veiculo veiculo = veiculos_alugados.get(Integer.parseInt(opcao2) - 1);
                System.out.println(
                        "Você deseja devolver o veículo " + veiculo.getId() + " - " + veiculo.getModelo() + "? (s/n)");

                if (readLine(scanner).equals("s")) {
                    veiculoRepository.devolver_veiculo(veiculo);
                    System.out.println("Veículo devolvido com sucesso!");
                } else {
                    System.out.println("Veículo não devolvido");
                }
            }

        } else {
            System.out.println("Opção inválida");
        }
        return true;
    }

    // classe para ler teclado
    private static String readLine(Scanner x) {
        String line = null;
        if (x.hasNextLine()) {
            line = x.nextLine();
        }
        return line;
    }

}