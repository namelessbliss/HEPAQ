
package com.example.prototipo.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("id_Personal")
    @Expose
    private Integer idPersonal;
    @SerializedName("Apellido")
    @Expose
    private String apellido;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Sexo")
    @Expose
    private String sexo;
    @SerializedName("Fecha_Nacim")
    @Expose
    private String fechaNacim;
    @SerializedName("Direccion")
    @Expose
    private String direccion;
    @SerializedName("Celular")
    @Expose
    private String celular;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("documento")
    @Expose
    private String documento;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("Fecha_Ingreso")
    @Expose
    private Object fechaIngreso;
    @SerializedName("Sueldo")
    @Expose
    private Double sueldo;
    @SerializedName("Estado")
    @Expose
    private String estado;
    @SerializedName("CMP")
    @Expose
    private String cMP;
    @SerializedName("Usuario")
    @Expose
    private String usuario;
    @SerializedName("Contrasena")
    @Expose
    private Object contrasena;
    @SerializedName("Fecha_registro")
    @Expose
    private Object fechaRegistro;
    @SerializedName("Fecha_modificacion")
    @Expose
    private Object fechaModificacion;
    @SerializedName("Empleado_registro")
    @Expose
    private Object empleadoRegistro;
    @SerializedName("Empleado_modificacion")
    @Expose
    private Object empleadoModificacion;
    @SerializedName("Id_perfil")
    @Expose
    private Integer idPerfil;
    @SerializedName("perfilObj")
    @Expose
    private PerfilObj perfilObj;
    @SerializedName("accesoObj")
    @Expose
    private AccesoObj accesoObj;

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

    public Object getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Object fechaIngreso) {
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

    public String getCMP() {
        return cMP;
    }

    public void setCMP(String cMP) {
        this.cMP = cMP;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Object getContrasena() {
        return contrasena;
    }

    public void setContrasena(Object contrasena) {
        this.contrasena = contrasena;
    }

    public Object getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Object fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Object getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Object fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Object getEmpleadoRegistro() {
        return empleadoRegistro;
    }

    public void setEmpleadoRegistro(Object empleadoRegistro) {
        this.empleadoRegistro = empleadoRegistro;
    }

    public Object getEmpleadoModificacion() {
        return empleadoModificacion;
    }

    public void setEmpleadoModificacion(Object empleadoModificacion) {
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

}
