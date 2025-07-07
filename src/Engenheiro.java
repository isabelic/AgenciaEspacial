public class Engenheiro extends Agente {
    private int inteligencia;

        public Engenheiro(String nome, int nivel, int inteligencia) {
            super(nome, nivel);
            if (inteligencia <= 0) throw new IllegalArgumentException("Inteligência deve ser positiva.");
            this.inteligencia = inteligencia;
        }

        @Override
        public int calcularDano() {
            return inteligencia * getNivel() + 10;
        }

    @Override
    public String toString() {
        return super.toString() +
                " //Inteligencia: "
                + inteligencia + "//";
    }
    }


