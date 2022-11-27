package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Aluno;
import utils.Conexao;

/**
 *
 * @author Lanylldo
 */
public class AlunoDAO {

    private Connection connection = Conexao.getConexao();

    public void save(Aluno cliente) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO clientes (nome, email, sexo, telefone, cpf) VALUES (?,?,?,?,?)");
            ps.setString(1, "nome");
            ps.setString(2, "email");
            ps.setString(3, "sexo");
            ps.setString(4, "telefone");
            ps.setString(5, "imagem");
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Aluno cliente) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE clientes SET  nome=?, email=?, sexo=?, telefone=? WHERE id=?");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getSexo());
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getCpf());
            ps.setInt(6, cliente.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveOrUpdate(Aluno cliente) {
        if (cliente.getId() == 0) {
            save(cliente);
        } else {
            update(cliente);
        }
    }

    public void delete(Aluno cliente) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM clientes WHERE id=?");
            ps.setInt(1, cliente.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Aluno> getAll() {
        List<Aluno> clientes = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM cliente");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Aluno cliente = new Aluno();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCpf(rs.getString("cpf"));
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
