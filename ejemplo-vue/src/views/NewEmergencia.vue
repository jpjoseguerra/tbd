<template>
    <div class="row">
    <div class="col-4 container-fluid">
        <h1>Agregar una emergencia</h1>
        <form>
            <div>
                <label for="name">Nombre</label>
            <input type="text" id="nombre" v-model="newEmergencia.nombre">
            </div>

            <div>
                <label for="descrip">Descripción</label>
            <input type="text" id="descrip" v-model="newEmergencia.descrip">
            </div>

            <div>
                <label for="finicio">Fecha de inicio</label>
            <input type="date" id="finicio" v-model="newEmergencia.finicio">
            </div>

            <div>
                <label for="ffin">Fecha de termino</label>
            <input type="date" id="ffin" v-model="newEmergencia.ffin">
            </div>

            <div>
                <label for="id_institucion">Elegir la institución encargada</label>
            <select id="id_institucion" v-model="newEmergencia.id_institucion">
                <option v-bind:value="institution.id" v-for="(institution, index) in insts" :key="index">{{institution.nombre}}</option>
            </select>
            </div>
            <div class="form-group">
                <label for="latitud">Latitud</label>
            <input class="form-control" :placeholder="ubicacion.lat" :readonly="ubicacion.lat" id="latitud">
            </div>
            <div class="form-group">
                <label for="longitud">Longitud</label>
            <input class="form-control" :placeholder="ubicacion.lng" :readonly="ubicacion.lng" id="longitud">
            </div>
            <div>
                <button type="button" @click="send">Crear</button>
            </div>
            <div class="info">
                <h2>Objeto</h2>
                <code>{{newEmergencia}}</code>
                <p class="message">
                    {{message}}
                </p>
            </div>
        </form>
    </div>
        <div id="mapa" class="container col-6 map">
            <l-map :zoom="zoom" :center="center" @click="getUbicacion">
                <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
                <l-marker
                    :key="'eme' + index"
                    v-for="(eme, index) in emergencias"
                    :lat-lng="L.latLng(eme.latitud, eme.longitud)"             
                >
                <l-icon
                    :icon-size="emergencia_tamanio"
                    :icon-url="emergencia_icono"
                >
                </l-icon>
                </l-marker>       
            </l-map>
        </div>
    </div>
</template>

<script>

import L from 'leaflet'
import { LMap, LTileLayer, LMarker } from 'vue2-leaflet'
import emergencia_icono from '../assets/emergencia_icono.png'

export default {
    
    data(){
        return{
            url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
            attribution: '&copy; <a href="http://osm.org/copyright">OpenStre,etMap</a> contributors',
            center: L.latLng(-33.561995, -70.562439),
            message:'',
            newEmergencia:{},
            insts:[],
            emergencias:[],
            ubicacion: L.latLng(-33.561995, -70.562439),
            zoom: 7,
            emergencia_icono: emergencia_icono,
            emergencia_tamanio: [8, 8]
        }
    },
    components: {
        LMap,
        LTileLayer,
        LMarker
    },
    methods:{
        
        
        send:async function(){
            this.message = '';
            this.newEmergencia.latitud = this.ubicacion.lat;
            this.newEmergencia.longitud = this.ubicacion.lng;
            if(!this.newEmergencia.nombre){
                this.message = 'Debe ingresar un nombre'
                return false
            }
            if(!this.newEmergencia.descrip){
                this.message = 'Debe ingresar una descripción'
                return false
            }
            if(!this.newEmergencia.finicio){
                this.message = 'Debe ingresar una fecha de inicio'
                return false
            }
            if(!this.newEmergencia.ffin){
                this.message = 'Debe ingresar una fecha de termino'
                return false
            }
            if(!this.newEmergencia.id_institucion){
                this.message = 'Debe ingresar una institución encargada de la emergencia'
                return false
            }
            try {
                var result = await this.$http.post('/emergencias', this.newEmergencia);
                let emergencia = result.data;
                this.message = `Se creó una nueva emergencia con id: ${emergencia.id}`;
                this.newEmergencia = {};
            } catch (error) {
                console.log('error', error)
                this.message = 'Ocurrió un error'
            }
            this.getEmergencias(); 
            
        },
        
        getInsts:async function(){
            try {
                let response = await this.$http.get('/instituciones');
                this.insts  = response.data;
                console.log(response);
            } catch (error) {
                console.log('error', error);
            }
        },
        getEmergencias: async function(){
             try {
                let response = await this.$http.get('/emergencias');
                this.emergencias  = response.data;
                console.log(this.emergencias);
            } catch (error) {
                console.log('error', error);
            }
        },
        getUbicacion: function(e){
          this.ubicacion = e.latlng;
      },         
    },
    created:function(){
        this.getInsts();
        this.getEmergencias();
    }
}
</script>
<style lang="scss">
code{
    padding: .5rem 1rem;
    color:white;
    background:#444;
    font-size: 1rem;
    width: 12rem;
    display:inline-block;
}
p.message{
    margin:0 auto;
    width: 12rem;
    border:solid 1px rgba(0,0,0,0.25);
    padding: .5rem 1rem;
    font-weight: bold;
}
#map {
    position: relative;
    height: 500px;
    width: 500px;
}
</style>