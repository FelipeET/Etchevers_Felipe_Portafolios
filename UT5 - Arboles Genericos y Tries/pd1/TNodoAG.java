
package ut5_pd1;

/**
 *
 * @author 
 */
public class TNodoAG<T> implements INodoAG<T> {
    
    private Comparable etiqueta;
    private INodoAG<T> primerHijo;
    private INodoAG<T> hermano;

    public TNodoAG(String etiqueta) {
        this.etiqueta = etiqueta;
        this.primerHijo = null;
        this.hermano = null;
    }
    
    @Override
    public Comparable getEtiqueta(){
        return this.etiqueta;
    }
    
    @Override
    public INodoAG<T> getPrimerHijo(){
        return this.primerHijo;
    }
    
    @Override
    public INodoAG<T> getHermano(){
        return this.hermano;
    }

    @Override
    public void setPrimerHijo(INodoAG<T> hijo){
        this.primerHijo = hijo;
    }

    @Override
    public void setHermano(INodoAG<T> hermano){
        this.hermano = hermano;
    }
    
    @Override
    public boolean insertar(String unaEtiqueta, Comparable etiquetaPadre){
        if (unaEtiqueta != etiqueta){
            if (etiquetaPadre == etiqueta){
                INodoAG nodo = new TNodoAG(unaEtiqueta);
                if (getPrimerHijo() == null){
                    setPrimerHijo(nodo);
                    return true;
                } else {
                    INodoAG nodoAuxiliar = getPrimerHijo();
                    while (nodoAuxiliar.getHermano() != null){
                        if (nodoAuxiliar.getEtiqueta() == unaEtiqueta){
                            return false;
                        } else {
                            nodoAuxiliar = nodoAuxiliar.getHermano();
                        }
                    }
                    if (nodoAuxiliar.getEtiqueta() != unaEtiqueta){
                        nodoAuxiliar.setHermano(nodo);
                        return true;
                    }
                }
            } else {
                boolean insertado = false;
                if (getHermano() != null){
                    insertado = getHermano().insertar(unaEtiqueta, etiquetaPadre);
                }
                if (insertado == false && getPrimerHijo() != null){
                    insertado = getPrimerHijo().insertar(unaEtiqueta, etiquetaPadre);
                }
                return insertado;
            }
        }
        return false;
    }

    @Override
    public INodoAG<T> buscar(Comparable unaEtiqueta){
        if (etiqueta == unaEtiqueta){
            return this;
        } else {
            INodoAG hijo = getPrimerHijo();
            while (hijo != null){
                INodoAG x = hijo.buscar(unaEtiqueta);
                if (x != null){
                    return x;
                }
                hijo = hijo.getHermano();
            }
        }
        return null;
    }

    @Override
    public String listarIndentado(String listado){
        listado += getEtiqueta() + " - ";
        if (getPrimerHijo() != null){
            listado = getPrimerHijo().listarIndentado(listado);
        }
        if (getHermano() != null){
            listado = getHermano().listarIndentado(listado);
        }
        return listado;
    }
    
}
