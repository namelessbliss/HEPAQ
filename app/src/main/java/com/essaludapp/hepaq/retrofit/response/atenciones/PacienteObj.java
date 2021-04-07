
package com.essaludapp.hepaq.retrofit.response.atenciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PacienteObj {

    @SerializedName("Documento")
    @Expose
    public String documento;
    @SerializedName("Apellidos")
    @Expose
    public String apellidos;
    @SerializedName("Nombres")
    @Expose
    public String nombres;
    @SerializedName("Sexo")
    @Expose
    public String sexo;
    @SerializedName("Fecha_Nacimiento")
    @Expose
    public String fechaNacimiento;
    @SerializedName("Direccion")
    @Expose
    public String direccion;
    @SerializedName("Telefono")
    @Expose
    public String telefono;
    @SerializedName("Email")
    @Expose
    public String email;
    @SerializedName("Tipo")
    @Expose
    public String tipo;
    @SerializedName("N_historia_Clinica")
    @Expose
    public Integer nHistoriaClinica;
    @SerializedName("Autogenerado")
    @Expose
    public String autogenerado;
    @SerializedName("Tipo_Asegurado")
    @Expose
    public String tipoAsegurado;
    @SerializedName("Estado")
    @Expose
    public String estado;
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
    @SerializedName("Id_Sede")
    @Expose
    public Integer idSede;

    @SerializedName("sedeObj")
    @Expose
    public SedeObj sedeObj;

    public PacienteObj() {
    }

    public String getDocumento() {
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
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

    public Integer getnHistoriaClinica() {
        return nHistoriaClinica;
    }

    public void setnHistoriaClinica(Integer nHistoriaClinica) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public SedeObj getSedeObj() {
        return sedeObj;
    }

    public void setSedeObj(SedeObj sedeObj) {
        this.sedeObj = sedeObj;
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

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }
}
