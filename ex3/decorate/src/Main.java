interface Bebida {
    String getDescricao();
    double getCusto();
}

class CafeExpresso implements Bebida {
    public String getDescricao() { return "Café Expresso"; }
    public double getCusto() { return 5.0; }
}

class Cappuccino implements Bebida {
    public String getDescricao() { return "Cappuccino"; }
    public double getCusto() { return 7.5; }
}

class Cha implements Bebida {
    public String getDescricao() { return "Chá"; }
    public double getCusto() { return 4.0; }
}

abstract class BebidaDecorator implements Bebida {
    protected Bebida bebidaBase;

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

class Canela extends BebidaDecorator {
    public Canela(Bebida b) { super(b); }
    public String getDescricao() { return bebidaBase.getDescricao() + " + Canela"; }
    public double getCusto() { return bebidaBase.getCusto() + 1.5; }
}

class CaldaChocolate extends BebidaDecorator {
    public CaldaChocolate(Bebida b) { super(b); }
    public String getDescricao() { return bebidaBase.getDescricao() + " + Calda de Chocolate"; }
    public double getCusto() { return bebidaBase.getCusto() + 2.5; }
}

class ClienteDecorator {
    public static void main(String[] args) {

        Bebida pedido1 = new Chantilly(new Leite(new CafeExpresso()));
        System.out.println("--- Pedido 1 ---");
        System.out.println("Item: " + pedido1.getDescricao());
        System.out.println("Total: R$ " + pedido1.getCusto());

        Bebida pedido2 = new CaldaChocolate(new Canela(new Cappuccino()));
        System.out.println("\n--- Pedido 2 ---");
        System.out.println("Item: " + pedido2.getDescricao());
        System.out.println("Total: R$ " + pedido2.getCusto());

        Bebida pedido3 = new Cha();
        System.out.println("\n--- Pedido 3 ---");
        System.out.println("Item: " + pedido3.getDescricao());
        System.out.println("Total: R$ " + pedido3.getCusto());
    }
}