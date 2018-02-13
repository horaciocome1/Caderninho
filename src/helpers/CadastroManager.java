/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.bean.Produto;
import model.bean.Stock;
import model.bean.Venda;
import model.dao.ProdutoDAO;
import strings.Strings;
import view.Alerta;
import view.ConfirmarCadastro;
import view.ConfirmarVenda;

/**
 *
 * @author horaciocome1
 * 
 * gere o cadastro de produtos, stocks, vendas
 */
public class CadastroManager {
    
    List<Produto> produtos;
    float total;
    Strings strings;

    public CadastroManager() {
        this.produtos = new ArrayList<>();
        this.total = 0f;
    }
    
    public void cadProduto(javax.swing.JTextField txtProduto, javax.swing.JTextArea txtDesc,
            javax.swing.JTextField txtPrecoGueva, javax.swing.JTextField txtPrecoVenda,
            javax.swing.JPanel panelParentProduto, javax.swing.JPanel panelDetalheProduto,
            javax.swing.JTable tabela) {
        if (txtProduto.getText().equalsIgnoreCase("")) {
            // sem nome
            Alerta a = new Alerta("Erro no campo Produto correspondente ao nome do produto. Preenchimento obrigatório.");
            a.setVisible(true);
        } else if (txtPrecoGueva.getText().equalsIgnoreCase("")) {
            // sem preco gueva
            Alerta a = new Alerta("Erro no preço de gueva. Preenchimento obrigatório. Tente no formato 162.50.");
            a.setVisible(true);
        } else if (txtPrecoVenda.getText().equalsIgnoreCase("")) {
            // sem preco venda
            Alerta a = new Alerta("Erro no preço de venda. Preenchimento obrigatório. Tente no formato 162.50.");
            a.setVisible(true);
        } else {
            // se os campos estiverem preenchidos vou verificar se os precos estao bem com um try-catch
            try {
                float pgueva = Float.parseFloat(txtPrecoGueva.getText());
                try {
                    float pvenda = Float.parseFloat(txtPrecoGueva.getText());
                    // aqui ja posso criar e cadastrar o produto, tudo esta como o banco de dados espera
                    Produto p = new Produto();
                    p.setNome(txtProduto.getText());
                    if (!txtDesc.getText().equalsIgnoreCase(""))
                        p.setDesc(txtDesc.getText());
                    p.setpCompra(pgueva);
                    p.setpVenda(pvenda);
                    ProdutoDAO pdao = new ProdutoDAO();
                    if (tabela.isEnabled()) {
                        // se a tabela produtos estiver activa se trata de um puro cadastro
                        pdao.add(p);
                    } else {
                        // caso contrario se trata de editar uma tupla, para que se mantenha fixa a linha a editar
                        // para isso preciso pegar o id na tabela
                        int id = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
                        p.setId(id);
                        pdao.update(p);
                        // depois deconcluir a operacao a tabela volta a estar ativa
                        tabela.setEnabled(true);
                    }
                    // vazar todos os campos
                    txtProduto.setText("");
                    txtDesc.setText("");
                    txtPrecoGueva.setText("");
                    txtPrecoVenda.setText("");
                    // voltar ao painel anterior
                    PanelManager pm = new PanelManager();
                    pm.loadPanel(panelParentProduto, panelDetalheProduto);
                } catch (NumberFormatException e) {
                    Alerta a = new Alerta("Erro no preço de venda. Tente no formato 162.50");
                    a.setVisible(true);
                }
            } catch (NumberFormatException e) {
                    Alerta a = new Alerta("Erro no preço de venda. Tente no formato 162.50");
                    a.setVisible(true);
            }
        }
    }
    
