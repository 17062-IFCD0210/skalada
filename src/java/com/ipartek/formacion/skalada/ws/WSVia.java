package com.ipartek.formacion.skalada.ws;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.bean.Via;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;
import com.ipartek.formacion.skalada.modelo.ModeloVia;

public class WSVia implements ServiceSOAP {
	
	private ModeloVia modeloVia;	
	private ModeloUsuario modeloUsuario;
	private Usuario admin;
	
	private ArrayList<Via> vias;
	private Via via;
	
	public WSVia() {
		init();
	}
	
	private void init(){
		modeloVia = new ModeloVia();
		modeloUsuario = new ModeloUsuario();
		admin = modeloUsuario.getById(Constantes.ROLE_ID_ADMIN);
	}

	@Override
	public List<ViaPOJO> vias() {		
		ArrayList<ViaPOJO> resul = new ArrayList<ViaPOJO>(); 
		vias = modeloVia.getAll(admin);
		
		for(int i=0; i<vias.size(); i++){
			resul.add(mapeo(vias.get(i)));
		}
				
		return resul;
	}

	@Override
	public ViaPOJO viaDetalle(int id) {
		ViaPOJO resul = new ViaPOJO();
		
		via = modeloVia.getById(id);
		
		resul = mapeo(via);	
		
		return resul;
	}

	private ViaPOJO mapeo(Via via){
		ViaPOJO resul = new ViaPOJO();
		
		resul.setId(via.getId());
		resul.setNombre(via.getNombre());
		resul.setDescripcion(via.getDescripcion());
		resul.setLongitud(via.getLongitud());
		resul.setValidado(via.isValidado());
		
		resul.setGrado(via.getGrado().getNombre());
		resul.setTipoEscalada(via.getTipoEscalada().getNombre());
		resul.setSector(via.getSector().getNombre());
		resul.setZona(via.getSector().getZona().getNombre());
		resul.setUsuario(via.getUsuario().getNombre());	
		
		return resul;
	}
	
}
