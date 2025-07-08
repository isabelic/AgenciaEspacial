

import java.util.ArrayList;

public class Gerenciamento {
    private static ArrayList<Agente> agentes = new ArrayList<>();
    private static ArrayList<Missao> missoes = new ArrayList<>();

    // ========== AGENTES ==========

    public static boolean adicionarAgente(Agente agente) {
        if (buscarAgentePorNomeExato(agente.getNome()) != null) {
            System.out.println("Ja existe um agente com esse nome.");
            return false;
        }
        agentes.add(agente);
        System.out.println("Agente '" + agente.getNome() + "' adicionado com sucesso.");
        return true;
    }

    public static boolean removerAgente(String nome) {
        Agente a = buscarAgentePorNomeExato(nome);
        if (a != null) {
            agentes.remove(a);
            System.out.println("Agente '" + nome + "' removido com sucesso.");
            return true;
        }
        System.out.println("Agente nao encontrado.");
        return false;
    }

    public static boolean atualizarAgente(String nome, String campo, int novoValor) {
        Agente a = buscarAgentePorNomeExato(nome);
        if (a == null) {
            System.out.println("Agente nao encontrado.");
            return false;
        }

        switch (campo.toLowerCase()) {
            case "nivel":
                if (novoValor < 1) {
                    System.out.println("Nivel invalido.");
                    return false;
                }
                a.ganharExperiencia((novoValor - a.getNivel()) * 100);
                System.out.println("Nivel de '" + nome + "' atualizado para " + a.getNivel());
                return true;
            case "vida":
                a.setVidaAtual(novoValor);
                System.out.println("Vida atual de '" + nome + "' atualizada para " + a.getVidaAtual());
                return true;
            default:
                System.out.println("Campo invalido.");
                return false;
        }
    }

    public static Agente buscarAgentePorNomeExato(String nome) {
        for (Agente a : agentes) {
            if (a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }
        return null;
    }

    public static ArrayList<Agente> buscarAgentesPorNome(String termo) {
        ArrayList<Agente> resultado = new ArrayList<>();
        for (Agente a : agentes) {
            if (a.getNome().toLowerCase().contains(termo.toLowerCase())) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public static void listarAgentes() {
        if (agentes.isEmpty()) {
            System.out.println("Nenhum agente cadastrado.");
            return;
        }
        for (Agente a : agentes) {
            System.out.println(a);
        }
    }

    public static void agruparAgentesPorNivel() {
        ArrayList<Integer> niveis = new ArrayList<>();
        for (Agente a : agentes) {
            if (!niveis.contains(a.getNivel())) {
                niveis.add(a.getNivel());
            }
        }

        for (int nivel : niveis) {
            System.out.print("Nivel " + nivel + ": ");
            ArrayList<String> nomes = new ArrayList<>();
            for (Agente a : agentes) {
                if (a.getNivel() == nivel) {
                    nomes.add(a.getNome());
                }
            }
            System.out.println(String.join(", ", nomes));
        }
    }

    // ========== MISSOES ==========

    public static boolean adicionarMissao(Missao m) {
        for (Missao mi : missoes) {
            if (mi.getNome().equalsIgnoreCase(m.getNome())) {
                System.out.println("Ja existe uma missao com esse nome.");
                return false;
            }
        }
        missoes.add(m);
        System.out.println("Missao '" + m.getNome() + "' criada com ID " + m.getId());
        return true;
    }

    public static boolean removerMissao(int id) {
        Missao m = metodoPorID(id);
        if (m != null) {
            missoes.remove(m);
            System.out.println("Missao ID " + id + " removida com sucesso.");
            return true;
        }
        System.out.println("Missao nao encontrada.");
        return false;
    }

    public static boolean atualizarMissao(int id, String campo, String novoValor) {
        Missao m = metodoPorID(id);
        if (m == null) {
            System.out.println("Missao nao encontrada.");
            return false;
        }

        if (campo.equalsIgnoreCase("nome")) {
            m.setNome(novoValor);
            System.out.println("Nome da missao atualizado para '" + novoValor + "'");
            return true;
        } else if (campo.equalsIgnoreCase("dificuldade")) {
            try {
                int dificuldade = Integer.parseInt(novoValor);
                if (dificuldade >= 1 && dificuldade <= 3) {
                    m.setDificuldade(dificuldade);
                    System.out.println("Dificuldade atualizada para " + dificuldade);
                    return true;
                } else {
                    System.out.println("Dificuldade deve ser entre 1 e 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido.");
            }
        }

        return false;
    }

    public static Missao metodoPorID(int id) {
        for (Missao m : missoes) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    public static void listarMissoes() {
        for (Missao m : missoes) {
            System.out.println(m);
        }
    }









    public static void agruparMissoesPorDificuldade() {
        ArrayList<Integer> dificuldades = new ArrayList<>();
        for (Missao m : missoes) {
            if (!dificuldades.contains(m.getDificuldade())) {
                dificuldades.add(m.getDificuldade());
            }
        }

        for (int dif : dificuldades) {
            System.out.print("Dificuldade " + dif + ": ");
            ArrayList<String> nomes = new ArrayList<>();
            for (Missao m : missoes) {
                if (m.getDificuldade() == dif) {
                    nomes.add(m.getId() + " - " + m.getNome());
                }
            }
            System.out.println(String.join(", ", nomes));
        }
    }
}

