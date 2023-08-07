/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
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
public class DAOCliente {
      public List<Cliente> getLista(){
        String sql = "select * from cliente";
        List<Cliente> lista = new ArrayList<>();
        
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cliente obj = new Cliente(); 
                obj.setCodigoCliente(rs.getInt("codigo"));
                obj.setNomeCliente(rs.getString("nomeCliente"));
                obj.setEstado(rs.getString("estado"));
                obj.setCidade(rs.getString("cidade"));
                obj.setBairro(rs.getString("bairro"));
                obj.setRua(rs.getString("rua"));
                obj.setNumero(rs.getString("numero"));
                obj.setEmail(rs.getString("email"));
                
                lista.add(obj);
            }
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no getLista()" + e.getMessage());
    }
         return lista;
      }
      
      public boolean salvar(Cliente obj) {
        if (obj.getCodigoCliente() == null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }
      public boolean incluir(Cliente obj) {
        String sql = "insert into cliente (nomeCliente,estado,cidade,bairro,rua,numero,email) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeCliente());
            pst.setString(2, obj.getEstado());
            pst.setString(3, obj.getCidade());
            pst.setString(4, obj.getBairro());
            pst.setString(5, obj.getRua());
            pst.setString(6, obj.getNumero());
            pst.setString(7, obj.getEmail());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente incluido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não incluido");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOCliente" + e.getMessage());

        }
        return false;
    }
      public boolean alterar(Cliente obj) {
        String sql = "update cliente set nomeCliente=?, estado=?,cidade=?,bairro=?, rua=?, numero=?, email=?   where codigo=?";
                
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeCliente());
            pst.setString(2, obj.getEstado());
            pst.setString(3, obj.getCidade());
            pst.setString(4, obj.getBairro());
            pst.setString(5, obj.getRua());
            pst.setString(6, obj.getNumero());
            pst.setString(7, obj.getEmail());
            pst.setInt(8, obj.getCodigoCliente());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente Alterado ");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não alterado ");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOCliente " + e.getMessage());

        }
        return false;
    }
      public boolean remover(Cliente obj) {
        String sql = "delete from cliente where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigoCliente());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente removido ");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não foi removido ");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro, esse cliente esta vinculado a uma venda, não foi possivel removelo!!!" + e.getMessage());

        }
        return false;
    }
    
}
