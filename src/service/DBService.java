package service;

import application.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ghavrilin Oleg on 06.06.2017.
 */
public class DBService {

    private Statement statement;

    private ResultSet resultSet;

    private String user;

    private String password;

    public DBService(String user, String password)
    {
        this.user = user;
        this.password = password;
    }

    private PreparedStatement preparedStatement;

     public Map<String,Integer> getAllPath() throws SQLException, ClassNotFoundException {

        statement =new Connector(user, password).getConnection().createStatement();

        resultSet = statement.executeQuery("SELECT id,path FROM mapper_sound");

        Map<String,Integer> pathMap = new HashMap<String,Integer>();

        while (resultSet.next()) {
            pathMap.put(resultSet.getString("path"),resultSet.getInt("id"));
        }

        return pathMap;
    }

    public Integer getMappId(Integer audioId) throws SQLException, ClassNotFoundException {
        statement =new Connector(user, password).getConnection().createStatement();

        resultSet = statement.executeQuery(
                "Select mapp_audio_id FROM audio WHERE id="+audioId);

        resultSet.next();

        return resultSet.getInt("mapp_audio_id");
    }

    public void removeMapper(Integer mapperId) throws SQLException, ClassNotFoundException {
        preparedStatement =
                new Connector(user, password).getConnection().prepareStatement(
                        "DELETE FROM mapper_sound WHERE id=?");
        preparedStatement.setInt(1,mapperId);
        preparedStatement.execute();
    }

    public void changeMapper(Integer audioId, Integer mapperId) throws SQLException, ClassNotFoundException {
        //hold old mapper id for remove
        Integer oldMapper = new DBService(user, password).getMappId(audioId);
        //update
        preparedStatement =
                new Connector(user, password).getConnection().prepareStatement(
                        "UPDATE audio SET mapp_audio_id= ? WHERE id=?");
        preparedStatement.setInt(1,mapperId);
        preparedStatement.setInt(2,audioId);
        preparedStatement.execute();

        //remove old mapper
        new DBService(user, password).removeMapper(oldMapper);
    }

}
