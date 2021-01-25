
package com.example.prototipo.retrofit.response.atenciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PacienteObj {

    @SerializedName("Documento")
    @Expose
    private String documento;
    @SerializedName("Apellidos")
    @Expose
    private String apellidos;
    @SerializedName("Nombres")
    @Expose
    private String nombres;
    @SerializedName("Sexo")
    @Expose
    private String sexo;
    @SerializedName("Fecha_Nacimiento")
    @Expose
    private String fechaNacimiento;
    @SerializedName("Direccion")
    @Expose
    private String direccion;
    @SerializedName("Telefono")
    @Expose
    private String telefono;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Tipo")
    @Expose
    private String tipo;
    @SerializedName("N_historia_Clinica")
    @Expose
    private Integer nHistoriaClinica;
    @SerializedName("Autogenerado")
    @Expose
    private String autogenerado;
    @SerializedName("Tipo_Asegurado")
    @Expose
    private String tipoAsegurado;
    @SerializedName("Estado")
    @Expose
    private String estado;
    @SerializedName("Fecha_registro")
    @Expose
    private String fechaRegistro;
    @SerializedName("Fecha_modificacion")
    @Expose
    private String fechaModificacion;
    @SerializedName("Empleado_registro")
    @Expose
    private String empleadoRegistro;
    @SerializedName("Empleado_modificacion")
    @Expose
    private String empleadoModificacion;
    @SerializedName("Id_Sede")
    @Expose
    private Integer idSede;
    @SerializedName("sedeObj")
    @Expose
    private Object sedeObj;

    public Object getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Object getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Object getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNHistoriaClinica() {
        return nHistoriaClinica;
    }

    public void setNHistoriaClinica(Integer nHistoriaClinica) {
        this.nHistoriaClinica = nHistoriaClinica;
    }

    public String getAutogenerado() {
        return autogenerado;
    }

    public void setAutogenerado(String autogenerado) {
        this.autogenerado = autogenerado;
    }

    public String getTipoAsegurado() {
        return tipoAsegurado;
    }

    public void setTipoAsegurado(String tipoAsegurado) {
        this.tipoAsegurado = tipoAsegurado;
    }

    public Object getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Object getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Object getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Object getEmpleadoRegistro() {
        return empleadoRegistro;
    }

    public void setEmpleadoRegistro(String empleadoRegistro) {
        this.empleadoRegistro = empleadoRegistro;
    }

    public Object getEmpleadoModificacion() {
        return empleadoModificacion;
    }

    public void setEmpleadoModificacion(String empleadoModificacion) {
        this.empleadoModificacion = empleadoModificacion;
    }

    public Object getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public Object getSedeObj() {
        return sedeObj;
    }

    public void setSedeObj(Object sedeObj) {
        this.sedeObj = sedeObj;
    }

}
