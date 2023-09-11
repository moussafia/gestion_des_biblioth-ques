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
    public <k, v> boolean save(Map<k, v> array) throws Exception {
        db database=new db();
        Connection con=null;
        PreparedStatement ps=null;
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
        int index=0;
        try{
            con=database.connect();
            if(conditions.length()>0){
                String sql="UPDATE "+this.tableName+" SET ";
                for(Map.Entry<String, String> items : array.entrySet()){
                    if (index<array.size()-1){
                        sql+=items.getKey() + "='"+ items.getValue() + "'," ;
                    }else{
                        sql+=items.getKey()+ "='"+ items.getValue()  + "' WHERE " ;
                    }
                    index++;
                }
                sql+=conditions;
                ps=con.prepareStatement(sql);
                ps.execute();
                System.out.println(sql);
                return true;
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
            System.out.println(sql);
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
    public <T>orm orWHERE(String Column, String operateur, T condition) throws Exception {
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

}
