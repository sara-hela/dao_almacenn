
package com.mycompany.dao;

import com.mycompany.modelo.Aviso;
import com.mycompany.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AvisoDAOImpl extends ConexionDB implements AvisoDAO {

    @Override
    public void insert(Aviso aviso) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps= this.conn.prepareStatement("INSERT into avisos(id,descripcion,cantidad,precio,categoria) values(?,?,?,?,?)");
            ps.setInt(1, aviso.getId());
            ps.setString(2, aviso.getDescripcion());
            ps.setInt(3, aviso.getCantidad());
            ps.setFloat(4, aviso.getPrecio());
            ps.setString(5, aviso.getCategoria());
            ps.executeUpdate();
        }
        catch(Exception e){
            throw e;
        } finally{
            this.desconectar();
        }
    }

    @Override
    public void update(Aviso aviso) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps= this.conn.prepareStatement("UPDATE avisos set descripcion=?,cantidad=?,precio=?,categoria=? where id=?");
            ps.setString(1, aviso.getDescripcion());
            ps.setInt(2, aviso.getCantidad());
            ps.setFloat(3, aviso.getPrecio());
            ps.setString(4, aviso.getCategoria());
            ps.setInt(5, aviso.getId());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }
    @Override
    public void delete(int id) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps= this.conn.prepareStatement("DELETE FROM avisos WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }
    @Override
    public Aviso getById(int id) throws Exception {
        
        Aviso avi= new Aviso();
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM avisos WHERE id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                avi.setId(rs.getInt("id"));
                avi.setDescripcion(rs.getString("descripcion"));
                avi.setCantidad(rs.getInt("cantidad"));
                avi.setPrecio(rs.getFloat("Precio"));
                avi.setCategoria(rs.getString("categoria"));
            }
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
        return avi;
    }
    @Override
    public List<Aviso> getAll() throws Exception {
        List<Aviso> lista= null;
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM avisos");
            ResultSet rs = ps.executeQuery();
            
            lista= new ArrayList<Aviso>();
            while(rs.next()){
                Aviso avi = new Aviso();
                avi.setId(rs.getInt("id"));
                avi.setDescripcion(rs.getString("descripcion"));
                avi.setCantidad(rs.getInt("cantidad"));
                avi.setPrecio(rs.getFloat("Precio"));
                avi.setCategoria(rs.getString("categoria"));
                lista.add(avi);
            }
            rs.close();
            ps.close();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
        return lista;
    }  
}
