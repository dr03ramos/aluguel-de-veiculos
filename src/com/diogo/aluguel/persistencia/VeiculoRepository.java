package com.diogo.aluguel.persistencia;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

import com.diogo.aluguel.model.*;

public class VeiculoRepository {
    private ArrayList<Veiculo> veiculos;

    public VeiculoRepository() {
        veiculos = new ArrayList<>();
    }

    public void add(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public Boolean criar_veiculos(Scanner scanner, Usuario dono) {
        int id = Funcoes.criador_de_ids(veiculos);
        LocalDateTime horario_cadastro = LocalDateTime.now();

        System.out.println("\nCadastrar novo veículo");
        System.out.print("Digite o modelo do veículo: ");
        String modelo = scanner.nextLine();
        System.out.print("Digite o ano do veículo: ");
        String anoString = scanner.nextLine();
        int ano = Integer.parseInt(anoString);
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine();
        System.out.print("Deseja disponibilizar para aluguel? (s/n): ");
        String disponibilidade = scanner.nextLine();
        if (disponibilidade.equals("s")) {
            disponibilidade = "true";
        } else {
            disponibilidade = "false";
        }

        System.out.println("Selecione o tipo de veículo: ");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");
        System.out.println("3 - Furgão");
        String tipo = scanner.nextLine();
        int tipoInt = Integer.parseInt(tipo);
        if (tipoInt == 1) {
            System.out.println("O carro possui multimídia? (s/n)");
            String multimidia = scanner.nextLine();
            boolean multimidia_bool = false;
            if (multimidia.equals("s")) {
                multimidia_bool = true;
            }
            System.out.print("Digite a cor do veículo: ");
            String cor = scanner.nextLine();
            Carro novoVeiculo = new Carro(id, modelo, ano, placa, dono, horario_cadastro, multimidia_bool, cor);
            novoVeiculo.setDisponivel_para_aluguel(Boolean.valueOf(disponibilidade));
            veiculos.add(novoVeiculo);
        } else if (tipoInt == 2) {
            System.out.println("A moto possui baú? (s/n)");
            String bau = scanner.nextLine();
            boolean bau_bool = false;
            if (bau.equals("s")) {
                bau_bool = true;
            }
            Moto novoVeiculo = new Moto(id, modelo, ano, placa, dono, horario_cadastro, bau_bool);
            novoVeiculo.setDisponivel_para_aluguel(Boolean.valueOf(disponibilidade));
            veiculos.add(novoVeiculo);
        } else if (tipoInt == 3) {
            System.out.println("O carro possui multimídia? (s/n)");
            String multimidia = scanner.nextLine();
            boolean multimidia_bool = false;
            if (multimidia.equals("s")) {
                multimidia_bool = true;
            }
            System.out.print("Digite a cor do veículo: ");
            String cor = scanner.nextLine();
            System.out.println("Qual o espaço interno do furgão? (em litros)");
            int espaco_interno = scanner.nextInt();

            Furgao novoVeiculo = new Furgao(id, modelo, ano, placa, dono, horario_cadastro, multimidia_bool, cor, espaco_interno);
            novoVeiculo.setDisponivel_para_aluguel(Boolean.valueOf(disponibilidade));
            veiculos.add(novoVeiculo);
        } else {
            System.out.println("Tipo de veículo inválido.");
            scanner.close();
            return false;
        }

        return true;
    }

    public void editar_veiculo(Scanner scanner, Veiculo veiculo) {
        System.out.println("Editar veículo");

        int opcao = -1;

        do {
            // perguntando o que editar
            System.out.println("1. Modelo");
            System.out.println("2. Placa");
            System.out.println("3. Disponibilidade para aluguel");
            if (veiculo instanceof Carro || veiculo instanceof Furgao) {
                System.out.println("4. Multimídia");
                System.out.println("5. Cor");
            } if (veiculo instanceof Moto) {
                System.out.println("4. Baú");
            } if (veiculo instanceof Furgao) {
                System.out.println("6. Espaço interno");
            }
            System.out.println("9. Excluir veículo");
            System.out.println("0. Sair");
            String opcaoString = scanner.nextLine();
            opcao = Integer.parseInt(opcaoString);

            // editando
            if (opcao == 1) {
                System.out.print("Digite o novo modelo: ");
                String modelo = scanner.nextLine();
                veiculo.setModelo(modelo);
            } else if (opcao == 2) {
                System.out.print("Digite a nova placa: ");
                String placa = scanner.nextLine();
                veiculo.setPlaca(placa);
            } else if (opcao == 3 && veiculo.getDisponivel_para_aluguel()) {
                System.out.println("Deseja desativar a disponibilidade para aluguel? (s/n)");
                String opcaoString2 = scanner.nextLine();
                if (opcaoString2.equals("s")) {
                    veiculo.setDisponivel_para_aluguel(false);
                }
            } else if (opcao == 3 && !veiculo.getDisponivel_para_aluguel()) {
                System.out.println("Deseja ativar a disponibilidade para aluguel? (s/n)");
                String opcaoString2 = scanner.nextLine();
                if (opcaoString2.equals("s")) {
                    veiculo.setDisponivel_para_aluguel(true);
                }
            } else if (opcao == 4 && (veiculo instanceof Carro || veiculo instanceof Furgao)) {
                System.out.println("O carro possui multimídia? (s/n)");
                String multimidia = scanner.nextLine();
                boolean multimidia_bool = false;
                if (multimidia.equals("s")) {
                    multimidia_bool = true;
                }
                ((Carro) veiculo).setMultimidia(multimidia_bool);
            } else if (opcao == 5 && (veiculo instanceof Carro || veiculo instanceof Furgao)) {
                System.out.print("Digite a nova cor: ");
                String cor = scanner.nextLine();
                    if (veiculo instanceof Carro) {
                        ((Carro) veiculo).setCor(cor);
                    } else if (veiculo instanceof Furgao) {
                        ((Furgao) veiculo).setCor(cor);
                    }
            } else if (opcao == 4 && veiculo instanceof Moto) {
                System.out.println("A moto possui baú? (s/n)");
                String bau = scanner.nextLine();
                boolean bau_bool = false;
                if (bau.equals("s")) {
                    bau_bool = true;
                }
                ((Moto) veiculo).setBau(bau_bool);
            } else if (opcao == 6 && veiculo instanceof Furgao) {
                System.out.println("Qual o espaço interno do furgão? (em litros)");
                int espaco_interno = scanner.nextInt();
                ((Furgao) veiculo).setEspaco_interno(espaco_interno);
            } else if (opcao == 9) {
                System.out.println("Excluir veículo");
                System.out.println("Tem certeza? (s/n)");
                String opcaoString2 = scanner.nextLine();
                if (opcaoString2.equals("s")) {
                    veiculos.remove(veiculo);
                    System.out.println("Veículo excluído");
                    opcao = 0;
                } else {
                    System.out.println("Voltando ao menu...");
                    opcao = 0;
                }
            } else if (opcao != 0) {
                System.out.println("Opção inválida");
            }

        } while (opcao != 0);

    }

    public ArrayList<Veiculo> veiculos_do_proprietario(Usuario proprietario) {
        ArrayList<Veiculo> veiculos_do_proprietario = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getProprietario().equals(proprietario)) {
                veiculos_do_proprietario.add(veiculo);
            }
        }
        return veiculos_do_proprietario;
    }

    public ArrayList<Veiculo> veiculos_locados(Usuario locatario) {
        ArrayList<Veiculo> veiculos_locados = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getLocatario().equals(locatario)) {
                veiculos_locados.add(veiculo);
            }
        }
        return veiculos_locados;
    }

    public ArrayList<Veiculo> veiculos_anunciados() {
        ArrayList<Veiculo> veiculos_anunciados = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getDisponivel_para_aluguel() && veiculo.getLocatario() == null) {
                veiculos_anunciados.add(veiculo);
            }
        }
        return veiculos_anunciados;
    }

    public void alugar_veiculo(Veiculo veiculo, Usuario locatario) {
        veiculo.setLocatario(locatario);
    }

    public void devolver_veiculo(Veiculo veiculo) {
        veiculo.setLocatario(null);
    }

    public void informacoes_veiculo(Veiculo veiculo) {
        System.out.println("Informações do veículo");
        System.out.println("Modelo: " + veiculo.getModelo());
        System.out.println("Ano: " + veiculo.getAno());
        System.out.println("Placa: " + veiculo.getPlaca());
        System.out.println("Proprietário: " + veiculo.getProprietario().getNome());
        if (veiculo.getLocatario() != null) {
            System.out.println("Locatário: " + veiculo.getLocatario().getNome());
        }
        System.out.println("Disponibilidade para aluguel: " + veiculo.getDisponivel_para_aluguel_string());
        if (veiculo instanceof Carro) {
            if (((Carro) veiculo).getMultimidia()) {
                System.out.println("Multimídia: Sim");
            } else {
                System.out.println("Multimídia: Não");
            }
            System.out.println("Cor: " + ((Carro) veiculo).getCor());
        } else if (veiculo instanceof Moto) {
            if (((Moto) veiculo).getBau()) {
                System.out.println("Baú: Sim");
            } else {
                System.out.println("Baú: Não");
            }
        } else if (veiculo instanceof Furgao) {
            if (((Furgao) veiculo).getMultimidia()) {
                System.out.println("Multimídia: Sim");
            } else {
                System.out.println("Multimídia: Não");
            }
            System.out.println("Cor: " + ((Furgao) veiculo).getCor());
            System.out.println("Espaço interno: " + ((Furgao) veiculo).getEspaco_interno());
        }
    }

    public ArrayList<Veiculo> veiculos_alugados(Usuario locatario) {
        ArrayList<Veiculo> veiculos_alugados = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getLocatario() != null && veiculo.getLocatario().equals(locatario)) {
                veiculos_alugados.add(veiculo);
            }
        }
        return veiculos_alugados;
    }
}