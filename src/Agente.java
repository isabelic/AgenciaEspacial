import java.util.ArrayList;

public abstract class Agente {

        private String nome;
        private int nivel;
        private int vidaAtual;
        private int vidaMax;
        private int experiencia;
        private static boolean status;
        ArrayList<Equipamento> equipa = new ArrayList<Equipamento>();

        public Agente(String nome, int nivel) {
                this.nome = nome;
                this.nivel = nivel;
                this.vidaMax = nivel * 100;
                this.vidaAtual = vidaAtual;
                this.experiencia = experiencia;
                this.status = true;

        }


        public abstract int calcularDano();

        public String getNome() {

                return nome;
        }

        public int getNivel() {

                return nivel;
        }

        public int getVidaAtual() {
                return vidaAtual;
        }

        public static boolean isStatus() {

                return status;
        }

        public void setVidaAtual(int vidaAtual) {
               this.vidaAtual = Math.min(vidaAtual, vidaMax);
                  if (this.vidaAtual <= 0) {
                       this.status = false;
                         this.vidaAtual = 0;
                     }


        }

        public void receberDano(int dano) {
                setVidaAtual(this.vidaAtual - dano);
        }


        public void ganharExperiencia(int xp) {
                this.experiencia += xp;
                while (this.experiencia >= 100) {
                        this.experiencia -= 100;
                        this.nivel++;
                        this.vidaMax = this.nivel * 100;
                        this.vidaAtual = this.vidaMax;
                        System.out.println(nome + " subiu para o nível " + nivel + "!");
                }
        }


        public void adicionarEquipa(Equipamento e) {
                equipa.add(e);
                System.out.println(nome + " recebeu " + e);
        }


        public void listarInventario() {
                if (equipa.isEmpty()) {
                        System.out.println("Nenhum equipamento encontrado!.");
                } else {
                        for (Equipamento equipame : equipa) {
                                System.out.println(equipame);
                        }
                }
        }
        @Override
        public String toString() {
                return "Nome: " + nome +
                        " | Nível: " + nivel +
                        " | Vida: " + vidaAtual +
                        " | XP: " + experiencia;
        }

}



