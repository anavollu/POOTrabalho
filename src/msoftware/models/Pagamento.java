package msoftware.models;

public class Pagamento {
    
    public static enum TIPO_PAGAMENTO {
        CARTAO, DINHEIRO
    }
    
    private TIPO_PAGAMENTO tipoPagamento;
    private double valorPago;
    private double total;
    private Atendimento atendimento;

    public void pagar(TIPO_PAGAMENTO tipoPagamento, double valorPago){
        this.tipoPagamento = tipoPagamento;
        this.valorPago = valorPago;
        double troco = calcularTroco();
        if(troco > 0){
            System.out.println("TROCO = " + troco);
        }else{
            System.out.println("COMPRA CONCLUÍDA");
        }
    }
    
    private double calcularTroco() {
        return this.valorPago - this.total;
    }
    
    public Pagamento(double total, Atendimento atendimento){
        this.total = total;
        this.atendimento = atendimento;
    }

    public TIPO_PAGAMENTO getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TIPO_PAGAMENTO tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }
    
    
}
