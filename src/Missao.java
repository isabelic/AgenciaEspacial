import java.util.ArrayList;

public class Missao {
    private static int cont = 1;
    private String nome;
    private int id;
    private int dificuldade;
    private boolean concluida;
    ArrayList<Agente> missaoAgente;

 public Missao(String nome, int dificuldade) {
     this.id = cont++;
     this.nome = nome;
     this.dificuldade = Math.max(1, Math.min(dificuldade, 3));
     this.missaoAgente = new ArrayList<>();
     this.concluida = false;

 }
    public String getNome() {

     return nome;
    }

    public int getId() {

     return id;
    }

    public int getDificuldade()
    {
        return dificuldade;
    }

    public boolean isConcluida()
    {
        return concluida;
    }

    public ArrayList<Agente> getMissaoAgente() {

     return missaoAgente;
    }


    //adicionando
    public boolean adicionarAgente(Agente agente) {
     if(concluida) {
         System.out.println("Agente adicionado na missão!");
         return false;
     }

    if(!Agente.isStatus()){
        System.out.println("O agente foi de F e não pode participar");
        return false;
    }

    if(missaoAgente.contains(agente)) {
        System.out.println("Agente ja está na missao");
        return false;
    }
    missaoAgente.add(agente);
    return true;
}


//Removendo

    public boolean removerAgente(Agente agente) {
     if(concluida) {
         System.out.println("Missão ja foi concluida");
         return false;
     }
     return missaoAgente.remove(agente);
    }


    //quantidade o suficiente de participantes

    public boolean QuantidadeMinimaParticipantes(){

     return missaoAgente.size() >= (dificuldade + 1);
    }

    //missoes
    public void iniciarMissoes(){
     if(concluida) {
         System.out.println("Missão concluida");
         return;
     }

     if(!QuantidadeMinimaParticipantes()){
         System.out.println("Quantidade insuficiente de participantes");
         return;
     }

     System.out.println("""
             ///////Iniciando Missão//////
    
                \s""");

     boolean alterado = true;
     for(Agente agente : missaoAgente) {
         int dano = agente.calcularDano();
         System.out.println("- " + agente.getNome()
                 + ": DANO CAUSADO "
                 + dano + ".");

         int xp = dificuldade * 50;
         agente.ganharExperiencia(xp);

         Equipamento equipamento = alterado ? new KitReparacao() : new CanyonPlasma();
         agente.adicionarEquipa(equipamento);
         equipamento.aplicar(agente);

         alterado = !alterado;
     }

     this.concluida = true;
     System.out.print("Missão" + nome + "concluida!");
    }

    @Override
    public String toString() {
        return "ID " + id +
                " | " + nome +
                " | Dificuldade: " + dificuldade +
                " | Status: " + (concluida ? "Concluída" : "Pendente") +
                " | Participantes: " + missaoAgente.size();
    }

    public void setNome(String nome) {
     this.nome = nome;
    }

    public void setDificuldade(int dificuldade) {
     this.dificuldade = dificuldade;
    }
}


