/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.io.Serializable;
import java.util.Objects;
/**
 *
 * @author user
 */
public class Cliente implements Serializable{

    
   

    Integer codigoCliente;
    String nomeCliente;
    String estado;
    String cidade;
    String bairro;
    String rua;
    String numero;
    String email;
    
    public Cliente() { // padrão javabeans
    }
    
    public Integer getCodigoCliente() {
        return codigoCliente;
    }
    
     public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.codigoCliente);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.codigoCliente, other.codigoCliente)) {
            return false;
        }
        return true;
    
    
  }
    @Override
    public String toString() {
        return this.nomeCliente; //To change body of generated methods, choose Tools | Templates.
    }
    

}
