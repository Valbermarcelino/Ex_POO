interface Bebida {
    String getDescricao();
    double getCusto();
}

class CafeExpresso implements Bebida {
    public String getDescricao() { return "Café Expresso"; }
    public double getCusto() { return 5.0; }
}

abstract class BebidaDecorator implements Bebida {
    protected Bebida bebidaBase; // Composição [cite: 192, 264]

    public BebidaDecorator(Bebida bebida) {
        this.bebidaBase = bebida;
    }
}

class Leite extends BebidaDecorator {
    public Leite(Bebida b) { super(b); }
    public String getDescricao() { return bebidaBase.getDescricao() + " + Leite"; }
    public double getCusto() { return bebidaBase.getCusto() + 2.0; }
}

class Chantilly extends BebidaDecorator {
    public Chantilly(Bebida b) { super(b); }
    public String getDescricao() { return bebidaBase.getDescricao() + " + Chantilly"; }
    public double getCusto() { return bebidaBase.getCusto() + 3.5; }
}

class ClienteDecorator {
    public static void main(String[] args) {
        Bebida meuCafe = new Chantilly(new Leite(new CafeExpresso()));

        System.out.println("Pedido: " + meuCafe.getDescricao());
        System.out.println("Total: R$ " + meuCafe.getCusto());
    }
}