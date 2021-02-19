
package com.example.prototipo.retrofit.response.atenciones;

import com.example.prototipo.retrofit.response.AccesoObj;
import com.example.prototipo.retrofit.response.PerfilObj;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonalObj {

    @SerializedName("id_Personal")
    @Expose
    public Integer idPersonal;
    @SerializedName("Apellido")
    @Expose
    public String apellido;
    @SerializedName("Nombre")
    @Expose
    public String nombre;
    @SerializedName("Sexo")
    @Expose
    public String sexo;
    @SerializedName("Fecha_Nacim")
    @Expose
    public String fechaNacim;
    @SerializedName("Direccion")
    @Expose
    public String direccion;
    @SerializedName("Celular")
    @Expose
    public String celular;
    @SerializedName("Email")
    @Expose
    public String email;
    @SerializedName("documento")
    @Expose
    public String documento;
    @SerializedName("tipo")
    @Expose
    public String tipo;
    @SerializedName("Fecha_Ingreso")
    @Expose
    public String fechaIngreso;
    @SerializedName("Sueldo")
    @Expose
    public Double sueldo;
    @SerializedName("Estado")
    @Expose
    public String estado;
    @SerializedName("CMP")
    @Expose
    public String cMP;
    @SerializedName("Usuario")
    @Expose
    public String usuario;
    @SerializedName("Contrasena")
    @Expose
    public String contrasena;
    @SerializedName("Fecha_registro")
    @Expose
    public String fechaRegistro;
    @SerializedName("Fecha_modificacion")
    @Expose
    public String fechaModificacion;
    @SerializedName("Empleado_registro")
    @Expose
    public String empleadoRegistro;
    @SerializedName("Empleado_modificacion")
    @Expose
    public String empleadoModificacion;
    @SerializedName("Id_perfil")
    @Expose
    public Integer idPerfil;
    @SerializedName("perfilObj")
    @Expose
    public PerfilObj perfilObj;
    @SerializedName("accesoObj")
    @Expose
    public AccesoObj accesoObj;

    public Integer getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Integer idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacim() {
        return fechaNacim;
    }

    public void setFechaNacim(String fechaNacim) {
        this.fechaNacim = fechaNacim;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getcMP() {
        return cMP;
    }

    public void setcMP(String cMP) {
        this.cMP = cMP;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getEmpleadoRegistro() {
        return empleadoRegistro;
    }

    public void setEmpleadoRegistro(String empleadoRegistro) {
        this.empleadoRegistro = empleadoRegistro;
    }

    public String getEmpleadoModificacion() {
        return empleadoModificacion;
    }

    public void setEmpleadoModificacion(String empleadoModificacion) {
        this.empleadoModificacion = empleadoModificacion;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public PerfilObj getPerfilObj() {
        return perfilObj;
    }

    public void setPerfilObj(PerfilObj perfilObj) {
        this.perfilObj = perfilObj;
    }

    public AccesoObj getAccesoObj() {
        return accesoObj;
    }

    public void setAccesoObj(AccesoObj accesoObj) {
        this.accesoObj = accesoObj;
    }

    public PersonalObj() {
    }
}
