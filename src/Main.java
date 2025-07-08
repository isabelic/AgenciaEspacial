import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean sair = false;

        while (!sair) {
            System.out.println("\n=== AGÊNCIA ESPACIAL INTERPLANETÁRIA ===");
            System.out.println("1. Gerenciar Agentes");
            System.out.println("2. Gerenciar Missões");
            System.out.println("3. Participação em Missões");
            System.out.println("4. Consultas e Relatórios");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            String opc = sc.nextLine();

            switch (opc) {
                case "1": gerenciarAgentes(); break;
                case "2": gerenciarMissoes(); break;
                case "3": participacaoMissoes(); break;
                case "4": consultasRelatorios(); break;
                case "5":
                    System.out.println("Obrigado por participar! Até a próxima missão espacial!");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    // --- Gerenciar Agentes ---
    private static void gerenciarAgentes() {
        while (true) {
            System.out.println("\n--- Gerenciar Agentes ---");
            System.out.println("1. Criar Agente");
            System.out.println("2. Atualizar Agente");
            System.out.println("3. Remover Agente");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha: ");
            String opc = sc.nextLine();

            switch (opc) {
                case "1": criarAgente(); break;
                case "2": atualizarAgente(); break;
                case "3": removerAgente(); break;
                case "4": return;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private static void criarAgente() {
        System.out.print("Nome do agente: ");
        String nome = sc.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }

        System.out.println("Classe do agente:");
        System.out.println("1 - Piloto");
        System.out.println("2 - Engenheiro");
        System.out.println("3 - Cientista");
        System.out.print("Escolha a classe (1-3): ");
        String cls = sc.nextLine();

        int nivel;
        try {
            System.out.print("Nível inicial (>=1): ");
            nivel = Integer.parseInt(sc.nextLine());
            if (nivel < 1) {
                System.out.println("Nível deve ser >= 1.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Nível inválido.");
            return;
        }

        int atributoEspecifico;
        try {
            System.out.print("Valor do atributo específico (>0): ");
            atributoEspecifico = Integer.parseInt(sc.nextLine());
            if (atributoEspecifico <= 0) {
                System.out.println("Atributo específico deve ser > 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Atributo específico inválido.");
            return;
        }

        Agente agente = null;
        switch (cls) {
            case "1":
                agente = new Piloto(nome, nivel, atributoEspecifico);
                break;
            case "2":
                agente = new Engenheiro(nome, nivel, atributoEspecifico);
                break;
            case "3":
                agente = new Cientista(nome, nivel, atributoEspecifico);
                break;
            default:
                System.out.println("Classe inválida.");
                return;
        }

        Gerenciamento.adicionarAgente(agente);
    }

    private static void atualizarAgente() {
        System.out.print("Nome do agente para atualizar: ");
        String nome = sc.nextLine().trim();
        Agente a = Gerenciamento.buscarAgentePorNomeExato(nome);
        if (a == null) {
            System.out.println("Agente não encontrado.");
            return;
        }

        System.out.println("Campo para alterar:");
        System.out.println("1 - nível");
        System.out.println("2 - vida");
        System.out.print("Escolha: ");
        String campo = sc.nextLine();

        String campoStr = null;
        switch (campo) {
            case "1": campoStr = "nivel"; break;
            case "2": campoStr = "vida"; break;
            default:
                System.out.println("Campo inválido.");
                return;
        }

        System.out.print("Novo valor: ");
        int novoValor;
        try {
            novoValor = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
            return;
        }

        Gerenciamento.atualizarAgente(nome, campoStr, novoValor);
    }

    private static void removerAgente() {
        System.out.print("Nome do agente para remover: ");
        String nome = sc.nextLine().trim();
        Gerenciamento.removerAgente(nome);
    }

    // --- Gerenciar Missões ---
    private static void gerenciarMissoes() {
        while (true) {
            System.out.println("\n--- Gerenciar Missões ---");
            System.out.println("1. Criar Missão");
            System.out.println("2. Atualizar Missão");
            System.out.println("3. Remover Missão");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha: ");
            String opc = sc.nextLine();

            switch (opc) {
                case "1": criarMissao(); break;
                case "2": atualizarMissao(); break;
                case "3": removerMissao(); break;
                case "4": return;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private static void criarMissao() {
        System.out.print("Nome da missão: ");
        String nome = sc.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }

        int dificuldade;
        try {
            System.out.print("Dificuldade (1-Fácil, 2-Médio, 3-Difícil): ");
            dificuldade = Integer.parseInt(sc.nextLine());
            if (dificuldade < 1 || dificuldade > 3) {
                System.out.println("Dificuldade inválida.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Dificuldade inválida.");
            return;
        }

        Missao m = new Missao(nome, dificuldade);
        Gerenciamento.adicionarMissao(m);
    }

    private static void atualizarMissao() {
        System.out.print("ID da missão para atualizar: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        System.out.println("Campo para alterar:");
        System.out.println("1 - nome");
        System.out.println("2 - dificuldade");
        System.out.print("Escolha: ");
        String campo = sc.nextLine();

        String campoStr = null;
        switch (campo) {
            case "1": campoStr = "nome"; break;
            case "2": campoStr = "dificuldade"; break;
            default:
                System.out.println("Campo inválido.");
                return;
        }

        System.out.print("Novo valor: ");
        String novoValor = sc.nextLine();

        Gerenciamento.atualizarMissao(id, campoStr, novoValor);
    }

    private static void removerMissao() {
        System.out.print("ID da missão para remover: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
        Gerenciamento.removerMissao(id);
    }

    // --- Participação em Missões ---
    private static void participacaoMissoes() {
        while (true) {
            System.out.println("\n--- Participação em Missões ---");
            System.out.println("1. Adicionar Agente a Missão");
            System.out.println("2. Remover Agente de Missão");
            System.out.println("3. Iniciar Missão");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha: ");
            String opc = sc.nextLine();

            switch (opc) {
                case "1": adicionarAgenteMissao(); break;
                case "2": removerAgenteMissao(); break;
                case "3": iniciarMissao(); break;
                case "4": return;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private static void adicionarAgenteMissao() {
        System.out.print("ID da missão: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
        Missao m = Gerenciamento.metodoPorID(id);
        if (m == null) {
            System.out.println("Missão não encontrada.");
            return;
        }

        System.out.print("Nome do agente: ");
        String nomeAgente = sc.nextLine().trim();
        Agente agente = Gerenciamento.buscarAgentePorNomeExato(nomeAgente);
        if (agente == null) {
            System.out.println("Agente não encontrado.");
            return;
        }
        if (!Agente.isStatus()) {
            System.out.println("Agente está morto e não pode participar.");
            return;
        }

        if (m.getMissaoAgente().contains(agente)) {
            System.out.println("Agente já está na missão.");
            return;
        }

        boolean ok = m.adicionarAgente(agente);
        if (ok) {
            System.out.println("Agente '" + agente.getNome() + "' adicionado à missão '" + m.getNome() + "'.");
        }
    }

    private static void removerAgenteMissao() {
        System.out.print("ID da missão: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
        Missao m = Gerenciamento.metodoPorID(id);
        if (m == null) {
            System.out.println("Missão não encontrada.");
            return;
        }

        System.out.print("Nome do agente: ");
        String nomeAgente = sc.nextLine().trim();
        Agente agente = Gerenciamento.buscarAgentePorNomeExato(nomeAgente);
        if (agente == null) {
            System.out.println("Agente não encontrado.");
            return;
        }

        boolean ok = m.removerAgente(agente);
        if (ok) {
            System.out.println("Agente '" + agente.getNome() + "' removido da missão '" + m.getNome() + "'.");
        } else {
            System.out.println("Agente não está na missão ou não pode ser removido.");
        }
    }

    private static void iniciarMissao() {
        System.out.print("ID da missão para iniciar: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Missao m = Gerenciamento.metodoPorID(id);
        if (m == null) {
            System.out.println("Missão não encontrada.");
            return;
        }

        if (!m.QuantidadeMinimaParticipantes()) {
            System.out.println("Número insuficiente de participantes para iniciar a missão.");
            return;
        }

        m.iniciarMissoes();
    }

    // --- Consultas e Relatórios ---
    private static void consultasRelatorios() {
        while (true) {
            System.out.println("\n--- Consultas e Relatórios ---");
            System.out.println("1. Listar Agentes");
            System.out.println("2. Listar Missões");
            System.out.println("3. Buscar Agente por Nome");
            System.out.println("4. Exibir Inventário de Agente");
            System.out.println("5. Agrupar Agentes por Nível");
            System.out.println("6. Agrupar Missões por Dificuldade");
            System.out.println("7. Voltar ao Menu Principal");
            System.out.print("Escolha: ");
            String opc = sc.nextLine();

            switch (opc) {
                case "1":
                    Gerenciamento.listarAgentes();
                    break;
                case "2":
                    System.out.print("Filtrar missões ");
                    String filtro = sc.nextLine();
                    Gerenciamento.listarMissoes();

                    break;
                case "3":
                    System.out.print("Digite termo para busca de agente: ");
                    String termo = sc.nextLine();
                    ArrayList<Agente> encontrados = Gerenciamento.buscarAgentesPorNome(termo);
                    if (encontrados.isEmpty()) {
                        System.out.println("Nenhum agente encontrado.");
                    } else {
                        for (Agente a : encontrados) {
                            System.out.println(a);
                        }
                    }
                    break;
                case "4":
                    System.out.print("Nome do agente para exibir inventário: ");
                    String nome = sc.nextLine();
                    Agente ag = Gerenciamento.buscarAgentePorNomeExato(nome);
                    if (ag == null) {
                        System.out.println("Agente não encontrado.");
                    } else {
                        ag.listarInventario();
                    }
                    break;
                case "5":
                    Gerenciamento.agruparAgentesPorNivel();
                    break;
                case "6":
                    Gerenciamento.agruparMissoesPorDificuldade();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
