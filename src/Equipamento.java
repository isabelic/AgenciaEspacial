
public abstract class Equipamento {
    private String nome;
    private int bonus;

    public Equipamento(String nome, int bonus) {
        this.nome = nome;
        this.bonus = bonus;
    }

    public String getNome() {
        return nome;
    }

    public int getBonus() {
        return bonus;
    }

    public abstract void aplicar(Agente agente);

    @Override
    public String toString() {
        return nome + " (+" + bonus + ")";
    }

}
