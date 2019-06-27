package Factura;



import java.io.Serializable;

public class Factura  implements Serializable {

    public Double getMonto() {
        return Monto;
    }

    public Factura(String Empresa, int IdFactura, Double Monto) {
        this.Empresa = Empresa;
        this.IdFactura = IdFactura;
        this.Monto = Monto;
    }
    private String Empresa;
    private int IdFactura;
    private Double Monto;

    public String getEmpresa() {
        return Empresa;
    }

    public int getIdFactura() {
        return IdFactura;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }

    public void setIdFactura(int IdFactura) {
        this.IdFactura = IdFactura;
    }

    public void setMonto(Double Monto) {
        this.Monto = Monto;
    }
    
    
}
