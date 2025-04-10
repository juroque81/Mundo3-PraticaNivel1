package model;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
        boolean executando = true;

        while (executando) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("Escolha uma opção:");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica:");
                    String tipo = scanner.nextLine();
                    System.out.println("Digite o id:");
                    int id = scanner.nextInt(); scanner.nextLine();
                    System.out.println("Nome:");
                    String nome = scanner.nextLine();

                    if (tipo.equalsIgnoreCase("F")) {
                        System.out.println("CPF:");
                        String cpf = scanner.nextLine();
                        System.out.println("Idade:");
                        int idade = scanner.nextInt(); scanner.nextLine();
                        repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                    } else if (tipo.equalsIgnoreCase("J")) {
                        System.out.println("CNPJ:");
                        String cnpj = scanner.nextLine();
                        repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                    }
                }
                case 2 -> {
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica:");
                    String tipo = scanner.nextLine();
                    System.out.println("Digite o id:");
                    int id = scanner.nextInt(); scanner.nextLine();

                    if (tipo.equalsIgnoreCase("F")) {
                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) {
                            System.out.println("Dados atuais:");
                            pf.exibir();
                            System.out.println("Novo nome:");
                            String nome = scanner.nextLine();
                            System.out.println("Novo CPF:");
                            String cpf = scanner.nextLine();
                            System.out.println("Nova idade:");
                            int idade = scanner.nextInt(); scanner.nextLine();
                            repoFisica.alterar(new PessoaFisica(id, nome, cpf, idade));
                        } else {
                            System.out.println("Pessoa Física não encontrada.");
                        }
                    } else if (tipo.equalsIgnoreCase("J")) {
                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) {
                            System.out.println("Dados atuais:");
                            pj.exibir();
                            System.out.println("Novo nome:");
                            String nome = scanner.nextLine();
                            System.out.println("Novo CNPJ:");
                            String cnpj = scanner.nextLine();
                            repoJuridica.alterar(new PessoaJuridica(id, nome, cnpj));
                        } else {
                            System.out.println("Pessoa Jurídica não encontrada.");
                        }
                    }
                }
                case 3 -> {
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica:");
                    String tipo = scanner.nextLine();
                    System.out.println("Digite o id:");
                    int id = scanner.nextInt(); scanner.nextLine();

                    if (tipo.equalsIgnoreCase("F")) {
                        repoFisica.excluir(id);
                    } else if (tipo.equalsIgnoreCase("J")) {
                        repoJuridica.excluir(id);
                    }
                }
                case 4 -> {
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica:");
                    String tipo = scanner.nextLine();
                    System.out.println("Digite o id:");
                    int id = scanner.nextInt(); scanner.nextLine();

                    if (tipo.equalsIgnoreCase("F")) {
                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) pf.exibir();
                        else System.out.println("Pessoa Física não encontrada.");
                    } else if (tipo.equalsIgnoreCase("J")) {
                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) pj.exibir();
                        else System.out.println("Pessoa Jurídica não encontrada.");
                    }
                }
                case 5 -> {
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica:");
                    String tipo = scanner.nextLine();

                    if (tipo.equalsIgnoreCase("F")) {
                        for (PessoaFisica pf : repoFisica.obterTodos()) {
                            pf.exibir();
                        }
                    } else if (tipo.equalsIgnoreCase("J")) {
                        for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                            pj.exibir();
                        }
                    }
                }
                case 6 -> {
                    try {
                        System.out.println("Prefixo do arquivo:");
                        String prefixo = scanner.nextLine();
                        repoFisica.persistir(prefixo + ".fisica.bin");
                        repoJuridica.persistir(prefixo + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar: " + e.getMessage());
                    }
                }
                case 7 -> {
                    try {
                        System.out.println("Prefixo do arquivo:");
                        String prefixo = scanner.nextLine();
                        repoFisica.recuperar(prefixo + ".fisica.bin");
                        repoJuridica.recuperar(prefixo + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao recuperar: " + e.getMessage());
                    }
                }
                case 0 -> {
                    System.out.println("Sair");
                    executando = false;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}