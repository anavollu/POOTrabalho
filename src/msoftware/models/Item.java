package msoftware.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Item {
    private static final Map<String, Item> ITEMS = new HashMap<>();
    private String cod;
    private String nome;
    private double preco;
    
    public static Item cadastrarItem(String nome, Double preco){
        String cod = Long.toString((new Date()).getTime());
        Item item = new Item(nome, preco, cod);
        Item.ITEMS.put(cod, item);
        return item;
    }
    
    public static Item procurarItem(String cod){
        return Item.ITEMS.get(cod);
    }

    private Item(String nome, Double preco, String cod) {
        this.nome = nome;
        this.preco = preco;
        this.cod = cod;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