    public void cadStock(javax.swing.JLabel lblErro) {
        if (!this.produtos.isEmpty()) {
            Stock stock = new Stock();
            stock.setDataCompra(Calendar.getInstance());
            stock.setProdutos(this.produtos);
            ConfirmarCadastro confirmarCadastro = new ConfirmarCadastro(stock);
            confirmarCadastro.setVisible(true);
        } else {
            // tentar cadastrar stock sem produtos
            Alerta alerta = new Alerta("Adicione produtos os produtos que pretende armazenar no stock");
            alerta.setVisible(true);
        }
    }
    
    public void cadVenda(String modoPagamento, String ordenante) {
        // este metodo recebe o modo de pagamento da combobox e o ordenante
        // ele pega nos produtos, empacota na venda e envia para a classe do dialogo de confirmacao
        // a nota sera adicionada la
        if (!this.produtos.isEmpty()) {
            Venda venda = new Venda();
            venda.setData(Calendar.getInstance());
            venda.setProdutos(this.produtos);
            if (modoPagamento.equalsIgnoreCase("dinheiro"))
                venda.setEstado(true);
            else
                venda.setEstado(false);
            venda.setOrdenante(ordenante);
            venda.setTotal(total);
            ConfirmarVenda confirmarVenda = new ConfirmarVenda(venda);
            confirmarVenda.setVisible(true);
        } else {
            // tentar cadastrar venda sem produtos
            Alerta alerta = new Alerta("Adicione produtos os produtos que pretende vender");
            alerta.setVisible(true);
        }
    }
    
    public void addProdutoToList(int id, javax.swing.JLabel lblErro) {
        ProdutoDAO dao = new ProdutoDAO();
        Produto produto = dao.getOne(id);
        produto.setQtd(1); // quantidade que se pretende vender/stockar || este eh o 1o produto
        this.produtos.add(produto);
        lblErro.setText("");
        setTotal();
    }
    
    public void aumentarQtd(int id) {
        // este metodo procura o produto na lista
        // encontra e incrementa a quantidade
        Produto newProduto = new Produto();
        newProduto.setId(id);
        for (Produto produto : this.produtos) {
            if (produto.getId() == newProduto.getId()) {
                newProduto.setQtd(produto.getQtd() + 1); 
                newProduto.setpVenda(produto.getpVenda());
                this.produtos.set(this.produtos.indexOf(produto), newProduto);
                setTotal();
            }
        }
    }
    
    public void diminuirQtd(int id) {
        // este metodo procura o produto na lista
        // encontra e decrementa a quantidade
        // caso seja 1 so, o produto eh removido
        Produto newProduto = new Produto();
        newProduto.setId(id);
        for (Produto produto : this.produtos) {
            if (produto.getId() == newProduto.getId()) {
                if (produto.getQtd() == 1)
                    this.produtos.remove(produto);
                else { // assumindo que nao havera um produto com quantidade nula nesta lista
                    newProduto.setQtd(produto.getQtd() - 1); // o novo recebe a qtd do antigo decrementada
                    this.produtos.set(this.produtos.indexOf(produto), newProduto);
                }
                setTotal();
            }
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }


    public float getTotal() {
        return total;
    }

    private void setTotal() {
        produtos.forEach((produto) -> {
            total = total + (produto.getQtd() * produto.getpVenda());
        });
    }
    
    public String vendaToText(Venda venda) {
        // este metodo transforma um objecto venda em um texto
        String vendaTxt;
        if (venda.isEstado())
            vendaTxt = "Pagamento a vista \n";
        else
            vendaTxt = "Vale \n";
        if (!venda.getOrdenante().equalsIgnoreCase(""))
            vendaTxt = vendaTxt + "Em nome de " + venda.getOrdenante() + "\n";
        vendaTxt = vendaTxt + "Os seguintes produtos: \n";
        for (Produto produto : venda.getProdutos())
            vendaTxt = vendaTxt + produto.getNome() + "(" + String.valueOf(produto.getQtd()) + "), ";
        vendaTxt = vendaTxt.substring(0, vendaTxt.length() - 2); // para tirar a virgula no final
        return vendaTxt;
    }
    
}