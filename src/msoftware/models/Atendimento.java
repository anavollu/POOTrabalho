package msoftware.models;

import java.util.ArrayList;
import java.util.List;

public class Atendimento {
    
    private Cliente cliente;
    private Atendente atendente;
    private List<Pedido> pedidos = new ArrayList<>();
    
    public void registrarPedido(String codigo, int qtd){
        Item item = Item.procurarItem(codigo);
        if(item == null) throw new RuntimeException("Item não existe");
        Pedido pedido = new Pedido(item, qtd);
        pedidos.add(pedido);
    }
    
    public static Atendimento criarAtendimento(String cpfCliente, Atendente atendente){
        Atendimento atendimento = new Atendimento(atendente);
        Cliente clienteEncontrado = Cliente.procurarCPF(cpfCliente);
        if(clienteEncontrado == null) throw new RuntimeException("Cliente não existe");
        atendimento.setCliente(clienteEncontrado);
        return atendimento;
    }
    
    public static Atendimento criarAtendimento(Atendente atendente){
        return new Atendimento(atendente);
    }
    
    private Atendimento(Atendente atendente){
        this.atendente = atendente;
    }
    
    public Pagamento fecharAtendimento() {
        double valorTotal = calcularValorTotal();
        return new Pagamento(valorTotal, this);
    }
    
    private double calcularValorTotal(){
        double total = 0.0;
        for(Pedido pedido : this.pedidos){
            total += pedido.getTotal();
        }
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.atendente).append("\n");
        if(this.cliente != null) sb.append(this.cliente).append("\n");
        for(Pedido pedido : this.pedidos){
            sb.append(pedido).append("\n");
        }
        return sb.toString();
    }
    
    
    
}
