package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.PropinaDetalhe;

public class PropinaDetalheDAO {

    private static final String INSERT = "INSERT INTO proprina_detalhes(id_proprina, id_mes_propina, valor_mes_proprina)VALUES(?, ?, ?)";
    private static final String UPDATE = "";
    private static final String DELETE = "";
    private static final String SELECT_ALL = "";

    public boolean save(PropinaDetalhe detalhe) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        if (detalhe == null) {
            System.err.println("PropinaDetalheDAO:save: O valor oassado nao pode ser nulo!");
        }
        try {

            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, detalhe.getPropina().getIdPropina());
            ps.setInt(2, detalhe.getMesPropina().getIdMesPropina());
            ps.setDouble(3, detalhe.getValorPropina());
           
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("PropinaDetalheDAO:save:Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (SQLException e) {
            System.out.println("PropinaDetalheDAO:save:Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }
}
