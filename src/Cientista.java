public class Cientista extends Agente{
     private  int criatividade;

          public Cientista(String nome, int nivel, int criatividade) {
               super(nome, nivel);
               if (criatividade <= 0) throw new IllegalArgumentException("Criatividade deve ser positiva.");
               this.criatividade = criatividade;
          }

          @Override
          public int calcularDano() {
               return criatividade * getNivel() + 5;
          }

     @Override
     public String toString() {
          return super.toString() +
                  " //Criatividade: "
                  + criatividade+ "//";
     }
     }


