/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut7.pd5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 *
 * @author felipe
 */
public class TVertice<T> implements IVertice {

    private final Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private int numBp;
    private int numBajo;

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public T getDatos() {
        return datos;
    }
    
    @Override
    public void bpf(LinkedList<TVertice> visitados) {
        visitados.add(this);
        this.visitado = true;
        
        for (TAdyacencia a : this.adyacentes) {
            TVertice v = a.getDestino();
            if (!v.getVisitado()) {
                v.bpf(visitados);
            }
        }
    }
    
    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        setVisitado(true);
        for (TAdyacencia adyacencia : this.adyacentes) {
            TVertice destino = adyacencia.getDestino();

            if (!destino.visitado) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    caminoPrevio.agregarAdyacencia(adyacencia);
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                }
            }
        }
        this.visitado = false;
        return todosLosCaminos;
    }
    
    @Override
    public boolean tieneCiclo() {
        setVisitado(true);
        for (TVertice verticeAdy : this.adyacentes.stream().map(TAdyacencia::getDestino).collect(Collectors.toList())) {
            if(verticeAdy.visitado) {
                return true;
            }
            return verticeAdy.tieneCiclo();
        }
        return false;
    }
    
    @Override
    public void clasificacionTopologica(LinkedList<TVertice> vertices){
        setVisitado(true);

        for (TAdyacencia adyacente : this.adyacentes) {
            TVertice destino = adyacente.getDestino();
            if(!destino.visitado){
                destino.clasificacionTopologica(vertices);
            }
        }
        vertices.add(this);
    }
    
    @Override
    public void bea(LinkedList<TVertice> visitados) {
        setVisitado(true);
        Queue<TVertice> cola = new LinkedList<>();
        cola.add(this);
        visitados.add(this);
        while (!cola.isEmpty()) {
            TVertice vertice = cola.remove();
            LinkedList<TAdyacencia> Adyacentes = vertice.getAdyacentes();
            for (TAdyacencia ad : Adyacentes) {
                TVertice destino = ad.getDestino();
                if (!destino.visitado) {
                    destino.setVisitado(true);
                    cola.add(destino);
                    visitados.add(destino);
                }
            }
        }
    }
    
    public void puntosArticulacion(LinkedList<TVertice> verticesArticulacion, int prof) {
        prof++;
        this.numBp = prof;
        this.numBajo = prof;

        LinkedList<TVertice> hijos = new LinkedList<>();

        for (TAdyacencia adyacencia : this.adyacentes) {
            TVertice adyacente = adyacencia.getDestino();

            if (!adyacente.getVisitado()) {
                adyacente.puntosArticulacion(verticesArticulacion, prof);
                hijos.add(adyacente);
                this.numBajo = Math.min(this.numBajo, adyacente.numBajo);
            } else {
                this.numBajo = Math.min(this.numBajo, adyacente.numBp);
            }
        }

        if (this.numBp > 1) {
            for (TVertice hijo : hijos) {
                if (hijo.numBajo >= this.numBp) {
                    verticesArticulacion.add(this);
                }
            }
        } else {
            if (hijos.size() > 1) {
                verticesArticulacion.add(this);
            }
        }
    }
    
    public boolean conectadoCon(TVertice destino) {
        if (this.etiqueta.compareTo(destino.getEtiqueta()) == 0) {
            return true;
        }

        this.setVisitado(true);
        for (TAdyacencia adyacencia : adyacentes) {
            TVertice verticeAdyacente = adyacencia.getDestino();
            if (!verticeAdyacente.getVisitado()) {
                if (verticeAdyacente.conectadoCon(destino)) {
                    return true;
                }
            }
        }

        return false;
    }

}
