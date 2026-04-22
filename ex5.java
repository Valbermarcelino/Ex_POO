interface Observer {
    void update(String mensagem);
}

interface Bebida {
    String getDescricao();
    double getCusto();
}

class Expresso implements Bebida {
    public String getDescricao() { return "Expresso"; }
    public double getCusto() { return 5.0; }
}

abstract class BebidaDecorator implements Bebida {
    protected Bebida base;
    
    public BebidaDecorator(Bebida base) { 
        this.base = base; 
    }
}

class Leite extends BebidaDecorator {
    public Leite(Bebida base) { super(base); }
    public String getDescricao() { return base.getDescricao() + " + Leite"; }
    public double getCusto() { return base.getCusto() + 2.0; }
}

interface EstrategiaPagamento {
    void pagar(double valor);
}

class PagamentoPix implements EstrategiaPagamento {
    public void pagar(double valor) {
        System.out.println("Pagamento de R$ " + valor + " aprovado instantaneamente via Pix.");
    }
}

class PagamentoCartao implements EstrategiaPagamento {
    public void pagar(double valor) {
        System.out.println("Pagamento de R$ " + valor + " aprovado no Cartão de Crédito.");
    }
}

class Moedor {
    void ligar() { System.out.println("Moedor ativado."); }
}

class Aquecedor {
    void ligar() { System.out.println("Aquecedor de água ativado."); }
}

class CafeteiraInteligente {
    private static CafeteiraInteligente instancia;
    
    private Moedor moedor = new Moedor();
    private Aquecedor aquecedor = new Aquecedor();
    private Observer[] dispositivos = new Observer[3];
    private int contador = 0;

    private CafeteiraInteligente() {}

    public static CafeteiraInteligente getInstancia() {
        if (instancia == null) {
            instancia = new CafeteiraInteligente();
        }
        return instancia;
    }

    public void registrar(Observer o) {
        if (contador < dispositivos.length) {
            dispositivos[contador++] = o;
        }
    }

    public void prepararPedido(Bebida bebida, EstrategiaPagamento pagamento) {
        System.out.println("\n--- Processando Pagamento ---");
        pagamento.pagar(bebida.getCusto());

        System.out.println("\n--- Iniciando Preparo ---");
        moedor.ligar();
        aquecedor.ligar();
        
        System.out.println("Montando: " + bebida.getDescricao());

        for (int i = 0; i < contador; i++) {
            dispositivos[i].update("Seu pedido (" + bebida.getDescricao() + ") está pronto!");
        }
    }
}

class Smartphone implements Observer {
    private String dono;
    
    public Smartphone(String dono) { 
        this.dono = dono; 
    }
    
    public void update(String mensagem) {
        System.out.println("Notificação no celular de " + dono + ": " + mensagem);
    }
}

class Teste {
    public static void main(String[] args) {
        CafeteiraInteligente maquina = CafeteiraInteligente.getInstancia();

        maquina.registrar(new Smartphone("Carlos"));
        maquina.registrar(new Smartphone("Ana"));

        Bebida meuPedido = new Leite(new Expresso());
        EstrategiaPagamento meuPagamento = new PagamentoPix();

        maquina.prepararPedido(meuPedido, meuPagamento);
    }
}
