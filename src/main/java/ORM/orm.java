package ORM;
import database.db;
import java.sql.*;
import java.util.Map;


public class orm {

    private StringBuilder conditions;
    private String tableName;

    public orm(){}
    public orm(String table_name){
        this.tableName=table_name;
        this.conditions=new StringBuilder();
    }

    public <k, v> ResultSet save(Map<k, v> array) throws Exception {
        db database=new db();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet generatedKeys = null;
        int index=0;
        try{
            con=database.connect();
            String sql="Insert INTO "+this.tableName+"(";
            for(Map.Entry<k, v> items : array.entrySet()){
                if (index<array.size()-1){
                    sql+=items.getKey() + "," ;
                }else{
                    sql+=items.getKey() + ")" ;
                }
                index++;
            }
            sql+="values (";
            index=0;
            for(Map.Entry<k, v> items : array.entrySet()){
                if (index<array.size()-1){
                    sql+="'"+items.getValue() + "'," ;
                }else{
                    sql+="'"+items.getValue() + "')" ;
                }
                index++;
            }
            ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            generatedKeys = ps.getGeneratedKeys();
        }catch(SQLException e){
            System.out.println("il ya une error de l'insertion\n"+e);
        }
        return generatedKeys;
    }

    public <k, v> ResultSet update(Map<k, v> array) throws Exception {
        db database=new db();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet generatedKeys = null;
        int index=0;
        try{
            con=database.connect();
            if(conditions.length()>0){
                String sql="UPDATE "+this.tableName+" SET ";
                for(Map.Entry<k, v> items : array.entrySet()){
                    if (index<array.size()-1){
                        sql+=items.getKey() + "='"+ items.getValue() + "'," ;
                    }else{
                        sql+=items.getKey()+ "='"+ items.getValue()  + "' WHERE " ;
                    }
                    index++;
                }
                sql+=conditions;
                ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.executeUpdate();
                generatedKeys = ps.getGeneratedKeys();
            }else {
                System.err.println("Error: No conditions specified for the SQL query.");
            }
        }catch(Exception e){
            System.out.println("il ya une error de l'insertion\n"+e);
        }
        return generatedKeys;
    }

    public ResultSet get() throws Exception {
        db database=new db();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet response=null;
        String sql="SELECT * FROM "+tableName;
        try{
            if(this.conditions.length()>0){
                sql+=" WHERE "+this.conditions;
            }
            con=database.connect();
            ps=con.prepareStatement(sql);
            response=ps.executeQuery();
        }catch(Exception e){
            System.out.println("il ya une error de l'insertion\n"+e);
        }

        return response;
    }

    public boolean delete() throws Exception {
        db database=new db();
        Connection con=null;
        PreparedStatement ps=null;
        String sql="DELETE FROM "+tableName;
        try{
            con=database.connect();
            if(this.conditions.length()>0){
                sql+=" WHERE "+this.conditions;
                ps=con.prepareStatement(sql);
                ps.executeUpdate();
            }else {
                System.err.println("Error: No conditions specified for the SQL query.");
            }
        }catch(Exception e){
            System.out.println("il ya une error durant delete\n"+e);
        }

        return true;
    }

    public  ResultSet first() throws Exception{
        db database=new db();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet response=null;
        String sql="SELECT * FROM "+tableName;
        try{
            con=database.connect();
            if(this.conditions.length()>0){
                sql+=" WHERE "+this.conditions;
            }
            sql+=" LIMIT 1";
            ps=con.prepareStatement(sql);
            response=ps.executeQuery();
        }catch(Exception e){
            System.out.println("il ya une error de l'insertion\n"+e);
        }
        return response;
    }

    public <T> orm WHERE(String Column, String operateur, T condition) throws Exception {
        try{
          if(this.conditions.length() >0){
              this.conditions.append(" AND ");
          }
            conditions.append(Column).append(operateur).append("'"+condition+"'");
            return this;
        }catch(Exception e){
            System.out.println("il ya une error de condition\n"+e);
        }
        return this;
    }
    public <T> orm orWHERE(String Column, String operateur, T condition) throws Exception {
        try{
            if(this.conditions.length() >0){
                this.conditions.append(" OR ");
            }
            conditions.append(Column).append(operateur).append("'"+condition+"'");
            return this;
        }catch(Exception e){
            System.out.println("il ya une error de condition\n"+e);
        }
        return this;
    }

    public static ResultSet query(String query) throws Exception {
        db database=new db();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet response=null;
        if(query.length()>0){
            con=database.connect();
            ps=con.prepareStatement(query);
            response=ps.executeQuery();
            if (response.next()){
                return response;
            }
            return null;
        }
        return null;
    }
    public static boolean queryUpdate(String query) throws Exception {
        db database=new db();
        if(query.length()>0){
            Connection con=database.connect();
            PreparedStatement ps=con.prepareStatement(query);
            ps.executeUpdate();
            return true;
        }
        return false;
    }
    public <T> int find(String TableName,String key, T condition) throws Exception{
        orm ormFind=new orm(TableName);
        ResultSet response=ormFind.WHERE(key,"=",condition).get();
        if (response.next()){
            return response.getInt("id");
        }
        return 0;

    }

}
