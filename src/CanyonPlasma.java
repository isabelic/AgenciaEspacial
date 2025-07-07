public class CanyonPlasma extends Equipamento{

   public CanyonPlasma() {
            super("Canhão de Plasma", 7);
        }

        @Override
        public void aplicar(Agente agente) {
            System.out.println(agente.getNome() + " recebeu Canhão de Plasma +7 de dano simbólico");
        }
    }


