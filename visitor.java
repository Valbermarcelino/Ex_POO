import java.util.ArrayList;
import java.util.List;

interface RelatorioVisitor {
    void visit(RelatorioAtividades r);
    void visit(RelatorioFinanceiro r);
}

interface Relatorio {
    void accept(RelatorioVisitor visitor);
}

class RelatorioAtividades implements Relatorio {
    private String nomeProjeto = "Projeto";
    private int horasTrabalhadas = 120;

    public String getNomeProjeto() { return nomeProjeto; }
    public int getHoras() { return horasTrabalhadas; }

    @Override
    public void accept(RelatorioVisitor visitor) {
        visitor.visit(this);
    }
}

class RelatorioFinanceiro implements Relatorio {
    private double saldoFinal = 50000.00;

    public double getSaldo() { return saldoFinal; }

    @Override
    public void accept(RelatorioVisitor visitor) {
        visitor.visit(this);
    }
}

class GeradorPDFVisitor implements RelatorioVisitor {
    @Override
    public void visit(RelatorioAtividades r) {
        System.out.println("[PDF] Relatório de Atividades - Projeto: " + r.getNomeProjeto() 
                           + " | Total de Horas: " + r.getHoras());
    }

    @Override
    public void visit(RelatorioFinanceiro r) {
        System.out.println("[PDF] Relatório Financeiro - Saldo Final: R$ " + r.getSaldo());
    }
}

class GeradorHTMLVisitor implements RelatorioVisitor {
    @Override
    public void visit(RelatorioAtividades r) {
        System.out.println("<html><body><h1>Atividades: " + r.getNomeProjeto() + "</h1></body></html>");
    }

    @Override
    public void visit(RelatorioFinanceiro r) {
        System.out.println("<html><body><h1>Financeiro: Saldo R$ " + r.getSaldo() + "</h1></body></html>");
    }
}

public class ExercicioVisitor {
    public static void main(String[] args) {
        List<Relatorio> relatorios = new ArrayList<>();
        relatorios.add(new RelatorioAtividades());
        relatorios.add(new RelatorioFinanceiro());

        RelatorioVisitor pdfVisitor = new GeradorPDFVisitor();
        for (Relatorio r : relatorios) {
            r.accept(pdfVisitor);
        }

        RelatorioVisitor htmlVisitor = new GeradorHTMLVisitor();
        for (Relatorio r : relatorios) {
            r.accept(htmlVisitor);
        }
    }
}
