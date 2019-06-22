package msoftware.models;

public class Pedido {
    private Item item;
    private double total;
    private int qtd;

    public Pedido(Item item, int qtd) {
        this.item = item;
        this.qtd = qtd;
        this.total = qtd*item.getPreco();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return this.item.getCod() + " " + this.item.getNome() + "(" + this.qtd + ") - " + this.total;
    }
    
}
