/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Funcionario;

public class DAOFuncionario { // Data Acess Object
   
    DAOCidade objDAOCidade = new DAOCidade();
    
     
    public ResultSet autentificacaoFuncionario(Funcionario obj) {
        String sql = "select * from funcionario where nomeFuncionario = ? and senha= ? ";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeFuncionario());
            pst.setString(2, obj.getSenha());
            ResultSet rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no autentificacaoFuncionario no DAOFuncionario" + e.getMessage());
            return null;
        }
        
    }
    
    public List<Funcionario> getLista(){
        String sql = "select * from funcionario";
         List<Funcionario> lista = new ArrayList<>();
         try{
           PreparedStatement pst = Conexao.getPreparedStatement(sql);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
                Funcionario objFuncionario = new Funcionario();
                objFuncionario.setCodigoFuncionario(rs.getInt("codigo"));
                objFuncionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
                objFuncionario.setSalarioFuncionario(rs.getDouble("salario"));
                java.sql.Date dt = rs.getDate("nascimento");
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                objFuncionario.setNascimentoFuncionario(c);
                objFuncionario.setSenha(rs.getString("senha"));
                
                objFuncionario.setObjCidade(objDAOCidade.localizar(rs.getInt("cidade")));
                lista.add(objFuncionario);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL  no getLista(): "+ex.getMessage());
        }
         
        return lista;
    }
    
public boolean salvar(Funcionario obj) {
        if (obj.getCodigoFuncionario() == null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }
public boolean incluir(Funcionario obj) {
        String sql = "insert into funcionario (nomeFuncionario,salario,nascimento,senha,cidade) values(?,?,?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeFuncionario());
            pst.setDouble(2, obj.getSalarioFuncionario());
            pst.setDate(3, new java.sql.Date(obj.getNascimentoFuncionario().getTimeInMillis()));
            pst.setString(4, obj.getSenha());
            pst.setInt(5, obj.objCidade.getCodigoCidade());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionário cadastrado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Funcionário não cadastrado");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOFuncionario" + e.getMessage());

        }
        return false;
    }
public boolean alterar(Funcionario obj) {
        String sql = "update funcionario set nomeFuncionario=?, salario=?, nascimento=?,senha=?, cidade=? where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
           pst.setString(1, obj.getNomeFuncionario());
            pst.setDouble(2, obj.getSalarioFuncionario());
            pst.setDate(3, new java.sql.Date (obj.getNascimentoFuncionario().getTimeInMillis()));
            pst.setString(4, obj.getSenha());
            pst.setInt(5, obj.getObjCidade().getCodigoCidade());
            pst.setInt(6, obj.getCodigoFuncionario());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionário alterado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Funcionário não alterado");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOFuncionario" + e.getMessage());

        }
        return false;
    }
 public boolean remover(Funcionario obj) {
        String sql = "delete from funcionario where codigo=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigoFuncionario());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionário excluído");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Funcionário não excluído");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no excluir do DAOFuncionario" + e.getMessage());

        }
        return false;
    }
    
}
    

    
    
    


