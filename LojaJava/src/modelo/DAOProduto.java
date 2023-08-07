/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class DAOProduto {
    
    public List<Produto> getLista(){
        String sql = "select * from produto";
        List<Produto> listaProduto = new ArrayList<>();
        try{
           PreparedStatement pst = Conexao.getPreparedStatement(sql);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
                Produto objProduto = new Produto();
                objProduto.setCodigoProduto(rs.getInt("codigo"));
                objProduto.setNomeProduto(rs.getString("nomeProduto"));
                objProduto.setPrecoCusto(rs.getFloat("precoCusto"));
                objProduto.setMargemLucro(rs.getFloat("margemLucro"));
                objProduto.setPrecoVenda(rs.getFloat("precoVenda"));
                objProduto.setTamanho(rs.getString("tamanho"));
                objProduto.setQuantidadeEstoque(rs.getInt("qtdeEstoque"));
                
                listaProduto.add(objProduto); 
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ex.getMessage());
        }   
        
        return listaProduto;
         
    }
    public Produto localizarProduto(Integer id) {
        String sql = "select * from produto where codigo = ?";
        Produto obj = new Produto();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                obj.setCodigoProduto(rs.getInt("codigo"));
                obj.setNomeProduto(rs.getString("nomeProduto"));
                obj.setPrecoCusto(rs.getFloat("precoCusto"));
                obj.setMargemLucro(rs.getFloat("margemLucro"));
                obj.setPrecoVenda(rs.getFloat("precoVenda"));
                obj.setTamanho(rs.getString("tamanho"));
                obj.setQuantidadeEstoque(rs.getInt("qtdeEstoque"));
                return obj;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no localizarProduto" + e.getMessage());

        }
        return null;
    }
    
   public boolean Salvar (Produto obj){
        if(obj.getCodigoProduto()==null){
            return incluir(obj);
        }
        return alterar(obj);
        
    }
    public boolean incluir(Produto obj) {
        String sql = "insert into produto (nomeProduto, precoCusto,precoVenda, tamanho, margemLucro, qtdeEstoque) values(?,?,?,?,?,?)";
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeProduto());
            pst.setFloat(2, obj.getPrecoCusto());
            pst.setFloat(3, obj.getPrecoVenda());
            pst.setString(4, obj.getTamanho());
            pst.setFloat(5, obj.getMargemLucro());
            pst.setInt(6, obj.getQuantidadeEstoque());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Produto incluido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Produto não incluido");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOProduto " + ex.getMessage());

        }
        return false;
    }
    
    public boolean alterar (Produto obj) {
        String sql = "update produto set  nomeProduto = ?, precoCusto = ?, precoVenda = ? , tamanho  = ? , margemLucro = ? , qtdeEstoque = ? where codigo = ? ";
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeProduto());
            pst.setFloat(2, obj.getPrecoCusto());
            pst.setFloat(3, obj.getPrecoVenda());
            pst.setString(4, obj.getTamanho());
            pst.setFloat(5, obj.getMargemLucro());
            pst.setInt(6, obj.getQuantidadeEstoque());
            pst.setInt(7, obj.getCodigoProduto());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Produto alterado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Produto não alterado");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOProduto " + ex.getMessage());

        }
        return false;
    }
    
    public boolean remover (Produto obj) {
        String sql = "delete from  produto  where codigo = ? ";
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigoProduto());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Produto deletado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Produto não deletado");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no remover do DAOProduto " + ex.getMessage());

        }
        return false;
    }
    
}
