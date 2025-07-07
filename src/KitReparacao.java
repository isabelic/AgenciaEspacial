

public class KitReparacao extends Equipamento {

    public KitReparacao() {
        super("Kit de Reparação", 30); // bônus de vida
    }

    @Override
    public void aplicar(Agente agente) {
        int novaVida = agente.getVidaAtual() + getBonus();
        agente.setVidaAtual(novaVida);
        System.out.println(agente.getNome() + " usou Kit de Reparação e restaurou +" + getBonus() + " de vida.");
    }
}
