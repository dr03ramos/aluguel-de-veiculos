package com.diogo.aluguel.persistencia;

import java.util.ArrayList;
import java.util.Scanner;

public class Funcoes {
    public static void opcaoDeSair(Scanner scanner) {
        System.out.println();
        System.out.print("Você deseja sair? (s/n) ");

        String opcao = scanner.nextLine();

        if (opcao.equals("s")) {
            System.out.println("Tchau!");
            scanner.close();
            System.exit(0);
        } else {
            System.out.println("Voltando ao menu...");
        }
    }

    static int criador_de_ids(ArrayList<?> lista) {
        int id;
        try {
            id = lista.size() + 1;
        } catch (Exception e) {
            id = 1;
        }
        return id;
    }
}
