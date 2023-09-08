package ORM;
import database.db;
import java.sql.*;
import java.util.Map;


public class orm {

    private StringBuilder conditions;
    private String tableName;

    public orm(String table_name){
        this.tableName=table_name;
        this.conditions=new StringBuilder();
    }
    public boolean save(Map<String, String> array) throws Exception {
        db database=new db();
        Connection con=null;
        PreparedStatement ps=null;
        int index=0;
        try{
            con=database.connect();
            String sql="Insert INTO "+this.tableName+"(";
            for(Map.Entry<String, String> items : array.entrySet()){
                if (index<array.size()-1){
                    sql+=items.getKey() + "," ;
                }else{
                    sql+=items.getKey() + ")" ;
                }
                index++;
            }
            sql+="values (";
            index=0;
            for(Map.Entry<String, String> items : array.entrySet()){
                if (index<array.size()-1){
                    sql+="'"+items.getValue() + "'," ;
                }else{
                    sql+="'"+items.getValue() + "')" ;
                }
                index++;
            }
            ps=con.prepareStatement(sql);
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("il ya une error de l'insertion\n"+e);
            return  false;
        }

    }
    public boolean update(Map<String, String> array) throws Exception {
        db database=new db();
        Connection con=null;
        PreparedStatement ps=null;
        String sql="UPDATE FROM "+tableName;
        try{
            con=database.connect();
            if(this.conditions.length()>0){
                sql+=" WHERE "+this.conditions;
                System.out.println(sql);
                ps=con.prepareStatement(sql);
                ps.executeUpdate();
            }else {
                System.err.println("Error: No conditions specified for the SQL query.");
            }
        }catch(Exception e){
            System.out.println("il ya une error de l'insertion\n"+e);
        }

        return true;
    }
    public ResultSet get() throws Exception {
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
                System.out.println(sql);
                ps=con.prepareStatement(sql);
                ps.executeUpdate();
            }else {
                System.err.println("Error: No conditions specified for the SQL query.");
            }
        }catch(Exception e){
            System.out.println("il ya une error de l'insertion\n"+e);
        }

        return true;
    }


    public orm WHERE(String Column, String operateur, String condition) throws Exception {
        try{
          if(this.conditions.length() >0){
              this.conditions.append(" AND ");
          }
            conditions.append(Column).append(operateur).append(condition);
            return this;
        }catch(Exception e){
            System.out.println("il ya une error de l'insertion\n"+e);
        }
        return this;
    }
    public orm orWHERE(String Column, String operateur, String condition) throws Exception {
        try{
            if(this.conditions.length() >0){
                this.conditions.append(" OR ");
            }
            conditions.append(Column).append(operateur).append(condition);
            return this;
        }catch(Exception e){
            System.out.println("il ya une error de l'insertion\n"+e);
        }
        return this;
    }
}
